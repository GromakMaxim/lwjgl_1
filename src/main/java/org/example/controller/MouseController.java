package org.example.controller;

import org.lwjgl.glfw.GLFW;

public class MouseController extends AbstractController{

    public MouseController(long windowId) {
        super(windowId);
        this.keys = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    }

    @Override
    public boolean keyDown(int keyId) {
        return GLFW.glfwGetMouseButton(this.windowId, keyId) == 1;
    }
}
