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
	//Установка выбранной карты
	
	private void setVibrane(int nom, int mX, int mY){
		
		//Если верхние стопки (1,2,3,4,5)
		
		if((nom>=1)&&(nom<=5))
		{
			//Если в стопке есть карыт
			if(stopki[nom].size()>0)
			{
				//Получаем номер верхней карты
				int nomPosled = stopki[nom].size()-1;
				
				//получаем верхнюю карту
				karta getKarta = stopki[nom].get(nomPosled);
				//Устанавливаем признак выбранной карты
				getKarta.vibrana = true;
				//Номер выбранной карты
				nomKarti = nomPosled;
				//Номер выбранной стопки
				nomStopki = nom;
				//Смещенния курсора мыши
				dx= mX - getKarta.x;
				dy= mY - getKarta.y;
				
				//Запоминаем текущие координаты карты
				oldX = getKarta.x;
				oldY = getKarta.y;		
			}
		}
		//Если нижние семь стопок. 
		else if ((nom>=6)&&(nom<=12))
		{
			//Если в стопке есть карты
			if(stopki[nom].size()>0)
			{
				//Получаем номер верхней карты
				int nomPosled = stopki[nom].size()-1;
				//Получаем верхнююю карту
				karta getKarta = stopki[nom].get(nomPosled);
				int nomVibrana = -1;
				//Если выбрана самая верхняя карта
				if((mY>=getKarta.y)&&(mY<=(getKarta.y+97)))
				{
					nomVibrana = nomPosled;
				}
				//Если выбрана НЕ самая верхняя карта
				else if (mY<getKarta.y)
				{
					//вычисляем номер выбранной карты 
					nomVibrana = (mY-130)/20;
					if(stopki[nom].get(nomVibrana).tipRubashka==true)
					{
						nomVibrana = -1;
					}
				}
				//Если карты выбрана
				if(nomVibrana!=-1){
					//Получаем выбранную карту
					karta getKartaVibrana = stopki[nom].get(nomVibrana);
				//Если карта открыта рубашкой
					if(getKartaVibrana.tipRubashka==false){
						//Установление признак выбранной 
						getKartaVibrana.vibrana=true;
						//Номер выбранной карты
						nomKarti = nomVibrana;
						//Номер выбранной стопки
						nomStopki = nom;
							//Смещения курсора мыщи
						dx = mX - getKartaVibrana.x;
						dy = mY - getKartaVibrana.y;
						
						//Запоминанием текущие координаты карты
						oldX = getKartaVibrana.x;
						oldY = getKartaVibrana.y;
						
						
					}
				
				}
				
			}
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
	
	/*//захват карты мышью
	public void mouseDragged(int mX,int mY)
	{
		//если стопка выбрана
		if(nomStopki>=0){
			//получаем выбранную карту
			karta getKarta = stopki[nomStopki].get(nomKarti);
			//именяем координаты карты по курсору мышы
			getKarta.x = mX-dx;
			getKarta.y = mY-dy;
			
			//Ограничение области переноса карт
			if(getKarta.x<0)getKarta.x=0;
			if(getKarta.x>720)getKarta.x=720;
			if(getKarta.y<0)getKarta.y = 0;
			if(getKarta.y>650)getKarta.y=650;
			
			//Все оставльные карыт в переносимой группе карт
			//размещаем с сдвигом вниз на 20 пикселей
			int y =20;
			for (int i =nomKarti+1;i<stopki[nomStopki].size();i++)
			{
				stopki[nomStopki].get(i).x = getKarta.x;
				stopki[nomStopki].get(i).y = getKarta.y+y;
				y+=20;
			}
		}
		
		
	}*/
	//при одном нажатии
	public void mousePressed(int mX,int mY)
	{
		//определяем номер стопки
		int nom = getNomKolodaPress(mX, mY);
		//Устанавливаем выбранную карту
		setVibrane(nom, mX, mY);
		
	}
	//при двойном нажатии
	public void mouseDoubalePressed(int mX,int mY)
	{
		//определяем номер стопки
		int nom = getNomKolodaPress(mX, mY);
		//Если это нижняя стопка или с номером 1
		if ((nom==1)||((nom>=6)&&(nom<=12)))
		{
			//Есил в стопке есть карты
			if(stopki[nom].size()>0)
			{
				//Номер верхней карты
				int nomPosled = stopki[nom].size()-1;
				//Получаем верхнюю карту
				karta getKarta = stopki[nom].get(nomPosled);
				
				if((mY>=getKarta.y)&&(mY<=(getKarta.y+97))
				{//Перебираем четыре домашнии стопки
					for(int i =2;i<=6;i++){
						//Результат поска подходящей домащней стопки
						int rez = -1;
						//Если домашняя стопка пустая
						if(stopki[i].size()==0)
						{
							//Если переносимая карта - туз
							if (getKarta.tipKarta==12)
							{
								//запоминаем номер домашней стопки
								rez=i;
								
							}
							//Если домашняя стопка уже не пустая
							else
							{
								//Получаем номер последней карты в домашений стопке
								int nomPosled2 = stopki[i].size()-1;
								//Получаем саму карту
								karta getKarta2 = stopki[i].get(nomPosled2);
								//Если эта карта в домашней стопке - туз, переносим и их масти совпадают
								if((getKarta2.tipKarta==12)&&
								(getKarta.tipKarta==0))
								{
									//Запоминаем номер домашней стопки
									rez =i;
								}
								//Если эта карта в домашней стопке НЕ туз,
								//а их масти совпадают
								else if((getKarta2.tipKarta>=0)&&(getKarta2.tipKarta<11)&&
								(getKarta.mast==getKarta2.mast))
									{
										//Если переносимая карта на одно уровень старше
					if((getKarta2.tipKarta+1==getKarta.tipKarta))
					{
						//запоминаем номер домашней стопки
						rez=i;
										}
									}
							
							}
							//Если удалось найти подходящую домашнюю стопку
							if(rez>=0)
							{
								//Изменяем координаты на домашнюю стопку
								getKarta.x =(110*(rez+1))+30;
								getKarta.y = 15;
								//Добавшяем в домашняя стопка
								stopki[rez].add(getKarta);
								//Удаляем из старой стопки
								stopki[nom].remove(nomPosled);
								//Проверяем конец игры
								testEndGame();
								//Прерываем цикл
								break;
							}
						}
					}
					
				}
			}
			
			//Отрываем верхняя карту 
			openKarta();
			
		}
	}
	//при отпускании левой кнопки мыши
	public void mouseReleased(int mX,int mY)
	{
		int nom=getNomKolodaPress(mX,mY);
	if(nom==0)
	{
	vidacha();	




	}

	}
	
	
	private boolean testPerenos(int nom1, int nom2){
		//Результат проверки
		boolean rez = false;
		//Карта которую нужно пеносить
		karta getKarta1 = stopki[nom1].get(nomKarti);
		karta getKarta2 = null;
		
		//Если карта в стопки
		if (stopki[nom2].size()>0)
				{
					//Получаем верхнюю карту
			getKarta2 =stopki[nom2].get(stopki[nom2].size()-1);
				}
		//Если четыре домашние стопки
		if((nom2>=2)&&(nom2<=5)){
			if(nomKarti==(stopki[nom1].size()-1))
			{
				//Если стопка была пустая
				if(getKarta2==null)
				{
					//Если переносимая карта ТУЗ
					if(getKarta1.tipKarta==12)rez=true;
								
				}
				//Если в домашней стопке ТУЗ, переносится
				//ДВОЙКА и масти совпадают
				else if ((getKarta2.tipKarta==12)
						&&(getKarta1.mast==getKarta2.mast)
						&&(getKarta1.tipKarta==0))
				{
					rez = true;
				}
				
				//Если в домашней стопке не ТУЗ
				//но масти совпадают
				else if((getKarta2.tipKarta>=0)
						&&(getKarta2.tipKarta<11)
						&&(getKarta1.mast==getKarta2.mast))
				{
					//Если резултаты проверки положительный
					if(rez==true)
					{
						//Переносим карту в домашнюю стопку
						getKarta1.x = (110*(nom2+1))+30;
						getKarta1.y = 15;
						stopki[nom2].add(getKarta1);
						stopki[nom1].remove(nomKarti);
						testEndGame();
					}
				}					
			}
			//Если перенос в нижние стопки
			if((nom2>=6)&&(nom2<12))
			{
				int x = 30+(nom2-6)*110;
				int y = 130;
				//Если нижняя стопка была пустая
				if(getKarta2==null)
				{
					//Если переносится КОРОЛЬ
					if(getKarta1.tipKarta==11)rez=true;
				}
				else //Если была НЕ пустая
				{
					//Если верхняя
				}
			}
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
	
	//ниже семь стопок
	for(int i =6;i<13;i++){
		
		if(stopki[i].size()>0)
		{
			//перебираем все карты из стопки
			for(int j=0;j<stopki[i].size();j++)
			{
				//если находи выбранную карту
				//то прерываем цикл
				if(stopki[i].get(j).vibrana==true)break;
				//рисуем карты
				stopki[i].get(j).draw(gr);
			}
		}
	}
	
	//переносимые мышью карты
	//если имеется выбранная стопка
	if(nomStopki!=-1)
	{
		for(int i = nomKarti;i<stopki[nomStopki].size();i++)
		{
			stopki[nomStopki].get(i).draw(gr);
		}
	}
	
	}
	///При захвате карты мышью
	
	public void mouseDragged(int mX,int mY)
	{
		if (nomStopki>=0)
		{
			//Полуычаем выбранныую карту
			karta getKarta = stopki[nomStopki].get(nomKarti);
			//Изменяем координаты карты по курсору мышки
			getKarta.x = mX-dx;
			getKarta.y = mY-dy;
			
			//Ограничение области переноса карт
			if(getKarta.x<0) getKarta.x=0;
			if(getKarta.x>720) getKarta.x = 720;
			if(getKarta.y<0) getKarta.y = 0;
			if(getKarta.y>650) getKarta.y = 650;
			
			//Все остальные карты в переносимой группе
			//размещаем со сдвигом вниз на 20 пикселей
			
			int y =20;
			for(int i=nomKarti+1;i<stopki[nomStopki].size();i++)
			{
				stopki[nomStopki].get(i).x = getKarta.x;
				stopki[nomStopki].get(i).y = getKarta.y +y;
				y+=20; //  
			}
		}
	}
	
	
	
	
	
}
