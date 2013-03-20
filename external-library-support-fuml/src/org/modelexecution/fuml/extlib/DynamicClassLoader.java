package org.modelexecution.fuml.extlib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class that creates an instance of {@link ClassLoader} as its member by taking
 * file path(s) to JAR file(s)
 * 
 * @author Patrick Neubauer
 * 
 */
public class DynamicClassLoader {

	private URL[] jarURLs; // ABSOLUTE paths linking to JAR files in the file
							// system
	private ClassLoader classLoader;

	/**
	 * Creates an instance of the class from which a {@link ClassLoader} can be
	 * obtained
	 * 
	 * @param jarPaths
	 *            RELATIVE paths pointing to the JAR file in the file system
	 */
	public DynamicClassLoader(String... jarPaths) {
		jarURLs = getJarPaths(jarPaths);
		classLoader = new URLClassLoader(jarURLs);
	}

	/**
	 * Get an array of URLs pointing to the JAR file(s) in the file system using
	 * ABSOLUTE path(s)
	 * 
	 * @param jarPaths
	 *            String array of RELATIVE paths linking to the JAR location in
	 *            the file system
	 * @return ABSOLUTE paths linking to JAR files in the file system
	 */
	private URL[] getJarPaths(String[] jarPaths) {
		URL[] urls = new URL[jarPaths.length];

		for (int i = 0; i < jarPaths.length; i++) {
			try {
				urls[i] = new File(jarPaths[i]).toURI().toURL();
			} catch (MalformedURLException e) {
				System.out.println("A JAR path (" + jarPaths[i]
						+ ") couldn't be added to the array of URLs pointing to the file in the file system. Details: " + e);
			}
		}

		return urls;
	}

	/**
	 * Get a {@link ClassLoader} to load a Class from
	 * 
	 * @return the classLoader from which Classes of the loaded JAR file(s) can
	 *         be loaded
	 */
	public ClassLoader getClassLoader() {
		return classLoader;
	}

}
