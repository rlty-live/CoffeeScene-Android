package com.geronimostudios.coffeescene;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.geronimostudios.coffeescene.annotations.CoffeeScene;
import com.geronimostudios.coffeescene.annotations.Scene;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>The {@link SceneManager} is used to initialize an {@link Activity},
 * {@link ViewGroup}, {@link android.app.Fragment} or
 * {@link android.support.v4.app.Fragment} annotated with {@link CoffeeScene}.</p>
 *
 * {@link SceneManager} is also used to switch the scenes.
 */
public final class SceneManager {

    /**
     * The default animation adapter.
     */
    private static SceneAnimationAdapter sDefaultSceneAnimationAdapter
            = new SceneAnimationAdapter() {
        @Override
        public void showView(View view, boolean animate) {
            if (animate) {
                AnimationHelper.showView(view);
            } else {
                view.setAlpha(1f);
                view.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void hideView(View view, boolean animate) {
            if (animate) {
                AnimationHelper.hideView(view);
            } else {
                view.setAlpha(0f);
                view.setVisibility(View.GONE);
            }
        }
    };

    /**
     * A dictionary of {@link ScenesMeta} associated with their view, activity or fragment.
     */
    private static List<Pair<WeakReference<Object>, ScenesMeta>> sScenesMeta = new LinkedList<>();

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link ViewGroup}
     * and add the scenes into the {@link ViewGroup}.</p>
     *
     * <p>This method will add each scene into {@link ViewGroup} with
     * {@link ViewGroup#addView(View)}.</p>
     *
     * @param context the holding context used to inflate the views.
     * @param reference an object that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull Context context, @NonNull Object reference) {
        return doCreate(
                context,
                reference,
                sDefaultSceneAnimationAdapter,
                new FrameLayout(context),
                null
        );
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link ViewGroup}
     * and add the scenes into the {@link ViewGroup}.</p>
     *
     * <p>This method will add each scene into {@link ViewGroup} with
     * {@link ViewGroup#addView(View)}.</p>
     *
     * @param context the holding context used to inflate the views.
     * @param reference an object that has a {@link CoffeeScene}
     * @param adapter The {@link SceneAnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull Context context,
                                   @NonNull Object reference,
                                   @Nullable SceneAnimationAdapter adapter) {
        return doCreate(
                context,
                reference,
                adapter,
                new FrameLayout(context),
                null
        );
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of an {@link Activity}
     * and add the scenes into the {@link Activity}.</p>
     *
     * <p>This method will add each scene into {@link Activity} with
     * {@link Activity#setContentView(int)} (View)}.</p>
     *
     * @param activity an {@link Activity} that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull Activity activity) {
        ViewGroup root = doCreate(
                activity,
                activity,
                sDefaultSceneAnimationAdapter,
                new FrameLayout(activity),
                null
        );
        activity.setContentView(root);
        return root;
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of an {@link Activity}
     * and add the scenes into the {@link Activity}.</p>
     *
     * <p>This method will add each scene into {@link Activity} with
     * {@link Activity#setContentView(int)} (View)}.</p>
     *
     * @param activity an {@link Activity} that has a {@link CoffeeScene}
     * @param adapter The {@link SceneAnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull Activity activity,
                                   @Nullable SceneAnimationAdapter adapter) {
        ViewGroup root = doCreate(activity, activity, adapter, new FrameLayout(activity), null);
        activity.setContentView(root);
        return root;
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link ViewGroup}
     * and add the scenes into the {@link ViewGroup}.</p>
     *
     * <p>This method will add each scene into {@link ViewGroup} with
     * {@link ViewGroup#addView(View)}.</p>
     *
     * @param view a {@link ViewGroup} that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull ViewGroup view) {
        return doCreate(view.getContext(), view, sDefaultSceneAnimationAdapter, view, null);
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link ViewGroup}
     * and add the scenes into the {@link ViewGroup}.</p>
     *
     * <p>This method will add each scene into {@link ViewGroup} with
     * {@link ViewGroup#addView(View)}.</p>
     *
     * @param view a {@link ViewGroup} that has a {@link CoffeeScene}
     * @param adapter The {@link SceneAnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull ViewGroup view,
                                   @Nullable SceneAnimationAdapter adapter) {
        return doCreate(view.getContext(), view, adapter, view, null);
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link Fragment}
     *  and returns the {@link ViewGroup} to returned by
     *  {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.</p>
     *
     * @param fragment an {@link Fragment} that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull Fragment fragment) {
        return doCreate(
                fragment.getActivity(),
                fragment,
                sDefaultSceneAnimationAdapter,
                new FrameLayout(fragment.getActivity()),
                null
        );
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link Fragment}
     *  and returns the {@link ViewGroup} to returned by
     *  {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}</p>
     *
     * @param fragment an {@link Fragment} that has a {@link CoffeeScene}
     * @param adapter The {@link SceneAnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull Fragment fragment,
                                   @Nullable SceneAnimationAdapter adapter) {
        return doCreate(
                fragment.getActivity(),
                fragment,
                adapter,
                new FrameLayout(fragment.getActivity()),
                null
        );
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link android.support.v4.app.Fragment}
     *  and returns the {@link ViewGroup} to returned by
     *  {@link android.support.v4.app.Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.</p>
     *
     * @param fragment an {@link android.support.v4.app.Fragment} that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull android.support.v4.app.Fragment fragment) {
        return doCreate(
                fragment.getActivity(),
                fragment,
                sDefaultSceneAnimationAdapter,
                new FrameLayout(fragment.getActivity()),
                null
        );
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link android.support.v4.app.Fragment}
     *  and returns the {@link ViewGroup} to returned by
     *  {@link android.support.v4.app.Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}</p>
     *
     * @param fragment an {@link android.support.v4.app.Fragment} that has a {@link CoffeeScene}
     * @param adapter The {@link SceneAnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull android.support.v4.app.Fragment fragment,
                                   @Nullable SceneAnimationAdapter adapter) {
        return doCreate(
                fragment.getActivity(),
                fragment,
                adapter,
                new FrameLayout(fragment.getActivity()),
                null
        );
    }

    /**
     * Creates the scene manager by providing a {@link SceneCreator}.
     *
     * @param creator a {@link SceneCreator}
     */
    public static void create(@NonNull SceneCreator creator) {
        // Save the scene's meta data
        SceneAnimationAdapter adapter = creator.getAdapter();
        sScenesMeta.add(Pair.create(
                new WeakReference<>(creator.getReference()),
                new ScenesMeta(
                        adapter == null ? sDefaultSceneAnimationAdapter : adapter,
                        creator.getScenes(),
                        creator.getListener()
                ))
        );
        if (creator.getDefaultSceneId() != -1) {
            doChangeScene(
                    creator.getReference(),
                    creator.getDefaultSceneId(),
                    false
            );
        }
    }

    /**
     * <p>Release all reference linked with an {@link Activity}.</p>
     *
     * @param activity an {@link Activity} that has called {@link #create(Activity)}
     */
    public static void release(@NonNull Activity activity) {
        releaseMeta(activity);
    }

    /**
     * <p>Release all reference linked with an {@link android.support.v4.app.Fragment}.</p>
     *
     * @param fragment an {@link Activity} that has called
     * {@link #create(android.support.v4.app.Fragment)}
     */
    public static void release(@NonNull android.support.v4.app.Fragment fragment) {
        releaseMeta(fragment);
    }

    /**
     * <p>Release all reference linked with an {@link Fragment}.</p>
     *
     * @param fragment an {@link Activity} that has called {@link #create(Fragment)}
     */
    public static void release(@NonNull Fragment fragment) {
        releaseMeta(fragment);
    }

    /**
     * <p>Release all reference linked with an {@link ViewGroup}.</p>
     *
     * @param view an {@link Activity} that has called {@link #create(ViewGroup)}
     */
    public static void release(@NonNull ViewGroup view) {
        releaseMeta(view);
    }

    private static void releaseMeta(@NonNull Object object) {
        Pair<WeakReference<Object>, ScenesMeta> node = safeGetMetaPair(object);
        if (node != null) {
            sScenesMeta.remove(node);
        }
    }

    /**
     * Parse the annotation {@link CoffeeScene}, of an object.
     * Creates the scenes and add them into a view group.
     * Save the metadata of those scenes into {@link SceneManager#sScenesMeta}.
     *
     * @param context Holding context for the inflater
     * @param object The associated activity, view or fragments that has a {@link CoffeeScene}
     * @param adapter The animation adapter to be used.
     * @param root The root view group in which the scenes will be added.
     *
     * @return The root view group that contains the scenes
     */
    private static ViewGroup doCreate(@NonNull Context context,
                                      @NonNull Object object,
                                      @Nullable SceneAnimationAdapter adapter,
                                      @NonNull ViewGroup root,
                                      @Nullable Listener listener) {
        // Retrieve annotations
        CoffeeScene setup = safeGetSetup(object);
        Scene[] scenes = setup.value();

        // Save scenes meta data
        int defaultScene = getValidDefaultScene(setup, scenes);

        // Create root node with all scenes
        if (adapter == null) {
            adapter = sDefaultSceneAnimationAdapter;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        for (Scene scene : scenes) {
            View view = inflater.inflate(scene.layout(), root, false);
            showOrHideView(scene.scene() == defaultScene, adapter, view, false);
            root.addView(view);
        }

        // Save the scene's meta data
        sScenesMeta.add(Pair.create(
                new WeakReference<>(object),
                new ScenesMeta(root, adapter, scenes, listener)
        ));
        return root;
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param activity The parent activity.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull Activity activity, int scene) {
        doChangeScene(activity, scene);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param view The holder view.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull ViewGroup view, int scene) {
        doChangeScene(view, scene);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param fragment The holder fragment.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull Fragment fragment, int scene) {
        doChangeScene(fragment, scene);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param fragment The holder fragment.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull android.support.v4.app.Fragment fragment, int scene) {
        doChangeScene(fragment, scene);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param activity The parent activity.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull Activity activity, int scene, boolean animate) {
        doChangeScene(activity, scene, animate);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param view The holder view.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull ViewGroup view, int scene, boolean animate) {
        doChangeScene(view, scene, animate);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param fragment The holder fragment.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull Fragment fragment, int scene, boolean animate) {
        doChangeScene(fragment, scene, animate);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param fragment The holder fragment.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull android.support.v4.app.Fragment fragment,
                             int scene,
                             boolean animate) {
        doChangeScene(fragment, scene, animate);
    }

    private static int getValidDefaultScene(CoffeeScene setup, Scene[] scenes) {
        int defaultScene = setup.defaultScene();
        for (Scene scene : scenes) {
            if (scene.scene() == defaultScene) {
                return defaultScene; // the default scene specified by the user is valid
            }
        }
        return scenes[0].scene(); // the default scene is not valid
    }

    private static void showOrHideView(boolean show,
                                       @NonNull SceneAnimationAdapter adapter,
                                       @NonNull View view,
                                       boolean animate) {
        if (show) {
            adapter.showView(view, animate);
        } else {
            adapter.hideView(view, animate);
        }
    }

    private static CoffeeScene safeGetSetup(@NonNull Object object) {
        Class<?> objClass = object.getClass();
        if (!objClass.isAnnotationPresent(CoffeeScene.class)) {
            throw new IllegalArgumentException("Annotation @CoffeeScene is missing");
        }
        return objClass.getAnnotation(CoffeeScene.class);
    }

    private static @Nullable ScenesMeta safeGetMetaData(@NonNull Object object) {
        Pair<WeakReference<Object>, ScenesMeta> node = safeGetMetaPair(object);
        if (node != null) {
            return node.second;
        }
        return null;
    }

    private static @Nullable Pair<WeakReference<Object>, ScenesMeta>
    safeGetMetaPair(@NonNull Object object) {
        for (Pair<WeakReference<Object>, ScenesMeta> pair : sScenesMeta) {
            if (pair.first != null) {
                if (pair.first.get().equals(object)) {
                    return pair;
                }
            }
        }
        return null;
    }

    private static void doChangeScene(@NonNull Object object, int sceneId) {
        doChangeScene(object, sceneId, true);
    }

    private static void doChangeScene(@NonNull Object object, int sceneId, boolean animate) {
        ScenesMeta meta = safeGetMetaData(object);
        if (meta == null) {
            return;
        }
        SceneAnimationAdapter adapter = meta.getSceneAnimationAdapter();
        SparseArray<View> scenesIdsToViews = meta.getScenesIds();
        Listener listener = meta.getListener();

        for (int i = 0; i < scenesIdsToViews.size(); i++) {
            // do change scene
            int viewSceneId = scenesIdsToViews.keyAt(i);
            View view = scenesIdsToViews.get(viewSceneId);
            boolean show = viewSceneId == sceneId;
            showOrHideView(show, adapter, view, animate);

            // Call the listener
            if (listener != null) {
                if (show) {
                    listener.onSceneDisplayed(sceneId);
                } else {
                    listener.onSceneHidden(sceneId);
                }
            }
        }

        // Call the listener
        if (listener != null) {
            listener.onSceneChanged(sceneId);
        }
    }
}
