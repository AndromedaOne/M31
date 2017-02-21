package Utilities;

import java.io.IOException;
import java.io.OutputStream;

public class MultipleOutputStream extends OutputStream
{
	private OutputStream[] m_outputStreams;
	public MultipleOutputStream(OutputStream... outputStreams)
	{
		m_outputStreams=outputStreams;
	}
	@Override
	public void write(int i) throws IOException {
		for (OutputStream out: m_outputStreams){
			out.write(i);
		}
	}
	public void write(byte[] b) throws IOException {
		for (OutputStream out: m_outputStreams){
			out.write(b);
		}
	}
	public void write(byte[] b,int off, int len) throws IOException { 
		for (OutputStream out: m_outputStreams){
			out.write(b, off, len);
		}
	}
	public void flush() throws IOException {
		for (OutputStream out: m_outputStreams){
			out.flush();
		}
	}
	public void close() throws IOException {
		for (OutputStream out: m_outputStreams){
			out.close();
		}
	}
}

