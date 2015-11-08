package com.chemicalGui;

import com.gameLogicAndLoadingFiles.PexesoLogic;

public class GameLauncher implements Runnable {

	LoadingFrame loadingFrame;
	String correctPairsFile, directoryWithMolecules;
	int numOfRows, numOfColumns, pocetHracu;
	
	
	public GameLauncher(LoadingFrame loadingFrame, String correctPairsFile,
			String directoryWithMolecules, int numOfRows, int numOfColumns, int pocetHracu ) {
		super();
		this.loadingFrame = loadingFrame;
		this.correctPairsFile = correctPairsFile;
		this.directoryWithMolecules = directoryWithMolecules;
		this.numOfRows = numOfRows;
		this.numOfColumns = numOfColumns;
		this.pocetHracu=pocetHracu;
	}


	@Override
	public void run() {
		PexesoLogic pexesoLogic = new PexesoLogic(correctPairsFile, directoryWithMolecules, numOfRows, numOfColumns, pocetHracu);
		new Gui(pexesoLogic, loadingFrame);		
	}

}
