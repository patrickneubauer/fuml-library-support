package org.modelexecution.fuml.extlib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Debug {
	
	public enum DEBUG_MODE {
		SYSTEM_OUT_ONLY,
		SYSTEM_OUT_AND_FILE_OUT,
		FILE_OUT_ONLY,
		NONE
	}
	public static File DEBUG_FILE = new File("debug.out");
	public static DEBUG_MODE debugMode = DEBUG_MODE.SYSTEM_OUT_AND_FILE_OUT;
	public static Object previousObject = null;

	public static void out(String output) {
		if (debugMode == DEBUG_MODE.SYSTEM_OUT_ONLY) {
			System.out.println(output);
		} else if (debugMode == DEBUG_MODE.SYSTEM_OUT_AND_FILE_OUT) {
			System.out.println(output);
			appendToFile(output, true);
		} else if (debugMode == DEBUG_MODE.FILE_OUT_ONLY) {
			appendToFile(output, true);
		} else if (debugMode == DEBUG_MODE.NONE) {
			System.out.println("DEBUG_MODE = NONE");
		}
	}
	
	public static void out(Object object, String output) {
		if (debugMode == DEBUG_MODE.SYSTEM_OUT_ONLY) {
			if (previousObject != null && !previousObject.getClass().equals(object.getClass())) {
				System.out.println(""); // print an empty line
			}
			System.out.println("[" + object.getClass().getSimpleName() + "] " + output);
			
		} else if (debugMode == DEBUG_MODE.SYSTEM_OUT_AND_FILE_OUT) {
			if (previousObject != null && !previousObject.getClass().equals(object.getClass())) {
				System.out.println(""); // print an empty line
				appendToFile("", true); // append an empty line
			}
			System.out.println("[" + object.getClass().getSimpleName() + "] " + output);
			appendToFile("[" + object.getClass().getSimpleName() + "] " + output, true);
		
		} else if (debugMode == DEBUG_MODE.FILE_OUT_ONLY) {
			if (previousObject != null && !previousObject.getClass().equals(object.getClass())) {
				appendToFile("", true); // append an empty line
			}
			appendToFile(output, true);
		} 
		
		previousObject = object;
	}
	
	public static void clearDebugFile() {
		if (DEBUG_FILE == null) {
			try {
				DEBUG_FILE.createNewFile();
			} catch (IOException e) {
			    System.err.println("Could not create debug file " + DEBUG_FILE.getAbsolutePath() + " because of the following error: " + e);
			}
		}
		appendToFile("", false);
	}
	
	public static String readFile() {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(DEBUG_FILE.getAbsolutePath()));
			return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
		} catch (IOException e) {
		    System.err.println("Could not read from debug file " + DEBUG_FILE.getAbsolutePath() + " because of the following error: " + e);
		}
		return "Could not read from debug file " + DEBUG_FILE.getAbsolutePath();
	}
	
	/**
	 * @param output
	 */
	private static void appendToFile(String output, boolean append) {
		try {
		    PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(DEBUG_FILE, append)));
		    printWriter.println(output);
		    printWriter.close();
		} catch (IOException e) {
		    System.err.println("Could not write to debug file " + DEBUG_FILE.getAbsolutePath() + " because of the following error: " + e);
		}
	}

}
