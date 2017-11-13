package translate_rule;

import bpmnElement.BPMN_elements;
import bpmnElement.ChoreographyTask;
import bpmnElement.IntermediateThrowEvent;
import petriElement.Petri_elements;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/24.
 */
public class Basic_Rule_3 extends Rule {

    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<IntermediateThrowEvent> intermediateThrowEventList = bpmn_elements.getIntermediateThrowEventList();
        for (IntermediateThrowEvent intermediateThrowEvent : intermediateThrowEventList) {
            String id = intermediateThrowEvent.getId();
            String name = intermediateThrowEvent.getName();
//            根据id将IntermediateThrowEvent转为对应的petri对象
            petri_elements.getTransition_list().add(new Transition(id + "_t",name + "_t"));
        }
    }
}
