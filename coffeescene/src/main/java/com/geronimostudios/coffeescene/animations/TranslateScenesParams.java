package com.geronimostudios.coffeescene.animations;

import android.support.annotation.NonNull;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TranslateScenesParams extends ScenesParams {
    private @NonNull List<Pair<Integer, List<View>>> mScenesIdsToViews;
    private int mLastSceneId = Integer.MIN_VALUE;

    TranslateScenesParams(final SparseArray<List<View>> scenes) {
        super(scenes);

        mScenesIdsToViews = new ArrayList<>();
        for (int i = 0; i < scenes.size(); i++) {
            int viewSceneId = scenes.keyAt(i);
            List<View> views = scenes.get(viewSceneId);

            mScenesIdsToViews.add(Pair.create(viewSceneId, views));
        }

        Collections.sort(mScenesIdsToViews, new Comparator<Pair<Integer, List<View>>>() {
            @Override
            public int compare(Pair<Integer, List<View>> o1, Pair<Integer, List<View>> o2) {
                return o1.first - o2.first;
            }
        });
    }

    protected int positionOf(int sceneId) {
        for (int i = 0; i < mScenesIdsToViews.size(); ++i) {
            Pair<Integer, List<View>> scenesIdsToView = mScenesIdsToViews.get(i);
            if (scenesIdsToView.first == sceneId) {
                return i;
            }
        }
        throw new RuntimeException("position not found");
    }

    protected int getLastSceneId() {
        return mLastSceneId;
    }

    protected boolean hasValidLastSceneId() {
        return mLastSceneId != Integer.MIN_VALUE;
    }

    protected void setLastSceneId(int lastSceneId) {
        mLastSceneId = lastSceneId;
    }
}
