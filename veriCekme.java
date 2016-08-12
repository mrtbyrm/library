package denemeee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;


public class veriCekme{

    public static void kayitGoster(){

	   Connection c = null;	  
	   ResultSet rs,rs1;
	   String sql = "";
	   int a = 0,b = 0;
		    try{
			c = islemler.baglantiAc();
			Statement stmt = c.createStatement(),stmt1 = c.createStatement();
				
				if(ogrenciGoster.cbSecim.getSelectedItem().toString().equals("Numaraya Göre")){
				sql = "select * from ogrenci where numara = '"+ogrenciGoster.textSecim.getText()+"'";
				}
				
				else if(ogrenciGoster.cbSecim.getSelectedItem().toString().equals("Ad'a Göre")){
				sql = "select * from ogrenci where adsoyad = '"+ogrenciGoster.textSecim.getText()+"'";
				}
				
				else if(ogrenciGoster.cbSecim.getSelectedItem().toString().equals("Bölüme Göre"))
				{
				sql = "select * from bolumler where bolum_adi = '"+ogrenciGoster.textSecim.getText()+"'"; 
				rs1 = stmt1.executeQuery(sql);
				
				while(rs1.next()){
					a = rs1.getInt("id");
				}
				
				sql = "select * from ogrenci where bolumid = '"+a+"'";
				}
				
				rs = stmt.executeQuery(sql);
					
         Object[] veri = new Object[7];
         DefaultTableModel model = new DefaultTableModel(new Object[]{"numara","adsoyad","sinif","ogretim","giris_tarihi"},0);

         while ( rs.next()) {
            veri[0] = rs.getString("numara");
            veri[1] = rs.getString("adsoyad");
            veri[2] = rs.getString("sinif");
            veri[3] = rs.getString("ogretim");
            veri[4] = rs.getDate("giris_tarihi");
            ogrenciGoster.tablo.setModel(model);
            model.addRow(veri);
         }
        /* while(rs2.next())
         {
        	 veri[5] = rs2.getString("fakulte_adi");
        	 ogrenciGoster.tablo.setModel(model);
             model.addRow(veri);
         }
         while(rs3.next()){
        	 veri[6] = rs3.getString("bolum_adi");
        	 ogrenciGoster.tablo.setModel(model);
             model.addRow(veri);
         }*/
        if(model.getRowCount() == 0){
        	ogrenciGoster.tablo.hide();
        	JOptionPane.showMessageDialog(null,"Böyle bir kayýt yok");
        } 
        else
        	ogrenciGoster.tablo.show();
        	 
         rs.close();
         stmt1.close();
         stmt.close();
         c.close();
         
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
}
   
   





