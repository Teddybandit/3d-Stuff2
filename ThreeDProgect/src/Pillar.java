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
                Mob.whereXLoad(this,player)-(int)(250/dist),
                250-(int)(1900/dist-player.getYFacing()/Math.PI*1000),
                (int)(500/dist),
                (int)(2000/dist)
        );
    }
    @Override
    public  boolean doDeleat(){
        return false;
    }
}
