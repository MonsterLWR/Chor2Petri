package translate_rule;

import bpmnElement.*;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/11/13.
 */
public class Gateway_Rule_2 extends Rule {

    /**
     * @param bpmn_elements
     * @param petri_elements
     */
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        //得到需要转换的网关元素
        List<EventBasedGateway> eventBasedGateways = bpmn_elements.getEventBasedGatewayList();
        List<ExclusiveGateway> exclusiveGateways = bpmn_elements.getExclusiveGatewayList();
        List<GateWay> gateWays = new ArrayList<GateWay>();
        gateWays.addAll(eventBasedGateways);
        gateWays.addAll(exclusiveGateways);
        //得到所有sequenceFlow
        List<SequenceFlow> sequenceFlows = bpmn_elements.getSequenceFlowList();
        //得到petri网
        List<Place> places = petri_elements.getPlace_list();
        List<Arc> arcs = petri_elements.getArc_list();
        //开始遍历网关
        for (GateWay gateWay : gateWays) {
            //得到该网关的incoming和outgoing
            List<String> incomings = gateWay.getIncoming();
            List<String> outgoings = gateWay.getOutgoing();
            //判断是否符合规则的输入
            if ((incomings.size() == 1 && outgoings.size() >= 2)||(incomings.size()>=2 && outgoings.size() == 1)) {
                String id = gateWay.getId();
                String name = gateWay.getName();
                //用来保存该结构的输入id与输出id
                List<String> source_ids = new ArrayList<String>();
                List<String> target_ids = new ArrayList<String>();
                //保存需要删除的sequenceFlow
                List<SequenceFlow> remove_flows = new ArrayList<SequenceFlow>();
                //查找该结构中的sequenFlow，并添加对应id到上面的list变量中
                for (SequenceFlow sequenceFlow : sequenceFlows) {
                    if (sequenceFlow.getTargetRef().equals(id)) {
                        source_ids.add(sequenceFlow.getSourceRef());
                        remove_flows.add(sequenceFlow);
                    } else if (sequenceFlow.getSourceRef().equals(id)) {
                        target_ids.add(sequenceFlow.getTargetRef());
                        remove_flows.add(sequenceFlow);
                    }
                }
                sequenceFlows.removeAll(remove_flows);
                //对petri网进行处理
                places.add(new Place(id+"_p",name+"_p"));
                int count = 0;
                for (String s_id : source_ids) {
                    arcs.add(new Arc(id + "_a" + count++, s_id + "_t", id + "_p"));
                }
                for (String t_id : target_ids) {
                    arcs.add(new Arc(id + "_a" + count++, id + "_p", t_id + "_t"));
                }
            }

        }
    }


}
