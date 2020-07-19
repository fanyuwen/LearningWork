package com.fanyuwen.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
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
import java.util.HashSet;
import java.util.Set;

/**
 * @author fanyuwen
 * @date 2020/7/17 23:39
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AllCompilerProcessor extends AbstractProcessor {

    private TreeMaker treeMaker;
    private JavacTrees javacTrees;
    private Messager messager;
    private Names names;
    private int step;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        javacTrees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getRootElements();
        messager.printMessage(Diagnostic.Kind.NOTE, "start to first compiler.");
        //构造函数
        TreeTranslator constructMethodDef = new TreeTranslator() {
            @Override
            public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
                JCTree.JCBlock jcBlock = jcMethodDecl.getBody();
                List<JCTree.JCStatement> jcStatements = jcBlock.getStatements();
                List<JCTree.JCStatement> tails = jcStatements.tail;
                if (tails != null) {
                    for (JCTree.JCStatement jcStatement : tails) {
                        if (jcStatement instanceof JCTree.JCExpressionStatement) {
                            JCTree.JCExpression jcExpression =
                                    ((JCTree.JCExpressionStatement) jcStatement).getExpression();
                            if (jcExpression instanceof JCTree.JCMethodInvocation) {
                                JCTree.JCMethodInvocation jcMethodInvocation = (JCTree.JCMethodInvocation) jcExpression;
                                JCTree.JCExpression jcExpression1 = jcMethodInvocation.getMethodSelect();
                                if (jcExpression1 instanceof JCTree.JCNewClass) {
                                    JCTree.JCNewClass jcNewClass = (JCTree.JCNewClass) jcExpression1;
                                    JCTree.JCExpression classIdentifier = jcNewClass.getIdentifier();
                                } else if (jcExpression1 instanceof JCTree.JCFieldAccess) {
                                    //TODO sdfsdf
                                }
                                JCTree.JCVariableDecl jcVariableDecl = treeMaker.VarDef(
                                        treeMaker.Modifiers(Flags.FINAL + Flags.BLOCK),
                                        names.fromString("traceId"),
                                        memberAccess("java.lang.String"),
                                        treeMaker.Apply(List.nil(),
                                                treeMaker.Select(treeMaker.Apply(
                                                        List.nil(),
                                                        memberAccess("java.util.UUID.randomUUID"),
                                                        List.nil()
                                                ), names.fromString("toString")),
                                                List.nil()
                                        )
                                );
                                jcBlock.stats = List.of(jcStatements.head).appendList(tails.prepend(jcVariableDecl));
                            }
                        }
                    }
                }
                super.visitMethodDef(jcMethodDecl);
            }
        };


        for (Element element : elements) {
            JCTree jcTree = javacTrees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                private Set<String> setNames = new HashSet<>();

                @Override
                public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
                    String name;
                    System.out.println("name: " + (name = jcMethodDecl.getName().toString()));
                    super.visitMethodDef(jcMethodDecl);
                    if (!name.contains("init") && !"main".equals(name)) {
                        setNames.add(jcMethodDecl.getName().toString());
                        List<JCTree.JCVariableDecl> jcVariableDecls_ = jcMethodDecl.getParameters();
                        jcMethodDecl.params = jcVariableDecls_.append(treeMaker.VarDef(treeMaker.Modifiers(Flags.PARAMETER),
                                names.fromString("traceId"),
                                memberAccess("java.lang.String"),
                                treeMaker.Literal("123456")
                        ));
                        JCTree.JCBlock jcBlock = jcMethodDecl.getBody();
                        List<JCTree.JCStatement> jcStatements = jcBlock.getStatements();
                        JCTree.JCExpression expression = treeMaker.Binary(JCTree.Tag.PLUS, treeMaker.Literal("invoke " + name + " - tradeId: "), treeMaker.Ident(names.fromString("traceId")));
                        System.out.println("expression: " + expression);
                        jcBlock.stats = jcStatements.prepend(treeMaker.Exec(treeMaker.Apply(
                                List.of(memberAccess("java.lang.String")),
                                memberAccess("java.lang.System.out.println"),
                                List.of(expression)
                        )));
                    }
                    List<JCTree.JCVariableDecl> jcVariableDecls = jcMethodDecl.getParameters();
                    JCTree.JCBlock jcBlock = jcMethodDecl.getBody();
                    List<JCTree.JCStatement> jcStatements = jcBlock.getStatements();
                    JCTree.JCVariableDecl jcVariableDecl;
                    //需要生成局部tradeId变量
                    boolean noTraceIdParameter = jcVariableDecls == null ||
                            (jcVariableDecl = jcVariableDecls.last()) == null
                            || !"traceId".equals(jcVariableDecl.getName().toString());
                    boolean hasInvocation = false;
                    if (jcStatements != null) {
                        for (JCTree.JCStatement jcStatement : jcStatements) {
                            if (jcStatement instanceof JCTree.JCExpressionStatement) {
                                JCTree.JCExpressionStatement jcExpressionStatement =
                                        (JCTree.JCExpressionStatement) jcStatement;
                                JCTree.JCExpression jcExpression = jcExpressionStatement.getExpression();
                                if (jcExpression instanceof JCTree.JCMethodInvocation) {
                                    JCTree.JCMethodInvocation invocation =
                                            (JCTree.JCMethodInvocation) jcExpression;
                                    JCTree.JCExpression jcExpression1 = invocation.getMethodSelect();
                                    if (jcExpression1 instanceof JCTree.JCFieldAccess) {
                                        JCTree.JCFieldAccess jcFieldAccess =
                                                (JCTree.JCFieldAccess) jcExpression1;
                                    }
                                    if (!invocation.getMethodSelect().toString().contains("super"))
                                        hasInvocation = true;
                                }
                            }
                        }
                    }
                    if (hasInvocation) {
                        jcVariableDecl = treeMaker.VarDef(
                                treeMaker.Modifiers(Flags.FINAL + Flags.BLOCK),
                                names.fromString("traceId"),
                                memberAccess("java.lang.String"),
                                treeMaker.Apply(List.nil(),
                                        treeMaker.Select(treeMaker.Apply(
                                                List.nil(),
                                                memberAccess("java.util.UUID.randomUUID"),
                                                List.nil()
                                        ), names.fromString("toString")),
                                        List.nil()
                                )
                        );
                        if ("<init>".equals(name))
                            jcBlock.stats = List.of(jcStatements.head).append(jcVariableDecl)
                                    .appendList(jcStatements.tail);
                        else if (noTraceIdParameter)
                            jcBlock.stats = jcStatements.prepend(jcVariableDecl);
                        List<JCTree.JCStatement> jcStatements1 = jcBlock.getStatements();
                        if (jcStatements1 != null) {
                            for (JCTree.JCStatement jcStatement : jcStatements1) {
                                JCTree.JCExpressionStatement jcExpressionStatement;
                                JCTree.JCMethodInvocation methodInvocation;
                                if (jcStatement instanceof JCTree.JCExpressionStatement &&
                                        (jcExpressionStatement = (JCTree.JCExpressionStatement) jcStatement) != null &&
                                        jcExpressionStatement.getExpression() instanceof JCTree.JCMethodInvocation &&
                                        (methodInvocation = (JCTree.JCMethodInvocation) jcExpressionStatement.getExpression()) != null
                                ) {
                                    List<JCTree.JCExpression> argument = methodInvocation.getArguments();
                                    String select;
                                    if (!(select = methodInvocation.getMethodSelect().toString()).contains("super") &&
                                            !select.contains("java")
                                            && argument != null) {
                                        methodInvocation.args = argument.append(treeMaker.Ident(names.fromString("traceId")));
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("end jcMethodDecl: " + jcMethodDecl);
                }
            });
        }
        return true;
    }

    private JCTree.JCExpression memberAccess(String components) {
        String[] componentArray = components.split("\\.");
        JCTree.JCExpression expr = treeMaker.Ident(getNameFromString(componentArray[0]));
        for (int i = 1; i < componentArray.length; i++) {
            expr = treeMaker.Select(expr, getNameFromString(componentArray[i]));
        }
        return expr;
    }

    private Name getNameFromString(String s) {
        return names.fromString(s);
    }
}