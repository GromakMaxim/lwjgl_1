package org.example;

import lombok.Getter;
import org.lwjgl.glfw.GLFW;


public class Engine {
    @Getter
    private EngineWindow engineWindow;
    private KeyboardController keyboardController;


    public Engine() {
        this.engineWindow = new EngineWindow();
        this.engineWindow.create();
        this.keyboardController = new KeyboardController(this.engineWindow.getWindowId());
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

            // обработка нажатий
            keyboardController.handleKeyboardInput();
            // рендеринг
            this.engineWindow.update();
        }
    }
}
