package com.geronimostudios.coffeescene;

import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerome on 28/03/17.
 */

public class SceneCreator {
    private ViewGroup mRoot;
    private @Nullable SceneAnimationAdapter mAdapter;
    private List<Integer> mScenesIds;
    private int mCurrentScene;
    private List<Pair<Integer, View>> mScenes;

    SceneCreator() {
        mScenes = new ArrayList<>();
    }

    public SceneCreator adapter(SceneAnimationAdapter adapter) {
        mAdapter = adapter;
        return this;
    }

    public SceneCreator adapter(int defaultScene) {
        mCurrentScene = defaultScene;
        return this;
    }

    public SceneCreator scene(int sceneId, View view) {
        mScenes.add(new Pair<>(sceneId, view));
        return this;
    }
}
