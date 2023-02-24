import java.awt.*;

public class Enemy extends Mob implements tangible{
    private int health = 5;
    public Enemy(int x,int y,int z){
        super(x,y,z);
    }
    @Override
    public boolean doHit(Bullet b){
        if(500<=Math.sqrt(Math.pow(b.getX()-x,2)+Math.pow(b.getZ()-z,2)+Math.pow(b.getY()-y,2))){
            return true;
        }
        return false;
    }
    @Override
    public void act(){

    }

    @Override
    public void display(Player player, Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(
                Mob.whereXLoad(this,player)-(int)(500/dist),
                Mob.whereYLoad(this,player)-(int)(500/dist),
                (int)(500/dist),
                (int)(500/dist)
        );
    }

    @Override
    public boolean doDeleat() {
        if(health<=0){
            return true;
        }
        return false;
    }
    @Override
    public void onHit(){
        health--;
    }
}
