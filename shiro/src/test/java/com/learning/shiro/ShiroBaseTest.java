package com.learning.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author fanyuwen
 * @date 2019/6/19 9:51
 */
public class ShiroBaseTest {

    @Test
    public void testBaseFunction() {
        //1����ȡSecurityManager Environment�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
        IniWebEnvironment environment = new IniWebEnvironment();
        environment.setIni(Ini.fromResourcePath("classpath:shiro.ini"));
        environment.init();//environment��ʼ��

        //�õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
        SecurityManager securityManager = environment.getSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);

        //3���õ�Subject�������û���/���������֤Token(���û����/ƾ֤)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1234");
        try {
        //4����¼���������֤
        subject.login(token);
        } catch (AuthenticationException e) {
        //5�������֤ʧ��
        }
        Assert.assertTrue(subject.isAuthenticated()); //�����û��Ѿ���¼

        //6���˳�
        subject.logout();
    }

    @After
    public void tearDown() throws Exception {
        //�˳�ʱ�����Subject���߳� ������´β������Ӱ��
        ThreadContext.unbindSubject();
    }

}