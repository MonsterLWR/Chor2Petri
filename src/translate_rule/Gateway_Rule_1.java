package translate_rule;

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
//        for (ParallelGateway parallelGateway : parallelGateways) {
////            得到该网关的incoming和outgoing
//            List<String> incomings = parallelGateway.getIncoming();
//            List<String> outgoings = parallelGateway.getOutgoing();
//            //判断是否符合规则的输入
//            if (incomings.size() == 1 && outgoings.size() >= 2) {
//                //得到petri网
//                List<Place> places = petri_elements.getPlace_list();
//                List<Transition> transitions = petri_elements.getTransition_list();
//                List<Arc> arcs = petri_elements.getArc_list();
//                //得到网管的id和name
//                String id = parallelGateway.getId();
//                String name = parallelGateway.getName();
//                //添加place
//                places.add(new Place(id + "_p0", name + "_p0"));
//                for (int i = 1; i <= outgoings.size(); ++i) {
//                    places.add(new Place(id + "_p" + i, name + "_p0" + i));
//                }
//                //添加transition
//                transitions.add(new Transition(id + "_t", name + "_t"));
//                //添加arc
//
//            } else if (incomings.size() >= 2 && outgoings.size() == 1) {
//                //得到petri网
//                List<Place> places = petri_elements.getPlace_list();
//                List<Transition> transitions = petri_elements.getTransition_list();
//                List<Arc> arcs = petri_elements.getArc_list();
//            }
//        }
    }
}
