package some_test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import petriElement.Arc;
import petriElement.Place;
import petriElement.Token;
import petriElement.Transition;
import tag_for_petri.Arcpath;
import tag_for_petri.Attribute;
import tag_for_petri.Tag;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Test_for_petri {

    private void output(Tag tag, String path) {
        Document document = DocumentHelper.createDocument(tag.toElement());

        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");//设置编码格式

        XMLWriter xmlWriter = null;
        try {
            xmlWriter = new XMLWriter(new FileOutputStream(path),format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void token_test() throws IOException {
        Token token = new Token();
        output(token,"token.xml");
    }


    @Test
    public void test_for_set_attr() {
        Arcpath arcpath = new Arcpath("1");
        arcpath.setAttribute(new Attribute("id","250"));
        System.out.println(arcpath);
    }

    @Test
    public void transition_arc() throws IOException {
        Arc arc = new Arc("1","2","3");
        output(arc,"arc.xml");
    }
}
