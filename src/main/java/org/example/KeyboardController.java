package org.example;

import org.lwjgl.glfw.GLFW;

public class KeyboardController {
    private long windowId;

    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST]; // хранит все нажатые кнопки, размер 348

    public KeyboardController(long windowId) {
        this.windowId = windowId;
    }

    private boolean keyDown(int keyId) {
        return GLFW.glfwGetKey(windowId, keyId) == 1;
    }

    public boolean keyPressed(int keyId) {
        return keyDown(keyId) && !keys[keyId];
    }

    private boolean keyReleased(int keyId) {
        return !keyDown(keyId) && keys[keyId];
    }

    public void handleKeyboardInput() {
        for (int i = 0; i < this.keys.length; i++) {
            this.keys[i] = this.keyDown(i);
        }
    }
}
