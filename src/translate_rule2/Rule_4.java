package translate_rule2;

import bpmnElement.BPMN_elements;
import bpmnElement.ChoreographyTask;
import bpmnElement.SequenceFlow;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/11/23.
 */
public class Rule_4 extends Rule {
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<ChoreographyTask> choreographyTasks = bpmn_elements.getChoreographyTaskList();
        for (ChoreographyTask choreographyTask : choreographyTasks) {
            String id = choreographyTask.getId();
            String name = choreographyTask.getName();
            //得到流入和流出的sequenceFlow
            SequenceFlow from = RuleHelper.getFromSequence(bpmn_elements.getSequenceFlowList(), id).get(0);
            SequenceFlow to = RuleHelper.getToSequence(bpmn_elements.getSequenceFlowList(), id).get(0);

//            根据id将choreographyTask转为对应的petri对象
            petri_elements.getTransition_list().add(new Transition(id + "_t", name + "_t"));

            //不存在该id的place才添加
            if (!RuleHelper.exist(petri_elements.getPlace_list(), from.getId() + "_p"))
                petri_elements.getPlace_list().add(new Place(from.getId() + "_p", name + "_p"));
            if (!RuleHelper.exist(petri_elements.getPlace_list(), to.getId() + "_p"))
                petri_elements.getPlace_list().add(new Place(to.getId() + "_p", name + "_p"));

            petri_elements.getArc_list().add(new Arc(from.getId() + "_a", from.getId() + "_p", id + "_t"));
            petri_elements.getArc_list().add(new Arc(to.getId() + "_a", id + "_t", to.getId() + "_p"));
        }
    }
}
