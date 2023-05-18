import java.awt.*;
public class Floor extends Mob{
    public Floor(int x,int y,int z, int color){
      super(x,y,z);
      wireFrame = new WireFrame("ThreeDProgect/src/floor.txt");
      wireFrame.setRGB(0,color);
      //wireFrame.setRGB(1,color);
    }
    public void act() {

    }
    public boolean doDeleat(){
      return false;
    }
    @Override
    public String toString() {
        return "Cube";
    }
}
