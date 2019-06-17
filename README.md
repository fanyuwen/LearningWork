# LearningWork(工作学习并总结,互帮互助,持续沉淀)
This project is mainly a summary of the problems encountered in the work and the learning framework of the experience
##### 团队成员能够把在工作中遇到的问题总结,把学习到的知识总结,共同沉淀成这个项目的结晶

##### The project build by *gradle* v*5.4.1*
###### base on jdk 1.8
##### 统一字符编码为 *UTF-8*
###### _注：gradle编译若出现：Cause: org.jetbrains.plugins.gradle.tooling.util.ModuleComponentIdentifierImpl.getModuleIdentifier()。_
###### _是由于gradle与idea版本冲突导致的，建议下载低版本的gradle或idea。令：低版本的gradle与高版本java不兼容，eg:grade4.6与java11不兼容。_

##### The following open source frameworks are involved (创建完模块之后记得更新一下列表,序号要写对啊!):

1. **spring** v:*5.1.7*.RELEASE
2. **springMVC** v:*5.1.7*.RELEASE
3. **spring boot1** v:*1.5.21*.RELEASE
4. **spring boot2(java8/spring5)** v:*2.1.5*.RELEASE
5. **spring boot1 activemq** v:*1.5.21*.RELEASE
6. **spring boot2 activemq** v:*2.1.5*.RELEASE
7. **mybatis** v:*3.5.1*
8. **shiro** v:*1.4.1*
9. **netty**
10. **MQ** (kafka)(activeMQ)
11. **redis** v:*(jedis)2.10.2*
12. **follow-up(只要是你感兴趣上面又没有列出来的,可以自己创建或者提出来)...**

by *fanyuwen*

### 成员必读:
1. 开发已dev分支为基础自己建立本地新分支,开发完了提交再合并到dev分支push
2. 所提交的代码都必须要有注释,哪怕只有一行(标识是谁提交的)
3. 提交的comment必须表述详细
4. 提交之前要经过严格测试,保证代码质量
5. 尊重他人的结果,要在获得别人的许可下才能修改别人的代码
6. 项目整体已经按照父子模块的方式进行创建了,每一个学习的主题都是一个模块(尽量保证大主题一致),已创建的模块见****文件,模块的约定包名以***com.learning***开始
7. 要理清楚你构建的模块的依赖关系(util模块特殊,只能成为总依赖源,不能依赖任何其他第三方)
8. 要非常小心不同版本的依赖的关系,很多模块只是版本不一样,依赖这些模块的时候一定要保证不能都依赖了,不然会导致依赖冲突
9. 以后代码测试尽量通过junit单元测试的方式实现
10. 待定...

### 学习清单:
1. [markdown](http://www.markdown.cn/)语法(学完之后也可以直接编辑本文件,把自己想写的,想说的写上去,或者修改整体的格式,只要不删减内容都行...)  --fanyuwen
2. [w3cGradle学习教程](https://www.w3cschool.cn/gradle/) [gradle官网](https://gradle.org/) [下载指定版本](https://gradle.org/releases/) 下拉找到指定的版本下载(建议下载binary-only)  --fanyuwen
3. (你想学的技术都能在这里写)未完待续...