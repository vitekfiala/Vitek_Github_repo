package com.chemicalGui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class RozmeryRBPanel extends JPanel {

	private JRadioButton ctyriNaCtyriRB, ctyriNaPetRB, petNaSestRB, sestNaSestRB;
	private ButtonGroup buttonGroup;
	
	public RozmeryRBPanel(){
		this.setLayout(new GridLayout(2,2));
		this.setBackground(Color.WHITE);
		
		ctyriNaCtyriRB = new JRadioButton();
		ctyriNaCtyriRB.setSelected(true);
		ctyriNaCtyriRB.setText("Rozměry pole: 4 x 4");
		ctyriNaCtyriRB.setBackground(Color.WHITE);
		
		ctyriNaPetRB = new JRadioButton();
		ctyriNaPetRB.setText("Rozměry pole: 4 x 5");
		ctyriNaPetRB.setBackground(Color.WHITE);
		
		petNaSestRB = new JRadioButton();
		petNaSestRB.setText("Rozměry pole: 5 x 6");
		petNaSestRB.setBackground(Color.WHITE);
		
		sestNaSestRB = new JRadioButton();
		sestNaSestRB.setText("Rozměry pole: 6 x 6");
		sestNaSestRB.setBackground(Color.WHITE);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(ctyriNaCtyriRB);
		buttonGroup.add(ctyriNaPetRB);
		buttonGroup.add(petNaSestRB);
		buttonGroup.add(sestNaSestRB);
		
		this.add(ctyriNaCtyriRB);
		this.add(ctyriNaPetRB);
		this.add(petNaSestRB);
		this.add(sestNaSestRB);
	}
	
	protected int[] getRozmery(){
		if(ctyriNaCtyriRB.isSelected()){
			return new int[]{4,4};
		}
		else if(ctyriNaPetRB.isSelected()){
			return new int[]{4,5};
		}
		else if(petNaSestRB.isSelected()){
			return new int[]{5,6};
		}
		else{
			return new int[]{6,6};
		}
		
		
	}
}
