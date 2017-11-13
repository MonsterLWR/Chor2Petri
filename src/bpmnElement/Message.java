package bpmnElement;

import layout.Shapeable;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Message implements Elementable, Shapeable{
	private String id;
	
	public Message() {
		
	}
	
	public Message(Element element) {
		this.id = element.attributeValue("id");
	}

	@Override
	public Element toElement() {
		Element message = DocumentHelper.createElement("message");
//		添加属性
		message.addAttribute("id", getId());
		//返回标签
		return message;
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
		return "35.0";
	}

	@Override
	public String getHeight() {
		return "30.0";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id='" + id + '\'' +
				'}';
	}
}
