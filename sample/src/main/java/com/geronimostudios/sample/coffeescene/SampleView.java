package com.geronimostudios.sample.coffeescene;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.geronimostudios.coffeescene.SceneManager;
import com.geronimostudios.coffeescene.annotations.CoffeeScene;
import com.geronimostudios.coffeescene.annotations.Scene;

@CoffeeScene(
        value = {
                @Scene(scene = Scene.MAIN, layout = R.layout.sample_view_main),
                @Scene(scene = Scene.SPINNER, layout = R.layout.spinner),
                @Scene(scene = Scene.PLACEHOLDER, layout = R.layout.placeholder),
        },
        first = Scene.MAIN
)
public class SampleView extends FrameLayout implements View.OnClickListener {
    public SampleView(Context context) {
        super(context);
        init();
    }

    public SampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SampleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SampleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        SceneManager.create(this);

        findViewById(R.id.sample_switch_to_main).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_progress).setOnClickListener(this);
        findViewById(R.id.go_to_main_from_loader).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_placeholder).setOnClickListener(this);
        findViewById(R.id.go_to_main_from_placeholder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_switch_to_main:
            case R.id.go_to_main_from_loader:
            case R.id.go_to_main_from_placeholder:
                SceneManager.scene(this, Scene.MAIN);
                break;
            case R.id.sample_switch_to_progress:
                SceneManager.scene(this, Scene.SPINNER);
                break;
            case R.id.sample_switch_to_placeholder:
                SceneManager.scene(this, Scene.PLACEHOLDER);
                break;
            default:
                throw new IllegalArgumentException("Nope");
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
