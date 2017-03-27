package com.geronimostudios.ui.annotations;

import android.support.annotation.LayoutRes;

public @interface Scene {

    public static final int MAIN_CONTENT = 0;
    public static final int LOADER = 1;
    public static final int PLACEHOLDER = 2;

    int scene();
    @LayoutRes int layout();
}
