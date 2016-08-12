package denemeee;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import denemeee.veriEkleme;

public class OgrenciEkle{

    static JFrame frame = new JFrame("Öðrenci Ekleme");
	
	static JPanel panel = new JPanel();
	
	static JComboBox<String> cbFak = new JComboBox<String>();
	static JComboBox<String> cbBolum = new JComboBox<String>();
	static JComboBox<String> cbSinif = new JComboBox<String>();
	static JComboBox<String> cbOgretim = new JComboBox<String>();
	static JTextField textNo = new JTextField();
	static JTextField textAd = new JTextField();
	static JTextField textTarih = new JTextField("YY/AA/GG");
	
	public static void main(String[]args){
		KayitEkle();
	}
	public static void KayitEkle(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		
		panel.setSize(400, 400);
		panel.setLayout(null);
		frame.add(panel);
		
		
		JLabel lblNo = new JLabel("Okul No : ");
		JLabel lblAd = new JLabel("Ad Soyad : ");
		JLabel lblFak = new JLabel("Fakülte : ");
		JLabel lblBolum = new JLabel("Bölüm : ");
		JLabel lblSinif = new JLabel("Sýnýf : ");
		JLabel lblOgretim = new JLabel("Öðretim Þekli : ");
		JLabel lblGirisTarihi = new JLabel("Okula Giriþ Tarihi :");
		
				
		panel.add(lblNo);
		panel.add(lblAd);
		panel.add(lblFak);
		panel.add(lblBolum);
		panel.add(lblSinif);
		panel.add(lblOgretim);
		panel.add(lblGirisTarihi);
		
		lblNo.setSize(100, 20);
		lblAd.setSize(100, 20);
		lblFak.setSize(100, 20);
		lblBolum.setSize(100, 20);
		lblSinif.setSize(100, 20);
		lblOgretim.setSize(100, 20);
		lblGirisTarihi.setSize(120,20);
		
		lblNo.setLocation(30, 30);
		lblAd.setLocation(30, 70);
		lblFak.setLocation(30, 110);
		lblBolum.setLocation(30, 150);
		lblSinif.setLocation(30, 190);
		lblOgretim.setLocation(30, 230);
		lblGirisTarihi.setLocation(30, 270);
			
		panel.add(textNo);
		panel.add(textAd);
		panel.add(cbFak);
		panel.add(cbBolum);
		panel.add(cbSinif);
		panel.add(cbOgretim);
		panel.add(textTarih);
		
		textTarih.setSize(200, 20);
		textNo.setSize(200, 20);
		textAd.setSize(200, 20);
		cbFak.setSize(200, 20);
		cbBolum.setSize(200, 20);
		cbSinif.setSize(200, 20);
		cbOgretim.setSize(200, 20);
		
		textNo.setLocation(140, 30);
		textAd.setLocation(140, 70);
		cbFak.setLocation(140, 110);
		cbBolum.setLocation(140, 150);
		cbSinif.setLocation(140, 190);
		cbOgretim.setLocation(140, 230);
		textTarih.setLocation(140, 270);
		
		textNo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (!(Character.isDigit(vChar)) || textNo.getText().length() == 12){
                    e.consume();
                }
            }
        });
		 
		 Statement stmt,stmt2,stmt3,stmt4;
		 ResultSet rs1,rs2,rs3,rs4;
		 Connection c = null;
		 String sql1 = "select * from fakulteler";
		 String sql2 = "select * from bolumler where bolum_fakulteid = 1";
		 String sql3 = "select * from bolumler where bolum_fakulteid = 2";
		 String sql4 = "select * from bolumler where bolum_fakulteid = 3";
         try {
        	c = islemler.baglantiAc();
        	stmt = c.createStatement();
        	stmt2 = c.createStatement();
        	stmt3 = c.createStatement();
        	stmt4 = c.createStatement();
			rs1 = stmt.executeQuery(sql1);
			rs2 = stmt2.executeQuery(sql2);
			rs3 = stmt3.executeQuery(sql3);
			rs4 = stmt4.executeQuery(sql4);
			
			cbFak.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent evt) {
					  if(cbFak.getSelectedIndex() == 0){
				        	 try {
				        		while(rs2.next()){
								cbBolum.addItem(rs2.getString("bolum_adi"));
				        		}
							 }catch (SQLException e1) {
								e1.printStackTrace();
							}
				         }
					  else if(cbFak.getSelectedIndex() == 1){
			    		try {
			        		while(rs3.next()){
							cbBolum.addItem(rs3.getString("bolum_adi"));
			        		}
						 }catch (SQLException e1) {
							e1.printStackTrace();
						}
			         }
					  else if(cbFak.getSelectedIndex() == 2){
			    		try {
			        		while(rs4.next()){
							cbBolum.addItem(rs4.getString("bolum_adi"));
			        		}
						 }catch (SQLException e1) {
							e1.printStackTrace();
						}
			         } 
				  }
			});
			
			while(rs1.next()){
	        	 cbFak.addItem(rs1.getString("fakulte_adi"));
	         }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
        cbSinif.removeAllItems();
		cbSinif.addItem("1.Sýnýf");
		cbSinif.addItem("2.Sýnýf");
		cbSinif.addItem("3.Sýnýf");
		cbSinif.addItem("4.Sýnýf");
		
		cbOgretim.removeAllItems();
		cbOgretim.addItem("I.Öðretim");
		cbOgretim.addItem("II.Öðretim");
		
		JButton button = new JButton("Kayýt et");
		button.setSize(100, 30);
		button.setLocation(140, 300);
		panel.add(button);
		
		
		
		button.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				 veriEkleme.ogrKayitEt(cbFak, cbBolum, cbSinif, cbOgretim, textNo, textAd,textTarih);
			  }
			});
		
		frame.repaint();
		
		
	}
	
}
