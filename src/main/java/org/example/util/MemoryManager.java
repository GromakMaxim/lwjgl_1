package org.example.util;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.system.MemoryStack.stackGet;

public class MemoryManager {
    public IntBuffer putData(int[] data) {
        return stackGet().mallocInt(4 * data.length).put(data).flip();
    }

    public FloatBuffer putData(float[] data) {
        return stackGet().mallocFloat(4 * data.length).put(data).flip();
    }

    public DoubleBuffer putData(double[] data) {
        return stackGet().mallocDouble(8 * data.length).put(data).flip();
    }

    public LongBuffer putData(long[] data) {
        return stackGet().mallocLong(8 * data.length).put(data).flip();
    }
}
