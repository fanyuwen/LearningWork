package com.learning.dbcp.spring;

import com.learning.StringUtils;
import com.learning.encrypt.des.DES;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    private String direct = System.getProperty("resource_direct");

    private static final String SEPARATECHAR = "/";

    @Bean
    public PropertySourcesPlaceholderConfigurer createPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        if (StringUtils.isNotEmpty(direct)) {
            Resource resource = resourcePatternResolver.getResource("classpath:" + direct + SEPARATECHAR + "config-connect.properties");
            configurer.setLocations(resource);
            return configurer;
        } else {
            throw new RuntimeException("自定义目录路径不能为空");
        }
    }

    /**
     * 配置数据源
     *
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @param initsize
     * @param maxtotal
     * @param maxidle
     * @param minidle
     * @param maxwaitmillis
     * @return
     */
    @Bean
    public DataSource createDataSource(
            @Value("${mybatis.jdbc.driver}") String driverClassName,
            @Value("${mybatis.jdbc.url}") String url,
            @Value("${mybatis.jdbc.username}") String username,
            @Value("${mybatis.jdbc.password}") String password,
            @Value("${mybatis.jdbc.initsize}") int initsize,
            @Value("${mybatis.jdbc.maxtotal}") int maxtotal,
            @Value("${mybatis.jdbc.maxidle}") int maxidle,
            @Value("${mybatis.jdbc.minidle}") int minidle,
            @Value("${mybatis.jdbc.maxwaitmillis}") long maxwaitmillis
    ) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(DES.decrypt(username));
        basicDataSource.setPassword(DES.decrypt(password));
        basicDataSource.setInitialSize(initsize);
        basicDataSource.setMaxTotal(maxtotal);
        basicDataSource.setMaxIdle(maxidle);
        basicDataSource.setMinIdle(minidle);
        basicDataSource.setMaxWaitMillis(maxwaitmillis);
        return basicDataSource;
    }

    /**
     * 配置spring-mybatis的 SqlSessionFactoryBean
     *
     * @param dataSource 数据源
     * @return SqlSessionFactoryBean
     * @throws Exception 异常
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:" + direct + SEPARATECHAR + "mapper" + SEPARATECHAR + "*.xml"));
        return factoryBean;
    }

    /**
     * 配置mapper扫描配置
     *
     * @return MapperScannerConfigurer
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.learning.dbcp.spring.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}