package com.example.demo.annotation;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes({"com.example.demo.fsm.condition.RuleCondition",
    "com.example.demo.fsm.action.RuleAction"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class RuleAnnotationProcessor extends AbstractProcessor {
    private Filer mFilerUtils;          // 文件管理工具类，可以用于生成java源文件
    private Types mTypesUtils;          // 类型处理工具类
    private Elements mElementsUtils;    // Element处理工具类，获取Element的信息
    private Messager mMessager;         // 用于打印信息

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFilerUtils = processingEnv.getFiler();
        mTypesUtils = processingEnv.getTypeUtils();
        mElementsUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(annotations != null && annotations.size() > 0){
            for (TypeElement typeElement : annotations) {
                mMessager.printMessage(Diagnostic.Kind.NOTE, "typeElement = ", typeElement);
                TypeMirror typeMirror = typeElement.asType();
                TypeKind typeKind = typeMirror.getKind();
            }

            return true;
        }
        return false;
    }

}
