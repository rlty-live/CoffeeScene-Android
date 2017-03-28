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

    public ScenesMeta(@NonNull ViewGroup root,
                      @NonNull SceneAnimationAdapter sceneAnimationAdapter,
                      Scene[] scenes,
                      int currentScene) {
        mRoot = root;
        mSceneAnimationAdapter = sceneAnimationAdapter;
        mScenes = scenes;
        mCurrentScene = currentScene;
    }

    @NonNull
    public SceneAnimationAdapter getSceneAnimationAdapter() {
        return mSceneAnimationAdapter;
    }

    public int getCurrentScene() {
        return mCurrentScene;
    }

    public void setSceneAnimationAdapter(@NonNull SceneAnimationAdapter sceneAnimationAdapter) {
        mSceneAnimationAdapter = sceneAnimationAdapter;
    }

    public void setCurrentScene(int currentScene) {
        mCurrentScene = currentScene;
    }

    public ViewGroup getRoot() {
        return mRoot;
    }

    public Scene[] getScenes() {
        return mScenes;
    }
}
