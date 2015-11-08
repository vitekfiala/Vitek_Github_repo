package com.chemicalGui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * 
 * @author Vitek
 *	Panel obsahujici JLabel s textem = nazev slouceniny
 */
@SuppressWarnings("serial")
public class TextPanel extends JPanel {
	String name;
	JLabel text;
	PexesoGridPanel parent;
	
	protected TextPanel(String name, PexesoGridPanel parent){
		this.name=name;
		this.parent=parent;
		this.setLayout(new BorderLayout());
		text = new JLabel(name, SwingConstants.CENTER);
		text.setFont(new Font("Serif", Font.PLAIN, 22));		
		this.add(text, BorderLayout.CENTER);
		parent.add(this, PexesoStates.uncovered.toString());
	}
}
