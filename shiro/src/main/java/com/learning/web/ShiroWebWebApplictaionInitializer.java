package com.learning.web;

import com.learning.springconfig.RootConfig;
import com.learning.springconfig.WebConfig;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * servlet3.0 新增特性(web.xml文件配置的替代方案,告别web.xml配置的方式)
 *
 * @author yuwen.fan
 * @date 2019/6/19 15:24
 * https://www.cnblogs.com/frankyou/p/6888566.html
 * @see ServletContainerInitializer 通过实现该接口,servlet容器能够扫描到并进行相应的初始化
 * @see SpringServletContainerInitializer spring提供了实现该接口的实现
 * 它会再去扫描寻找实现了{@link WebApplicationInitializer}接口的实现,并调用它的onStartup方法
 * 进行servlet初始化
 * @see AbstractAnnotationConfigDispatcherServletInitializer spring提供的便捷的类(implements WebApplicationInitializer),用于初始化Servlet
 */
public class ShiroWebWebApplictaionInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 为dispatcherServlet注册filter
     */
    @Override
    protected Filter[] getServletFilters() {
        return super.getServletFilters();
    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));
    }
}