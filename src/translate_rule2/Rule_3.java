package translate_rule2;

import bpmnElement.BPMN_elements;
import bpmnElement.IntermediateThrowEvent;
import bpmnElement.SequenceFlow;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.List;

public class Rule_3 extends Rule {

    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<IntermediateThrowEvent> intermediateThrowEvents = bpmn_elements.getIntermediateThrowEventList();
        for (IntermediateThrowEvent intermediateThrowEvent : intermediateThrowEvents) {
            String id = intermediateThrowEvent.getId();
            String name = intermediateThrowEvent.getName();

            petri_elements.getTransition_list().add(new Transition(id + "_t", name + "_t"));

            List<SequenceFlow> froms = RuleHelper.getFromSequence(bpmn_elements.getSequenceFlowList(), id);
            if (froms.size() != 0) {
                SequenceFlow from = RuleHelper.getFromSequence(bpmn_elements.getSequenceFlowList(), id).get(0);
                //不存在该id的place才添加
                if (!RuleHelper.exist(petri_elements.getPlace_list(), from.getId() + "_p"))
                    petri_elements.getPlace_list().add(new Place(from.getId() + "_p", name + "_p"));
                petri_elements.getArc_list().add(new Arc(from.getId() + "_a", from.getId() + "_p", id + "_t"));
            }

            List<SequenceFlow> tos = RuleHelper.getToSequence(bpmn_elements.getSequenceFlowList(), id);
            if (tos.size() != 0) {
                SequenceFlow to = RuleHelper.getToSequence(bpmn_elements.getSequenceFlowList(), id).get(0);
                //不存在该id的place才添加
                if (!RuleHelper.exist(petri_elements.getPlace_list(), to.getId() + "_p"))
                    petri_elements.getPlace_list().add(new Place(to.getId() + "_p", name + "_p"));
                petri_elements.getArc_list().add(new Arc(to.getId() + "_a", id + "_t", to.getId() + "_p"));
            }

        }
    }

}
