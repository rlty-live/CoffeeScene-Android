package com.geronimostudios.coffeescene.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

public class TranslateXAnimationAdapter implements AnimationAdapter<TranslateScenesParams> {

    private Animator.AnimatorListener mNoAnimation = new AnimatorListenerAdapter() {};
    private TimeInterpolator mInterpolator;
    private int mAnimationDuration;


    TranslateXAnimationAdapter() {
        mAnimationDuration = 200;
        mInterpolator = new DecelerateInterpolator();
    }

    public TranslateXAnimationAdapter(@Nullable TimeInterpolator interpolator,
                                      int animationDuration) {
        mInterpolator = interpolator;
        mAnimationDuration = animationDuration;
    }

    @Override
    public TranslateScenesParams
    generateScenesParams(@NonNull SparseArray<List<View>> scenesIdsToViews) {
        return new TranslateScenesParams(scenesIdsToViews);
    }

    @Override
    public void doChangeScene(@NonNull SparseArray<List<View>> scenesIdsToViews,
                              @Nullable TranslateScenesParams scenesParams,
                              int sceneId,
                              boolean animate) {
        if (scenesParams == null) {
            throw new NullPointerException("Scenes params are null");
        }

        int lastScenePosition = 0;
        if (!scenesParams.hasValidLastSceneId()) {
            animate = false;
        } else {
            lastScenePosition = scenesParams.positionOf(scenesParams.getLastSceneId());
        }
        int scenePosition = scenesParams.positionOf(sceneId);
        List<View> currentSceneViews = scenesIdsToViews.get(sceneId);

        for (int i = 0; i < scenesIdsToViews.size(); ++i) {
            // do change scene
            int viewSceneId = scenesIdsToViews.keyAt(i);
            List<View> views = scenesIdsToViews.get(viewSceneId);

            // If the scene is not displayed nor to be animated
            if (viewSceneId != sceneId && viewSceneId != scenesParams.getLastSceneId()) {
                allGone(views, currentSceneViews);
                continue;
            }

            boolean isNewScene = viewSceneId == sceneId;
            if (!animate) {
                showOrHideWithoutAnimations(isNewScene, views);
                continue;
            }

            if (scenePosition < lastScenePosition) {
                // left to right
                doLeftToRight(isNewScene, views);
            } else {
                // right to left
                doRightToLeft(isNewScene, views);
            }
        }
        scenesParams.setLastSceneId(sceneId);
    }

    private void showOrHideWithoutAnimations(boolean isNewScene, @NonNull List<View> views) {
        for (View view : views) {
            if (isNewScene) {
                view.setTranslationX(0);
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    private void doLeftToRight(boolean isNewScene, @NonNull List<View> views) {
        for (final View view : views) {
            view.clearAnimation();
            int parentWidth = ((ViewGroup) view.getParent()).getWidth();
            if (isNewScene) {
                if (view.getVisibility() == View.GONE) {
                    view.setVisibility(View.VISIBLE);
                    view.setTranslationX(-parentWidth);
                }
                view.animate()
                        .translationX(0)
                        .setDuration(mAnimationDuration)
                        .setInterpolator(mInterpolator)
                        .setListener(mNoAnimation);
            } else {
                if (view.getVisibility() == View.GONE) {
                    view.setVisibility(View.VISIBLE);
                    view.setTranslationX(0);
                }
                view.animate()
                        .translationX(parentWidth)
                        .setDuration(mAnimationDuration)
                        .setInterpolator(mInterpolator)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                view.setVisibility(View.GONE);
                            }
                        });
            }
        }
    }

    private void doRightToLeft(boolean isNewScene, @NonNull List<View> views) {
        for (final View view : views) {
            view.clearAnimation();
            int parentWidth = ((ViewGroup) view.getParent()).getWidth();
            if (isNewScene) {
                if (view.getVisibility() == View.GONE) {
                    view.setVisibility(View.VISIBLE);
                    view.setTranslationX(parentWidth);
                }
                view.animate()
                        .translationX(0)
                        .setDuration(mAnimationDuration)
                        .setInterpolator(mInterpolator)
                        .setListener(mNoAnimation);
            } else {
                if (view.getVisibility() == View.GONE) {
                    view.setVisibility(View.VISIBLE);
                    view.setTranslationX(0);
                }
                view.animate()
                        .translationX(-parentWidth)
                        .setDuration(mAnimationDuration)
                        .setInterpolator(mInterpolator)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                view.setVisibility(View.GONE);
                            }
                        });
            }
        }
    }

    private void allGone(@NonNull List<View> views, @Nullable List<View> forceShowIfHidden) {
        for (View view : views) {
            if (forceShowIfHidden == null || !forceShowIfHidden.contains(view)) {
                view.setVisibility(View.GONE);
            }
        }
    }
}
