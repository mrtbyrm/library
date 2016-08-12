package denemeee;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JTextField;

public class dersEkle {

	public static void dersEkleme(){
		JFrame frame = new JFrame("Ders ekleme");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(350,300);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		JLabel lblDersAd,lblDersKontenjan,lblDersDonemi,lblDersEgitmeni,lblDersBolumu,lblDersKredi,lblDersSinifi;
		JTextField textDersAd,textDersKontenjan,textDersDonemi,textDersKredi;
		JComboBox egitmenler,bolumler,siniflar;
		JButton ekle = new JButton("Dersi Ekle");
		
		lblDersAd = new JLabel("Dersin adý : ");
		lblDersBolumu = new JLabel("Bölümü : ");
		lblDersDonemi = new JLabel("Dersin dönemi : ");
		lblDersEgitmeni = new JLabel("Dersin eðitmeni : ");
		lblDersKontenjan = new JLabel("Dersin kontenjaný : ");
		lblDersKredi = new JLabel("Dersin kredisi : ");
		lblDersSinifi = new JLabel("Dersin Sýnýfý : ");
		
		textDersAd = new JTextField();
		textDersDonemi = new JTextField();
		textDersKontenjan = new JTextField();
		textDersKredi = new JTextField();
		
		egitmenler = new JComboBox<>();
		bolumler = new JComboBox<>();
		siniflar = new JComboBox<>();
		
		lblDersAd.setBounds(15, 10, 120, 20);
		lblDersDonemi.setBounds(15, 40, 120, 20);
		lblDersKontenjan.setBounds(15, 70, 120, 20);
		lblDersKredi.setBounds(15, 100, 120, 20);
		lblDersBolumu.setBounds(15, 160, 120, 20);
		lblDersEgitmeni.setBounds(15, 130, 120, 20);
		lblDersSinifi.setBounds(15, 190, 120, 20);
		
		textDersAd.setBounds(140, 10, 140, 20);
		textDersDonemi.setBounds(140, 40, 140, 20);
		textDersKontenjan.setBounds(140, 70, 140, 20);
		textDersKredi.setBounds(140, 100, 140, 20);
		
		egitmenler.setBounds(140, 130, 140, 20);
		bolumler.setBounds(140, 160, 140, 20);
		siniflar.setBounds(140, 190, 140, 20);
		
		siniflar.removeAllItems();
		siniflar.addItem("1");
		siniflar.addItem("2");
		siniflar.addItem("3");
		siniflar.addItem("4");
		
		ekle.setBounds(140, 220, 140, 25);
		
		
		frame.add(ekle);
		frame.add(lblDersAd);
		frame.add(lblDersBolumu);
		frame.add(lblDersEgitmeni);
		frame.add(lblDersDonemi);
		frame.add(lblDersKredi);
		frame.add(lblDersKontenjan);
		frame.add(textDersAd);
		frame.add(textDersDonemi);
		frame.add(textDersKontenjan);
		frame.add(textDersKredi);
		frame.add(egitmenler);
		frame.add(bolumler);
		frame.add(lblDersSinifi);
		frame.add(siniflar);
		
		Connection c;
		Statement stmt,stmt2;
		ResultSet rs,rs2;
		try {

			c = islemler.baglantiAc();
			stmt = c.createStatement();
			stmt2 = c.createStatement();
			rs = stmt.executeQuery("select * from bolumler");
			rs2 = stmt2.executeQuery("select * from egitimci");
			
			while(rs.next()){
	       	 bolumler.addItem(rs.getString("bolum_adi"));
	        }
			while(rs2.next())
			{
			 egitmenler.addItem(rs2.getString("egitimci_adsoyad"));
			}
			

			c.close();
			stmt.close();
			rs.close();
			rs2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ekle.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				 try {
					int a = 0,b = 0;
					Connection c;
					Statement stmt,stmt1;
					ResultSet rs,rs1;
					c = islemler.baglantiAc();
					stmt = c.createStatement();
					stmt1 = c.createStatement();
					
					rs = stmt.executeQuery("select * from bolumler where bolum_adi = '"+bolumler.getSelectedItem().toString()+"'");
					while(rs.next()){
						a = rs.getInt("id");
					}
					
					rs1 = stmt1.executeQuery("select * from egitimci where egitimci_adsoyad = '"+egitmenler.getSelectedItem().toString()+"'");
					while(rs1.next()){
						b = rs1.getInt("id");
					}
					
					stmt.executeUpdate("insert into dersler(ders_adi,ders_donemi,ders_kontenjani,ders_egitmenid,ders_bolumid,ders_kredi,ders_sinifi)"
				    + "values('"+textDersAd.getText()+"','"+textDersDonemi.getText()+"','"+textDersKontenjan.getText()+"','"+b+"','"+a+"','"+textDersKredi.getText()+"','"+siniflar.getSelectedItem().toString()+"')");
					JOptionPane.showMessageDialog(null, "Ders baþarýyla eklendi");
					c.commit();
					c.close();
				   stmt.close();
				   
				 } catch (SQLException e) {
					e.printStackTrace();
				}
			  }
			});
		
		frame.repaint();
	}
	
}
