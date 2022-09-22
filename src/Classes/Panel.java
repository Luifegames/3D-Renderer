package Classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseMotionListener, MouseListener {

    private float fNear, fFar, fFov, fAspectRatio, fFovRad;

    private Mesh[] m = {new Mesh("src/monkey.obj"), new Mesh("src/n64model.obj"), new Mesh("src/figure.obj"), new Mesh("src/dice.obj"), new Mesh("src/circle.obj")};
    private int iMesh = 0;
    private static Vector vCamera;

    static float[][] projection;

    public Panel() {
        vCamera = new Vector(1, 1, 1);
        fNear = 0.1f;
        fFar = 1000.0f;
        fFov = 90.0f;
        fAspectRatio = 16/9;
        fFovRad = (float) (1.0f / Math.tan(fFov * 0.5f / 180.0f * 3.14159f));
        projection = new float[4][4];
        projection[0][0] = fAspectRatio * fFovRad;
        projection[1][1] = fFovRad;
        projection[2][2] = fFar / (fFar - fNear);
        projection[3][2] = (-fFar * fNear) / (fFar - fNear);
        projection[2][3] = 1.0f;
        projection[3][3] = 0.0f;
    }

    public static Vector Matmul(float[][] a, float[][] b) {

/*        Vector result = new Vector();
        result.setX(a[0][0] * b[0][0] + a[0][1] * b[1][0] + a[0][2] * b[2][0] + b[3][0]);
        result.setY(a[1][0] * b[0][0] + a[1][1] * b[1][0] + a[1][2] * b[2][0] + b[3][0]);
        result.setZ(a[2][0] * b[0][0] + a[2][1] * b[1][0] + a[2][2] * b[2][0] + b[3][0]);
        float w   = a[3][0] * b[0][0] + a[3][1] * b[1][0] + a[3][2] * b[2][0] + b[3][0];

     
*/
          int colsA = a[0].length;
        int rowsA = a.length;
        int colsB = b[0].length;
        int rowsB = b.length;

        if (colsA != rowsB) {
            System.out.println("Colums of A must match rows of B");
            return null;
        }

        float[][] r = new float[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                float sum = 0;
                for (int k = 0; k < rowsB; k++) {
                    sum += a[i][k] * b[k][j];
                }
                r[i][j] = sum;
            }
        }
           Vector result = new Vector();
        result.setX(r[0][0]);
        result.setY(r[1][0]);
        result.setZ(r[2][0]);
        float w     = a[3][0] * b[0][0] + a[3][1] * b[1][0] + a[3][2] * b[2][0] + b[3][0];

           if (w != 0) {
            result.setX(result.getX() / w);
            result.setY(result.getY() / w);
            result.setZ(result.getZ() / w);
        }
        
       
return result;
        
    }

    public static Vector Matmul(float[][] a, Vector v) {
        float[][] b = v.vectToMatrix();

        return Matmul( a,b);
    }

    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        g.fillRect(0,0,getWidth(),getHeight());
        m[iMesh].draw(g2);
        setOpaque(false);
        super.paint(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (iMesh < m.length - 1) {
            iMesh++;
        } else {
            iMesh = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static Vector getvCamera() {
        return vCamera;
    }

}
