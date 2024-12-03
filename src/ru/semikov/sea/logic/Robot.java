package ru.semikov.sea.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * ������������� ��������  
 */
public class Robot {

	public FieldShip field;
	public int x, y;
	Random rand;
	
	/**
	 * �������� ������ ��� ���������� ����
	 * �������� ��� ������������ ����  
	 */
	public Robot(FieldShip field) {
		this.field = field;
		this.rand = new Random();
	}
	
	
	/**
	 * ������� ������� �������
	 * @return
	 */
	public boolean tryShot() {
		ArrayList<Element> list = new ArrayList<Element>();
		
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				Element e = field.elements[i][j];
				if (!e.shuted) { 
					list.add(e);
				}
			}
		}
		Element e = list.get(rand.nextInt(list.size()));
		return field.doShot(e.x, e.y);
	}
	
	/**
	 * ������� ���
	 */
	public boolean move() {
		boolean finded = false;
		// ���� �������� ������� �������
		first: for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				Element element = field.elements[i][j];
				if (element.state == ElementStates.enInjured) {
					// ����� �������� ������� �������
					finded = true;
					x = i;
					y = j;
					break first;
				}
			}
		}
/*		
		if (finded) {
			//TODO �������� ����� ����������
			System.out.printf("finded> %s,%s\n", x, y);
			
			ArrayList<Element> list = new ArrayList<Element>();
			
			// ������� �������� ������ �������� �������
			// ������ ��������� �������
			for(int j = 0; j < 2; j++) {
				int a = x;
				int b = y + j*2-1;
				if ( (b < 0) || (b>9) ) {
					continue;
				}
				if (!field.elements[a][b].shuted) {
					list.add(field.elements[a][b]);
					System.out.printf("> %s,%s\n", a, b);
				}
			}
			for(int i = 0; i < 2; i++) {
				int a = x + i*2-1;
				int b = y;
				if ( (a < 0) || (a>9) ) {
					continue;
				}
				if (!field.elements[a][b].shuted) {
					list.add(field.elements[a][b]);
					System.out.printf("> %s,%s\n", a, b);
				}
			}
			Element e = list.get(rand.nextInt(list.size()));
			return field.doShot(e.x, e.y);
		}
*/
		return tryShot();
	}
	
	/**
	 * �������� ���������� X
	 * @return
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * �������� ���������� Y
	 * @return
	 */
	public int getY() {
		return this.y;
	}
	
}
