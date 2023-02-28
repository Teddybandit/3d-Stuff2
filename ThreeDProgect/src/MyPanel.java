import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
public class MyPanel extends JPanel{
    private Player P;
    private ArrayList<Mob> mobs = new ArrayList<Mob>();
    public MyPanel(Player player){
        P = player;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.GREEN);
        g.fillRect(0, Math.max(getHeight()/2+(int)(P.getYFacing()/Math.PI*getHeight()*4),0), getWidth(), getHeight());
        for(Mob mob:mobs){
            mob.refreshDistence(P);
        }
        Mob.sortDist(mobs);
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