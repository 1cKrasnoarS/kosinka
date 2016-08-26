package kosinka;


import java.util.ArrayList;


public class stopka {
	
	//Список значений
	private ArrayList<karta> lst;
	
	//Конструктор класса
	public stopka()
	{
		//Создание пустого списка значений
		lst = new ArrayList<karta>();
	}
	//Получаем карты из списка по номеру (1)
	public karta get(int nom)
	{
		return lst.get(nom);
	}
	
	//Добовление новой карты из списка (2)
	public void add (karta elem)
	{
		lst.add(elem);
	}
	
	//Удаление карты из списка по номеру (3)
	public void remove(int nom){
		lst.remove(nom);
	}
	
	//Получение колличества элементов по списку (4)
	public int size(){
		return lst.size();
	}
	
	//Удаление всех элементов списка (5)
	public void clear(){
		lst.clear();
	}

}
