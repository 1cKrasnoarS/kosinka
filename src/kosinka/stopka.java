package kosinka;


import java.util.ArrayList;


public class stopka {
	
	//������ ��������
	private ArrayList<karta> lst;
	
	//����������� ������
	public stopka()
	{
		//�������� ������� ������ ��������
		lst = new ArrayList<karta>();
	}
	//�������� ����� �� ������ �� ������ (1)
	public karta get(int nom)
	{
		return lst.get(nom);
	}
	
	//���������� ����� ����� �� ������ (2)
	public void add (karta elem)
	{
		lst.add(elem);
	}
	
	//�������� ����� �� ������ �� ������ (3)
	public void remove(int nom){
		lst.remove(nom);
	}
	
	//��������� ����������� ��������� �� ������ (4)
	public int size(){
		return lst.size();
	}
	
	//�������� ���� ��������� ������ (5)
	public void clear(){
		lst.clear();
	}

}
