package com.bulletHell;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private BufferedImage currentImage, originalImage, hoverImage;
    private int buttonWidth = 400;
    private int buttonHeight = 120;
    private int buttonPositionX = Gui.WIDTH/2 - buttonWidth /2 ;
    private int buttonPositionY = 350;

    Button(BufferedImage original, BufferedImage hover, int x, int y, int width, int height){
        currentImage = original;
        originalImage = original;
        hoverImage = hover;
        buttonPositionX = x;
        buttonPositionY = y;
        buttonWidth = width;
        buttonHeight = height;
    }


    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public int getButtonPositionX() {
        return buttonPositionX;
    }

    public int getButtonPositionY() {
        return buttonPositionY;
    }

    public Graphics drawButton(Graphics g, Canvas c){

        g.drawImage(currentImage, buttonPositionX, buttonPositionY, buttonWidth, buttonHeight, c);

        return g;
    }

    public boolean isClicked(double x, double y){
        if(y > buttonPositionY && y < buttonPositionY + buttonHeight && x > buttonPositionX && x < buttonPositionX + buttonWidth){
            return true;
        } else {
            return false;
        }
    }

    public void isHovered(double x, double y){
        if(y > buttonPositionY && y < buttonPositionY + buttonHeight && x > buttonPositionX && x < buttonPositionX + buttonWidth){
            currentImage = hoverImage;
        } else {
            currentImage = originalImage;
        }
    }
}
