package translate_rule2;

import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.SequenceFlow;
import bpmnElement.StartEvent;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

public class Rule_1 extends Rule {

	@Override
	public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
		List<StartEvent> startEvents = bpmn_elements.getStartEventList();
		for (StartEvent startEvent : startEvents) {
			String id = startEvent.getId();
			String name = startEvent.getName();
			
			List<SequenceFlow> to = RuleHelper.getToSequence(bpmn_elements.getSequenceFlowList(), id);
			
			petri_elements.getPlace_list().add(new Place(id + "_p", name + "_p"));
			
			petri_elements.getTransition_list().add(new Transition(id + "_t", name + "_t"));
			
			for (SequenceFlow sequenceFlow : to) {
				if (!RuleHelper.exist(petri_elements.getPlace_list(), sequenceFlow.getId() + "_p"))
					petri_elements.getPlace_list().add(new Place(sequenceFlow.getId() + "_p", name + "_p"));
				petri_elements.getArc_list().add(new Arc(sequenceFlow.getId() + "_a", id + "_p", id + "_t"));
				petri_elements.getArc_list().add(new Arc(sequenceFlow.getId() + "_a_a", id + "_t", sequenceFlow.getId() + "_p"));
			}
		}
	}

}
