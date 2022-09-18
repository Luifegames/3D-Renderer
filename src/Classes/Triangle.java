
package Classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

 public class Triangle {
    private Vector[] v;
    private int scale;
    private Color c;
    private Vector angle,center;

    public Triangle(Color c,Vector v1,Vector v2, Vector v3) {
        this.c = c;
        angle = new Vector(0, 0, 0);
        
        center = new Vector(10, 10, 0);
        v = new Vector[3];
        v[0] = v1;
        v[1] = v2;
        v[2] = v3;
    }

  
    
    
    public float[][] Matmul(float[][] a,float[][] b){
        
        
        int colsA = a[0].length;
        int rowsA = a.length;
        int colsB = b[0].length;
        int rowsB = b.length;
        
        if (colsA != rowsB){
            System.out.println("Colums of A must match rows of B");
            return null;
        }
        
        float[][] result = new float[rowsA][colsB];
        
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                float sum = 0;
                for (int k = 0; k < rowsB; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
        
    }
    
    
    public  float[][] Matmul (float[][] a,Vector v){
        float[][] b = v.vectToMatrix();
        
        return Matmul(a, b);
    }
    
    
    
   
    
    public void draw(Graphics2D g){
        
         float [][] rotationX= {
            {1,0,0},
            {0,(float) Math.cos( angle.getX()),(float) - Math.sin(angle.getX())},
            {0,(float) Math.sin(angle.getX()),(float) Math.cos(angle.getX())}
        };
        
        float [][] rotationY= {
            {(float) Math.cos( angle.getY()),0,(float) - Math.sin(angle.getY())},
            {0,1,0},
            {(float) Math.sin(angle.getY()),0,(float) Math.cos(angle.getY())}
        };
        
        float [][] rotationZ= {
            {(float) Math.cos( angle.getZ()),(float) - Math.sin(angle.getZ()),0},
            {(float) Math.sin(angle.getZ()),(float) Math.cos(angle.getZ()),0},
            {0,0,1}
        };
        
        int[] xp = new int[3];
        int[] yp = new int[3];
        int[] line1 = new int[3];
        int[] line2 = new int[3];
        
        int normal;
        
        //Ilumination
        Vector light_direction = new Vector(0, 0,-1);
        //Normalize
        float l = (float) Math.sqrt(light_direction.getX() * light_direction.getX() + 
                            light_direction.getY() * light_direction.getY() + 
                light_direction.getY() * light_direction.getY());
        
        light_direction.setX(light_direction.getX() / l);
        light_direction.setY(light_direction.getY() / l);
        light_direction.setZ(light_direction.getZ() / l);
        
        
        
        
        
        
        for (int i = 0; i < 3; i++) {
            float[][] rotated = Matmul(rotationX, v[i]);
           rotated = Matmul(rotationY, rotated);
           rotated = Matmul(rotationZ, rotated);
       
       
       float [][] projection = {
            {1,0,0},
            {0,1,0}
        };
       
       
            float[][] projected2d = Matmul(projection, rotated); 
               xp[i] =  (int)(center.getX() +  scale * projected2d[0][0]) ;
            yp[i] =  (int) (center.getY() + scale *projected2d[1][0]) ;
            
           
           
        }
        
        line1[0] = xp[1] - xp[0];
        line1[1] = yp[1] - yp[0];
        
        
        line2[0] = xp[2] - xp[1];
        line2[1] = yp[2] - yp[1];
        
        normal = line1[0] * line2[1] - line1[1] * line2[0]; 
        
        
        if (normal > 0)
         drawTriangle(g, xp, yp);
        
       // g.drawRect(150, 150, 4, 4);
        g.setPaint(c);
        
    }

    
    public void drawTriangle(Graphics2D g,int[] xp,int[] yp){
        g.setPaint(Color.black);
        g.setStroke(new BasicStroke(2));
        g.drawLine((int)xp[0],yp[0],xp[1],yp[1]);
        g.drawLine((int)xp[2],yp[2],xp[0],yp[0]);
        g.drawLine((int)xp[1],yp[1],xp[2],yp[2]);
        g.setPaint(c);
        g.fillPolygon(xp, yp, 3);
    }

    public Vector getAngle() {
        return angle;
    }

    public void setAngle(Vector angle) {
        this.angle = angle;
    }

    public Vector getCenter() {
        return center;
    }

    public void setCenter(Vector center) {
        this.center = center;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Vector[] getV() {
        return v;
    }
    
    
    
    public float setShadow(float lum){
        int pixel_bw = (int)(13.0f*lum);
        int value = 0;
        switch(pixel_bw){
            case 5:
            case 6:
            case 7:
            case 8:
                value = 100;
                break;
             case 9:
            case 10:
            case 11:
            case 12:
                value = 255;
                break;    
        }
        return value;
    }
        
    
    
}
