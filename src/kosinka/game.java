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
	
	//
	private int nomStopki;
	private int nomKarti;
	private int dx,dy;
	private int oldX,oldY;
	private Timer tmEndGame;
	
	
	
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
		
		tmEndGame = new Timer(100,new ActionListener(){
			//перебираем четыре домашниие стопки
					@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
						for (int i=2;i<=5;i++)
						{
				//Получаем самую нижнюю карту
				karta getKarta = stopki[i].get(0);
				//нижнюю карту добавляем наверх
				stopki[i].add(getKarta);
				//удаляем нижнюю карту
				stopki[i].remove(0);
						}
					}
		});
		
		start();
	}
	//Раздача карт в нижние семь стопок
	private void razdacha(){
		int x = 30;
		for (int i =6;i<13;i++)
		{
			//добавление карт в стопку
			for(int j=6;j<=i;j++)
			{
				int rnd = (int)(Math.random()*stopki[0].size());
				
				//получаем эту карту
				karta getKarta = stopki[0].get(rnd);
				
				//Если карта не самая верхняя
				//то паказываем ее рубашкой
				
				if(j<i) getKarta.tipRubashka = true;
				else getKarta.tipRubashka = false; //если карта верхняя
				
				//кординаты по x
				getKarta.x = x;
				//Каждую следующую карту располагаем ниже на 20 пикселей
				getKarta.y=130+stopki[i].size()*20;
				//добавляем карту в нижнюю стопку
				stopki[i].add(getKarta);
				//Удаляем карту из верхней левой стопки
				stopki[0].remove(rnd);
			
			}
			//Увеличиваем координату по X
			//(смещаемся правее)
			
			x+=110;
		}
		
	}
	
	
	//Проверка оканчания игры
	private void testEndGame()
	{
		if( (stopki[2].size()==13)&&
			(stopki[3].size()==13)&&
			(stopki[4].size()==13)&&
			(stopki[5].size()==13))
		{
			endGame = true;
			tmEndGame.start();
		}
	}
	
	//Автоматическое открытие карт в нижних стопках
	private void openKarta(){
		//Перебираем все нижние стопки карт
		for(int i =6;i<=12;i++)
		{
			if (stopki[i].size()>0)
			{
				//номер последненей карты в стопке
				int nomPoseld = stopki[i].size()-1;
				//получаем последнюю карту
				karta getKarta = stopki[i].get(nomPoseld);
				//если карты отображается рубашкой
				//то открываем ее
				if(getKarta.tipRubashka==true)getKarta.tipRubashka = false;
			}
		}
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
	{
		int nom=getNomKolodaPress(mX,mY);
	if(nom==0)
	{
	vidacha();	




	}

	}
	//определении стопки на какую нажали мышью
	public int getNomKolodaPress(int mX,int mY)
	{
		int nom=1;
		
	if((mY>=15)&&(mY<=(30+72)))
	{
	if((mX>=30)&&(mX<=(30+72))) nom=0;
	if((mX>=140)&&(mX<=(140+72))) nom=1;
	if((mX>=360)&&(mX<=(360+72))) nom=2;
	if((mX>=470)&&(mX<=(470+72))) nom=3;
	if((mX>=580)&&(mX<=(580+72))) nom=4;
	if((mX>=690)&&(mX<=(690+72))) nom=5;
	}
	else if((mX>=130)&&(mY<=(700)))
	{
		
	if((mX>=30)&&(mX<=110*7))
	{
		if(((mX-30)%110)<=72)
		{
			nom=(mX-30)/110;
			nom+=6;
			
		}


	}



	}
	return nom;
	}
	
	//выдача карт из верхней левой стопки
	private void vidacha()
	{
		
		if(stopki[0].size()>0)
		{
		int nom;	
		if(pervVidacha==true)
		{
		nom=(int)(Math.random()*stopki[0].size());	

		}

		else
		{	
		nom=stopki[0].size()-1;
		}

		karta getKarta=stopki[0].get(nom);
		getKarta=stopki[0].get(nom);
		getKarta.tipRubashka = false;
		getKarta.x+=110;
		stopki[1].add(getKarta);
		stopki[0].remove(nom);
		
		}
		
		else{
		
		int nomPosled = stopki[1].size()-1;
		for(int i = nomPosled;i>=0;i--)
		{
			karta getKarta = stopki[1].get(i);
			getKarta.tipRubashka=true;
			getKarta.x-=110;
			stopki[0].add(getKarta);
		}
		stopki[1].clear();
		
		pervVidacha=false;

		}
}
	
	//старт игры новая игра
	public void start()
	{
		for (int i=0;i<13;i++)	
		{
			
		stopki[i].clear();

		}

		load();
		razdacha();
		
		
		
		endGame=false;
		pervVidacha=true;
		
		
		//Номер выбранной карты
		nomKarti = -1;
		//номер выбранной стопки
		nomStopki = -1;

		}
	
	//загрузка изображений из колоды
	private void load()	
	{
		for (int i =1;i<=52;i++){
			stopki[0].add(new karta("c:\\karta\\k"+(i)+".png",rubashka,i));
		}
		
		
	}

	//метод отрисовки всех стопок карт
	public void drawKoloda(Graphics gr)
	{
		if(stopki[0].size()>0)
		{
			stopki[0].get(stopki[0].size()-1).draw(gr);
		}
	if(stopki[1].size()>1)
	{
		stopki[1].get(stopki[1].size()-2).draw(gr);
		stopki[1].get(stopki[1].size()-1).draw(gr);
	}
	else if(stopki[1].size()==1)
	{
		
		stopki[1].get(stopki[1].size()-1).draw(gr);	
	}
	//Четыри домашних строчки
	for(int i=2;i<=5;i++){
		
		if(stopki[i].size()>1){
			stopki[i].get(stopki[i].size()-2).draw(gr);
			stopki[i].get(stopki[i].size()-1).draw(gr);
		}
		else if (stopki[i].size()==1)
		{
			stopki[i].get(stopki[i].size()-1).draw(gr);
		}
		
		
	}
	
	//Нижние семь стопок
	for(int i=6;i<13;i++){
		if(stopki[i].size()>0){
			for(int j=0;j<stopki[i].size();j++)
			{
				stopki[i].get(j).draw(gr);
			}
		}
	}
	
	}
	


}
