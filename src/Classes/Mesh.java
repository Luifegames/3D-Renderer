/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class TriComparator implements java.util.Comparator<Object>{

    @Override
    public int compare(Object o1, Object o2) {
     Triangle t1 = (Triangle) o1;
     Triangle t2 = (Triangle) o2;
     
     if (t1.getZDepth() < t2.getZDepth()) {
         //System.out.println("1");
         return 1;
     }
     else if  (t1.getZDepth() > t2.getZDepth()) {
         //System.out.println("-1");
         return -1;
     }
     else {
         //System.out.println("0");
         return 0;
     }
     
    }
    
}



public class Mesh{
    
    
    
    private ArrayList<Triangle>tris;
    private int scale = 100;
    private float angle;
    private Vector mouseVector, center;
    
    public Mesh() {
        tris = new ArrayList<>();
        mouseVector = new Vector(0,0,0);
        center = new Vector(0,0 ,0);
    }
    
    public Mesh(String type){
        mouseVector = new Vector(0,0,0);
        center = new Vector(200,200 ,400);
        tris = new ArrayList<>(); 
        if ("Cube".equals(type)){
            Vector v1 = new Vector(-0.5f, -0.5f, -0.5f);
            Vector v2 = new Vector(-0.5f, -0.5f,  0.5f);
            Vector v3 = new Vector(-0.5f,  0.5f, -0.5f);
            Vector v4 = new Vector(-0.5f,  0.5f,  0.5f);
            Vector v5 = new Vector( 0.5f, -0.5f, -0.5f);
            Vector v6 = new Vector( 0.5f, -0.5f,  0.5f);
            Vector v7 = new Vector( 0.5f,  0.5f, -0.5f);
            Vector v8 = new Vector( 0.5f,  0.5f,  0.5f);
            
            tris.add(new Triangle(Color.red, v1,v3,v7));
            tris.add(new Triangle(Color.red, v1,v7,v5));
            
            tris.add(new Triangle(Color.yellow, v5,v7,v8));
            tris.add(new Triangle(Color.yellow, v5,v8,v6));
            
            tris.add(new Triangle(Color.orange, v6,v8,v4));
            tris.add(new Triangle(Color.orange, v6,v4,v2));
            
            tris.add(new Triangle(Color.white, v2,v4,v3));
            tris.add(new Triangle(Color.white, v2,v3,v1));
            
            tris.add(new Triangle(Color.blue, v3,v4,v8));
            tris.add(new Triangle(Color.blue, v3,v8,v7));
            
           tris.add(new Triangle(Color.pink, v6,v2,v1));
            tris.add(new Triangle(Color.pink, v6,v1,v5));
            
        }
       else
        {
            BufferedReader reader;
            ArrayList<Vector> v = new ArrayList<>();
            try {
                reader = new BufferedReader(new FileReader(type));
                String line = reader.readLine();
                while (line != null) {       
                    String[] s1 = line.split(" ");
                    if (s1[0].equals("v")){
                        
                        v.add(new Vector(Float.parseFloat(s1[1]), Float.parseFloat(s1[2]), Float.parseFloat(s1[3])));
                    }
                    
                    
                    if (s1[0].equals("f")){
                        int[] f = new int[3];
                        f[0] = Integer.parseInt(s1[1]) - 1;
                        f[1] = Integer.parseInt(s1[2]) - 1;
                        f[2] = Integer.parseInt(s1[3]) - 1;
                        tris.add(new Triangle(Color.white, v.get(f[0]),v.get(f[1]),v.get(f[2])));
                    }
                    
                    line = reader.readLine();
                }
                
                reader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Not Found");
            } catch (IOException ex) {
                System.out.println("Error");
            }
        }
    }
    
    
    
    public void draw(Graphics2D g){
        angle += 0.4;
        angle = angle%360;
        
        tris.sort(new TriComparator());
        
        
        for(Triangle t:tris){
          //  System.out.println(t.getZDepth());
            t.setCenter(center);
            t.setScale(scale);
            t.setAngle(new Vector((float)Math.toRadians(angle),(float)Math.toRadians(angle),(float)Math.toRadians(angle)));
            t.draw(g);
        }
     //System.out.println("--------------------");
     
    }

   
    public void mouseMoved(MouseEvent e) {
       
    }
    
    
    
    
}
