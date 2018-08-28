package com.geronimostudios.coffeescene.animations;

import android.util.SparseArray;
import android.view.View;

import java.util.List;

public abstract class ScenesParams {
    protected final SparseArray<List<View>> mScenes;

    protected ScenesParams(SparseArray<List<View>> scenes) {
        this.mScenes = scenes;
    }
}
