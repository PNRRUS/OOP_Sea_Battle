package ru.semikov.sea.swing;

import java.util.ArrayList;
import java.util.Iterator;

import ru.semikov.sea.logic.FieldShip;
import ru.semikov.sea.logic.Robot;

/**
 * ������������� �������� ����
 */
public class GameModel {
	private ArrayList<ISubscriber> listeners = new ArrayList<ISubscriber>();
	public FieldShip playerFiledA;
	public FieldShip playerFiledB;
	public Robot robot;
	public int currentPlayer;

	/**
	 * �������� ������ �������� �����
	 */
	public GameModel() {
		this.currentPlayer = 0;
		playerFiledA = new FieldShip();
		playerFiledB = new FieldShip();
		robot = new Robot(playerFiledA);
	}

	/**
	 * ������������ �������� ������ 
	 */
	public void newGame() {
		playerFiledA.PutShip();
		playerFiledB.PutShip();
		updateSubscribers();
	}

	/**
	 * ������� �� �������� ������
	 */
	public void doShotByOpponent(int x, int y) {
		// ���� ����� ��������� �����
		if (currentPlayer == 0) {
			// ���� ������������
			if (playerFiledB.doShot(x, y) == false) {
				currentPlayer = 1;
			}
		}
		
		// ���� ����� ���������
		if (currentPlayer ==1) {
			while (robot.move());
			currentPlayer = 0;
		}
		updateSubscribers();
	}
	
	/**
	 * ����������� ���������, ������� ����� ��������� ��� ����������� ������ �� �����
	 */
	public void register(ISubscriber o) {
		listeners.add(o);
		o.update();
	}
	
	/**
	 * �������������� �������� �����������
	 */
	public void unRegister(ISubscriber o) {
		listeners.remove(o);
	}
	
	/**
	 * ����������� ������ � �������������
	 */
	public void updateSubscribers() {
		Iterator<ISubscriber> i = listeners.iterator();
		while(i.hasNext()) {
			ISubscriber o = (ISubscriber)i.next();
			o.update();
		}
	}

}
