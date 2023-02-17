import java.awt.*;

public class Pillar extends Mob{
    public Pillar(int x,int y){
        super(x,y,0);
    }

    @Override
    public void act() {

    }

    @Override
    public void display(Player player, Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(
                Mob.whereXLoad(this,player)-(int)(x/2/dist),
                Mob.whereYLoad(this,player)-(int)(y*10/dist),
                (int)(x/dist),
                (int)(y*10/dist)
        );
    }
    @Override
    public  boolean doDeleat(){
        return false;
    }
}
