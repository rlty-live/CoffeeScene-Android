# CoffeeScene-Android

A simple scene manager for Android.<br>
CoffeeScene allows to switch the current scene of an Activity, ViewGroup and Fragment (v4 supported).<br>
This library can be usefull if you want to switch from a spinner loader to your main content or a placeholder for example.

Sample app
=======
The sample app is available in this repository under **sample/**.

Example
=======
<img src="preview/video_sample.gif"  height="700">

How to use
==========

You have to declare your **scenes** in your activity, viewgroup or fragment.
Each scene requires an unique identifier and a valid layout resource.

```java
@CoffeeScene({
        @Scene(scene = Scene.MAIN_CONTENT, layout = R.layout.sample_activity_main),
        @Scene(scene = Scene.LOADER, layout = R.layout.loader),
        @Scene(scene = Scene.PLACEHOLDER, layout = R.layout.placeholder)
})
public class MainActivity extends Activity {
    ...
}
```

The class **Scene** provides a few scene's identifiers : _Scene.MAIN_CONTENT, Scene.LOADER and Scene.PLACEHOLDER_.<br>
You are free to use it or not, but be sure that each identifier is unique.

Setup with Activities
---------------------
Just call **SceneManager.create(this);**. You don't need to call setContentView();

```java
...
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        SceneManager.create(this);
        
        ...
    }
    ...
}
```

Setup with ViewGroup
--------------------
Just call **SceneManager.create(this);**. All scenes will be automatically created and added to your viewgroup.

```java
...
public class SampleView extends FrameLayout implements View.OnClickListener {
    public SampleView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        SceneManager.create(this); // Same thing than for activities
    }
    
    ...
}
```

Setup with Fragments
---------------------
With fragments **SceneManager.create(this)** will returns the root view that you have to return with **onCreateView**

```java
...
public class SampleFragment extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = SceneManager.create(this);
        ...
        return root;
    }
}

```

Change the current scene
------------------------
You can easily change the current scene with **SceneManager.scene(this, int sceneId);**.<br>
-The first parameter must be the same object that was passed to _SceneManager.create(this);_.<br>
-The second parameter is the unique identifier of the scene.

```java
...
public class SampleActivity extends AppCompatActivity implements View.OnClickListener {
    ...
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_switch_to_progress:
                SceneManager.scene(this, Scene.LOADER); // Change the current scene
                break;
            case R.id.sample_switch_to_placeholder:
                SceneManager.scene(this, Scene.PLACEHOLDER); // Change the current scene
                break;
             ...
        }
    }
}
```

Full example
------------------------
```java
@CoffeeScene(
        value = {
                @Scene(scene = Scene.MAIN_CONTENT, layout = R.layout.sample_activity_main),
                @Scene(scene = Scene.LOADER, layout = R.layout.loader),
                @Scene(scene = Scene.PLACEHOLDER, layout = R.layout.placeholder)
        },
        defaultScene = Scene.MAIN_CONTENT
)
public class SampleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SceneManager.create(this); // Setup scenes

        findViewById(R.id.sample_switch_to_main).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_progress).setOnClickListener(this);
        findViewById(R.id.sample_switch_to_placeholder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_switch_to_main:
                SceneManager.scene(this, Scene.MAIN_CONTENT); // Change the current scene
                break;
            case R.id.sample_switch_to_progress:
                SceneManager.scene(this, Scene.LOADER); // Change the current scene
                break;
            case R.id.sample_switch_to_placeholder:
                SceneManager.scene(this, Scene.PLACEHOLDER); // Change the current scene
                break;
            ...
        }
    }
}
```

License
======
```
Copyright (C) 2014 Geronimo Studios

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
