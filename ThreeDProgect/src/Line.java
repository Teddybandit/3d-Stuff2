import java.awt.*;
public class Line{
  private double slope;
  private double xIntercept;
  public Line(Point p1, Point p2){
    slope = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
    
  }
}