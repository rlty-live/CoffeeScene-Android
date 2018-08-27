package com.geronimostudios.coffeescene.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.Nullable;
import android.view.View;

public final class SceneAnimations {

    private SceneAnimations() {
        // Not instantiable
    }
    /**
     * Fade in or out and change the visibility from {@link View#VISIBLE} to {@link View#GONE}.
     * This is the default animation adapter.
     */
    public static AnimationAdapter ANIMATION_FADE
            = new SimpleAnimationAdapter() {

        @Override
        public void showView(View view, @Nullable ScenesParams params, boolean animate) {
            if (animate) {
                AnimationHelper.showView(view);
            } else {
                view.setAlpha(1f);
                view.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void hideView(View view, @Nullable ScenesParams params, boolean animate) {
            if (animate) {
                AnimationHelper.hideView(view);
            } else {
                view.setAlpha(0f);
                view.setVisibility(View.GONE);
            }
        }
    };

    /**
     * Fade in or out and call {@link View#setEnabled(boolean)} on the view.
     * The visibility changes from {@link View#VISIBLE} to {@link View#INVISIBLE}.
     */
    public static AnimationAdapter ANIMATION_ALPHA_ENABLE
            = new SimpleAnimationAdapter<ScenesParams>() {

        @Override
        public void showView(final View view, @Nullable ScenesParams params, boolean animate) {
            if (animate) {
                view.animate()
                        .alpha(1f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                view.setVisibility(View.VISIBLE);
                                view.setEnabled(true);
                            }
                        });
            } else {
                view.setAlpha(1f);
                view.setVisibility(View.VISIBLE);
                view.setEnabled(true);
            }
        }

        @Override
        public void hideView(final View view, @Nullable ScenesParams params, boolean animate) {
            if (animate) {
                view.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                view.setVisibility(View.INVISIBLE);
                                view.setEnabled(false);
                            }
                        });
            } else {
                view.setAlpha(0f);
                view.setVisibility(View.INVISIBLE);
                view.setEnabled(false);
            }
        }
    };
}
