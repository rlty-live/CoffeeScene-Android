package com.geronimostudios.sample.coffeelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.geronimostudios.ui.SceneManager;
import com.geronimostudios.ui.annotations.CoffeeScene;
import com.geronimostudios.ui.annotations.Scene;

@CoffeeScene({
        @Scene(scene = Scene.MAIN_CONTENT, layout = R.layout.sample_activity_main),
        @Scene(scene = Scene.LOADER, layout = R.layout.loader),
        @Scene(scene = Scene.PLACEHOLDER, layout = R.layout.placeholder),
        @Scene(scene = SampleActivity.SAMPLE_WITH_VIEW, layout = R.layout.sample_with_view)
})
public class SampleActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int SAMPLE_WITH_VIEW = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SceneManager.create(this);

        findViewById(R.id.sample_switch_to_main).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_progress).setOnClickListener(this);
        findViewById(R.id.go_to_main_from_loader).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_placeholder).setOnClickListener(this);
        findViewById(R.id.go_to_main_from_placeholder).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_custom_view).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_switch_to_main:
            case R.id.sample_switch_to_activity:
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
            case R.id.sample_switch_to_custom_view:
                SceneManager.scene(this, SAMPLE_WITH_VIEW);
                break;
            default:
                throw new IllegalArgumentException("Nope");
        }
    }
}
