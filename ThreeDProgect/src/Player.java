import java.awt.event.*;
import java.lang.Math;
import java.sql.SQLOutput;

public class Player{
    private double x = 0;
    private double y = 0;
    private double xFacing = 0;
    private boolean aPress,sPress,dPress,wPress,leftPress,rightPress;
    KeyListener list;
    public Player(){
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
                  System.out.println(xFacing);
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
    public double getx(){
        return x;
    }
    public KeyListener getControlls(){
      return list;
    }
    public double gety(){
        return y;
    }
    public double getFacing(){
        return xFacing;
    }
    public void act(){
      if(aPress){
        y+=.5;
      }
      if(sPress){
        x-=.5;
      }
      if(dPress){
        y-=.5;
      }
      if(wPress){
        x+=Math.tan(xFacing);
      }
      if(leftPress){
          xFacing=xFacing+Math.PI/10%(2*Math.PI);
      }
      if(rightPress){
          xFacing=xFacing-Math.PI/10%(2*Math.PI);
      }
    }
}