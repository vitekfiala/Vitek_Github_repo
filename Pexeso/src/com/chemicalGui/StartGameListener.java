package com.chemicalGui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.gameLogicAndLoadingFiles.Topics;

public class StartGameListener implements ActionListener {

	private Topics topic;
	private String correctPairsFile, directoryWithMolecules;
	private int numOfRows, numOfColumns, pocetHracu;
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		topic = ((InitialPanel) ((JButton) ae.getSource()).getParent()).getChosenTopic();
		int[] rozmery = ((InitialPanel) ((JButton) ae.getSource()).getParent()).getRozmery();
		pocetHracu=((InitialPanel) ((JButton) ae.getSource()).getParent()).getPocetHracu();;
		numOfRows = rozmery[0];
		numOfColumns = rozmery[1];
		if(topic.equals(Topics.organika)){
			correctPairsFile = InitialGui.ORGANICKENAZVOSLOVITXT;
			directoryWithMolecules = InitialGui.ORGANICKENAZVOSLOVIDIR;
		}
		else if(topic.equals(Topics.prirodniLatky)){
			correctPairsFile = InitialGui.PRIRODNILATKYTXT;
			directoryWithMolecules = InitialGui.PRIRODNILATKYDIR;
		}
		else{
			System.out.println("chyba v StartGameListener");
		}
		((JFrame) SwingUtilities.getWindowAncestor((Component) ae.getSource())).setVisible(false);
		LoadingFrame loadingFrame = new LoadingFrame();	
		
		SwingUtilities.invokeLater(new GameLauncher(loadingFrame, correctPairsFile, directoryWithMolecules, numOfRows, numOfColumns, pocetHracu));
	}

}
