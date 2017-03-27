package com.geronimostudios.sample.coffeelayout;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geronimostudios.ui.SceneManager;
import com.geronimostudios.ui.annotations.CoffeeScene;
import com.geronimostudios.ui.annotations.Scene;

@CoffeeScene(
        value = {
                @Scene(scene = Scene.MAIN_CONTENT, layout = R.layout.sample_fragment_main),
                @Scene(scene = Scene.LOADER, layout = R.layout.fragment_loader),
                @Scene(scene = Scene.PLACEHOLDER, layout = R.layout.fragment_placeholder),
        },
        defaultScene = Scene.MAIN_CONTENT
)
public class SampleFragment extends DialogFragment implements View.OnClickListener {

    public static SampleFragment newInstance() {
        return new SampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = SceneManager.create(this);

        root.findViewById(R.id.sample_switch_to_main).setOnClickListener(this);
        root.findViewById(R.id.sample_switch_to_progress).setOnClickListener(this);
        root.findViewById(R.id.go_to_main_from_loader).setOnClickListener(this);
        root.findViewById(R.id.sample_switch_to_placeholder).setOnClickListener(this);
        root.findViewById(R.id.go_to_main_from_placeholder).setOnClickListener(this);

        return root;
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
