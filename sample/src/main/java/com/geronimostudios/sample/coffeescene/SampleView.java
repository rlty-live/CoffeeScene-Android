package com.geronimostudios.sample.coffeescene;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.geronimostudios.ui.SceneManager;
import com.geronimostudios.ui.annotations.CoffeeScene;
import com.geronimostudios.ui.annotations.Scene;

@CoffeeScene(
        value = {
                @Scene(scene = Scene.MAIN_CONTENT, layout = R.layout.sample_view_main),
                @Scene(scene = Scene.LOADER, layout = R.layout.loader),
                @Scene(scene = Scene.PLACEHOLDER, layout = R.layout.placeholder),
        },
        defaultScene = Scene.MAIN_CONTENT
)
public class SampleView extends FrameLayout implements View.OnClickListener {
    public SampleView(Context context) {
        super(context);
        init(context);
    }

    public SampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SampleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
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
                SceneManager.scene(this, Scene.MAIN_CONTENT);
                break;
            case R.id.sample_switch_to_progress:
                SceneManager.scene(this, Scene.LOADER);
                break;
            case R.id.sample_switch_to_placeholder:
                SceneManager.scene(this, Scene.PLACEHOLDER);
                break;
            default:
                throw new IllegalArgumentException("Nope");
        }
    }
}
