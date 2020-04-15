
package crud_mahasiswa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class formmhs extends JFrame{
    JLabel lnim,lnama,lalamat;
    JTextField txnim,txnama,txalamat;
    JButton cetak,lihat,btnUpdate,btnHapus;
    Statement statement;
    ResultSet resultSet;
    String[][] data = new String[500][3];
    
    public void tesformmhs (){
        
        setTitle("From Pengisian Mahasiswa");
        
        lnim = new JLabel("NIM");
        lnama = new JLabel("Nama");     
        lalamat = new JLabel("Alamat");
        
        txnim = new JTextField("");
        txnama = new JTextField("");
        txalamat = new JTextField("");
        
        cetak = new JButton("Cetak");
        lihat = new JButton("Lihat");
        
        btnUpdate = new JButton("Update");
        btnHapus = new JButton("Hapus");
        
        setLayout(null);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(txnim);
        add(txnama);
        add(txalamat);
        add(cetak);
        add(lihat);
        add(btnHapus);
        add(btnUpdate);
        
        lnim.setBounds(75, 50, 30, 20);
        lnama.setBounds(75, 75, 50, 20);
        lalamat.setBounds(75, 125, 50, 20);
        txnim.setBounds(150, 50, 150, 20);
        txnama.setBounds(150, 75, 150, 20);
        txalamat.setBounds(150, 125, 150, 100);
        cetak.setBounds(75, 230, 75, 20);
        lihat.setBounds(155, 230, 75, 20);
        btnUpdate.setBounds(235, 230, 75, 20);
        btnHapus.setBounds(315, 230, 75, 20);
                
        setSize(500,400); //untuk luas jendela

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        lihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new LihatData();
            }

            
            //@Override
            public void actionPerformed() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        cetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                int a1 =  Integer.parseInt(txnim.getText());
                String a2 = txnama.getText();
                String a3 = txalamat.getText();
//                String gender = jenis();
                        
                KoneksiDB koneksi = new KoneksiDB();
                    try {
                        statement = koneksi.getKoneksi().createStatement();
                        String sql = "INSERT INTO data_mhs VALUES ('"+ a2 + "','" + a1 + "','" + a3 + "')";
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(rootPane, "Data Tersimpan");
                        new LihatData();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(formmhs.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(formmhs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                System.out.println("NIM = "+a1);
                System.out.println("Nama = "+a2);
                System.out.println("Alamat"+a3);
                    
                } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(rootPane,"TIPE DATA SALAH");
                } catch (Error ext){
                 JOptionPane.showMessageDialog(rootPane,"SALAH");
                 
                }
            }
        });
            
            btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            new EditData();
            }
        });
        
        btnHapus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new HapusData();
            }
        });


        
        
    }
}
