import java.awt.*;
public class Cube extends Mob{
    WireFrame frame;
    public Cube(int x,int y,int z){
      super(x,y,z);
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
