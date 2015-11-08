package com.chemicalGui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class LoadingFrame {
	
	private JFrame frame;
	private JLabel loading;
	
	public LoadingFrame() {
		frame = new JFrame("Chemické 3D pexeso");
		   
        loading = new JLabel("Loading...", SwingConstants.CENTER);
        loading.setFont(new Font("Serif", Font.PLAIN, 25));	        
              
        Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(20, 40, 20, 40));
        loading.setBorder(compound);
        
        frame.getContentPane().add(loading); 
                        
        frame.setUndecorated(true);
        frame.pack();            
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

	protected void hide(){
		frame.setVisible(false);
	}
}
