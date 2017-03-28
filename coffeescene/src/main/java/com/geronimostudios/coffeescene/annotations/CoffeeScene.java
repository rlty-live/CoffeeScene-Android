package com.geronimostudios.coffeescene.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>{@link CoffeeScene} is used to define a list of {@link Scene} with a default scene
 * that will be displayed first.</p>
 *
 * <p>{@link CoffeeScene} can be used with an {@link android.app.Activity},
 * a {@link android.view.ViewGroup}, a {@link android.app.Fragment}
 * or a {@link android.support.v4.app.Fragment}.</p>
 *
 * <p>See {@link com.geronimostudios.coffeescene.SceneManager}.</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CoffeeScene {

    /**
     * The list of {@link Scene} that will be created.
     */
    Scene[] value();

    /**
     * The default scene id. See {@link Scene#scene()}.
     * The {@link Scene} associated to this id will be the displayed first.
     */
    int defaultScene() default Scene.MAIN_CONTENT;
}