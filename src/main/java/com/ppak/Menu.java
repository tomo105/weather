package com.ppak;

import sun.rmi.transport.ObjectTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Menu implements ActionListener {

    private List<Weather> list;

    JMenu menu, submenu;
    JLabel jLabel;
    JMenuItem i1, i2, i3, i4, i5, i6;
    JFrame frame = new JFrame("Pogoda");

    public Menu(List<Weather> list) {

        this.list = list;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar mb = new JMenuBar();
        jLabel= new JLabel("label1");
        jLabel.setBounds(10,10,560,560);
        jLabel.setSize(900,600);
        frame.setSize(900,600);
        menu = new JMenu("Menu");
        submenu = new JMenu("Wybierz dzień");

        i1 = new JMenuItem("O aplikacji");
        i2 = new JMenuItem("Dzisiaj");
        i3 = new JMenuItem("Jutro");
        i4 = new JMenuItem("Pojutrze");
        i5 = new JMenuItem("3 dni na przód");
        i6 = new JMenuItem("4 dni na przód");

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);
        i6.addActionListener(this);

        menu.add(i1);
        submenu.add(i2);
        submenu.add(i3);
        submenu.add(i4);
        submenu.add(i5);
        submenu.add(i6);

        menu.add(submenu);
        mb.add(menu);
        frame.add(jLabel);
        frame.setJMenuBar(mb);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == i2) {
            pathFromDB("clear_sky.png");
        }
    }

    private void pathFromDB(String path){
        try {
            File file = new File(path);
            BufferedImage bi = ImageIO.read(file);
            ImageIcon imgIcon = new ImageIcon(bi);
            jLabel.setIcon(imgIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
