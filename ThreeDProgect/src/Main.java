

import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.*;
class Main {
    private static boolean pause = true;
    public static void main(String[] args) {
        System.out.println(1/Math.cos(Math.PI/2));
        Frame frame = new Frame();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==27) {
                    pause = !pause;
                }
            }
        });
        frame.setSize(500,500);
        Player player = new Player();
        frame.addKeyListener(player.getControlls());
        MyPanel panel = new MyPanel(player);
        panel.setFOV(Math.PI/2);
        Mob.setPanel(panel);
        player.addPanel(panel);
        panel.addMob(new Cube(100,0,0));
        for(int x=-1;x<=1;x++) {
            for (int y = -1; y <= 1; y++) {
                int floorC = new Color(20,70,20).getRGB();
                if((x+y)%2==0)
                    floorC = new Color(20,100,20).getRGB();
                panel.addMob(new Floor(100*x, 100*y, -10,floorC));
            }
        }
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