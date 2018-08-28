package com.geronimostudios.coffeescene.animations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.geronimostudios.coffeescene.Listener;
import com.geronimostudios.coffeescene.SceneManager;

import java.util.List;

/**
 * {@link AnimationAdapter} can be used to override the
 * transition animations.
 */
public interface AnimationAdapter<T extends ScenesParams> {

    /**
     * <p>Called once at the beginning when the mScenes are created.
     * The adapter is free to use {@link ScenesParams}, it will be provided
     * to {@link #doChangeScene(SparseArray, ScenesParams, int, boolean)} at each call.</p>
     *
     * <p>There is one {@link ScenesParams} per
     * {@link SceneManager#doCreate(Context, Object, AnimationAdapter, ViewGroup, Listener)}</p>
     */
    @Nullable T generateScenesParams(final @NonNull SparseArray<List<View>> scenes);

    /**
     * <p>This is called each time the current scene changes. This is where the animations are
     * done.</p>
     *
     * @param scenesIdsToViews An {@link SparseArray} that links each scene id to
     *                         its associated views.
     * @param scenesParams The {@link ScenesParams} returned
     *                     by {@link #generateScenesParams(SparseArray)} for these mScenes.
     * @param sceneId The scene id to be displayed.
     * @param animate true if you can animate the transition.
     *                false if an animation is not recommended. Ex: When the default scene is
     *                displayed.
     */
    void doChangeScene(final @NonNull SparseArray<List<View>> scenesIdsToViews,
                       @Nullable T scenesParams,
                       int sceneId,
                       boolean animate);
}
