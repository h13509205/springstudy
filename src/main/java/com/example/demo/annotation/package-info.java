package com.example.demo.annotation;

/**
 * 1.自带的标准注解，如@Override, @Deprecated,@SuppressWarnings,@FunctionalInterface
 *   如：@SuppressWarnings: unchecked：执行了未经检查的转换时候的告警，如集合没有使用泛型来指定集合保存的数据类型
 *                         rawtypes：不用提示使用基本类型（int float）参数时相关的警告信息。一般是在通过传参调用某个方法的时候进行标识。
 * 2.java元注解，用于修饰注解的注解，如：@Retention, @Documented, @Target, @Inherited, @Repeatable
 *   如：@Retention 标识这个注解怎么保存，分为：Source(源代码里)， Class（编译到类文件中，默认是这个），Runtime（在运行时可以拿到），通过enum RetentionPolicy.java获取
 *   如：@Documented 标注这个注解是否需要写入java-doc（生成文档信息的时候保留注解，对类作辅助说明）
 *   如：@Target 标注了注解范围，所有范围在enum ElementType.java中，常用的有Type(作用在Class上), Field, Method, Parameter等
 *   如：@Inherited 标注了该注解的注解，标记在某个类中，如果这个类被继承，该继承类可以获取该标注了@Inherited的注解
 *   如：@Repeatable 标识某注解可以在同一个声明中使用多次
 * 3.自定义注解，使用class @interface开头进行标注，所有的注解类会自动继承java.lang.Annotation这一接口，并且不能再去继承别的类或是接口（可以通过编译后的继承关系去看）。
 *   通过反射拿到的自定义注解，实现类都是动态代理类，其中生成代理类时候用的InvocationHandler为AnnotationDescription下的AnnotationInvocationHandler，
 *   主要功能为为获取自定义属性，所以要生成动态代理类(定义自定义注解的时候，参数定义为field，但是获取参数的时候，通过调用和参数同名的方法去获取)（顺便复习一波动态代理）
 *   自定义属性被定义在AnnotationInvocationHandler下的memberValues下面（是个map），实际调用方法获取参数值时，就相当于从map里拿个值
 *   其中invoke()主要是对其中的equals(),hashcode(),toString()等方法进行代理，除这几个方法之外，其他方法都委托给自定义注解中的方法
 *   此外，自定义注解参数的数据绑定，通过AnnotationParser进行绑定（不关键）
 * 4.注解作用范围仅在源代码中： https://blog.csdn.net/u010126792/article/details/96703015
 *   java在编译的时候，会使用Annotation Processor（是javac的一个工具），
 *   如果需要对自定义注解进行处理，需要自己实现一个注解处理器，如继承AbstractProcessor，
 *   继承完后，需要在resource下的时候创建文件META-INF/services/javax.annotation.processing.Processor，并将处理器写到该文件中，格式为packageName.Processor
 *   此外可以通过google的@AutoService进行标注，说明这个注解需要在编译时被处理
 *   javax.lang.model.Element  表示类中的一个元素，可以表示：package, class, interface, method, field, parameter等等，也有和这些概念一一对应的子类，如ExecutableElement表示method
 *
 */