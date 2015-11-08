package com.chemicalGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class WinningDialog2Hraci extends JDialog {

	public WinningDialog2Hraci(final JFrame parent, int scorePrvniho, int scoreDruhyho, String vitez ) {
	    super(parent, vitez, false);
	    
	    if (parent != null) {
	        Dimension parentSize = parent.getSize(); 
	        Point p = parent.getLocation(); 
	        setLocation(p.x + parentSize.width / 3, p.y + parentSize.height / 3);
	      }

	    JPanel texty = new JPanel(new BorderLayout());
	    texty.setBackground(Color.WHITE);

	    JLabel text = new JLabel("1. hráč našel dvojic: "+scorePrvniho +" a druhý hráč našel dvojic: "+scoreDruhyho, SwingConstants.CENTER);
		text.setFont(new Font("Serif", Font.PLAIN, 25));		
	    texty.add(text, BorderLayout.PAGE_START);
	    
	    JLabel znovu = new JLabel("Chcete hrát znovu?", SwingConstants.CENTER);
		znovu.setFont(new Font("Serif", Font.PLAIN, 25));
		texty.add(znovu, BorderLayout.PAGE_END);
		
	    JPanel p2 = new JPanel();
	    p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
	    p2.setBackground(Color.WHITE);
	    p2.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 25));
	    
	    p2.add(Box.createHorizontalGlue());
	    
	    JButton ok = new JButton("Ano");
	    ok.setFont(new Font("Serif", Font.PLAIN, 25));
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
	    ne.setFont(new Font("Serif", Font.PLAIN, 25));	    
	    ne.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
	    	
	    });
	    p2.add(ne);	    	   
	    
	    getContentPane().add(texty,"Center");
	    getContentPane().add(p2, "South");

	    setSize(550, 190);
	  }
}
