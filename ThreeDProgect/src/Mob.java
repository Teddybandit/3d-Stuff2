import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
public abstract class Mob{
    //public final static double INV_ROOT_3 = ;
    protected double x,y,z;
    protected double dist;
    protected static MyPanel panel;
    public Mob(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public void display(Player player,Graphics g){

    }
    public void refreshDistence(Player p){
        dist = Math.sqrt(Math.pow(p.getx()-x,2)+Math.pow(p.gety()-y,2));
    }
    public abstract void act();
    public static int whereXLoad(Mob mob, Player p) {
        return (int) ((panel.getWidth()*2.5 - (Math.atan2(mob.y - p.gety(), mob.x - p.getx()) - p.getXFacing()) / Math.PI * panel.getWidth()*2)%(panel.getWidth()*4))-panel.getWidth()*2;
    }
    public static int whereYLoad(Mob mob, Player p){
        return (int)(panel.getHeight()/2-((Math.atan2(mob.z-p.getz(),mob.dist))-p.getYFacing())/Math.PI*panel.getHeight()*2);
    }
    public static Point whereLoad(double x,double y,double z, Player p){
        double x1 = x-p.getx();
        double y1 = y-p.gety();
        double z1 = z-p.getz();
        double x2 = Math.cos(p.getXFacing())*x1+
          Math.sin(p.getXFacing())*y1;
        double x3 = Math.cos(p.getYFacing())*x2+
          Math.sin(p.getYFacing())*z1;
        double y2 = Math.cos(p.getXFacing())*y1-
          Math.sin(p.getXFacing())*x1;
        double z2 = Math.cos(p.getYFacing())*z1-
          Math.sin(p.getYFacing())*x2;
        return new Point(
          (int)((panel.getWidth()*2.5 - (Math.atan2(y2,x3))*panel.getWidth()*2)%(panel.getWidth()*4)-panel.getWidth()*2),
          (int)(panel.getHeight()/2-(Math.atan2(z2,Mob.hypotenuse(x3,y2))*panel.getHeight()*2))
        );
    }
    public static void sortDist(ArrayList<Mob> mobs){//uses bubble sort because the array will be nearly sorted
        boolean cont = true;
        Mob temp;
        while(cont){
            cont = false;
            for(int i=0;i<mobs.size()-1;i++){
                if(mobs.get(i).dist<mobs.get(i+1).dist){
                    temp = mobs.set(i,mobs.get(i+1));
                    mobs.set(i+1,temp);
                    cont = true;
                }
            }
        }
    }
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