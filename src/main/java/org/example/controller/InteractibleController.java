package org.example.controller;

public interface InteractibleController {
    boolean keyDown(int keyId);

     boolean keyPressed(int keyId);

    boolean keyReleased(int keyId);

    void handleInput();
}
