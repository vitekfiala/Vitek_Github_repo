package com.gameLogicAndLoadingFiles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

public class PexesoLogic {
	private final int NUMOFROWS;					
	private final int NUMOFCOLUMNS;				
	private final int NUMOFPAIRS;
	public final int POCETHRACU;
	String[][] names;							
	boolean[][] isJmolStructure;	
	HashMap<String,String> pairs = new HashMap<String,String>();
	HashMap<String,String> correctPairs = new HashMap<String,String>();
	ArrayList <int[]> colors = new ArrayList<int[]>();
	String correctPairsFile;
	String directoryWithMolecules;
	
	public PexesoLogic(String correctPairsFile, String directoryWithMolecules, int numOfRows, int numOfColumns, int pocetHracu){
		this.correctPairsFile=correctPairsFile;
		this.directoryWithMolecules = directoryWithMolecules;
		this.NUMOFROWS = numOfRows;
		this.NUMOFCOLUMNS=numOfColumns;
		this.POCETHRACU=pocetHracu;
		NUMOFPAIRS = NUMOFROWS*NUMOFCOLUMNS/2;
		names = new String [NUMOFROWS][NUMOFCOLUMNS];
		isJmolStructure = new boolean [NUMOFROWS][NUMOFCOLUMNS];
		
		loadPairs(pairs, correctPairsFile);
		shuffleBoard(pairs, names, isJmolStructure);
		setCorrectPairs(pairs, correctPairs);
		loadColors(colors);
	}

	private void loadColors(ArrayList<int[]> colors) {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("barvy.txt")))){	
			String line;
			while ((line=in.readLine())!=null){
				String [] colorInString = line.split(";");
				int [] color = new int [colorInString.length];
				for (int i = 0; i<color.length;i++){
					color[i]=Integer.parseInt(colorInString[i]);
				}
				colors.add(color);
			}			
			Collections.shuffle(colors);
		}
		catch (IOException e){
			e.printStackTrace();;
			System.exit(1);
		}
	}

	private void setCorrectPairs(HashMap<String, String> pairs,
			HashMap<String, String> correctPairs) {
		for(String key:pairs.keySet()){
			correctPairs.put(key, pairs.get(key));
			correctPairs.put(pairs.get(key), key);
		}		
	}

	private void shuffleBoard(HashMap<String, String> pairs,
			String[][] names, boolean[][] isJmolStructure) {
		boolean isAtKey = true;			
		Set<String> keys = pairs.keySet();
		Iterator<String> it = keys.iterator();
		String tempKey=null;
		for (int i = 0; i<NUMOFROWS; i++ ){
			for (int j = 0; j<NUMOFCOLUMNS; j++ ){
				if (isAtKey){
					tempKey=(String) it.next();
					names[i][j] = tempKey;
					isJmolStructure[i][j]=false;
					isAtKey = false;
				}
				else{
					names[i][j]=pairs.get(tempKey);
					isJmolStructure[i][j]=true;
					isAtKey = true;
				}
			}
		}
		Random r = new Random();
		for (int i = 0; i<NUMOFROWS; i++ ){
			for (int j = 0; j<NUMOFCOLUMNS; j++ ){
				int row = r.nextInt(NUMOFROWS);
				int column = r.nextInt(NUMOFCOLUMNS);
				swap(i, j, row, column, names, isJmolStructure);
			}
		}
	}

	private void swap(int i, int j, int row, int column, String[][] names,
			boolean[][] isJmolStructure) {
		String temps = names[i][j];
		boolean tempb = isJmolStructure[i][j];
		
		names[i][j] = names[row][column];
		isJmolStructure[i][j] = isJmolStructure[row][column];
		
		names[row][column] = temps;
		isJmolStructure[row][column] = tempb;
		
	}

	private void loadPairs(HashMap<String,String> pairs, String correctPairsFile) {
		List<String> keys = new ArrayList<String>();
		HashMap<String,String> allPairs = new HashMap<String,String>();		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(correctPairsFile), "UTF8"))){	
			String line;
			while ((line=in.readLine())!=null){
				String [] pair = line.split(";");
				allPairs.put(pair[0], pair[1]);
				keys.add(pair[0]);
			}
			Collections.shuffle(keys);
			for (int i=0; i< NUMOFPAIRS; i++){
				pairs.put(keys.get(i), allPairs.get(keys.get(i)));
			}
		}
		catch (IOException e){
			JOptionPane.showConfirmDialog(null,
					"Někde se vám ztratil adresář 'res', který musí být přítomen vedle spouštěcího souboru 3D Pexeso.jar. "
					+ "Bez adresáře nelze pokračovat",
					"Varování",
					JOptionPane.DEFAULT_OPTION);
			System.exit(1);
		}
	}

	
	public boolean[][] getIsJmolStructure() {
		return isJmolStructure;
	}
	
	public HashMap<String, String> getCorrectPairs() {
		return correctPairs;
	}

	public String[][] getNames() {
		return names;
	}
	
	public ArrayList<int[]> getColors(){
		return colors;
	}

	public String getCorrectPairsFile() {
		return correctPairsFile;
	}

	public String getDirectoryWithMolecules() {
		return directoryWithMolecules;
	}

	public int getNUMOFPAIRS() {
		return NUMOFPAIRS;
	}

	public int getPOCETHRACU() {
		return POCETHRACU;
	}

}
