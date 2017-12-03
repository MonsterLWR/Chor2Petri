package translate_rule2;

import java.util.List;

import bpmnElement.BPMN_elements;
import bpmnElement.ExclusiveGateway;
import bpmnElement.SequenceFlow;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

public class Rule_7_8 extends Rule {

	@Override
	public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
		List<ExclusiveGateway> exclusiveGateways = bpmn_elements.getExclusiveGatewayList();
		for (ExclusiveGateway exclusiveGateway : exclusiveGateways) {
			String id = exclusiveGateway.getId();
			String name = exclusiveGateway.getName();
			List<SequenceFlow> from = RuleHelper.getFromSequence(bpmn_elements.getSequenceFlowList(), id);
			List<SequenceFlow> to = RuleHelper.getToSequence(bpmn_elements.getSequenceFlowList(), id);

			if (from.size() == 1) {
				if (!RuleHelper.exist(petri_elements.getPlace_list(), from.get(0).getId() + "_p"))
					petri_elements.getPlace_list().add(new Place(from.get(0).getId() + "_p", name + "_p"));
				for (SequenceFlow sequenceFlow : to) {
					if (!RuleHelper.exist(petri_elements.getPlace_list(), sequenceFlow.getId() + "_p"))
						petri_elements.getPlace_list().add(new Place(sequenceFlow.getId() + "_p", name + "_p"));
					petri_elements.getTransition_list().add(new Transition(sequenceFlow.getId() + "_t", name + "_t"));
					petri_elements.getArc_list().add(new Arc(sequenceFlow.getId() + "_a", from.get(0).getId() + "_p",
							sequenceFlow.getId() + "_t"));
					petri_elements.getArc_list()
							.add(new Arc(sequenceFlow.getId() + "_a_a", sequenceFlow.getId() + "_t", sequenceFlow.getId() + "_p"));
				}
			} else {
				if (!RuleHelper.exist(petri_elements.getPlace_list(), to.get(0).getId() + "_p"))
					petri_elements.getPlace_list().add(new Place(to.get(0).getId() + "_p", name + "_p"));
				for (SequenceFlow sequenceFlow : from) {
					if (!RuleHelper.exist(petri_elements.getPlace_list(), sequenceFlow.getId() + "_p"))
						petri_elements.getPlace_list().add(new Place(sequenceFlow.getId() + "_p", name + "_p"));
					petri_elements.getTransition_list().add(new Transition(sequenceFlow.getId() + "_t", name + "_t"));
					petri_elements.getArc_list().add(new Arc(sequenceFlow.getId() + "_a", sequenceFlow.getId() + "_p",
							sequenceFlow.getId() + "_t"));
					petri_elements.getArc_list()
							.add(new Arc(sequenceFlow.getId() + "_a_a", sequenceFlow.getId() + "_t", to.get(0).getId() + "_p"));
				}
			}
		}
	}

}
