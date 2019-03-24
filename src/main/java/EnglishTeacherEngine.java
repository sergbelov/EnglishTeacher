// класс обработки событий 

import java.awt.event.ActionListener;
import java.awt.Color;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JButton;
//import java.util.concurrent.TimeUnit;
//import java.text.SimpleDateFormat;
//import java.util.Date;


public class EnglishTeacherEngine implements ActionListener {
	
	String clickedButtonLabel;
    Object[] options = { "Да", "Нет" };
	EnglishTeacher parent; // ссылка на EnglishTeacher	
	my_JButton memButton = null; // первая нажатая кнопка
	
	// Конструктор сохраняет ссылку на окно EnglishTeacher в переменной класса “parent”
	EnglishTeacherEngine(EnglishTeacher parent){
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent evt){
		
		my_JButton clickedButton=null;
		
		// Получаем источник события
		Object eventSource = evt.getSource();		

		if (eventSource instanceof JButton){
			clickedButton = (my_JButton) eventSource;
		}
		
		// Получаем надпись на кнопке
//		clickedButtonLabel = clickedButton.getText();
		
		if (eventSource == parent.buttonExit){ // выход из программы
            int n = JOptionPane.showOptionDialog(null, 
            				"Уверены, что хотите выйти?",
                            "Подтверждение", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            options,
                            options[0]);
            
            if (n == 0) { System.exit(0); }

		} else if (eventSource == parent.buttonNew){
            int n = JOptionPane.showOptionDialog(null, 
            				"Начинаем заново?",
                            "Подтверждение", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, 
                            null, 
                            options,
                            options[0]);
            
            if (n == 0) { this.parent.Filling(); }			
			
		} else{
			// первая кнопка из пары или нажата другая кнопка из той же языковой группы
			if ( (memButton == null) || (memButton.getName().charAt(0) == clickedButton.getName().charAt(0)) ) { 
				if (memButton != null) { 
					memButton.setBackground(new Color(238, 238, 238));					
				}
						
				if (memButton == clickedButton){ // нажата повторно, сбросим
					memButton = null;
				}
				else{ // нажата впервые, отметим зеленым цветом, запомним что нажали
					clickedButton.setBackground(Color.green);
					memButton = clickedButton; 
				}
			}
			else{ // вторая кнопка (перевод)
				clickedButtonLabel = memButton.getText();
				// ответ верный
				if (clickedButton.getText().equals(memButton.TranslationValue)) {
					memButton.setVisible(false);
					memButton = null;
					clickedButton.setVisible(false);
					
					EnglishTeacher.guessedWord ++; // отгаданно еще одно слово 
					
					if (EnglishTeacher.guessedWord >= EnglishTeacher.maxWord) {
						JOptionPane.showMessageDialog(null,	
								"Поздравляю, Вы справились!", 
								"Сообщение",
								JOptionPane.INFORMATION_MESSAGE);						
					}
				}
				else{
					clickedButton.setBackground(Color.RED);
					
					JOptionPane.showMessageDialog(null,	
					"Неверный ответ", 
					"Сообщение",
					JOptionPane.WARNING_MESSAGE);							
					
					clickedButton.setBackground(new Color(238, 238, 238));
				}
			}
		}
		
	}
}

