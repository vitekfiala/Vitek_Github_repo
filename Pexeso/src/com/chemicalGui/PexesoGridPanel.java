package com.chemicalGui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 * 
 * @author Vitek
 *	Panel pro policko pexesa, ma cardLayout mezi zakrytou a nezakrytou verzi (text/soubor)
 */
@SuppressWarnings("serial")
public class PexesoGridPanel extends JPanel {
	boolean isJmolStructure;
	String name;
	JPanel covered, uncovered;
	String directory;
	
	protected PexesoGridPanel(boolean isJmolStructure, String name, String dir){
		this.isJmolStructure=isJmolStructure;
		this.name=name;
		this.directory=dir;
		this.setLayout(new CardLayout());
		covered = new ButtonPanel(this);
		if(isJmolStructure){
			uncovered = new JmolPanel(name,directory, this);
		}
		else{
			uncovered = new TextPanel(name, this);
		}
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setCard(PexesoStates.covered);
		this.setPreferredSize(new Dimension(100,100));
	}
	
	protected void setCard(PexesoStates state){
		((CardLayout)this.getLayout()).show(this,state.toString());
	}

}
