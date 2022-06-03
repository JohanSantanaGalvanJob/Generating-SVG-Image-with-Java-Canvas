package primitiva;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {

    public void insertarPunto(int x0, int y0) {
        try {
            String palabra = "punto";
            Connection con = null;
            String sURL = "jdbc:mariadb://localhost:3306/canvas";
            con = DriverManager.getConnection(sURL, "root", "");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("INSERT INTO `canva` (`nombre`,`x0`,`y0`) VALUES " + "('" + palabra + "','" + x0 + "','" + y0 + "')");
            String cadena = "";
            while (rs.next()) {

                cadena += rs.getString(1) + "\n";
            }
            System.out.println(cadena);
            con.close();
        } catch (SQLException e) {
        }
    }

    public void insertarLinea(int x0, int y0, int x1, int y1) {
        try {
            String palabra = "linea";
            Connection con = null;
            String sURL = "jdbc:mariadb://localhost:3306/canvas";
            con = DriverManager.getConnection(sURL, "root", "");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("INSERT INTO `canva` (`nombre`,`x0`,`y0`,`x1`,`y1`) VALUES " + "('" + palabra + "','" + x0 + "','" + y0 + "','" + x1 + "','" + y1 + "')");
            String cadena = "";
            while (rs.next()) {

                cadena += rs.getString(1) + "\n";
            }
            System.out.println(cadena);
            con.close();
        } catch (SQLException e) {
        }
    }

    public void insertarPoligono(int x0, int y0, int x1, int y1, int lados) {
        try {
            String palabra = "poligono";
            Connection con = null;
            String sURL = "jdbc:mariadb://localhost:3306/canvas";
            con = DriverManager.getConnection(sURL, "root", "");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("INSERT INTO `canva` (`nombre`,`x0`,`y0`,`x1`,`y1`,`lados`) VALUES " + "('" + palabra + "','" + x0 + "','" + y0 + "','" + x1 + "','" + y1 + "','" + lados + "')");
            String cadena = "";
            while (rs.next()) {

                cadena += rs.getString(1) + "\n";
            }
            System.out.println(cadena);
            con.close();
        } catch (SQLException e) {
        }
    }

    public void insertarCircunferencia(int x0, int y0, int x1, int y1, int radio) {
        try {
            String palabra = "circunferencia";
            Connection con = null;
            String sURL = "jdbc:mariadb://localhost:3306/canvas";
            con = DriverManager.getConnection(sURL, "root", "");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("INSERT INTO `canva` (`nombre`,`x0`,`y0`,`x1`,`y1`,`radio`) VALUES " + "('" + palabra + "','" + x0 + "','" + y0 + "','" + x1 + "','" + y1 + "','" + radio + "')");
            String cadena = "";
            while (rs.next()) {

                cadena += rs.getString(0) + "\n";
            }
            System.out.println(cadena);
            con.close();
        } catch (SQLException e) {
        }
    }

    public void leerContenido() {
        try {

            Connection con = null;
            String sURL = "jdbc:mariadb://localhost:3306/canvas";
            con = DriverManager.getConnection(sURL, "root", "");

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("select * from `canva`");
            String cadena = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                    + "<!DOCTYPE svg>\n"
                    + "<svg height=\"700\" width=\"700\" xmlns=\"http://www.w3.org/2000/svg\">\n";
            while (rs.next()) {
                switch (rs.getString(1)) {
                    case "punto":
                        cadena += "<circle cx=\"" + rs.getString(2) + "\" cy=\"" + rs.getString(3) + "\" r=\"3\" stroke=\"red\" stroke-width=\"1\" fill=\"red\" />\n";
                        break;
                    case "linea":
                        cadena += " <polyline points=\"" + rs.getString(2) + "," + rs.getString(3) + " " + rs.getString(4) + "," + rs.getString(5) + "\" style=\"fill:none;stroke:red;stroke-width:1\" />\n";
                        break;
                    case "poligono":
                        int x0= Integer.parseInt(rs.getString(2));
                        int y0= Integer.parseInt(rs.getString(3));
                        int x1=Integer.parseInt(rs.getString(4));
                        int y1=Integer.parseInt(rs.getString(5));
                        int lados = Integer.parseInt(rs.getString(7));
                        
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
                            }
                            //segundo
                            if ((x1 < x0) && (y1 < y0)) {
                                alfa = Math.PI - Math.atan((float) (y1 - y0) / (float) (x0 - x1));
                            }

                            //tercero
                            if ((x1 < x0) && (y1 > y0)) {
                                alfa = (3 / 2) * Math.PI - Math.atan((float) (y1 - y0) / (float) (x0 - x1));
                            }
                            //cuarto
                            if ((x1 > x0) && (y1 > y0)) {
                                alfa = Math.atan((float) (y1 - y0) / (float) (x1 - x0));
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
                            cadena += " <polygon points=\"" + String.valueOf(xa) + "," + String.valueOf(ya);
                            for (t = 0; t <= lados; t++) {
                                a += ((double) 360 / lados) * Math.PI / 180;
                                x = (int) x0 + (int) (radio * Math.cos(a + alfa));
                                y = (int) y0 + (int) (radio * Math.sin(a + alfa));
                                cadena+=" "+String.valueOf(x)+","+String.valueOf(y);
                                xa = x;
                                ya = y;
                                
                               
                            }
                        cadena += "\" style=\"fill:none;stroke:red;stroke-width:1\" />\n";
                        break;
                    case "circunferencia":
                        cadena += "<circle cx=\"" + rs.getString(2) + "\" cy=\"" + rs.getString(3) + "\" r=\""+rs.getString(6)+"\" stroke=\"red\" stroke-width=\"1\" fill=\"none\" />\n";
                        break;
                        
                }
            }
            cadena += "</svg>";
            generarSVG(cadena);

            con.close();
        } catch (SQLException e) {
        }
    }

    public void generarSVG(String contenido) {
        System.out.println(contenido);
        try {
            String ruta = "C:\\Users\\Johan\\Desktop\\lil.svg";

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void borrarBD(){
         try {
            Connection con = null;
            String sURL = "jdbc:mariadb://localhost:3306/canvas";
            con = DriverManager.getConnection(sURL, "root", "");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("DELETE FROM `canva` WHERE 1");
             System.out.println("Base de datos borrada");
            con.close();
        } catch (SQLException e) {
        }
    }
}
