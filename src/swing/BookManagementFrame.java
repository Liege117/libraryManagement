package swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class BookManagementFrame extends JFrame {
    private List<Book> books = new ArrayList<>();
    private JTextField tfTitle, tfAuthor, tfPublisher, tfDeleteId, tfUpdateId;
    private JButton btnAdd, btnView, btnDelete, btnUpdate;

    public BookManagementFrame() {
    	getContentPane().setBackground(new Color(128, 255, 255));
        setTitle("Book Management");
        getContentPane().setLayout(new GridLayout(7, 2, 10, 10));
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadBooksFromFile();

        getContentPane().add(new JLabel("Title:"));
        tfTitle = new JTextField();
        getContentPane().add(tfTitle);

        getContentPane().add(new JLabel("Author:"));
        tfAuthor = new JTextField();
        getContentPane().add(tfAuthor);

        getContentPane().add(new JLabel("Publisher:"));
        tfPublisher = new JTextField();
        getContentPane().add(tfPublisher);

        getContentPane().add(new JLabel("ID to Delete(Enter only if req.):"));
        tfDeleteId = new JTextField();
        getContentPane().add(tfDeleteId);

        getContentPane().add(new JLabel("ID to Update(Enter only if req.):"));
        tfUpdateId = new JTextField();
        getContentPane().add(tfUpdateId);

        btnAdd = new JButton("Add Book");
        btnAdd.setForeground(new Color(0, 0, 255));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        
        btnView = new JButton("View Books");
        btnView.setForeground(new Color(0, 0, 255));
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBooks();
            }
        });
        
        btnDelete = new JButton("Delete Book");
        btnDelete.setForeground(new Color(0, 0, 255));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        
        btnUpdate = new JButton("Update Book");
        btnUpdate.setForeground(new Color(0, 0, 255));
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        getContentPane().add(btnAdd);
        getContentPane().add(btnView);
        getContentPane().add(btnDelete);
        getContentPane().add(btnUpdate);

        setVisible(true);
    }

    private void addBook() {
        int nextID = books.size() + 1;
        Book book = new Book(nextID, tfTitle.getText(), tfAuthor.getText(), tfPublisher.getText());
        books.add(book);
        saveBooksToFile();
        JOptionPane.showMessageDialog(this, "Book added successfully!");
        clearFields();
    }

    private void viewBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append("ID: ").append(book.getId())
              .append(", Title: ").append(book.getTitle())
              .append(", Author: ").append(book.getAuthor())
              .append(", Publisher: ").append(book.getPublisher())
              .append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Book List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteBook() {
        try {
            int deleteId = Integer.parseInt(tfDeleteId.getText());
            books.removeIf(book -> book.getId() == deleteId);
            saveBooksToFile();
            JOptionPane.showMessageDialog(this, "Book deleted successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBook() {
        try {
            int updateId = Integer.parseInt(tfUpdateId.getText());
            for (Book book : books) {
                if (book.getId() == updateId) {
                    book.setTitle(tfTitle.getText());
                    book.setAuthor(tfAuthor.getText());
                    book.setPublisher(tfPublisher.getText());
                    saveBooksToFile();
                    JOptionPane.showMessageDialog(this, "Book updated successfully!");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveBooksToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                out.println(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getPublisher());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksFromFile() {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String title = data[1];
                    String author = data[2];
                    String publisher = data[3];
                    Book book = new Book(id, title, author, publisher);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfPublisher.setText("");
        tfDeleteId.setText("");
        tfUpdateId.setText("");
    }
}