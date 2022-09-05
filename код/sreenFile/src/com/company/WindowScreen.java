package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WindowScreen {
    private static JFrame window;
    private static WindowPanel panel;
    public static void start(int x,int y) throws IOException {
        if(window==null||!window.isShowing()) {
            window = new JFrame();
            x=(x>Screen.device.getDisplayMode().getWidth())?0:x;
            window.setLocation(x,y);
            panel=new WindowPanel();
            window.setSize(800, 500);
            window.add(panel);
            window.setVisible(true);
        }
    }

    public static JFrame getWindow() {
        return window;
    }
}
