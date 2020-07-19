package com.fanyuwen.processor;

import com.fanyuwen.annotation.TestAnnotation;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * 参考网上的示例,实现一个自动生成set方法的类lombok实现
 *
 * @author fanyuwen
 * @date 2020/7/12 1:17
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.fanyuwen.annotation.TestAnnotation")
public class TestAnnotationProcessor extends AbstractProcessor {

    private Messager messager;
    private JavacTrees javacTrees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.javacTrees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(TestAnnotation.class);
        System.out.println("elements: " + elements);
        for (Element element : elements) {
            JCTree jcTree = javacTrees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                    List<JCTree.JCVariableDecl> jcVariableDecls = List.nil();
                    for (JCTree jcTree1 : jcClassDecl.defs) {
                        if (jcTree1.getKind().equals(Tree.Kind.VARIABLE)) {
                            JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) jcTree1;
                            jcVariableDecls = jcVariableDecls.append(jcVariableDecl);
                        }
                    }
                    for (JCTree.JCVariableDecl jcVariableDecl : jcVariableDecls) {
                        messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.getName() + "has been processed.");
                        jcClassDecl.defs = jcClassDecl.defs.prepend(makeSetterMethodDecl(jcVariableDecl));
                    }
                    super.visitClassDef(jcClassDecl);
                }

                @Override
                public void visitVarDef(JCTree.JCVariableDecl jcVariableDecl) {
                    super.visitVarDef(jcVariableDecl);
                }
            });
        }
        return true;
    }

    private JCTree.JCMethodDecl makeSetterMethodDecl(JCTree.JCVariableDecl jcVariableDecl) {
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        // this.a = a;
        JCTree.JCExpressionStatement aThis = makeAssignment(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.getName()), treeMaker.Ident(jcVariableDecl.getName()));
        statements.append(aThis);
        JCTree.JCBlock block = treeMaker.Block(0, statements.toList());
        //
        JCTree.JCVariableDecl param = treeMaker.VarDef(treeMaker.Modifiers(Flags.PARAMETER), jcVariableDecl.getName(), jcVariableDecl.vartype, null);
        List<JCTree.JCVariableDecl> parameters = List.of(param);
        //
        JCTree.JCExpression methodType = treeMaker.Type(new Type.JCVoidType());
        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC), getNewMethodName(jcVariableDecl.getName()), methodType, List.nil(), parameters, List.nil(), block, null);
    }

    private Name getNewMethodName(Name name) {
        String s = name.toString();
        return names.fromString("set" + s.substring(0, 1).toUpperCase() + s.substring(1));
    }

    private JCTree.JCExpressionStatement makeAssignment(JCTree.JCExpression lhs, JCTree.JCExpression rhs) {
        return treeMaker.Exec(treeMaker.Assign(lhs, rhs));
    }
}