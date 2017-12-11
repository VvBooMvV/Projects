package com.bulletHell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Factory implements Runnable, MouseListener, MouseMotionListener {

    public static BufferedImage mainBackgroundImg;
    public static Button startButton, retryButton, helpButton, backButton, menuButton;
    public static Hero hero;
    public static boolean toggleMotion = true;
//    public int speed = hero.speed;

    public static double mouseX = 300, mouseY = 600;
    private static double fps;
    private boolean running = false;

    Factory(Hero hero, double fps, int screenWidth, int screenHeight){
        this.hero = hero;
        this.fps = fps;
        hero.setX(mouseX);
        hero.setY(mouseY);
        hero.setOutOfBoundsX(screenWidth-hero.getWidth());
        hero.setOutOfBoundsY(screenHeight-hero.getHeight()*2);
    }

    public static void setMouseX(double x){
        mouseX = x;
    }

    public static void setMouseY(double y){
        mouseY = y;
    }

    public static String isFollowOn() {
        String follow = "Follow: ";
        follow += toggleMotion ? "ON" : "OFF";
        return follow;
    }

    public synchronized void start(){
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop(){
        running = false;
    }

    public void run() {

        //offset to repaint based on time, not local hardware.
        long lastStamp = System.nanoTime();
        double tickPerNs = 1000000000D/fps;

        double delta = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastStamp)/tickPerNs;
            lastStamp = now;

            //trigger ~every second
            while(delta >= 1D){

                int x = hero.x();
                int y = hero.y();
                if(mouseX > x){
                    hero.setX(x + hero.speed);
                }else if(mouseX < x){
                    hero.setX(x - hero.speed);
                }
                if(mouseY > y){
                    hero.setY(y + hero.speed);
                } else if(mouseY < y){
                    hero.setY(y - hero.speed);
                }
                if(Math.abs(mouseX - x) < hero.speed){
                    hero.setX(mouseX);
                }
                if(Math.abs(mouseY - y) < hero.speed){
                    hero.setY(mouseY);
                }

                delta -= 1.0D;
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point coordinates = e.getPoint();
        double y = coordinates.getY(),
                x =coordinates.getX();
        if(Gui.state == State.game){

            if(SwingUtilities.isLeftMouseButton(e)){
                System.out.println("left");
                setMouseX(x);
                setMouseY(y);
                if(toggleMotion){
                    toggleMotion = false;
                }
            }
            if(SwingUtilities.isRightMouseButton(e)){
                System.out.println("right");
                toggleMotion = !toggleMotion;
                if(toggleMotion == false){
                    setMouseY(hero.y());
                    setMouseX(hero.x());
                } else {
                    setMouseX(x);
                    setMouseY(y);
                }
            }
        } else if (Gui.state == State.main){
            if(startButton.isClicked(x, y)){
                Gui.state = State.game;
                toggleMotion = true;
            }
            if(helpButton.isClicked(x,y)){
                Gui.state = State.help;
            }
        } else if( Gui.state == State.help){
            if(backButton.isClicked(x,y)){
                Gui.state = State.main;
            }
        } else if (Gui.state == State.restart){
            hero.setX(300);
            hero.setY(600);
            mouseX = 300;
            mouseY = 600;
            toggleMotion = true;
            Gui.enemies.clear();
            Gui.enemySpeed = 0.3;
            if(retryButton.isClicked(x,y)){
                Gui.state = State.game;
            }
            if(menuButton.isClicked(x,y)){
                Gui.state = State.main;
            }
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point coordinates = e.getPoint();
        double y = coordinates.getY();
        double x = coordinates.getX();
        if(Gui.state == State.game){
            if(toggleMotion){
                mouseX = x;
                mouseY = y;
            }
        } else if(Gui.state == State.main){
            startButton.isHovered(x,y);
            helpButton.isHovered(x,y);
        } else if(Gui.state == State.restart){
            retryButton.isHovered(x,y);
            menuButton.isHovered(x,y);
        } else if(Gui.state == State.help){
            backButton.isHovered(x,y);
        }
    }
}
