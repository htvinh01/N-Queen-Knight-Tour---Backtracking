/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Cris17
 */
public final class Window extends javax.swing.JFrame {

    /**
     * Creates new form QueenGUI
     */
    private test b;
    private test_1 c;
    JPanel panelBoard;

    public Window() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icons/Queen.png"));
        Image image = icon.getImage();
        setIconImage(image);
        jsrSpeed.setMaximum(50);
        jsrSpeed.setValue(25);
        jsrSpeed.setMinimum(1);
        SpinnerNumberModel model1 = new SpinnerNumberModel(9, 4, 26, 1);
        //JSpinner spin1 = new JSpinner(model1);

        jSPSize.setModel(model1);

        ImageIcon img = new ImageIcon(getClass().getResource("/img/chess.png"));
        int width = img.getIconWidth() / 3;
        int height = img.getIconHeight() / 3;
        Image scaled = scaleImage(img.getImage(), width, height);
        ImageIcon scaledIcon = new ImageIcon(scaled);

        jLabel6.setIcon(scaledIcon);
        setBoard(9);
    }
//set board lúc mới khởi tạo
    //panelBoard sẽ chứa chessBoard bên class test.

    public void setBoard(int n) {

        panelBoard = new JPanel(new BorderLayout(3, 3));

        panelBoard.setBounds(0, 0, 810, 810);

        panelBoard.setBorder(new EmptyBorder(5, 5, 5, 5));
        b = new test(n);
        panelBoard.add(b.getChessBoard());
        panelBoard.setVisible(true);
        this.add(panelBoard);

    }

    //set board để chạy thuật toán
    public void setBoard_Queen(int n, int row, int col, int time) {

        row = getTxtRow();
        col = getTxtCol();
        time = getJsrSpeed();
        panelBoard = new JPanel(new BorderLayout(3, 3));

        panelBoard.setBounds(0, 0, 810, 810);

        panelBoard.setBorder(new EmptyBorder(5, 5, 5, 5));
        b = new test(n, row, col, time);

//        b.getChessBoard().revalidate();
        panelBoard.add(b.getChessBoard());
        panelBoard.setVisible(true);
        this.add(panelBoard);

    }

    public void setBoard(int n, int row, int col, int time) {

        row = getTxtRow() - 1;
        col = getTxtCol() - 1;
        time = getJsrSpeed();
        panelBoard = new JPanel(new BorderLayout(3, 3));

        panelBoard.setBounds(0, 0, 810, 810);

        panelBoard.setBorder(new EmptyBorder(5, 5, 5, 5));
        c = new test_1(n, row, col, time);

//        b.getChessBoard().revalidate();
        panelBoard.add(c.getChessBoard());
        panelBoard.setVisible(true);
        this.add(panelBoard);

    }

    private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInfor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRow = new javax.swing.JTextField();
        txtCol = new javax.swing.JTextField();
        btnGo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jsrSpeed = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        jSPSize = new javax.swing.JSpinner();
        btnKnight = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Niên Luận");
        setBackground(new java.awt.Color(255, 255, 204));
        setResizable(false);

        jPanelInfor.setBackground(new java.awt.Color(255, 255, 204));
        jPanelInfor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Setting", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanelInfor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Row:");
        jPanelInfor.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 376, -1, -1));

        jLabel2.setText("Column:");
        jPanelInfor.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 424, -1, -1));

        jLabel3.setText("Board size:");
        jPanelInfor.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 260, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Choose position");
        jPanelInfor.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));
        jPanelInfor.add(txtRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 373, 180, -1));

        txtCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColActionPerformed(evt);
            }
        });
        jPanelInfor.add(txtCol, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 420, 180, -1));

        btnGo.setText("N-Queens");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        jPanelInfor.add(btnGo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, -1, -1));

        jLabel5.setText("Speed:");
        jPanelInfor.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 493, -1, -1));

        jsrSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsrSpeedStateChanged(evt);
            }
        });
        jPanelInfor.add(jsrSpeed, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));
        jPanelInfor.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jSPSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSPSizeStateChanged(evt);
            }
        });
        jPanelInfor.add(jSPSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 251, 67, 31));

        btnKnight.setText("Knight Tour");
        btnKnight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKnightActionPerformed(evt);
            }
        });
        jPanelInfor.add(btnKnight, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 809, Short.MAX_VALUE)
                .addComponent(jPanelInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInfor, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColActionPerformed
// để chạy thuật toán
    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        // TODO add your handling code here:
        // b.getChessBoard().hide();

        try {
            if (txtRow.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in the Row", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (txtCol.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in the Column", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (getTxtRow() > getJsSize() || getTxtCol() > getJsSize()) {
                JOptionPane.showMessageDialog(this, "Please choose the position of the chess piece that is less than or equal to the size of the chessboard", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                panelBoard.hide();
                int n = getJsSize();

                setBoard_Queen(n, getTxtRow(), getTxtCol(), getJsrSpeed());
            }
//            this.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnGoActionPerformed


    private void btnKnightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKnightActionPerformed
        // TODO add your handling code here:
        try {

            if (txtRow.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in the Row", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (txtCol.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in the Column", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (getTxtRow() > getJsSize() || getTxtCol() > getJsSize()) {
                JOptionPane.showMessageDialog(this, "Please choose the position of the chess piece that is less than or equal to the size of the chessboard", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                panelBoard.hide();
                int n = getJsSize();

                setBoard(n, getTxtRow(), getTxtCol(), getJsrSpeed());
            }
//            this.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnKnightActionPerformed

    private void jSPSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSPSizeStateChanged
        // TODO add your handling code here:
        try {

            panelBoard.hide();
            int n = getJsSize();

            setBoard(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jSPSizeStateChanged


    private void jsrSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsrSpeedStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jsrSpeedStateChanged

    public JButton getBtnGo() {
        return btnGo;
    }

    public int getJsrSpeed() {
        return jsrSpeed.getValue();
    }

    public void setJsrSpeed(JSlider jsrSpeed) {
        this.jsrSpeed = jsrSpeed;
    }

    public void setTxtCol(JTextField txtCol) {
        this.txtCol = txtCol;
    }

    public int getTxtCol() {
        return Integer.parseInt(txtCol.getText());
    }

    public int getTxtRow() {
        return Integer.parseInt(txtRow.getText());
    }

    public void setTxtRow(JTextField txtRow) {
        this.txtRow = txtRow;
    }

    public int getJsSize() {
        return Integer.parseInt(jSPSize.getValue().toString());
    }

    public void setjSPSize(JSpinner jSPSize) {
        this.jSPSize = jSPSize;
    }

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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnKnight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanelInfor;
    private javax.swing.JSpinner jSPSize;
    private javax.swing.JSlider jsrSpeed;
    private javax.swing.JTextField txtCol;
    private javax.swing.JTextField txtRow;
    // End of variables declaration//GEN-END:variables
}