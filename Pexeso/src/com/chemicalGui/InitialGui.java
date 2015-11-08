package com.chemicalGui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class InitialGui {
	public static final String MAINDIR = "./res/";
	public static final String PRIRODNILATKYDIR = MAINDIR+"prirodni latky";
	public static final String ORGANICKENAZVOSLOVIDIR = MAINDIR+"organicke nazvoslovi";
	public static final String PRIRODNILATKYTXT = MAINDIR+"prirodniLatky.txt"; 
	public static final String ORGANICKENAZVOSLOVITXT = MAINDIR+"organickeNazvoslovi.txt";
	
	private JFrame frame;
	private JPanel panel;
	private InitialPanel initialPanel;
	private JLabel autor;

	
	public InitialGui(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame("Chemické 3D pexeso");
        frame.addWindowListener(new AppCloser());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel(new BorderLayout());
        
        initialPanel = new InitialPanel();
        
		autor = new JLabel("autor:  Vít Fiala (vit.fiala@gmail.com)", SwingConstants.CENTER);
		autor.setFont(new Font("Serif", Font.PLAIN, 15));				
        
		panel.add(initialPanel, BorderLayout.CENTER);
		panel.add(autor, BorderLayout.PAGE_END);
				
        frame.getContentPane().add(new JScrollPane(panel));      
        frame.pack();             
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
