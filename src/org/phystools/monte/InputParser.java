package org.phystools.monte;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.phystools.monte.action.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class InputParser {

	private Document doc;
	private double kT;
	private int sliceCount;
	private double angfreq;
	private double mass;
	private double deltaTau;
	private int dimension;
	private Action action;
	private double magneticfield;
	public SimulationInfo parseXMLFromFile(String inputFileName) 
	        throws ParserConfigurationException, SAXException, IOException {
        Document doc = getXMLDocumentFromFile(inputFileName);
		
		SimulationInfo simulationInfo = parseXML(doc);
        return simulationInfo;
	}

    public Document getXMLDocumentFromFile(String inputFileName) 
            throws ParserConfigurationException, SAXException, IOException{
        File input = new File(inputFileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(input);
        return doc;
    }

    public SimulationInfo parseXML(Document document) {
        this.doc = document;
        
        kT = getTemperature();
		sliceCount = getSliceCount();
		angfreq = getFrequency();
		mass = getMass();
		deltaTau = 1.0/(kT*sliceCount);
		dimension = getDimension();
		action = getAction();
		magneticfield = getMagneticField();
		
		SimulationInfo simulationInfo = new SimulationInfo();
		simulationInfo.setkT(kT);
		simulationInfo.setAngfreq(angfreq);
		simulationInfo.setSliceCount(sliceCount);
		simulationInfo.setMass(mass);
		simulationInfo.setAction(action);
		simulationInfo.setDimension(dimension);
		simulationInfo.setMagneticField(magneticfield);
		
		return simulationInfo;
    }

    private double getMagneticField() {
    	return getDoubleAttribute("MagneticField", "value");
	}

	private int getDimension() {
		return getIntegerAttribute("Dimension","value");
	}

	private Action getAction() {
		String name = getStringAttribute("Action", "name");
        if (name.equals("ExactSHOAction")) {
            return new ExactSHOAction(deltaTau, mass, angfreq, dimension);
        } else if (name.equals("PrimitiveAction")) {
            return new PrimitiveAction(deltaTau, mass);
        }
		return null; 
	}

	public double getTemperature() {
        return getDoubleAttribute("Temperature", "value");
    }

    public int getSliceCount() {
        return getIntegerAttribute("Path", "sliceCount");
    }

    public double getMass() {
        return getDoubleAttribute("Mass", "value");
    }

    public double getFrequency() {
        return getDoubleAttribute("Oscillator", "frequency");
    }

    public String getStringAttribute(String nodeName, String attributeName) {
        NodeList osc = doc.getElementsByTagName(nodeName);
    	Node node = osc.item(0);
        NamedNodeMap attributes = node.getAttributes();
        Node attribute = attributes.getNamedItem(attributeName);
        String string = attribute.getNodeValue();
        return string;
    }

    public int getIntegerAttribute(String nodeName, String attributeName) {
        String string = getStringAttribute(nodeName, attributeName);
        return Integer.parseInt(string);
    }

    public double getDoubleAttribute(String nodeName, String attributeName) {
        String string = getStringAttribute(nodeName, attributeName);
		return Double.parseDouble(string);
    }


}
