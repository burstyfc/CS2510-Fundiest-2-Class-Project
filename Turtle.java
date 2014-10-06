/*
assignment 8
pair 2
Chao Fang
bursty
Amanda Fode
afode11
 */
public class Turtle extends Obstacles implements MarioConstants {
    Turtle(CartPt loc) {
        super(loc);
        this.h = 27;
        this.w = 20;
        this.img = "turtle.png";
        this.sp = spdt1;
        this.num = 3;
    }
    
    //is this obstacle collide the given mario?
    public boolean collide(CartPt that) {
        return  Math.sqrt((this.loc.x - that.x) * (this.loc.x - that.x) + 
                (this.loc.y - that.y) * (this.loc.y - that.y)) <
                this.h / 2 + 10;
    }
    //to make this obstacle move faster
    public void level2() {

        this.sp = spdt2;
    }
    //to make this obstacle move faster
    public void level3() {

        this.sp = spdt3;
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
