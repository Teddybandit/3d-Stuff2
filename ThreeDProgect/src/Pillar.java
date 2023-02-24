import java.awt.*;

public class Pillar extends Mob{
    public Pillar(int x,int y){
        super(x,y,0);
    }

    @Override
    public void act() {

    }

    @Override
    public void display(Player player, Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(
                Mob.whereXLoad(this,player)-(int)(250/2/dist),
                Mob.whereYLoad(this,player)-(int)(1250/dist),
                (int)(250/dist),
                (int)(2500/dist)
        );
    }
    @Override
    public  boolean doDeleat(){
        return false;
    }
}
