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

import domein.Domeincontroller;
import domein.Pion;
import domein.Speler;
import domein.vak;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Admin
 */
public class BordPaneel extends javax.swing.JPanel {

    private int breedte = 50;
    Graphics gr;
    vak vakUnderMouse;
    boolean gameStarted = false;
    Domeincontroller domeinC;
    int dimensie = 9;
    int offset = 2;
    

    public Domeincontroller getDomeinC() {
        return domeinC;
    }

    public void setDomeinC(Domeincontroller domeinC) {
        this.domeinC = domeinC;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /** Creates new form BordPaneel */
    public BordPaneel() {
        initComponents();

    }

    public void paintComponent(Graphics g) {
        gr = g;
        paintGame();

    }

    private void paintGame() {
        if (gameStarted) {
            dimensie = domeinC.getSpel().getDimensie();
            int breedteTest = this.getWidth() / dimensie;
            int hoogteTest = this.getHeight() / dimensie;
            breedte = (breedteTest > hoogteTest ? hoogteTest : breedteTest);
            clearBoard();
            drawBackground(gr);
            paintHuidigBord(gr);
        }
    }

    private void clearBoard() {
        gr.clearRect(0, 0, getWidth(), getHeight());
        this.repaint();
    }

    public void paintHuidigBord(Graphics g) {
        for (Speler speler : domeinC.getSpel().getSpelers()) {
            paintPion(speler.getPion());
        }
        if (vakUnderMouse != null) {
            //g.setColor(Color.LIGHT_GRAY);
            //     g.fillRect(vakUnderMouse.getX() * breedte + 2 + offset, vakUnderMouse.getY() * breedte + 2 + offset, breedte - 2, breedte - 2);
           if(true)
               g.setColor(Color.RED);
           else
               g.setColor(Color.GREEN);
            gr.drawRoundRect(vakUnderMouse.getX() * breedte + offset, vakUnderMouse.getY() * breedte + offset, breedte, breedte, 5, 5);
            
        }

    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < dimensie + 1; i++) {
            g.drawRect(breedte * i + offset, 0 + offset, 1, breedte * dimensie);
            g.drawRect(0 + offset, breedte * i + offset, breedte * dimensie, 1);

        }
    }

    private void paintPion(Pion pion) {

        gr.setColor(pion.getKleur());
        gr.fillOval(pion.getHuidig().getX() * breedte + 2 + offset, pion.getHuidig().getY() * breedte + 2 + offset, breedte - 4, breedte - 4);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

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

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        vakUnderMouse = getVakUnderMouse(evt.getX(), evt.getY());
        paintGame();
//        this.repaint();
    }//GEN-LAST:event_formMouseMoved

    public vak getVakUnderMouse(int x, int y) {
        if (domeinC == null || domeinC.getSpel() == null) {
            return null;
        }
        return domeinC.getSpel().getVak(x / breedte, y / breedte);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
