import java.awt.*;
import javax.swing.*;
class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(500,500);
        Player player = new Player();
        frame.addKeyListener(player.getControlls());
        MyPanel panel = new MyPanel(player);
        Bullet bullet = new Bullet(10,0,0,0);
        panel.addMob(bullet);
        frame.add(panel);
        frame.setVisible(true);
        while(true){
            panel.repaint();
            player.act();
            try{
                Thread.sleep(100);
            }catch(Exception e){}
        }
    }
}