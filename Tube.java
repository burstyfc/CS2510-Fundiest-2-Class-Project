/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
public class Tube extends Obstacles implements MarioConstants {
    Tube(CartPt loc) {
        super(loc);
        this.h = 40;
        this.w = 20;
        this.img = "tube.png";
        this.sp = spd1;
        this.num = 2;

    }
    //is this obstacle collide the given mario?
    public boolean collide(CartPt that) {
        int xdist = Math.abs(this.loc.x - that.x);
        int ydist = Math.abs(this.loc.y - that.y);
        if (xdist < this.w / 2 + 10) {
            return ydist < this.h / 2 + 13;
        }
        if (ydist < this.h / 2 + 12) {
            return xdist < this.w / 2 + 10;
        }
        else {
            return false;
        }
    }
    
    //to make this obstacle move faster
    public void level2() {

        this.sp = spd2;
    }
    //to make this obstacle move faster
    public void level3() {

        this.sp = spd3;
    }

    //to move this obstacle
    public void move() {
        if (this.loc.x < 0) {
            this.loc.x = -15;

        }
        else {
            this.loc.x = this.loc.x - sp;
        }
    }
        
    public void bossComing() {
        
    };
        
}
