public class Inequality extends Line{
    boolean facing;
    public Inequality(double s,MyPoint p, MyPoint i){
        super(s,p);
        if(isVerticle) {
            facing = verticleX>i.getX();
        }
        else{
            facing = i.getY()>slope*i.getX()+yIntercept;
        }
    }
    public boolean checkPoint(int x,int y){
        if(isVerticle)
            return facing == verticleX>x;
        else
        return facing == y>slope*x+yIntercept;
    }
}
