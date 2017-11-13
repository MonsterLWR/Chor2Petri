package bpmnElement;

import layout.Flowable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Association implements Elementable, Flowable{
	private String associationDirection;
	private String id;
	private String sourceRef;
	private String targetRef;

	public Association() {

	}

	public Association(Element element) {
		this.associationDirection = element.attributeValue("associationDirection");
		this.id = element.attributeValue("id");
		this.sourceRef = element.attributeValue("sourceRef");
		this.targetRef = element.attributeValue("targetRef");
	}

	public String getAssociationDirection() {
		return associationDirection;
	}

	public void setAssociationDirection(String associationDirection) {
		this.associationDirection = associationDirection;
	}

	@Override
	public Element toElement() {
		Element association = DocumentHelper.createElement("association");
//		添加属性
		association.addAttribute("id", getId());
		association.addAttribute("sourceRef", getSourceRef());
		association.addAttribute("targetRef", getTargetRef());
		association.addAttribute("associationDirection", getAssociationDirection());

		//返回标签
		return association;
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
		return "Association{" +
				"associationDirection='" + associationDirection + '\'' +
				", id='" + id + '\'' +
				", sourceRef='" + sourceRef + '\'' +
				", targetRef='" + targetRef + '\'' +
				'}';
	}
}
