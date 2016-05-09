package com.chemicalGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WinningDialog1Hrac extends JDialog {	


	public WinningDialog1Hrac(final JFrame parent, int finishingRound, int chyby, long cas, int correctlyFound ) {
	    super(parent, "Vyhrál jsi!", false);
	    Font font = new Font("Serif", Font.PLAIN, 25);
	    
	    if (parent != null) {
	        Dimension parentSize = parent.getSize(); 
	        Point p = parent.getLocation(); 
	        setLocation(p.x + parentSize.width / 5, p.y + parentSize.height / 3);
	      }

	    JPanel texty = new JPanel(new GridLayout(0,1));
	    texty.setBackground(Color.WHITE);
	    texty.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));

	    JLabel naPocetKol = new JLabel("Hra dohrána na počet kol: "+finishingRound);	    
		naPocetKol.setFont(font);		
	    texty.add(naPocetKol);
	    
	    JLabel chybneOtocenych = new JLabel(chyby+" krát se stalo, že jsi měl(a) vědět, kde je správná kartička, protože jsi ji už viděl(a).");	    
	    chybneOtocenych.setFont(font);		
	    texty.add(chybneOtocenych);
	    
	    long minutes = cas / (60 * 1000);
		long seconds = (cas / 1000) % 60;
		String time = String.format("%d:%02d", minutes, seconds);
	    
	    JLabel casLbl = new JLabel("Hru jsi dokončil(a) za "+ time +".");	    
	    casLbl.setFont(font);		
	    texty.add(casLbl);
	    
	    // TODO konecny pocet bodu
	    double pocetBodu=(correctlyFound * correctlyFound * 300 / Math.sqrt(cas/1000))-200*chyby;
	    
	    JLabel bodyLbl = new JLabel("Získal(a) jsi celkem "+ (int)pocetBodu+" bodů.");	    
	    bodyLbl.setFont(font);		
	    texty.add(bodyLbl);
	    
	    JLabel znovu = new JLabel("Chceš hrát znovu?", SwingConstants.CENTER);
		znovu.setFont(font);
		texty.add(znovu);
		
	    JPanel p2 = new JPanel();
	    p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
	    p2.setBackground(Color.WHITE);
	    p2.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 25));
	    
	    p2.add(Box.createHorizontalGlue());
	    
	    JButton ok = new JButton("Ano");
	    ok.setFont(font);
	    ok.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		        ((JButton)evt.getSource()).getParent().setVisible(false);		    	  
		        parent.setVisible(false);
		        new InitialGui();
		      }
		    });
	    p2.add(ok);
	    
	    p2.add(Box.createRigidArea(new Dimension(20,0)));
	    
	    JButton ne = new JButton("Ne");
	    ne.setFont(font);	    
	    ne.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
	    	
	    });
	    p2.add(ne);	    	   
	    
	    getContentPane().add(texty,"Center");
	    getContentPane().add(p2, "South");

	    setSize(840, 360);
	  }
}
