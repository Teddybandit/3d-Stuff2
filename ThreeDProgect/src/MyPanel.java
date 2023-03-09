import java.awt.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class MyPanel extends JPanel{
    private Player P;
    private ArrayList<Mob> mobs = new ArrayList<Mob>();
    private BufferedImage image;
    int[][] zBuffer;
    public MyPanel(Player player){
        P = player;
    }
    private int lineAt(Point p1,Point p2,int x){
      return (int)((Math.round((double)(p2.getX()-p1.getX())/(p2.getY()-p1.getY())))+p1.getX());
    }
    @Override
    public void paint(Graphics g){
        zBuffer = new int[getWidth()][getHeight()];//creates the zBuffer array to determine occlusion
        for(int i=0;i< zBuffer.length;i++){
            for(int i2=0;i2<zBuffer[0].length;i2++){
                zBuffer[i][i2] = Integer.MAX_VALUE;
            }
        }
        image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);//the image thet will be displayed at the end
        double cosX = Math.cos(P.getXFacing());
        double sinX = Math.sin(P.getXFacing());
        double cosY = Math.cos(P.getYFacing());
        double sinY = Math.sin(P.getYFacing());//are all calculated once, not for every point
        for(int i = 0;i<mobs.size();i++){//loops through every mob
            double mobX = mobs.get(i).getX()-P.getx();
            double mobY = mobs.get(i).getY()-P.gety();
            double mobZ = mobs.get(i).getZ()-P.getz();// the relative position of a mob the player
            for(int faceNum=0;faceNum<mobs.get(i).getWireFrame().getFaceNum();faceNum++){//loops through every face in the mob
              ThreeDPoint[] triangle = mobs.get(i).getWireFrame().getPolygon(faceNum);
              for(ThreeDPoint vertex:triangle){//Adjusts the placement of the points
                double x1 = vertex.getX()+mobX;//adjusts the point based on where the mob is
                double y1 = vertex.getY()+mobY;
                double z1 = vertex.getZ()+mobZ;
                double x2 = cosX*x1+sinX*y1;//rotate the points about the z axis
                vertex.setY(cosX*y1-sinX*x1);
                vertex.setZ(cosY*z1-sinY*x2);//rotates the points about the y axis
                vertex.setX(cosY*x2+sinY*z1);
              }
              Point[] screenTriangle = new Point[3];
              for(int i2=0;i2<3;i2++){//gives screenTriangle the location of the points on a 2d screen
                  screenTriangle[i2] = Mob.whereLoad(triangle[i2],P);
              }
                boolean cont = true;
                while(cont){//sorts the screen points by ascending xvalue
                    Point temp;
                    cont = false;
                    for(int i2=0;i2<2;i2++){
                        if(screenTriangle[i2].getX()<screenTriangle[i2+1].getX()){
                            temp = screenTriangle[i2];
                            screenTriangle[i2]=screenTriangle[i2+1];
                            screenTriangle[i2+1]=temp;
                            cont = true;
                        }
                    }
                }
                int direction = -1;
                if(LineAt(screenTriangle[0],screenTriangle[2],screenTriangle[1].getX())>screenTriangle[1].getY){
                  direction = 1;
                }
                for(int x=(int)screenTriangle[0].getX();x<screenTriangle[2].getX();x++){
                    for(int y=lineAt(screenTriangle[0],))
                }
            }
            mobs.get(i).act();
            if(mobs.get(i).doDeleat()){
                mobs.remove(i);
            }
            if(mobs.get(i) instanceof Bullet){
                for(Mob mob:mobs){
                    if(mob!=mobs.get(i)&&(mob instanceof tangible)&&((tangible)mob).doHit((Bullet)mobs.get(i))){
                        ((tangible)mob).onHit();
                        mobs.remove(i);
                        i--;
                    }
                }
            }
        }
    }
    public void addMob(Mob m){
        mobs.add(m);
    }
}