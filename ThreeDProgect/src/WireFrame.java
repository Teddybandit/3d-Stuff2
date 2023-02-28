import java.awt.*;
import java.util.Scanner;
import java.io.*;
public class WireFrame {
    private int[][] points;
    private int[][] faces;
    private Color[] colors;
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
        colors = new Color[faceNum];
        for(int i=0;i<faceNum;i++){
            String sFace = scan.next();
            Scanner scan2 = new Scanner(sFace);
            scan2.useDelimiter(",");
            for(int i2=0;i2<sideNum;i2++){
                faces[i2][i] = Integer.decode(scan2.next());
                System.out.print(" "+faces[i2][i]);
            }
            int[] preColor = new int[3];
            for(int i2=0;i2<3;i2++){
              preColor[i2]=Integer.decode(scan2.next());
            }
            colors[i] = new Color(preColor[0],preColor[1],preColor[2]);
        }
}
    public void display(Graphics g, Player p, Mob m, MyPanel panel){
        Point point;
        int[] xs = new int[sideNum];
        int[] ys = new int[sideNum];
        boolean doDraw = true;
        for(int i=0;i<faceNum;i++){
            doDraw = true;
            for(int i2=0;i2<sideNum;i2++){
                point = Mob.whereLoad(
                    m.getX()+points[0][faces[i2][i]],
                    m.getY()+points[1][faces[i2][i]],
                    m.getZ()+points[2][faces[i2][i]],
                    p
                );
                xs[i2] = (int)point.getX();
                ys[i2] = (int)point.getY();
            }
            for(int i2=0;i2<sideNum;i2++){
              if(m.getPanel().getWidth()*2>Math.abs(xs[i2]-xs[(i2+1)%sideNum])){
                doDraw = false;
              }
            }
            g.setColor(colors[i]);
            if(true){
                g.fillPolygon(xs,ys,sideNum);

            }
        }
    }
}
