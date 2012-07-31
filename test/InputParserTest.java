import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class InputParserTest {

    private Document doc;
    private Double temperature;
    private Integer sliceCount;
    private Double frequency;
    private Double mass;
    private InputParser inputParser;
    private SimulationInfo expectedSimInfo;
    private Action action;

    @Before
    public void before() throws ParserConfigurationException {
        temperature = 0.25;
        sliceCount = 20;
        frequency = 0.8;
        mass = 0.1;
        
        double deltaTau = 1.0/(temperature*sliceCount);
        action = new PrimitiveAction(deltaTau, mass);
        createXMLDocument();
        inputParser = new InputParser();
        expectedSimInfo = createSimulationInfo();
    }

    @Test
    public void testFrequency() {
        SimulationInfo simulationInfo = inputParser.parseXML(doc);
        assertEquals(expectedSimInfo.getAngfreq(), simulationInfo.getAngfreq(),
                1e-14);
    }

    @Test
    public void testTemperature() {
        SimulationInfo simulationInfo = inputParser.parseXML(doc);
        assertEquals(expectedSimInfo.getkT(), simulationInfo.getkT(), 1e-14);
    }

    @Test
    public void testSliceCount() {
        SimulationInfo simulationInfo = inputParser.parseXML(doc);
        assertEquals(expectedSimInfo.getSliceCount(),
                simulationInfo.getSliceCount(), 1e-14);
    }

    @Test
    public void testMass() {
        SimulationInfo simulationInfo = inputParser.parseXML(doc);
        assertEquals(expectedSimInfo.getMass(), simulationInfo.getMass(), 1e-14);
    }
    
    @Test
    public void testAction() {
        SimulationInfo simulationInfo = inputParser.parseXML(doc);
        Class<? extends Action> expectedClass = expectedSimInfo.getAction().getClass();
        Class<? extends Action> actionClass = simulationInfo.getAction().getClass();
        assertEquals(expectedClass, actionClass);
    }
    
    private SimulationInfo createSimulationInfo() {
        SimulationInfo simulationInfo = new SimulationInfo();
        simulationInfo.setkT(temperature);
        simulationInfo.setSliceCount(sliceCount);
        simulationInfo.setAngfreq(frequency);
        simulationInfo.setMass(mass);
        simulationInfo.setAction(action);
        return simulationInfo;
    }

    private void createXMLDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("MonteCarlo");
        doc.appendChild(rootElement);

        addNodeWithAttribute("Temperature", "value", temperature.toString());
        addNodeWithAttribute("Path", "sliceCount", sliceCount.toString());
        addNodeWithAttribute("Oscillator", "frequency", frequency.toString());
        addNodeWithAttribute("Mass", "value", mass.toString());
        addNodeWithAttribute("Action","name", action.getName());
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
