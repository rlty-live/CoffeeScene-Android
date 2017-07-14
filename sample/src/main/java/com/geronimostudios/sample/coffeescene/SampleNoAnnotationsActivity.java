package com.geronimostudios.sample.coffeescene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.geronimostudios.coffeescene.CoffeeSceneListenerAdapter;
import com.geronimostudios.coffeescene.SceneCreator;
import com.geronimostudios.coffeescene.SceneManager;
import com.geronimostudios.coffeescene.annotations.Scene;

public class SampleNoAnnotationsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_annotations_sample);
        SceneManager.create(
                SceneCreator.with(this)
                        .add(Scene.MAIN_CONTENT, R.id.activity_no_annotations_sample_main_content)
                        .add(Scene.MAIN_CONTENT, R.id.activity_no_annotations_sample_main_content_another_view)
                        .add(Scene.LOADER, R.id.activity_no_annotations_sample_loader)
                        .add(Scene.PLACEHOLDER, R.id.activity_no_annotations_sample_placeholder)
                        .main(Scene.MAIN_CONTENT)
                        .animation(null)
                        .listener(new CoffeeSceneListenerAdapter() {
                            @Override
                            public void onSceneChanged(int sceneId) {
                                Toast.makeText(
                                        SampleNoAnnotationsActivity.this,
                                        "New scene " + sceneId,
                                        Toast.LENGTH_SHORT
                                ).show();
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
                SceneManager.scene(this, Scene.LOADER);
                break;
            case R.id.activity_no_annotations_sample_loader:
                SceneManager.scene(this, Scene.PLACEHOLDER);
                break;
            case R.id.activity_no_annotations_sample_placeholder:
                SceneManager.scene(this, Scene.MAIN_CONTENT);
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
