import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class MyPanel extends JPanel{
    private Player P;
    private ArrayList<Mob> mobs = new ArrayList<Mob>();
    private BufferedImage image;
    double[][] zBuffer;
    public MyPanel(Player player){
        P = player;
    }
    private int lineAt(Point p1,Point p2,int x){
      return (int)((Math.round((double)(p2.getX()-p1.getX())/(p2.getY()-p1.getY())))+p1.getX());
    }
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        zBuffer = new double[getWidth()][getHeight()];//creates the zBuffer array to determine occlusion
        image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);//the image that will be displayed at the end
        for(int x=0;x< zBuffer.length;x++){
            for(int y=0;y<zBuffer[0].length;y++){
                zBuffer[x][y] = Integer.MAX_VALUE;
                image.setRGB(x,y,256*256*128);
            }
        }
        double cosX = Math.cos(P.getXFacing());
        double sinX = Math.sin(P.getXFacing());
        double cosY = Math.cos(P.getYFacing());
        double sinY = Math.sin(P.getYFacing());//are all calculated once, not for every point
        for(int mob = 0;mob<mobs.size();mob++){//loops through every mob
            double mobX = mobs.get(mob).getX()-P.getx();
            double mobY = mobs.get(mob).getY()-P.gety();
            double mobZ = mobs.get(mob).getZ()-P.getz();// the relative position of a mob the player
            for(int faceNum=0;faceNum<mobs.get(mob).getWireFrame().getFaceNum();faceNum++){//loops through every face in the mob
              ThreeDPoint[] triangle = mobs.get(mob).getWireFrame().getPolygon(faceNum);
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
              for(int i=0;i<3;i++){//gives screenTriangle the location of the points on a 2d screen
                  screenTriangle[i] = Mob.whereLoad(triangle[i],P);
              }
                boolean cont = true;
                while(cont){//sorts the screen points by ascending x value
                    Point temp;
                    cont = false;
                    for(int i=0;i<2;i++){
                        if(screenTriangle[i].getX()<screenTriangle[i+1].getX()){
                            temp = screenTriangle[i];
                            screenTriangle[i]=screenTriangle[i+1];
                            screenTriangle[i+1]=temp;
                            cont = true;
                        }
                    }
                }
                int direction = -1;//whether the triangle is drawn from top to bottom, or bottom to top
                if(lineAt(screenTriangle[0],screenTriangle[2],(int)screenTriangle[1].getX())>screenTriangle[1].getY()){
                  direction = 1;
                }

                for(int x=(int)screenTriangle[0].getX();x<screenTriangle[2].getX();x++){//loops through every pixel that needs to be displayed
                    int end;
                    if(screenTriangle[1].getX()>x)
                        end = lineAt(screenTriangle[0],screenTriangle[1],x);
                    else
                        end = lineAt(screenTriangle[1],screenTriangle[2],x);
                    for(int y=lineAt(screenTriangle[0],screenTriangle[2],x);y-direction!=end;y+=direction){
                        ThreeDPoint planePoint = Vector.whereplane(
                                Vector.multiply(
                                        new Vector(triangle[1],triangle[0]),
                                        new Vector(triangle[2],triangle[0])),
                                triangle[0],
                                new Vector(
                                        cosY*cosX,
                                        cosY*sinX,
                                        cosY
                                )
                        );
                        if(planePoint.getX()>0) {
                            Double distance = Math.pow(planePoint.getX(),2) * Math.pow(planePoint.getY(),2) * Math.pow(planePoint.getZ(),2);
                            if (zBuffer[x][y]<distance){
                                zBuffer[x][y]=distance;
                                image.setRGB(x,y,mobs.get(mob).getWireFrame().getRGB(faceNum));
                            }
                        }
                    }
                }
            }
            mobs.get(mob).act();
            if(mobs.get(mob).doDeleat()){
                mobs.remove(mob);
            }

        }
        image.setRGB(150,150,Color.RED.getRGB());
        image.getGraphics().setColor(Color.BLACK);
        image.getGraphics().drawRect(100,100,100,100);
        g.drawImage(image,0,0,null);
    }
    public void addMob(Mob m){
        mobs.add(m);
    }
}