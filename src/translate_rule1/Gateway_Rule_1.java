package translate_rule1;

import bpmnElement.BPMN_elements;
import bpmnElement.ParallelGateway;
import petriElement.Petri_elements;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/11/12.
 */
public class Gateway_Rule_1 extends Rule {
    /**
     * 网关映射规则-1
     *
     * @param bpmn_elements
     * @param petri_elements
     */
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<ParallelGateway> parallelGateways = bpmn_elements.getParallelGatewayList();
        //将ParallelGateway转换成一个transition
        for (ParallelGateway parallelGateway : parallelGateways) {
            String id = parallelGateway.getId();
            String name = parallelGateway.getName();
            petri_elements.getTransition_list().add(new Transition(id + "_t", name + "_t"));
        }
    }
}
