import java.awt.*;
import java.lang.Math;
public abstract class Mob{
    protected int x;
    protected int y;
    public Mob(int x,int y){
        this.x=x;
        this.y=y;
    }
    public abstract void display(Player player,Graphics g);
    public abstract void act();
    public static int whereLoad(Mob mob, Player p){
        return (int)(2250-(Math.atan2(mob.y-p.gety(),mob.x-p.getx())-p.getFacing())/Math.PI*1000)%2000;
    }
}