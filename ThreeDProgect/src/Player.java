import java.awt.event.*;
import java.awt.*;
import java.lang.Math;
import java.sql.SQLOutput;
public class Player{
    private static double x = 0;
    private static double y = 0;
    private static double z = 10;
    private static double xFacing = 0;
    private static double yFacing = 0;
    private static int health = 10;
    private static boolean
            aPress,sPress,dPress,wPress,leftPress,rightPress,upPress,downPress,shoot;
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
                    case 84:
                        panel.zBufferOn();
                        break;
                    case 37:
                        leftPress=true;
                        break;
                    case 38:
                        upPress=true;
                        break;
                    case 39:
                        rightPress=true;
                        break;
                    case 40:
                        downPress=true;
                        break;
                    case 32:
                        shoot=true;
                        break;
                    default:
                        System.out.println(e.getKeyCode());
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
                    case 84:
                        panel.zBufferOff();
                        break;
                    case 37:
                        leftPress=false;
                        break;
                    case 38:
                        upPress=false;
                        break;
                    case 39:
                        rightPress=false;
                        break;
                    case 40:
                        downPress=false;
                        break;
                    case 32:
                        shoot = false;
                        break;
                }
            }
        };
    }
    public void mouseMovement(Frame f){
        if(f.getMousePosition()!=null){
            xFacing=(xFacing-((f.getMousePosition().getX()-panel.getWidth()/2)/
                    panel.getWidth()*Math.PI))%(Math.PI*2);
            yFacing=yFacing-(f.getMousePosition().getY()-panel.getHeight()/2)/
                    panel.getHeight()*Math.PI;
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
    static double getYFacing(){return yFacing;}
    public void act(){
        if(aPress){
            x+=2*Math.cos(xFacing+Math.PI/2);
            y+=2*Math.sin(xFacing+Math.PI/2);
        }
        if(sPress){
            x+=2*Math.cos(xFacing+Math.PI);
            y+=2*Math.sin(xFacing+Math.PI);
        }
        if(dPress){
            x+=2*Math.cos(xFacing-Math.PI/2);
            y+=2*Math.sin(xFacing-Math.PI/2);
        }
        if(wPress){
            x+=2*Math.cos(xFacing);
            y+=2*Math.sin(xFacing);
        }
        if(leftPress){
            xFacing=(xFacing+Math.PI/100)%(2*Math.PI);
        }
        if(rightPress){
            xFacing=(xFacing-Math.PI/100)%(2*Math.PI);
        }
        if(upPress){
            yFacing=Math.min(yFacing+Math.PI/100,Math.PI/2);
        }
        if(downPress){
            yFacing=Math.max(yFacing-Math.PI/100,Math.PI/-2);
        }
        if(x>150) x=150;
        if(x<-150) x=-150;
        if(y>150) y=150;
        if(y<-150) y=-150;
    }
    public void addPanel(MyPanel p){
        panel = p;
    }
    public void addControlls(){
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
    public static double getX(){return x;}
    public static double getY(){return y;}
    public static double getZ(){return z;}
    public static void doDamage(int d){
        health-=d;
        if(health<=0){
            System.out.println("you died");
            System.exit(0);
        }
    }
    public static int getHealth(){return health;}
    public static boolean isShooting(){return shoot;}
}
