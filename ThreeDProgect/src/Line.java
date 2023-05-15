import java.awt.*;
public class Line{
  private double slope;
  private double xIntercept;
  private double verticleX = 0;
  private boolean isVerticle = false;
  //point point
  public Line(MyPoint p1, MyPoint p2){
    slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    if(p1.getX()==p2.getX()){
      verticleX = p1.getX();
      isVerticle = true;
    }else {
      xIntercept = p1.getY() - p1.getX() * slope;
    }
  }
  //slope intercept
  public Line (double s,double intercept){
    slope = s;
    xIntercept = intercept;
  }
  //point slope
  public Line(double s,MyPoint p){
    slope = s;
    if(s == Double.POSITIVE_INFINITY||s == Double.NEGATIVE_INFINITY){
      isVerticle = true;
      verticleX = p.getX();
    }else {
      xIntercept = p.getY() - p.getX() * slope;
    }
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
  public static MyPoint intersect(Line l1, Line l2){
    if(l1.isVerticle){
      return new MyPoint(l1.verticleX,l2.solveY(l1.verticleX));
    }
    if(l2.isVerticle){
      return new MyPoint(l2.verticleX,l1.solveY(l2.verticleX));
    }
    if(l1.slope==l2.slope) {
      return new MyPoint(0, 0);
    }
    double x = (l1.xIntercept-l2.xIntercept)/(l2.slope-l1.slope);
    return new MyPoint(x,l1.solveY(x));
  }
  public void draw(Graphics g){
    g.setColor(Color.BLACK);
    if(isVerticle)
      g.drawLine((int)verticleX,-500,(int)verticleX,500);
    else
      g.drawLine(-500,(int)solveY(-500),1500,(int)solveY(1500));
  }
  public Line pendicular(MyPoint p){
      return new Line(-1/slope,p);
  }
  public String toString(){
    if (isVerticle){
      return "x = "+verticleX;
    }
    if (slope == 0){
      return "y = "+xIntercept;
    }
    return "y = "+slope+"x + "+xIntercept;
  }
}