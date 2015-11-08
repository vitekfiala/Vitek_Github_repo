package com.chemicalGui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.gameLogicAndLoadingFiles.Topics;

@SuppressWarnings("serial")
public class TopicsRBPanel extends JPanel {
		
	private JRadioButton organickyRB, prirodniRB;
	private ButtonGroup buttonGroup;
	
	public TopicsRBPanel(){
		this.setLayout(new GridLayout(1, 2));
		this.setBackground(Color.WHITE);
		
		organickyRB = new JRadioButton();
		organickyRB.setSelected(true);
		organickyRB.setMnemonic(KeyEvent.VK_O);
		organickyRB.setText("Organické názvosloví");
		organickyRB.setActionCommand("organika");
		organickyRB.setBackground(Color.WHITE);
		
		prirodniRB = new JRadioButton();
		prirodniRB.setText("Přírodní látky");
		prirodniRB.setMnemonic(KeyEvent.VK_P);
		prirodniRB.setActionCommand("prirodniLatky");
		prirodniRB.setBackground(Color.WHITE);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(organickyRB);
		buttonGroup.add(prirodniRB);
		
		this.add(organickyRB);
		this.add(prirodniRB);
	}
	
	protected Topics getChosenTopic(){
		String selection = buttonGroup.getSelection().getActionCommand();
		if(selection.equals(Topics.organika.toString())){
			return Topics.organika;
		}
		else if (selection.equals(Topics.prirodniLatky.toString())) {
			return Topics.prirodniLatky;
		}
		else{
			return null;
		}
		
	}

}
