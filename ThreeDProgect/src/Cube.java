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
    @Override
    public void display(Player player,Graphics g){
      frame.display(g,player,this, panel);
    }

    @Override
    public String toString() {
        return "Cube";
    }
}
