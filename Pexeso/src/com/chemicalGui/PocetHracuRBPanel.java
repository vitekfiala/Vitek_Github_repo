package com.chemicalGui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class PocetHracuRBPanel extends JPanel {

	private JRadioButton jedenHracRB, dvaHraciRB;
	private ButtonGroup buttonGroup;
	
	public PocetHracuRBPanel(){
		this.setLayout(new GridLayout(1,2));
		this.setBackground(Color.WHITE);
		
		jedenHracRB = new JRadioButton();
		jedenHracRB.setSelected(true);
		jedenHracRB.setText("Počet hráčů: 1 hráč");
		jedenHracRB.setBackground(Color.WHITE);
		
		dvaHraciRB = new JRadioButton();
		dvaHraciRB.setText("Počet hráčů: 2 hráči");
		dvaHraciRB.setBackground(Color.WHITE);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jedenHracRB);
		buttonGroup.add(dvaHraciRB);
		
		this.add(jedenHracRB);
		this.add(dvaHraciRB);
	}
	
	protected int getPocetHracu(){
		if(jedenHracRB.isSelected()){
			return 1;
		}
		else{
			return 2;
		}
		
		
	}
}
