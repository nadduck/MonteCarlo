import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class InputParser {
	private SimulationInfo simulationInfo;
	private File input;

	public SimulationInfo readXML() throws ParserConfigurationException, SAXException, IOException {
		input = new File("input.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(input);
		doc.getDocumentElement().normalize();
		
		NodeList temp = doc.getElementsByTagName("Temperature");
		String Temperature  = temp.item(0).getAttributes().getNamedItem("value").getNodeValue();
		
		NodeList slice = doc.getElementsByTagName("Path");
		String numslice  = slice.item(0).getAttributes().getNamedItem("sliceCount").getNodeValue();
		
		NodeList osc = doc.getElementsByTagName("Oscillator");
		String oscillator  = osc.item(0).getAttributes().getNamedItem("frequency").getNodeValue();
		
		NodeList masslist = doc.getElementsByTagName("Mass");
		String massval  = masslist.item(0).getAttributes().getNamedItem("value").getNodeValue();
		
		double kT = Double.parseDouble(Temperature);
		int sliceCount = Integer.parseInt(numslice);
		double angfreq = Double.parseDouble(oscillator);
		double mass = Double.parseDouble(massval);
		
		simulationInfo = new SimulationInfo();
		simulationInfo.setkT(kT);
		simulationInfo.setAngfreq(angfreq);
		simulationInfo.setSliceCount(sliceCount);
		simulationInfo.setMass(mass);
		
		return simulationInfo;
	}


}
