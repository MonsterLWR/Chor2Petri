package UI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import org.dom4j.DocumentException;

import bpmnElement.BPMN_elements;
import petriElement.Petri_elements;
import pre_process_rule.PreRules_excecutor;
import translate_rule1.Rules_executor_1;
import translate_rule2.Rules_executor_2;
import xml_operation.BPMN_reader;
import xml_operation.BPMN_writer;
import xml_operation.Petri_writer;

public class UI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGHT = 600;
	
	// 设置模块
	JPanel northPanel = new JPanel();
	JPanel northPanel1 = new JPanel();
	JPanel northPanel2 = new JPanel();
	JPanel midPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel southPanel1 = new JPanel();
	JPanel southPanel2 = new JPanel();
	JPanel southPanel3 = new JPanel();
	
	// 设置字体
	Font f1 = new Font("楷体", Font.BOLD, 50);
	Font f2 = new Font("宋体", Font.BOLD, 20);
	Font f3 = new Font("", Font.BOLD, 15);
	Font f4 = new Font("楷体", Font.BOLD, 25);
	
	//标题
	JLabel title = new JLabel("BPMN文件转化器");
	JLabel title1 = new JLabel("选择文件:");
	JLabel title2 = new JLabel("文件内容:");
	JLabel title3 = new JLabel("BPMNtoPetri@1.0.0");
	
	
	// 按钮
	JButton Choose_Button =    new JButton("选择");
	JButton Pre_Button =    new JButton("BPMN预处理");
	JButton Petri1_Button = new JButton("生成Petri1");
	JButton Petri2_Button = new JButton("生成Petri2");
	JButton exit_Button =   new JButton("退出该程序");

	//文本框
	JTextField ChoFile_text =new JTextField("请选择您想要转换的BPMN文件",40);
	JTextArea jta= new JTextArea("这里将会显示文件内容", 7, 58);
    //在文本框上添加滚动条
    JScrollPane jsp = new JScrollPane(jta);
	
	//文件选择器
    String filePath = "";
    String outFilePath = "";
    String fileName = "";
    JFileChooser chooser=new JFileChooser("d:/");
    JFileChooser chooser1=new JFileChooser("d:/");
	
	public void userFrame() throws Exception {
		// 界面初始化
		this.setTitle("BPMNtoPetri.exe----软测大作业");
		this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // 获得屏幕的宽度
		int sHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // 获得屏幕的高度
		int fWidth = this.getWidth();// 获得窗口的宽度
		int fHeight = this.getHeight(); // 获得窗口的高度
		this.setLocation((sWidth - fWidth) / 2, (sHeight - fHeight) / 2);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(3, 1));
		this.add(northPanel);
		northPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(northPanel1);
		northPanel.add(northPanel2);
		title.setFont(f1);
		northPanel1.add(title);
		
		title1.setFont(f4);
		northPanel2.add(title1);
		ChoFile_text.setFont(f2);
		ChoFile_text.setEditable(false);
		Choose_Button.setFont(f4);
		northPanel2.add(ChoFile_text);
		northPanel2.add(Choose_Button);
		
		this.add(midPanel);
		title2.setFont(f4);
        //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
        jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jta.setFont(f2);
        jta.setEditable(false);
        midPanel.add(title2);
        midPanel.add(jsp);
		
		this.add(southPanel);
		southPanel.setLayout(new GridLayout(3, 1));
		southPanel.add(southPanel1);
		southPanel.add(southPanel2);
		southPanel.add(southPanel3);
		Pre_Button.setFont(f4);
		exit_Button.setFont(f4);
		Petri1_Button.setFont(f4);
		Petri2_Button.setFont(f4);
		title3.setFont(f3);
		
		southPanel2.add(Petri1_Button);
		southPanel2.add(Petri2_Button);
		southPanel2.add(Pre_Button);
		southPanel2.add(exit_Button);
		southPanel3.add(title3);
		this.setVisible(true);
		//添加监听
		Choose_Button.addActionListener(new ActionListener() {// 文件选择按钮
			public void actionPerformed(ActionEvent e) {
//				ChoFile_text.setText("");
//				ChoFile_text.setText("xxx");
//				System.out.println("文件选择");
				chooser.setFileFilter(new BPMNFilter());
		        int returnval=chooser.showDialog(null, "选择BPMN文件");
                if(returnval == JFileChooser.APPROVE_OPTION)
                {
                    filePath = chooser.getSelectedFile().getPath();
                    File f=chooser.getSelectedFile();   
                    fileName = f.getName(); 
                    if ((fileName != null) && (fileName.length() > 0)) {   
                        int dot = fileName.lastIndexOf('.');   
                        if ((dot >-1) && (dot < (fileName.length()))) {   
                        	fileName = fileName.substring(0, dot);   
                        }   
                    }
                    System.out.println(fileName);
                    ChoFile_text.setText(filePath);
                    File file = new File(filePath);  
                    BufferedReader reader = null;  
                    try {    
                        reader = new BufferedReader(new FileReader(file));  
                        String tempString = null;  
                        String total = null;
                        //int line = 1;  
                        // 一次读入一行，直到读入null为文件结束  
                        while ((tempString = reader.readLine()) != null) {  
                            // 显示行号  
                            total = total + tempString + "\n"; 
                            //line++;  
                        }  
                        jta.setText("");
                        jta.setFont(null);
                        jta.setText(total);
                        reader.close();  
                    } catch (IOException e1) {  
                        e1.printStackTrace();  
                    } finally {  
                        if (reader != null) {  
                            try {  
                                reader.close();  
                            } catch (IOException e1) {  
                            }  
                        }  
                    } 
                }
			}
		});
		
		Petri1_Button.addActionListener(new ActionListener() {// 生成Petri1按钮
			public void actionPerformed(ActionEvent e) {
				//System.out.println("生成Petri1");
				if(filePath != "") {
					chooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnval=chooser1.showDialog(null, "选择保存位置");
	                if(returnval == JFileChooser.APPROVE_OPTION){
	                	File fileDir = new File(chooser1.getSelectedFile().getPath());
	                	fileDir.mkdirs();
	                	outFilePath = chooser1.getSelectedFile().getPath()+"/Petri1_"+fileName+"_"+String.valueOf((int)((Math.random()*9+1)*100000))+".xml";
	                	System.out.println(outFilePath);
	                	//fileName = "";
	                	BPMN_reader reader = new BPMN_reader();
	                    BPMN_elements bpmn_elements = null;
						try {
							bpmn_elements = reader.read_bpmn(filePath);
							//预处理
							PreRules_excecutor preRules_excecutor = new PreRules_excecutor();
							preRules_excecutor.excecute(bpmn_elements);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    Petri_elements petri_elements = new Petri_elements();

	                    Rules_executor_1 rulesExecutor1 = new Rules_executor_1();
	                    rulesExecutor1.excecute(bpmn_elements, petri_elements);
	                    Petri_writer petri_writer = new Petri_writer();
	                    petri_writer.write(petri_elements, outFilePath);
	                    outFilePath = "";
	                    Font font = new Font("楷体",0,20);
					    UIManager.put("OptionPane.font", font);
					    UIManager.put("OptionPane.messageFont", font);
					    UIManager.put("OptionPane.buttonFont", font);
						JOptionPane.showMessageDialog(null, "转换成功", "操作提示",JOptionPane.PLAIN_MESSAGE);
	                }
				}
				else {
					Font font = new Font("楷体",0,20);
				    UIManager.put("OptionPane.font", font);
				    UIManager.put("OptionPane.messageFont", font);
				    UIManager.put("OptionPane.buttonFont", font);
					JOptionPane.showMessageDialog(null, "请先选择需要转换的文件", "操作提示",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		Petri2_Button.addActionListener(new ActionListener() {// 生成Petri2按钮
			public void actionPerformed(ActionEvent e) {
				//System.out.println("生成Petri2");
				if(filePath != "") {
					chooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnval=chooser1.showDialog(null, "选择保存位置");
	                if(returnval == JFileChooser.APPROVE_OPTION){
	                	File fileDir = new File(chooser1.getSelectedFile().getPath());
	                	fileDir.mkdirs();
	                	outFilePath = chooser1.getSelectedFile().getPath()+"/Petri2_"+fileName+"_"+String.valueOf((int)((Math.random()*9+1)*100000))+".xml";
	                	System.out.println(outFilePath);
	                	//fileName = "";
	                	BPMN_reader reader = new BPMN_reader();
	                    BPMN_elements bpmn_elements = null;
						try {
							bpmn_elements = reader.read_bpmn(filePath);
							//预处理
							PreRules_excecutor preRules_excecutor = new PreRules_excecutor();
							preRules_excecutor.excecute(bpmn_elements);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    Petri_elements petri_elements = new Petri_elements();

	                    Rules_executor_2 rulesExecutor2 = new Rules_executor_2();
	                    rulesExecutor2.excecute(bpmn_elements, petri_elements);
	                    Petri_writer petri_writer = new Petri_writer();
	                    petri_writer.write(petri_elements, outFilePath);
	                    outFilePath = "";
	                    Font font = new Font("楷体",0,20);
					    UIManager.put("OptionPane.font", font);
					    UIManager.put("OptionPane.messageFont", font);
					    UIManager.put("OptionPane.buttonFont", font);
						JOptionPane.showMessageDialog(null, "转换成功", "操作提示",JOptionPane.PLAIN_MESSAGE);
	                }
				}
				else {
					Font font = new Font("楷体",0,20);
				    UIManager.put("OptionPane.font", font);
				    UIManager.put("OptionPane.messageFont", font);
				    UIManager.put("OptionPane.buttonFont", font);
					JOptionPane.showMessageDialog(null, "请先选择需要转换的文件", "操作提示",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		Pre_Button.addActionListener(new ActionListener() {// BPMN预处理按钮
			public void actionPerformed(ActionEvent e) {
//				jta.setText("");
//				jta.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXX\nxxxxxxxxxxxxxx\nxxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\nxx\n");
//				System.out.println("BPMN预处理");
				if(filePath != "") {
					chooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnval=chooser1.showDialog(null, "选择保存位置");
	                if(returnval == JFileChooser.APPROVE_OPTION){
	                	File fileDir = new File(chooser1.getSelectedFile().getPath());
	                	fileDir.mkdirs();
	                	outFilePath = chooser1.getSelectedFile().getPath()+"/Pre_BPMN_"+fileName+"_"+String.valueOf((int)((Math.random()*9+1)*100000))+".bpmn";
	                	System.out.println(outFilePath);
	                	//fileName = "";
	                	BPMN_reader reader = new BPMN_reader();
				        BPMN_elements bpmn_elements = null;
						try {
							bpmn_elements = reader.read_bpmn(filePath);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

				        PreRules_excecutor preRules_excecutor = new PreRules_excecutor();
				        try {
							preRules_excecutor.excecute(bpmn_elements);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

				        BPMN_writer bpmn_writer = new BPMN_writer(bpmn_elements);
				        try {
							bpmn_writer.write(outFilePath);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        outFilePath = "";
				        Font font = new Font("楷体",0,20);
					    UIManager.put("OptionPane.font", font);
					    UIManager.put("OptionPane.messageFont", font);
					    UIManager.put("OptionPane.buttonFont", font);
						JOptionPane.showMessageDialog(null, "转换成功", "操作提示",JOptionPane.PLAIN_MESSAGE);
	                }
				}
				else {
					Font font = new Font("楷体",0,20);
				    UIManager.put("OptionPane.font", font);
				    UIManager.put("OptionPane.messageFont", font);
				    UIManager.put("OptionPane.buttonFont", font);
					JOptionPane.showMessageDialog(null, "请先选择需要转换的文件", "操作提示",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		exit_Button.addActionListener(new ActionListener() {// 退出按钮
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	class BPMNFilter extends FileFilter
    {
 
        public boolean accept(File f)
        {
            if (f.isDirectory())
            {
                return true;
            }
            String fileName = f.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension != null)
            {
                if (extension.equals("bpmn") || extension.equals("BPMN"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }
 
        public String getDescription()
        {
            return "Files(*.bpmn)";
        }
    }
	
	class XMLFilter extends FileFilter
    {
 
        public boolean accept(File f)
        {
            if (f.isDirectory())
            {
                return true;
            }
            String fileName = f.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension != null)
            {
                if (extension.equals("XML") || extension.equals("xml"))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }
 
        public String getDescription()
        {
            return "Files(*.xml)";
        }
    }
	
	public static void main(String[] args) throws Exception {
		new UI().userFrame();
	}
}
