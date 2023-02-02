import java.lang.Math;
import java.awt.*;
import java.sql.SQLOutput;

public class Bullet extends Mob{
    double xSpeed;
    double ySpeed;
    public Bullet(int x,int y, int xs, int ys){
        super(x,y);
        xSpeed = xs;
        ySpeed = ys;
    }
    @Override
    public void display(Player player,Graphics g){
        double dist = Math.sqrt(Math.pow(player.getx()-x,2)+Math.pow(player.gety()-y,2));
        if(dist!=0){
            g.setColor(Color.RED);
            g.fillOval(Mob.whereXLoad(this,player)-(int)(50/dist),
                    250-(int)(50/dist-player.getYFacing()/Math.PI*1000),
                    (int)(100/dist),
                    (int)(100/dist));
        }
    }
    @Override
    public void act(){
        x+=xSpeed;
        y+=ySpeed;
    }
}