/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
//import javalib.impworld.*;

import javalib.tunes.Note;
import javalib.worldimages.*;
import javalib.soundworld.World;




import java.util.*;
import java.awt.Color;
//to represent the game World of MarioWorld
class MarioWorld extends World implements MarioConstants {
    Mario mario;
    ArrayList<Obstacles> obs;
    ArrayList<Coin> coins;
    int lives = 0;
    int scores = 0;
    double time = 0;
    int level = 1;

    int jumpsd = 5;  //the sound when mario jump
    int coinsd = 12; //the sound when mario get coins
    int diesd = 14; // the sound when mario loses lives
    int stepsd = 7; //the sound when mario get scores by stepping obs

    Iterator<Note> tuneToPlay = (new Song()).iterator();


    Note note = new Note("C4n2");
    Note note2 = new Note("G4n3");

    MarioWorld(Mario mario,  int lives, int scores) {
        this.mario = mario;
        this.lives = lives;
        this.scores = scores;
        this.obs = new ArrayList<Obstacles>();
        this.coins = new ArrayList<Coin>();


        initObs();
        initCoins();

    }
    //to initialize obstacles
    public void initObs() {
        int i = rand.nextInt(4);
        int interval = 170;
        int xloc = 250;
        while (xloc < width) {
            if (i == 0) {
                this.obs.add(new Mushroom(new CartPt(xloc, 
                        height - ground - 13)));
                xloc = xloc + interval;
                i = rand.nextInt(3);
            }
            if (i == 1 ||
                    i == 3) {
                this.obs.add(new Tube(new CartPt(xloc, 
                        height - ground - 20)));
                xloc = xloc + interval;
                i = rand.nextInt(3);
            }
            if (i == 2) {
                this.obs.add(new Turtle(new CartPt(xloc, 
                        height - ground - 13)));
                xloc = xloc + interval;
                i = rand.nextInt(3);
            }
            this.obs.add(new Bowser(new CartPt(750, 295)));

        }


    }

    //to initialize coins
    public void initCoins() {
        int interval = 140;
        int xloc = 400;
        int i = rand.nextInt(80) + 230;
        while (xloc < width) {
            this.coins.add(new Coin(new CartPt(xloc, i)));
            i = rand.nextInt(80) + 230;
            xloc = xloc + interval;
        }
    }

    /**
     * to initialize(add) new obstacles in this ArrayList<Obstacles>
     * when each obstacle goes through the canvas, or mario steps directly on 
     * a mushroom or turtle obstacle
     */
    public void newObs() {

        for (int index = 0; index < this.obs.size(); index++) {
            if (this.obs.get(index).loc.x < 0) { 
                //the case when one of the obstacle's x-posn 
                //becomes less than zero
                
                //add new obstacle to this obs
                this.obs.add(this.obs.get(index).newObs());  
                //remove the obstacle which moves out of canvas
                this.obs.remove(index);  
                index = index - 1;

            }

        }

        //to generate the case when mario steps on an obstacle
        for (int i = 0; i < this.obs.size(); i++) {
            if (this.obs.get(i).stepOn(this.mario) &&
                    (this.obs.get(i).num == 1 ||
                    this.obs.get(i).num == 3)) {
                //the case when mario steps on a non-deadly obstacle
                this.scores++;
                this.tickTunes.addNote(this.stepsd, this.note2);
                this.obs.add(this.obs.get(i).newObs());
                this.obs.remove(i);
                i = i - 1;
            }


        }
    }

    /**
     * to initialize new coins in this ArrayList<Coin> when mario gets 
     * one of its coin. 
     */
    public void newCoin() {
        for (int index = 0; index < this.coins.size(); index++) {
            if (this.coins.get(index).loc.x < 0) {
                this.coins.add(this.coins.get(index).newCoin());
                this.coins.remove(index);
                index = index - 1;
            }
        }
    }

    // to make World Image
    public WorldImage makeImage() {
        WorldImage img = bg;

        for (Obstacles o: this.obs) {
            img = img.overlayImages(o.makeImage());
        }
        img = img.overlayImages(this.mario.marioImage());


        for (Coin c: this.coins) {
            img = img.overlayImages(c.makeImage());
        }
        img = img.overlayImages(new TextImage(new Posn(600, 60), 
                "Score:  " + Integer.toString(this.scores), 
                20, 30, Color.white));

        img = img.overlayImages(new TextImage(new Posn(600, 80), 
                "Lives:  " + Integer.toString(this.lives), 
                20, 30, Color.white));

        img = img.overlayImages(new TextImage(new Posn(100, 80), 
                "Level  " + Integer.toString(this.level), 
                40, 30, Color.yellow));




        return img;
    }
    /**
     * move the Obstacles and coins on tick
     * in this world
     */
    public void move() {
        //move obstacles
        for (Obstacles o: this.obs) {
            o.move();
        }
        //move coins
        for (Coin c: this.coins) {
            c.move();
        }
        //to initialize mew Coins
        newCoin();
        //to initialize mew Obstacles
        newObs();

    }
    /**
     * Generate the lives of mario
     * loses life if he collides with an Obstacle, 
     * not including stepping on a non-deadly obstacle 
     * (mushroom/turtle)
     */
    public void live() {
        //to reborn Mario when mario loses lives
        for (Obstacles o : this.obs) {
            if (o.collide(this.mario.marioLoc())) {
                this.lives--;

                this.mario.relocate(new CartPt(60, 200));
                this.tickTunes.addNote(this.diesd, this.note2);

            }
        }

    }

    /**
     * Generate a case when mario can get scores:
     * Mario gets points or steps on non-deadly obstacle
     */
    public void score() {
        //to add up the scores when Mario gets coins or
        //it step on undeadly obstacles
        for (int i = 0; i < this.coins.size(); i++) {
            if (this.coins.get(i).getCoin(this.mario)) {
                int i2 = rand.nextInt(80) + 230;
                this.scores++;
                this.tickTunes.addNote(this.coinsd, this.note2);
                this.coins.add(new Coin(new CartPt(width + 100, i2)));
                this.coins.remove(i);
                i = i - 1;
            }
        }

    }
    //to represent the world on next tick
    public void onTick() {
        //the jumpmove of mario when players press "up"
        this.mario.jumpMove();
        //move Obstacles and Coins by tick
        move();

        //Generate the lives of Mario
        live();

        //Generate the Scores of Mario
        score();

        //to accumulate game level (difficulties)
        if (this.scores > 10 &&
                this.scores <= 15) {
            this.level = 2;
        }
        if (this.scores > 20 &&
                this.scores <= 30) {
            this.level = 3;
        }
        if (this.scores > 30) {
            this.level = 4;
        }



        //to level up the game according to the scores players
        //has got
        if (this.level == 2) {  //in level 2, obstacles speed up
            for (Obstacles o: this.obs) {
                o.level2();
            }
        }
        if (this.level == 3) { // in level 3, obstacles speed up
            for (Obstacles o: this.obs) {
                o.level3();
            }
        }
        if (this.level == 4) { 
            // in level 4, Release the boss, 
            //speed of Obstacles remains same as level 3
            for (Obstacles o: this.obs) {
                o.bossComing();
                o.level3();
            }
        }

        //play the Back Ground Music
        this.tickTunes.addNote(10, tuneToPlay.next());



    }





    //control mario by the given key
    public void onKeyEvent(String ke) {
        if (ke.equals("up") &&
                this.mario.y >= height - ground - this.mario.h / 2) {
            //add the jump sound when player presses "up"
            this.keyTunes.addNote(this.jumpsd, this.note);

            //Mario starts to jump, dist begins from 1
            this.mario.dist = 1;


        }
        if (this.mario.y == 200 &&
                ke.equals("down")) { 
            //when mario lost lives and is relocated, it reborns
            //at the certain height 200 (safe place). When player presses
            //"down", mario jumps back to ground
            this.mario.dist = 4 * mario.h;
        }
        if (ke.equals("left") &&
                this.mario.x > 0) { //Mario moves left
            this.mario.x = this.mario.x - speed;
        }
        if (ke.equals("right") &&
                this.mario.x < width) { // Mario moves right
            this.mario.x = this.mario.x + speed;
        }

    }

    //to represent the case when world ends
    public WorldEnd worldEnds() {

        if (this.lives == 0) { //when lives reach 0, game over
            return new WorldEnd(true, new OverlayImages(this.makeImage(), 
                    new TextImage(new Posn(350, 200), 
                            "Congratulation! You got " 
                                    + Integer.toString(this.scores)
                                    + " Points", 30, 30, Color.red)));

        }
        if (this.scores == 50) { //when scores reach 50, player wins
            return new WorldEnd(true, new OverlayImages(this.makeImage(), 
                    new TextImage(new Posn(350, 200), 
                            "Congratulation! You win the Game !!", 
                            30, 30, Color.red)));
        }

        else {
            return new WorldEnd(false, this.makeImage());
        }
    }



}
