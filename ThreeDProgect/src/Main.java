import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.*;
class Main {
    private static boolean pause = true;
    public static void main(String[] args) {
        System.out.println(new Color(100,100,100).getRGB());
        System.out.println(Color.ORANGE.getRGB());
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
        frame.setSize(1000,500);
        Player player = new Player();
        frame.addKeyListener(player.getControlls());
        MyPanel panel = new MyPanel(player);
        panel.setFOV(Math.PI);
        Mob.setPanel(panel);
        Side.setMyPanl(panel);
        player.addPanel(panel);
        //panel.addMob(new Cube(100,0,0));
        panel.addMob(new Floor(0,0,0));
        //player.addControlls();
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
            }catch(Exception e){System.out.println("couldn't sleep");}
        }
    }
    public static boolean getPause(){
        return pause;
    }
}