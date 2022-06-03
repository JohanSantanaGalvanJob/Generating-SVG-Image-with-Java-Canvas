/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitiva;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Dibujo extends Canvas {
   
  Graphics2D k;
    boolean kk;
    boolean bb;
    boolean bb2;
    int x, y;

    public Dibujo() {
        setSize(700, 700);
        this.setBackground(Color.white);
        this.repaint();
    }
    @Override
    public void paint(Graphics g) {
        
}
}
