package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CenterPanel extends JPanel implements ActionListener, DropTargetListener {
    private JFrame screen;
    public static BufferedImage bufferedImage;
    public static String nameImage="";
    public static ImageIcon bg;
    public  ImageIcon setbg;
    boolean isSet=false;
    CenterPanel(JFrame screen) throws IOException {
        DropTarget dt = new DropTarget(
                this,
                DnDConstants.ACTION_COPY_OR_MOVE,
                this,
                true);
        this.screen=screen;
        Timer timer=new Timer(0,this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.setLocation(screen.getWidth()/7,screen.getHeight()/7);
        this.setSize((int)(screen.getWidth()*0.7),(int)(screen.getHeight()*0.7));
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        Transferable t = dtde.getTransferable();
        if(t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
            try {
                Object td =  t.getTransferData(DataFlavor.javaFileListFlavor);
                System.out.println(((java.util.List)td).size());
                for (Object val:((java.util.List)td)){
                    if(val instanceof File){
                        bufferedImage=ImageIO.read(((File) val));
                        nameImage=((File) val).getName();
                        setbg=new ImageIcon(((File) val).getPath());
                        isSet=true;
                    }
                }


            }catch (Exception e){

            }
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        if(isSet){
            bg=setbg;
            isSet=false;
            try {
                WindowScreen.start(screen.getWidth()+screen.getX(),screen.getY());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ImageIcon getBg() {
        return bg;
    }
}
