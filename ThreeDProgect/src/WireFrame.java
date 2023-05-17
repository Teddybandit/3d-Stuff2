import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class WireFrame {
    private ThreeDPoint[] points;
    private int[][] faces;
    private int[] RGB;
    private int faceNum;
    private int sideNum;

    public int getFaceNum() {
        return faceNum;
    }
    public int getPointNum(){
      return points.length;
    }
    public ThreeDPoint[] getPointsCopy(){
      ThreeDPoint[] pointsCopy = new ThreeDPoint[points.length];
      for(int i=0;i<pointsCopy.length;i++){
        pointsCopy[i] = new ThreeDPoint (points[i].getX(),points[i].getY(),points[i].getZ());
      }
      return pointsCopy;
    }
    public int getFacePoint(int face, int point) {
        return faces[face][point];
    }

    public int getRGB(int i) {
        return RGB[i];
    }
    public void setRGB (int i, int color){RGB[i] = color;}

    public WireFrame(String FileName) {
        Scanner scan = new Scanner("0\n0\n0");
        try {
            File file = new File(FileName);
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        scan.useDelimiter("\n");
        int num = Integer.decode(scan.next());//the number of points
        System.out.println("num - " + num);
        points = new ThreeDPoint[num];
        for (int i = 0; i < num; i++) {
            String sPoint = (scan.next());//a line in the file for a point
            Scanner scan2 = new Scanner(sPoint);
            scan2.useDelimiter(",");
            points[i] = new ThreeDPoint(//breaks the line into the x,y,z coordinates of the point
                    Integer.decode(scan2.next()),
                    Integer.decode(scan2.next()),
                    Integer.decode(scan2.next())
            );
        }
        faceNum = Integer.decode(scan.next());//the number of faces
        faces = new int[faceNum][3];
        RGB = new int[faceNum];
        for (int face = 0; face < faceNum; face++) {
            String sFace = scan.next();
            Scanner scan2 = new Scanner(sFace);
            scan2.useDelimiter(",");
            for (int i = 0; i < 3; i++) {
                faces[face][i] = Integer.decode(scan2.next());
            }
            RGB[face] = Integer.decode(scan2.next());//the hexidecimal representation of the color
        }
    }
}
