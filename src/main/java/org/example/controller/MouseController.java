package org.example.controller;

import lombok.Getter;
import org.example.callback.mouse.MouseCallback;
import org.lwjgl.glfw.GLFW;

public class MouseController extends AbstractController {
    @Getter
    private MouseCallback mouseCallback;

    public MouseController(long windowId) {
        super(windowId);
        this.keys = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
        this.mouseCallback = new MouseCallback(windowId);
    }

    @Override
    public boolean keyDown(int keyId) {
        return GLFW.glfwGetMouseButton(this.windowId, keyId) == 1;
    }
}
