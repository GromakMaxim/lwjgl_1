package org.example.controller;

import org.lwjgl.glfw.GLFW;

public abstract class AbstractController implements InteractiveController {
    protected long windowId;
    protected boolean[] keys;

    public AbstractController(long windowId) {
        this.windowId = windowId;
    }

    @Override
    public boolean keyDown(int keyId) {
        return GLFW.glfwGetKey(windowId, keyId) == 1;
    }

    @Override
    public boolean keyPressed(int keyId) {
        return keyDown(keyId) && !keys[keyId];
    }

    @Override
    public boolean keyReleased(int keyId) {
        return !keyDown(keyId) && keys[keyId];
    }

    @Override
    public void handleInput() {
        for (int i = 0; i < this.keys.length; i++) {
            this.keys[i] = this.keyDown(i);
        }
    }
}
