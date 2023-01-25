import java.awt.*;
import java.lang.Math;
public class Mob{
    protected int x;
    protected int y;
    public Mob(int x,int y){
        x=this.x;
        y=this.y;
    }
    public void display(Player player,Graphics g){

    }
    public void act(){

    }
    public static int whereLoad(Mob mob, Player p){
        return (int)((Math.atan2(mob.y-p.gety(),mob.x-p.getx())-p.getFacing()+Math.PI/4)/Math.PI*250);
    }
}