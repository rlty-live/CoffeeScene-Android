package com.geronimostudios.coffeescene;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.geronimostudios.coffeescene.animations.AnimationAdapter;
import com.geronimostudios.coffeescene.animations.ScenesParams;
import com.geronimostudios.coffeescene.annotations.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains data about a {@link com.geronimostudios.coffeescene.annotations.CoffeeScene}
 */
final class ScenesMeta {
    private @NonNull AnimationAdapter mSceneAnimationAdapter;
    private @NonNull SparseArray<List<View>> mScenesIdsToViews;
    private Listener mListener;
    private @Nullable ScenesParams mScenesParams;
    private int mCurrentSceneId = Integer.MIN_VALUE;

    ScenesMeta(@NonNull ViewGroup root,
               @NonNull AnimationAdapter sceneAnimationAdapter,
               Scene[] scenes,
               @Nullable Listener listener) {
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mListener = listener;
        mScenesIdsToViews = new SparseArray<>();
        for (int i = 0; i < scenes.length; ++i) {
            int sceneId = scenes[i].scene();
            List<View> list = mScenesIdsToViews.get(sceneId);
            if (list == null) {
                list = new ArrayList<>();
                assertValidScene(sceneId, list);
                mScenesIdsToViews.put(sceneId, list);
            }
            list.add(root.getChildAt(i));
        }
        mScenesParams = mSceneAnimationAdapter.generateScenesParams(mScenesIdsToViews);
    }

    ScenesMeta(@NonNull AnimationAdapter sceneAnimationAdapter,
               @NonNull List<Pair<Integer, View>> scenesIds,
               @Nullable Listener listener) {
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mListener = listener;
        mScenesIdsToViews = new SparseArray<>();
        for (Pair<Integer, View> pair : scenesIds) {
            List<View> list = mScenesIdsToViews.get(pair.first);
            if (list == null) {
                list = new ArrayList<>();
                assertValidScene(pair.first, list);
                mScenesIdsToViews.put(pair.first, list);
            }
            list.add(pair.second);
        }
        mScenesParams = mSceneAnimationAdapter.generateScenesParams(mScenesIdsToViews);
    }

    private void assertValidScene(int sceneId, List<View> views) {
        if (sceneId == Integer.MIN_VALUE) {
            throw new RuntimeException("Invalid scene id, do not use Integer.MIN_VALUE.");
        }
    }

    @NonNull
    AnimationAdapter getSceneAnimationAdapter() {
        return mSceneAnimationAdapter;
    }

    @NonNull
    SparseArray<List<View>> getScenesIdsToViews() {
        return mScenesIdsToViews;
    }

    @Nullable
    public ScenesParams getScenesParams() {
        return mScenesParams;
    }

    @Nullable
    Listener getListener() {
        return mListener;
    }

    public void setCurrentSceneId(int currentSceneId) {
        mCurrentSceneId = currentSceneId;
    }

    public int getCurrentSceneId() {
        return mCurrentSceneId;
    }
}
