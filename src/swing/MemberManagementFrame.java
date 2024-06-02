package swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MemberManagementFrame extends JFrame {
    private List<Member> members = new ArrayList<>();
    private JTextField tfName, tfTakenBookIDs, tfPhoneNumber, tfDeleteId, tfUpdateId;
    private JButton btnAdd, btnView, btnDelete, btnUpdate;

    public MemberManagementFrame() {
    	getContentPane().setBackground(new Color(128, 255, 255));
    	setTitle("Member Management");
        getContentPane().setLayout(new GridLayout(7, 2, 10, 10));
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadMembersFromFile(); 

        getContentPane().add(new JLabel("Name:"));
        tfName = new JTextField();
        getContentPane().add(tfName);

        getContentPane().add(new JLabel("Taken Book IDs:"));
        tfTakenBookIDs = new JTextField();
        getContentPane().add(tfTakenBookIDs);

        getContentPane().add(new JLabel("Phone Number:"));
        tfPhoneNumber = new JTextField();
        getContentPane().add(tfPhoneNumber);

        getContentPane().add(new JLabel("ID to Delete(Enter only if req.):"));
        tfDeleteId = new JTextField();
        getContentPane().add(tfDeleteId);
        
        getContentPane().add(new JLabel("ID to Update(Enter only if req.):"));
        tfUpdateId = new JTextField();
        getContentPane().add(tfUpdateId);

        btnAdd = new JButton("Add Member");
        btnAdd.setForeground(new Color(0, 0, 255));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });
        
        btnView = new JButton("View Members");
        btnView.setForeground(new Color(0, 0, 255));
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewMembers();
            }
        });
        
        btnDelete = new JButton("Delete Member");
        btnDelete.setForeground(new Color(0, 0, 255));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });
        
        btnUpdate = new JButton("Update Member");
        btnUpdate.setForeground(new Color(0, 0, 255));
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMember();
            }
        });

        getContentPane().add(btnAdd);
        getContentPane().add(btnView);
        getContentPane().add(btnDelete);
        getContentPane().add(btnUpdate);

        setVisible(true);
    }

    private void addMember() {
        int nextID = members.size() + 1;
        Member member = new Member(nextID, tfName.getText(), tfTakenBookIDs.getText(), tfPhoneNumber.getText());
        members.add(member);
        saveMembersToFile();
        JOptionPane.showMessageDialog(this, "Member added successfully!");
        clearFields();
    }

    private void viewMembers() {
        StringBuilder sb = new StringBuilder();
        for (Member member : members) {
            sb.append("ID: ").append(member.getId())
              .append(", Name: ").append(member.getName())
              .append(", Taken Book IDs: ").append(member.getTakenBookIDs())
              .append(", Phone: ").append(member.getPhoneNumber())
              .append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Member List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteMember() {
        try {
            int deleteId = Integer.parseInt(tfDeleteId.getText());
            members.removeIf(member -> member.getId() == deleteId);
            saveMembersToFile();
            JOptionPane.showMessageDialog(this, "Member deleted successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateMember() {
        try {
            int updateId = Integer.parseInt(tfUpdateId.getText());
            for (Member member : members) {
                if (member.getId() == updateId) {
                    member.setName(tfName.getText());
                    member.setEmail(tfTakenBookIDs.getText());
                    member.setPhoneNumber(tfPhoneNumber.getText());
                    saveMembersToFile();
                    JOptionPane.showMessageDialog(this, "Member updated successfully!");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveMembersToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter("members.txt"))) {
            for (Member member : members) {
                out.println(member.getId() + "," + member.getName() + "," + member.getTakenBookIDs() + "," + member.getPhoneNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMembersFromFile() {
        members.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("members.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]); 
                    Member member = new Member(id, data[1], data[2], data[3]);
                    members.add(member);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tfName.setText("");
        tfTakenBookIDs.setText("");
        tfPhoneNumber.setText("");
        tfDeleteId.setText("");
        tfUpdateId.setText("");
    }
}