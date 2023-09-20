package parsertests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.XMLParser;

import static java.lang.String.valueOf;

public class XMLParserTestValidXml {

    @DataProvider (name = "validXmlFiles")
    public Object[][] createData() {
        return new Object[][] {
                {"src/resources/valid xml/valid xml1.xml", Integer.valueOf("0")},
                {"src/resources/valid xml/valid xml2.xml", Integer.valueOf("0")},
                {"src/resources/valid xml/valid xml3.xml", Integer.valueOf("0")},
                {"src/resources/valid xml/valid xml4.xml", Integer.valueOf("0")},
                {"src/resources/valid xml/valid xml5.xml", Integer.valueOf("0")},
                {"src/resources/valid xml/valid xml6.xml", Integer.valueOf("0")}
        };
    }

    @Test(dataProvider = "validXmlFiles", groups = "valid xml")
    public void testParseDocumentValid1(String filePath, Integer expected) {
        XMLParser parser = new XMLParser(filePath);
        parser.parseDocument();

        int result = parser.getErrors().size();

        Assert.assertEquals(result, expected);
    }
}
