package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
// singleton pattern. on the first call to Trace.getInstance(), the utility will
// create a trace directory in /home/lvuser/traceLogs/trace<next number>. the utility
// uses a file, .traceNumb, that is written in the traceLogs dir to store the next 
// number to use. if the .traceNumb file does not exist, the next number will be 0
// and a .traceNumb file will be created containing the number 1. when the robot
// code is restarted, the number is read from the file and then incremented and stored
// back. 
// to use this utility you simply call 
// Trace.getInstance().addTrace(...) at the point where you want to trace some
// interesting values. the addTrace signature is as follow:
// addTrace(<filename for trace file, .csv will be appended>,
// 			new TracePair(<name of this column>, <value to be written>),
//  		new TracePair(...),
//			<as many items as you want to log>);
//
// on the first call to this method with a unique filename, 
// this method will create the csv file to store the data as well as the
// header row with the names of the columns. it will then store the set
// of values. on subsequent calls to this method with a filename that has already
// been encountered, the method will simply store the values passed in.
// an example for tracing the navx:
// Trace.getInstance().addTrace("NavxGyro", 
// 		new TracePair("Raw Angle", m_navX.getAngle()),
//		new TracePair("X Accel", (double) m_navX.getWorldLinearAccelX()),
//		new TracePair("X Accel", (double) m_navX.getWorldLinearAccelY()),
//		new TracePair("Z Accel", (double) m_navX.getWorldLinearAccelZ()));
//
// on the first call a file NavxGyro will be created in the trace directory.
// the header: Raw Angle, X Accel, Y Accel, Z Accel will be written to the file along
// with the angle, x, y and z values. on subsequent calls only the values will be
// written.
// once all tracing in complete you need to call closeTraceFiles() if possible.
// you can also call flush periodically so that values are flushed out to the trace
// file so that at least something will be written out before the robot it turned 
// off.
public class Trace 
{
	private String m_basePathOfTraceDirs = "/home/lvuser/traceLogs";
	private static String m_traceDirNumberFile = ".traceNumb";
	private String m_pathOfTraceDir;
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
		createNewTraceDir();
		redirectOutput();
		
	}
	
	private String getDateStr()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
		Date date = new Date();
		String dateStr = new String(dateFormat.format(date));
		return(dateStr);
	}
	
	private void createNewTraceDir()
	{
		try {
			File directory = new File(m_basePathOfTraceDirs);
			if(!directory.exists()) {
				if(!directory.mkdir()) {
					System.err.println("ERROR: failed to create directory " 
							+ m_basePathOfTraceDirs +
							" for tracing data.");
					m_basePathOfTraceDirs = null;
					m_pathOfTraceDir = null;
					return;
				}
			}
			// open the trace dir number file to retrieve the number to concatenate
			// to the trace dir
			int dirNumb = 0;
			String traceNumFileName = m_basePathOfTraceDirs + "/" + m_traceDirNumberFile;
			File traceNumbFile = new File(traceNumFileName);
			if(!traceNumbFile.exists()) {
				System.out.println("Trace numb file does not exist");
				m_pathOfTraceDir = m_basePathOfTraceDirs + "/" + "trace0";
			} else {
				System.out.println("Found trace numb file: " + traceNumFileName);
				BufferedReader reader = new 
					BufferedReader(new FileReader(traceNumbFile));
				String line = reader.readLine();
				reader.close();
				traceNumbFile.delete();
				if(line == null) {
					System.err.println("ERROR: failed to read trace file number file: " 
							+ m_basePathOfTraceDirs + m_traceDirNumberFile);
					m_pathOfTraceDir = null;
					return;
				}
				m_pathOfTraceDir = m_basePathOfTraceDirs + "/trace" + line;
				dirNumb = Integer.parseInt(line);
			}
			File traceDir = new File(m_pathOfTraceDir);
			if(!traceDir.exists()) {
				if(!traceDir.mkdirs()) {
					System.err.println("ERROR: failed to create directory " 
							+ m_pathOfTraceDir +
							" for tracing data.");
					m_basePathOfTraceDirs = null;
					m_pathOfTraceDir = null;
					return;
				}
			}
			FileWriter fstream = new FileWriter(traceNumFileName, false);
			BufferedWriter dirNumbFile = new BufferedWriter(fstream);
			System.out.println("Created trace file " + m_basePathOfTraceDirs + m_traceDirNumberFile);
			++dirNumb;
			dirNumbFile.write(Integer.toString(dirNumb));
			dirNumbFile.close();
		}
		catch(SecurityException e) {
			System.err.println("ERROR: unable to create trace directory"
					+ e.getMessage());
			e.printStackTrace();
			m_pathOfTraceDir = null;
		}
		catch(FileNotFoundException e) {
			System.err.println("ERROR: unable to open trace dir");
			e.printStackTrace();
			m_pathOfTraceDir = null;
		} catch (IOException e) {
			System.err.println("ERROR: unable to open trace dir");
			e.printStackTrace();
			m_pathOfTraceDir = null;
		}
	}

	public void addTrace(String fileName, TracePair... header) {
		if(m_pathOfTraceDir == null)
		{
			return;
		}
		try {
			if(!m_traces.containsKey(fileName)) {
				BufferedWriter outputFile = null;
				String fullFileName = new String(m_pathOfTraceDir  + "/" + fileName + ".csv");
				FileWriter fstream = new FileWriter(fullFileName, false);
				outputFile = new BufferedWriter(fstream);
				m_traces.put(fileName, new TraceEntry(outputFile, header.length));
				String line = new String("Time");
				for(TracePair pair : header) {
					line += "," + pair.getColumnName();
				}
				outputFile.write(line);
				outputFile.newLine();
				System.out.println("Opened trace file " + m_pathOfTraceDir + "/" + fileName);
			}
			addEntry(fileName, header);
		}
		catch(IOException e) {
			System.err.println("ERROR: unable to open/write to trace file " + 
					fileName + " ;" + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private void addEntry(String fileName, TracePair... values) {
		try {
			if(m_pathOfTraceDir == null)
			{
				return;
			}
			if(!m_traces.containsKey(fileName)) {
				String err = new String("Warning: trace file " + fileName);
				err += " has not been added to the Trace instance";
				throw(new Exception(err));
			}
			TraceEntry traceEntry = m_traces.get(fileName);
			if(values.length != traceEntry.getNumbOfValues()) {
				String err = new String("ERROR: trace entry for " + fileName + " has ");
				err += String.valueOf(values.length) + " but should have ";
				err += String.valueOf(traceEntry.getNumbOfValues());
				throw(new Exception(err));
			}
			long correctedTime = System.currentTimeMillis() - m_startTime;
			String line = new String(String.valueOf(correctedTime));
			for(TracePair entry : values) {
				line += "," + entry.getValue();
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
		if(m_pathOfTraceDir == null)
		{
			return;
		}
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
			if(m_pathOfTraceDir == null)
			{
				return;
			}
			FileOutputStream fOut= new FileOutputStream(m_pathOfTraceDir + "/" + 
					m_consoleOutput + ".log");
			m_out= new MultipleOutputStream(System.out, fOut);
			m_err= new MultipleOutputStream(System.err, fOut);
			PrintStream stdOut= new PrintStream(m_out);
			PrintStream stdErr= new PrintStream(m_err);
			System.setOut(stdOut);
			System.setErr(stdErr);
			System.out.println("Redirected console output.");
		}
		catch (FileNotFoundException E) {
			System.err.println("ERROR: Redirect Failed");
		}
	}
	
	public void matchStarted() {
		if(m_pathOfTraceDir == null)
		{ 
			return;
		}
		BufferedWriter outputFile = null;
		try {
			String fullFileName = new 
					String(m_pathOfTraceDir  + "/" + m_matchStartFname + ".txt");
			FileWriter fstream = new FileWriter(fullFileName, false);
			outputFile = new BufferedWriter(fstream);
			outputFile.write("Match Started @" + getDateStr());
			outputFile.flush();
			outputFile.close();
			System.out.println("Logged match started.");
		}
		catch(IOException e) {
			System.err.println("ERROR: unable to open text file " + m_matchStartFname + " ;"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}
