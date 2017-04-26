package com.geronimostudios.sample.coffeescene;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geronimostudios.coffeescene.SceneManager;

public class SampleNoAnnotationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SceneManager.create(this);
    }
}
