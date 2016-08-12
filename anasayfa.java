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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import denemeee.ogrenciGoster;

public class anasayfa{

	static JComboBox bolumler;
	
	public static void main(String[]args){
		JFrame frame = new JFrame("�niversite Kay�t Sistemi");
		frame.setSize(488, 250);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);


		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 600, 20);
		frame.add(menuBar);
		
		
		JMenu menuOgr = new JMenu("�grenci ��lemleri");
		menuBar.add(menuOgr);	
		JMenuItem menuOgrEkle = new JMenuItem("�grenci Ekle");	
        menuOgr.add(menuOgrEkle);	
        
		JMenuItem menuOgrGoster = new JMenuItem("��rencileri G�ster");
		menuOgr.add(menuOgrGoster);
		
		JMenu menuBolum = new JMenu("B�l�m ��lemleri");
		menuBar.add(menuBolum);
		
		JMenuItem menuBolumEkle = new JMenuItem("B�l�m Ekle");
		menuBolum.add(menuBolumEkle);
		
		JMenuItem menuBolumGoster = new JMenuItem("B�l�m G�ster");
		menuBolum.add(menuBolumGoster);
		
		JMenu menuDers = new JMenu("Ders ��lemleri");
		menuBar.add(menuDers);
		
		JMenuItem menuDersEkle = new JMenuItem("Ders Ekle");
		menuDers.add(menuDersEkle);
		
		JMenuItem menuDersGoster = new JMenuItem("Ders G�ster");
		menuDers.add(menuDersGoster);
		
		JButton egitimciEkleme = new JButton("E�itimci Ekle");
		JButton egitimciGosterme = new JButton("E�itimci G�ster");
		JButton fakulteEkleme = new JButton("Fakulte Ekle");
		bolumler = new JComboBox();
		JLabel lblBilgi = new JLabel("B�l�m se�iniz :");
		lblBilgi.setBounds(10, 25, 90, 20);
		bolumler.setBounds(110, 25, 140	, 25);

		egitimciEkleme.setBounds(373, 25, 110, 25);
		egitimciGosterme.setBounds(255, 25, 120, 25);
		fakulteEkleme.setBounds(10, 55, 105, 25);
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		frame.add(egitimciGosterme);
		frame.add(egitimciEkleme);
		frame.add(fakulteEkleme);
		frame.add(lblBilgi);
		frame.add(bolumler);
	
		menuOgrEkle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OgrenciEkle.KayitEkle();
			}
		});
		
		menuOgrGoster.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ogrenciGoster.OgrenciGoster();
			}
		});
		
		menuBolumEkle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bolumEkle.bolumEkleme();
			}
		});
		
		menuBolumGoster.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bolumGoster.bolumGosterme();
			}
		});
		
		menuDersEkle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dersEkle.dersEkleme();
			}
		});
		
		menuDersGoster.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dersGoster.dersGosterme();
			}
		});
		
		egitimciEkleme.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				egitimciEkle.egitimciEkleme();
			}
		});
		
		egitimciGosterme.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				egitimciGoster.egitimciGosterme();
			}
		});
		
		fakulteEkleme.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			     fakulteEkle.fakulteEkleme();
			}
		});
		frame.repaint();
	}
}
