import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class WireFrame {
    private int[][] points;
    private int[][] faces;
    private int faceNum;
    private int sideNum;
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
        points = new int[3][num];
        for(int i =0;i<num;i++){
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
                System.out.print(" "+faces[i2][i]);
            }
        }
}
    public void display(Graphics g, Player p, Mob m){
        int[] ys = new int[sideNum];
        int[] wraps = new int[sideNum];
        int[] xs = new int[sideNum];
        for(int i=0;i<faceNum;i++){
          boolean doDraw = true;
            for(int i2=0;i2<sideNum;i2++){
                xs[i2] = Mob.whereXLoad(
                  m.getX()+points[0][faces[i2][i]],
                  m.getY()+points[1][faces[i2][i]],
                  p
                );
              wraps[i2]=Mob.getWraps();
                ys[i2] = Mob.whereYLoad(
                  m.getX()+points[0][faces[i2][i]],
                  m.getY()+points[1][faces[i2][i]],
                  m.getZ()+points[2][faces[i2][i]],
                  p
                );
                if(i2>0 && wraps[i2]!=wraps[i2-1]){
                  doDraw = false;
                  break;
                }
            }
            g.setColor(Color.BLACK);
            if(i==3)
              g.fillPolygon(xs,ys,sideNum);
            else
              g.drawPolygon(xs,ys,sideNum);
            
        }
    }
}
