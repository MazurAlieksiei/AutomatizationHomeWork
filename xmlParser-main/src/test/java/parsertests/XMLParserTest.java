package parsertests;

import org.testng.Assert;
import org.testng.annotations.Test;
import parser.XMLParser;


import static org.testng.Assert.*;

public class XMLParserTest {

    @Test (groups = "invalid xml", description = "Root element without '<'")
    public void testParseDocument() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml1 without root tag start.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "Document should start from root tag or instruction.  ");
    }

    @Test (groups = "invalid xml", description = "Mistake near closing tag")
    public void testParseDocument2() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml2 with unexpected closing tag.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "Unexpected closing tag bookstore ");
    }

    @Test (groups = "invalid xml", description = "Mistake with closing tag (missing '>')")
    public void testParseDocument3() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml without closing tag.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "The tag note closed incorrectly. Missing >" +
                " All data should be inside of the root tag ");
    }

    @Test (groups = "invalid xml", description = "Mistake with / (missing '/')")
    public void testParseDocument4() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml3 missed slash.xml");
        parser.parseDocument();
        int result = parser.getErrors().size();

        Assert.assertEquals(result, 1);
    }

    @Test (groups = "invalid xml", description = "Mistake with instruction tag (not closed)")
    public void testParseDocument5() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml4 not closed instruction tag.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "Instruction tag is not closed ");
    }

    @Test (groups = "invalid xml", description = "Mistake with instruction tag (missing '?')")
    public void testParseDocument6() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml bad open instruction tag.xml");
        parser.parseDocument();
        int result = parser.getErrors().size();

        Assert.assertEquals(result, 1);
    }

    @Test (groups = "invalid xml", description = "Mistake with cases of tag (one Uppercase, second Lowercase)")
    public void testParseDocument7() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml5 different case tag.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "Unexpected closing tag To ");
    }

    //TODO не известная ошибка как должна обозначаться?
    @Test (groups = "invalid xml", description = "Mistake with tag attributes (missing '\"')")
    public void testParseDocument8() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml not quoted attrib.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 1);
    }

    @Test (groups = "invalid xml", description = "Mistake with nesting of elements")
    public void testParseDocument9() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml improperly nested elements.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "Unexpected closing tag supermarket " +
                "Unexpected closing tag product Unexpected closing tag shops ");
    }

    @Test (groups = "invalid xml", description = "Mistake with 'special' character (not using of entity reference)")
    public void testParseDocument10() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml not use entity reference.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 1);
    }

    @Test (groups = "invalid xml", description = "Mistake with data layout (not in root tag)")
    public void testParseDocument11() {
        XMLParser parser = new XMLParser("src/resources/invalid xml/invalid xml data not in root tag.xml");
        parser.parseDocument();
        StringBuilder builder = new StringBuilder();
        Object[] result = parser.getErrors().toArray();

        for (int i = 0; i < result.length; i++) {
            builder.append(result[i]);
            builder.append(" ");
        }

        Assert.assertEquals(builder.toString(), "All data should be inside of the root tag ");
    }

    @Test(groups = "valid xml")
    public void testParseDocumentValid1() {
        XMLParser parser = new XMLParser("src/resources/valid xml/valid xml1.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 0);
    }
    @Test(groups = "valid xml")
    public void testParseDocumentValid2() {
        XMLParser parser = new XMLParser("src/resources/valid xml/valid xml2.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 0);
    }
    @Test(groups = "valid xml", description = "Starts with instruction block")
    public void testParseDocumentValid3() {
        XMLParser parser = new XMLParser("src/resources/valid xml/valid xml3.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 0);
    }
    @Test(groups = "valid xml")
    public void testParseDocumentValid4() {
        XMLParser parser = new XMLParser("src/resources/valid xml/valid xml4.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 0);
    }

    @Test(groups = "valid xml")
    public void testParseDocumentValid5() {
        XMLParser parser = new XMLParser("src/resources/valid xml/valid xml5.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 0);
    }

    @Test(groups = "valid xml")
    public void testParseDocumentValid6() {
        XMLParser parser = new XMLParser("src/resources/valid xml/valid xml6.xml");
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, 0);
    }

}