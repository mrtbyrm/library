package denemeee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JTextField;



public class ogrenciGoster{

	static JFrame frame = new JFrame("Öðrenci Gösterme");
	static JPanel panel = new JPanel();
	static JComboBox cbSecim = new JComboBox();
	static JTextField textSecim = new JTextField();
	static JTable tablo = new JTable();
	public static void OgrenciGoster(){
		
		frame.setSize(630, 400);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		panel.setSize(620, 400);
		panel.setLayout(null);
		frame.add(panel);
		
		JLabel label = new JLabel("Arama Türünüzü Seçiniz:");
		label.setSize(150, 20);
		label.setLocation(10, 5);
		panel.add(label);
		

		cbSecim.setSize(120, 20);
		cbSecim.setLocation(170, 5);
		cbSecim.removeAllItems();
		cbSecim.addItem("Numaraya Göre");
		cbSecim.addItem("Ad'a Göre");
		cbSecim.addItem("Bölüme Göre");
		panel.add(cbSecim);
		
		
		textSecim.setSize(150,20);
		textSecim.setLocation(300, 5);
		panel.add(textSecim);
		
		JButton goster = new JButton("Göster");
		
		goster.setSize(80,25);
		goster.setLocation(450, 3);
		panel.add(goster);
				
		JButton sil = new JButton("Sil");
		sil.setSize(80, 25);
		sil.setLocation(530, 3);
		panel.add(sil);
		
	
	    
	    
	   tablo.addMouseListener(new MouseAdapter() { //tabloda hangi satýra týklandýysa o satýrdaki verileri çekmek
		    public void mousePressed(MouseEvent e) {
		    	try{
		    	OgrenciEkle.textNo.setText(tablo.getValueAt(tablo.getSelectedRow(), 0).toString());
		    	/*OgrenciEkle.textAd.setText(tablo.getValueAt(tablo.getSelectedRow(), 1).toString());
		    	String fakulte = OgrenciEkle.cbFak.getSelectedItem().toString();
		    	fakulte = tablo.getValueAt(tablo.getSelectedRow(), 2).toString();
		    	String bolum = OgrenciEkle.cbBolum.getSelectedItem().toString();
		    	bolum = tablo.getValueAt(tablo.getSelectedRow(), 3).toString();
		    	String sinif = OgrenciEkle.cbSinif.getSelectedItem().toString();
		    	sinif = tablo.getValueAt(tablo.getSelectedRow(), 4).toString();
		    	String ogretim = OgrenciEkle.cbOgretim.getSelectedItem().toString();
		    	ogretim = tablo.getValueAt(tablo.getSelectedRow(), 5).toString();*/
		    	}catch(ArrayIndexOutOfBoundsException ex){
		    	}
		    }
		});
		
		tablo.setSize(630,360);
		tablo.setLocation(0,40);
	    panel.add(tablo);
	       
		goster.addActionListener(new ActionListener() { //göster butonuna týklandýðýnda çalýþacak olay
			  public void actionPerformed(ActionEvent evt) {
				  veriCekme.kayitGoster();
			  }
			});
		
		sil.addActionListener(new ActionListener() {  //Sil butonuna týklandýðýnda çalýþacak olay
			  @SuppressWarnings("null")
			public void actionPerformed(ActionEvent evt) {
				   Connection c = null;
			       Statement stmt = null;
			       try {
			    	 c = islemler.baglantiAc();
			         stmt = c.createStatement();
			         
			         String sql = "DELETE from ogrenci where numara= '"+OgrenciEkle.textNo.getText()+"';";
			         stmt.executeUpdate(sql);
			         c.commit();
			        
			         JOptionPane.showMessageDialog(null, "Kayýt silindi.");
			         veriCekme.kayitGoster();
			         stmt.close();
			         c.close();
			       } catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
			       }
			  }
			});
		
		frame.repaint();
	}
	
	
	
}
