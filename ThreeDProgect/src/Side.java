import java.awt.*;
import java.lang.Math;
public class Side{
  private Point center;
  private double radius;
  public Side(Point p1, Point p2, Point p3){
    Line Cord = new Line(p1,p2);
    Line Perpendicular = new Line(1/Cord.getSlope(),0);
    Point intersection = Line.intersect(Cord,Perpendicular);
    //the difference between the real midpoint and the point along the cord
    Point Shift = new Point(
            (int)(intersection.getX()-(p1.getX()+p2.getX())/2),
            (int)(intersection.getY()-(p1.getY()+p2.getY())/2)
    );

  }
}