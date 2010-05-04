/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BordPaneel.java
 *
 * Created on 15-apr-2010, 21:28:13
 */
package gui.grafisch;

import domein.Pion;
import domein.Spelbord;
import domein.vak;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Admin
 */
public class BordPaneel extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    private Spelbord bord;
    private int breedte = 50;
    Graphics gr;
    vak vakUnderMouse;

    /** Creates new form BordPaneel */
    public BordPaneel() {
        initComponents();




    }

    public void paintComponent(Graphics g) {
        gr = g;
        clearBoard();
        paintHuidigBord(g);

    }

    private void clearBoard() {
        gr.clearRect(0, 0, getWidth(), getHeight());
        this.repaint();
    }

    public void paintHuidigBord(Graphics g) {
        g.setColor(Color.BLACK);
        int x = 0;
        for (int i = 0; i < 10; i++) {
            g.drawRect(x, 0, 1, breedte * 9);
            g.drawRect(0, x, breedte * 9, 1);
            x += breedte;
        }
        //Test pionnen
        Pion pion = new Pion("", "");
        pion.setKleur(Color.blue);
        paintPion(new vak(breedte * 4, 0, false, null), pion, g);

        pion.setKleur(Color.YELLOW);
        paintPion(new vak(breedte * 4, breedte * 8, false, null), pion, g);
        if (vakUnderMouse != null) {
            g.fillOval(vakUnderMouse.getX() + 2, vakUnderMouse.getY() + 2, breedte - 4, breedte - 4);
        }

    }

    private void paintPion(vak vak, Pion pion, Graphics g) {
        g.setColor(pion.getKleur());
        g.fillOval(vak.getX() + 2, vak.getY() + 2, breedte - 4, breedte - 4);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void mouseClicked(MouseEvent e) {
        System.out.println(" mouseClicked");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println(" mousePressed");
    }

    public void mouseReleased(MouseEvent e) {
        vakUnderMouse = null;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        Pion pion = new Pion("", "");
        vakUnderMouse = new vak(e.getX() - 20, e.getY() - 50, false, null);
        this.repaint();


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
