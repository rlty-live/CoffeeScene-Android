package com.geronimostudios.coffeescene;


/**
 * This is a dummy implementation of {@link Listener}.
 * This adapter can be used to avoid to implements of all methods.
 */
public class CoffeeSceneListenerAdapter implements Listener {
    @Override
    public void onSceneChanged(int sceneId) {
        // nothing to do by default
    }

    @Override
    public void onSceneHidden(int sceneId) {
        // nothing to do by default
    }

    @Override
    public void onSceneDisplayed(int sceneId) {
        // nothing to do by default
    }
}
