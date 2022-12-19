package org.example.shapes;

import lombok.Getter;

public enum Shapes {
    TRIANGLE(new float[]{0.0f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f}),

    SQUARE(new float[]{0.5f, 0.5f, 0,
            -0.5f, 0.5f, 0,
            -0.5f, -0.5f, 0,
            0.5f, -0.5f, 0}),

    ROMB(new float[]{0.0f, 0.5f, 0f,
            -0.5f, 0.0f, 0f,
            0.5f, 0.0f, 0f,
            0.5f, 0.0f, 0f,
            -0.5f, 0.0f, 0f,
            0.0f, -0.5f, 0f});

    @Getter
    private float[] arr;

    public int getLength(){
        return this.arr.length;
    }

    Shapes(float[] arr) {
        this.arr = arr;
    }
}
