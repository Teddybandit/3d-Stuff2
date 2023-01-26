import java.awt.*;
import javax.swing.*;
class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(500,500);
        Player player = new Player();
        MyPanel panel = new MyPanel(player);
        Bullet bullet = new Bullet(100,10,-1,0);
        panel.addMob(bullet);
        frame.add(panel);
        frame.setVisible(true);
        while(true){
            panel.repaint();
            try{
                Thread.sleep(50);
            }catch(Exception e){}
        }
    }
}