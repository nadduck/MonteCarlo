package org.phystools.monte;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.phystools.monte.action.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XMLCreator {
    private Document doc;
    private Double temperature;
    private Integer sliceCount;
    private Double frequency;
    private Double mass;
    private Action action;
    
    public XMLCreator() {
    	temperature = 0.5;
    	sliceCount = 20;
    	frequency = 1.0;
    	mass = 1.0;
    	double deltaTau = 1/(temperature*sliceCount);
		action = new ExactSHOAction(deltaTau , mass, frequency);
    }
    
    public XMLCreator(Double temperature, Integer sliceCount, 
    		Double frequency, Double mass, Action action) {
    	this.temperature = temperature;
    	this.sliceCount = sliceCount;
    	this.frequency = frequency;
    	this.mass = mass;
		this.action = action;
    }
    
	public Document createXMLDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("MonteCarlo");
        doc.appendChild(rootElement);

        addNodeWithAttribute("Temperature", "value", temperature.toString());
        addNodeWithAttribute("Path", "sliceCount", sliceCount.toString());
        addNodeWithAttribute("Oscillator", "frequency", frequency.toString());
        addNodeWithAttribute("Mass", "value", mass.toString());
        addNodeWithAttribute("Action","name", action.getName());
        return doc;
    }

    private void addNodeWithAttribute(String elemTag, String attrTag,
            String attrVal) {
        Element elem = doc.createElement(elemTag);
        Element rootElement = doc.getDocumentElement();
        rootElement.appendChild(elem);
        Attr elemAttr = doc.createAttribute(attrTag);
        elemAttr.setValue(attrVal);
        elem.setAttributeNode(elemAttr);
    }
}
