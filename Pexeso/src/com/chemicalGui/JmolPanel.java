package com.chemicalGui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;

import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;
import org.jmol.util.Logger;
/**
 * 
 * @author Vitek
 *	Panel obsahujici 3D molekulu, preda se nazev souboru pri alokaci
 */
@SuppressWarnings("serial")
class JmolPanel extends JPanel {
	PexesoGridPanel parent;
	JmolViewer viewer;
	JmolAdapter adapter;
	String fileName;
	String directory;

	JmolPanel(String fileName, String dir, PexesoGridPanel parent) {
		this.fileName=fileName;		
		this.directory=dir;
		adapter = new SmarterJmolAdapter();
		viewer = JmolViewer.allocateViewer(this, adapter);		
		load();

	    parent.add(this, PexesoStates.uncovered.toString());
	}

	public JmolViewer getViewer() {
		return viewer;
	}

	final Dimension currentSize = new Dimension();
	private final Rectangle rectClip = new Rectangle(); // ignored by Jmol

	@Override
	public void paint(Graphics g) {
		getSize(currentSize);
		g.getClipBounds(rectClip);
		viewer.renderScreenImage(g, currentSize, rectClip);
	}

	public void load() {
		String strError = viewer.openFile(directory+"/"+fileName);
	    if (strError == null) {
	    	String initializingScript = "";
			try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(directory+"/"+fileName+".spt"), "UTF8"))){	
				StringBuilder popis = new StringBuilder();
				String line;
				while ((line=in.readLine())!=null){
					popis.append(line);
				}
				initializingScript=popis.toString();		
				viewer.script(initializingScript);							   
			} catch (IOException e) {
		    	Logger.error(e.getMessage());
			}
			finally{
				initializingScript = initializingScript+"frank off; set background WHITE; set scriptQueue ON";
				viewer.script(initializingScript);							   
			}
	    }	     
	    else{
	    	Logger.error(strError);
	    }
		
		
	}

}
