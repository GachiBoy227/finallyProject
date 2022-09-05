package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.io.IOException;

public class Screen{
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    static int width=device.getDisplayMode().getWidth()/2;
    static int height=device.getDisplayMode().getHeight()/2;
    private JFrame jframe;
    public Screen(String label) throws IOException {
       jframe=new JFrame(label);
       jframe.setResizable(true);
       jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jframe.setSize(width,height);
        CenterPanel panel3=new CenterPanel(jframe);
        Controller controller=new Controller(jframe,panel3);
        panel3.setOpaque(false);
        jframe.add(panel3);
        jframe.add(controller);
       jframe.setVisible(true);
    }

}
