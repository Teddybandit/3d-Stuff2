import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class WireFrame {
    private ThreeDPoint[] points;
    private ThreeDPoint[][] faces;
    private Color[] colors;
    private int faceNum;
    private int sideNum;
    public int getFaceNum(){
        return faceNum;
    }
    public ThreeDPoint[] getPolygon(int num){
        return faces[num];
    }
    public WireFrame(String FileName){
        Scanner scan = new Scanner("0\n0\n0");
        try {
            File file = new File(FileName);
            scan = new Scanner(file);
        }catch(Exception e){
            System.out.println(e);
        }
        scan.useDelimiter("\n");
        int num = Integer.decode(scan.next());
        System.out.println("num - "+num);
        points = new ThreeDPoint[num];
        for(int i =0;i<num;i++){
            String sPoint = (scan.next());
            Scanner scan2 = new Scanner(sPoint);
            scan2.useDelimiter(",");
            points[i] = new ThreeDPoint(
              Integer.decode(scan2.next()),
              Integer.decode(scan2.next()),
              Integer.decode(scan2.next())
            );
        }
        faceNum = Integer.decode(scan.next());
        faces = new ThreeDPoint[faceNum][3];
        colors = new Color[faceNum];
        for(int i=0;i<faceNum;i++){
            String sFace = scan.next();
            Scanner scan2 = new Scanner(sFace);
            scan2.useDelimiter(",");
            for(int i2=0;i2<3;i2++){
                faces[i][i2] = points[Integer.decode(scan2.next())];
            }
            int[] preColor = new int[3];
            for(int i2=0;i2<3;i2++){
              preColor[i2]=Integer.decode(scan2.next());
            }
            colors[i] = new Color(preColor[0],preColor[1],preColor[2]);
        }
}
