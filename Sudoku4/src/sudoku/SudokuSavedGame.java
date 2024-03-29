package sudoku;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SudokuSavedGame extends javax.swing.JFrame {

    private DefaultTableModel tm;
    private final String leaderboardFile = "leaderboard123";
    private TableRowSorter<TableModel> sorter;
    //private SudokuPuzzleTableModel tableModel;

    public SudokuSavedGame() {
        initComponents();
        initTableData();
        initTableSorter();
        setLocationRelativeTo(null);
    }

    private void initTableSorter() {
        sorter = new TableRowSorter<>(tm);
        leaderboard.setRowSorter(sorter);

        ArrayList<RowSorter.SortKey> keys = new ArrayList<>();
        keys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));

        sorter.setSortKeys(keys);
    }

    private void initTableData() {
        Vector ci = new Vector();
        ci.add("Player");
        ci.add("State");
        ci.add("Mode");
        ci.add("Time");

        tm = (DefaultTableModel) leaderboard.getModel();

        try {
            FileInputStream fs = new FileInputStream(leaderboardFile);
            ObjectInputStream os = new ObjectInputStream(fs);

            tm.setDataVector((Vector<Vector>) os.readObject(), ci);

            os.close();
            fs.close();
        } catch (Exception e) {
        }

    }

    private void saveLeaderboard() {
        try {
            FileOutputStream fs = new FileOutputStream(leaderboardFile);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(tm.getDataVector());

            os.close();
            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addPlayer(String player, String state, String mode) {
        LocalTime time = LocalTime.now();

        tm.addRow(new Object[]{player, state, mode, time});
        sorter.sort();

        saveLeaderboard();

        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboard = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 750));

        jButton1.setText("Home Page");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        leaderboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "State", "Mode", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leaderboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaderboardMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(leaderboard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Sudoku.showHomePage();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void leaderboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaderboardMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_leaderboardMouseClicked

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
            java.util.logging.Logger.getLogger(SudokuSavedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SudokuSavedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SudokuSavedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SudokuSavedGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SudokuSavedGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderboard;
    // End of variables declaration//GEN-END:variables
}
