package jjybdx4il.jaxb.bugs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.util.ValidationEventCollector;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * <a href="http://stackoverflow.com/questions/26618647">Stackoverflow 26618647 - JAXB2 type restriction not
 * working?</a>.
 *
 * <pre>$ mvn clean install
 * [INFO] Scanning for projects...
 * [INFO]
 * [INFO] Using the builder org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder with a thread count of 1
 * [INFO]
 * [INFO] ------------------------------------------------------------------------
 * [INFO] Building jaxb2-bugs 1.0-SNAPSHOT
 * [INFO] ------------------------------------------------------------------------
 * [INFO]
 * [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ jaxb2-bugs ---
 * [INFO]
 * [INFO] --- maven-jaxb2-plugin:0.8.3:generate (default) @ jaxb2-bugs ---
 * [INFO]
 * [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ jaxb2-bugs ---
 * [INFO] Using 'UTF-8' encoding to copy filtered resources.
 * [INFO] Copying 56 resources
 * [INFO] Copying 1 resource
 * [INFO]
 * [INFO] --- maven-compiler-plugin:2.5.1:compile (default-compile) @ jaxb2-bugs ---
 * [INFO] Compiling 757 source files to /home/mark/tmp/jaxb2-bugs/target/classes
 * [INFO]
 * [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ jaxb2-bugs ---
 * [INFO] Using 'UTF-8' encoding to copy filtered resources.
 * [INFO] skip non existing resourceDirectory /home/mark/tmp/jaxb2-bugs/src/test/resources
 * [INFO]
 * [INFO] --- maven-compiler-plugin:2.5.1:testCompile (default-testCompile) @ jaxb2-bugs ---
 * [INFO] Compiling 1 source file to /home/mark/tmp/jaxb2-bugs/target/test-classes
 * [INFO]
 * [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ jaxb2-bugs ---
 * [INFO] Surefire report directory: /home/mark/tmp/jaxb2-bugs/target/surefire-reports
 *
 * -------------------------------------------------------
 * T E S T S
 * -------------------------------------------------------
 * Running jjybdx4il.jaxb.bugs.Stackoverflow26618647Test
 * log4j:WARN No appenders could be found for logger (jjybdx4il.jaxb.bugs.Stackoverflow26618647Test).
 * log4j:WARN Please initialize the log4j system properly.
 * log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
 * Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 1.3 sec &lt;&lt;&lt; FAILURE!
 * test(jjybdx4il.jaxb.bugs.Stackoverflow26618647Test)  Time elapsed: 1.247 sec  &lt;&lt;&lt; ERROR!
 * javax.xml.bind.UnmarshalException: Unable to create an instance of org.sdmx.resources.sdmxml.schemas.v2_1.message.BaseHeaderType
 * - with linked exception:
 * [java.lang.InstantiationException]
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.handleEvent(UnmarshallingContext.java:647)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader.reportError(Loader.java:243)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.createInstance(UnmarshallingContext.java:614)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.StructureLoader.startElement(StructureLoader.java:170)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiTypeLoader.startElement(XsiTypeLoader.java:65)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext._startElement(UnmarshallingContext.java:486)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.startElement(UnmarshallingContext.java:465)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.SAXConnector.startElement(SAXConnector.java:135)
 * at com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.startElement(AbstractSAXParser.java:509)
 * at com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.scanStartElement(XMLNSDocumentScannerImpl.java:379)
 * at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.next(XMLDocumentFragmentScannerImpl.java:2786)
 * at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:606)
 * at com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl.next(XMLNSDocumentScannerImpl.java:117)
 * at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:510)
 * at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:848)
 * at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:777)
 * at com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:141)
 * at com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser.parse(AbstractSAXParser.java:1213)
 * at com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser.parse(SAXParserImpl.java:648)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallerImpl.unmarshal0(UnmarshallerImpl.java:203)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallerImpl.unmarshal(UnmarshallerImpl.java:175)
 * at javax.xml.bind.helpers.AbstractUnmarshallerImpl.unmarshal(AbstractUnmarshallerImpl.java:157)
 * at javax.xml.bind.helpers.AbstractUnmarshallerImpl.unmarshal(AbstractUnmarshallerImpl.java:204)
 * at jjybdx4il.jaxb.bugs.Stackoverflow26618647Test.test(Stackoverflow26618647Test.java:62)
 * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
 * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * at java.lang.reflect.Method.invoke(Method.java:606)
 * at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
 * at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
 * at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
 * at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
 * at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
 * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
 * at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
 * at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
 * at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
 * at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
 * at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
 * at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
 * at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
 * at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
 * at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
 * at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
 * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
 * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * at java.lang.reflect.Method.invoke(Method.java:606)
 * at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
 * at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
 * at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
 * at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
 * at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)
 * Caused by: java.lang.InstantiationException
 * at sun.reflect.InstantiationExceptionConstructorAccessorImpl.newInstance(InstantiationExceptionConstructorAccessorImpl.java:48)
 * at java.lang.reflect.Constructor.newInstance(Constructor.java:526)
 * at com.sun.xml.internal.bind.v2.ClassFactory.create0(ClassFactory.java:118)
 * at com.sun.xml.internal.bind.v2.runtime.ClassBeanInfoImpl.createInstance(ClassBeanInfoImpl.java:269)
 * at com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallingContext.createInstance(UnmarshallingContext.java:608)
 * ... 50 more
 *
 *
 * Results :
 *
 * Tests in error:
 * test(jjybdx4il.jaxb.bugs.Stackoverflow26618647Test): Unable to create an instance of org.sdmx.resources.sdmxml.schemas.v2_1.message.BaseHeaderType
 *
 * Tests run: 1, Failures: 0, Errors: 1, Skipped: 0
 *
 * [INFO] ------------------------------------------------------------------------
 * [INFO] BUILD FAILURE
 * [INFO] ------------------------------------------------------------------------
 * [INFO] Total time: 9.044 s
 * [INFO] Finished at: 2014-10-28T22:10:44+01:00
 * [INFO] Final Memory: 20M/428M
 * [INFO] ------------------------------------------------------------------------
 * [ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test (default-test) on project jaxb2-bugs: There are test failures.
 * [ERROR]
 * [ERROR] Please refer to /home/mark/tmp/jaxb2-bugs/target/surefire-reports for the individual test results.
 * [ERROR] -> [Help 1]
 * [ERROR]
 * [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
 * [ERROR] Re-run Maven using the -X switch to enable full debug logging.
 * [ERROR]
 * [ERROR] For more information about the errors and possible solutions, please read the following articles:
 * [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
 * </pre>
 */
public class Stackoverflow26618647Test {

    private static final Logger log = Logger.getLogger(Stackoverflow26618647Test.class.getName());

    private static String getJAXBObjectFactoryClassPath() throws IOException {
        final Path classesOutputDir = new File("target/classes").toPath();
        final Path objectFactoryClassFileName = new File("ObjectFactory.class").toPath();
        final StringBuilder jaxbClasspath = new StringBuilder();
        Files.walkFileTree(classesOutputDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.endsWith(objectFactoryClassFileName)) {
                    if (jaxbClasspath.length() > 0) {
                        jaxbClasspath.append(File.pathSeparator);
                    }
                    jaxbClasspath.append(classesOutputDir.relativize(file.getParent()).toString().replace(File.separator, "."));
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

        });
        log.info("jaxb classpath = " + jaxbClasspath);
        return jaxbClasspath.toString();
    }

    @Test(expected = JAXBException.class)
    public void test() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(getJAXBObjectFactoryClassPath());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        
        ValidationEventCollector eventCollector = new ValidationEventCollector();
        unmarshaller.setEventHandler(eventCollector);

        try {
            unmarshaller.unmarshal(new ByteArrayInputStream(
                    ("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message:GenericData xmlns:message=\"http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message\" xmlns:common=\"http://www.sdmx.org/resources/sdmxml/schemas/v2_1/common\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:generic=\"http://www.sdmx.org/resources/sdmxml/schemas/v2_1/data/generic\" xsi:schemaLocation=\"http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message https://sdw-wsrest.ecb.europa.eu/vocabulary/sdmx/2_1/SDMXMessage.xsd http://www.sdmx.org/resources/sdmxml/schemas/v2_1/common https://sdw-wsrest.ecb.europa.eu/vocabulary/sdmx/2_1/SDMXCommon.xsd http://www.sdmx.org/resources/sdmxml/schemas/v2_1/data/generic https://sdw-wsrest.ecb.europa.eu/vocabulary/sdmx/2_1/SDMXDataGeneric.xsd\">\n"
                    + "<message:Header>\n"
                    + "<message:ID>66aafd68-47cd-42bc-a601-b1e0033c9457</message:ID>\n"
                    + "<message:Test>false</message:Test>\n"
                    + "<message:Prepared>2014-10-28T00:55:06.480Z</message:Prepared>\n"
                    + "<message:Sender id=\"ECB\"/>\n"
                    + "<message:Structure structureID=\"ECB_EXR1\" dimensionAtObservation=\"TIME_PERIOD\">\n"
                    + "<common:Structure>\n"
                    + "<URN>urn:sdmx:org.sdmx.infomodel.datastructure.DataStructure=ECB:ECB_EXR1(1.0)</URN>\n"
                    + "</common:Structure>\n"
                    + "</message:Structure>\n"
                    + "</message:Header>\n"
                    + "<message:DataSet action=\"Replace\" validFromDate=\"2014-10-28T00:55:06.480Z\" structureRef=\"ECB_EXR1\">\n"
                    + "<generic:Series>\n"
                    + "<generic:SeriesKey>\n"
                    + "<generic:Value id=\"FREQ\" value=\"M\"/>\n"
                    + "<generic:Value id=\"CURRENCY\" value=\"USD\"/>\n"
                    + "<generic:Value id=\"CURRENCY_DENOM\" value=\"EUR\"/>\n"
                    + "<generic:Value id=\"EXR_TYPE\" value=\"SP00\"/>\n"
                    + "<generic:Value id=\"EXR_SUFFIX\" value=\"A\"/>\n"
                    + "</generic:SeriesKey>\n"
                    + "<generic:Attributes>\n"
                    + "<generic:Value id=\"TITLE_COMPL\" value=\"ECB reference exchange rate, US dollar/Euro, 2:15 pm (C.E.T.)\"/>\n"
                    + "<generic:Value id=\"UNIT\" value=\"USD\"/>\n"
                    + "<generic:Value id=\"PUBL_PUBLIC\" value=\"MOB.T0801; MOB.T0802\"/>\n"
                    + "<generic:Value id=\"SOURCE_AGENCY\" value=\"4F0\"/>\n"
                    + "<generic:Value id=\"DECIMALS\" value=\"4\"/>\n"
                    + "<generic:Value id=\"COLLECTION\" value=\"A\"/>\n"
                    + "<generic:Value id=\"UNIT_MULT\" value=\"0\"/>\n"
                    + "</generic:Attributes>\n"
                    + "<generic:Obs>\n"
                    + "<generic:ObsDimension value=\"1999-01\"/>\n"
                    + "<generic:ObsValue value=\"1.16078\"/>\n"
                    + "<generic:Attributes>\n"
                    + "<generic:Value id=\"OBS_STATUS\" value=\"A\"/>\n"
                    + "</generic:Attributes>\n"
                    + "</generic:Obs>\n"
                    + "</generic:Series>\n"
                    + "</message:DataSet>\n"
                    + "</message:GenericData>\n").getBytes()));
            fail();
        } catch (JAXBException ex) {
            assertEquals(1, eventCollector.getEvents().length);
            ValidationEvent event = eventCollector.getEvents()[0];
            log.warn(event);
            assertEquals(event.FATAL_ERROR, event.getSeverity());
            assertEquals("Unable to create an instance of org.sdmx.resources.sdmxml.schemas.v2_1.message.BaseHeaderType",
                    event.getMessage());
            assertEquals("[node=null,object=null,url=null,line=2,col=17,offset=-1]", event.getLocator().toString());
            throw ex;
        }
    }

}
