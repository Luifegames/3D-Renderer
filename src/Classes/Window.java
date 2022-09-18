/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable {

    private Panel p;
    private boolean running;
    
    public Window() {
        p = new Panel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.addMouseMotionListener(p);
        p.addMouseListener(p);
        p.setSize(600, 400);
        add(p);
        pack();
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            repaint();
        }
        dispose();
    }

}
