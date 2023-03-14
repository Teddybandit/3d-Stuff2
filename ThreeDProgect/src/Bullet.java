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