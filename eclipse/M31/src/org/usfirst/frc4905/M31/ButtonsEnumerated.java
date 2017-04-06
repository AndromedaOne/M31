package org.usfirst.frc4905.M31;

public enum ButtonsEnumerated {
	ABUTTON(1),
	BBUTTON(2),
	XBUTTON(3),
	YBUTTON(4),
	LEFTBUMPERBUTTON(5),
	RIGHTBUMPERBUTTON(6),
	BACKBUTTON(7),
	STARTBUTTON(8),
	LEFTSTICKBUTTON(9),
	RIGHTSTICKBUTTON(10);
	
	private int m_buttonValue;
	
	private ButtonsEnumerated(int value) {
		this.m_buttonValue = value;
	}
	
	public int getValue() {
		return m_buttonValue;
		
	}
}
