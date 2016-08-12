package denemeee;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class egitimciGoster {
	static int a =0;
	
	public static void egitimciGosterme(){
	 Connection c;
   	 Statement stmt,stmt3,stmt2;
   	 ResultSet rs,rs3;
   	 try{
   		 c = islemler.baglantiAc();
   		 stmt = c.createStatement();
   		 stmt3 = c.createStatement();
   		 stmt2 = c.createStatement();
   		 
   		 
		 rs3 = stmt3.executeQuery("select * from bolumler where bolum_adi = '"+anasayfa.bolumler.getSelectedItem().toString()+"'");
		 int a = 0;
			
			while(rs3.next()){
				a = rs3.getInt("id");
			}
	     rs = stmt.executeQuery("select egitimci_adsoyad,egitimci_yas,egitimci_gbt,egitimci_verdigi_ds,bolum_adi from egitimci,bolumler where egitimci_bolumid = '"+a+"' and bolumler.id = '"+a+"'and bolumler.id = egitimci_bolumid");
   		 ResultSetMetaData rsmt = rs.getMetaData();
   		 int r = rsmt.getColumnCount();
   		 Vector sutun = new Vector(r);
   		 sutun.add("E�itmen Ad�");
   		 sutun.add("E�itmen Ya��");
   		 sutun.add("G�reve ba�lama");
   		 sutun.add("Verdi�i ders say.");
   		 sutun.add("B�l�m�");
   		 Vector veri = new Vector();
   		 Vector satir = new Vector();
   		 
   		 
   		 while(rs.next()){
   			 satir = new Vector(r);
   			 for(int i = 1;i<=r;i++){
   				 satir.add(rs.getString(i));
   			 }
   			 veri.add(satir);
   		 }
		 
       	 JFrame frame = new JFrame("E�itimci G�sterme");
       	 frame.setSize(400,400);
       	 frame.setLocationRelativeTo(null);
       
       	 
       	
       	 JPanel panel = new JPanel();
       	 JTable tablo = new JTable(veri,sutun);
       	 JScrollPane pane = new JScrollPane(tablo);
       	 panel.setLayout(new BorderLayout());
       	 panel.add(pane,BorderLayout.CENTER);
       	 frame.add(panel);
       	 frame.setVisible(true);
       	 
       	JPopupMenu popupMenu = new JPopupMenu();
       	JMenuItem duzenle = new JMenuItem("D�zenle");
       	JMenuItem sil = new JMenuItem("Sil");

       	popupMenu.add(duzenle);
       	popupMenu.add(sil);
       	
		    	try{
		    		duzenle.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent evt) {
		    	    	 JFrame frame = new JFrame("E�itimci D�zenle");
		    	       	 frame.setSize(400,400);
		    	       	 frame.setLocationRelativeTo(null);
		    	       	 frame.setLayout(null);
		    	       	 frame.setVisible(true);
		    	       	 
		    	       	JLabel lblAd = new JLabel("G�ncel Ad :");
		    	       	JLabel lblYas = new JLabel("G�ncel Ya� :");
		    	       	JLabel lblGiris = new JLabel("G�ncel Giri� Tarihi :");
		    	       	JLabel lblDS = new JLabel("G�ncel Ders Say�s�");
		    	       	JLabel lblBolumu = new JLabel("G�ncel B�l�m :");
		    	       	       	
		    	       	JTextField textAd = new JTextField(tablo.getValueAt(tablo.getSelectedRow(), 0).toString());
		    	       	JTextField textYas = new JTextField(tablo.getValueAt(tablo.getSelectedRow(), 1).toString());
		    	       	JTextField textGiris = new JTextField(tablo.getValueAt(tablo.getSelectedRow(), 2).toString());
		    	       	JTextField textDS = new JTextField(tablo.getValueAt(tablo.getSelectedRow(), 3).toString());
		    	       	JTextField textBolumu = new JTextField(tablo.getValueAt(tablo.getSelectedRow(), 4).toString());
		    	       	JButton guncel = new JButton("G�ncelle");
		    	       	       	
		    	       	lblAd.setBounds(10, 20, 120, 20);
		    	       	lblYas.setBounds(10, 50, 120, 20);
		    	       	lblGiris.setBounds(10, 80, 120, 20);
		    	       	lblDS.setBounds(10, 110, 120, 20);
		    	       	lblBolumu.setBounds(10, 140, 120, 20);
		    	       	
		    	       	textAd.setBounds(170, 20, 140, 20);
		    	       	textYas.setBounds(170, 50, 140, 20);
		    	       	textGiris.setBounds(170, 80, 140, 20);
		    	       	textDS.setBounds(170, 110, 140, 20);
		    	       	textBolumu.setBounds(170, 140, 140, 20);
		    	       	guncel.setBounds(170, 170, 100, 25);
		    	       	
		    	       	frame.add(textBolumu);
		    	       	frame.add(textDS);
		    	       	frame.add(textGiris);
		    	       	frame.add(textYas);
		    	       	frame.add(textAd);
		    	       	frame.add(guncel);
		    	       	frame.add(lblAd);
		    	       	frame.add(lblBolumu);
		    	       	frame.add(lblDS);
		    	       	frame.add(lblGiris);
		    	       	frame.add(lblYas);
		    	       	
		    	       	frame.repaint();
		    	       	
		    	       
						try {
							Connection c;
							Statement stmt;
							ResultSet rs;
							c = islemler.baglantiAc();
							stmt = c.createStatement();
							rs = stmt.executeQuery("select * from egitimci where egitimci_adsoyad = '"+textAd.getText()+"'");
							while(rs.next()){
								egitimciGoster.a = rs.getInt("id");
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
		    	       	
		    	       	guncel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Connection c;
								Statement stmt;
								try{
									c = islemler.baglantiAc();
									stmt = c.createStatement();					
									stmt.executeUpdate("update egitimci set egitimci_adsoyad = '"+textAd.getText()+"',egitimci_yas = '"+textYas.getText()+""
											+ "',egitimci_gbt = '"+textGiris.getText()+"', egitimci_verdigi_ds = '"+textDS.getText()+"'"
													+ "where id = '"+egitimciGoster.a+"'");
									JOptionPane.showMessageDialog(null, "Kayd�n�z G�ncellendi");
									c.commit();
									
								}catch(Exception ex){
	
								}
							}
						});
		    	    	
		    	    }
		    		});
		    		
		    		sil.addActionListener(new ActionListener() {
			    	    public void actionPerformed(ActionEvent evt) {
			    	    	Connection c;
			    	    	Statement stmt;
			    	    	try {
								c = islemler.baglantiAc();
								stmt = c.createStatement();
								stmt.executeUpdate("delete from egitimci where egitimci_adsoyad = '"+tablo.getValueAt(tablo.getSelectedRow(), 0).toString()+"'");
								c.commit();
								egitimciGosterme();
								frame.hide();
								stmt.close();
								c.close();
								
							} catch (SQLException e) {
								e.printStackTrace();
							}

			    	    }
		    		});
		    	}catch(ArrayIndexOutOfBoundsException ex){
		    	}
       	tablo.setComponentPopupMenu(popupMenu);
		
		
       	 
   		 
   	 }catch(Exception e){
   		 
   	 }

	}

	}

