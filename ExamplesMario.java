/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
import java.awt.Color;
import java.util.*;

import tester.Tester;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;


// Examples
public class ExamplesMario implements MarioConstants {

    //Examples of CartPt
    CartPt p1 = new CartPt(30, 310);
    CartPt p2 = new CartPt(100, 310);
    CartPt p3 = new CartPt(-1, 310);
    CartPt p4 = new CartPt(250, 320);

    // Examples of Mario
    Mario mario = new Mario(30, 310);
    Mario mariol = new Mario(20, 310);
    Mario marior = new Mario(40, 310);
    Mario mariod = new Mario(60, 200);
    
    
    Mario mario2 = new Mario(40, 280);
    Mario mario3 = new Mario(20, 250);
    Mario mario4 = new Mario(50, 260);
    Mario mario5 = new Mario(30, 310);

    // Examples of MarioWorld
    MarioWorld mwd = new MarioWorld(this.mario, 5, 0);
    MarioWorld mwd2 = new MarioWorld(this.mario2, 3, 0);
    MarioWorld mwd3 = new MarioWorld(this.mario3, 5, 50);
    

    // Examples of Obstacles
    Mushroom mushroom = new Mushroom(new CartPt(30, 310));
    Mushroom mushroom2 = new Mushroom(new CartPt(100, 310));
    Tube tube = new Tube(p1);
    Tube tube2 = new Tube(p3);
    Turtle turt1 = new Turtle(p1);
    Turtle turt2 = new Turtle(p3);
    Bowser boss = new Bowser(new CartPt(700, 310));
    Bowser boss2 = new Bowser(p2);
    Bowser boss3 = new Bowser(p2);
    Bowser boss4 = new Bowser(p3);

    //Examples of Coin
    Coin c1 = new Coin(p1);
    Coin c2 = new Coin(p2);
    Coin c3 = new Coin(p3);
    Coin c4 = new Coin(p4);

    //Examples of ArrayList of Obstacles and Coins
    ArrayList<Obstacles> olist1 = new ArrayList<Obstacles>(
            Arrays.asList(this.mushroom, this.tube2, this.turt1));
    ArrayList<Coin> clist1 = new ArrayList<Coin>(
            Arrays.asList(this.c1, this.c2, this.c3));
    
    //to initialize examples
    void reset() {
        p1 = new CartPt(30, 310);
        p2 = new CartPt(100, 310);
        p3 = new CartPt(-1, 310);
        p4 = new CartPt(250, 320);

        mariod.dist = 100;
        
        mario = new Mario(30, 310);
        mario2 = new Mario(40, 280);
        mario2.dist = 1;

        mario3 = new Mario(20, 250);
        mario3.dist = 100;

        mario4 = new Mario(50, 260);
        mario4.dist = 200;

        mushroom = new Mushroom(new CartPt(30, 310));
        mushroom2 = new Mushroom(p3);

        tube = new Tube(p1);
        tube2 = new Tube(p3);

        turt1 = new Turtle(p1);
        turt2 = new Turtle(p3);

        c1 = new Coin(p1);
        c2 = new Coin(p2);
        c4 = new Coin(p4);
        
        boss2 = new Bowser(p2);
        boss2.sp = 5;
        
        boss3 = new Bowser(p2);
        boss3.sp = -5;
        
        boss4 = new Bowser(p3);

        olist1 = new ArrayList<Obstacles>(Arrays.asList(this.mushroom, 
                this.tube2, this.turt1));
        clist1 = new ArrayList<Coin>(Arrays.asList(this.c4, this.c2, this.c3));
    }
    
    //to initialize examples of World
    void initWorld() {
        reset();
        mwd = new MarioWorld(this.mario, 5, 0);
        mwd2 = new MarioWorld(this.mario2, 3 , 0);
        mwd2.lives = 0;
        
        mwd3 = new MarioWorld(this.mario3, 5, 50);
        
        mwd3.obs = olist1;
        mwd3.coins = clist1;
    }


    // test methods in class Mario
    void testMario(Tester t) {
        //test move method in Mario class
        this.mario.move("right");
        t.checkExpect(this.mario, this.marior);

        reset();
        this.mario.move("left");
        t.checkExpect(this.mario, this.mariol);
        
        reset();
        this.mario.move("up"); 
        t.checkExpect(this.mario.marioLoc(), new CartPt(30, 303));
        
        reset();
        this.mariod.move("down");
        t.checkExpect(this.mariod.dist, 96);

        reset();
        t.checkExpect(this.mario.x, 30);
        t.checkExpect(this.mario.y, 310);

        //test marioImage method in mario class
        t.checkExpect(this.mario.marioImage(), 
                new FromFileImage(this.mario.marioLoc(), "mariorun.png"));

        //test relocate method in mario class
        this.mario.relocate(new CartPt(50, 50));
        t.checkExpect(this.mario.marioLoc(), new CartPt(50, 50));

        //test jumpMove method in mario class
        reset();
        this.mario2.jumpMove();
        t.checkExpect(this.mario2.marioLoc(), new CartPt(40, 273));
        this.mario3.jumpMove();
        t.checkExpect(this.mario3.marioLoc(), new CartPt(20, 258));
        this.mario4.jumpMove();
        t.checkExpect(this.mario4.marioLoc(), new CartPt(50, 308));
    }

    //test methods in class Obstacles

    void testObs(Tester t) {

        //test move method in abstract class Obstacles
        reset();
        this.mushroom.move();
        t.checkExpect(this.mushroom.loc, new CartPt(25, 310));
        this.mushroom2.move();
        t.checkExpect(this.mushroom2.loc, new CartPt(-15, 310));
        this.turt1.move();
        t.checkExpect(this.turt1.loc, new CartPt(26, 310));
        this.turt2.move();
        t.checkExpect(this.turt2.loc, new CartPt(-15, 310));
        reset();
        this.tube.move();
        t.checkExpect(this.tube.loc, new CartPt(25, 310));
        this.tube2.move();
        t.checkExpect(this.tube2.loc, new CartPt(-15, 310));
        
        
        //test level method in class Obstacles
        reset();
        this.mushroom.level2();
        t.checkExpect(this.mushroom.sp, 7);
        reset();
        this.mushroom.level3();
        t.checkExpect(this.mushroom.sp, 8);
        
        this.turt1.level2();
        t.checkExpect(this.turt1.sp, 5);
        this.turt1.level3();
        t.checkExpect(this.turt1.sp, 7);
        reset();
        this.tube.level2();
        t.checkExpect(this.tube.sp, 7);
        this.tube2.level3();
        t.checkExpect(this.tube2.sp, 8);

        //test collide method in class Obstacles
        reset();
        t.checkExpect(this.mushroom.collide(this.mario.marioLoc()), true);
        t.checkExpect(this.mushroom2.collide(this.mario.marioLoc()), false);

        reset();
        t.checkExpect(this.tube.collide(this.mario.marioLoc()), true);
        t.checkExpect(this.tube2.collide(this.mario.marioLoc()), false);
        
        reset();
        t.checkExpect(this.turt1.collide(this.mario.marioLoc()), true);
        t.checkExpect(this.turt2.collide(this.mario.marioLoc()), false);

        
        //test makeImage method in abstract class Obstacles
        reset();
        t.checkExpect(this.mushroom.makeImage(), 
                new FromFileImage(p1, "rushroom2.png"));
        t.checkExpect(this.turt1.makeImage(), 
                new FromFileImage(p1, "turtle.png"));
        t.checkExpect(this.tube.makeImage(), 
                new FromFileImage(p1, "tube.png"));

        //test stepOn method in abstract class Obstacles
        t.checkExpect(this.mushroom.stepOn(this.mario), true);
        t.checkExpect(this.mushroom2.stepOn(this.mario), false);
        t.checkExpect(this.turt1.stepOn(this.mario), true);
        t.checkExpect(this.turt2.stepOn(this.mario), false);
        t.checkExpect(this.tube.stepOn(this.mario), true);
        t.checkExpect(this.tube2.stepOn(this.mario), false);

        //test bossComing method in abstract class Obstacles
        reset();
        this.mushroom.bossComing();
        t.checkExpect(this.mushroom, this.mushroom);
        this.turt1.bossComing();
        t.checkExpect(this.turt1, this.turt1);
        this.tube.bossComing();
        t.checkExpect(this.tube, this.tube);

        //test all methods in class Bowser extends to Obstacles
        this.boss.bossComing();
        t.checkExpect(this.boss.sp, -5);
        this.boss4.bossComing();
        t.checkExpect(this.boss4.sp, 5);
        reset();
        this.boss2.move();
        t.checkExpect(this.boss2.loc, new CartPt(105, 310));
        t.checkExpect(this.boss2.makeImage(), 
                new FromFileImage(this.boss2.loc, "bowser4.png"));
        reset();
        this.boss3.move();
        t.checkExpect(this.boss3.loc, new CartPt(95, 310));
        t.checkExpect(this.boss3.makeImage(), 
                new FromFileImage(this.boss3.loc, "bowser3.png"));
        
        reset();
        t.checkExpect(this.boss2.collide(p2), true);
        t.checkExpect(this.boss2.collide(p3), false);
        
        this.boss.level2();
        t.checkExpect(this.boss, this.boss);
        this.boss.level3();
        t.checkExpect(this.boss, this.boss);

    }

    //test methods in Coin class
    void testCoin(Tester t) {

        //test move method in class Coin
        reset();
        this.c1.move();
        t.checkExpect(this.c1.loc, new CartPt(23, 310));
        this.c3.move();
        t.checkExpect(this.c3.loc, new CartPt(-15, 310));

        //test makeImage method in class coin
        reset();
        t.checkExpect(this.c1.makeImage(), new FromFileImage(p1, "coin.png"));

        //test getCoin method in class coin
        t.checkExpect(this.c1.getCoin(this.mario), true);
        t.checkExpect(this.c2.getCoin(this.mario), false);


    }

    //test methods in MarioWorld
    void testMarioWorld(Tester t) {

        //test onKeyEvent in class MarioWorld
        initWorld();
        this.mwd.onKeyEvent("up");
        t.checkExpect(this.mwd.mario.dist, 1);
        initWorld();
        this.mwd.onKeyEvent("right");
        t.checkExpect(this.mwd.mario.marioLoc(), new CartPt(40, 310));
        initWorld();
        this.mwd.onKeyEvent("left");
        t.checkExpect(this.mwd.mario.marioLoc(), new CartPt(20, 310));

        //test WorldEnds method in class MarioWorld
        initWorld();
        t.checkExpect(this.mwd.worldEnds(), new WorldEnd(false, 
                this.mwd.makeImage()));
        t.checkExpect(this.mwd2.worldEnds(), new WorldEnd(true, 
                new OverlayImages(this.mwd2.makeImage(), 
                new TextImage(new Posn(350, 200), 
                        "Congratulation! You got " 
                                + Integer.toString(this.mwd2.scores)
                                + " Points", 30, 30, Color.red))));
        t.checkExpect(this.mwd3.worldEnds(),  new WorldEnd(true, 
                new OverlayImages(this.mwd3.makeImage(), 
                new TextImage(new Posn(350, 200), 
                        "Congratulation! You win the Game !!", 
                        30, 30, Color.red))));
        
        //test move method in class MarioWorld
        initWorld();
        this.mwd3.move();
        t.checkExpect(this.mwd3.obs.get(0).loc,  new CartPt(25, 310));
        t.checkExpect(this.mwd3.obs.get(1).loc,  new CartPt(26, 310));
        t.checkExpect(this.mwd3.obs.size(), 3);
        t.checkExpect(this.mwd3.coins.get(0).loc, new CartPt(243, 320));
        t.checkExpect(this.mwd3.coins.get(1).loc, new CartPt(93, 310));
        
        
        //test live method in class MarioWorld
        initWorld();
        this.mwd3.mario = new Mario(25, 310);
        this.mwd3.live();
        t.checkExpect(this.mwd3.lives, 4);
        
        //test score method in class MarioWorld
        initWorld();
        this.mwd3.mario = new Mario(250, 320);
        this.mwd3.score();
        t.checkExpect(this.mwd3.scores, 51);
        
    }

    //test Random 
    void testRandom(Tester t) {
        int i = rand.nextInt(4);
        t.checkOneOf(i, 0, 1, 2, 3);
    }
    
    //test methods which invoke Random
    void testRandomMethod(Tester t) {
        initWorld();
        this.mwd.initObs();
        t.checkOneOf(this.mwd.obs.size(), 8, 9);
        
        t.checkExpect(this.mwd.coins.size(), 3);
        this.mwd.initCoins();
        t.checkExpect(this.mwd.coins.size(), 6);
        //it's complicated to test random part in this method because
        //the range of y value is big.
        
        
        //test newObs method in Obstacles
        t.checkOneOf("newObs", this.mushroom.newObs(), 
                new Mushroom(new CartPt(width, height - ground - 13)), 
                        new Tube(new CartPt(width, height - ground - 20)), 
                        new Turtle(new CartPt(width, height - ground - 13)));
        
    }




}
