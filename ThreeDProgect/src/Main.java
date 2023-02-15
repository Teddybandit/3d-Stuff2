import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Main {
    private static boolean pause = true;
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==27) {
                    pause = !pause;
                }
            }
        });
        frame.setCursor(Cursor.CROSSHAIR_CURSOR);
        frame.setSize(500,500);
        Player player = new Player();
        frame.addKeyListener(player.getControlls());
        MyPanel panel = new MyPanel(player);
        Mob.setPanel(panel);
        player.addPanel(panel);
        player.addControlls();
        for(int i=0;i<5;i++){
          panel.addMob(new Bullet(10+i,0,10,0.1,0,0));
        }
        panel.addMob(new Pillar(20,0));
        panel.addMob(new Enemy(50,50,10));
        frame.add(panel);
        frame.setVisible(true);
        while(true){
            if(pause) {
                panel.repaint();
                player.act();
                player.mouseMovement(frame);
            }
            try{
                Thread.sleep(50);
            }catch(Exception e){}
        }
    }
    public static boolean getPause(){
        return pause;
    }
}