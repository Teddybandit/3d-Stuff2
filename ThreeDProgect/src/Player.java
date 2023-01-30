import java.awt.event.*;
public class Player{
    private int x = 0;
    private int y = 0;
    private double xFacing = 0;
    private boolean aPress,sPress,dPress,wPress;
    KeyListener list;
    public Player(){
      list = new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
          switch(e.getKeyChar()){
            case 'a':
              aPress = true;
              System.out.println("a pressed");
              break;
            case 's':
              sPress = true;
              System.out.println("s pressed");
              break;
            case 'd':
              dPress = true;
              System.out.println("d pressed");
              break;
            case 'w':
              System.out.println("w pressed");
              wPress = true;
              break;
          }
        }
         @Override
        public void keyReleased(KeyEvent e){
          switch(e.getKeyChar()){
            case 'a':
              System.out.println("a released");
              aPress = false;
              break;
            case 's':
              System.out.println("s released");
              sPress = false;
              break;
            case 'd':
              System.out.println("d released");
              dPress = false;
              break;
            case 'w':
              System.out.println("w released");
              wPress = false;
              break;
          }
        }
      };
    }
    public int getx(){
        return x;
    }
    public KeyListener getControlls(){
      return list;
    }
    public int gety(){
        return y;
    }
    public double getFacing(){
        return xFacing;
    }
    public void act(){
      if(aPress){
        y+=.1;
      }
      if(sPress){
        x-=.1;
      }
      if(dPress){
        y-=.1;
      }
      if(wPress){
        x+=.1;
      }
    }
}