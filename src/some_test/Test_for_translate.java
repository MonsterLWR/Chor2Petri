package some_test;

import bpmnElement.BPMN_elements;
import org.dom4j.DocumentException;
import org.junit.Test;
import pre_process_rule.PreRules_excecutor;
import petriElement.Petri_elements;
import translate_rule1.Rules_executor_1;
import translate_rule2.Rules_executor_2;
import xml_operation.BPMN_reader;
import xml_operation.BPMN_writer;
import xml_operation.Petri_writer;

import java.io.IOException;

/**
 * Created by 李炆睿 on 2017/10/15.
 */
public class Test_for_translate {

    private void output(Petri_elements petri_elements) {
        Petri_writer petri_writer = new Petri_writer();
        petri_writer.write(petri_elements,"test.xml");
    }

    @Test
    public void test_translate_rules() throws DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");
        Petri_elements petri_elements = new Petri_elements();

        Rules_executor_1 rulesExecutor = new Rules_executor_1();
        rulesExecutor.excecute(bpmn_elements, petri_elements);
        output(petri_elements);
    }

    @Test
    public void test_pre_and_translate_rules() throws DocumentException, IOException {
        //读取
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");
        //预处理
        PreRules_excecutor preRules_excecutor = new PreRules_excecutor();
        preRules_excecutor.excecute(bpmn_elements);
        //生成预处理后的bpmn
        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
        bpmn_writer.write("BPMN.bpmn");
        //转换规则
        Petri_elements petri_elements = new Petri_elements();
        Rules_executor_1 rulesExecutor = new Rules_executor_1();
        rulesExecutor.excecute(bpmn_elements, petri_elements);
        //生成xml
        output(petri_elements);
    }

    @Test
    public void test_translate_rules2() throws DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");
        Petri_elements petri_elements = new Petri_elements();

        Rules_executor_2 rulesExecutor2 = new Rules_executor_2();
        rulesExecutor2.excecute(bpmn_elements, petri_elements);
        output(petri_elements);
    }
}
