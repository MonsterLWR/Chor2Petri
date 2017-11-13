package xml_operation;

import layout.Layoutor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import tag_for_petri.Net;
import petriElement.Petri_elements;
import tag_for_petri.Pnml;
import petriElement.Token;
import tag_for_petri.Tag;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Petri_writer {
    public void write(Petri_elements petri_elements,String path) {
//        布局
        Layoutor layoutor = new Layoutor();
        layoutor.layout(petri_elements.getShapeableList(), petri_elements.getFlowableList());

        Tag root = new Pnml();
        root.addChild(new Net());

        Tag net = root.getChild("net");
        net.addChild(new Token());

//        将petri_elements中的petri对象添加到root标签下
        for (Tag tag : petri_elements.getTransition_list()) {
            net.addChild(tag);
        }
        for (Tag tag : petri_elements.getPlace_list()) {
            net.addChild(tag);
        }
        for (Tag tag : petri_elements.getArc_list()) {
            net.addChild(tag);
        }

        Document document = DocumentHelper.createDocument(root.toElement());

        OutputFormat format = new OutputFormat("",true);
        format.setEncoding("ISO-8859-1");//设置编码格式

        XMLWriter xmlWriter = null;
        try {
            xmlWriter = new XMLWriter(new FileOutputStream(path),format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
