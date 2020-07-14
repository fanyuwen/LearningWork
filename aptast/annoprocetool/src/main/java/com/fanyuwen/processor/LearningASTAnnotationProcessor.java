package com.fanyuwen.processor;

import com.fanyuwen.annotation.MyLocalVarAnnotation;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * 学习ast(abstracti syntax tree)api
 * https://liuyehcf.github.io/2018/02/02/Java-JSR-269-插入式注解处理器/
 * https://blog.csdn.net/a_zhenzhen/article/details/86065063
 *
 * @author fanyuwen
 * @date 2020/7/12 15:11
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.fanyuwen.annotation.MyLocalVarAnnotation")
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
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyLocalVarAnnotation.class);
        System.out.println("has elements? " + elements);
        for (Element element : elements) {
            JCTree jcTree = javacTrees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
                    List<JCTree.JCVariableDecl> jcVariableDecls = jcMethodDecl.getParameters();
                    jcMethodDecl.params = jcVariableDecls.append(treeMaker.VarDef(treeMaker.Modifiers(Flags.PARAMETER, List.nil()), names.fromString("lizihan1"),
                            treeMaker.Type(new Type.JCPrimitiveType(TypeTag.INT, null)), null));
//                    jcMethodDecl.getParameters();
//                    treeMaker.Apply(treeMaker.Types(List.of(treeMaker.TypeIdent(TypeTag.valueOf("")).type)), treeMaker.Select(treeMaker.Select(treeMaker.Ident(names.fromString("System")),
//                            names.fromString("out")), names.fromString("println")), List.of(treeMaker.Ident(names.fromString("lizihan1"))));
                    jcMethodDecl.accept(new TreeTranslator() {
                        @Override
                        public void visitBlock(JCTree.JCBlock jcBlock) {
                            List<JCTree.JCStatement> jcStatements = jcBlock.getStatements();
                            System.out.println("jcStatements : " + jcStatements);
                            VariableTree variableTree = (VariableTree)jcStatements.get(0);
                            System.out.println(variableTree.getModifiers().getAnnotations().get(0).getAnnotationType());
                            System.out.println(variableTree.getModifiers().getAnnotations().get(0).getClass());
                            try {
                                jcBlock.stats = jcStatements.append(treeMaker.Exec(
//                                    treeMaker.Apply(treeMaker.Types(List.of(new Type.JCVoidType())),
//                                    treeMaker.Select(treeMaker.Ident(names.fromString("System.out")), names.fromString("println")),
//                                    List.of(treeMaker.Ident(names.fromString("lizihan1")))
                                        treeMaker.Apply(
                                                List.of(memberAccess("java.lang.String")),
                                                memberAccess("java.lang.System.out.println"),
                                                List.of(treeMaker.Ident(names.fromString("lizihan1")))
                                        )));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            super.visitBlock(jcBlock);
                        }
                    });
                    super.visitMethodDef(jcMethodDecl);
                }
            });
        }
        return true;
    }

    private Name getNameFromString(String s) {
        return names.fromString(s);
    }

    private JCTree.JCExpression memberAccess(String components) {
        String[] componentArray = components.split("\\.");
        JCTree.JCExpression expr = treeMaker.Ident(getNameFromString(componentArray[0]));
        for (int i = 1; i < componentArray.length; i++) {
            expr = treeMaker.Select(expr, getNameFromString(componentArray[i]));
        }
        return expr;
    }
}