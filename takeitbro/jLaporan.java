/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pertemuan_12;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.TableModel;

/**
 *
 * @author febry
 */
public class jLaporan extends javax.swing.JFrame {

    // Class fields for layout management
    private int scrollPaneWidth;
    
    /**
     * Creates new form jLaporan
     */
    public jLaporan() {
        initComponents();
        
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
     * Set up responsive components with proper minimum sizes and constraints
     */
    private void setupResponsiveComponents() {
        // Set minimum sizes for any input form components
        // Adjust as needed for the specific components in jLaporan
        
        // Set preferred sizes for better initial display
        jPanel1.setPreferredSize(new Dimension(1150, 700));
        
        // If there's a table in the form, configure it
        // Configure table for better display
        if (table_laporan != null) {
            // Configure table for better display
            table_laporan.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            table_laporan.setFillsViewportHeight(true);
            table_laporan.setRowHeight(25);
            table_laporan.getTableHeader().setReorderingAllowed(false);
            table_laporan.getTableHeader().setResizingAllowed(true);
            // If there's a scroll pane, configure it
            if (jScrollPane1 != null) {
                jScrollPane1.setPreferredSize(new Dimension(600, 350));
                
                // Configure scroll pane for better table display
                jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                
                // Set border for better visual separation
                jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(new Color(200, 200, 200))
                ));
            }
        }
        
        // Set form buttons to maintain proportions
        // Adjust for specific buttons in jLaporan
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
        
        // Handle table if it exists
        if (table_laporan != null && jScrollPane1 != null) {
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
            
            // Adjust table columns width with optimized handling
            if (table_laporan.getColumnCount() > 0) {
                int tableWidth = jScrollPane1.getWidth();
                int columnCount = table_laporan.getColumnCount();
                
                // Only adjust columns if table width has changed significantly
                // This avoids unnecessary column adjustments during minor resizes
                if (Math.abs(tableWidth - table_laporan.getWidth()) > 10) {
                    // First, set all columns to auto resize
                    table_laporan.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                    
                    // Set column widths proportionally
                    if (columnCount > 0 && tableWidth > 0) {
                        // Define column width percentages - adjust dynamically based on table width
                        int[] columnWidthPercentage = new int[columnCount];
                        int[] minColumnWidths = new int[columnCount];
                        
                        // Set default values for all columns
                        for (int i = 0; i < columnCount; i++) {
                            columnWidthPercentage[i] = 100 / columnCount;
                            minColumnWidths[i] = 80;
                        }
                        
                        // Adjust based on table width
                        if (tableWidth < 400) {
                            // For narrow tables, make columns more equal
                            // Adjust as needed for the specific columns in jLaporan
                        } else if (tableWidth > 800) {
                            // For wide tables, optimize column distribution
                            // Adjust as needed for the specific columns in jLaporan
                        } else {
                            // For medium tables, balanced sizing
                            // Adjust as needed for the specific columns in jLaporan
                        }
                        
                        // First set minimum widths to ensure readability
                        for (int i = 0; i < columnCount; i++) {
                            table_laporan.getColumnModel().getColumn(i).setMinWidth(minColumnWidths[i]);
                        }
                        
                        // Then set preferred widths for proportional sizing
                        for (int i = 0; i < columnCount; i++) {
                            int colWidth = Math.max(
                                minColumnWidths[i],
                                (tableWidth * columnWidthPercentage[i]) / 100
                            );
                            table_laporan.getColumnModel().getColumn(i).setPreferredWidth(colWidth);
                        }
                    }
                }
                
                // Adjust viewport size for better table display
                jScrollPane1.getViewport().setPreferredSize(
                    new Dimension(jScrollPane1.getWidth() - 5, jScrollPane1.getHeight() - 5)
                );
                
                // Set optimal table row height based on available space
                int visibleRowCount = Math.max(10, (jScrollPane1.getHeight() - 50) / 30);
                // Calculate optimal row height
                int optimalRowHeight = Math.min(30, (jScrollPane1.getHeight() - 50) / visibleRowCount);
                table_laporan.setRowHeight(Math.max(optimalRowHeight, 25)); // Minimum height of 25 pixels
                // Ensure table header is properly sized
                if (table_laporan.getTableHeader() != null) {
                    table_laporan.getTableHeader().setPreferredSize(new Dimension(
                        table_laporan.getTableHeader().getWidth(),
                        Math.max(25, optimalRowHeight - 5)
                    ));
                }
            }
        }
        
        // Apply smooth transitions for better user experience
        SwingUtilities.invokeLater(() -> {
            // Use a single revalidate/repaint cycle for better performance
            revalidate();
            repaint();
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_laporan = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        file_btn = new javax.swing.JMenu();
        MenuOpen = new javax.swing.JMenuItem();
        MenuSave = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenuItem();
        inputdata_btn = new javax.swing.JMenu();
        MenuInventory = new javax.swing.JMenuItem();
        MenuLaporan = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Data Laporan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        table_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_laporan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
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
        inputdata_btn.add(MenuLaporan);

        jMenuBar1.add(inputdata_btn);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSaveActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if(!path.toLowerCase().endsWith(".csv")) {
                path += ".csv";
            }
            
            // Check if table has data to save
            if (table_laporan.getRowCount() == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Tidak ada data untuk disimpan!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            // Try to save the file and show appropriate message
            boolean saveSuccess = savetableToCsv(table_laporan, path);
            
            if (saveSuccess) {
                JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil disimpan ke file:\n" + path,
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
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
        
        jInventory _inventory = new jInventory();
        _inventory.setVisible(true);
        _inventory.setLocationRelativeTo(null);
        this.dispose();
        
        JOptionPane.showMessageDialog(this, "Selamagt datang di Menu Inventory ", "Halo admin", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_MenuInventoryActionPerformed

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
        
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            loadCSVToTable(path, table_laporan);
        }
    }//GEN-LAST:event_MenuOpenActionPerformed
    
    /**
     * Loads data from a CSV file into the specified table
     * 
     * @param path Path to the CSV file
     * @param table JTable to load the data into
     */
    private void loadCSVToTable(String path, JTable table) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            String line;
            boolean isFirstline = true;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (isFirstline) {
                    model.setColumnIdentifiers(data);
                    isFirstline = false;
                } else {
                    model.addRow(data);
                }
            }
            
            // Show success message
            JOptionPane.showMessageDialog(
                this,
                "Data berhasil dimuat dari file:\n" + path,
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Gagal membuka file: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Saves the data from the specified table to a CSV file
     * 
     * @param table JTable containing data to save
     * @param path File path where to save the CSV
     * @return boolean indicating success (true) or failure (false)
     */
    private boolean savetableToCsv(JTable table, String path) {
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
                for(int j = 0; j < model.getColumnCount(); j++) {
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
            java.util.logging.Logger.getLogger(jLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jLaporan laporan = new jLaporan();
                laporan.setVisible(true);
                laporan.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuEdit;
    private javax.swing.JMenuItem MenuInventory;
    private javax.swing.JMenuItem MenuLaporan;
    private javax.swing.JMenuItem MenuOpen;
    private javax.swing.JMenuItem MenuSave;
    private javax.swing.JMenu file_btn;
    private javax.swing.JMenu inputdata_btn;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_laporan;
    // End of variables declaration//GEN-END:variables
}
