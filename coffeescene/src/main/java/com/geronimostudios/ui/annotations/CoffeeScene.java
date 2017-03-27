package com.geronimostudios.ui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CoffeeScene {

    Scene[] value();
    int defaultScene() default Scene.MAIN_CONTENT;
}