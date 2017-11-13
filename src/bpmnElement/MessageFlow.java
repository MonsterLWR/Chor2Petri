package bpmnElement;


import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by 李炆睿 on 2017/10/10.
 */
public class MessageFlow implements Elementable{
    private String id;
    private String sourceRef;
    private String targetRef;

    public MessageFlow() {

    }

    public MessageFlow(Element element) {
        id = element.attributeValue("id");
        sourceRef = element.attributeValue("sourceRef");
        targetRef = element.attributeValue("targetRef");
    }

    @Override
    public Element toElement() {
        Element messageFlow = DocumentHelper.createElement("messageFlow");
//		添加属性
        messageFlow.addAttribute("id", getId());
        messageFlow.addAttribute("sourceRef", getSourceRef());
        messageFlow.addAttribute("targetRef", getTargetRef());

        //返回标签
        return messageFlow;
    }

    public String getId() {
        return id;
    }

    public String getSourceRef() {
        return sourceRef;
    }

    public String getTargetRef() {
        return targetRef;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }

    @Override
    public String toString() {
        return "MessageFlow{" +
                "id='" + id + '\'' +
                ", sourceRef='" + sourceRef + '\'' +
                ", targetRef='" + targetRef + '\'' +
                '}';
    }
}
