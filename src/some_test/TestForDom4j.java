package some_test;

import bpmnElement.Participant;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/9/26.
 */
public class TestForDom4j {

    @Test
    public void test1() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("case.bpmn"));
        Element root = doc.getRootElement();
        System.out.println(root.getName());
        List<Element> elems = root.elements();
        for (Element element : elems) {
            System.out.println("========================================");
            System.out.println(element.getName());
//            for (Element element1 : element.elements()) {
//                System.out.println(element1.getName());
////                System.out.println(element1.attributes());
//            }
            System.out.println("========================================");
//            System.out.println(element);
        }
        Element element = root.element("choreography");
        System.out.println(element.getName());
        for (Attribute attribute : element.attributes()) {
            System.out.println(attribute.getName());
            System.out.println(attribute.getData());
        }
    }

//    @Test
//    public  void  test_read() throws DocumentException {
//        //读取bpmn文件
//        SAXReader reader = new SAXReader();
//        Document doc = reader.read(new File("case.bpmn"));
//        //获得需要处理的所有节点，存储在一个list中
//        Element root = doc.getRootElement();
//        Element choreography_element = root.element("choreography");
//        List<Element> elements = choreography_element.elements();
//        //遍历所有节点，将节点转换为响应的bpmn对象
//        for (Element e : elements) {
//            switch (e.getName()) {
//                case "participant":
//                    Participant p = new Participant(e);
//                    System.out.println(p);
//                    break;
//            }
//        }
//    }

    @Test
    public  void test_write() throws IOException {
        Element root = DocumentHelper.createElement("Person");
        Document document = DocumentHelper.createDocument(root);
        //给根节点添加属性
        root.addAttribute("学校", "南大").addAttribute("位置", "江西");

        //给根节点添加孩子节点
        Element element1 = root.addElement("学生");
        element1.addElement("姓名").addAttribute("婚姻", "单身").addText("小章");
        element1.addElement("年龄").addText("21");

        Element element2 = root.addElement("学生");
        element2.addElement("姓名").addAttribute("婚姻", "单身").addText("小红").addElement("爱好").addText("唱歌");
        element2.addElement("年龄").addText("22");


        //把生成的xml文档存放在硬盘上  true代表是否换行
        OutputFormat format = new OutputFormat("    ",true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("Person.xml"),format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
