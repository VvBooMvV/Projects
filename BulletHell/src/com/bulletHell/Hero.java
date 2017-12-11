package com.bulletHell;

public class Hero extends Sprite {

    public static int speed = 4;
    private int outOfBoundsX, outOfBoundsY;

    public void setOutOfBoundsX(int outOfBoundsX) {
        this.outOfBoundsX = outOfBoundsX;
    }

    public void setOutOfBoundsY(int outOfBoundsY) {
        this.outOfBoundsY = outOfBoundsY;
    }

    public Hero(int height, int width){
        setHeight(height);
        setWidth(width);
    }

    public void setX(double radius){
        x = (int)radius;
    }

    public void setY(double radius){
        y = (int)radius;
    }

    @Override
    public int y(){
        return (y > outOfBoundsY) ? outOfBoundsY : y;
    }

    @Override
    public int x(){
        return (x > outOfBoundsX) ? outOfBoundsX : x;
    }
}
