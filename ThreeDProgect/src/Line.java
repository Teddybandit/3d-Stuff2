import java.awt.*;
public class Line{
  private double slope;
  private double xIntercept;
  private double verticleX = 0;
  private boolean isVerticle = false;
  public Line(MyPoint p1, MyPoint p2){
    if(p1.getX()==p2.getX()){
      verticleX = p1.getX();
      isVerticle = true;
    }else {
      slope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
      xIntercept = p1.getY() - p1.getX() * slope;
    }
  }
  public Line (double s,double intercept){
    slope = s;
    xIntercept = intercept;
  }
  public Line(double s,MyPoint p){
    slope = s;
    if(s == Integer.MAX_VALUE){
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
      return new MyPoint(l2.solveY(l1.verticleX),l1.verticleX);
    }
    if(l2.isVerticle){
      return new MyPoint(l1.solveY(l2.verticleX),l2.verticleX);
    }
    if(l1.slope==l2.slope) {
      return new MyPoint(0, 0);
    }
    double x = (l1.xIntercept-l2.xIntercept)/(l2.slope-l1.slope);
    return new MyPoint(x,l1.solveY(x));
  }
  public void draw(Graphics g){
    g.setColor(Color.BLACK);
    g.drawLine(-500,(int)solveY(-500),500,(int)solveY(500));
  }
  public Line pendicular(MyPoint p){
    if(slope == 0){
      return new Line(Integer.MAX_VALUE,p.getX());
    }else{
      return new Line(-1/slope,p);
    }
  }
}