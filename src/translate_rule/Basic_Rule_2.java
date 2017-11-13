package translate_rule;

import bpmnElement.BPMN_elements;
import bpmnElement.ChoreographyTask;
import bpmnElement.StartEvent;
import petriElement.Arc;
import petriElement.Petri_elements;
import petriElement.Place;
import petriElement.Transition;

import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Basic_Rule_2 extends Rule{
    @Override
    public void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements) {
        List<ChoreographyTask> choreographyTasks = bpmn_elements.getChoreographyTaskList();
        for (ChoreographyTask choreographyTask : choreographyTasks) {
            String id = choreographyTask.getId();
            String name = choreographyTask.getName();
//            根据id将choreographyTask转为对应的petri对象
            petri_elements.getTransition_list().add(new Transition(id + "_t",name + "_t"));
        }
    }
}
