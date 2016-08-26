package kosinka;

import javax.swing.*;
import java.awt.*;


public class okno extends JFrame{
	
	//Конструктор
	
	/**
	 * 
	 */
	public okno()
	{
		
		//Создаем объект окна
		pole panel = new pole();
		
		//Подключаем панель к окну
		Container cont = getContentPane();
		cont.add(panel);
		
		
		//Заголовок окна
		setTitle("Игра - Пасьянс-Косынка");
		//Расположение и размер окна
		setBounds(0,0,1000,700);
		//запрет изменения размеров окна пользователем
		setResizable(false);
		//Операция заверщения приложения при закрытии окна
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Показ окна
		setVisible(true);
	}
}
