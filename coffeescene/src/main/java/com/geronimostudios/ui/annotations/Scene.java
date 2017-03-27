package com.geronimostudios.ui.annotations;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Create a scene by providing an id {@link Scene#scene()}
 * and a layout {@link Scene#layout()}.</p>
 *
 * <p>This annotation can be used with {@link CoffeeScene}.</p>
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scene {

    /**
     * Those constants can be reused by your application for {@link Scene#scene()}.
     * You can of course also use your own id.
     */
    public static final int MAIN_CONTENT = 0x420;
    public static final int LOADER = 0x421;
    public static final int PLACEHOLDER = 0x423;

    /**
     * The unique identifier associated to this scene.
     */
    int scene();

    /**
     * The {@link LayoutRes} that will be inflated for this scene
     */
    @LayoutRes int layout();
}
