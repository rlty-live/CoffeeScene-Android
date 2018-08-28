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

import com.geronimostudios.coffeescene.animations.AnimationAdapter;
import com.geronimostudios.coffeescene.animations.SceneAnimations;
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
 * {@link SceneManager} is also used to switch the mScenes.
 */
public final class SceneManager {

    /**
     * A dictionary of {@link ScenesMeta} associated with their view, activity or fragment.
     */
    private static List<Pair<WeakReference<Object>, ScenesMeta>> sScenesMeta = new LinkedList<>();

    /**
     * <p>Parse the annotation {@link CoffeeScene} of an Object
     * and creates the mScenes.</p>
     *
     * @param context the holding context used to inflate the views.
     * @param reference an object that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull Context context, @NonNull Object reference) {
        return doCreate(
                context,
                reference,
                SceneAnimations.FADE,
                new FrameLayout(context),
                null
        );
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of an Object
     * and creates the mScenes.</p>
     *
     * @param context the holding context used to inflate the views.
     * @param reference an object that has a {@link CoffeeScene}
     * @param adapter The {@link AnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull Context context,
                                   @NonNull Object reference,
                                   @Nullable AnimationAdapter adapter) {
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
     * and add the mScenes into the {@link Activity}.</p>
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
                SceneAnimations.FADE,
                new FrameLayout(activity),
                null
        );
        activity.setContentView(root);
        return root;
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of an {@link Activity}
     * and add the mScenes into the {@link Activity}.</p>
     *
     * <p>This method will add each scene into {@link Activity} with
     * {@link Activity#setContentView(int)} (View)}.</p>
     *
     * @param activity an {@link Activity} that has a {@link CoffeeScene}
     * @param adapter The {@link AnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull Activity activity,
                                   @Nullable AnimationAdapter adapter) {
        ViewGroup root = doCreate(activity, activity, adapter, new FrameLayout(activity), null);
        activity.setContentView(root);
        return root;
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link ViewGroup}
     * and add the mScenes into the {@link ViewGroup}.</p>
     *
     * <p>This method will add each scene into {@link ViewGroup} with
     * {@link ViewGroup#addView(View)}.</p>
     *
     * @param view a {@link ViewGroup} that has a {@link CoffeeScene}
     */
    public static ViewGroup create(@NonNull ViewGroup view) {
        return doCreate(view.getContext(), view, SceneAnimations.FADE, view, null);
    }

    /**
     * <p>Parse the annotation {@link CoffeeScene} of a {@link ViewGroup}
     * and add the mScenes into the {@link ViewGroup}.</p>
     *
     * <p>This method will add each scene into {@link ViewGroup} with
     * {@link ViewGroup#addView(View)}.</p>
     *
     * @param view a {@link ViewGroup} that has a {@link CoffeeScene}
     * @param adapter The {@link AnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull ViewGroup view,
                                   @Nullable AnimationAdapter adapter) {
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
                SceneAnimations.FADE,
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
     * @param adapter The {@link AnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull Fragment fragment,
                                   @Nullable AnimationAdapter adapter) {
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
                SceneAnimations.FADE,
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
     * @param adapter The {@link AnimationAdapter} to be used.
     */
    public static ViewGroup create(@NonNull android.support.v4.app.Fragment fragment,
                                   @Nullable AnimationAdapter adapter) {
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
        AnimationAdapter adapter = creator.getAdapter();
        sScenesMeta.add(Pair.create(
                new WeakReference<>(creator.getReference()),
                new ScenesMeta(
                        adapter == null ? SceneAnimations.FADE : adapter,
                        creator.getScenes(),
                        creator.getListener()
                ))
        );
        if (creator.getFirstSceneId() != -1) {
            doChangeScene(
                    creator.getReference(),
                    creator.getFirstSceneId(),
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

    /**
     * @param reference The reference of the mScenes, it can be a {@link ViewGroup},
     *                  {@link android.support.v4.app.Fragment}, {@link Fragment},
     *                  {@link Activity} or a custom reference if you know what you are doing.
     * @return The current scene if linked to the provided reference or {@link Integer#MIN_VALUE}.
     */
    public static int current(@NonNull Object reference) {
        ScenesMeta meta = safeGetMetaData(reference);
        if (meta == null) {
            return Integer.MIN_VALUE;
        }
        return meta.getCurrentSceneId();
    }

    private static void releaseMeta(@NonNull Object object) {
        Pair<WeakReference<Object>, ScenesMeta> node = safeGetMetaPair(object);
        if (node != null) {
            sScenesMeta.remove(node);
        }
    }

    /**
     * Parse the annotation {@link CoffeeScene}, of an object.
     * Creates the mScenes and add them into a view group.
     * Save the metadata of those mScenes into {@link SceneManager#sScenesMeta}.
     *
     * @param context Holding context for the inflater
     * @param object The associated activity, view or fragments that has a {@link CoffeeScene}
     * @param adapter The animation adapter to be used.
     * @param root The root view group in which the mScenes will be added.
     *
     * @return The root view group that contains the mScenes
     */
    private static ViewGroup doCreate(@NonNull Context context,
                                      @NonNull Object object,
                                      @Nullable AnimationAdapter adapter,
                                      @NonNull ViewGroup root,
                                      @Nullable Listener listener) {
        // Retrieve annotations
        CoffeeScene setup = safeGetSetup(object);
        Scene[] scenes = setup.value();

        // Create root node with all mScenes
        if (adapter == null) {
            adapter = SceneAnimations.FADE;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        for (Scene scene : scenes) {
            View view = inflater.inflate(scene.layout(), root, false);
            root.addView(view);
        }

        // Save the scene's meta data
        ScenesMeta meta = new ScenesMeta(root, adapter, scenes, listener);
        sScenesMeta.add(Pair.create(
                new WeakReference<>(object),
                meta
        ));
        //noinspection unchecked
        adapter.doChangeScene(
                meta.getScenesIdsToViews(),
                meta.getScenesParams(),
                getValidFirstScene(setup, scenes),
                false
        );
        return root;
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param reference The reference.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull Object reference, int scene) {
        doChangeScene(reference, scene);
    }

    /**
     * Switch to another {@link Scene}.
     *
     * @param reference The reference.
     * @param scene The scene id. See {@link Scene#scene()}.
     */
    public static void scene(@NonNull Object reference, int scene, boolean animate) {
        doChangeScene(reference, scene, animate);
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

    private static int getValidFirstScene(CoffeeScene setup, Scene[] scenes) {
        int firstScene = setup.first();
        for (Scene scene : scenes) {
            if (scene.scene() == firstScene) {
                return firstScene; // the default scene specified by the user is valid
            }
        }
        return scenes[0].scene(); // the default scene is not valid
    }

    private static CoffeeScene safeGetSetup(@NonNull Object object) {
        Class<?> objClass = object.getClass();
        if (!objClass.isAnnotationPresent(CoffeeScene.class)) {
            throw new RuntimeException("Annotation @CoffeeScene is missing");
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
        SparseArray<List<View>> scenesIdsToViews = meta.getScenesIdsToViews();
        //noinspection unchecked
        meta.getSceneAnimationAdapter()
                .doChangeScene(scenesIdsToViews, meta.getScenesParams(), sceneId, animate);
        meta.setCurrentSceneId(sceneId);
        notifyListener(scenesIdsToViews, sceneId, meta.getListener());
    }

    private static void notifyListener(SparseArray<List<View>> scenesIdsToViews,
                                       int sceneId,
                                       Listener listener) {

        for (int i = 0; i < scenesIdsToViews.size(); i++) {
            int viewSceneId = scenesIdsToViews.keyAt(i);
            boolean show = viewSceneId == sceneId;

            if (listener != null) {
                if (show) {
                    listener.onSceneDisplayed(sceneId);
                } else {
                    listener.onSceneHidden(sceneId);
                }
            }
        }

        if (listener != null) {
            listener.onSceneChanged(sceneId);
        }
    }
}
