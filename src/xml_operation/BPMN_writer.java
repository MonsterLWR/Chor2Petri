package xml_operation;

import bpmnElement.*;
import layout.Layoutor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/17.
 */
public class BPMN_writer {
    private BPMN_elements bpmn_elements;

    public BPMN_writer(BPMN_elements bpmn_elements) {
        this.bpmn_elements = bpmn_elements;
    }

    private void write_in_normal_tags(Element tag_root, Element shape_root, Elementable element, String height, String width,
                                      String x, String y) {
        //     添加标签内容
        tag_root.add(element.toElement());
        //     添加位置信息
        Element bpmndi_BPMNShape = shape_root.addElement("bpmndi:BPMNShape");
        bpmndi_BPMNShape.addAttribute("bpmnElement", element.getId());
        bpmndi_BPMNShape.addAttribute("id", "Yaoqiang-" + element.getId());

        Element dc_Bounds = bpmndi_BPMNShape.addElement("dc:Bounds");
        dc_Bounds.addAttribute("height", height);
        dc_Bounds.addAttribute("width", width);
        dc_Bounds.addAttribute("x", x);
        dc_Bounds.addAttribute("y", y);
    }

    private void write_in_sequenceFlow(Element tag_root, Element shape_root, Elementable sequenceFlow) {
        //     添加标签内容
        tag_root.add(sequenceFlow.toElement());
        //     添加位置信息
        Element bpmndi_BPMNEdge = shape_root.addElement("bpmndi:BPMNEdge");
        bpmndi_BPMNEdge.addAttribute("bpmnElement", sequenceFlow.getId());
        bpmndi_BPMNEdge.addAttribute("id", "Yaoqiang-" + sequenceFlow.getId());

        Element di_waypoint1 = bpmndi_BPMNEdge.addElement("di:waypoint");
        di_waypoint1.addAttribute("x", "0.0");
        di_waypoint1.addAttribute("y", "0.0");

        Element di_waypoint2 = bpmndi_BPMNEdge.addElement("di:waypoint");
        di_waypoint2.addAttribute("x", "0.0");
        di_waypoint2.addAttribute("y", "0.0");
    }

    private void write_in_choreographyTask(Element tag_root, Element shape_root, ChoreographyTask choreographyTask,String x, String y) {
//      取出participant的id
        String p_id_2 = null;
        String p_id_1 = choreographyTask.getInitiatingParticipantRef();
        if (choreographyTask.getParticipantRef().get(0).equals(p_id_1)) {
            p_id_2 = choreographyTask.getParticipantRef().get(1);
        } else {
            p_id_2 = choreographyTask.getParticipantRef().get(0);
        }
//        根据id得到participant对象
        Participant p_1 = null;
        Participant p_2 = null;
        List<Participant> participantList = bpmn_elements.getParticipantList();
        for (Participant participant : participantList) {
            if (participant.getId().equals(p_id_1)) {
                p_1 = participant;
            } else if (participant.getId().equals(p_id_2)) {
                p_2 = participant;
            }
        }
        //     添加标签内容
        tag_root.add(choreographyTask.toElement());
        tag_root.add(p_1.toElement());
        tag_root.add(p_2.toElement());
        //     添加choreographyTask位置信息
        Element bpmndi_BPMNShape = shape_root.addElement("bpmndi:BPMNShape");
        bpmndi_BPMNShape.addAttribute("bpmnElement", choreographyTask.getId());
        bpmndi_BPMNShape.addAttribute("id", "Yaoqiang-" + choreographyTask.getId());

        Element dc_Bounds = bpmndi_BPMNShape.addElement("dc:Bounds");
        dc_Bounds.addAttribute("height", "95.0");
        dc_Bounds.addAttribute("width", "93.0");
        dc_Bounds.addAttribute("x", x);
        dc_Bounds.addAttribute("y", y);
//      添加participant的位置信息
        bpmndi_BPMNShape = shape_root.addElement("bpmndi:BPMNShape");
        bpmndi_BPMNShape.addAttribute("bpmnElement", p_1.getId());
        bpmndi_BPMNShape.addAttribute("choreographyActivityShape", "Yaoqiang-" + choreographyTask.getId());
        bpmndi_BPMNShape.addAttribute("id", "Yaoqiang-" + choreographyTask.getId() + "_part" + p_1.getId());
        bpmndi_BPMNShape.addAttribute("participantBandKind", "top_initiating");

        dc_Bounds = bpmndi_BPMNShape.addElement("dc:Bounds");
        dc_Bounds.addAttribute("height", "20.0");
        dc_Bounds.addAttribute("width", "93.0");
        dc_Bounds.addAttribute("x", x);
        dc_Bounds.addAttribute("y", y);

        bpmndi_BPMNShape = shape_root.addElement("bpmndi:BPMNShape");
        bpmndi_BPMNShape.addAttribute("bpmnElement", p_2.getId());
        bpmndi_BPMNShape.addAttribute("choreographyActivityShape", "Yaoqiang-" + choreographyTask.getId());
        bpmndi_BPMNShape.addAttribute("id", "Yaoqiang-" + choreographyTask.getId() + "_part" + p_2.getId());
        bpmndi_BPMNShape.addAttribute("participantBandKind", "bottom_non_initiating");

        dc_Bounds = bpmndi_BPMNShape.addElement("dc:Bounds");
        dc_Bounds.addAttribute("height", "20.0");
        dc_Bounds.addAttribute("width", "93.0");
        dc_Bounds.addAttribute("x", x);
        dc_Bounds.addAttribute("y", (Double.parseDouble(y)+75) + "");
    }

    public void write(String path) throws IOException {
//        创建bpmn文件所必须的节点
        Element definitions = DocumentHelper.createElement("definitions");
        definitions.addAttribute("xmlns", "http://www.omg.org/spec/BPMN/20100524/MODEL");
        definitions.addNamespace("bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI");
        definitions.addNamespace("dc", "http://www.omg.org/spec/DD/20100524/DC");
        definitions.addNamespace("di", "http://www.omg.org/spec/DD/20100524/DI");
        definitions.addNamespace("tns", "http://sourceforge.net/bpmn/definitions/_1505696515948");
        definitions.addNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
        definitions.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        definitions.addNamespace("yaoqiang", "http://bpmn.sourceforge.net");
        definitions.addAttribute("exporter", "Yaoqiang BPMN Editor");
        definitions.addAttribute("exporterVersion", "4.0");
        definitions.addAttribute("expressionLanguage", "http://www.w3.org/1999/XPath");
        definitions.addAttribute("id", "_1505696515948");
        definitions.addAttribute("name", "");
        definitions.addAttribute("targetNamespace", "http://sourceforge.net/bpmn/definitions/_1505696515948");
        definitions.addAttribute("typeLanguage", "http://www.w3.org/2001/XMLSchema");
        definitions.addNamespace("schemaLocation", "http://www.omg.org/spec/BPMN/20100524/MODEL http://bpmn.sourceforge.net/schemas/BPMN20.xsd");
//      必要的子标签
        Element choreography = definitions.addElement("choreography");
        choreography.addAttribute("id", "_1");
        choreography.addAttribute("isClosed", "false");

        Element bpmndi_BPMNDiagram = definitions.addElement("bpmndi:BPMNDiagram");
        bpmndi_BPMNDiagram.addAttribute("id", "Yaoqiang_Diagram-_1");
        bpmndi_BPMNDiagram.addAttribute("name", "Untitled Diagram");
        bpmndi_BPMNDiagram.addAttribute("resolution", "96.0");
//      子标签必要的子标签
        Element bpmndi_BPMNPlane = bpmndi_BPMNDiagram.addElement("bpmndi:BPMNPlane");
        bpmndi_BPMNPlane.addAttribute("bpmnElement", "_1");

        Element extensionElements = choreography.addElement("extensionElements");
//      标签添加入口
        Element tag_root = choreography;
        Element shape_root = bpmndi_BPMNPlane;

//      添加标签
//        double x = 0.0;
//        double y = 0.0;
//        写入前进行布局
        Layoutor layoutor = new Layoutor();
        layoutor.layout(bpmn_elements.getShapeableList(),bpmn_elements.getFlowableList());
//        开始写入
        List<StartEvent> startEventList = bpmn_elements.getStartEventList();
        for (StartEvent startEvent : startEventList) {
            write_in_normal_tags(tag_root, shape_root, startEvent, "32.0", "32.0",startEvent.getX(),startEvent.getY());
//            x+=50;
        }

        List<EndEvent> endEventList = bpmn_elements.getEndEventList();
        for (EndEvent endEvent : endEventList) {
            write_in_normal_tags(tag_root, shape_root, endEvent, "32.0", "32.0",endEvent.getX(),endEvent.getY());
//            x+=50;
        }

        List<SequenceFlow> sequenceFlowList = bpmn_elements.getSequenceFlowList();
        for (SequenceFlow sequenceFlow : sequenceFlowList) {
            write_in_sequenceFlow(tag_root, shape_root, sequenceFlow);
        }

        List<ChoreographyTask> choreographyTaskList = bpmn_elements.getChoreographyTaskList();
        for (ChoreographyTask choreographyTask : choreographyTaskList) {
            write_in_choreographyTask(tag_root, shape_root, choreographyTask,choreographyTask.getX(),choreographyTask.getY());
//            x+=50;
        }

        List<MessageFlow> messageFlowList = bpmn_elements.getMessageFlowList();
        for (MessageFlow messageFlow : messageFlowList) {
            tag_root.add(messageFlow.toElement());
        }

        List<GateWay> gateWayList = bpmn_elements.getGateWayList();
        for (GateWay gateWay : gateWayList) {
            write_in_normal_tags(tag_root, shape_root, gateWay,"42.0","42.0",gateWay.getX(),gateWay.getY());
//            x+=50;
        }

        List<IntermediateThrowEvent> intermediateThrowEventList = bpmn_elements.getIntermediateThrowEventList();
        for (IntermediateThrowEvent intermediateThrowEvent : intermediateThrowEventList) {
            write_in_normal_tags(tag_root, shape_root, intermediateThrowEvent,"42.0","42.0",intermediateThrowEvent.getX(),intermediateThrowEvent.getY());
//            x+=50;
        }

        List<Message> messageList = bpmn_elements.getMessageList();
        for (Message message : messageList) {
            write_in_normal_tags(definitions, shape_root, message,"30.0","35.0",message.getX(),message.getY());
//            x+=50;
        }

        List<Association> associationList = bpmn_elements.getAssociationList();
        for (Association association : associationList) {
//            在位置信息上，association可以当作sequenceFlow处理
            write_in_sequenceFlow(tag_root, shape_root, association);
        }
//        写入.bpmn文件
        Document document = DocumentHelper.createDocument(definitions);

        OutputFormat format = new OutputFormat("    ", true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
