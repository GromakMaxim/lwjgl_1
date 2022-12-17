package org.example;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

@Getter
@Setter
public class EngineWindow {
    private final int WIDTH = 1024;
    private final int HEIGHT = 768;
    private final String TITLE = "My game";
    public IntBuffer bufferWidth;
    public IntBuffer bufferHeight;
    private long windowId;
    private GLFWVidMode vidMode;

    public void create() {

        // проверка доступности библиотеки
        if (!GLFW.glfwInit()) {
            System.err.println("GLFW не инициализирован");
            System.exit(-1);
        }

        this.windowId = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
        if (this.windowId == 0) {
            System.err.println("window id = 0, не могу создать окно");
            System.exit(-1);
        }


        try (MemoryStack mem = MemoryStack.stackPush()) {
            this.bufferWidth = BufferUtils.createIntBuffer(1);
            this.bufferHeight = BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(this.windowId, this.bufferWidth, this.bufferHeight);
        } catch (Exception ignored) {

        }

        GLFW.glfwSetWindowTitle(this.windowId, TITLE);
        GLFW.glfwSetWindowSize(this.windowId, WIDTH, HEIGHT);
        GLFW.glfwSetWindowAspectRatio(this.windowId, WIDTH, HEIGHT); // ширина разделить на высоту
        GLFW.glfwSetWindowPos(this.windowId,
                              (this.vidMode.width() - this.bufferWidth.get(0)) / 2,
                              (this.vidMode.height() - this.bufferHeight.get(0)) / 2); // установка окна посередине
        GLFW.glfwSetWindowSizeLimits(this.windowId, WIDTH, HEIGHT, 1920, 1080);


        GLFW.glfwMakeContextCurrent(this.windowId);
        GL.createCapabilities();
        GL11.glViewport(0, 0, this.bufferWidth.get(), this.bufferHeight.get());
    }

    public void update() {

    }

    public void destroy() {

    }

}
