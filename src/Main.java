import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;
public class Main{
    public static void main(String[] args)
    {
        Frame frame = new Frame();
        frame.setSize(500,300);
        MyJPanel panel = new MyJPanel();
        frame.add(panel);
        frame.setVisible(true);

    }
}