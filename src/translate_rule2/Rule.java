package translate_rule2;

import bpmnElement.BPMN_elements;
import petriElement.Petri_elements;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public abstract class Rule {
    public abstract void excecute(BPMN_elements bpmn_elements, Petri_elements petri_elements);
}
