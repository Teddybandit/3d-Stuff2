import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
public abstract class Mob{
    //public final static double INV_ROOT_3 = ;
    protected double x,y,z;
    protected WireFrame wireFrame;
    protected double dist;
    protected static MyPanel panel;
    public Mob(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public static MyPoint whereLoad(ThreeDPoint point){
        double r = hypotenuse(point.getZ(),point.getY());
        double theta = Math.atan2(r,point.getX())/panel.getFOV();
        return new MyPoint(
          (int)(panel.getWidth()*(.5-(theta*point.getY()/r))),
          (int)(panel.getWidth()*(-(theta*point.getZ()/r))+panel.getHeight()*.5)
        );
    }
    public abstract void act();
    public abstract boolean doDeleat();
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public WireFrame getWireFrame(){return wireFrame;}
    public static void setPanel(MyPanel pan){
        panel = pan;
    }
    public static MyPanel getPanel(){
      return panel;
    }
    private static double hypotenuse(double a,double b){
        return(Math.sqrt(a*a+b*b));
    }
}