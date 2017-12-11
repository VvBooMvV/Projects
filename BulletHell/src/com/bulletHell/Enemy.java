package com.bulletHell;

public class Enemy extends Sprite {

    private int radius;
    private int startY, startX;
    private double angleInRadian;

    public Enemy(int height, int width){
        radius = height / 2;
        setHeight(height);
        setWidth(width);
    }

    public int getRadius(){
        return radius;
    }

    public void setX(double radius){
        x = startX + (int)(radius * Math.cos(angleInRadian));
    }

    public void setY(double radius){
        y = startY + (int)(radius * Math.sin(angleInRadian));
    }


    public void setAngle(double angle){
        angleInRadian = (angle * Math.PI) / 180.0;
    }

    public void setStartY(int y){
        startY = y;
    }

    public void setStartX(int x){
        startX = x;
    }
}