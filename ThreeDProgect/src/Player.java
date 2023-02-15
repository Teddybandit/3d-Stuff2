import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
import java.sql.SQLOutput;

public class Player{
    private double x = 0;
    private double y = 0;
    private double z = 10;
    private double xFacing = 0;
    private double yFacing = 0;
    private boolean aPress,sPress,dPress,wPress,leftPress,rightPress;
    MyPanel panel;
    KeyListener list;
    MouseAdapter mouse;
  Robot robot;
    public Player(){
      try{
        robot = new Robot();
      }catch(Exception e){
        System.out.println("mouse controlls unavailable");
      };
      list = new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
            //System.out.println(e.getKeyCode());
          switch(e.getKeyCode()){
            case 65:
              aPress = true;
              //System.out.println("a pressed");
              break;
            case 83:
              sPress = true;
              //System.out.println("s pressed");
              break;
            case 68:
              dPress = true;
              //System.out.println("d pressed");
              break;
            case 87:
              //System.out.println("w pressed");
              wPress = true;
              break;
              case 37:
                  leftPress=true;
                  break;
              case 39:
                  rightPress=true;
                  break;

          }
        }
         @Override
        public void keyReleased(KeyEvent e){
          switch(e.getKeyCode()){
            case 65:
              //System.out.println("a released");
              aPress = false;
              break;
            case 83:
              //System.out.println("s released");
              sPress = false;
              break;
            case 68:
              //System.out.println("d released");
              dPress = false;
              break;
            case 87:
              //System.out.println("w released");
              wPress = false;
              break;
              case 37:
                  leftPress=false;
                  break;
              case 39:
                  rightPress=false;
                  break;
          }
        }
      };
    }
    public void mouseMovement(Frame f){
      if(f.getMousePosition()!=null){
        xFacing=(xFacing-((f.getMousePosition().getX()-panel.getWidth()/2)/panel.getWidth()*2*Math.PI))%(Math.PI*2);
        yFacing=yFacing-(f.getMousePosition().getY()-panel.getHeight()/2)/panel.getHeight()*2*Math.PI;
        if(yFacing<Math.PI/-2){
          yFacing=Math.PI/-2;
        }else if(yFacing>Math.PI/2){
          yFacing=Math.PI/2;
        }
        robot.mouseMove(f.getX()+panel.getWidth()/2,f.getY()+panel.getHeight()/2);
      }
    }
    public double getx(){
        return x;
    }
    public KeyListener getControlls(){
      return list;
    }
    public double gety(){
        return y;
    }
    public double getz() {return z;}
    public double getXFacing(){
        return xFacing;
    }
    public double getYFacing(){return yFacing;}
    public void act(){
      if(aPress){
        x+=.5*Math.cos(xFacing+Math.PI/2);
        y+=.5*Math.sin(xFacing+Math.PI/2);
      }
      if(sPress){
        x+=.5*Math.cos(xFacing+Math.PI);
        y+=.5*Math.sin(xFacing+Math.PI);
      }
      if(dPress){
        x+=.5*Math.cos(xFacing-Math.PI/2);
        y+=.5*Math.sin(xFacing-Math.PI/2);
      }
      if(wPress){
        x+=.5*Math.cos(xFacing);
        y+=.5*Math.sin(xFacing);
      }
      if(leftPress){
          xFacing=xFacing+Math.PI/100%(2*Math.PI);
      }
      if(rightPress){
          xFacing=xFacing-Math.PI/100%(2*Math.PI);
      }
    }
    public void addPanel(MyPanel p){
      panel = p;
    }
    public void addControlls(){
      panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          panel.addMob(new Bullet(x,y,z,5*Math.cos(xFacing)*Math.cos(yFacing),5*Math.sin(xFacing)*Math.cos(yFacing),5*Math.sin(yFacing)));
        }
      });
    }
}