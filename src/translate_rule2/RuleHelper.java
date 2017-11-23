package translate_rule2;


import bpmnElement.BPMN_elements;
import bpmnElement.SequenceFlow;
import petriElement.Arc;
import petriElement.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/11/23.
 */
public class RuleHelper {
    /**
     * 根据id得到指向该id的sequenceFlow
     * @param sequenceFlowList
     * @param id
     * @return
     */
    public static List<SequenceFlow> getFromSequence(List<SequenceFlow> sequenceFlowList, String id) {
        List<SequenceFlow> sequenceFlows = new ArrayList<>();
        for (SequenceFlow sequenceFlow : sequenceFlowList) {
            if (sequenceFlow.getTargetRef().equals(id)) {
                sequenceFlows.add(sequenceFlow);
            }
        }
        return sequenceFlows;
    }


    /**
     * 根据id得到源头为该id的sequenceFlow
     * @param sequenceFlowList
     * @param id
     * @return
     */
    public static List<SequenceFlow> getToSequence(List<SequenceFlow> sequenceFlowList, String id) {
        List<SequenceFlow> sequenceFlows = new ArrayList<>();
        for (SequenceFlow sequenceFlow : sequenceFlowList) {
            if (sequenceFlow.getSourceRef().equals(id)) {
                sequenceFlows.add(sequenceFlow);
            }
        }
        return sequenceFlows;
    }

    /**
     * 检查places中是否已经存在指定id的place
     * @param places
     * @param id
     * @return
     */
    public static boolean exist(List<Place> places,String id) {
        for (Place place : places) {
            if (place.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
