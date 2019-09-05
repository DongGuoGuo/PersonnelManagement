package mycom;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MianBan_A_yuangongcharu extends JPanel implements ActionListener{

	String quanxian=null;
	String xingbie=null;
	public static void main(String[] args) {
		MianBan_A_yuangongcharu q=new MianBan_A_yuangongcharu();
    }
	private static DbProcess dbProcess;
	
	// �������
		JComboBox comboBox=null;
		JComboBox comboBox_sex=null;
		JLabel jLStudentInfoTable = null;//���Ա����Ϣ��
		
		JLabel jLSid = null;//Ա����
		JLabel jLSpassword  = null;//����
		JLabel jLSauthority  = null;//Ȩ��
		JLabel jLSname = null;//����
		JLabel jLSsex = null;//�Ա�
		JLabel jLSDEPARTMENT = null;//���ڲ���
		JLabel JLSJOB = null;//ְ��
		JLabel jLSEDU_LEVEL = null;//ѧ��
		JLabel jLSADDRESS = null;//סַ
		JLabel jLSTEL = null;//�绰

		
		JTextField jTFid = null;//Ա����
		JTextField jTFpassword = null;//����
		JTextField jTFauthority  = null;//Ȩ��
		JTextField jTFname = null;//����
		JTextField jTFsex = null;//�Ա�
		JTextField jTFDEPARTMENT = null;//���ڲ���
		JTextField jTFJOB = null;//ְ��
		JTextField jTFSEDU_LEVEL = null;//ѧ��
		JTextField jTFSADDRESS= null;//סַ
		JTextField jTFSTEL = null;//�绰
		
		JButton jBInsert = null;//����
		JButton jBUpdate = null;//����
		
		JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10,jP11 = null;
		JPanel jPTop, jPBottom = null;
		DefaultTableModel studentTableModel = null;
		
		JTable studentJTable = null;
		JScrollPane studentJScrollPane = null;
		Vector studentVector = null;
		Vector titleVector = null;
	
		
	public MianBan_A_yuangongcharu() {
	// �������	
		System.out.println("qw");
		jLStudentInfoTable = new JLabel("���Ա����Ϣ�� ",JLabel.CENTER);
	
		jLSid = new JLabel("Ա  �� ��:");
		jLSpassword =new JLabel("��         ��:");
		jLSauthority =new JLabel("Ȩ         ��:");
		jLSname = new JLabel("��         ��:");
		jLSsex = new JLabel("��         ��:");
		jLSDEPARTMENT =new JLabel("���ڲ���:");
		JLSJOB = new JLabel("ְ         ��:");
		jLSEDU_LEVEL = new JLabel("ѧ         ��:");
		jLSADDRESS = new JLabel("ס         ַ:");
		jLSTEL =new JLabel("��         ��:");
		
		
		
		jTFid = new JTextField(20);//Ա����
		jTFpassword = new JTextField(20);//����
		jTFauthority = new JTextField(20);//Ȩ��
		jTFname = new JTextField(20);//����
		jTFsex = new JTextField(20);//�Ա�
		jTFDEPARTMENT = new JTextField(20);//���ڲ���
		jTFJOB = new JTextField(20);//ְ��
		jTFSEDU_LEVEL = new JTextField(20);//ѧ��
		jTFSADDRESS = new JTextField(20);//סַ
		jTFSTEL = new JTextField(20);//�绰
		
		
	

		jBInsert = new JButton("����");
		jBInsert.setSize(30, 20);
		jBUpdate = new JButton("����");
		// ���ü���
		jBInsert.addActionListener(this);
		jBUpdate.addActionListener(this);
		

		//jP1���� ���
		jP1 = new JPanel();	
		jP1.add(jLStudentInfoTable,BorderLayout.CENTER);
		

		
		comboBox=new JComboBox();  
		comboBox.setPreferredSize(new Dimension(225, 30));
        comboBox.addItem("a");  
        comboBox.addItem("b");
        
        comboBox_sex=new JComboBox();  
        comboBox_sex.setPreferredSize(new Dimension(225, 30));
        comboBox_sex.addItem("��");  
        comboBox_sex.addItem("Ů");

		//jP2 ��һ�����
		jP2 = new JPanel();
		jP2.add(jLSauthority);
		jP2.add(comboBox);
		
		jP4 = new JPanel();
		jP4.add(jLSname);
		jP4.add(jTFname);
		
		jP3 = new JPanel();
		jP3.add(jLSsex);
		jP3.add(comboBox_sex);

		jP5 = new JPanel();
		jP5.add(jLSDEPARTMENT);
		jP5.add(jTFDEPARTMENT);


		
		jP6 = new JPanel();
		jP6.add(JLSJOB);
		jP6.add(jTFJOB);
		
		jP7 = new JPanel();
		jP7.add(jLSEDU_LEVEL);
		jP7.add(jTFSEDU_LEVEL);
		
		jP8 = new JPanel();
		jP8.add(jLSADDRESS);
		jP8.add(jTFSADDRESS);
		
		jP9 = new JPanel();
		jP9.add(jLSTEL);
		jP9.add(jTFSTEL);
		

		//jP5 ��ť���
		jP10 = new JPanel();
		jP10.add(jBInsert);
//		jP10.add(jBUpdate);
		
		
	
		jP11 = new JPanel();
		jP11.setLayout(new GridLayout(10,1));
		jP11.add(jP1);
		jP11.add(jP2);
		jP11.add(jP3);
		jP11.add(jP4);
		jP11.add(jP5);
		jP11.add(jP6);
		jP11.add(jP7);
		jP11.add(jP8);
		jP11.add(jP9);
		jP11.add(jP10);

		dbProcess = new DbProcess();
		

		this.add(jP11);
		
		this.setSize(1000, 600);
		this.setLocation(300, 200);
		this.setVisible(true);
//		this.setTitle("���¹���ϵͳ");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("����")){
			System.out.println(" ����˲��밴ť");
			if(jTFname.getText().isEmpty()||jTFDEPARTMENT.getText().isEmpty()
					||jTFJOB.getText().isEmpty()||jTFSEDU_LEVEL.getText().isEmpty()
					||jTFSADDRESS.getText().isEmpty()||jTFSTEL.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,
						"�뽫������д����","��ʾ",JOptionPane.ERROR_MESSAGE);
			}else{
				insertProcess();
			}
		}
	}
		

	
	public void insertProcess()
	{
		System.out.println("�Ѿ����ò��뺯����");
		String id = jTFid.getText().trim();
		String password = jTFpassword.getText().trim();
		
		quanxian=comboBox.getSelectedItem().toString();
		xingbie=comboBox_sex.getSelectedItem().toString();
		String authority = jTFauthority.getText().trim();
		
		String name = jTFname.getText().trim();
//		String sex = jTFsex.getText().trim();
		String DEPARTMENT = jTFDEPARTMENT.getText().trim();
		String JOB = jTFJOB.getText().trim();
		String EDU_LEVEL = jTFSEDU_LEVEL.getText().trim();
		String ADDRESS = jTFSADDRESS.getText().trim();
		String TEL = jTFSTEL.getText().trim();
		// ������������
		String sql = "insert into person (authority,name,sex,DEPARTMENT,JOB,EDU_LEVEL,ADDRESS,TEL) values('";
		sql = sql + quanxian + "','";
		sql = sql + name + "','";
		sql = sql + xingbie + "','";
		sql = sql + DEPARTMENT + "','";
		sql = sql + JOB + "','";
		sql = sql + EDU_LEVEL + "','";
		sql = sql + ADDRESS + "','";
		sql = sql + TEL + "');";

		System.out.println("�������Ϊ" + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("insertProcess(). insert database failed.");
			}
			else{
				System.out.println("���ݲ���ɹ�!");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݴ���","����",JOptionPane.ERROR_MESSAGE);
		}
	}
}
