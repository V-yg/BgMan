package com.yiie.aop.annotation;

import java.lang.annotation.*;

/**
 * Time：2020-1-2 17:09
 * Email： yiie315@163.com
 * Desc：自定义注解
 *
 * @author： 王一钢
 * @version：1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /** 模块 */
    String title() default "";

    /** 功能 */
    String action() default "";
}
