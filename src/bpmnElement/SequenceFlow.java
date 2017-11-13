package bpmnElement;

import layout.Flowable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

// 连接线
public class SequenceFlow implements Elementable, Flowable{
	private String id;
	private String sourceRef;
	private String targetRef;

	public SequenceFlow() {

	}

	public SequenceFlow(Element element) {
		id = element.attributeValue("id");
		sourceRef = element.attributeValue("sourceRef");
		targetRef = element.attributeValue("targetRef");
	}

	@Override
	public Element toElement() {
		Element sequenceFlow = DocumentHelper.createElement("sequenceFlow");
//		添加属性
		sequenceFlow.addAttribute("id", getId());
		sequenceFlow.addAttribute("sourceRef", getSourceRef());
		sequenceFlow.addAttribute("targetRef", getTargetRef());

		//返回标签
		return sequenceFlow;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}

	public String getTargetRef() {
		return targetRef;
	}

	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}

	@Override
	public String toString() {
		return "SequenceFlow{" +
				"id='" + id + '\'' +
				", sourceRef='" + sourceRef + '\'' +
				", targetRef='" + targetRef + '\'' +
				'}';
	}
}
