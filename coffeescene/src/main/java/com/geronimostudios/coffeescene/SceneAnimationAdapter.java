package com.geronimostudios.coffeescene;

import android.view.View;

import com.geronimostudios.coffeescene.annotations.Scene;

/**
 * {@link SceneAnimationAdapter} can be used to override the
 * transition animations.
 */
public interface SceneAnimationAdapter {

    /**
     * Called when a scene has to be displayed.
     *
     * @param view The view holding a scene. See {@link Scene#layout()}
     * @param animate true if you can animate the transition.
     *                false if an animation is not recommended. Ex: When the default scene is
     *                displayed.
     */
    void showView(final View view, boolean animate);

    /**
     * Called when a scene has to be hidden.
     *
     * @param view The view holding a scene. See {@link Scene#layout()}
     * @param animate true if you can animate the transition.
     *                false if an animation is not recommended. Ex: When the default scene is
     *                displayed.
     */
    void hideView(final View view, boolean animate);
}
