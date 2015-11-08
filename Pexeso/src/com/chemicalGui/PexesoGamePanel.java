package com.chemicalGui;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.jmol.api.JmolViewer;

import com.gameLogicAndLoadingFiles.PexesoLogic;
/**
 * 
 * @author Vitek
 *	Panel obsahujici hraci plan, kontroluje stav hry, spravne dvojice
 */
@SuppressWarnings("serial")
public class PexesoGamePanel extends JPanel {
	String[][] names;
	boolean [][] isJmolStructure;
	HashMap<String,String> correctPairs;
	ArrayList<PexesoGridPanel> uncoveredPexesoGrids = new ArrayList<PexesoGridPanel>();
	ArrayList<String> seenPexesoGrids = new ArrayList<String>();
	JTextArea popisek, popisek2;
	private boolean foundPair=false;
	int round = 1;
	PropertyChangeSupport propertyChangeSupport;
	private int correctlyFound=0, pocetChybnych=0;;
	ArrayList <int[]> colors;
	String directory;
	PexesoLogic pexesoLogic;
	
	protected PexesoGamePanel (PexesoLogic pexesoLogic, JTextArea popisek, JTextArea popisek2){
		this.names=pexesoLogic.getNames();
		this.setLayout(new GridLayout(names.length, names[0].length)); 				
		this.isJmolStructure=pexesoLogic.getIsJmolStructure();
		this.correctPairs = pexesoLogic.getCorrectPairs();
		this.colors=pexesoLogic.getColors();
		this.popisek=popisek;
		this.popisek2=popisek2;
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.directory = pexesoLogic.getDirectoryWithMolecules();
		this.pexesoLogic=pexesoLogic;
		for (int i=0; i<names.length;i++){
			for (int j = 0; j< names[0].length; j++){
				this.add(new PexesoGridPanel(isJmolStructure[i][j],names[i][j], directory));				
			}
		}
	}
	
	protected void uncoverPexesoGrid(PexesoGridPanel uncovered){	
		if(!seenPexesoGrids.contains(uncovered.name)){
			seenPexesoGrids.add(uncovered.name);
		}		
		if (uncoveredPexesoGrids.size()>1){
			coverUncoveredPexesoGrids();
			popisek.setText("");
			popisek2.setText("");
			propertyChangeSupport.firePropertyChange("round", round, round+1);
			round++;			
		}
		uncoveredPexesoGrids.add(uncovered);
		
		if(foundPair){
			popisek.setText("");
			popisek2.setText("");
			foundPair=false;
		}

		
		if (uncoveredPexesoGrids.size()==2){
			String firstName=uncoveredPexesoGrids.get(0).name;
			String secondName=uncoveredPexesoGrids.get(1).name;
			if(firstName.equals(correctPairs.get(secondName))){
				int [] color = colors.get(0);
				colors.remove(0);
				for(PexesoGridPanel panel:uncoveredPexesoGrids){
					if(panel.isJmolStructure){
						JmolViewer viewer = ((JmolPanel) panel.uncovered).viewer;
						viewer.evalString("quit ;set background ["+color[0]+","+color[1]+","+color[2]+"];");
					}
					else {
						JPanel uncoveredCorrectTextPanel = panel.uncovered;
						uncoveredCorrectTextPanel.setBackground(new Color(color[0],color[1],color[2]));
					}
					panel.setEnabled(false);
					
				}
				uncoveredPexesoGrids.clear();
				foundPair=true;
				propertyChangeSupport.firePropertyChange("correctlyFound", correctlyFound, correctlyFound+1);
				correctlyFound++;
				if(correctlyFound == pexesoLogic.getNUMOFPAIRS()){
					gameFinished();
				}
			}
			else{
				for(String seen:seenPexesoGrids){
					if(uncoveredPexesoGrids.get(0).name.equals(correctPairs.get(seen))){
						propertyChangeSupport.firePropertyChange("pocetChybnych", pocetChybnych, pocetChybnych+1);
						pocetChybnych++;
					}
				}
			}
		}
		
		if(uncovered.isJmolStructure){
			setText(uncovered, uncoveredPexesoGrids);
			((JmolPanel)uncovered.uncovered).viewer.evalString("quit; move 0 180 0 0 0 0 0 0 12;");
			// TODO tady se otaci
			
		}
	}

	private void setText(PexesoGridPanel uncovered,
			ArrayList<PexesoGridPanel> uncoveredPexesoGrids2) {
		String popisMolekuly="";
		popisMolekuly = nactiText(uncovered);
		
		if(uncoveredPexesoGrids.size()==1){
			popisek.setText(popisMolekuly);
		}
		else{
			popisek2.setText(popisMolekuly);
		}			
	}

	private String nactiText(PexesoGridPanel uncovered) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(directory+"/"+uncovered.name+".txt"), "UTF8"))){	
			StringBuilder popis = new StringBuilder();
			String line;
			while ((line=in.readLine())!=null){
				popis.append(line);
			}
			return popis.toString();				
		}
		catch (IOException e){
			e.printStackTrace();
			return "";
		}
	}

	private void gameFinished() {
		NorthGameWithRoundPanel northPanel = ((PexesoGameWithDescriptionsPanel) getParent()).getNorthPanel();
		northPanel.timerStop();
		JDialog f;
		if(northPanel.getPocetHracu()==1){
			long cas = northPanel.getCas();
			f = new WinningDialog1Hrac(((JFrame) SwingUtilities.getWindowAncestor(this)), round, pocetChybnych, cas, correctlyFound);
		}else{
			String vitez;
			if(northPanel.getScorePrvniho()>northPanel.getScoreDruhyho()){
				vitez = "Vyhrál 1. hráč, gratuluji!";
			}
			else if(northPanel.getScorePrvniho()<northPanel.getScoreDruhyho()){
				vitez = "Vyhrál 2. hráč, gratuluji!";
			}
			else{
				vitez = "Remíza!";
			}
			f = new WinningDialog2Hraci(((JFrame) SwingUtilities.getWindowAncestor(this)), northPanel.getScorePrvniho(), northPanel.getScoreDruhyho(), vitez);
		}
	    f.setVisible(true);	
	}

	private void coverUncoveredPexesoGrids() {
		while (uncoveredPexesoGrids.size()!=0){
			uncoveredPexesoGrids.get(0).setCard(PexesoStates.covered);
			uncoveredPexesoGrids.remove(0);
		}
	}
	
	public int getRound(){
		return round;
	}
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener l) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, l);
	}

}
