/*
 	--------------------------
 	 Класс на базе JButton
 	--------------------------
 	getText - слово
 	TranslationValue - перевод
*/

import javax.swing.*;

//@SuppressWarnings("serial")
public class my_JButton extends JButton{

	//	private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = 1L;
	public String TranslationValue = null; // значение перевода
	
	public my_JButton(String nameButton) {
		this.setName(nameButton);
		this.setText(nameButton);	
	}
}
