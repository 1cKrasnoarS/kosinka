package kosinka;

import javax.imageio.*;
import java.awt.*;
import java.io.*;

public class karta {

	//Координаты крты на игровом поле
	public int x,y;
	//Изображение краты
	public Image img;
	//Вид расположения рубашкой или картинкой 
	public boolean tipRubashka;
	//Изображение рубашки карты
	public Image rubashka;
	//Масть карты
	public int mast;
	//Тип карты (король, туз и т.д)
	public int tipKarta;
	//Признак захвата карты мышью
	public boolean vibrana;
	//Признак крайсной или черной масти
	public boolean red_karta;
	
	//Конструктор класса
	
	public karta(String path,Image rubashka, int nom){
		//Признк захвата мышью - НЕ захвачена
		vibrana = false;
		//Изображение рубашки карты
		this.rubashka = rubashka;
		//Загружка изображения карты из файла
		try
		{
			img = ImageIO.read(new File(path));
		}
		catch (Exception ex) {}
		//Начальная координата по X
		x = 30;
		//Начальная координата по y
		y = 15;
		
		//Изначально карта расположена рубашкой
		tipRubashka = true;
		//Вычисляем масть карты
		mast = (nom-1)%4;
		//Вычисляем тип карты 
		tipKarta = (nom -1)/4;
		
		//Определяем цвет масти (Красный или черный)
		red_karta = true;
		if(mast<=1)red_karta = false;	
	}
	
	//Матод отображения (рисование карты)
	public void draw(Graphics gr){
		//Если карта расоложена рубашкой вверх
		if(tipRubashka==false)
		{
			//ввыводим изображение рубашки
			gr.drawImage(img, x, y, 72,97,null);
		}
		else //Иначе если НЕ рубашкой
		{
			//Выводим изображение карты
			gr.drawImage(rubashka, x, y, 72,97,null);
		}
		//Если карты захвачена мышью
		if(vibrana==true){
			//Рисуем вокруг карты прямоугольник желтого цвета
			gr.setColor(Color.YELLOW);
			gr.drawRect(x, y, 72, 97);
		}
		
	}
	
	
	
}
