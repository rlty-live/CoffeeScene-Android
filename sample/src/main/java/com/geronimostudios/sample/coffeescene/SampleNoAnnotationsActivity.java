package com.geronimostudios.sample.coffeescene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.geronimostudios.coffeescene.CoffeeSceneListenerAdapter;
import com.geronimostudios.coffeescene.SceneCreator;
import com.geronimostudios.coffeescene.SceneManager;
import com.geronimostudios.coffeescene.animations.SceneAnimations;
import com.geronimostudios.coffeescene.annotations.Scene;

public class SampleNoAnnotationsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_annotations_sample);
        SceneManager.create(
                SceneCreator.with(this)
                        .add(Scene.MAIN, R.id.activity_no_annotations_sample_main_content)
                        .add(Scene.MAIN, R.id.activity_no_annotations_sample_main_content_another_view)
                        .add(Scene.SPINNER, R.id.activity_no_annotations_sample_main_content_another_view)
                        .add(Scene.SPINNER, R.id.activity_no_annotations_sample_loader)
                        .add(Scene.PLACEHOLDER, R.id.activity_no_annotations_sample_placeholder)
                        .first(Scene.MAIN)
                        .animation(SceneAnimations.ANIMATION_TRANSLATE_X)
                        .listener(new CoffeeSceneListenerAdapter() {
                            @Override
                            public void onSceneChanged(int sceneId) {
                                Log.d("SceneListener", "New scene " + sceneId);
                            }
                        })
        );

        findViewById(R.id.activity_no_annotations_sample_main_content).setOnClickListener(this);
        findViewById(R.id.activity_no_annotations_sample_loader).setOnClickListener(this);
        findViewById(R.id.activity_no_annotations_sample_placeholder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_no_annotations_sample_main_content:
                SceneManager.scene(this, Scene.SPINNER);
                break;
            case R.id.activity_no_annotations_sample_loader:
                SceneManager.scene(this, Scene.PLACEHOLDER);
                break;
            case R.id.activity_no_annotations_sample_placeholder:
                SceneManager.scene(this, Scene.MAIN);
                break;
            default:
                throw new IllegalArgumentException("Invalid view id");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SceneManager.release(this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SampleNoAnnotationsActivity.class));
    }
}
