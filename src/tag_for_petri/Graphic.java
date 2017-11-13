package tag_for_petri;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import tag_for_petri.Tag;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public class Graphic extends Tag {
    public Graphic() {
    }

    public String getName() {
        return "graphics";
    }

    @Test
    public void xml_test() throws IOException {
        Graphic graphic = new Graphic();
        graphic.addChild(new Position());
        System.out.println(graphic);
        Document document = DocumentHelper.createDocument(graphic.toElement());

        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("place.xml"),format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
