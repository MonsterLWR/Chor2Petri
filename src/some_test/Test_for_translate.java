package some_test;

import bpmnElement.BPMN_elements;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import tag_for_petri.Net;
import petriElement.Petri_elements;
import tag_for_petri.Pnml;
import petriElement.Token;
import tag_for_petri.Tag;
import translate_rule.Basic_Rule_1;
import translate_rule.Rule;
import translate_rule.Rules_executor;
import xml_operation.BPMN_reader;
import xml_operation.Petri_writer;

import java.io.FileOutputStream;
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
    public void test_rule_one() throws DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");
        Petri_elements petri_elements = new Petri_elements();
//        System.out.println(bpmn_elements);
        Rule rule = new Basic_Rule_1();
        rule.excecute(bpmn_elements, petri_elements);
        output(petri_elements);
    }

    @Test
    public void test_rules() throws DocumentException {
        BPMN_reader reader = new BPMN_reader();
        BPMN_elements bpmn_elements = reader.read_bpmn("case.bpmn");
        Petri_elements petri_elements = new Petri_elements();

        Rules_executor rulesExecutor = new Rules_executor();
        rulesExecutor.excecute(bpmn_elements, petri_elements);
        output(petri_elements);
    }
}
