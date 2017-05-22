package com.geronimostudios.coffeescene;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import com.geronimostudios.coffeescene.annotations.Scene;

import java.util.List;

/**
 * Contains data about a {@link com.geronimostudios.coffeescene.annotations.CoffeeScene}
 */
final class ScenesMeta {
    private final ViewGroup mRoot;
    private @NonNull SceneAnimationAdapter mSceneAnimationAdapter;
    private SparseIntArray mScenesIds;
    private int mCurrentScene;

    ScenesMeta(@NonNull ViewGroup root,
               @NonNull SceneAnimationAdapter sceneAnimationAdapter,
               Scene[] scenes,
               int currentScene) {
        mRoot = root;
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mScenesIds = new SparseIntArray();
        for (int i = 0; i < scenes.length; ++i) {
            mScenesIds.put(i, scenes[i].scene());
        }
        mCurrentScene = currentScene;
    }

    ScenesMeta(@NonNull ViewGroup root,
               @NonNull SceneAnimationAdapter sceneAnimationAdapter,
               @NonNull List<Pair<Integer, View>> scenesIds,
               int currentScene) {
        mRoot = root;
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mScenesIds = new SparseIntArray();
        for (Pair<Integer, View> pair : scenesIds) {
            int position = mRoot.indexOfChild(pair.second);
            if (position == -1) {
                throw new IllegalArgumentException("The sceneId " + pair.first +
                        " is not a child of the root view " + mRoot);
            }
            mScenesIds.put(position, pair.first);
        }
        mCurrentScene = currentScene;
    }

    @NonNull
    SceneAnimationAdapter getSceneAnimationAdapter() {
        return mSceneAnimationAdapter;
    }

    ViewGroup getRoot() {
        return mRoot;
    }

    @NonNull
    SparseIntArray getScenesIds() {
        return mScenesIds;
    }
}
