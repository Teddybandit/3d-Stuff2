import java.awt.*;
import java.lang.Math;
public class Side{
  private Point center;
  private double sqrradius;
  private boolean facing;//true if the side needs to be filled on the inside of the circle
  public Side(Point p1, Point p2, Point p3){
    Line cord = new Line(p1,p2);
    Line perpendicular = new Line(1/cord.getSlope(),0);
    Point intersection = Line.intersect(cord,perpendicular);
    //the difference between the real midpoint and the point along the cord
    Point shift = new Point(
            (int)((p1.getX()+p2.getX())/2-intersection.getX()),
            (int)((p1.getY()+p2.getY())/2-intersection.getY())
    );
    //the distance between shift and either of the points
    Double dist = Math.sqrt(Math.pow(shift.getX()-p1.getX(),2)+Math.pow(shift.getY()-p1.getY(),2));
    //the arc between the two points on the circle
    double angle = Math.atan2(p1.getY()-shift.getY(),p1.getX()-shift.getX())-
            Math.atan2(p2.getY()-shift.getY(),p2.getX()-shift.getY());
    //the distance from shift that the circle will be drawn
    Double finalDist = Math.tan(-1*(dist/1000*Math.PI)+Math.PI/2)*Math.cos(angle/2);
    double perpAngle = Math.atan2(shift.getX()-intersection.getX(),shift.getY()-intersection.getY());
    center = new Point((int)(shift.getX()+finalDist*Math.cos(perpAngle)),
            (int)(shift.getY()+finalDist*Math.sin(perpAngle))
            );
    sqrradius = Math.pow(center.getX()-p1.getX(),2);
    facing = (Math.pow(p3.getX()-center.getX(),2)+Math.pow(p3.getY()-center.getX(),2))<sqrradius;
  }
  public boolean isInside(int x,int y){
    return ((Math.pow(x-center.getX(),2)+Math.pow(y-center.getX(),2))<sqrradius)==facing;
  }
  public static boolean insideShape(Side[] sides,int x,int y){
    return sides[0].isInside(x,y)&&sides[1].isInside(x,y)&&sides[2].isInside(x,y);
  }
  public static Side[] makeTriangle(Point[] points){
    Side[] triangle = new Side[3];
    for(int i=0;i<3;i++){
      triangle[i] = new Side(points[i],points[(i+1)%3],points[(i+2)%3]);
    }
    return triangle;
  }
}