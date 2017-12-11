package com.bulletHell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gui extends Canvas implements Runnable{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    private final String TITLE = "Here Box, Here!";

    JFrame frame;
    public static State state = State.main;

    private boolean running = false;
    Factory heroFactory;
    static BufferStrategy bs;
    static Graphics g;
    Timer time = new Timer(1000,new TimerListener());
    boolean isTimer = true;
    TimerListener timeListener;

    private double changeHeight = 0.0;
    public static double enemySpeed = 0.3;
    private double enemySpeedIncrement = 0.01;
    private boolean isRender = false;
    private final int SPRITE_SIZE = 20;
    private final double FPS = 120D;
    public static List<Enemy> enemies;
    private boolean newLevelLoading = false;
    boolean settingUpEnemies = true;

    public Gui(){
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setSize( WIDTH,  HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.setFocusable(true);

        try{
            //main
            Factory.mainBackgroundImg = ImageIO.read(getClass().getResource("/Mainbackground.png"));

            BufferedImage Img = ImageIO.read(getClass().getResource("/help.png"));
            BufferedImage HoverImg = ImageIO.read(getClass().getResource("/helpHover.png"));
            int buttonWidth = 300;
            int buttonHeight = 80;
            int buttonPositionX = Gui.WIDTH/2 - buttonWidth /2 ;
            int buttonPositionY = 550;
            Factory.helpButton = new Button(Img, HoverImg, buttonPositionX, buttonPositionY, buttonWidth, buttonHeight);

            Img = ImageIO.read(getClass().getResource("/start.png"));
            HoverImg = ImageIO.read(getClass().getResource("/startHover.png"));
            buttonWidth = 300;
            buttonHeight = 80;
            buttonPositionX = Gui.WIDTH/2 - buttonWidth /2  ;
            buttonPositionY = 630;
            Factory.startButton = new Button(Img, HoverImg, buttonPositionX, buttonPositionY, buttonWidth, buttonHeight);


            //help
            Img = ImageIO.read(getClass().getResource("/back.png"));
            HoverImg = ImageIO.read(getClass().getResource("/backHover.png"));
            buttonWidth = 300;
            buttonHeight = 80;
            buttonPositionX = Gui.WIDTH/2 - buttonWidth /2  ;
            buttonPositionY = 630;
            Factory.backButton = new Button(Img, HoverImg, buttonPositionX, buttonPositionY, buttonWidth, buttonHeight);


            //game
            Img = ImageIO.read(getClass().getResource("/retry.png"));
            HoverImg = ImageIO.read(getClass().getResource("/retryHover.png"));
            buttonWidth = 300;
            buttonHeight = 80;
            buttonPositionX = Gui.WIDTH/2 - buttonWidth /2 ;
            buttonPositionY = 550;
            Factory.retryButton = new Button(Img, HoverImg, buttonPositionX, buttonPositionY, buttonWidth, buttonHeight);

            Img = ImageIO.read(getClass().getResource("/menu.png"));
            HoverImg = ImageIO.read(getClass().getResource("/menuHover.png"));
            buttonWidth = 300;
            buttonHeight = 80;
            buttonPositionX = Gui.WIDTH/2 - buttonWidth /2  ;
            buttonPositionY = 630;
            Factory.menuButton = new Button(Img, HoverImg, buttonPositionX, buttonPositionY, buttonWidth, buttonHeight);

        }catch(Exception e){
            //game failed to load
            JOptionPane.showMessageDialog(frame, e.toString(), "Reload - Missing File", JOptionPane.ERROR_MESSAGE);
        }

        enemies = new ArrayList<>();
        heroFactory = new Factory(new Hero(SPRITE_SIZE, SPRITE_SIZE), 2*FPS, WIDTH, HEIGHT);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                heroFactory.stop();
                stop();
                time.stop();
            }
        });
        this.addMouseListener(heroFactory);
        this.addMouseMotionListener(heroFactory);

        frame.setVisible(true);
    }

    public void update(){
        //update x and y position

        if(enemies.size() <= 1){
            changeHeight = 0;
            enemies.clear();
            settingUpEnemies = true;

            Random r = new Random(System.currentTimeMillis());
            int randomEnemy = r.nextInt(5);

            if(newLevelLoading){
                randomEnemy = -1;
            }

//            //for test
//            randomEnemy = 2;
            switch(randomEnemy){
                case 0:
                    straightEnemies();
                    settingUpEnemies = false;
                    break;
                case 1:
                    randomEnemies();
                    settingUpEnemies = false;
                    break;
                case 2:
                    spiralEnemies();
                    settingUpEnemies = false;
                    break;
                case 3:
                    circularEnemies();
                    settingUpEnemies = false;
                    break;
                case 4:
                    crossPatternEnemies();
                    settingUpEnemies = false;
                    break;
                default:
                    //for pausing enemy loads
                    settingUpEnemies = true;
                    break;
            }

            if(!settingUpEnemies){
                enemySpeed += enemySpeedIncrement;
            }
        }

        //clear up memory by removing enemy if enemy is off screen (except the top)
        for(int i = 0; i < enemies.size(); i++){
            int curX = enemies.get(i).x();
            int curY = enemies.get(i).y();
            if(curX > WIDTH + SPRITE_SIZE ){
                enemies.remove(i);
            }
            if(curX < -SPRITE_SIZE){
                enemies.remove(i);
            }
            if(curY > HEIGHT){
                enemies.remove(i);
            }
            if(curY < -3*HEIGHT){
                enemies.remove(i);
            }
        }

        for(Enemy enemy : enemies){
            enemy.setY(changeHeight);
            enemy.setX(changeHeight);
            if(RectCircleColliding(enemy, Factory.hero)){
//                System.out.println("collided");
                render();
                state = State.restart;
                time.stop();
            }
        }

    }

    public void render(){

        bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }
        g = bs.getDrawGraphics();

        if(state == State.game){

            if(isTimer){
                timeListener = ((TimerListener)time.getActionListeners()[0]);
                timeListener.reset();
                time.start();
                isTimer = false;
            }

            //background
            g.setColor(new Color(20,20, 20));
            g.fillRect(0,0, WIDTH, HEIGHT);

            //timer
            g.setColor(new Color(225,225,225));
            g.setFont( new Font("Courier", Font.PLAIN, 30));
            g.drawString(timeListener.timePassed(), 520,30);
            g.drawString(Factory.isFollowOn(), 20,30);

            //hero
            g.setColor(new Color(0,150,225));
            g.fillRect(Factory.hero.x(), Factory.hero.y(), Factory.hero.getWidth(), Factory.hero.getHeight());

            //enemies
            for(Enemy enemy: enemies){
                g.setColor(new Color(150,40,225));
                g.fillOval(enemy.x(), enemy.y(), enemy.getWidth(), enemy.getHeight());
            }
        } else if(state == State.restart){
            Factory.retryButton.drawButton(g, this);
            Factory.menuButton.drawButton(g, this);
            isTimer = true;
        } else if(state == State.main){
            g.setColor(new Color(20,20, 20));
            g.fillRect(0,0, WIDTH, HEIGHT);

            g.drawImage(Factory.mainBackgroundImg, 0, 0, WIDTH, HEIGHT, this);

            Factory.startButton.drawButton(g, this);
            Factory.helpButton.drawButton(g, this);
        } else if(state == State.help){
            g.setColor(new Color(20,20, 20));
            g.fillRect(0,0, WIDTH, HEIGHT);

            g.setColor(new Color(225,225,225));


            g.setFont( new Font("Courier", Font.PLAIN, 30));
            g.drawString("HELP", 255,130);
            g.drawString("____", 255,133);

            int shiftHeight = 140;
            g.setFont( new Font("Courier", Font.PLAIN, 25));
            g.drawString("Objective:", 25,50+shiftHeight);

            g.setFont( new Font("Courier", Font.PLAIN, 15));
            g.drawString("Try to lure the box by wiggling your mouse pointer to where", 25,75+shiftHeight);
            g.drawString("you want the box to follow (over black area). Avoid getting", 25,90+shiftHeight);
            g.drawString("hit by the circles, and survive for as long as you can.", 25,105+shiftHeight);

            shiftHeight = 160;
            g.setFont( new Font("Courier", Font.PLAIN, 25));
            g.drawString("Controls:", 25,150+shiftHeight);

            g.setFont( new Font("Courier", Font.PLAIN, 15));
            g.drawString("LEFT CLICK - command the box to go to the point clicked on.", 25,175+shiftHeight);
            g.drawString("RIGHT CLICK - toggle the follow function:", 25,190+shiftHeight);
            g.drawString("on = box will try to follow the mouse (default)", 65,205+shiftHeight);
            g.drawString("off = the box stays at current location", 65,220+shiftHeight);

            /*
            *
              Objective:
                try to lure the box by wiggling your mouse pointer to where
                you want the box to follow (over black area). Avoid getting
                hit by the circles, and survive for as long as you can.

              Controls:
                left click: command the box to go to the point clicked on.
                right click: toggle the follow function:
                    on = box will try to follow the mouse
                    off = stays at current location
            *
            */

            Factory.backButton.drawButton(g, this);
        }


        g.dispose();
        bs.show();

        if(!settingUpEnemies){
            changeHeight += enemySpeed;
        }
    }

    public void run() {
        //offset to repaint based on time, not speed of local hardware.
        long lastStamp = System.nanoTime();
        double tickPerNs = 1000000000D/FPS;
        double delta = 0;

        while(running){
            try{
                long now = System.nanoTime();
                delta += (now - lastStamp)/tickPerNs;
                lastStamp = now;

                //trigger ~every second
                while(delta >= 1D){
                    if(state == State.game){
                        update();
                    }
                    delta -= 1.0D;
                    isRender = true;
                }

                if(isRender){
                    render();
                }
            }catch (Exception e){
                //don't break the game if you hit an exception by accident.
            }
        }
    }

    public boolean RectCircleColliding(Enemy circle, Hero rect) {
        int distX = Math.abs(circle.x() + circle.getRadius() - rect.x() - rect.getWidth() / 2);
        int distY = Math.abs(circle.y() + circle.getRadius() - rect.y() - rect.getHeight() / 2);

        if (distX > (rect.getWidth() / 2 + circle.getRadius())) {
            return false;
        }
        if (distY > (rect.getHeight() / 2 + circle.getRadius())) {
            return false;
        }

        if (distX <= (rect.getWidth() / 2)) {
            return true;
        }
        if (distY <= (rect.getHeight() / 2)) {
            return true;
        }

        int dx = distX - rect.getWidth() / 2;
        int dy = distY - rect.getHeight() / 2;
        return (dx * dx + dy * dy <= (circle.getRadius() * circle.getRadius()));
    }

    private void straightEnemies(){

        int startHeight = -20,
                heightSpace = 80,
                radius = 35,
                numberOfEnemiesRow = 8,
                numberOfEnemiesColumn = 5,
                shift = 15;

        Random r = new Random(System.currentTimeMillis());
        int rand = r.nextInt();
        boolean isLeaningLeft = (rand % 2 == 0);

        if(isLeaningLeft){
            for(int j = 0; j < numberOfEnemiesColumn; j++){
                for(int i = 0; i < numberOfEnemiesRow; i++){
                    enemies.add(new Enemy(SPRITE_SIZE, SPRITE_SIZE));
                }
                for(int i = j*numberOfEnemiesRow; i < numberOfEnemiesRow * (j+1); i++){
                    enemies.get(i).setStartX( (i- j * numberOfEnemiesRow) * (radius * 2) + 80 - (shift * j));
                    enemies.get(i).setStartY(startHeight - (heightSpace * j));
                    enemies.get(i).setAngle(90);
                }
            }
        } else {
            for(int j = 0; j < numberOfEnemiesColumn; j++){
                for(int i = 0; i < numberOfEnemiesRow; i++){
                    enemies.add(new Enemy(SPRITE_SIZE, SPRITE_SIZE));
                }
                for(int i = j*numberOfEnemiesRow; i < numberOfEnemiesRow * (j+1); i++){
                    enemies.get(i).setStartX( (i- j * numberOfEnemiesRow) * (radius * 2) + (shift * (j+1)));
                    enemies.get(i).setStartY(startHeight - (heightSpace * j));
                    enemies.get(i).setAngle(90);
                }
            }
        }
    }

    private void randomEnemies(){

        int numberOfEnemies = 8;
        int startAngle = 50;
        int incrementAngleBy = 10;
        int numberOfRows = 4;
        Random r = new Random(System.currentTimeMillis());

        for(int j = 0; j < numberOfRows; j++){
            for(int i = 0; i < numberOfEnemies; i++){
                enemies.add(new Enemy(SPRITE_SIZE, SPRITE_SIZE));
//                enemies.get(i+numberOfEnemies*j).setStartX(WIDTH/ (2 + j) - (SPRITE_SIZE/2));
                enemies.get(i+numberOfEnemies*j).setStartX((WIDTH/ (2 + r.nextInt(5))) - (SPRITE_SIZE/2));
                enemies.get(i+numberOfEnemies*j).setStartY(-40 - (80*j) - ( 2 * incrementAngleBy * i));
                enemies.get(i+numberOfEnemies*j).setAngle(startAngle + (incrementAngleBy * i));
            }
        }
    }

    private void spiralEnemies(){

        Random r = new Random(System.currentTimeMillis());

        int numberOfEnemies = 8;
        int startAngle = 95;
        int incrementAngleBy = 10;
        int numberOfRows = 4;

        if(r.nextInt(200) % 2 == 0){
            for(int j = 0; j < numberOfRows; j++){
                for(int i = 0; i < numberOfEnemies; i++){
                    enemies.add(new Enemy(SPRITE_SIZE, SPRITE_SIZE));
                    enemies.get(i+numberOfEnemies*j).setStartX((WIDTH - (r.nextInt(5))) + (SPRITE_SIZE/2));
                    enemies.get(i+numberOfEnemies*j).setStartY(-40 - (80*j) - ( 2 * incrementAngleBy * i));
                    enemies.get(i+numberOfEnemies*j).setAngle(startAngle + (incrementAngleBy * i));
                }
            }
        }else {
            numberOfEnemies = 8;
            startAngle = 40;
            incrementAngleBy = 10;
            numberOfRows = 4;

            for(int j = 0; j < numberOfRows; j++){
                for(int i = 0; i < numberOfEnemies; i++){
                    enemies.add(new Enemy(SPRITE_SIZE, SPRITE_SIZE));
                    enemies.get(i+numberOfEnemies*j).setStartX(WIDTH/ (2 + j) - (SPRITE_SIZE/2));
                    enemies.get(i+numberOfEnemies*j).setStartY(-40 - (80*j) - ( 2 * incrementAngleBy * i));
                    enemies.get(i+numberOfEnemies*j).setAngle(startAngle + (incrementAngleBy * i));
                }
            }
        }
    }

    private void circularEnemies(){

        int numberOfEnemies = 11;
        int startAngle = 40, angleIncrement = 10;
        int widthRange = WIDTH - 120, widthShift = 60;
        int heightRange = HEIGHT/2, heightShift = -100;
        int numberOfSpawns = 4;
        Random r = new Random(System.currentTimeMillis());
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for(int i = 0; i < numberOfSpawns; i++){
            x.add(r.nextInt(widthRange));
            y.add(r.nextInt(heightRange));
        }

        for(int j = 0; j < numberOfSpawns; j++){
            for(int i = 0; i < numberOfEnemies; i++){
                enemies.add(new Enemy(SPRITE_SIZE, SPRITE_SIZE));
                enemies.get(i + numberOfEnemies*j).setStartX(x.get(j) + widthShift);
                enemies.get(i + numberOfEnemies*j).setStartY(y.get(j) + heightShift);
                enemies.get(i + numberOfEnemies*j).setAngle(startAngle + angleIncrement*i);
            }
        }

    }

    private void crossPatternEnemies() {

        int halfTheEnemies = 5;
        int heightRange = HEIGHT/3, heightShift = -100;
        Random r = new Random(System.currentTimeMillis());


        for(int i = 0; i < halfTheEnemies; i++){
            //left
            enemies.add(new Enemy(SPRITE_SIZE,SPRITE_SIZE));
            enemies.get(i).setStartX(40 + 60*i);
            enemies.get(i).setStartY(r.nextInt(heightRange)+heightShift);
            enemies.get(i).setAngle(80);
        }
        for(int i = halfTheEnemies; i < halfTheEnemies*2; i++){
            //right
            enemies.add(new Enemy(SPRITE_SIZE,SPRITE_SIZE));
            enemies.get(i).setStartX(40 + 60*i);
            enemies.get(i).setStartY( r.nextInt(heightRange)+heightShift);
            enemies.get(i).setAngle(100);
        }


        for(int i = halfTheEnemies*2; i < halfTheEnemies*3; i++){
            //left
            enemies.add(new Enemy(SPRITE_SIZE,SPRITE_SIZE));
            enemies.get(i).setStartX(40 + 60* (i -halfTheEnemies*2));
            enemies.get(i).setStartY(r.nextInt(heightRange)+heightShift);
            enemies.get(i).setAngle(80);
        }
        for(int i = halfTheEnemies*3; i < halfTheEnemies*4; i++){
            //right
            enemies.add(new Enemy(SPRITE_SIZE,SPRITE_SIZE));
            enemies.get(i).setStartX(40 + 60* (i -halfTheEnemies*2));
            enemies.get(i).setStartY(r.nextInt(heightRange)+ heightShift);
            enemies.get(i).setAngle(100);
        }


        for(int i = halfTheEnemies+ halfTheEnemies*3; i < halfTheEnemies+ halfTheEnemies*4; i++){
            //left
            enemies.add(new Enemy(SPRITE_SIZE,SPRITE_SIZE));
            enemies.get(i).setStartX(40 + 60* (i -halfTheEnemies*4));
            enemies.get(i).setStartY(heightShift - r.nextInt(heightRange));
            enemies.get(i).setAngle(80);
        }
        for(int i = halfTheEnemies + halfTheEnemies*4; i < halfTheEnemies + halfTheEnemies*5; i++){
            //right
            enemies.add(new Enemy(SPRITE_SIZE,SPRITE_SIZE));
            enemies.get(i).setStartX(40 + 60* (i -halfTheEnemies*4));
            enemies.get(i).setStartY( heightShift - r.nextInt(heightRange));
            enemies.get(i).setAngle(100);
        }
    }

    public synchronized void start(){
        running = true;
        new Thread(this).start();
        heroFactory.start();
    }

    public synchronized void stop(){
        running = false;
        heroFactory.stop();
    }
}
