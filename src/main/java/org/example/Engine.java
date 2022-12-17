package org.example;

import lombok.Getter;


public class Engine {
    @Getter
    private EngineWindow engineWindow;

    public void run() {
        this.init();
    }

    /**
     * логика
     */
    public void init() {
        this.engineWindow = new EngineWindow();
        this.engineWindow.create();
        this.update();
    }

    /**
     * обновление каждый фрейм
     */
    public void update() {
        while (!this.engineWindow.isRequestClosed()){
            // рендеринг
            this.engineWindow.update();
        }
    }
}
