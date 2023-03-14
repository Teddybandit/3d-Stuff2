public class Vector {
    double x;
    double y;
    double z;
    public Vector (double inX, double inY, double inZ){
        x=inX;
        y=inY;
        z=inZ;
    }
    public Vector (ThreeDPoint p1, ThreeDPoint p2){
        x=p2.getX()-p1.getX();
        y=p2.getY()-p1.getY();
        z=p2.getZ()-p1.getZ();
    }
    public static Vector multiply(Vector v1, Vector v2){
           return new Vector(v1.y*v2.z - v1.z*v2.y, v1.z*v2.x - v1.x*v2.z, v1.x*v2.y - v1.y*v2.x);
    }
    //takes a point on the plane, the normal of the plane, and the line being checked
    //returns the point that the vector hits the plane if the vector starts at the origin
    public static ThreeDPoint whereplane(Vector normal,ThreeDPoint point, Vector line){
        double d = normal.x*point.getX()+normal.y*point.getY()+normal.z*point.getZ();// the d in ax+by+cz=d
        double t = d/(normal.x*line.x+normal.y*line.y+normal.z*line.z);
        return new ThreeDPoint(t*line.x,t*line.y,t*line.z);
    }
}
