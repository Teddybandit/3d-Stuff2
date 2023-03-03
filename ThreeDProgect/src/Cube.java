import java.awt.*;
public class Cube extends Mob{
    public Cube(int x,int y,int z){
      super(x,y,z);
      wireFrame = new WireFrame("ThreeDProgect/src/CubeFrame.txt");
    }
    public void act() {

    }
    public boolean doDeleat(){
      return false;
    }
    @Override
    public WireFrame getWireFrame(){
      return wireFrame;
    }

    @Override
    public String toString() {
        return "Cube";
    }
}
