import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class MyPanel extends JPanel{
    private Player P;
    private ArrayList<Mob> mobs = new ArrayList<Mob>();
    private BufferedImage image;
    private double[][] zBuffer;
    private boolean seeZBuffer = false;
    public MyPanel(Player player){
        P = player;
    }
    private int lineAt(Point p1,Point p2,int x){
        if(p1.getX()==p2.getX())
            return (int)(p1.getY()+p2.getY())/2;
        if(p1.getY()==p2.getY())
            return (int)p1.getY();
      return (int)((p2.getY()-p1.getY())/(p2.getX()-p1.getX())*(x-p1.getX())+p1.getY());
    }
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        zBuffer = new double[getWidth()][getHeight()];//creates the zBuffer array to determine occlusion
        image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);//the image that will be displayed at the end
        Side.setGraphics(image.getGraphics());
        for(int x=0;x< zBuffer.length;x++){
            for(int y=0;y<zBuffer[0].length;y++){
                zBuffer[x][y] = Integer.MAX_VALUE;
                image.setRGB(x,y,new Color(64,64,128).getRGB());
            }
        }
        //System.out.println("XFacing - "+P.getXFacing()/Math.PI+"pi\nYFacing - "+P.getYFacing()/Math.PI+"pi");
        double cosX = Math.cos(P.getXFacing());
        double sinX = Math.sin(P.getXFacing());
        double cosY = Math.cos(P.getYFacing());
        double sinY = Math.sin(P.getYFacing());
        double[] cosXs = new double[getWidth()];
        double[] sinXs = new double[getWidth()];
        for(int i=0;i<getWidth();i++){
              cosXs[i] = Math.cos(P.getXFacing()+((getWidth()*-.5+i)/getWidth())*Math.PI/2);
              sinXs[i] = Math.sin(P.getXFacing()+((getWidth()*-.5+i)/getWidth())*Math.PI/2);
            }
        double[] cosYs = new double[getHeight()];
        double[] sinYs = new double[getHeight()];
        for(int i=0;i<getHeight();i++){
            cosYs[i] = Math.cos(P.getYFacing()+((getHeight()*-.5+i)/getHeight())*Math.PI/2);
            sinYs[i] = Math.sin(P.getYFacing()+((getHeight()*-.5+i)/getHeight())*Math.PI/2);
        }
                //are all calculated once, not for every point
        //System.out.println("cosX - "+cosX+"\ncosY - "+cosY+"\nsinX - "+sinX+"\nsinY - "+sinY+"\n");
        //System.out.println("mobs - "+mobs.size());
        for(int mob = 0;mob<mobs.size();mob++){//loops through every mob
            double mobX = mobs.get(mob).getX()-P.getx();
            double mobY = mobs.get(mob).getY()-P.gety();
            double mobZ = mobs.get(mob).getZ()-P.getz();// the relative position of a mob the player
            ThreeDPoint[] points = mobs.get(mob).getWireFrame().getPointsCopy();
            for(ThreeDPoint vertex : points){//loops through all the points
                //System.out.println(vertex);
                double x1 = vertex.getX()+mobX;//adjusts the point based on where the mob is
                double y1 = vertex.getY()+mobY;
                double z1 = vertex.getZ()+mobZ;
                //System.out.println("["+x1+","+y1+","+z1+"]");
                double x2 = cosX*x1+sinX*y1;//rotate the points about the z axis
                vertex.setY(cosX*y1-sinX*x1);
                vertex.setZ(cosY*z1-sinY*x2);//rotates the points about the y axis
                vertex.setX(cosY*x2+sinY*z1);
                Point p = Mob.whereLoad(vertex);
                image.getGraphics().setColor(Color.BLACK);
                image.getGraphics().fillOval((int)p.getX()-5,(int)p.getY()-5,10,10);
            }
            for(int faceNum=0;faceNum<mobs.get(mob).getWireFrame().getFaceNum();faceNum++){//loops through every face in the mob
              ThreeDPoint[] triangle = {
                points[mobs.get(mob).getWireFrame().getFacePoint(faceNum,0)],
                points[mobs.get(mob).getWireFrame().getFacePoint(faceNum,1)],
                points[mobs.get(mob).getWireFrame().getFacePoint(faceNum,2)]
              };
              Point[] screenTriangle = new Point[3];
              for(int i=0;i<3;i++){//gives screenTriangle the location of the points on a 2d screen
                  screenTriangle[i] = Mob.whereLoad(triangle[i]);
                  //System.out.println(""+screenTriangle[i].toString());
              }
                
                Vector normal = Vector.multiply(
                  new Vector(triangle[1],triangle[0]),
                  new Vector(triangle[2],triangle[0]));
              Side[] sides = Side.makeTriangle(screenTriangle);
                //System.out.println(normal);

                for(int x=0;x<getWidth();x++){
                    for(int y=0;y<getHeight();y++){
                        if(Side.insideShape(sides,x,y)) {
                            try {
                                double dist = Vector.planeDist(
                                        normal,
                                        triangle[0],
                                        new Vector(
                                                cosYs[y] * cosXs[x],
                                                cosYs[y] * sinXs[x],
                                                sinYs[y]
                                        )
                                );
                                //System.out.println(dist);
                                if (dist > 0) {
                                    if (true || zBuffer[x][y] > dist) {
                                        zBuffer[x][y] = dist;
                                        image.setRGB(x, y, mobs.get(mob).getWireFrame().getRGB(faceNum));
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                                System.out.println("" + screenTriangle[0] + screenTriangle[1] + screenTriangle[2] + "\n" + x + " " + y);
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
        if(seeZBuffer){
          for(int x=0;x< zBuffer.length;x++){
              for(int y=0;y<zBuffer[0].length;y++){
                if(zBuffer[x][y] >= 255)
                      image.setRGB(x,y,Color.WHITE.getRGB());
                else
                  image.setRGB(x,y,new Color((int)zBuffer[x][y],(int)zBuffer[x][y],(int)zBuffer[x][y]).getRGB());
              }
          }
        }
        image.getGraphics().drawString(""+P.getXFacing(),0,0);
        image.getGraphics().drawString(""+P.getYFacing(),0,0);
        g.drawImage(image,0,0,null);
    }
    public void addMob(Mob m){
        mobs.add(m);
    }
    public void zBufferOff(){
      seeZBuffer = false;
    }
    public void zBufferOn(){
      seeZBuffer = true;
    }
}