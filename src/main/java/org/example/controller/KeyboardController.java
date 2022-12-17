package org.example.controller;

import org.lwjgl.glfw.GLFW;

public class KeyboardController extends AbstractController {

    public KeyboardController(long windowId) {
        super(windowId);
        this.keys = new boolean[GLFW.GLFW_KEY_LAST]; // хранит все нажатые кнопки, размер 348
    }
}
