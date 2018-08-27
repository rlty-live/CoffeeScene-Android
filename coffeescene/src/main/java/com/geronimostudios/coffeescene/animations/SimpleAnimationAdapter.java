package com.geronimostudios.coffeescene.animations;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

import com.geronimostudios.coffeescene.annotations.Scene;

import java.util.List;

/**
 * {@link SimpleAnimationAdapter} can be used to override the
 * transition animations.
 */
public abstract class SimpleAnimationAdapter<T extends ScenesParams>
        implements AnimationAdapter<T> {

    /**
     * Called once when the scenes are created.
     */
    @Override
    public @Nullable T generateScenesParams(final @NonNull SparseArray<List<View>> scenes) {
        return null;
    }

    /**
     * Called when a scene has to be displayed.
     *
     * @param view The view holding a scene. See {@link Scene#layout()}
     * @param animate true if you can animate the transition.
     *                false if an animation is not recommended. Ex: When the default scene is
     *                displayed.
     */
    abstract void showView(final View view, @Nullable T params, boolean animate);

    /**
     * Called when a scene has to be hidden.
     *
     * @param view The view holding a scene. See {@link Scene#layout()}
     * @param animate true if you can animate the transition.
     *                false if an animation is not recommended. Ex: When the default scene is
     *                displayed.
     */
    abstract void hideView(final View view, @Nullable T params, boolean animate);

    @Override
    public void doChangeScene(final @NonNull SparseArray<List<View>> scenesIdsToViews,
                              @Nullable T scenesParams,
                              int sceneId,
                              boolean animate) {

        for (int i = 0; i < scenesIdsToViews.size(); ++i) {
            // do change scene
            int viewSceneId = scenesIdsToViews.keyAt(i);
            List<View> views = scenesIdsToViews.get(viewSceneId);
            boolean show = viewSceneId == sceneId;
            List<View> currentSceneViews = scenesIdsToViews.get(sceneId);
            showOrHideView(show, views, currentSceneViews, scenesParams, animate);
        }
    }

    private void showOrHideView(boolean show,
                                @NonNull List<View> views,
                                @Nullable List<View> forceShow,
                                T scenesParams,
                                boolean animate) {
        for (View view : views) {
            if (!show && forceShow != null && forceShow.contains(view)) {
                continue; // Skip an forceShow view
            }
            showOrHideView(show, view, scenesParams, animate);
        }
    }

    private void showOrHideView(boolean show,
                                @NonNull View views,
                                @Nullable T scenesParams,
                                boolean animate) {
        if (show) {
            showView(views, scenesParams, animate);
        } else {
            hideView(views, scenesParams, animate);
        }
    }
}
