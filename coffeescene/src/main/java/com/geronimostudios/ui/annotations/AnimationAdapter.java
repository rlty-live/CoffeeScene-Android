package com.geronimostudios.ui.annotations;

import android.view.View;

/**
 * Created by jerome on 24/03/17.
 */

public interface AnimationAdapter {
    void showView(final View view, boolean animate);
    void hideView(final View view, boolean animate);
}
