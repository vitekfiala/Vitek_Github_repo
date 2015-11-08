package com.chemicalGui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * 
 * @author Vitek
 *	Panel for covered pexeso grid
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	JButton button;
	PexesoGridPanel parent;

	protected ButtonPanel(PexesoGridPanel pexesoGridPanel){
		parent = pexesoGridPanel;
		this.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon(this.getClass().getResource("questionMark.png"));
		button = new JButton(icon);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.setCard(PexesoStates.uncovered);
				((PexesoGamePanel) parent.getParent()).uncoverPexesoGrid(parent);
			}					
		});
		
		this.add(button, BorderLayout.CENTER);
		parent.add(this, PexesoStates.covered.toString());
	}
	

}
