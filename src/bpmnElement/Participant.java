package bpmnElement;


import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by 李炆睿 on 2017/10/10.
 */
public class Participant implements Elementable{
    private String id;
    private String name;
    private String participantMultiplicity_maximum;
    private String participantMultiplicity_minimum;

    public Participant() {

    }

    public Participant(Element element) {
        id = element.attributeValue("id");
        name = element.attributeValue("name");
        //participantMultiplicity是一个子标签
        Element participantMultiplicity = element.element("participantMultiplicity");
        participantMultiplicity_maximum = participantMultiplicity.attributeValue("maximum");
        participantMultiplicity_minimum = participantMultiplicity.attributeValue("minimum");
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParticipantMultiplicity_maximum(String participantMultiplicity_maximum) {
        this.participantMultiplicity_maximum = participantMultiplicity_maximum;
    }

    public void setParticipantMultiplicity_minimum(String participantMultiplicity_minimum) {
        this.participantMultiplicity_minimum = participantMultiplicity_minimum;
    }

    @Override
    public Element toElement() {
        Element participant = DocumentHelper.createElement("participant");
//		添加属性
        participant.addAttribute("id", getId());
        participant.addAttribute("name", getName());
//		添加子标签
        Element participantMultiplicity = participant.addElement("participantMultiplicity");
        participantMultiplicity.addAttribute("maximum", getParticipantMultiplicity_maximum());
        participantMultiplicity.addAttribute("minimum", getParticipantMultiplicity_minimum());
        //返回标签
        return participant;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParticipantMultiplicity_maximum() {
        return participantMultiplicity_maximum;
    }

    public String getParticipantMultiplicity_minimum() {
        return participantMultiplicity_minimum;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", participantMultiplicity_maximum='" + participantMultiplicity_maximum + '\'' +
                ", participantMultiplicity_minimum='" + participantMultiplicity_minimum + '\'' +
                '}';
    }
}
