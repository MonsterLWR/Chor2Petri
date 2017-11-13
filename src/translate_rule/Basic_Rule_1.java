package translate_rule;

import bpmnElement.BPMN_elements;
import bpmnElement.StartEvent;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Basic_Rule_1 extends Rule{
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<StartEvent> startEvents = bpmn_elements.getStartEventList();
        for (StartEvent startEvent : startEvents) {
            String id = startEvent.getId();
            String name = startEvent.getName();
//            根据id将startevent为对应的petri对象
            petri_elements.getPlace_list().add(new Place(id+"_p",name + "_p"));
            petri_elements.getTransition_list().add(new Transition(id + "_t",name + "_t"));
            petri_elements.getArc_list().add(new Arc(id+"_a",id+"_p",id + "_t"));
        }
    }
}
