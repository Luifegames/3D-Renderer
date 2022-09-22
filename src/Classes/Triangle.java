package Classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle {

    private Vector[] v;
    private int scale;
    private Color c;
     int[] xp = new int[3];
        int[] yp = new int[3];
        int[] zp = new int[3];
    private Vector angle, center;

    public Triangle(Color c, Vector v1, Vector v2, Vector v3) {
        this.c = c;
        angle = new Vector(0, 0, 0);

        center = new Vector();
        v = new Vector[3];
        v[0] = v1;
        v[1] = v2;
        v[2] = v3;
    }



   

    public void draw(Graphics2D g) {
        g.setPaint(Color.black);
        g.drawRect((int)center.getX(),(int)center.getY(), 2, 2);
        
        
        float[][] rotationX = {
            {1, 0, 0, 0},
            {0, (float) Math.cos(angle.getX()), (float)- Math.sin(angle.getX()), 0},
            {0, (float) Math.sin(angle.getX()), (float) Math.cos(angle.getX()), 0},
            {0, 0, 0, 1}
        };

        float[][] rotationY = {
            {(float) Math.cos(angle.getY()), 0, (float) Math.sin(angle.getY()), 0},
            {0, 1, 0, 0},
            {(float) -Math.sin(angle.getY()), 0, (float) Math.cos(angle.getY()), 0},
            {0, 0, 0, 1}
        };

        float[][] rotationZ = {
            {(float) Math.cos(angle.getZ()), (float) Math.sin(angle.getZ()), 0, 0},
            {(float) -Math.sin(angle.getZ()), (float) Math.cos(angle.getZ()), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };

       
        
        Vector line1  = new Vector();
        Vector line2  = new Vector();
        Vector normal = new Vector();

        //Ilumination
        Vector light_direction = new Vector(0, 0, 1);
        //Normalize
        double l = (double) Math.sqrt(light_direction.getX() * light_direction.getX()
                + light_direction.getY() * light_direction.getY()
                + light_direction.getZ() * light_direction.getZ());

        light_direction.setX((float) (light_direction.getX() / l));
        light_direction.setY((float) (light_direction.getY() / l));
        light_direction.setZ((float) (light_direction.getZ() / l));

        for (int i = 0; i < 3; i++) {
            Vector rotated = Panel.Matmul(rotationX, v[i]);
            rotated = Panel.Matmul(rotationY, rotated);
            rotated = Panel.Matmul(rotationZ, rotated);
            Vector projected2d = Panel.Matmul(Panel.projection, rotated);

            xp[i] = (int) (center.getX() +scale * projected2d.getX());
            yp[i] = (int) (center.getY() + scale * projected2d.getY());
            zp[i] = (int) (center.getZ() +scale * projected2d.getZ());
        }

        line1.setX( xp[1] - xp[0]);
        line1.setY( yp[1] - yp[0]);
        line1.setZ( zp[1] - zp[0]);
        
        line2.setX( xp[2] - xp[1]);
        line2.setY( yp[2] - yp[1]);
        line2.setZ( zp[2] - zp[1]);
        
        normal.setX( line1.getY() * line2.getZ() - line1.getZ() * line2.getY());
        normal.setY( line1.getZ() * line2.getX() - line1.getX() * line2.getZ());
        normal.setZ( line1.getX() * line2.getY() - line1.getY() * line2.getX());
        
        float ln = (float) Math.sqrt(normal.getX() * normal.getX()
                + normal.getY() * normal.getY()
                + normal.getZ() * normal.getZ());
        
        normal.setX((float) (normal.getX() / ln));
        normal.setY((float) (normal.getY() / ln));
        normal.setZ((float) (normal.getZ() / ln));
        

        float dp = normal.getX() * light_direction.getX() + normal.getY() * light_direction.getY() + normal.getZ() * light_direction.getZ();
       
        //CAMERA RENDERIZATION TO DO
        if (normal.getX() * (xp[0] - Panel.getvCamera().getX()) +
            normal.getY() * (yp[0] - Panel.getvCamera().getY()) +
                normal.getZ() * (zp[0] - Panel.getvCamera().getZ()) < 0.0f)
        {
            
        }
        if (normal.getZ() < 0.0f)
        drawTriangle(g,setShadow(dp), xp, yp);
        


    }

    public void drawTriangle(Graphics2D g,Color gp, int[] xp, int[] yp) {
        g.setPaint(Color.black);
        g.setStroke(new BasicStroke(2));
       g.drawLine((int) xp[0], yp[0], xp[1], yp[1]);
       g.drawLine((int) xp[2], yp[2], xp[0], yp[0]);
        g.drawLine((int) xp[1], yp[1], xp[2], yp[2]);
        g.setPaint(gp);
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

    public Color setShadow(float lum) {
        
        return Color.getHSBColor(0, 0, lum);
    }

    public int getZDepth() {
       int zDepth = ( zp[0] + zp[1] + zp[2])/3;
        return zDepth;
    }

}
