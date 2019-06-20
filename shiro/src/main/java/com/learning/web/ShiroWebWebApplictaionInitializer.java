package com.learning.web;

import com.learning.springconfig.RootConfig;
import com.learning.springconfig.WebConfig;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContainerInitializer;

/**
 * servlet3.0 ��������(web.xml�ļ����õ��������,���web.xml���õķ�ʽ)
 *
 * @author yuwen.fan
 * @date 2019/6/19 15:24
 * https://www.cnblogs.com/frankyou/p/6888566.html
 * @see ServletContainerInitializer ͨ��ʵ�ָýӿ�,servlet�����ܹ�ɨ�赽��������Ӧ�ĳ�ʼ��
 * @see SpringServletContainerInitializer spring�ṩ��ʵ�ָýӿڵ�ʵ��
 * ������ȥɨ��Ѱ��ʵ����{@link WebApplicationInitializer}�ӿڵ�ʵ��,����������onStartup����
 * ����servlet��ʼ��
 * @see AbstractAnnotationConfigDispatcherServletInitializer spring�ṩ�ı�ݵ���(implements WebApplicationInitializer),���ڳ�ʼ��Servlet
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
}