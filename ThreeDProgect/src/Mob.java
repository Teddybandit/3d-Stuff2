import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
public abstract class Mob{
    //public final static double INV_ROOT_3 = ;
    protected double x,y,z;
    protected double dist;
    private static MyPanel panel;
    public Mob(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public void display(Player player,Graphics g,int x,int y){

    }
    public void refreshDistence(Player p){
        dist = Math.sqrt(Math.pow(p.getx()-x,2)+Math.pow(p.gety()-y,2));
    }
    public abstract void act();
    public static int whereXLoad(Mob mob, Player p) {
        return (int) (panel.getWidth()*4.5 - (Math.atan2(mob.y - p.gety(), mob.x - p.getx()) - p.getXFacing()) / Math.PI * panel.getWidth()*2)%(panel.getWidth()*4);
    }
    public static int whereXLoad(int x,int y, Player p) {
        return (int) (panel.getWidth()*4.5 - (Math.atan2(y - p.gety(), x - p.getx()) - p.getXFacing()) / Math.PI * panel.getWidth()*2)%(panel.getWidth()*4);
    }
    public static int whereYLoad(Mob mob, Player p){
        return (int)(panel.getHeight()/2-((Math.atan2(mob.z-p.getz(),mob.dist))-p.getYFacing())/Math.PI*panel.getHeight()*2);
    }
    public static int whereYLoad(int x,int y,int z, Player p){
        return (int)(panel.getHeight()/2-
                (Math.atan2(z-p.getz(),Mob.hypotenuse(x-p.getx(),y-p.gety()))-
                p.getYFacing())/Math.PI*panel.getHeight()*2);
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
    private static double hypotenuse(double a,double b){
        return(Math.sqrt(a*a+b*b));
    }
}