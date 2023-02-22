import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class WireFrame {
    private int[][] points;
    private int[][] faces;
    private int faceNum;
    private int sideNum;
    public WireFrame(String FileName){
        try {
            File file = new File(FileName);
            Scanner scan = new Scanner(file);
            scan.useDelimiter("\n");
            int Num = Integer.decode(scan.next());
            points = new int[3][Num];
            for(int i =0;i<Num;i++){
                String sPoint = (scan.next());
                Scanner scan2 = new Scanner(sPoint);
                scan2.useDelimiter(",");
                for(int i2=0;i2<3;i2++){
                    points[i2][i] = Integer.decode(scan2.next());
                }
            }
            faceNum = Integer.decode(scan.next());
            sideNum = Integer.decode(scan.next());
            faces = new int[sideNum][faceNum];
            for(int i=0;i<faceNum;i++){
                String sFace = scan.next();
                Scanner scan2 = new Scanner(sFace);
                scan2.useDelimiter(",");
                for(int i2=0;i2<sideNum;i2++){
                    faces[i2][i] = Integer.decode(scan2.next());
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void display(WireFrame frame, Graphics g, Player p, Mob m){
        int[] ys = new int[frame.sideNum];
        int[] xs = new int[frame.sideNum];
        for(int i=0;i<frame.faceNum;i++){
            for(int i2=0;i2<frame.sideNum;i++){
                xs[i2] =
            }
            g.drawPolygon(

            );
        }
    }
}
