import java.awt.*;
public class Line{
  private double slope;
  private double xIntercept;
  public Line(Point p1, Point p2){
    slope = (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
    xIntercept = p1.getY()-p1.getX()*slope;
  }
  public Line (double s,double intercept){
    slope = s;
    xIntercept = intercept;
  }
  public Line(double s,Point p){
    slope = s;
    xIntercept = p.getY()-p.getX()*slope;
  }
  //returns the Y with the X
  public double solveY(double x){
    return xIntercept+(slope)*x;
  }
  public double solveX(double y){
    return (y-xIntercept)/slope;
  }
  public double getSlope(){
    return slope;
  }
  public static Point intersect(Line l1, Line l2){
    if(l1.slope==l2.slope)
      return new Point(0,0);
    double x = (l1.xIntercept-l2.xIntercept)/(l2.slope-l1.slope);
    return new Point((int)x,(int)l1.solveY(x));
  }
  public void draw(Graphics g){
    g.setColor(Color.BLACK);
    g.drawLine(-500,(int)solveY(-500),500,(int)solveY(500));
  }
}