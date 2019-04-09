package myself.annotationprocessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 元注解共有四种@Retention, @Target, @Inherited, @Documented

@Retention 保留的范围，默认值为CLASS. 可选值有三种

SOURCE, 只在源码中可用

CLASS, 在源码和字节码中可用

RUNTIME, 在源码,字节码,运行时均可用

@Target 可以用来修饰哪些程序元素，如 TYPE, METHOD, CONSTRUCTOR, FIELD, PARAMETER等，未标注则表示可修饰所有
@Inherited 是否可以被继承，默认为false
@Documented 是否会保存到 Javadoc 文档中

其中, @Retention是定义保留策略, 直接决定了我们用何种方式解析. SOUCE级别的注解是用来标记的, 比如Override, SuppressWarnings. 我们真正使用的类型是CLASS(编译时)和RUNTIME(运行时)
*/
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface testannotation {
    String name() default "";
}
