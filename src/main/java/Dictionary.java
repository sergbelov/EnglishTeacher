/*
	Класс чтения словаря из файла

	Слова храняться в текстовом файле Dictionary.txt
	построчно
	в формате: Eng#Rus
	Accept#одобрить

	Словарь помещаем в массив "word"
*/


import java.io.FileInputStream;
import java.io.BufferedReader;
//import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dictionary {	
	
	ArrayList<String> words = new ArrayList<String>();	
	
	@SuppressWarnings("resource")
	public boolean Read() throws IOException {

//		System.out.println("Текущий каталог " + new File(".").getAbsolutePath());

		FileInputStream fstream = null;
		try{
//			   FileInputStream fstream = new FileInputStream("C:\\TMP\\Dictionary.txt");
			fstream = new FileInputStream("Dictionary.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "Cp1251"));
			String strLine;
			while ((strLine = br.readLine()) != null){
			   words.add(strLine);

			   /*JOptionPane.showMessageDialog(null,	
						strLine, 
						"Сообщение",
						JOptionPane.INFORMATION_MESSAGE);*/	
		   	}
		   	return true;
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null,	
					"Ошибка при чиении файла-словаря", 
					"Сообщение",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		finally {
			if (fstream != null) {
				fstream.close();
			}
		}
	}
}
