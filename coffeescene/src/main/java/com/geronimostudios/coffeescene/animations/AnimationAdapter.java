package com.geronimostudios.coffeescene.animations;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;

import java.util.List;

/**
 * {@link AnimationAdapter} can be used to override the
 * transition animations.
 */
public interface AnimationAdapter<T extends ScenesParams> {

    /**
     * Called once when the scenes are created.
     */
    @Nullable T generateScenesParams(final @NonNull SparseArray<List<View>> scenes);

    /**
     * TODO
     * @param scenesIdsToViews
     * @param scenesParams
     * @param sceneId
     * @param animate
     */
    void doChangeScene(final @NonNull SparseArray<List<View>> scenesIdsToViews,
                       @Nullable T scenesParams,
                       int sceneId,
                       boolean animate);
}
