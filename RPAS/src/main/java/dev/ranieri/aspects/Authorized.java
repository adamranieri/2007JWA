package dev.ranieri.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // this annotation can only be put on top of methods
public @interface Authorized {

}
