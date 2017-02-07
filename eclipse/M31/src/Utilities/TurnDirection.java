package Utilities;

public enum TurnDirection {
	ClockWise,
	CounterClockWise;

	public TurnDirection opposite() {
		switch (this) {
		case ClockWise:
			return CounterClockWise;
		default:
			return ClockWise;		
		}
	}

}
