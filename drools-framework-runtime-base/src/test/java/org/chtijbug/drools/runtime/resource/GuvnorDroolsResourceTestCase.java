package org.chtijbug.drools.runtime.resource;

import org.drools.io.Resource;
import org.junit.Test;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: samuel
 * Date: 26/09/12
 * Time: 18:01
 */
public class GuvnorDroolsResourceTestCase {

    @Test
    public void testRemoveTrailingSlash()  {
        String expected = "//String";

        String toEval = GuvnorDroolsResource.removeTrailingSlash("//String/");
        assertEquals("Remove trailind '/' operation failed", expected, toEval);
    }


    @Test
    public void testGetWebResourceUrl()  {
        GuvnorDroolsResource toTest = new GuvnorDroolsResource("http://localhost:8080/", "drools-guvnor", "amag/", "LATEST", "tomcat", "tomcat");
        String expected = "http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/amag/LATEST";

        String toEval = toTest.getWebResourceUrl();
        assertEquals("Expected webresource url not match", expected, toEval);
    }

    @Test
    public void testGetResource() {
        GuvnorDroolsResource toTest = new GuvnorDroolsResource("http://localhost:8080/", "drools-guvnor", "amag/", "LATEST", "tomcat", "tomcat");


        final String expectedChangeSet = "<change-set xmlns='http://drools.org/drools-5.0/change-set' xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'>" +
                "<add>" +
                "<resource source='http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/amag/LATEST' type='PKG' " +
                "basicAuthentication='enabled' username='tomcat' password='tomcat' />" +
                "</add>" +
                "</change-set>";
        try {
            Resource toEval = toTest.getResource();

            InputStreamReader reader = new InputStreamReader(toEval.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringWriter writer = new StringWriter();
            String line;
            while ( (line=bufferedReader.readLine()) != null ){
                writer.write(line);
            }
            String generatedChangeSet = writer.toString();
            assertEquals("Generated Change set does not match with the expected one.", expectedChangeSet, generatedChangeSet);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
