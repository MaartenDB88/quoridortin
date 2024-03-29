/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TaalKeuze.java
 *
 * Created on 10-mei-2010, 16:40:35
 */
package gui.grafisch;

import gui.MainGui;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Admin
 */
public class TaalKeuze extends javax.swing.JFrame {

    /** Creates new form TaalKeuze */
    public TaalKeuze() {

        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTaalKeuze = new javax.swing.ButtonGroup();
        jRadioButtonNederlands = new javax.swing.JRadioButton();
        jRadioButtonFrans = new javax.swing.JRadioButton();
        jRadioButtonEngels = new javax.swing.JRadioButton();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Language");
        setAlwaysOnTop(true);

        buttonGroupTaalKeuze.add(jRadioButtonNederlands);
        jRadioButtonNederlands.setSelected(true);
        jRadioButtonNederlands.setText("Nederlands");
        jRadioButtonNederlands.setActionCommand("0");

        buttonGroupTaalKeuze.add(jRadioButtonFrans);
        jRadioButtonFrans.setText("Français");
        jRadioButtonFrans.setActionCommand("1");

        buttonGroupTaalKeuze.add(jRadioButtonEngels);
        jRadioButtonEngels.setText("English");
        jRadioButtonEngels.setActionCommand("2");

        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonFrans)
                            .addComponent(jRadioButtonNederlands)
                            .addComponent(jRadioButtonEngels)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jRadioButtonNederlands)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonFrans)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonEngels)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOk)
                    .addComponent(jButtonCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        this.dispose();

        int taal = Integer.parseInt(this.buttonGroupTaalKeuze.getSelection().getActionCommand());
        String language = "";
        String country = "";
        switch (taal) {
            case 0:
                language = "ne";
                country = "NL";
                break;
            case 2:
                language = "en";
                country = "US";
                break;
            case 1:
                language = "fr";
                country = "FR";
                break;
        }
        ResourceBundle mess = ResourceBundle.getBundle("MessagesBundle", new Locale(language, country));
        MainGui main = new MainGui(mess);
        main.setVisible(true);
    }//GEN-LAST:event_jButtonOkActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTaalKeuze;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JRadioButton jRadioButtonEngels;
    private javax.swing.JRadioButton jRadioButtonFrans;
    private javax.swing.JRadioButton jRadioButtonNederlands;
    // End of variables declaration//GEN-END:variables
}
