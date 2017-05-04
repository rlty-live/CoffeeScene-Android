package com.geronimostudios.coffeescene;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.geronimostudios.coffeescene.annotations.Scene;

/**
 * Contains data about a {@link com.geronimostudios.coffeescene.annotations.CoffeeScene}
 */
final class ScenesMeta {
    private final ViewGroup mRoot;
    private @NonNull
    SceneAnimationAdapter mSceneAnimationAdapter;
    private Scene[] mScenes;
    private int mCurrentScene;

    ScenesMeta(@NonNull ViewGroup root,
               @NonNull SceneAnimationAdapter sceneAnimationAdapter,
               Scene[] scenes,
               int currentScene) {
        mRoot = root;
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mScenes = scenes;
        mCurrentScene = currentScene;
    }

    @NonNull
    SceneAnimationAdapter getSceneAnimationAdapter() {
        return mSceneAnimationAdapter;
    }

    int getCurrentScene() {
        return mCurrentScene;
    }

    void setSceneAnimationAdapter(@NonNull SceneAnimationAdapter sceneAnimationAdapter) {
        mSceneAnimationAdapter = sceneAnimationAdapter;
    }

    void setCurrentScene(int currentScene) {
        mCurrentScene = currentScene;
    }

    ViewGroup getRoot() {
        return mRoot;
    }

    Scene[] getScenes() {
        return mScenes;
    }
}
