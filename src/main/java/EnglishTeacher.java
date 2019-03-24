
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executors;
//import java.awt.Image;
//import java.awt.Toolkit;

import javax.swing.*;
//import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;


//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;


public class EnglishTeacher {
	// Объявление всех компонентов учителя.
	static int maxButton = 20;
	static int maxWord = 0;
	static int guessedWord = 0;
	
	static Dictionary dict = new Dictionary();
	static EnglishTeacher calc; 
	
	JPanel windowContent;
	
	// массив кнопок с английскими словами
	my_JButton[] buttonE = new my_JButton[maxButton];
	// массив кнопок с русскими словами
	my_JButton[] buttonR = new my_JButton[maxButton];
	
	my_JButton buttonNew;
	my_JButton buttonExit;
	
	JPanel p1;
	JPanel p2;
	
	// В конструкторе создаются все компоненты
	// и добавляются на фрейм с помощью комбинации
	// Borderlayout и Gridlayout
	EnglishTeacher(){
		windowContent= new JPanel();
		
		// Задаём схему для этой панели
		BorderLayout bl = new BorderLayout();
		windowContent.setLayout(bl);
		
		// обработчик событий
		EnglishTeacherEngine etEngine = new EnglishTeacherEngine(this);

		// Создаём кнопки, используя конструктор класса my_JButton, который принимает текст кнопки в качестве параметра
		for (int i = 0; i < maxButton; i++){
			buttonE[i] = new my_JButton("E"+Integer.toString(i));
			buttonR[i] = new my_JButton("R"+Integer.toString(i));
			
			buttonE[i].addActionListener(etEngine);		
			buttonR[i].addActionListener(etEngine);	
		}
				
		buttonNew = new my_JButton("Начать заново");
		buttonNew.addActionListener(etEngine);
		buttonNew.setBackground(new Color(210,210,210));
		
		buttonExit = new my_JButton("Выход");	
		buttonExit.addActionListener(etEngine);		
		buttonExit.setBackground(new Color(210,210,210));

		// Создаём панель p1 с GridLayout (maxButton строчeк, 2 столбца)
		p1 = new JPanel();
		GridLayout gl = new GridLayout(maxButton,2,1,2);
		p1.setLayout(gl);		
		
		// Добавляем кнопки на панель p1
		for (int i = 0; i < maxButton; i++){
			p1.add(buttonE[i]);	p1.add(buttonR[i]);			
		}		
		// Помещаем панель p1 в центральную область окна
		windowContent.add("Center",p1);
		
		// Создаём панель с GridLayout (1 строчкa, 3 столбца)
		p2 = new JPanel();
		GridLayout gl2 = new GridLayout(1,2,1,2);
		p2.setLayout(gl2);	
		
		// Добавляем кнопки на панель p2
		p2.add(buttonNew);		
		p2.add(buttonExit);		
		// Помещаем панель p2 в южную область окна
		windowContent.add("South",p2);		

		
		//Создаём фрейм и задаём его основную панель
		JFrame frame = new JFrame("English Teacher");
		frame.setContentPane(windowContent);

		//иконка для приложения
		ImageIcon Checkers = new ImageIcon("EnglishDic.jpg");
		Image image = Checkers.getImage();
		frame.setIconImage(image);

		// делаем размер окна достаточным для того, чтобы вместить все компоненты
//		frame.pack();
		
		// Задаем размер окна
		frame.setSize(800,600);

/*		
		// иконка
		try{
			Image image = Toolkit.getDefaultToolkit().createImage( getClass().getResource("DRAG1PG.ICO") );
			frame.setIconImage( image );
		}
		catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null,	
					"Не найден файл ico", 
					"Сообщение",
					JOptionPane.WARNING_MESSAGE);	
		}
*/		
		// Наконец, отображаем окно
		frame.setVisible(true);
		
	}
	
	
	// очищаем надписи
	public void setButton(my_JButton m_JB){
		m_JB.TranslationValue = null;
		m_JB.setText("");
		m_JB.setBackground(new Color(238, 238, 238));
		m_JB.setVisible(true);
	}

	// обновим слова на кнопках
	public void Filling(){
		
		guessedWord = 0; // количество отгаданных слов
		
		for (int i = 0; i < maxButton; i++){
			setButton(calc.buttonE[i]);			
			setButton(calc.buttonR[i]);			
		}		
		
		int[] words = new int[maxButton];
		int[] buttons = new int[maxButton];
		
		//зачистим массивы (0 по умолчанию не годится)
		for (int i = 0; i < maxButton; i++){
			words[i] = -1;
			buttons[i] = -1;
		}
				
		boolean find;
		int num_word = 0, num_button = 0;
		String word, eng, rus;
		
		if (maxButton > dict.words.size()){
			maxWord = dict.words.size();
		} else {
			maxWord = maxButton;
		}
		
		for (int i = 0; i < maxWord; i++){
			
			// выбираем новое слово
			find = false;			
			while (!find){				
			
				find = true;
				num_word = (int)(Math.random()*dict.words.size());
				
				for (int j = 0; j <= i; j++){
					if (words[j] == num_word){
						find = false;
						break;
					}
				}
//				System.out.println("выбираем новое слово " + i +"  "+ maxWord +"  "+ dict.words.size() +"  "+ num_word);
			}
			
			words[i] = num_word;
			
			// выбираем свободную кнопочку
			find = false;			
			while (!find){				
			
				find = true;
				num_button = (int)(Math.random()*maxButton);
				
				for (int j = 0; j <= i; j++){
					if (buttons[j] == num_button){
						find = false;
						break;
					}
				}
//				System.out.println("выбираем свободную кнопочку " + i +"  "+ maxButton +"  "+ "  "+ num_button);
			}

			buttons[i] = num_button;
			
			word = dict.words.get(num_word); 
			eng = word.substring(0, word.indexOf('#')); 
			rus = word.substring(word.indexOf('#')+1, word.length()); 
			
			calc.buttonE[i].setText(eng);
			calc.buttonE[i].TranslationValue = rus;
			
			// кнопка с переводом	
			calc.buttonR[num_button].setText(rus);
			calc.buttonR[num_button].TranslationValue = eng;			
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		if (dict.Read()){  		
			calc = new EnglishTeacher();
			calc.Filling();	
		}
	}
}