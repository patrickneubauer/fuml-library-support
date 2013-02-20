package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.modelexecution.fuml.extlib.UML2UMLWithPlaceholderActivitiesConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UML2UMLWithPlaceholderActivitiesConverterTest {

	@Test
	public void addPlaceholderActivitiesToVehiclesUML() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "libraries/Vehicles.jar";
		
		UML2UMLWithPlaceholderActivitiesConverter converter = new UML2UMLWithPlaceholderActivitiesConverter(inputFilePath, jarFilePath);
		converter.converter(outputFilePath);
		
		// check if every uml:Class contains a ownedComment referring to the JAR file
		assertTrue(containsComments(outputFilePath, jarFilePath));
		
		// check if every ownedOperation in every uml:Class also has a corresponding ownedBehavior
		assertTrue(containsPlaceholderActivities(outputFilePath));
	}

	/**
	 * Checks if the output file created with the {@link UML2UMLWithPlaceholderActivitiesConverter} contains
	 * the necessary new ownedBehavior elements
	 * 
	 * @param outputFilePath file path of the output file that has been created by the {@link UML2UMLWithPlaceholderActivitiesConverter}outputFilePath file path of the output file that has been created by the {@link UML2UMLWithPlaceholderActivitiesConverter}
	 * @return true if it does contain the necessary new elements and false otherwise
	 */
	private boolean containsPlaceholderActivities(String outputFilePath) {
		try {
			Document outputDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(outputFilePath);
			// Cycle through the number of packagedElements
			for (int i = 0; i < outputDocument.getElementsByTagName("packagedElement").getLength(); i++) {
				// Get packagedElement and convert into an element to gain access to element methods
				Element outputPackagedElement = (Element) outputDocument.getElementsByTagName("packagedElement").item(i);
				
				// Check if the element has the attribute set
				if (outputPackagedElement.getAttribute("xmi:type").equals("uml:Class")) {
					// In "uml:Class", look for child node "ownedOperation"
					NodeList ownedOperations = outputPackagedElement.getElementsByTagName("ownedOperation");
					for(int j=0; j < ownedOperations.getLength(); j++) {
						Element ownedBehavior = (Element) outputPackagedElement.getElementsByTagName("ownedBehavior").item(j);
						
						assertTrue(ownedBehavior.getAttribute("xmi:id").contains("_placeholder"));
						assertTrue(ownedBehavior.getAttribute("xmi:type").equals("uml:Activity"));
					}
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			return false;
		}
		
		// At this point everything necessary has been found, therefore return true
		return true;
	}

	/**
	 * Checks if the output file created with the {@link UML2UMLWithPlaceholderActivitiesConverter} contains
	 * the necessary new ownedComment elements
	 * 
	 * @param outputFilePath file path of the output file that has been created by the {@link UML2UMLWithPlaceholderActivitiesConverter}
	 * @param jarFilePath file path of the JAR file
	 * @return true if it does contain the necessary new elements and false otherwise
	 */
	private boolean containsComments(String outputFilePath, String jarFilePath) {
		try {
			Document outputDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(outputFilePath);
			// Cycle through the number of packagedElements
			for (int i = 0; i < outputDocument.getElementsByTagName("packagedElement").getLength(); i++) {
				// Get packagedElement and convert into an element to gain access to element methods
				Element outputPackagedElement = (Element) outputDocument.getElementsByTagName("packagedElement").item(i);
				
				// Check if the element has the attribute set
				if (outputPackagedElement.getAttribute("xmi:type").equals("uml:Class")) {
					Element ownedComment = (Element) outputPackagedElement.getElementsByTagName("ownedComment").item(0);
					
					assertTrue(ownedComment != null);
					assertTrue(ownedComment.getElementsByTagName("body").item(0) != null);
					assertEquals("@external " + jarFilePath, ownedComment.getElementsByTagName("body").item(0).getTextContent());
					
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			return false;
		}
		
		// At this point everything necessary has been found, therefore return true
		return true;
	}
	
}
