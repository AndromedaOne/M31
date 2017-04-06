package org.usfirst.frc4905.M31;

public enum EnumeratedRawAxis {
	LEFTSTICKHORIZONTAL(0),
	LEFTSTICKVERTICAL(1),
	LEFTTRIGGER(2),
	RIGHTTRIGGER(3),
	RIGHTSTICKHORIZONTAL(4),
	RIGHTSTICKVERTICAL(5);
	
	private int m_rawAxisValue;
	
	private EnumeratedRawAxis(int value) {
		this.m_rawAxisValue = value;
		
	}
	public int getValue() {
		return m_rawAxisValue;
	}
}
