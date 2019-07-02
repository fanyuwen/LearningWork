package com.learning;

import com.learning.feigninterface.SSSS;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.client.ClientFactory;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.*;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;
import feign.slf4j.Slf4jLogger;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.inject.Provider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author fanyuwen
 * @date 2019/6/30 19:37
 */
@Configuration
@SuppressWarnings("deprecation")
@ComponentScan("com.learning.feigninterface")
public class EurekaOrigin {

    public static void main(String[] args) throws Exception {
        try (AnnotationConfigApplicationContext configurableApplicationContext = new AnnotationConfigApplicationContext(EurekaOrigin.class)) {
            SSSS request = configurableApplicationContext.getBean(SSSS.class);
            request.showMessage("柏人杰");
        }
    }

    @Bean
    @SuppressWarnings("deprecation")
    public EurekaClient eurekaClient() {
        EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
        ApplicationInfoManager manager = new ApplicationInfoManager(instanceConfig, instanceInfo);
        manager.setInstanceStatus(InstanceInfo.InstanceStatus.STARTING);
        EurekaClient eurekaClient = new DiscoveryClient(manager, new DefaultEurekaClientConfig());

        DiscoveryManager.getInstance().setDiscoveryClient((DiscoveryClient) eurekaClient);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        manager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        return eurekaClient;
    }

    @Bean
    @Qualifier("springcloudfeignNames")
    List<String> generateServers() {
        return checkPackageClassNameList(Arrays.asList(
                "com.learning.feigninterface.SpringFeignRequest",
                "com.learning.feigninterface.SpringFeignRequest1"
        ));
    }

    private List<String> checkPackageClassNameList(List<String> serverList) {
        return serverList;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public Object buildFeign(
            @Qualifier("springcloudfeignNames") @Autowired List<String> serverNames
    ) throws Exception {
        //加载配置文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        ConfigurationManager.loadProperties(
                convertFileToProperties(resolver.getResource("classpath:eureka-client.properties"))
        );
        ConfigurationManager.loadProperties(
                convertFileToProperties(resolver.getResource("classpath:ribbon.properties"))
        );
        class EurekaClientProvider implements Provider<EurekaClient> {
            @Override
            public EurekaClient get() {
                return eurekaClient();
            }
        }

        final String FEIGN_CLIENT_ANNO = "org.springframework.cloud.netflix.feign.FeignClient",
                FEIGN_METHOD_ANNO = "org.springframework.web.bind.annotation.RequestMapping",
                ORIGIN_FEIGN_CLIENT = "feign.Headers",
                ORIGIN_FEIGN_LINE = "feign.RequestLine";

        ClassPool cp = ClassPool.getDefault();

        ServerListFilter<DiscoveryEnabledServer> filter
                = new ZoneAffinityServerListFilter<>();

        for (String serverPackageClassName : serverNames) {

            //对应的feign接口
            CtClass ctClass_feignInterface = cp.get(serverPackageClassName);

            String beanName = ctClass_feignInterface.getSimpleName();

            beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
            //获得类常量池
            ConstPool constPool = ctClass_feignInterface.getClassFile().getConstPool();
            //获取类的注解属性
            AnnotationsAttribute attribute = new AnnotationsAttribute(constPool,
                    AnnotationsAttribute.visibleTag,
                    ctClass_feignInterface.getAttribute(AnnotationsAttribute.visibleTag));

            //获取指定的注解
            Annotation annotation = attribute.getAnnotation(FEIGN_CLIENT_ANNO);
            StringMemberValue serverNameValue = (StringMemberValue) annotation.getMemberValue("name");
            //从注解中获取指定的服务名
            String serverName = serverNameValue.getValue();

            IRule rule = irule();

            ServerList<DiscoveryEnabledServer> list = new DiscoveryEnabledNIWSServerList(serverName, new EurekaClientProvider());

            ZoneAwareLoadBalancer<DiscoveryEnabledServer> lb = LoadBalancerBuilder.<DiscoveryEnabledServer>newBuilder()
                    .withDynamicServerList(list)
                    .withRule(rule)
                    .withServerListFilter(filter)
                    .buildDynamicServerListLoadBalancer();

            //构建ribbon
            RibbonClient ribbonClient = RibbonClient.builder().lbClientFactory(new LBClientFactory() {
                @Override
                public LBClient create(String clientName) {
                    return LBClient.create(lb, ClientFactory.getNamedConfig(clientName));
                }
            }).build();

            //将类上的Spring feign注释删除
            attribute.removeAnnotation(FEIGN_CLIENT_ANNO);
            //添加@Headers注解
            Annotation annotation_class = new Annotation(ORIGIN_FEIGN_CLIENT, constPool);
            ArrayMemberValue memberValue = new ArrayMemberValue(constPool);
            memberValue.setValue(new MemberValue[]{new StringMemberValue("Content-Type: application/json", constPool),
                    new StringMemberValue("Accept: application/json", constPool)});
            annotation_class.addMemberValue("value", memberValue);
            attribute.addAnnotation(annotation_class);
            //将注解添加到feign接口上
            ctClass_feignInterface.setAttribute(AnnotationsAttribute.visibleTag, attribute.get());
            //遍历该class下面的所有方法,将方法上的@RequestMapping注解去除掉,添加@RequestLine注解
            CtMethod[] ctMethods = ctClass_feignInterface.getDeclaredMethods();

            for (CtMethod ctMethod : ctMethods) {
                MethodInfo methodInfo = ctMethod.getMethodInfo();
                constPool = methodInfo.getConstPool();
                AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);

                Annotation annotation_method = annotationsAttribute.getAnnotation(FEIGN_METHOD_ANNO);
                ArrayMemberValue usrl_members = (ArrayMemberValue) annotation_method.getMemberValue("value");
                ArrayMemberValue method_members = (ArrayMemberValue) annotation_method.getMemberValue("method");
//            ArrayMemberValue produces_members = (ArrayMemberValue) annotation_method.getMemberValue("produces");
//            ArrayMemberValue consumes_members = (ArrayMemberValue) annotation_method.getMemberValue("consumes");

                if (annotationsAttribute.removeAnnotation(FEIGN_METHOD_ANNO)) {
                    Annotation annotation_new = new Annotation(ORIGIN_FEIGN_LINE, constPool);
                    String requestlineURL =
                            ((EnumMemberValue) method_members.getValue()[0]).getValue() + " "
                                    + ((StringMemberValue) usrl_members.getValue()[0]).getValue();
                    annotation_new.addMemberValue("value", new StringMemberValue(requestlineURL, constPool));
                    annotationsAttribute.setAnnotation(annotation_new);
                }
            }

            applicationContext.getBeanFactory()
                    .registerSingleton(beanName, Feign.builder()
                    .client(ribbonClient)
                    .logger(new Slf4jLogger())
                    .logLevel(Logger.Level.FULL)
                    .encoder(new JacksonEncoder())
                    .decoder(new JacksonDecoder())
                    .target(ctClass_feignInterface.toClass(Thread.currentThread().getContextClassLoader()), "http://" + serverName));
        }

        return new Object();
    }

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private Properties convertFileToProperties(Resource resource) throws IOException {
        File file = resource.getFile();
        Properties properties = new Properties();
        if (file != null) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                properties.load(fileInputStream);
            }
        }
        return properties;
    }

    /**
     * 监听应用关闭的事件
     */
    static class EurekaDispose implements ApplicationListener<ContextClosedEvent> {
        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            if (event.getApplicationContext().getParent() == null) {
                DiscoveryManager.getInstance().shutdownComponent();
            }
        }
    }

    /**
     * 监听应用停止的事件
     */
    static class EurekaStop implements ApplicationListener<ContextStoppedEvent> {
        @Override
        public void onApplicationEvent(ContextStoppedEvent event) {
            if (event.getApplicationContext().getParent() == null) {
                DiscoveryManager.getInstance().shutdownComponent();
            }
        }
    }

    @Bean
    public EurekaDispose eurekaDispose() {
        return new EurekaDispose();
    }

    @Bean
    public EurekaStop eurekaStop() {
        return new EurekaStop();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IRule irule() {
        return new AvailabilityFilteringRule();
    }

//    @SuppressWarnings("deprecation")
//    void origin() {
//        //获取eureka中心配置实例
//        EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();
//        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
//        ApplicationInfoManager manager = new ApplicationInfoManager(instanceConfig, instanceInfo);
//        manager.setInstanceStatus(InstanceInfo.InstanceStatus.STARTING);
//
//        EurekaClient eurekaClient = new DiscoveryClient(manager, new DefaultEurekaClientConfig());
//        DiscoveryManager.getInstance().setDiscoveryClient((DiscoveryClient) eurekaClient);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
//        manager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
//        //加载配置
//        final String CONFIG_EUREKA = "eureka-client.properties",
//                CONFIG_RIBBON = "ribbon.properties";
//        try {
//            ConfigurationManager.loadPropertiesFromResources(CONFIG_EUREKA);
//            ConfigurationManager.loadPropertiesFromResources(CONFIG_RIBBON);
//        } catch (IOException e) {
//            System.out.println("ribbon init error");
//        }

    //自动创建ribbon
//        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("testeurekaorigin", false);
//        System.out.println(String.format("------------------------,APOLLO-CONFIGSERVICE= %s:%s", nextServerFromEureka.getIPAddr(), nextServerFromEureka.getPort()));
//        FeignRequest feignRequest = Feign.builder().client(RibbonClient.create())
//                .logger(new Slf4jLogger()).logLevel(Logger.Level.FULL)
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder())
//                .target(FeignRequest.class, "http://testeurekaorigin");
//
//        System.out.println(feignRequest.sayHelloWorld());

//        class EurekaClientProvider implements Provider<EurekaClient> {
//            @Override
//            public EurekaClient get() {
//                return eurekaClient;
//            }
//        }
//
//        //手动创建ribbon
//        IRule rule = new AvailabilityFilteringRule();
//        ServerList<DiscoveryEnabledServer> list
//                = new DiscoveryEnabledNIWSServerList("testeurekaorigin", new EurekaClientProvider());
//        ServerListFilter<DiscoveryEnabledServer> filter
//                = new ZoneAffinityServerListFilter<>();
//
//        ZoneAwareLoadBalancer<DiscoveryEnabledServer> lb = LoadBalancerBuilder.<DiscoveryEnabledServer>newBuilder()
//                .withDynamicServerList(list)
//                .withRule(rule)
//                .withServerListFilter(filter)
//                .buildDynamicServerListLoadBalancer();
//
//        RibbonClient client = RibbonClient.builder().lbClientFactory(new LBClientFactory() {
//            @Override
//            public LBClient create(String clientName) {
//                return LBClient.create(lb, ClientFactory.getNamedConfig(clientName));
//            }
//        }).build();
//
//        //创建feign 接口
//        FeignRequest request = Feign.builder()
//                .client(client)
//                .logger(new Slf4jLogger())
//                .logLevel(Logger.Level.FULL)
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder())
//                .target(FeignRequest.class, "http://testeurekaorigin");
//
//        System.out.println(request.sayHelloWorld());
//    }

}