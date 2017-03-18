package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

// utility to store trace information to a file on the roborio. this class uses the 
// singleton pattern. to use this you must add a filename to the trace instance with
// a vector of strings that contain the header information. for example if you want to
// trace a PID controller that is turning the robot with the gyro you can do this:
// Vector<String> header = new Vector<String>();
// header.add(new String("compass heading"));
// header.add(new String("output")); 
// ... as many columns as needed
// Trace.getInstance().addTrace("GyroPID", header);
// now you can start adding entries. the trace instance remembers the filename
// and number of columns in the header
// to add an entry to the following:
// Vector<Double> entry = new Vector<Double>();
// entry.add(new Double(gyro.getHeading());
// entry.add(new Double(output));
// Trace.getInstance().addEntry("GyroPID", entry);
// each entry is time stamped with the number of milliseconds since the Trace instance
// was created.
// once all tracing in complete you need to call closeTraceFiles()
public class Trace 
{
	private static String m_pathOfFile = "/home/lvuser/traceLogs";
	private static String m_consoleOutput = "ConsoleOutput";
	private static Trace m_instance;
	private Map<String, TraceEntry> m_traces;
	private long m_startTime = 0;
	private MultipleOutputStream m_out;
	private MultipleOutputStream m_err;
	private static String m_matchStartFname = "matchStarted";
	
	private class TraceEntry {
		private BufferedWriter m_file;
		private int m_numbOfValues;
		
		public TraceEntry(BufferedWriter file, int numbOfValues) {
			m_file = file;
			m_numbOfValues = numbOfValues;
		}
		
		public BufferedWriter getFile() {
			return(m_file);
		}
		
		public long getNumbOfValues() {
			return(m_numbOfValues);
		}
	}
	
	public static Trace getInstance() {
		if(m_instance == null) {
			m_instance = new Trace();
		}
		return(m_instance);
	}
	
	private Trace() {
		m_traces = new TreeMap<String, TraceEntry>();
		m_startTime = System.currentTimeMillis();
		redirectOutput();
		
	}
	
	public void addTrace(String fileName, Vector<String> header) {
		if(m_traces.containsKey(fileName)) {
			System.out.println("Warning: trace " + fileName + " already exists.");
			return;
		}
		BufferedWriter outputFile = null;
		try {
			File directory = new File(m_pathOfFile);
			if(!directory.exists()) {
				if(!directory.mkdir()) {
					System.err.println("ERROR: failed to create directory " + m_pathOfFile +
							" for tracing data.");
				}
			}
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
			Date date = new Date();
			String dateStr = new String(dateFormat.format(date));
			String fullFileName = new String(m_pathOfFile  + "/" + fileName + dateStr + ".csv");
			FileWriter fstream = new FileWriter(fullFileName, false);
			outputFile = new BufferedWriter(fstream);
		}
		catch(IOException e) {
			System.err.println("ERROR: unable to open trace file " + fileName + " ;"
					+ e.getMessage());
			e.printStackTrace();
		}
		m_traces.put(fileName, new TraceEntry(outputFile, header.size()));
		String line = new String("Time");
		for(String name : header) {
			line += "," + name;
		}
		try {
			outputFile.write(line);
			outputFile.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Opened trace file " + m_pathOfFile + "/" + fileName);
	}
	
	public void addEntry(String fileName, Vector<Double> values) {
		try {
			if(!m_traces.containsKey(fileName)) {
				String err = new String("Warning: trace file " + fileName);
				err += " has not been added to the Trace instance";
				throw(new Exception(err));
			}
			TraceEntry traceEntry = m_traces.get(fileName);
			if(values.size() != traceEntry.getNumbOfValues()) {
				String err = new String("ERROR: trace entry for " + fileName + " has ");
				err += String.valueOf(values.size()) + " but should have ";
				err += String.valueOf(traceEntry.getNumbOfValues());
				throw(new Exception(err));
			}
			long correctedTime = System.currentTimeMillis() - m_startTime;
			String line = new String(String.valueOf(correctedTime));
			for(Double entry : values) {
				line += "," + entry.toString();
			}
			traceEntry.getFile().write(line);
			traceEntry.getFile().newLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void flushTraceFiles() {
		if(m_traces != null) {
			// new lambda functionality!!
			m_traces.forEach((k,v) -> {
				try {
					v.getFile().flush();
					System.out.println("Flushing file " + k);
				} catch (IOException e) {
					System.err.println("ERROR: failed to flush trace file" + k);
					e.printStackTrace();
				}
			});
		}
		try {
			m_out.flush();
			m_err.flush();
		} catch (IOException e) {
			System.err.println("ERROR: Failed to Flush");
			e.printStackTrace();
		}
		
	}
	private void redirectOutput(){
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
			Date date = new Date();
			String dateStr = new String(dateFormat.format(date));
			FileOutputStream fOut= new FileOutputStream(m_pathOfFile + "/" + 
					m_consoleOutput + dateStr + ".log");
			m_out= new MultipleOutputStream(System.out, fOut);
			m_err= new MultipleOutputStream(System.err, fOut);
			PrintStream stdOut= new PrintStream(m_out);
			PrintStream stdErr= new PrintStream(m_err);
			System.setOut(stdOut);
			System.setErr(stdErr);
		}
		catch (FileNotFoundException E) {
			System.err.println("ERROR: Redirect Failed");
		}
	}
	public void matchStarted() {
		BufferedWriter outputFile = null;
		try {
			File directory = new File(m_pathOfFile);
			if(!directory.exists()) {
				if(!directory.mkdir()) {
					System.err.println("ERROR: failed to create directory " + m_pathOfFile +
							" for match start data.");
				}
			}
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
			Date date = new Date();
			String dateStr = new String(dateFormat.format(date));
			String fullFileName = new String(m_pathOfFile  + "/" + m_matchStartFname + dateStr + ".txt");
			FileWriter fstream = new FileWriter(fullFileName, false);
			outputFile = new BufferedWriter(fstream);
			outputFile.write("Match Started @" + dateStr);
			outputFile.flush();
			outputFile.close();
		}
		catch(IOException e) {
			System.err.println("ERROR: unable to open text file " + m_matchStartFname + " ;"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}
