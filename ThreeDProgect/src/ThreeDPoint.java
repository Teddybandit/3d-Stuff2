

public class ThreeDPoint{
  private double x,y,z;
  public ThreeDPoint(double x,double y,double z){
    this.x=x;
    this.y=y;
    this.z=z;
  }
  public double getX(){
    return x;
  }
  public double getY(){
    return y;
  }
  public double getZ(){
    return z;
  }
  public void setX(double num){
    x=num;
  }
  public void setY(double num){
    y=num;
  }
  public void setZ(double num){
    z=num;
  }
  public String toString(){
    return ("["+x+","+y+","+z+"]");
  }
}
