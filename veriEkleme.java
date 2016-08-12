package denemeee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class veriEkleme{

   public static void ogrKayitEt(JComboBox cbFak,JComboBox cbBolum,JComboBox cbSinif,JComboBox cbOgretim,JTextField textNo,JTextField textAd,JTextField textTarih){
	      Connection c = null;
	      Statement stmt = null;
	      ResultSet rs,rs2;
	      
	      
	   
	      try {
	         
	         if(textNo.getText().equals("") || textAd.getText().equals("")){
	        	 JOptionPane.showMessageDialog(null, "Kayýt baþarýsýz");
	         return;
	         }
	         else
	        	 JOptionPane.showMessageDialog(null, "Öðrenciyi baþarýyla kayýt ettiniz.");
	         
	         c = islemler.baglantiAc();
	         stmt = c.createStatement();
	         int a = 0,b=0;

         	 String sql1 = "select * from fakulteler where fakulte_adi = '"+cbFak.getSelectedItem().toString()+"'";
					rs = stmt.executeQuery(sql1);
					while(rs.next()){
						a = rs.getInt("id");
					}
			 String sql2 = "select * from bolumler where bolum_adi = '"+cbBolum.getSelectedItem().toString()+"'";
			        rs2 = stmt.executeQuery(sql2);
				    while(rs2.next()){
					    b = rs2.getInt("id");
				    }
			 
	         String sql = "INSERT INTO ogrenci(numara,adsoyad,sinif,ogretim,fakulteid,bolumid,giris_tarihi) "
	  	      + "VALUES ('"+textNo.getText()+"','"+textAd.getText()+"','"+cbSinif.getSelectedItem().toString()+"','"+cbOgretim.getSelectedItem().toString()+"','"+a+"','"+b+"','"+textTarih.getText()+"');";
	         stmt.executeUpdate(sql);
	         c.commit();
	         
	         
	         stmt.close();
	         c.close();
	      }catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
   }
}