package com.geronimostudios.ui.coffeescene;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * This helper provides a few static methods for the animations.
 */
final class AnimationHelper {

    private AnimationHelper() {
        // ignored - not instantiable
    }

    /**
     * Do a smooth fade animation to show a view.
     */
    static void showView(final View view) {
        view.animate()
                .alpha(1f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
    }

    /**
     * Do a smooth fade animation to hide a view.
     */
    static void hideView(final View view) {
        view.animate()
                .alpha(0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
    }
}
