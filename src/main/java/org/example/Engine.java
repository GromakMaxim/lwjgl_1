package org.example;

import lombok.Getter;
import org.example.controller.KeyboardController;
import org.example.controller.MouseController;
import org.example.shapes.Shapes;
import org.example.shapes.ShapesInt;
import org.example.util.MemoryManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL46C;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;
import static org.lwjgl.opengl.GL45C.glCreateBuffers;
import static org.lwjgl.opengl.GL45C.glCreateVertexArrays;


public class Engine {
    @Getter
    private EngineWindow engineWindow;
    private KeyboardController keyboardController;
    private MouseController mouseController;

    private MemoryManager memoryManager;


    public Engine() {
        this.engineWindow = new EngineWindow();
        this.engineWindow.create();
        this.keyboardController = new KeyboardController(this.engineWindow.getWindowId());
        this.mouseController = new MouseController(this.engineWindow.getWindowId());
        this.memoryManager = new MemoryManager();
    }

    public void run() {
        this.update();
    }

    /**
     * обновление каждый фрейм
     */
    public void update() {

        int vertexArrays = glCreateVertexArrays();
        glBindVertexArray(vertexArrays);

        //генерируем Vertex Buffer и связываем его
        int vertexBuffer = glCreateBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, this.memoryManager.putData(Shapes.SQUARE.getArr()), GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);


        int indexBuffer = glCreateBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, this.memoryManager.putData(ShapesInt.SQUARE.getArray()), GL_STATIC_DRAW);

        glBindVertexArray(vertexArrays);

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

            glClearColor(0, 1, 1, 1);
            glClear(GL_COLOR_BUFFER_BIT);

            glBindVertexArray(vertexArrays);

            glDrawElements(GL_TRIANGLES, ShapesInt.SQUARE.getLength(), GL_UNSIGNED_INT, 0);
            glBindVertexArray(vertexArrays);

            // рендеринг
            this.engineWindow.update();
        }

        this.engineWindow.destroy();
    }
}
