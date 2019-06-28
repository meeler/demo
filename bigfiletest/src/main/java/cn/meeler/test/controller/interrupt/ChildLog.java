package cn.meeler.test.controller.interrupt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
public @interface ChildLog {
}
