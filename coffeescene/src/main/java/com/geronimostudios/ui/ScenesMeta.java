package com.geronimostudios.ui;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.geronimostudios.ui.annotations.AnimationAdapter;
import com.geronimostudios.ui.annotations.Scene;

/**
 * Created by jerome on 27/03/17.
 */
class ScenesMeta {
    private final ViewGroup mRoot;
    private @NonNull AnimationAdapter mAnimationAdapter;
    private Scene[] mScenes;
    private int mCurrentScene;

    public ScenesMeta(@NonNull ViewGroup root,
                      @NonNull AnimationAdapter animationAdapter,
                      Scene[] scenes,
                      int currentScene) {
        mRoot = root;
        mAnimationAdapter = animationAdapter;
        mScenes = scenes;
        mCurrentScene = currentScene;
    }

    @NonNull
    public AnimationAdapter getAnimationAdapter() {
        return mAnimationAdapter;
    }

    public int getCurrentScene() {
        return mCurrentScene;
    }

    public void setAnimationAdapter(@NonNull AnimationAdapter animationAdapter) {
        mAnimationAdapter = animationAdapter;
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
