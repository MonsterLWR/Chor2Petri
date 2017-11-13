package tag_for_petri;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public class Name extends Tag {
    public Name() {
//        默认子标签
        this.addChild(new Value());
        Graphic graphic_for_name = new Graphic();
        graphic_for_name.addChild(new Offset());
        this.addChild(graphic_for_name);
    }
    public String getName() {
        return "name";
    }

    @Test
    public void xml_test() throws IOException {
        Name name = new Name();
        Document document = DocumentHelper.createDocument(name.toElement());

        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("place.xml"),format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
