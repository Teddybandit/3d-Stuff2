import java.awt.*;
import javax.swing.*;
class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setCursor(Cursor.CROSSHAIR_CURSOR);
        frame.setSize(500,500);
        Player player = new Player();
        frame.addKeyListener(player.getControlls());
        MyPanel panel = new MyPanel(player);
        for(int i=0;i<5;i++){
          Bullet bullet = new Bullet(10+i,0,0,0);
          panel.addMob(bullet);
        }
        frame.add(panel);
        frame.setVisible(true);
        while(true){
            panel.repaint();
            player.act();
            player.mouseMovement(frame);
            try{
                Thread.sleep(100);
            }catch(Exception e){}
        }
    }
}