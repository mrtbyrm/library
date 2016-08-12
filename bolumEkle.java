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

public class bolumEkle {

	public static void bolumEkleme(){
		JFrame frame = new JFrame("Bölüm ekleme");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(400,300);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		JTextField textBolumAd,textBolumKontenjan,textKurulus;
		JLabel lblBolumAd,lblBolumKontenjan,lblBolumBaskani,lblKurulus,lblFakulte;
		
		lblBolumAd = new JLabel("Bölüm adý : ");
		lblBolumKontenjan = new JLabel("Bölüm kontenjaný : ");
		lblBolumBaskani = new JLabel("Bölüm baþkaný : ");
		lblKurulus = new JLabel("Kuruluþ tarihi : ");
		lblFakulte = new JLabel("Fakülte");
		
		lblBolumAd.setSize(120,20);
		lblBolumBaskani.setSize(120, 20);
		lblBolumKontenjan.setSize(120, 20);
		lblKurulus.setSize(120,20);
		lblFakulte.setSize(120,20);
		
		lblBolumAd.setLocation(20,20);
		lblBolumBaskani.setLocation(20,50);
		lblBolumKontenjan.setLocation(20, 80);
		lblKurulus.setLocation(20, 110);
		lblFakulte.setLocation(20, 140);
		
		textBolumAd = new JTextField();
		textBolumKontenjan = new JTextField();
		textKurulus = new JTextField();
		
		textBolumAd.setSize(150, 20);
		textBolumKontenjan.setSize(150, 20);
		textKurulus.setSize(150, 20);
		
		textBolumAd.setLocation(130, 20);
		textBolumKontenjan.setLocation(130, 80);
		textKurulus.setLocation(130, 110);
		
		JComboBox bolumBaskani = new JComboBox();
		JComboBox fakulteler = new JComboBox();
		fakulteler.setSize(150, 20);
		fakulteler.setLocation(130, 140);
		bolumBaskani.setLocation(130, 50);
		bolumBaskani.setSize(150, 20);
		
		JButton gonder = new JButton("Bölüm Ekle");
		gonder.setSize(100, 25);
		gonder.setLocation(180, 170);
		
		
		Connection c;
		Statement stmt,stmt2;
		ResultSet rs,rs2;
		
		try {
			c = islemler.baglantiAc();
			stmt = c.createStatement();
			stmt2 = c.createStatement();
			rs = stmt.executeQuery("select * from fakulteler");
			rs2 = stmt2.executeQuery("select * from egitimci");
			
			while(rs.next()){
	       	 fakulteler.addItem(rs.getString("fakulte_adi"));
	        }
			while(rs2.next())
			{
			 bolumBaskani.addItem(rs2.getString("egitimci_adsoyad"));
			}
					
			c.close();
			stmt2.close();
			rs.close();
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		frame.add(lblFakulte);
		frame.add(lblBolumAd);
		frame.add(lblBolumBaskani);
		frame.add(lblBolumKontenjan);
		frame.add(lblKurulus);
		frame.add(textBolumAd);
		frame.add(bolumBaskani);
		frame.add(textBolumKontenjan);
		frame.add(textKurulus);
		frame.add(fakulteler);
		frame.add(gonder);
		
		gonder.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				 try {
					int a = 0;
					Connection c;
					Statement stmt3;
					ResultSet rs;
					c = islemler.baglantiAc();
					stmt3 = c.createStatement();
					
					rs = stmt3.executeQuery("select * from fakulteler where fakulte_adi = '"+fakulteler.getSelectedItem().toString()+"'");
					while(rs.next()){
						a = rs.getInt("id");
					}
					
					stmt3.executeUpdate("insert into bolumler(bolum_adi,kurulus_tarihi,bolum_kontenjan,bolum_baskani,bolum_fakulteid)"
				    + "values('"+textBolumAd.getText()+"','"+textKurulus.getText()+"','"+textBolumKontenjan.getText()+"','"+bolumBaskani.getSelectedItem().toString()+"','"+a+"')");
					JOptionPane.showMessageDialog(null, "Bölüm baþarýyla eklendi");
					c.commit();
					c.close();
				   stmt3.close();
	
				 } catch (SQLException e) {
					e.printStackTrace();
				}
			  }
			});
		
		
	}
}
