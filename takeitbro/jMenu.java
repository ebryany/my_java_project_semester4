/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pertemuan_12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author febry
 */
public class jMenu extends javax.swing.JFrame {

    // Class fields for layout management
    private int scrollPaneWidth;
    
    /**
     * Creates new form jMenu
     */
    public jMenu() {
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
     * Set up responsive components with proper minimum sizes and constraints
     */
    private void setupResponsiveComponents() {
        // Set panel properties
        jPanel1.setPreferredSize(new Dimension(1150, 700));
        jPanel1.setMinimumSize(new Dimension(400, 200));
        jPanel1.setBackground(new Color(102, 102, 102));
        
        if (jLabel1 != null) {
            // Remove previous layout and set new one
            jPanel1.removeAll();
            jPanel1.setLayout(new GridBagLayout());
            
            // Configure label for better centering
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel1.setVerticalAlignment(SwingConstants.CENTER);
            jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel1.setVerticalTextPosition(SwingConstants.BOTTOM);
            jLabel1.setIconTextGap(50);  // Increase gap between icon and text
            
            // Set font with slightly larger size
            jLabel1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 42));  // Increase font size
            jLabel1.setForeground(new Color(255, 255, 255));  // Ensure text is white
            
            // Add slight text shadow for better visibility
            jLabel1.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            
            // Adjust icon size if needed
            if (jLabel1.getIcon() != null) {
                Image img = ((ImageIcon)jLabel1.getIcon()).getImage();
                Image newImg = img.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
                jLabel1.setIcon(new ImageIcon(newImg));
            }
            
            // Set size with adjusted dimensions
            Dimension labelSize = new Dimension(700, 600);  // Increase size slightly
            jLabel1.setPreferredSize(labelSize);
            
            // Add to panel with adjusted GridBagConstraints
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets = new Insets(50, 50, 50, 50);  // Increase padding
            
            jPanel1.add(jLabel1, gbc);
        }
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
        // Label sizing and positioning is handled in setupResponsiveComponents
        
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
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        x = new javax.swing.JMenu();
        MenuOpen = new javax.swing.JMenuItem();
        MenuSave = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuInventory = new javax.swing.JMenuItem();
        MenuLaporan = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        // Font is set in setupResponsiveComponents
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pertemuan_12/delivery-man (1).png"))); // NOI18N
        jLabel1.setText("Take It Bro");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        x.setText("File");

        MenuOpen.setText("Open");
        x.add(MenuOpen);

        MenuSave.setText("Save");
        MenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSaveActionPerformed(evt);
            }
        });
        x.add(MenuSave);

        MenuEdit.setText("Exit");
        MenuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEditActionPerformed(evt);
            }
        });
        x.add(MenuEdit);

        jMenuBar1.add(x);

        jMenu2.setText("Input Data");

        MenuInventory.setText("Inventory");
        MenuInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuInventoryActionPerformed(evt);
            }
        });
        jMenu2.add(MenuInventory);

        MenuLaporan.setText("Laporan");
        MenuLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLaporanActionPerformed(evt);
            }
        });
        jMenu2.add(MenuLaporan);

        jMenuBar1.add(jMenu2);

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
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuSaveActionPerformed

    private void MenuInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuInventoryActionPerformed
        // TODO add your handling code here:
        
        jInventory _inventory = new jInventory();
        _inventory.setVisible(true);
        _inventory.setLocationRelativeTo(null);
        this.dispose();
        
        
      JOptionPane.showMessageDialog(this, "Selamat Datang di Menu Inventory ", "Halo admin", JOptionPane.INFORMATION_MESSAGE);
        
        
    }//GEN-LAST:event_MenuInventoryActionPerformed

    private void MenuLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLaporanActionPerformed
        // TODO add your handling code here:
        
        jLaporan _laporan = new jLaporan();
        _laporan.setVisible(true);
        _laporan.setLocationRelativeTo(null);
        this.dispose();
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
            java.util.logging.Logger.getLogger(jMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            jMenu menu = new jMenu();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuEdit;
    private javax.swing.JMenuItem MenuInventory;
    private javax.swing.JMenuItem MenuLaporan;
    private javax.swing.JMenuItem MenuOpen;
    private javax.swing.JMenuItem MenuSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu x;
    // End of variables declaration//GEN-END:variables
}
