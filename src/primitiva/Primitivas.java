/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package primitiva;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Primitivas {
public static BufferedImage imagen; 
    public static Graphics2D g2d;
    public static void main(String[] args)
    {
        imagen =new BufferedImage(1000,1000,TYPE_INT_ARGB );
        g2d=(Graphics2D)imagen.getGraphics();        
        new Ventana(); 
    }
}
