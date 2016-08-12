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

public class egitimciEkle {
	public static void egitimciEkleme(){
		
	JFrame frame = new JFrame("Eðitimci ekleme");
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setSize(350,270);
	frame.setVisible(true);
	frame.setLayout(null);
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
	JLabel lblEgitimciAd,lblEgitimciYas,lblEgitimciGBT,lblEgitimciVDS,lblEgitimciBolumu;
	JTextField textEgitimciAd,textEgitimciYas,textEgitimciGBT,textEgitimciVDS;
	JComboBox bolumler = new JComboBox<>();
	JButton gonder = new JButton("Ekle");
	
	lblEgitimciAd = new JLabel("Eðitimcinin adý :");
	lblEgitimciYas = new JLabel("Eðitimcinin yaþý :");
	lblEgitimciGBT = new JLabel("Göreve baþlama t. :");
	lblEgitimciVDS = new JLabel("Verdiði ders sayýsý :");
	lblEgitimciBolumu = new JLabel("Eðitimcinin bölümü :");
	
	textEgitimciAd = new JTextField();
	textEgitimciYas = new JTextField();
	textEgitimciGBT = new JTextField();
	textEgitimciVDS = new JTextField();
	
	lblEgitimciAd.setBounds(15, 10, 120, 20);
	lblEgitimciYas.setBounds(15, 40, 120, 20);
	lblEgitimciGBT.setBounds(15, 70, 120, 20);
	lblEgitimciVDS.setBounds(15, 100, 120, 20);
	lblEgitimciBolumu.setBounds(15, 130, 120, 20);
	
	textEgitimciAd.setBounds(140, 10, 140, 20);
	textEgitimciYas.setBounds(140, 40, 140, 20);
	textEgitimciGBT.setBounds(140, 70, 140, 20);
	textEgitimciVDS.setBounds(140, 100, 140, 20);
	
	bolumler.setBounds(140, 130, 140, 20);
	
	gonder.setBounds(200,155 , 80, 25);
	
	frame.add(lblEgitimciAd);
	frame.add(lblEgitimciBolumu);
	frame.add(lblEgitimciGBT);
	frame.add(lblEgitimciVDS);
	frame.add(lblEgitimciYas);
	frame.add(textEgitimciAd);
	frame.add(textEgitimciGBT);
	frame.add(textEgitimciVDS);
	frame.add(textEgitimciYas);
	frame.add(bolumler);
	frame.add(gonder);
	
	Connection c;
	Statement stmt;
	ResultSet rs;
	try {
		c = islemler.baglantiAc();
		stmt = c.createStatement();
		rs = stmt.executeQuery("select * from bolumler");
		while(rs.next()){
       	 bolumler.addItem(rs.getString("bolum_adi"));
		}
		c.close();
		stmt.close();
		rs.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	gonder.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
			 try {
				int a = 0;
				Connection c;
				Statement stmt;
				ResultSet rs;
				c = islemler.baglantiAc();
				stmt = c.createStatement();

				
				rs = stmt.executeQuery("select * from bolumler where bolum_adi = '"+bolumler.getSelectedItem().toString()+"'");
				while(rs.next()){
					a = rs.getInt("id");
				}
				
				stmt.executeUpdate("insert into egitimci(egitimci_adsoyad,egitimci_yas,egitimci_gbt,egitimci_verdigi_ds,egitimci_bolumid)"
						+ "values('"+textEgitimciAd.getText()+"','"+textEgitimciYas.getText()+"','"+textEgitimciGBT.getText()+"','"+textEgitimciVDS.getText()+"','"+a+"')");
				JOptionPane.showMessageDialog(null, "Eðitmen baþarýyla eklendi");
				c.commit();
				c.close();
			    stmt.close();
			    rs.close();
			   
			 } catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		});
	
	frame.repaint();
	}
}
