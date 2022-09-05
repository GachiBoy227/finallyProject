package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.TimerTask;
public class Controller extends JPanel implements ActionListener{
    public  ImageIcon bg;
    public  ImageIcon bgcent=new ImageIcon(ImageIO.read(getClass().getResourceAsStream("bpgcg.jpg")));
    JFrame JF;
    CenterPanel centerPanel;
    Controller(final JFrame jFrame,CenterPanel centerPanel) throws IOException {
        JF=jFrame;
        Timer task=new Timer(1,this);
        this.centerPanel=centerPanel;
        setBackground(Color.decode("#082137"));
        task.start();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgcent.getImage(),centerPanel.getX(),centerPanel.getY(),centerPanel.getWidth(),centerPanel.getHeight(),null);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("TimesRoman", Font.BOLD, JF.getWidth()/25));
        g2.drawString("Ширина:"+JF.getWidth()+" Высота:"+JF.getHeight(),0,JF.getWidth()/25);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        bg= centerPanel.getBg();
    }

}
