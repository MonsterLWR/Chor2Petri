package xml_operation;

import bpmnElement.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/10.
 */
public class BPMN_reader {

    /**
     * 读取给定bpmn文件，并将所有元素转换成的对象封装在BPMN_element对象中
     *
     * @param path:BPMN文件路径
     * @throws DocumentException
     */
    public BPMN_elements read_bpmn(String path) throws DocumentException {
        //将要返回的对象
        BPMN_elements bpmn_elements = new BPMN_elements();
        //读取bpmn文件
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(path));
        //获得需要处理的所有节点，存储在一个list中
        Element root = doc.getRootElement();
        //        添加message，因为该标签的位置与其他的不一样
        List<Element> messages=root.elements("message");
        for (Element element : messages) {
            bpmn_elements.getMessageList().add(new Message(element));
        }
        //标签可能存在于choreography或者process下面
        List<Element> elements = null;
        Element choreography_element = root.element("choreography");
        Element process = root.element("process");
        System.out.println("------------------------input----------------------");
        //遍历所有节点，将节点转换为相应的bpmn对象
        if (choreography_element!=null)
            construct(bpmn_elements, choreography_element.elements());
        if(process!=null)
            construct(bpmn_elements,process.elements());
        System.out.println("------------------------input----------------------");
        return bpmn_elements;
    }

    private void construct(BPMN_elements bpmn_elements, List<Element> elements) {
        for (Element e : elements) {
            switch (e.getName()) {
	            case "association":
	            	Association association = new Association(e);
	            	bpmn_elements.getAssociationList().add(association);
	            	System.out.println(association);
	            	break;
                case "participant":
                    Participant p = new Participant(e);
                    bpmn_elements.getParticipantList().add(p);
                    System.out.println(p);
                    break;
                case "messageFlow":
                    MessageFlow messageFlow = new MessageFlow(e);
                    bpmn_elements.getMessageFlowList().add(messageFlow);
                    System.out.println(messageFlow);
                    break;
                case "choreographyTask":
                    ChoreographyTask choreographyTask = new ChoreographyTask(e);
                    bpmn_elements.getChoreographyTaskList().add(choreographyTask);
                    System.out.println(choreographyTask);
                    break;
                case "endEvent":
                    EndEvent endEvent = new EndEvent(e);
                    bpmn_elements.getEndEventList().add(endEvent);
                    System.out.println(endEvent);
                    break;
                case "intermediateCatchEvent":
                    IntermediateThrowEvent intermediateCatchEvent = new IntermediateThrowEvent(e);
                    bpmn_elements.getIntermediateThrowEventList().add(intermediateCatchEvent);
                    System.out.println(intermediateCatchEvent);
                    break;
                case "intermediateThrowEvent":
                    IntermediateThrowEvent intermediateThrowEvent = new IntermediateThrowEvent(e);
                    bpmn_elements.getIntermediateThrowEventList().add(intermediateThrowEvent);
                    System.out.println(intermediateThrowEvent);
                    break;
                case "parallelGateway":
                    ParallelGateway parallelGateway = new ParallelGateway(e);
                    bpmn_elements.getParallelGatewayList().add(parallelGateway);
                    bpmn_elements.getGateWayList().add(parallelGateway);
                    System.out.println(parallelGateway);
                    break;
                case "sequenceFlow":
                    SequenceFlow sequenceFlow = new SequenceFlow(e);
                    bpmn_elements.getSequenceFlowList().add(sequenceFlow);
                    System.out.println(sequenceFlow);
                    break;
                case "startEvent":
                    StartEvent startEvent = new StartEvent(e);
                    bpmn_elements.getStartEventList().add(startEvent);
                    System.out.println(startEvent);
                    break;
                case "eventBasedGateway":
                    EventBasedGateway eventBasedGateway = new EventBasedGateway(e);
                    bpmn_elements.getEventBasedGatewayList().add(eventBasedGateway);
                    bpmn_elements.getGateWayList().add(eventBasedGateway);
                    System.out.println(eventBasedGateway);
                    break;
                case "exclusiveGateway":
                    ExclusiveGateway exclusiveGateway = new ExclusiveGateway(e);
                    bpmn_elements.getExclusiveGatewayList().add(exclusiveGateway);
                    bpmn_elements.getGateWayList().add(exclusiveGateway);
                    System.out.println(exclusiveGateway);
                    break;
            }
        }
    }

    @Test
    public void read_test() throws DocumentException {
        BPMN_reader bpmn_reader = new BPMN_reader();
        bpmn_reader.read_bpmn("case.bpmn");
    }
}
