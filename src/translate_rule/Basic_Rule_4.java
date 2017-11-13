package translate_rule;

import bpmnElement.BPMN_elements;
import bpmnElement.SequenceFlow;
import bpmnElement.StartEvent;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Basic_Rule_4 extends Rule{
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<SequenceFlow> sequenceFlows = bpmn_elements.getSequenceFlowList();
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            String id = sequenceFlow.getId();
            String source = sequenceFlow.getSourceRef();
            String target = sequenceFlow.getTargetRef();
//            根据id,source,target将sequenceFlow为对应的petri对象
            petri_elements.getPlace_list().add(new Place(id+"_p","sequenceFlow_p"));
            petri_elements.getArc_list().add(new Arc(id+"_a1",source+ "_t",id+"_p"));
            petri_elements.getArc_list().add(new Arc(id+"_a2",id+"_p",target+ "_t"));
        }
    }
}
