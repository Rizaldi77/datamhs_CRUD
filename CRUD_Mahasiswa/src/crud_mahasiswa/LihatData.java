
package crud_mahasiswa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

public class LihatData extends JFrame{
    String[][] data = new String[480][3];//480 itu untuk banyaknya data yg bisa dimuat
    //data cuma variabel baru            //3 itu karna data yang dimasukkan ke database ada 3, nama nim alamat
    String[] kolom = {"NAMA","NIM","ALAMAT"}; //buat nama kolom di tabel outputnya nanti
    JTable tabel;
    JButton  btnBack;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;
    
    public LihatData() {
        setTitle("Data Mahasiswa!");
   
        btnBack = new JButton("Kembali");     
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        
        setLayout(new FlowLayout());
        add(scrollPane); add(btnBack);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               new formmhs();
            }

            //@Override
            public void actionPerformed() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        KoneksiDB koneksi = new KoneksiDB(); //untuk manggil kelas KoneksiDB nya
        try {            
            statement = koneksi.getKoneksi().createStatement();
            String sql = "select * from data_mhs "; //data_mhs sesuai tabel di database
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while (resultSet.next()) {
                data[p][0] = resultSet.getString("nama"); //nama nim alamatnya harus urut sesuai database
                data[p][1] = resultSet.getString("nim");
                data[p][2] = resultSet.getString("alamat");
                p++; //biar kalo ada data tambah dia otomatis nambah dibawah data pertama
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}