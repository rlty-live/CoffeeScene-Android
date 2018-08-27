package com.geronimostudios.coffeescene.animations;

import android.util.SparseArray;
import android.view.View;

import java.util.List;

public class ScenesParams {
    private final SparseArray<List<View>> scenes;

    public ScenesParams(SparseArray<List<View>> scenes) {
        this.scenes = scenes;
    }

    protected SparseArray<List<View>> scenes() {
        return scenes;
    }
}
