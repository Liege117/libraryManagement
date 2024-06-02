package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DashboardFrame extends JFrame implements ActionListener {
    private JButton btnManageBooks, btnManageMembers;

    public DashboardFrame() {
    	getContentPane().setBackground(new Color(128, 255, 255));
        setTitle("Library Management Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(2, 1, 10, 10));

        btnManageBooks = new JButton("Manage Books");
        btnManageBooks.setForeground(new Color(0, 0, 255));
        btnManageBooks.setBackground(new Color(255, 255, 255));
        
        btnManageMembers = new JButton("Manage Members");
        btnManageMembers.setForeground(new Color(0, 0, 255));
        btnManageMembers.setBackground(new Color(255, 255, 255));

        btnManageBooks.addActionListener(this);
        btnManageMembers.addActionListener(this);

        getContentPane().add(btnManageBooks);
        getContentPane().add(btnManageMembers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnManageBooks) {
            JOptionPane.showMessageDialog(this, "Book Management Panel");
            // Implement book management functionality
            BookManagementFrame bookmanagement = new BookManagementFrame();
            bookmanagement.setVisible(true);
        } else if (e.getSource() == btnManageMembers) {
            JOptionPane.showMessageDialog(this, "Member Management Panel");
            // Implement member management functionality
            MemberManagementFrame membermanagement = new MemberManagementFrame();
            membermanagement.setVisible(true);
        }
    }
}