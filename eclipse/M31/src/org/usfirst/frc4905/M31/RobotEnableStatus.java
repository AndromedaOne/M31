package org.usfirst.frc4905.M31;

public enum RobotEnableStatus {
	ENABLED(true), 
	DISABLED(false);
	
	private boolean m_enableStatus;
	private RobotEnableStatus(boolean enabled) {
		m_enableStatus = enabled;
	}
	public boolean getEnabled() {
		return m_enableStatus;
	}
}