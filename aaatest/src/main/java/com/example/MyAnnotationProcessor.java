package com.example;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"com.example.MyAnnotation"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MyAnnotationProcessor extends AbstractProcessor {
    private Filer mFilerUtils;          // 文件管理工具类，可以用于生成java源文件
    private Types mTypesUtils;          // 类型处理工具类
    private Elements mElementsUtils;    // Element处理工具类，获取Element的信息
    private Messager mMessager;         // 用于打印信息

    private List<Element> myElementList;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFilerUtils = processingEnv.getFiler();
        mTypesUtils = processingEnv.getTypeUtils();
        mElementsUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        myElementList = new ArrayList<>();
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(annotations != null && annotations.size() > 0){
            for (TypeElement typeElement : annotations) {
                //annotations中是SupportedAnnotationTypes中的注解类
                mMessager.printMessage(Diagnostic.Kind.NOTE, "typeElement = "+typeElement.toString(), typeElement);
            }
            //获取所有使用MyAnnotation的Element，我这个就作用在方法上，所以里面的Element理论上应该都是ExecutableElement
            Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(MyAnnotation.class);
            for(Element e: set){
                myElementList.add(e);
                ElementKind elementKind = e.getKind();
                mMessager.printMessage(Diagnostic.Kind.NOTE, "ElementKind = "+ elementKind.name());
                if(elementKind == ElementKind.METHOD) {
                    createClass((ExecutableElement) e);
                } else if (elementKind == ElementKind.FIELD) {
                    createClass((VariableElement) e);
                }
            }


            return true;
        }
        return false;
    }

    private void createClass(ExecutableElement element){
        //获取上级Element，对于method，应该就是class，也就是TypeElement
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        //获取全包名
        String fullPackageName = mElementsUtils.getPackageOf(typeElement).getQualifiedName().toString();
        //or
        String fullPackageName2 = mElementsUtils.getPackageOf(typeElement).asType().toString();
        //获取文件名
        String className = typeElement.getSimpleName().toString();

        mMessager.printMessage(Diagnostic.Kind.NOTE,
                "enclosing element = " + typeElement.toString()
                        + "; fullPackageName = " + fullPackageName
                        + " fullPackageName2 = " + fullPackageName2
                        + " className = " + className);
    }

    private void createClass(VariableElement element){

    }
}
