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
        //1、获取SecurityManager Environment工厂，此处使用Ini配置文件初始化SecurityManager
        IniWebEnvironment environment = new IniWebEnvironment();
        environment.setIni(Ini.fromResourcePath("classpath:shiro.ini"));
        environment.init();//environment初始化

        //得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = environment.getSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1234");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }
        Assert.assertTrue(subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    @After
    public void tearDown() throws Exception {
        //退出时解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

}