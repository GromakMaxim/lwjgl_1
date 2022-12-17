package org.example.callback.mouse;

import lombok.Getter;
import org.lwjgl.glfw.GLFW;

@Getter
public class MouseCallback {
    private double cursorPosX = 0;
    private double cursorPosY = 0;

    public MouseCallback(long windowId) {
        GLFW.glfwSetCursorPosCallback(windowId, (winId, posX, posY) -> {
            this.cursorPosX = posX;
            this.cursorPosY = posY;
        });
    }


}
