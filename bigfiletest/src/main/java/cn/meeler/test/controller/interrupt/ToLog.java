package cn.meeler.test.controller.interrupt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ChildLog
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToLog {

}
