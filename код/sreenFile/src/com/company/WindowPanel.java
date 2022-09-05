package com.company;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WindowPanel extends JPanel implements ActionListener {
    JFrame getWindow;
    TextField textvertical=new TextField();
    TextField texthorizontal=new TextField();
    Button create=new Button("Создать");
    public WindowPanel() throws IOException {
       getWindow=WindowScreen.getWindow();
       setLayout(new FlowLayout(SwingConstants.RIGHT,getWidth(),0));
       add(textvertical);
       add(new Label("По вертикали"));
        add(texthorizontal);
        add(new Label("По горизонтали"));
        create.setBackground(Color.GREEN);
        add(create);
        create.setEnabled(true);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writeImage(Integer.parseInt(texthorizontal.getText()), Integer.parseInt(textvertical.getText()));
                }catch (Exception error){

                }
            }
        });
       Timer timer=new Timer(10,this);
       timer.start();
    }
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        g.drawImage(CenterPanel.bg.getImage(),0,0,getWindow.getWidth(),getWindow.getHeight(),null);
        try {
            drawSetka(g, Integer.parseInt(texthorizontal.getText()), Integer.parseInt(textvertical.getText()));
        }catch (Exception e){

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    private void drawSetka(Graphics g,int horizontal_count,int vertical_count){
        g.setColor(Color.red);
        int width=getwidthhorizont(horizontal_count,getWindow.getWidth());
        int height=getheightverical(vertical_count,getWindow.getHeight());
        for(int i=0;i<getWindow.getWidth();i+=width){
            g.drawLine(i,0,i,getWindow.getHeight());
        }
        for(int i=0;i<getWindow.getHeight();i+=height){
            g.drawLine(0,i,getWindow.getWidth(),i);
        }
    }
    private void writeImage(int horizontal_count,int vertical_count) {
        BufferedImage image=CenterPanel.bufferedImage;
        int count=0;
        String nameImage=CenterPanel.nameImage.split("\\.")[0];
        String rashirenie=CenterPanel.nameImage.split("\\.")[1];
        int width=getwidthhorizont(horizontal_count,image.getWidth());
        int height=getheightverical(vertical_count,image.getHeight());
        for(int h=0;h<image.getWidth();h+=width) {
            for (int v = 0; v < image.getHeight(); v += height) {
                File file = new File("вырезанное изображение/"+nameImage + "_" + count + "." + rashirenie);
                File folder=new File("вырезанное изображение/");
                try {
                    if (!file.exists()&&count<vertical_count*horizontal_count) {
                        folder.mkdir();
                        file.createNewFile();
                    }
                    BufferedImage subimage=image.getSubimage(h, v, width, height);
                    if(subimage!=null) {
                        ImageIO.write(image.getSubimage(h, v, width, height), rashirenie, file);
                        count++;
                    }
                } catch (Exception e) {

                }
            }
        }
    }
    private int getwidthhorizont(int horizontal_count,double max_width){
       return (int)(max_width/horizontal_count);
    }
    private int getheightverical(int vertical_count,double max_height){
        return (int)(max_height/vertical_count);
    }
}
