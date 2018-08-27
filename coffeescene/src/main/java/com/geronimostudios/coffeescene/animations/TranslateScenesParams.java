package com.geronimostudios.coffeescene.animations;

import android.support.annotation.NonNull;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TranslateScenesParams extends ScenesParams {
    private @NonNull List<Pair<Integer, List<View>>> mScenesIdsToViews;

    public TranslateScenesParams(final SparseArray<List<View>> scenes) {
        super(scenes);

        mScenesIdsToViews = new ArrayList<>();
        for (int i = 0; i < scenes.size(); i++) {
            // do change scene
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

    public int positionOf(@NonNull View view) {
        for (int i = 0; i < mScenesIdsToViews.size(); ++i) {
            Pair<Integer, List<View>> scenesIdsToView = mScenesIdsToViews.get(i);
            for (View item : scenesIdsToView.second) {
                if (item.equals(view)) {
                    return i;
                }
            }
        }
        throw new RuntimeException("position not found");
    }
}
