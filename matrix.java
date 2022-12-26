/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectahp;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Keshav Kokande
 */
public class matrix extends javax.swing.JFrame {
    String query;
    String query2;
    /**
     * Creates new form matrix
     */
    int crtNum;
    public matrix() {
        initComponents();
    }
    public matrix(int cntNum){
        initComponents();
        this.crtNum = cntNum;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        counterLabel = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        paramText = new javax.swing.JTextField();
        cnfrmLabel = new javax.swing.JLabel();
        gridPanel = new javax.swing.JPanel();
        prefrenceBtn = new javax.swing.JButton();
        prefrenceLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        counterLabel.setText("Enter Parameter number 1");

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        gridPanel.setLayout(new java.awt.GridLayout(1, 0));

        prefrenceBtn.setText("Submit Prefrence");
        prefrenceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefrenceBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(counterLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(cnfrmLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paramText)
                                    .addComponent(submit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prefrenceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(prefrenceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(counterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paramText, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cnfrmLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(submit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(prefrenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(prefrenceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int count = 0;
    JComboBox comboBox1;
    List<String> name = new ArrayList<>();
    int selected;
    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        if(count<crtNum){
            try {
            // TODO add your handling code here:
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
                String dburl="jdbc:sqlserver://KESHAV;databaseName=Nehra;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            try {
                Connection con=DriverManager.getConnection(dburl);
            counterLabel.setText("Enter Parameter number " + Integer.toString(count+1));
            query = "ALTER TABLE Student  ADD "+ paramText.getText() +" DECIMAL(4, 3)";
            query2 = "ALTER TABLE Swingg  ADD "+ paramText.getText() +" DECIMAL(5,3)";
            PreparedStatement ps=con.prepareStatement(query);
            PreparedStatement ps2=con.prepareStatement(query2);
            ps.executeUpdate();
            ps2.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
            cnfrmLabel.setText(paramText.getText()+" Added to Database");
            if(paramText.getText() != null){
                name.add(paramText.getText());
            }
            count++;
            paramText.setText("");
            if(count == (crtNum)){
                for(int i = 0;i<((crtNum+1)*(crtNum+1));i++){
                    if(i != 0){
                     if(i/(crtNum+1) == 0 || i%(crtNum+1) == 0){
                         if(i >= (crtNum+1)){
                             int n = i/(crtNum+1);
                             JButton btn1 = new JButton(name.get(n-1));
                             gridPanel.add(btn1);
                         }
                         else{
                             JButton btn1 = new JButton(name.get(i-1));
                             gridPanel.add(btn1);
                         }
                     }

                     else{
                         comboBox1 = new JComboBox();
                         comboBox1.addItem("Select");
                         comboBox1.addItem((float)1/4);
                         comboBox1.addItem((float)1/2);
                         comboBox1.addItem((float)2/3);
                         comboBox1.addItem(1);
                         comboBox1.addItem((float)3/2);
                         comboBox1.addItem(2);
                         comboBox1.addItem(4);
                         gridPanel.add(comboBox1);
                         if(i%(crtNum+2) == 0){
                            comboBox1.setSelectedIndex(4);
                        }
                     }
                    }
                    else{
                        JLabel lbl = new JLabel("0");
                        gridPanel.add(lbl,0,0);
                    }
                }
                }gridPanel.setLayout(new GridLayout((crtNum+1),(crtNum+1),5,10));
        }
    }//GEN-LAST:event_submitActionPerformed
    List<Integer> prefrence = new ArrayList<>();
    private void prefrenceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefrenceBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prefrenceBtnActionPerformed
int num;    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(matrix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(matrix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(matrix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(matrix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new matrix().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cnfrmLabel;
    private javax.swing.JLabel counterLabel;
    private javax.swing.JPanel gridPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField paramText;
    private javax.swing.JButton prefrenceBtn;
    private javax.swing.JLabel prefrenceLabel;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
