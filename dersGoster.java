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

public class dersGoster {
	static JTable tablo = new JTable();
	static JComboBox bolumler = new JComboBox();
	static int b = 0;
     public static void dersGosterme(){
    	 Connection c;
    	 Statement stmt;
    	 ResultSet rs;
    	 
    	 JFrame frame = new JFrame("Dersleri Göster");
    	 frame.setSize(600,400);
    	 frame.setLocationRelativeTo(null);
    	 frame.setLayout(null);
    	 frame.setVisible(true);
    	 
    	 JLabel lblBilgi = new JLabel("Bölüm seçiniz :");
    	 JLabel lblTablo1,lblTablo2,lblTablo3,lblTablo4,lblTablo5,lblTablo6,lblTablo7;
    	 lblBilgi.setSize(100,20);
    	 lblBilgi.setLocation(10, 10);
    	 
    	 lblTablo1 = new JLabel("Ad");
    	 lblTablo2 = new JLabel("Dönemi");
    	 lblTablo3 = new JLabel("Kontenjaný");
    	 lblTablo4 = new JLabel("Kredisi");
    	 lblTablo5 = new JLabel("Sýnýfý");
    	 lblTablo6 = new JLabel("Bölümü");
    	 lblTablo7 = new JLabel("Eðitmeni");
    	 
    	 lblTablo1.setBounds(5,34 , 76, 10);
    	 lblTablo2.setBounds(81,34 , 76, 10);
    	 lblTablo3.setBounds(157,34 , 76, 13);
    	 lblTablo4.setBounds(233,34 , 76, 10);
    	 lblTablo5.setBounds(309,34 , 76, 10);
    	 lblTablo6.setBounds(385, 34, 76, 10);
    	 lblTablo7.setBounds(461, 34, 76, 13);
    	 
    	 bolumler.setSize(180, 20);
    	 bolumler.setLocation(110, 10);
    	 
    	 JButton goster = new JButton("Göster");
    	 goster.setSize(80,25);
    	 goster.setLocation(300,7);
    	 
    	 try {
 			c = islemler.baglantiAc();
 			stmt = c.createStatement();
 			rs = stmt.executeQuery("select * from bolumler");
     	 while(rs.next()){
 	       	 bolumler.addItem(rs.getString("bolum_adi"));
 	        }
     	 
     	goster.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				   tablo = tabloDoldur();
				   
				   tablo.setSize(532, 300);
				   tablo.setLocation(5, 50);
				   frame.add(tablo);
				   
				   frame.repaint();
			  }
         });
     	
      	 frame.add(goster);
    	 frame.add(bolumler);
    	 frame.add(lblBilgi);
    	 frame.add(lblTablo1);
    	 frame.add(lblTablo2);
    	 frame.add(lblTablo3);
    	 frame.add(lblTablo4);
    	 frame.add(lblTablo5);
    	 frame.add(lblTablo6);
    	 frame.add(lblTablo7);
     	
     	 c.close();
     	 stmt.close();
     	 rs.close();
 		}
     	 catch (SQLException e) {
 			e.printStackTrace();
 		}
     }
     
     public static JTable tabloDoldur(){
    	 try (

    		 Connection c = islemler.baglantiAc();
    		 Statement stmt = c.createStatement();
    		 Statement stmt2 = c.createStatement();
    		 
    	     ResultSet rs = stmt.executeQuery("select * from bolumler where bolum_adi = '"+bolumler.getSelectedItem().toString()+"'")) {   	
    		 Object[] veri = new Object[7];
    		 
             DefaultTableModel model = new DefaultTableModel(new Object[]{"","asd","asd","asd","sad","",""},0);
				
             int a = 0;
				
				while(rs.next()){
					a = rs.getInt("id");
				}
			 ResultSet rs2 = stmt2.executeQuery("select * from egitimci where egitimci_bolumid = '"+a+"'");
	    		 while(rs2.next()){
	    			 b = rs2.getInt("id");
	    		 }	
		     ResultSet rs1 = stmt.executeQuery("select ders_adi,ders_donemi,ders_kontenjani,ders_kredi,ders_sinifi,bolum_adi,egitimci_adsoyad from dersler,bolumler,egitimci where ders_bolumid = '"+a+"' and bolumler.id =  '"+a+"' and egitimci.id = '"+b+"' and ders_bolumid = bolumler.id and bolumler.id = egitimci_bolumid ");
             
             while ( rs1.next()) {
                veri[0] = rs1.getString("ders_adi");
                veri[1] = rs1.getString("ders_donemi");
                veri[2] = rs1.getString("ders_kontenjani");
                veri[3] = rs1.getString("ders_kredi");
                veri[4] = rs1.getInt("ders_sinifi");
                veri[5] = rs1.getString("bolum_adi");
                veri[6] = rs1.getString("egitimci_adsoyad");
            	
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
