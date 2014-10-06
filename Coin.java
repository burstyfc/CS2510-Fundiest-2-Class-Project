/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;
public class Coin implements MarioConstants {
    CartPt loc;
    int h;       //height of coin image
    int w;       //width of coin image

    Coin(CartPt loc) {
        this.loc = loc;
        this.h = 22;
        this.w = 16;
    }

    //move this coin
    public void move() {
        if (this.loc.x < 0) {
            this.loc.x = -15;
        }
        else {
            this.loc.x = this.loc.x - coinspeed;   
        }

    }

    //Image of this coin
    public WorldImage makeImage() {
        return new FromFileImage(this.loc, "coin.png");
    }

    //Does the given Mario get this coin?
    public boolean getCoin(Mario m) {
        return  Math.sqrt((this.loc.x - m.x) * (this.loc.x - m.x) + 
                (this.loc.y - m.y) * (this.loc.y - m.y)) <
                this.h / 2 + 10; 
    }

    //
    public Coin newCoin() {
        //the range of height that coins can appear
        int i = rand.nextInt(80) + 230;    
        return new Coin(new CartPt(width, i));
    }

}
