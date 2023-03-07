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
        for(int i = 0;i<mobs.size();i++){
            double mobX = mobs.get(i).getX()-P.getx();
            double mobY = mobs.get(i).getY()-P.gety();
            double mobZ = mobs.get(i).getZ()-P.getz();// the relative position of a mob the player
            for(int faceNum=0;faceNum<mobs.get(i).getWireFrame().getFaceNum();faceNum++){
              ThreeDPoint[] triangle = mobs.get(i).getWireFrame().getPolygon(faceNum);
              for(ThreeDPoint vertex:triangle){//Adjusts the placement of the points
                x1 = vertex.getX+mobX;//adjusts the point based on where the mob is
                y1 = vertex.getY+mobY;
                z1 = vertex.getZ+mobZ;
                double x2 = cosX*x1+sinX*y1;//rotate the points about the z axis
                double y2 = cosX*y1-xinX*x1;
                double z2 = cosY*z1-sinY*x2;
                double x3 = cosY*x2+sinY*z1;
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