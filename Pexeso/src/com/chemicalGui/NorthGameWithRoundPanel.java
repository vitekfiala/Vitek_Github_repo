package com.chemicalGui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class NorthGameWithRoundPanel extends JPanel {
	private JTextArea text, roundLabel, dataHrace;
	private JLabel hrajeHrac;
	private int round=1, scorePrvniho =0, scoreDruhyho=0, pocetChybnych=0;
	private long now =0;
	private final int pocetHracu;
	String pocetHracuS;
	private Timer timer = new Timer(1000, new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			now+=1000;
			setRoundTimeLabel(round, now);
		}
	});
	private String data;
	
	public NorthGameWithRoundPanel (int pocetHracu){
		this.pocetHracu=pocetHracu;
		this.pocetHracuS = "Počet hráčů: " +pocetHracu+"   \n";
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBackground(Color.WHITE);
		
		text = new JTextArea(" Najdi správné dvojice kartiček - název a molekulu. "
				+ "Model se dá stisknutím levého tlačítka na myši a táhnutím "
				+ "\n otáčet. Kolečkem myši lze model oddalovat či přibližovat. "
				+ "Při otevření 3. kartičky se první 2 otočí zpět.",2,0);
		text.setFont(new Font("Serif", Font.PLAIN, 18));
		//text.setBorder(BorderFactory.createLineBorder(Color.black));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);        
		text.setEditable(false);    
		
		this.add(text);
		this.add(Box.createHorizontalGlue());
		
		if(pocetHracu==1){
			data = getUdajHrace();
		}else{
			data= getUdajeHracu();
			hrajeHrac=new JLabel("Na tahu je 1. hráč    ");
			hrajeHrac.setFont(new Font("Serif", Font.PLAIN, 18));  
			hrajeHrac.setForeground(Color.RED); 
	        this.add(hrajeHrac);
		}
		dataHrace = new JTextArea(pocetHracuS+data);
		dataHrace.setFont(new Font("Serif", Font.PLAIN, 18));  
		dataHrace.setEditable(false);    
		
		roundLabel = new JTextArea("    Číslo tahu:   1     \n"
								 + "    Čas:   0:00", 2,0);
		roundLabel.setFont(new Font("Serif", Font.PLAIN, 18));   
		roundLabel.setEditable(false);    
		
		Border compound = BorderFactory.createCompoundBorder();
        roundLabel.setBorder(compound);
		
        this.add(dataHrace);
		this.add(roundLabel);
		timer.start();
	}
	
	private String getUdajHrace() {
		return "Skóre: "+scorePrvniho+ ", Tohle jsi měl(a) vědět: "+pocetChybnych ;
	}

	private String getUdajeHracu() {
		return "Skóre: 1. hráč: "+scorePrvniho+", 2. hráč: "+scoreDruhyho+ "      ";
	}

	public void setRoundLabel(int round){
		this.round=round;
		setRoundTimeLabel(round, now);
		if(pocetHracu==2){
			hrajeHrac.setText("Na tahu je "+((round+1)%2+1)+". hráč    ");
		}
	}
	
	private void setRoundTimeLabel(int round, long time){
		long minutes = time / (60 * 1000);
		long seconds = (time / 1000) % 60;
		String str = String.format("%d:%02d", minutes, seconds);
		roundLabel.setText("    Číslo tahu:   "+round+"     \n"
				         + "    Čas:   "+str+"  ");
	}
	
	public void timerStop(){
		timer.stop();
	}
	
	public void setCorrectlyFound(int correctlyFound){
		if(pocetHracu==1){
			scorePrvniho++;
			dataHrace.setText(pocetHracuS+getUdajHrace());
		}
		else{
			if(round%2==1){
				scorePrvniho++;
			}else{
				scoreDruhyho++;
			}
			dataHrace.setText(pocetHracuS+getUdajeHracu());
		}
	}
	
	public void setChybneNalezene(int chybneNalezene){
		if(pocetHracu==1){
			pocetChybnych++;
			dataHrace.setText(pocetHracuS+getUdajHrace());	
		}		
	}

	public int getScorePrvniho() {
		return scorePrvniho;
	}

	public int getScoreDruhyho() {
		return scoreDruhyho;
	}

	public int getPocetHracu() {
		return pocetHracu;
	}

	public long getCas(){
		return now;
	}
	
}
