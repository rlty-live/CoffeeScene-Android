package com.geronimostudios.coffeescene;

/**
 * This listener can be used with {@link SceneManager} to listen
 * the events.
 */
public interface Listener {

    /**
     * This method is called when the current scene change.
     *
     * @param sceneId the new scene that is displayed to the user.
     */
    void onSceneChanged(int sceneId);

    /**
     * This method is called when a scene is hidden.
     *
     * @param sceneId the new scene that is has been hidden.
     */
    void onSceneHidden(int sceneId);

    /**
     * This method is called when a scene is displayed.
     *
     * @param sceneId the new scene that is has been displayed.
     */
    void onSceneDisplayed(int sceneId);
}