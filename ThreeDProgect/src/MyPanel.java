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
    private double FOV = .5*Math.PI;
    private int canShoot = 0;
    public MyPanel(Player player){
        P = player;
    }
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        zBuffer = new double[getWidth()][getHeight()];//creates the zBuffer array
                image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);//the image that
        for(int x=0;x< zBuffer.length;x++){
            for(int y=0;y<zBuffer[0].length;y++){
                zBuffer[x][y] = Integer.MAX_VALUE;
                image.setRGB(x,y,new Color(64,64,128).getRGB());
            }
        }
//System.out.println("XFacing - "+P.getXFacing()/Math.PI+"pi\nYFacing -
        double cosX = Math.cos(P.getXFacing());
        double sinX = Math.sin(P.getXFacing());
        double cosY = Math.cos(P.getYFacing());
        double sinY = Math.sin(P.getYFacing());
        double[] cosXs = new double[getWidth()];
        double[] sinXs = new double[getWidth()];
        for(int i=0;i<getWidth();i++){
            cosXs[i] = Math.cos(((getWidth()*.5-i)/getWidth())*FOV);
            sinXs[i] = Math.sin(((getWidth()*.5-i)/getWidth())*FOV);
        }
        double[] cosYs = new double[getHeight()];
        double[] sinYs = new double[getHeight()];
        for(int i=0;i<getHeight();i++){
            cosYs[i] = Math.cos(((getHeight()*-.5+i)/getWidth())*FOV);
            sinYs[i] = Math.sin(((getHeight()*-.5+i)/getWidth())*FOV);
        }
//are all calculated once, not for every point
//System.out.println("cosX - "+cosX+"\ncosY - "+cosY+"\nsinX - "+sinX+"\
//System.out.println("mobs - "+mobs.size());
        for(int mob = 0;mob<mobs.size();mob++){//loops through every mob
            double mobX = mobs.get(mob).getX()-P.getx();
            double mobY = mobs.get(mob).getY()-P.gety();
            double mobZ = mobs.get(mob).getZ()-P.getz();// the relative position of
            ThreeDPoint[] points = mobs.get(mob).getWireFrame().getPointsCopy();
            double mobcos = Math.cos(-1*mobs.get(mob).getFacing());
            double mobsin = Math.sin(-1*mobs.get(mob).getFacing());
            for(ThreeDPoint vertex : points){//loops through all the points
                double x3 = vertex.getX()*mobcos+vertex.getY()*mobsin;
                double y2 = vertex.getY()*mobcos-vertex.getX()*mobsin;
                double x1 = x3+mobX;//adjusts the point based on where the mob is
                double y1 = y2+mobY;
                double z1 = vertex.getZ()+mobZ;
//System.out.println("["+x1+","+y1+","+z1+"]");
                double x2 = cosX*x1+sinX*y1;//rotate the points about the z axis
                vertex.setY(cosX*y1-sinX*x1);
                vertex.setZ(cosY*z1-sinY*x2);//rotates the points about the y axis
                vertex.setX(cosY*x2+sinY*z1);
                image.getGraphics().setColor(Color.BLACK);
//image.getGraphics().fillOval((int)p.getX()-5,(int)p.getY()-
            }
            for(int
                faceNum=0;faceNum<mobs.get(mob).getWireFrame().getFaceNum();faceNum++){//loops
                ThreeDPoint[] triangle = {
                        points[mobs.get(mob).getWireFrame().getFacePoint(faceNum,0)],
                        points[mobs.get(mob).getWireFrame().getFacePoint(faceNum,1)],
                        points[mobs.get(mob).getWireFrame().getFacePoint(faceNum,2)]
                };
                for(int i=0;i<3;i++){//gives screenTriangle the location of the
//System.out.println(""+screenTriangle[i].toString());
                }
                Vector normal = Vector.multiply(
                        new Vector(triangle[1],triangle[0]),
                        new Vector(triangle[2],triangle[0]));
                Plane[] tPlane = new Plane[3];
                for(int i=0;i<3;i++){
                    tPlane[i] = new Plane(
                            Vector.multiply(
                                    new Vector(triangle[i],triangle[(i+1)%3]),
                                    normal),
                            triangle[i]);
                    if(!tPlane[i].isInside(triangle[(i+2)%3]))tPlane[i].flip();
                }
//System.out.println(normal);
                for(int x=0;x<getWidth();x++){
                    for(int y=0;y<getHeight();y++){
                        Vector vector = new Vector(
                                cosYs[y] * cosXs[x],
                                cosYs[y] * sinXs[x],
                                -sinYs[y]
                        );
//System.out.println(vector);
                        double dist = Vector.planeDist(
                                normal,
                                triangle[0],
                                vector
                        );
                        ThreeDPoint pixelpoint = new ThreeDPoint(
                                cosYs[y] * cosXs[x]*dist,
                                cosYs[y] * sinXs[x]*dist,
                                -sinYs[y]*dist
                        );
                        boolean doDraw = true;
                        for(int i=0;i<3;i++){
                            if(!tPlane[i].isInside(pixelpoint)){
                                doDraw = false;
                                break;
                            }
                        }
//System.out.println(dist);
                        if (doDraw&&dist>0) {
                            if (zBuffer[x][y] > dist) {
                                zBuffer[x][y] = dist;
                                image.setRGB(x, y,
                                        mobs.get(mob).getWireFrame().getRGB(faceNum));
                                if(Player.isShooting() && x==getWidth()/2 &&
                                        y==getHeight()/2 && !mobs.get(mob).isHit){mobs.get(mob).hit();}
                            }
                        }
                    }
                }
            }
            mobs.get(mob).act();
            mobs.get(mob).resetHit();
            if(mobs.get(mob).doDeleat()){
                addMob(new Cube((int)(Math.random()*300)-150,200,0));
                addMob(new Cube((int)(Math.random()*300)-150,200,0));
                mobs.remove(mob);
            }
        }
        if(seeZBuffer){
            for(int x=0;x< zBuffer.length;x++){
                for(int y=0;y<zBuffer[0].length;y++){
                    if(zBuffer[x][y] >= 255)
                        image.setRGB(x,y,Color.WHITE.getRGB());
                    else
                        image.setRGB(x,y,new Color((int)zBuffer[x][y],(int)zBuffer[x][y],
                                (int)zBuffer[x][y]).getRGB());
                }
            }
        }
        for(int x=20;x<20+Player.getHealth()*10;x++){
            for(int y=getHeight()-40;y<getHeight()-20;y++){
                image.setRGB(x,y,Color.RED.getRGB());
            }
        }
        if(Player.isShooting() && canShoot <=0){
            canShoot = 2;
            image.getGraphics().setColor(Color.WHITE);
            image.getGraphics().fillOval(getWidth()/2-5,getHeight()/2-5,10,10);
        }
        canShoot--;
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
    public double getFOV(){
        return FOV;
    }
    public void setFOV(Double f){
        FOV = f;
    }
}
