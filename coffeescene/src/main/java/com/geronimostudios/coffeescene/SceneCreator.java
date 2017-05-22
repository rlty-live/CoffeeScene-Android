package com.geronimostudios.coffeescene;

import android.app.Activity;
import android.support.annotation.IdRes;
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
    private final Object mReference;
    private final ViewGroup mRootView;
    private @Nullable SceneAnimationAdapter mAdapter;
    private int mDefaultSceneId;
    private List<Pair<Integer, View>> mScenes;

    private SceneCreator(Object reference, ViewGroup rootView) {
        mReference = reference;
        mRootView = rootView;
        mScenes = new ArrayList<>();
        mDefaultSceneId = -1;
    }

    public static SceneCreator with(Activity activity) {
        return new SceneCreator(
                activity,
                (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content))
                        .getChildAt(0)
        );
    }

    public SceneCreator animation(SceneAnimationAdapter adapter) {
        mAdapter = adapter;
        return this;
    }

    public SceneCreator main(int defaultSceneId) {
        mDefaultSceneId = defaultSceneId;
        return this;
    }

    public SceneCreator add(int sceneId, View view) {
        if (view == null) {
            throw new NullPointerException("Invalid view scene. (view == null");
        }
        mScenes.add(new Pair<>(sceneId, view));
        return this;
    }

    public SceneCreator add(int sceneId, @IdRes int idRes) {
        return add(sceneId, mRootView.findViewById(idRes));
    }

    Object getReference() {
        return mReference;
    }

    @Nullable
    SceneAnimationAdapter getAdapter() {
        return mAdapter;
    }

    int getDefaultSceneId() {
        return mDefaultSceneId;
    }

    List<Pair<Integer, View>> getScenes() {
        return mScenes;
    }

    ViewGroup getRootView() {
        return mRootView;
    }
}
