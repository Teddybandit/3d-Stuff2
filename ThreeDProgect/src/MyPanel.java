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
            for(int i2=0;i2< zBuffer[0].length;i2++){
                zBuffer[i][i2] = Integer.MAX_VALUE;
            }
        }
        image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);//the image thet will be displayed at the end
        g.setColor(Color.GREEN);
        g.fillRect(0, Math.max(getHeight()/2+(int)(P.getYFacing()/Math.PI*getHeight()*4),0), getWidth(), getHeight());
        for(int i = 0;i<mobs.size();i++){
            mobs.get(i).display(P,g);
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