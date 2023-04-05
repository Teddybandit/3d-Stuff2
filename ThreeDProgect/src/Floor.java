import java.awt.*;
public class Floor extends Mob{
    public Floor(int x,int y,int z){
      super(x,y,z);
      wireFrame = new WireFrame("ThreeDProgect/src/floor.txt");
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
