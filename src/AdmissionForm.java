package src;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdmissionForm {

    public static void main(String[] args) {

        JFrame f = new JFrame("College Admission Form");

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(20,20,100,30);
        JTextField t1 = new JTextField();
        t1.setBounds(140,20,180,30);

        JLabel l2 = new JLabel("Father Name:");
        l2.setBounds(20,60,100,30);
        JTextField t2 = new JTextField();
        t2.setBounds(140,60,180,30);

        JLabel l3 = new JLabel("Email:");
        l3.setBounds(20,100,100,30);
        JTextField t3 = new JTextField();
        t3.setBounds(140,100,180,30);

        JLabel l4 = new JLabel("Phone:");
        l4.setBounds(20,140,100,30);
        JTextField t4 = new JTextField();
        t4.setBounds(140,140,180,30);

        JLabel l5 = new JLabel("Course:");
        l5.setBounds(20,180,100,30);
        JTextField t5 = new JTextField();
        t5.setBounds(140,180,180,30);

        JButton btn = new JButton("Submit");
        btn.setBounds(120,230,100,35);

        f.add(l1); f.add(t1);
        f.add(l2); f.add(t2);
        f.add(l3); f.add(t3);
        f.add(l4); f.add(t4);
        f.add(l5); f.add(t5);
        f.add(btn);

        f.setSize(380,330);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BUTTON ACTION
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String name = t1.getText();
                String father = t2.getText();
                String email = t3.getText();
                String phone = t4.getText();
                String course = t5.getText();

                try{
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/admission_db",
                        "root",
                        ""
                    );

                    String query = "INSERT INTO students(name,father_name,email,phone,course) VALUES (?,?,?,?,?)";

                    PreparedStatement pst = con.prepareStatement(query);

                    pst.setString(1,name);
                    pst.setString(2,father);
                    pst.setString(3,email);
                    pst.setString(4,phone);
                    pst.setString(5,course);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(f,"Data Saved Successfully ✅");

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(f,ex);
                }
            }
        });
    }
}
