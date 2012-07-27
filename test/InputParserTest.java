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
		
		addNodeAttribute(doc, rootElement, "Temperature", "value", "0.5");
		addNodeAttribute(doc, rootElement, "Path", "sliceCount", "10");
		addNodeAttribute(doc, rootElement, "Oscillator", "frequency", "1.0");
		addNodeAttribute(doc, rootElement, "Mass", "value", "1.0");

		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		source = new DOMSource(doc);
		result = new StreamResult(new File("input.xml"));
		
		transformer.transform(source, result);
	}

	private void addNodeAttribute(Document doc, Element rootElement, String elemTag, String attrTag, String attrVal) {
		Element elem = doc.createElement(elemTag);
		rootElement.appendChild(elem);
		Attr elemAttr = doc.createAttribute(attrTag);
		elemAttr.setValue(attrVal);
		elem.setAttributeNode(elemAttr);
	}

}
