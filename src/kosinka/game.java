package kosinka;

//���������� �������
import java.awt.event.*;

//��� ������ � ������ � ��������
import javax.swing.*;

//��� ������ � ������� 
import java.io.*;

//��� ������ � ����������
import javax.imageio.*;

//��� ������ � ��������
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
	
	//������ ����� �����
	public void mouseDragged(int mX,int mY)
	{}
	//��� ����� �������
	public void mousePressed(int mX,int mY)
	{}
	//��� ������� �������
	public void mouseDoubalePressed(int mX,int mY)
	{}
	//��� ���������� ����� ������ ����
	public void mouseReleased(int mX,int mY)
	{}
	//����������� ������ �� ����� ������ �����
	public int getNomKolodaPress(int mX,int mY)
	{
		return -1;		
	}
	
	//������ ���� �� ������� ����� ������
	private void vidacha()
	{}
	
	//����� ���� ����� ����
	public void start()
	{}
	
	//�������� ����������� �� ������
	private void load()	
	{
		for (int i =1;i<=52;i++){
			stopki[0].add(new karta("c:\\karta\\k"+(i)+".png",rubashka,i));
		}
		
		
	}

	//����� ��������� ���� ������ ����
	public void drawKoloda(Graphics gr)
	{}
	
	
	
	
	
	

}
