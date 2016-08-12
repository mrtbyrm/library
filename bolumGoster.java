package denemeee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class bolumGoster {
	 static JTable tablo = new JTable();
	 static JComboBox fakulteler = new JComboBox();
     public static void bolumGosterme(){
    	 Connection c;
    	 Statement stmt;
    	 ResultSet rs;
    	 
    	 JFrame frame = new JFrame("Bölümleri Göster");
    	 frame.setSize(400,400);
    	 frame.setLocationRelativeTo(null);
    	 frame.setLayout(null);
    	 frame.setVisible(true);
    	 
    	 JLabel lblBilgi = new JLabel("Fakülte seçiniz :");
    	 JLabel lblTablo1,lblTablo2,lblTablo3,lblTablo4;
    	 lblBilgi.setBounds(10, 10, 100, 20);
    	 
    	 lblTablo1 = new JLabel("Bölüm Adý");
    	 lblTablo2 = new JLabel("Eðitmen Adý");
    	 lblTablo3 = new JLabel("Kontenjan");
    	 lblTablo4 = new JLabel("Kuruluþ Tarihi");
    	 
    	 lblTablo1.setBounds(37, 38, 75	, 13);
    	 lblTablo2.setBounds(112, 38, 75, 13);
    	 lblTablo3.setBounds(187, 38, 75, 13);
    	 lblTablo4.setBounds(262, 38, 90, 13);
    	 
    	
    	 fakulteler.setSize(180, 20);
    	 fakulteler.setLocation(110, 10);
    	 
    	 JButton goster = new JButton("Göster");
    	 goster.setSize(80,25);
    	 goster.setLocation(300,7);
    	 
    	 
    	 try {
			c = islemler.baglantiAc();
			stmt = c.createStatement();
			rs = stmt.executeQuery("select * from fakulteler");
			fakulteler.removeAllItems();
    	 while(rs.next()){
	       	 fakulteler.addItem(rs.getString("fakulte_adi"));
	        }
    	 
    	 c.close();
    	 stmt.close();
    	 rs.close();
		}
    	 catch (SQLException e) {
			e.printStackTrace();
		}
			
           goster.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				   tablo = tabloDoldur();
				   tablo.setSize(300, 350);
				   tablo.setLocation(37, 55);
				   frame.add(tablo);
				   
				   frame.repaint();
			  }
           });
    	 frame.add(goster);
    	 frame.add(fakulteler);
    	 frame.add(lblBilgi);
    	 frame.add(lblTablo1);
    	 frame.add(lblTablo2);
    	 frame.add(lblTablo3);
    	 frame.add(lblTablo4);

     }
     
     public static JTable tabloDoldur(){
    	 try {
    		 Connection c = islemler.baglantiAc();
    		 Statement stmt = c.createStatement();
    		 
    	     ResultSet rs = stmt.executeQuery("select * from fakulteler where fakulte_adi = '"+fakulteler.getSelectedItem().toString()+"'"); 
    		 Object[] veri = new Object[7];
             DefaultTableModel model = new DefaultTableModel(new Object[]{"","","",""},0);
				int a = 0;
				while(rs.next()){
					a = rs.getInt("id");
				}
				
		     ResultSet rs1 = stmt.executeQuery("select * from bolumler where bolum_fakulteid = '"+a+"'");

             while ( rs1.next()) {
                veri[0] = rs1.getString("bolum_adi");
                veri[1] = rs1.getString("bolum_baskani");
                veri[2] = rs1.getString("bolum_kontenjan");
                veri[3] = rs1.getString("kurulus_tarihi");

                tablo.setModel(model);
                model.addRow(veri);
             }
             if(model.getRowCount() == 0){
             	tablo.hide();
             	JOptionPane.showMessageDialog(null,"Böyle bir kayýt yok");
             } 
             else
             	tablo.show();

             c.close();
             stmt.close();
             rs.close();
             rs1.close();
         }catch(SQLException e){
        	 
          }
      return tablo; 
     }
	
}
