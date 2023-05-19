

import java.awt.*;
public class Cube extends Mob{
    private double health = 50;
    private double wait;
    public Cube(int x,int y,int z){
        super(x,y,z);
        wireFrame = new WireFrame("ThreeDProgect/src/CubeFrame.txt");
    }
    public void act() {
        if (wait==0) {
            x += Math.cos(facing);
            y += Math.sin(facing);
            if ((Math.atan2(Player.getY() - y, Player.getX() - x) - facing +
                    Math.PI * 2) % (Math.PI * 2) < Math.PI) {
                facing += Math.PI / 100;
            } else {
                facing -= Math.PI / 100;
            }
            facing = facing % (Math.PI * 2);
            if (hypotenuse(Player.getY() - y, Player.getX() - x)<20){
                wait = 20;
                wireFrame.setRGB(6,Color.RED.getRGB());
                wireFrame.setRGB(7,Color.RED.getRGB());
            }
        }else if(wait == 1){
            if(hypotenuse(Player.getY() - y, Player.getX() - x)<30) {
                Player.doDamage(3);
            }
            wireFrame.setRGB(6,-14336);
            wireFrame.setRGB(7,-14336);
            wait--;
        }else wait=Math.max(0,wait-1);
    }
    public boolean doDeleat(){
        return(health<=0);
    }
    public void hit(){
        super.hit();
        health--;
    }
    @Override
    public String toString() {
        return "Cube";
    }
}
