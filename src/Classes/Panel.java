
package Classes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseMotionListener,MouseListener{
   /* float fNear = 0.1f;
    float fFar = 1000.0f;
    float fFov = 90.0f;
    
    float fAspectRatio = 9/16;
    float fFovRad = (float) (1.0f / Math.tan(fFov * 0.5f / Math.toRadians(180.0f)));*/
    Mesh[] m = {new Mesh("src/monkey.obj"),new Mesh("src/n64model.obj"),new Mesh("src/figure.obj"),new Mesh("src/dice.obj"),new Mesh("src/circle.obj")};
    int iMesh = 0;
    
    
  //  static float[][] projection;

    public Panel() {
     /*   projection = new float[4][4];
        projection[0][0] = fAspectRatio *fFovRad;
        projection[1][1] = fFovRad;
        projection[2][2] = fFar / (fFar -fNear);
        projection[3][2] = (-fFar * fNear) / (fFar - fNear);
        projection[2][3] = 1.0f;
        projection[3][3] = 0.0f;*/
    }
    
    
    
    

   
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
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
        if (iMesh < m.length-1){
            iMesh ++;
        }
        else{
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
    
    
    
}
