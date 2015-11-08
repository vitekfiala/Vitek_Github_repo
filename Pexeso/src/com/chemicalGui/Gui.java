package com.chemicalGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.gameLogicAndLoadingFiles.PexesoLogic;
/**
 * 
 * @author Vitek
 *	Hlavni trida pro Gui.
 */
public class Gui {
	private LoadingFrame loadingFrame;
	private PexesoLogic pexesoLogic;
	private JFrame frame;
	private PexesoGameWithDescriptionsPanel pexesoGameWithDescriptions;
	private JPanel panel;
	private JLabel barvyAtomuPopisek;
	private NorthGameWithRoundPanel northPanel;
	
	
	public Gui (PexesoLogic pexesoLogic, LoadingFrame loadingFrame) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.pexesoLogic = pexesoLogic;
		this.loadingFrame = loadingFrame;
		frame = new JFrame("Chemické 3D pexeso");
        frame.addWindowListener(new AppCloser());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel(new BorderLayout());
        
        northPanel = new NorthGameWithRoundPanel(pexesoLogic.getPOCETHRACU());
      
        pexesoGameWithDescriptions = new PexesoGameWithDescriptionsPanel(this.pexesoLogic, northPanel, new GridBagLayout());

        barvyAtomuPopisek = new JLabel("Barvy atomů: "
        		+ "    uhlík - černá		"
        		+ "    vodík - bílá"
        		+ "    kyslík - červená"
        		+ "    dusík - modrá"
        		+ "    fosfor - oranžová"
        		+ "    chlor - zelená"
        		+ "          autor: Vít Fiala (vit.fiala@gmail.com)", SwingConstants.CENTER);
        barvyAtomuPopisek.setFont(new Font("Serif", Font.PLAIN, 18));
        barvyAtomuPopisek.setBackground(Color.WHITE);
        barvyAtomuPopisek.setOpaque(true);
        
        pexesoGameWithDescriptions.addPropertyChangeListener("round", new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent event) {	
				northPanel.setRoundLabel((Integer) event.getNewValue());
			}});
        pexesoGameWithDescriptions.addPropertyChangeListener("correctlyFound", new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent event) {	
				northPanel.setCorrectlyFound((Integer) event.getNewValue());
			}});
        pexesoGameWithDescriptions.addPropertyChangeListener("pocetChybnych", new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent event) {	
				northPanel.setChybneNalezene((Integer) event.getNewValue());
			}});

        panel.add(northPanel, BorderLayout.PAGE_START);
        panel.add(pexesoGameWithDescriptions, BorderLayout.CENTER);
        panel.add(barvyAtomuPopisek, BorderLayout.PAGE_END);
        
        frame.getContentPane().add(new JScrollPane(panel));        
        frame.pack();        
        frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
        this.loadingFrame.hide();
        frame.setVisible(true);
	}
}





