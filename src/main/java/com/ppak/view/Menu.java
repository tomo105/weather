package com.ppak.view;

import com.ppak.model.Weather;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Menu implements ActionListener {

    private List<Weather> list;
    private int tempId;
    private Weather tmpWeather;

    JMenu menu, submenu;
    JLabel jLabel, deg, rest, rest2, rest3, rest4, rest5, miasto;
    JMenuItem i1, i2, i3, i4, i5, i6;
    JFrame frame = new JFrame("Pogoda");
    JButton leftButton;
    JButton rightButton;

    public Menu(List<Weather> list) {

        this.list = list;
        this.tempId = 0;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar mb = new JMenuBar();

        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 90);
        Font font1 = new Font(Font.SANS_SERIF, Font.PLAIN, 24);

        jLabel = new JLabel("");
        jLabel.setBounds(600, -150, 900, 600);

        miasto = new JLabel("KRAKÓW");
        miasto.setBounds(50, -190, 500, 500);
        miasto.setFont(font);

        deg = new JLabel("");
        deg.setBounds(300, 150, 560, 560);
        deg.setSize(450, 150);
        rest = new JLabel("");
        rest.setBounds(100, 50, 560, 560);
        rest.setSize(450, 150);
        rest2 = new JLabel("");
        rest2.setBounds(100, 100, 560, 560);
        rest2.setSize(250, 150);
        rest3 = new JLabel("");
        rest3.setBounds(100, 150, 560, 560);
        rest3.setSize(250, 150);
        rest4 = new JLabel("");
        rest4.setBounds(100, 200, 560, 560);
        rest4.setSize(250, 150);
        rest5 = new JLabel("");
        rest5.setBounds(100, 250, 560, 560);
        rest5.setSize(250, 150);

        rest.setFont(font1);

        deg.setFont(font);

        frame.setMinimumSize(new Dimension(400, 300));
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setMaximumSize(new Dimension(1600, 900));

        rightButton = new JButton("+3H");
        leftButton = new JButton("-3H");
        leftButton.setBounds(150, 400, 150, 70);
        rightButton.setBounds(550, 400, 150, 70);

        menu = new JMenu("Menu");
        submenu = new JMenu("Wybierz dzień");

        i1 = new JMenuItem("O aplikacji");
        i2 = new JMenuItem(date(0));
        i3 = new JMenuItem(date(1));
        i4 = new JMenuItem(date(2));
        i5 = new JMenuItem(date(3));
        i6 = new JMenuItem(date(4));

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);
        i6.addActionListener(this);
        rightButton.addActionListener(this);
        leftButton.addActionListener(this);

        menu.add(i1);
        submenu.add(i2);
        submenu.add(i3);
        submenu.add(i4);
        submenu.add(i5);
        submenu.add(i6);

        menu.add(submenu);
        mb.add(menu);
        frame.add(jLabel);
        frame.add(deg);
        frame.add(rest);
        frame.add(rest2);
        frame.add(rest3);
        frame.add(rest4);
        frame.add(rest5);
        frame.add(miasto);
        frame.add(leftButton);
        frame.add(rightButton);
        frame.setJMenuBar(mb);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        tmpWeather = chooseFirstWeatherInDay(0);
        pathFromDB(checkImage(tmpWeather.getDescription()));
        print();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Object source = e.getSource();
        print();
        if (source == i2) {
            tmpWeather = chooseFirstWeatherInDay(0);
            pathFromDB(checkImage(tmpWeather.getDescription()));
        } else if (source == i3) {
            tmpWeather = chooseFirstWeatherInDay(1);
            pathFromDB(checkImage(tmpWeather.getDescription()));
        } else if (source == i4) {
            tmpWeather = chooseFirstWeatherInDay(2);
            pathFromDB(checkImage(tmpWeather.getDescription()));
        } else if (source == i5) {
            tmpWeather = chooseFirstWeatherInDay(3);
            pathFromDB(checkImage(tmpWeather.getDescription()));
        } else if (source == i6) {
            tmpWeather = chooseFirstWeatherInDay(4);
            pathFromDB(checkImage(tmpWeather.getDescription()));

        }
        if (source == i1)
            JOptionPane.showMessageDialog(i1, "Apka pogoda, dobra jak jagoda");

        if (source == rightButton) {
            tempId = tmpWeather.getId();
            if (++tempId > 39) {
                tempId = 39;
            }
            pathFromDB(checkImage(list.get(tempId).getDescription()));
            tmpWeather = list.get(tempId);

        } else if (source == leftButton) {
            tempId = tmpWeather.getId();
            if (--tempId < 0) {
                tempId = 0;
            }
            pathFromDB(checkImage(list.get(tempId).getDescription()));
            tmpWeather = list.get(tempId);
        }


    }

    private void print() {
        double temperature = convertFromKelvinToCelsius(tmpWeather.getTemp());

        deg.setText(String.format("%.1f", temperature) + " C");
        rest.setText("Data: " + tmpWeather.getData());
        rest2.setText("Ciśnienie: " + tmpWeather.getPressure() + "hPa");
        rest3.setText("Prędkość wiatru: " + tmpWeather.getSpeed() + "km/h");
        rest4.setText("Kąt wiatru: " + tmpWeather.getDeg());
        rest5.setText("Wilgotność: " + tmpWeather.getHumidity() + "%");
    }

    private void pathFromDB(String path) {
        try {
            File file = new File(path);
            BufferedImage bi = ImageIO.read(file);
            ImageIcon imgIcon = new ImageIcon(bi);
            jLabel.setIcon(imgIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String date(int dayPlus) {

        LocalDateTime dateTime = LocalDateTime.now().plusDays(dayPlus);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(format);

    }

    private Weather chooseFirstWeatherInDay(int dayPlus) {
        String data = date(dayPlus);

        Weather weather = new Weather();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getData().substring(0, 10).equals(data)) {
                weather = list.get(i);
                break;
            }

        }
        System.out.println(weather.getData());
        return weather;

    }

    private Double convertFromKelvinToCelsius(Double kelvin) {
        return kelvin - 273.15;
    }

    private String checkImage(String description) {
        if (description.equals("light rain")) {
            return "deszcz.png";
        } else if (description.equals("clear sky")) {
            return "clear_sky.png";
        } else if (description.equals("few clouds")) {
            return "za_chmura.png";
        } else if (description.equals("broken clouds")) {
            return "dwiechmury.png";
        } else if (description.equals("overcast clouds")) {
            return "ciemnachmura.png";
        } else if (description.equals("scattered clouds")) {
            return "za_chmura.png";
        } else {
            return "deszczichmura.png";
        }
    }
}
