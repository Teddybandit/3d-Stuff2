public class MyPoint {
    double x;
    double y;
    public MyPoint(double setx,double sety){
        x=setx;
        y=sety;
    };
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double setx){
        x=setx;
    }
    public void setY(double sety){
        y=sety;
    }
    public String toString(){
        return "{"+x+","+y+"}";
    }
}
