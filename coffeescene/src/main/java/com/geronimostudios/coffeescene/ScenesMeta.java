package com.geronimostudios.coffeescene;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.geronimostudios.coffeescene.annotations.Scene;

import java.util.List;

/**
 * Contains data about a {@link com.geronimostudios.coffeescene.annotations.CoffeeScene}
 */
final class ScenesMeta {
    private @NonNull SceneAnimationAdapter mSceneAnimationAdapter;
    private SparseArray<View> mScenesIdsToViews;
    private Listener mListener;

    ScenesMeta(@NonNull ViewGroup root,
               @NonNull SceneAnimationAdapter sceneAnimationAdapter,
               Scene[] scenes,
               @Nullable Listener listener) {
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mListener = listener;
        mScenesIdsToViews = new SparseArray<>();
        for (int i = 0; i < scenes.length; ++i) {
            mScenesIdsToViews.put(scenes[i].scene(), root.getChildAt(i));
        }
    }

    ScenesMeta(@NonNull SceneAnimationAdapter sceneAnimationAdapter,
               @NonNull List<Pair<Integer, View>> scenesIds,
               @Nullable Listener listener) {
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mListener = listener;
        mScenesIdsToViews = new SparseArray<>();
        for (Pair<Integer, View> pair : scenesIds) {
            mScenesIdsToViews.put(pair.first, pair.second);
        }
    }

    @NonNull
    SceneAnimationAdapter getSceneAnimationAdapter() {
        return mSceneAnimationAdapter;
    }

    @NonNull
    SparseArray<View> getScenesIds() {
        return mScenesIdsToViews;
    }

    @Nullable
    Listener getListener() {
        return mListener;
    }
}
