package com.chemicalGui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MoleculDescriptionTextArea extends JTextArea {

	public MoleculDescriptionTextArea (){		
		super("",10,10);
		
        this.setFont(new Font("Serif", Font.PLAIN, 18));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLineWrap(true);
        this.setWrapStyleWord(true);        
        this.setEditable(false);        
	}
}
