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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class fakulteEkle {
	public static void fakulteEkleme(){
		JFrame frame = new JFrame("Fakülte Ekleme");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(350,270);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		JLabel lblFakulteAdi,lblFakulteDekani,lblFakulteOIB,lblFakulteAdres,lblFakulteTel;
		JTextField textFakulteAdi,textFakulteDekani,textFakulteOIB,textFakulteTel,textFakulteAdres;
	
		JButton gonder = new JButton("Ekle");
		
		lblFakulteAdi = new JLabel("Fakülte Adý :");
		lblFakulteDekani = new JLabel("Fakülte Dekaný :");
		lblFakulteOIB = new JLabel("Fakülte Öð.Ýþ.Baþ. :");
		lblFakulteTel = new JLabel("Fakülte Telefonu :");
		lblFakulteAdres = new JLabel("Fakülte Adresi :");
		
		
		textFakulteAdi = new JTextField();
		textFakulteDekani = new JTextField();
		textFakulteOIB = new JTextField();
		textFakulteTel = new JTextField();
		textFakulteAdres = new JTextField();
		
		lblFakulteAdi.setBounds(15, 10, 120, 20);
		lblFakulteDekani.setBounds(15, 40, 120, 20);
		lblFakulteOIB.setBounds(15, 70, 120, 20);
		lblFakulteTel.setBounds(15, 100, 120, 20);
		lblFakulteAdres.setBounds(15, 130, 120, 20);
		
		textFakulteAdi.setBounds(140, 10, 140, 20);
		textFakulteDekani.setBounds(140, 40, 140, 20);
		textFakulteOIB.setBounds(140, 70, 140, 20);
		textFakulteTel.setBounds(140, 100, 140, 20);
		textFakulteAdres.setBounds(140, 130, 140, 20);
		
		gonder.setBounds(200,160 , 80, 25);
		
		frame.add(lblFakulteAdi);
		frame.add(lblFakulteDekani);
		frame.add(lblFakulteOIB);
		frame.add(lblFakulteAdres);
		frame.add(lblFakulteTel);
		frame.add(textFakulteAdres);
		frame.add(textFakulteTel);
		frame.add(textFakulteAdi);
		frame.add(textFakulteDekani);
		frame.add(textFakulteOIB);
		frame.add(gonder);
		
		gonder.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				 try {
					Connection c;
					Statement stmt;
					c = islemler.baglantiAc();
					stmt = c.createStatement();
					stmt.executeUpdate("insert into fakulteler(fakulte_adi,fakulte_dekani,ogrenci_isleri_baskani,fakulte_adresi,fakulte_tel)"
							+ "values('"+textFakulteAdi.getText()+"','"+textFakulteDekani.getText()+"','"+textFakulteOIB.getText()+"','"+textFakulteAdres.getText()+"','"+textFakulteTel.getText()+"')");
					JOptionPane.showMessageDialog(null, "Fakülte baþarýyla eklendi");
					c.commit();
					c.close();
				    stmt.close();
				   
				 } catch (SQLException e) {
					e.printStackTrace();
				}
			  }
			});
		
	}
}
