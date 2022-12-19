package org.example;

import lombok.Getter;
import org.example.controller.KeyboardController;
import org.example.controller.MouseController;
import org.example.shapes.Shapes;
import org.example.shapes.ShapesInt;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;


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

    public FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    public IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    public void run() {
        this.update();
    }

    /**
     * обновление каждый фрейм
     */
    public void update() {

        int vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);


        int iboId= GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, iboId);
        IntBuffer intBuffer = this.storeDataInIntBuffer(ShapesInt.SQUARE.getArray());
        GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL30.GL_STATIC_DRAW);
        MemoryUtil.memFree(intBuffer);

        //генерируем Vertex Buffer и связываем его
        int vboId = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);
        FloatBuffer floatBuffer = this.storeDataInFloatBuffer(Shapes.SQUARE.getArr());
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, floatBuffer, GL30.GL_STATIC_DRAW);
        MemoryUtil.memFree(floatBuffer);
        //создаем атрибут дл¤ вершины, указываем id, тип данных.
        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);


        //разв¤зываем Vbo's и сам лист Vao
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);
        GL30.glBindVertexArray(vaoId);


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

            GL11.glClearColor(0, 1, 0, 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL30.glBindVertexArray(vaoId);
            GL30.glEnableVertexAttribArray(0);
            //GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, Shapes.SQUARE.getLength() / 3);
            GL11.glDrawElements(GL11.GL_TRIANGLES, ShapesInt.SQUARE.getLength(), GL11.GL_UNSIGNED_INT, 0);
            GL30.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(vaoId);

            // рендеринг
            this.engineWindow.update();
        }

        this.engineWindow.destroy();
    }
}
