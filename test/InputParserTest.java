import static org.junit.Assert.*;

import org.junit.Test;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class InputParserTest {
       
    private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DOMSource source;
	private StreamResult result;
	private InputParser inputParser;
	private SimulationInfo simulationInfo;
	private SimulationInfo expectedSimInfo;

	@Test
    public void testReadXML() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        createXML();
        inputParser = new InputParser();
        expectedSimInfo = new SimulationInfo();
        simulationInfo = inputParser.readXML();
        assertEquals(expectedSimInfo.getAngfreq(), simulationInfo.getAngfreq(),1e-14);
        assertEquals(expectedSimInfo.getkT(), simulationInfo.getkT(),1e-14);
        assertEquals(expectedSimInfo.getSliceCount(), simulationInfo.getSliceCount(),1e-14);
        assertEquals(expectedSimInfo.getMass(), simulationInfo.getMass(),1e-14);
    }

	private void createXML() throws ParserConfigurationException, TransformerException {
		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("MonteCarlo");
		doc.appendChild(rootElement);
		
		Element temperature = doc.createElement("Temperature");
		Element path = doc.createElement("Path");
		Element oscillator = doc.createElement("Oscillator");
		Element mass = doc.createElement("Mass");
		
		rootElement.appendChild(temperature);
		rootElement.appendChild(path);
		rootElement.appendChild(oscillator);
		rootElement.appendChild(mass);
		
		Attr tempAttr = doc.createAttribute("value");
		Attr pathAttr = doc.createAttribute("sliceCount");
		Attr oscAttr = doc.createAttribute("frequency");
		Attr massAttr = doc.createAttribute("value");
		
		tempAttr.setValue("0.5");
		pathAttr.setValue("10");
		oscAttr.setValue("1.0");
		massAttr.setValue("1.0");
		
		temperature.setAttributeNode(tempAttr);
		path.setAttributeNode(pathAttr);
		oscillator.setAttributeNode(oscAttr);
		mass.setAttributeNode(massAttr);
		
		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		source = new DOMSource(doc);
		result = new StreamResult(new File("input.xml"));
		
		transformer.transform(source, result);
	}

}
