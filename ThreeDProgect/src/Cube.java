import java.awt.*;
public class Cube extends Mob{
    WireFrame frame;
    public Cube(){
      super(0,0,0);
      frame = new WireFrame("ThreeDProgect/src/CubeFrame.txt");
    }
    public void act() {

    }
    public boolean doDeleat(){
      return false;
    }
    public void display(Graphics g, Player p){
      frame.display(g,p,this);
    }
}
