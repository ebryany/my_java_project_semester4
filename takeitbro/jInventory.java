/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pertemuan_12;

/**
 *
 * @author febry
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.TableModel;
public class jInventory extends javax.swing.JFrame {

    // Class fields for layout management
    private int scrollPaneWidth;
    
    /**
     * Creates new form jInventory
     */
    public jInventory() {
        initComponents();
        MenuOpen.setEnabled(false);
        
        bg_kondisi.add(rb_baik);
        bg_kondisi.add(rb_sedang);
        bg_kondisi.add(rb_buruk);
        
        String[] kolom = {"Nama Produk", "Kode Produk", "Quantity", "Kondisi"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0);
        table_x.setModel(model);
        
        // Setup layout before other initializations
        setupMainLayout();
        setupResponsiveComponents();
        
        // Set up window properties for proper resizing
        setMinimumSize(new Dimension(950, 700));
        setPreferredSize(new Dimension(1200, 800));
        setResizable(true);
        
        // Add component listeners after layout setup
        addComponentResizeListener();
        addWindowStateListener();
        
        // Initial layout validation
        revalidateLayout();
    }
    
    /**
     * Set up the main layout structure for responsive behavior
     */
    private void setupMainLayout() {
        // Set content pane layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(15, 15));
        ((JComponent)contentPane).setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );
        
        // Add main panel to center of content pane
        contentPane.add(jPanel1, BorderLayout.CENTER);
    }
    
    /**
     * Add component resize listener to handle window resizing
     */
    private void addComponentResizeListener() {
        addComponentListener(new ComponentAdapter() {
            private Timer resizeTimer;
            private final int RESIZE_DELAY = 100; // milliseconds
            private Dimension lastSize;
            
            @Override
            public void componentResized(ComponentEvent e) {
                // Skip small resize events (helps with performance)
                Dimension currentSize = getSize();
                if (lastSize != null) {
                    int widthDiff = Math.abs(currentSize.width - lastSize.width);
                    int heightDiff = Math.abs(currentSize.height - lastSize.height);
                    
                    // Only handle significant size changes
                    if (widthDiff < 5 && heightDiff < 5) {
                        return;
                    }
                }
                lastSize = currentSize;
                
                // Use timer to avoid too frequent revalidation
                if (resizeTimer == null) {
                    resizeTimer = new Timer(RESIZE_DELAY, evt -> {
                        revalidateLayout();
                        resizeTimer.stop();
                    });
                    resizeTimer.setRepeats(false);
                }
                
                if (!resizeTimer.isRunning()) {
                    resizeTimer.start();
                }
            }
        });
    }
    
    /**
     * Add window state listener to handle maximize/restore
     */
    private void addWindowStateListener() {
        addWindowStateListener(new WindowStateListener() {
            private Timer stateChangeTimer;
            private final int STATE_CHANGE_DELAY = 150; // milliseconds
            
            @Override
            public void windowStateChanged(WindowEvent e) {
                // Use a timer to handle state change after the window operation completes
                if (stateChangeTimer == null) {
                    stateChangeTimer = new Timer(STATE_CHANGE_DELAY, evt -> {
                        // Use invokeLater to ensure we're on the EDT
                        SwingUtilities.invokeLater(() -> {
                            revalidateLayout();
                        });
                        stateChangeTimer.stop();
                    });
                    stateChangeTimer.setRepeats(false);
                }
                
                if (!stateChangeTimer.isRunning()) {
                    stateChangeTimer.start();
                }
            }
        });
    }
    
    /**
     * Save table data to CSV file
     * 
     * @param table JTable containing data to save
     * @param path File path where to save the CSV
     * @return boolean indicating success (true) or failure (false)
     */
    public boolean savetableToCsv(JTable table, String path) {
        try (FileWriter csv = new FileWriter(path)) {
           TableModel model = table.getModel();
           
           // Write header row
           for(int i = 0; i < model.getColumnCount(); i++) {
               csv.write(model.getColumnName(i));
               if (i != model.getColumnCount() - 1) {
                   csv.write(",");
               }
           }
           
           csv.write("\n");
           
           // Write data rows
           for (int i = 0; i < model.getRowCount(); i++) {
               for(int j = 0; j < model.getColumnCount(); j ++ ) {
                   Object cell = model.getValueAt(i, j);
                   String cellText = cell == null ? "" : cell.toString();
                   csv.write(cellText);
                   
                   if (j != model.getColumnCount() - 1) {
                       csv.write(",");
                   }
               }
               
               csv.write("\n");
           }
           
           csv.flush();
           System.out.println("Data tabel berhasil disimpan ke: " + path);
           return true; // Save successful
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data table: " + e.getMessage());
            return false; // Save failed
        }
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        bg_kondisi = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_quality = new javax.swing.JTextField();
        txt_namaproduk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_kodeproduk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rb_baik = new javax.swing.JRadioButton();
        rb_sedang = new javax.swing.JRadioButton();
        rb_buruk = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_x = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btn_inputdata = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        file_btn = new javax.swing.JMenu();
        MenuOpen = new javax.swing.JMenuItem();
        MenuSave = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenuItem();
        inputdata_btn = new javax.swing.JMenu();
        MenuInventory = new javax.swing.JMenuItem();
        MenuLaporan = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data Inventory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Produk");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kode Produk");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity");

        rb_baik.setForeground(new java.awt.Color(255, 255, 255));
        rb_baik.setText("Baik");

        rb_sedang.setForeground(new java.awt.Color(255, 255, 255));
        rb_sedang.setText("Sedang");

        rb_buruk.setForeground(new java.awt.Color(255, 255, 255));
        rb_buruk.setText("Buruk");

        table_x.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        table_x.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        table_x.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table_x.setFillsViewportHeight(true);
        table_x.setRowHeight(25); // Set consistent row height for better appearance
        table_x.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        table_x.getTableHeader().setResizingAllowed(true); // Allow manual column resizing
        table_x.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Better selection behavior
        table_x.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nama Produk", "Kode Produk", "Quality"
            }
        ));
        jScrollPane1.setViewportView(table_x);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kondisi");

        btn_inputdata.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_inputdata.setText("INPUT DATA");
        btn_inputdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inputdataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_quality, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(txt_kodeproduk, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_buruk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_inputdata, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_baik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_namaproduk, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb_sedang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_namaproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_kodeproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_baik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_sedang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rb_buruk)
                        .addGap(18, 18, 18)
                        .addComponent(btn_inputdata, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_quality, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(15))
        );
        file_btn.setText("File");

        MenuOpen.setText("Open");
        MenuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpenActionPerformed(evt);
            }
        });
        file_btn.add(MenuOpen);

        MenuSave.setText("Save");
        MenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSaveActionPerformed(evt);
            }
        });
        file_btn.add(MenuSave);

        MenuEdit.setText("Exit");
        MenuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEditActionPerformed(evt);
            }
        });
        file_btn.add(MenuEdit);

        jMenuBar1.add(file_btn);

        inputdata_btn.setText("Input Data");

        MenuInventory.setText("Inventory");
        MenuInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuInventoryActionPerformed(evt);
            }
        });
        inputdata_btn.add(MenuInventory);

        MenuLaporan.setText("Laporan");
        MenuLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLaporanActionPerformed(evt);
            }
        });
        inputdata_btn.add(MenuLaporan);

        jMenuBar1.add(inputdata_btn);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSaveActionPerformed
        // Handle Save menu action
        
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if(!path.toLowerCase().endsWith(".csv")) {
                path += ".csv";
            }
            
            // Check if table has data to save
            if (table_x.getRowCount() == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Tidak ada data untuk disimpan!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Try to save the file and show appropriate message
            boolean saveSuccess = savetableToCsv(table_x, path);
            
            if (saveSuccess) {
                // Show success message with file path
                JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil disimpan ke file:\n" + path,
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Show error message
                JOptionPane.showMessageDialog(
                    this,
                    "Gagal menyimpan data ke file:\n" + path,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        
    }//GEN-LAST:event_MenuSaveActionPerformed

    private void MenuInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuInventoryActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_MenuInventoryActionPerformed

    private void MenuLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLaporanActionPerformed
        // TODO add your handling code here:
        
        jLaporan _laporan = new jLaporan();
        _laporan.setVisible(true);
        _laporan.setLocationRelativeTo(null);
        this.dispose();
        
                JOptionPane.showMessageDialog(this, "Selamat Datang laporan ", "halo admin", JOptionPane.INFORMATION_MESSAGE);

        
       
        
    }//GEN-LAST:event_MenuLaporanActionPerformed
    private void MenuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEditActionPerformed
        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin keluar?",
            "Konfirmasi Keluar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        // If user confirms, show goodbye message and exit
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                this,
                "Yahh kenapa keluar sih",
                "Goodbye!",
                JOptionPane.INFORMATION_MESSAGE
            );
            this.dispose();
        }
        // If NO_OPTION, do nothing and keep program running
    }//GEN-LAST:event_MenuEditActionPerformed

    private void MenuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpenActionPerformed

    private void btn_inputdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inputdataActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) table_x.getModel();
        String namaproduk = txt_namaproduk.getText();
        String kode = txt_kodeproduk.getText();
        String Quantity = txt_quality.getText();
        String kondisi = null;
        
        if(rb_baik.isSelected()) {
            kondisi = "Baik";
        } else if (rb_sedang.isSelected()) {
            kondisi = "Sedang";
        } else if (rb_buruk.isSelected()) {
            kondisi = "Buruk";
        }
        
        if (namaproduk.isEmpty() || kode.isEmpty() || Quantity.isEmpty() || kondisi == null ) {
            JOptionPane.showMessageDialog(null, JOptionPane.WARNING_MESSAGE);
            return;
        
        }
         if (model != null) {
             model.addRow(new Object[]  {namaproduk, kode, Quantity, kondisi});
         } else {
             JOptionPane.showMessageDialog(this, "Model tabel belum diinisalisasi!", "Error", JOptionPane.ERROR_MESSAGE);
    }        
       txt_namaproduk.setText("");
       txt_quality.setText("");
       txt_kodeproduk.setText("");
       rb_baik.setSelected(false);
       rb_sedang.setSelected(false);
       rb_buruk.setSelected(false);
        
    }//GEN-LAST:event_btn_inputdataActionPerformed

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
            java.util.logging.Logger.getLogger(jInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jInventory inventory = new jInventory();
                inventory.setVisible(true);
                inventory.setLocationRelativeTo(null);
            }
        });
    }
    
    /**
     * Set up responsive components with proper minimum sizes and constraints
     */
    private void setupResponsiveComponents() {
        // Set minimum sizes for input form components
        txt_namaproduk.setMinimumSize(new Dimension(200, 30));
        txt_kodeproduk.setMinimumSize(new Dimension(200, 30));
        txt_quality.setMinimumSize(new Dimension(200, 30));
        
        // Set preferred sizes for better initial display
        jPanel1.setPreferredSize(new Dimension(1150, 700));
        jScrollPane1.setPreferredSize(new Dimension(600, 350));
        
        // Set form components to maintain proportions
        btn_inputdata.setMinimumSize(new Dimension(100, 40));
        btn_inputdata.setPreferredSize(new Dimension(104, 44));
        
        // Configure table for better display
        table_x.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table_x.setFillsViewportHeight(true);
        table_x.setRowHeight(25);
        table_x.getTableHeader().setReorderingAllowed(false);
        table_x.getTableHeader().setResizingAllowed(true);
        
        // Configure scroll pane for better table display
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Set border for better visual separation
        jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));
    }
    
    /**
     * Revalidates the layout based on the current window size
     * This method handles responsive layout adjustments when the window is resized
     */
    private void revalidateLayout() {
        // Get current window size
        Dimension currentSize = getSize();
        
        // Handle different window size categories based on breakpoints
        boolean isSmallWindow = currentSize.width < 900;
        boolean isMediumWindow = currentSize.width >= 900 && currentSize.width <= 1400;
        boolean isLargeWindow = currentSize.width > 1400;
        
        // Calculate dynamic padding based on window size and screen category
        int horizontalPadding;
        int verticalPadding;
        
        if (isSmallWindow) {
            horizontalPadding = Math.max(8, currentSize.width / 60);
            verticalPadding = Math.max(8, currentSize.height / 60);
        } else if (isLargeWindow) {
            horizontalPadding = Math.max(15, currentSize.width / 40);
            verticalPadding = Math.max(15, currentSize.height / 40);
        } else {
            horizontalPadding = Math.max(10, currentSize.width / 50);
            verticalPadding = Math.max(10, currentSize.height / 50);
        }
        
        // Determine if window is maximized
        boolean isMaximized = (getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
        
        // Adjust main panel size with dynamic padding based on window state
        if (isMaximized) {
            // When maximized, ensure panel uses all available space
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            jPanel1.setPreferredSize(new Dimension(
                screenSize.width - 50,
                screenSize.height - 100
            ));
        } else {
            // For normal window state, use dynamic padding
            jPanel1.setPreferredSize(new Dimension(
                Math.max(800, currentSize.width - (horizontalPadding * 2) - 10), 
                Math.max(500, currentSize.height - (verticalPadding * 2) - 50)
            ));
        }
        
        // Dynamic form section width based on window size
        int formSectionWidth;
        if (isSmallWindow) {
            formSectionWidth = Math.max(280, currentSize.width / 3);
        } else if (isLargeWindow) {
            formSectionWidth = Math.max(400, currentSize.width / 4);
        } else {
            formSectionWidth = Math.max(350, currentSize.width / 3);
        }
        
        // Adjust scroll pane size to take advantage of available space
        // Adjust scroll pane size to take advantage of available space
        int panelWidth = jPanel1.getWidth();
        // Calculate table width - ensure it has at least 40% of panel width for small windows
        if (isSmallWindow) {
            scrollPaneWidth = Math.max((int)(panelWidth * 0.4), panelWidth - formSectionWidth - horizontalPadding);
        } else {
            scrollPaneWidth = Math.max(400, panelWidth - formSectionWidth - (horizontalPadding * 2));
        }
        // Set scroll pane size with better proportions based on window size
        int scrollPaneHeight;
        if (isMaximized) {
            // For maximized windows, make the scroll pane fill most of the available height
            scrollPaneHeight = jPanel1.getHeight() - 40;
        } else {
            // For regular windows, calculate height based on vertical padding
            scrollPaneHeight = jPanel1.getHeight() - (verticalPadding * 2);
        }
        jScrollPane1.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
        
        // Adjust text field sizes based on form section width and window size category
        int textFieldWidth;
        if (isSmallWindow) {
            // For small windows, ensure text fields are not too narrow
            textFieldWidth = Math.max(180, formSectionWidth - 60);
        } else if (isLargeWindow) {
            // For large windows, give text fields more room
            textFieldWidth = Math.max(250, formSectionWidth - 100);
        } else {
            // For medium windows, use balanced sizing
            textFieldWidth = Math.max(200, formSectionWidth - 80);
        }
        
        // Set preferred sizes for text fields with consistent heights
        int textFieldHeight = 47; // Maintain consistent height for better alignment
        txt_namaproduk.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        txt_kodeproduk.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        txt_quality.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
        // Adjust table columns width with optimized handling
        if (table_x != null && table_x.getColumnCount() > 0) {
            int tableWidth = jScrollPane1.getWidth();
            int columnCount = table_x.getColumnCount();
            
            // Only adjust columns if table width has changed significantly
            // This avoids unnecessary column adjustments during minor resizes
            if (Math.abs(tableWidth - table_x.getWidth()) > 10) {
                // First, set all columns to auto resize
                table_x.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                
                // Set column widths proportionally
                if (columnCount > 0 && tableWidth > 0) {
                    // Define column width percentages - adjust dynamically based on table width
                    int[] columnWidthPercentage = new int[4];
                    int[] minColumnWidths = new int[4];
                    
                    if (tableWidth < 400) {
                        // For narrow tables, make columns more equal
                        columnWidthPercentage = new int[]{30, 25, 25, 20};
                        minColumnWidths = new int[]{80, 70, 70, 60}; // Minimum widths for narrow tables
                    } else if (tableWidth > 800) {
                        // For wide tables, optimize column distribution
                        columnWidthPercentage = new int[]{40, 22, 18, 20};
                        minColumnWidths = new int[]{150, 100, 80, 90}; // Minimum widths for wide tables
                    } else {
                        // For medium tables, give more space to name column
                        columnWidthPercentage = new int[]{35, 25, 20, 20};
                        minColumnWidths = new int[]{120, 90, 75, 75}; // Minimum widths for medium tables
                    }
                    
                    // First set minimum widths to ensure readability
                    for (int i = 0; i < Math.min(columnCount, minColumnWidths.length); i++) {
                        table_x.getColumnModel().getColumn(i).setMinWidth(minColumnWidths[i]);
                    }
                    
                    // Then set preferred widths for proportional sizing
                    for (int i = 0; i < Math.min(columnCount, columnWidthPercentage.length); i++) {
                        int colWidth = Math.max(
                            minColumnWidths[i],
                            (tableWidth * columnWidthPercentage[i]) / 100
                        );
                        table_x.getColumnModel().getColumn(i).setPreferredWidth(colWidth);
                    }
                }
            }
        }
        
        // Adjust viewport size for better table display
        jScrollPane1.getViewport().setPreferredSize(
            new Dimension(jScrollPane1.getWidth() - 5, jScrollPane1.getHeight() - 5)
        );

        // Set optimal table row height based on available space
        int visibleRowCount = Math.max(10, (jScrollPane1.getHeight() - 50) / 30);
        int optimalRowHeight = Math.min(30, (jScrollPane1.getHeight() - 50) / visibleRowCount);
        table_x.setRowHeight(Math.max(optimalRowHeight, 25)); // Minimum height of 25 pixels
        
        // Ensure table header is properly sized
        if (table_x.getTableHeader() != null) {
            table_x.getTableHeader().setPreferredSize(new Dimension(
                table_x.getTableHeader().getWidth(),
                Math.max(25, optimalRowHeight - 5)
            ));
        }
        // Apply smooth transitions for better user experience
        SwingUtilities.invokeLater(() -> {
            // Use a single revalidate/repaint cycle for better performance
            revalidate();
            repaint();
        });
    }
    private javax.swing.JMenuItem MenuEdit;
    private javax.swing.JMenuItem MenuInventory;
    private javax.swing.JMenuItem MenuLaporan;
    private javax.swing.JMenuItem MenuOpen;
    private javax.swing.JMenuItem MenuSave;
    private javax.swing.ButtonGroup bg_kondisi;
    private javax.swing.JButton btn_inputdata;
    private javax.swing.JMenu file_btn;
    private javax.swing.JMenu inputdata_btn;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rb_baik;
    private javax.swing.JRadioButton rb_buruk;
    private javax.swing.JRadioButton rb_sedang;
    private javax.swing.JTable table_x;
    private javax.swing.JTextField txt_kodeproduk;
    private javax.swing.JTextField txt_namaproduk;
    private javax.swing.JTextField txt_quality;
    // End of variables declaration                   
}
