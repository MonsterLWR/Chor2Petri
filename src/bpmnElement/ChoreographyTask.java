package bpmnElement;

import layout.Shapeable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/10.
 */
public class ChoreographyTask implements Elementable, Shapeable{

    private String id;
    private String initiatingParticipantRef;
    private String loopType;
    private String name;
    private List<String> incoming = new ArrayList<String>();
    private List<String> outgoing = new ArrayList<String>();
    private List<String> participantRef= new ArrayList<String>();
    private List<String> messageFlowRef= new ArrayList<String>();

    public ChoreographyTask() {
    }

    public ChoreographyTask(Element element) {
        id = element.attributeValue("id");
        initiatingParticipantRef = element.attributeValue("initiatingParticipantRef");
        loopType = element.attributeValue("loopType");
        name = element.attributeValue("name");
        //循环遍历子标签，并将标签保存为属性
        for (Element e : element.elements()) {
            switch (e.getName()) {
                case "incoming":
                    incoming.add(e.getText());
                    break;
                case "outgoing":
                    outgoing.add(e.getText());
                    break;
                case "participantRef":
                    participantRef.add(e.getText());
                    break;
                case "messageFlowRef":
                    messageFlowRef.add(e.getText());
                    break;
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInitiatingParticipantRef(String initiatingParticipantRef) {
        this.initiatingParticipantRef = initiatingParticipantRef;
    }

    public void setLoopType(String loopType) {
        this.loopType = loopType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncoming(List<String> incoming) {
        this.incoming = incoming;
    }

    public void setOutgoing(List<String> outgoing) {
        this.outgoing = outgoing;
    }

    public void setParticipantRef(List<String> participantRef) {
        this.participantRef = participantRef;
    }

    public void setMessageFlowRef(List<String> messageFlowRef) {
        this.messageFlowRef = messageFlowRef;
    }

    @Override
    public String toString() {
        return "ChoreographyTask{" +
                "id='" + id + '\'' +
                ", initiatingParticipantRef='" + initiatingParticipantRef + '\'' +
                ", loopType='" + loopType + '\'' +
                ", name='" + name + '\'' +
                ", incoming=" + incoming +
                ", outgoing=" + outgoing +
                ", participantRef=" + participantRef +
                ", messageFlowRef=" + messageFlowRef +
                '}';
    }

    @Override
    public Element toElement() {
        Element choreographyTask = DocumentHelper.createElement("choreographyTask");
//		添加属性
        choreographyTask.addAttribute("id", getId());
        choreographyTask.addAttribute("name", getName());
        choreographyTask.addAttribute("initiatingParticipantRef", getInitiatingParticipantRef());
        choreographyTask.addAttribute("loopType", getLoopType());
//		添加子标签
        for (String s : getIncoming()) {
            Element incoming = choreographyTask.addElement("incoming");
            incoming.addText(s);
        }
        for (String s : getOutgoing()) {
            Element outgoing = choreographyTask.addElement("outgoing");
            outgoing.addText(s);
        }
        for (String s : getParticipantRef()) {
            Element participantRef = choreographyTask.addElement("participantRef");
            participantRef.addText(s);
        }
        for (String s : getMessageFlowRef()) {
            Element messageFlowRef = choreographyTask.addElement("messageFlowRef");
            messageFlowRef.addText(s);
        }

        //返回标签
        return choreographyTask;
    }

    private String x;
    private String y;

    @Override
    public String getX() {
        return x;
    }

    @Override
    public void setX(String x) {
        this.x = x;
    }

    @Override
    public String getY() {
        return y;
    }

    @Override
    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String getWidth() {
        return "32.0";
    }

    @Override
    public String getHeight() {
        return "32.0";
    }

    public String getId() {
        return id;
    }

    public String getInitiatingParticipantRef() {
        return initiatingParticipantRef;
    }

    public String getLoopType() {
        return loopType;
    }

    public String getName() {
        return name;
    }

    public List<String> getIncoming() {
        return incoming;
    }

    public List<String> getOutgoing() {
        return outgoing;
    }

    public List<String> getParticipantRef() {
        return participantRef;
    }

    public List<String> getMessageFlowRef() {
        return messageFlowRef;
    }
}
