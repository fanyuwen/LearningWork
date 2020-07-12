package com.fanyuwen.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 学习ast(abstracti syntax tree)api
 * https://liuyehcf.github.io/2018/02/02/Java-JSR-269-插入式注解处理器/
 *
 * @author fanyuwen
 * @date 2020/7/12 15:11
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class LearningASTAnnotationProcessor extends AbstractProcessor {

    /**
     * 编译时消息输出
     */
    private Messager messager;
    /**
     * 抽象语法树结构(当前已存在)
     */
    private JavacTrees javacTrees;
    /**
     * 抽象语法树新构造
     */
    private TreeMaker treeMaker;
    /**
     * 抽象语法树命名(提供了创建标识符命名的方法)
     */
    private Names names;

    /**
     * 初始化
     *
     * @param processingEnv 执行环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        //获取消息输出组件
        this.messager = processingEnv.getMessager();
        //从当前的执行环境构造编译语法树
        this.javacTrees = JavacTrees.instance(processingEnv);
        //获取java执行环境的上下文
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        //用该上下文构造treeMaker
        this.treeMaker = TreeMaker.instance(context);
        //用该上下文构造命名组件
        names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        return false;
    }
}