package Utilities;


public class TracePair
{
	public TracePair(String columnName, Double value) {
		m_columnName = columnName;
		m_value = value;
	}

	public String getColumnName() {
		return m_columnName;
	}

	public Double getValue() {
		return m_value;
	}

	private String m_columnName;
	private Double m_value;
}

