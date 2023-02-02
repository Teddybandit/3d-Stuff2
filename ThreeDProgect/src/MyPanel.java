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
        g.fillRect(0,0,1000,1000);
        g.setColor(Color.GREEN);
        g.fillRect(0, 250+(int)(P.getYFacing()/Math.PI*1000), 1000, 1000);

        for(Mob mob:mobs){
            mob.display(P,g);
            mob.act();
        }
    }
    public void addMob(Mob m){
        mobs.add(m);
    }
}