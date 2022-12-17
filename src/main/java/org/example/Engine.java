package org.example;

import lombok.Getter;
import org.example.controller.KeyboardController;
import org.example.controller.MouseController;
import org.lwjgl.glfw.GLFW;


public class Engine {
    @Getter
    private EngineWindow engineWindow;
    private KeyboardController keyboardController;
    private MouseController mouseController;


    public Engine() {
        this.engineWindow = new EngineWindow();
        this.engineWindow.create();
        this.keyboardController = new KeyboardController(this.engineWindow.getWindowId());
        this.mouseController = new MouseController(this.engineWindow.getWindowId());
    }

    public void run() {
        this.update();
    }

    /**
     * обновление каждый фрейм
     */
    public void update() {
        while (!this.engineWindow.isRequestClosed()) {

            if (this.keyboardController.keyPressed(GLFW.GLFW_KEY_UP)) System.out.println("up");
            if (this.keyboardController.keyPressed(GLFW.GLFW_KEY_DOWN)) System.out.println("down");
            if (this.keyboardController.keyPressed(GLFW.GLFW_KEY_LEFT)) System.out.println("left");
            if (this.keyboardController.keyPressed(GLFW.GLFW_KEY_RIGHT)) System.out.println("right");

            if (this.mouseController.keyPressed(0)) System.out.println("left mouse click");
            if (this.mouseController.keyPressed(1)) System.out.println("right mouse click");

            System.out.println("x: " + this.mouseController.getMouseCallback().getCursorPosX() +
                                       "y: " + this.mouseController.getMouseCallback().getCursorPosY());

            // обработка нажатий
            keyboardController.handleInput();
            mouseController.handleInput();
            // рендеринг
            this.engineWindow.update();
        }
    }
}
