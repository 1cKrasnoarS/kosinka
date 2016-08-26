package kosinka;

//обработчик событий
import java.awt.event.*;

//для работы с окнами и кнопками
import javax.swing.*;

//Для работы с файлами 
import java.io.*;

//для работы с картинками
import javax.imageio.*;

//для работы с графикой
import java.awt.*;



public class game {
	
	public Image rubashka;
	
	private stopka[] stopki;
	
	private boolean pervVidacha;
	
	public boolean endGame;
	
	public game()
	{
		try
		{
			rubashka = ImageIO.read(new File("c:\\karta\\k0.png"));
		}catch(Exception ex){}
		
		stopki = new stopka[13];
		for(int i =0;i<13;i++){
			stopki[i] = new stopka();
		}
		start();
	}
	
	//захват карты мышью
	public void mouseDragged(int mX,int mY)
	{}
	//при одном нажатии
	public void mousePressed(int mX,int mY)
	{}
	//при двойном нажатии
	public void mouseDoubalePressed(int mX,int mY)
	{}
	//при отпускании левой кнопки мыши
	public void mouseReleased(int mX,int mY)
	{}
	//определении стопки на какую нажали мышью
	public int getNomKolodaPress(int mX,int mY)
	{
		return -1;		
	}
	
	//выдача карт из верхней левой стопки
	private void vidacha()
	{}
	
	//старт игры новая игра
	public void start()
	{}
	
	//загрузка изображений из колоды
	private void load()	
	{
		for (int i =1;i<=52;i++){
			stopki[0].add(new karta("c:\\karta\\k"+(i)+".png",rubashka,i));
		}
		
		
	}

	//метод отрисовки всех стопок карт
	public void drawKoloda(Graphics gr)
	{}
	
	
	
	
	
	

}
