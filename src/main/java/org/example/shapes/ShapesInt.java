package org.example.shapes;

import lombok.Getter;

public enum ShapesInt {
    SQUARE(new int[]{0, 1, 2, 0, 2, 3});

    @Getter
    private int[] array;

    ShapesInt(int[] array) {
        this.array = array;
    }

    public int getLength() {
        return this.array.length;
    }
}
