package translate_rule2;

import bpmnElement.BPMN_elements;
import bpmnElement.ParallelGateway;
import bpmnElement.SequenceFlow;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/11/23.
 */
public class Rule_5_6 extends Rule {
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<ParallelGateway> parallelGateways = bpmn_elements.getParallelGatewayList();

        for (ParallelGateway parallelGateway : parallelGateways) {
            String id = parallelGateway.getId();
            String name = parallelGateway.getName();
            //得到流入和流出的sequenceFlow
            List<SequenceFlow> from = RuleHelper.getFromSequence(bpmn_elements.getSequenceFlowList(), id);
            List<SequenceFlow> to = RuleHelper.getToSequence(bpmn_elements.getSequenceFlowList(), id);

//            根据id将parallelGateways转为对应的petri对象
            petri_elements.getTransition_list().add(new Transition(id + "_t", name + "_t"));

            for (SequenceFlow sequenceFlow : from) {
                if (!RuleHelper.exist(petri_elements.getPlace_list(), sequenceFlow.getId() + "_p"))
                    petri_elements.getPlace_list().add(new Place(sequenceFlow.getId() + "_p", name + "_p"));
                petri_elements.getArc_list().add(new Arc(sequenceFlow.getId() + "_a", sequenceFlow.getId() + "_p", id + "_t"));
            }

            for (SequenceFlow sequenceFlow : to) {
                if (!RuleHelper.exist(petri_elements.getPlace_list(), sequenceFlow.getId() + "_p"))
                    petri_elements.getPlace_list().add(new Place(sequenceFlow.getId() + "_p", name + "_p"));
                petri_elements.getArc_list().add(new Arc(sequenceFlow.getId() + "_a", id + "_t", sequenceFlow.getId() + "_p"));
            }
        }
    }
}
