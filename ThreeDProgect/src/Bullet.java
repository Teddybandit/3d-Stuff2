import java.lang.Math;
import java.awt.*;

public class Bullet extends Mob{
    double xSpeed,ySpeed,zSpeed;
    public Bullet(double x,double y,double z, double xs, double ys, double zs){
        super(x,y,z);
        xSpeed = xs;
        ySpeed = ys;
        zSpeed = zs;
    }
    @Override
    public void display(Player player,Graphics g){
        if(dist!=0){
            g.setColor(Color.RED);
            g.fillOval(
                    Mob.whereXLoad(this,player)-(int)(1/5/dist),
                    Mob.whereYLoad(this,player)-(int)(1/5/dist),
                    (int)(1/10/dist),
                    (int)(1/10/dist)
            );
        }
    }
    @Override
    public void act(){
        x+=xSpeed;
        y+=ySpeed;
        z+=zSpeed;
    }
    public boolean doDeleat(){
        if(dist>1000||z<0){
            return true;
        }
        return false;
    }
}