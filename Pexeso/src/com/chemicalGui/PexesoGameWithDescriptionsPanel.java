package com.chemicalGui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.gameLogicAndLoadingFiles.PexesoLogic;

@SuppressWarnings("serial")
public class PexesoGameWithDescriptionsPanel extends JPanel {
	JPanel popisky;
	JTextArea popisek, popisek2;
	PexesoLogic pexesoLogic;
	final PexesoGamePanel gamePanel;
	private final NorthGameWithRoundPanel northPanel;
	
	public PexesoGameWithDescriptionsPanel (PexesoLogic pexesoLogic, NorthGameWithRoundPanel northPanel, LayoutManager layout){
		super(layout);
		this.pexesoLogic=pexesoLogic;
		this.northPanel=northPanel;
		this.setBackground(Color.WHITE);
		
        GridBagConstraints c = new GridBagConstraints();

        
        popisky = new JPanel(new GridLayout(2,1));        
        popisek = new MoleculDescriptionTextArea();
        popisek2 = new MoleculDescriptionTextArea();
		
        JScrollPane scrollablePopisek = new JScrollPane(popisek);        
        popisky.add(scrollablePopisek);
        
        JScrollPane scrollablePopisek2 = new JScrollPane(popisek2);        
        popisky.add(scrollablePopisek2);
                
        gamePanel = new PexesoGamePanel(pexesoLogic, popisek, popisek2); 
        
        c.weighty=1;
        c.weightx=1;
        c.fill=GridBagConstraints.BOTH;
        this.add(gamePanel, c);   
        c.weightx=0;
        c.fill=GridBagConstraints.VERTICAL;
        this.add(popisky, c);
	}
	
	
	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener l) {
		gamePanel.addPropertyChangeListener(propertyName, l);
	}
	
	public int getRound(){
		return gamePanel.round;
	}


	public NorthGameWithRoundPanel getNorthPanel() {
		return northPanel;
	}
	

}
