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
        frame.add(panel);
        panel.addMob(new Cube());
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