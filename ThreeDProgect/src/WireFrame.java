import java.awt.Color;
import java.util.Scanner;
import java.io.*;
public class WireFrame {
    private int[][] points;
    private int[][] faces
    public WireFrame(String FileName){
        try {
            File file = new File(FileName);
            Scanner scan = new Scanner(file);
            scan.useDelimiter("\n");
            String sPoint;
            int pointNum = Integer.decode(scan.next());
            points = new int[3][pointNum];
            for(int i =0;i<pointNum;i++){
                sPoint = (scan.next());
                Scanner scan2
            }
        }catch(Exception e){}
    }
}
