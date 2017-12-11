package com.bulletHell;

public abstract class Sprite {
    private int height, width;
    protected int x = 0;
    protected int y = 0;

    public abstract void setX(double radius);

    public abstract void setY(double radius);

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }
}
