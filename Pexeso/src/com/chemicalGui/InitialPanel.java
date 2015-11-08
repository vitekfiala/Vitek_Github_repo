package com.chemicalGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.gameLogicAndLoadingFiles.Topics;


@SuppressWarnings("serial")
public class InitialPanel extends JPanel {
	JLabel nadpis;
	JTextArea uvodniText, viceHracu, dalsiPopis;
	TopicsRBPanel topicsRBPanel;
	RozmeryRBPanel rozmeryRBPanel;
	JButton spustHruButton;
	PocetHracuRBPanel pocetHracuRBPanel;
	
	public InitialPanel(){
		this.setPreferredSize(new Dimension(500, 720));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("nadpis.png"));
		nadpis = new JLabel(icon);				
		nadpis.setAlignmentX(CENTER_ALIGNMENT);
		this.add(nadpis);
				
		uvodniText = new JTextArea();
		uvodniText.setEditable(false);		
		uvodniText.setText("Vítám Vás! Spustili jste hru Chemické 3D pexeso.\n"
				+ "Cílem hry je najít správné dvojice kartiček, přičemž na jedné musí být název "
				+ "sloučeniny a na druhé její interaktivní 3D model. Při odhalení modelu se "
				+ "také vpravo zobrazí informační text o molekule. Model se dá stisknutím levého"
				+ " tlačítka na myši a táhnutím otáčet. Kolečkem myši lze model oddalovat či "
				+ "přibližovat. Po nalezení správné dvojice se obě kartičky stejně zabarví.\n"
				+ "Nejprve si zvolte téma, dle kterého budou vylosovány kartičky.");
		uvodniText.setFont(new Font("Serif", Font.PLAIN, 17));
		uvodniText.setLineWrap(true);
		uvodniText.setWrapStyleWord(true);
		uvodniText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));		
		this.add(uvodniText);
		
		topicsRBPanel = new TopicsRBPanel();
		topicsRBPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
		topicsRBPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.add(topicsRBPanel);
		
		
		viceHracu = new JTextArea();
		viceHracu.setEditable(false);		
		viceHracu.setText("Nyní zvolte počet hráčů. Pokud hrajou 2 hráči, "
				+ "hraje se na to, kdo nalezne více dvojic.\nPokud "
				+ "hraje jeden hráč, tak se snaží získat co nejvíce bodů. "
				+ "Počet bodů se spočítá podle rozměrů hrací plochy, "
				+ "doby hraní a počtu 'chyb'. Chybu hráč udělá, když nenajde "
				+ "pár, ale přitom už by měl vědět, kde správná kartička je, "
				+ "protože ji už dříve viděl.");
		viceHracu.setFont(new Font("Serif", Font.PLAIN, 17));
		viceHracu.setLineWrap(true);
		viceHracu.setWrapStyleWord(true);
		viceHracu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(viceHracu);
		
		pocetHracuRBPanel = new PocetHracuRBPanel();
		pocetHracuRBPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
		pocetHracuRBPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.add(pocetHracuRBPanel);
		
		this.add(new JLabel(" "));
		
		rozmeryRBPanel = new RozmeryRBPanel();
		rozmeryRBPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
		rozmeryRBPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.add(rozmeryRBPanel);
				
		dalsiPopis = new JTextArea();
		dalsiPopis.setEditable(false);
		dalsiPopis.setText("Kolik bodů dokážeš získat?");
		dalsiPopis.setFont(new Font("Serif", Font.PLAIN, 17));	
		dalsiPopis.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dalsiPopis.setLineWrap(true);
		dalsiPopis.setWrapStyleWord(true);
		dalsiPopis.setAlignmentX(CENTER_ALIGNMENT);
		this.add(dalsiPopis);		
		
		spustHruButton = new JButton("Spustit hru");		
		spustHruButton.setAlignmentX(CENTER_ALIGNMENT);
		spustHruButton.addActionListener(new StartGameListener());
		this.add(spustHruButton);		
		
	}

	public Topics getChosenTopic() {
		return topicsRBPanel.getChosenTopic();
	}
	
	public int[] getRozmery() {
		return rozmeryRBPanel.getRozmery();
	}
	
	public int getPocetHracu(){
		return pocetHracuRBPanel.getPocetHracu();
	}

}