package org.modelexecution.fuml.extlib;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UML2UMLWithPlaceholderActivitiesConverter {
	
	private String inputFilePath;
	private String jarFilePath;
	
	private Document inputDocument;
	private Document outputDocument;
	
	public UML2UMLWithPlaceholderActivitiesConverter(String inputFilePath, String jarPath) {
		this.inputFilePath = inputFilePath;
		this.jarFilePath = jarPath;
	}
	
	/**
	 * Initializes the input {@link Document} and output {@link Document} with the given path
	 */
	private void initalizeDocuments() {
		try {
			inputDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFilePath);
			outputDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFilePath); // initially the same as inputDoc !
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("Error occured during the initialization of the input and output XML document: " + e);
		}
	}
	
	/**
	 * Append a comment to every UML class that references to the external JAR file.
	 * 
	 * Example result:
	 *   <ownedComment xmi:id="_oA2b0FHQEeKwiMIhkV3wDA_comment">
	 *     <body>@external libraries/Vehicles.jar</body>
	 *   </ownedComment>
	 */
	private void appendCommentToUMLClasses() {
		// Cycle through the number of packagedElements
		for (int i = 0; i < inputDocument.getElementsByTagName("packagedElement").getLength(); i++) {
			// Get packagedElement and convert into an element to gain access to element methods
			Element inputPackagedElement = (Element) inputDocument.getElementsByTagName("packagedElement").item(i);				
			Element outputPackagedElement = (Element) outputDocument.getElementsByTagName("packagedElement").item(i);
			
			// Check if the element has the attribute set
			if (inputPackagedElement.getAttribute("xmi:type").equals("uml:Class")) {
				// Append "ownedComment" node for the corresponding class with a "body" node referencing to the external library
				Element ownedComment = outputDocument.createElement("ownedComment");
				ownedComment.setAttribute("xmi:id", inputPackagedElement.getAttribute("xmi:id").concat("_comment"));
				
				Element ownedCommentBody = outputDocument.createElement("body");
				ownedCommentBody.appendChild(outputDocument.createTextNode("@external " + jarFilePath));
				
				ownedComment.appendChild(ownedCommentBody);
				outputPackagedElement.appendChild(ownedComment);	
			}
		}
	}

	/**
	 * Append a placeholder activity to every ownedOperation of every UML class. 
	 * Every ownedOperation is cloned and modified s.t. its xmi:id appends "_placeholder".
	 * Therefore, also all children of ownedOperation are cloned (without xmi:id appending "_placeholder").
	 * 
	 * Example result:
	 *   <ownedBehavior name="toString" visibility="public" xmi:id="_oA3CwFHQEeKwiMIhkV3wDA_placeholder" xmi:type="uml:Activity">
     *     <ownedParameter direction="return" type="_oA4Q5VHQEeKwiMIhkV3wDA" visibility="public" xmi:id="_oA3CwVHQEeKwiMIhkV3wDA">
     *       <lowerValue xmi:id="_oA3Cw1HQEeKwiMIhkV3wDA" xmi:type="uml:LiteralInteger"/>
     *       <upperValue value="1" xmi:id="_oA3CwlHQEeKwiMIhkV3wDA" xmi:type="uml:LiteralUnlimitedNatural"/>
     *     </ownedParameter>
     *   </ownedBehavior>
	 */
	private void appendPlaceholderActivities() {
		// Cycle through the number of packagedElements
		for (int i = 0; i < inputDocument.getElementsByTagName("packagedElement").getLength(); i++) {
			// Get packagedElement and convert into an element to gain access to element methods
			Element inputPackagedElement = (Element) inputDocument.getElementsByTagName("packagedElement").item(i);				
			Element outputPackagedElement = (Element) outputDocument.getElementsByTagName("packagedElement").item(i);
			
			// Check if the element has the attribute set
			if (inputPackagedElement.getAttribute("xmi:type").equals("uml:Class")) {
				// In "uml:Class", look for child node "ownedOperation"
				// and create corresponding sibling node (placeholder node)
				NodeList ownedOperations = inputPackagedElement.getElementsByTagName("ownedOperation");
				for(int j=0; j < ownedOperations.getLength(); j++) {
					// create a corresponding (copied) sibling node "ownedOperation" with different xmi:id
					Element ownedOperation = (Element) ownedOperations.item(j);
					
					// Access and clone the same Node in the output document
					Element ownedBehavior = (Element) outputPackagedElement.getElementsByTagName("ownedOperation").item(j).cloneNode(true);
					
					// Modify element and append it to the output document
					ownedBehavior.setAttribute("xmi:id", ownedOperation.getAttribute("xmi:id").concat("_placeholder"));
					ownedBehavior.setAttribute("xmi:type", "uml:Activity");
					ownedBehavior.setAttribute("name", ownedOperation.getAttribute("name"));
					outputPackagedElement.appendChild(ownedBehavior);	
					outputDocument.renameNode(ownedBehavior, null, "ownedBehavior");
				}
			}
		}
	}
	
	/**
	 * Converts the input UML file to a output UML file that contains:
	 * <list>
	 *   <li>A ownedComment in every uml:Class referencing to the external JAR file</li>
	 *   <li>A ownedBehavior (placeholder activity) for every ownedOperation in every uml:Class</li>
	 * </list>
	 * 
	 * @param outputFilePath path where the output file is written to
	 * @return true on successful conversation and false otherwise
	 */
	public void converter(String outputFilePath) {
		initalizeDocuments();
		appendCommentToUMLClasses();
		appendPlaceholderActivities();
		
		// write the content into xml file
		outputDocument.normalizeDocument(); // prettify the formatting
		DOMSource source = new DOMSource(outputDocument);
		StreamResult result = new StreamResult(new File(outputFilePath));
		try {
			TransformerFactory.newInstance().newTransformer().transform(source, result);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			System.out.println("Something went wrong during the writing of the output document to the file: " + e);
		}
		System.out.println("Successfully transformed " + inputFilePath + " into " + outputFilePath);
	}

}
