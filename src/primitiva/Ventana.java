/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitiva;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class Ventana extends JFrame {

    Modelo m = new Modelo();

    public Ventana() {
        JFrame f;
        f = new JFrame();
        JTextField numLados = new JTextField();
        JLabel texto = new JLabel();
        texto.setBounds(900, 50, 200, 20);
        texto.setText("Número de lados para el polígono:");
        numLados.setBounds(900, 75, 140, 20);
        numLados.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    evt.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
            
            }
        });
        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.addItem("Punto");
        jComboBox.addItem("Linea");
        jComboBox.addItem("Poligono regular");
        jComboBox.addItem("Circunferencia");
        jComboBox.setBounds(730, 75, 140, 20);
        JButton archivoSVG = new JButton();
        archivoSVG.setText("Generar archivo svg");
        archivoSVG.setBounds(900, 100, 200, 40);
        f.add(archivoSVG);
        archivoSVG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                m.leerContenido();
            }
        });

        JButton borrarBD = new JButton();
        borrarBD.setText("Borrar base de datos");
        borrarBD.setBounds(900, 150, 200, 40);
        f.add(borrarBD);
        borrarBD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                m.borrarBD();
            }
        });
        f.add(jComboBox);
        f.add(numLados);
        f.add(texto);
        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        f.setSize(1324, 768);
        f.setLayout(null);
        Dibujo d = new Dibujo();
        d.setForeground(Color.red);
        f.add(d);
        d.repaint();

        d.addMouseListener(new MouseAdapter() {

            int x0 = 0;
            int y0 = 0;
            int click = 1;
            int x1;
            int y1;
            String ladosS = "";
            int lados = 0;

            @Override

            public void mousePressed(MouseEvent e) {

                System.out.println(e.getX() + "," + e.getY());
                Graphics g = d.getGraphics();

                switch (jComboBox.getSelectedIndex()) {
                    case 0:
                        g.fillOval(e.getX() - 4, e.getY() - 4, 8, 8);
                        m.insertarPunto(e.getX(), e.getY());
                        break;
                    case 1:
                        click = (click + 1) % 2;

                        if (click == 0) {
                            x0 = e.getX();
                            y0 = e.getY();
                            g.fillOval(x0 - 4, y0 - 4, 8, 8);
                        } else {
                            x1 = e.getX();
                            y1 = e.getY();
                            g.fillOval(x1 - 4, y1 - 4, 8, 8);
                            g.drawLine(x0, y0, x1, y1);
                            m.insertarLinea(x0, y0, x1, y1);
                        }
                        break;
                    case 2:
                        ladosS = numLados.getText();
                        lados = Integer.parseInt(ladosS);
                        click = (click + 1) % 2;

                        if (click == 0) {
                            x0 = e.getX();
                            y0 = e.getY();
                            g.fillOval(x0 - 4, y0 - 4, 8, 8);
                        } else {
                            x1 = e.getX();
                            y1 = e.getY();
                            g.fillOval(x1 - 4, y1 - 4, 8, 8);
                            g.drawLine(x0, y0, x1, y1);
                            int t,
                                    x,
                                    y;
                            int xdefinitivo = (int) Math.pow(x1 - x0, 2);
                            int ydefinitivo = (int) Math.pow((y1 - y0), 2);
                            double a = 0; //ángulo     
                            int xa,
                                    ya;
                            int radio = (int) Math.sqrt(xdefinitivo + ydefinitivo);
                            xa = radio;
                            ya = 0;
                            double alfa = 0;
                            //primer
                            if ((x1 > x0) && (y1 < y0)) {
                                alfa = Math.atan((float) (y1 - y0) / (float) (x1 - x0));
                                System.out.println("PRIMER CUADRANTE");
                                System.out.println("ÁNGULO " + alfa * 180 / Math.PI);
                            }
                            //segundo
                            if ((x1 < x0) && (y1 < y0)) {
                                alfa = Math.PI - Math.atan((float) (y1 - y0) / (float) (x0 - x1));
                                System.out.println("SEGUNDO CUADRANTE");
                                System.out.println("ÁNGULO " + alfa * 180 / Math.PI);
                            }

                            //tercero
                            if ((x1 < x0) && (y1 > y0)) {
                                alfa = (3 / 2) * Math.PI - Math.atan((float) (y1 - y0) / (float) (x0 - x1));
                                System.out.println("SEGUNDO CUADRANTE");
                                System.out.println("ÁNGULO " + alfa * 180 / Math.PI);
                            }
                            //cuarto
                            if ((x1 > x0) && (y1 > y0)) {
                                alfa = Math.atan((float) (y1 - y0) / (float) (x1 - x0));
                                System.out.println("CUARTO CUADRANTE");
                                System.out.println("ÁNGULO " + alfa * 180 / Math.PI);
                            }

                            //pendiente infinita
                            if (x1 == x0) {
                                if (y1 < y0) {
                                    alfa = -Math.PI / 2;
                                } else {
                                    alfa = Math.PI / 2;
                                }
                            }

                            xa = (int) x0 + (int) (radio * Math.cos(alfa));
                            ya = (int) y0 + (int) (radio * Math.sin(alfa));

                            for (t = 0; t <= lados; t++) {
                                a += ((double) 360 / lados) * Math.PI / 180;
                                x = (int) x0 + (int) (radio * Math.cos(a + alfa));
                                y = (int) y0 + (int) (radio * Math.sin(a + alfa));
                                g.drawLine(xa, ya, x, y);
                                xa = x;
                                ya = y;

                            }
                            m.insertarPoligono(x0, y0, x1, y1, lados);
                        }

                        break;
                    case 3:

                        click = (click + 1) % 2;

                        if (click == 0) {
                            x0 = e.getX();
                            y0 = e.getY();
                            g.fillOval(x0 - 4, y0 - 4, 8, 8);
                        } else {
                            x1 = e.getX();
                            y1 = e.getY();
                            g.fillOval(x1 - 4, y1 - 4, 8, 8);
                            g.drawLine(x0, y0, x1, y1);
                            double xdefinitivo = Math.pow(x1 - x0, 2);
                            double ydefinitivo = Math.pow((y1 - y0), 2);
                            int radio = (int) ((int) 2 * (Math.sqrt(xdefinitivo + ydefinitivo)));
                            System.out.println(radio);

                            g.drawOval(x0 - radio / 2, y0 - radio / 2, radio, radio);
                            m.insertarCircunferencia(x0, y0, x1, y1, radio / 2);
                        }

                        break;
                }
            }
        }
        );
        f.setVisible(true);
    }
}
