import java.lang.Math;
import java.awt.*;
public class Bullet extends Mob{
    double xSpeed = 0;
    double ySpeed = -1;
    public Bullet(int x,int y){
        super(x,y);
    }
    @Override
    public void display(Player player,Graphics g){
        double dist = Math.sqrt(Math.pow(player.getx()-x,2)+Math.pow(player.gety()-y,2));
        if(dist!=0){
            g.setColor(Color.RED);
            g.fillOval(Mob.whereLoad(this,player),250,(int)(100/dist),(int)(100/dist));
        }
    }
    @Override
    public void act(){
        x+=xSpeed;
        if(y>50)
            ySpeed=Math.abs(ySpeed)*-1;
        y+=ySpeed;
    }
}