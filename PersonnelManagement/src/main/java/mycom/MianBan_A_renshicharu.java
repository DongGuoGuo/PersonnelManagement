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

public class MianBan_A_renshicharu extends JPanel implements ActionListener{
	
	public static void main(String[] args) {
		MianBan_A_renshicharu q=new MianBan_A_renshicharu();
    }
	private static DbProcess dbProcess;
	
	// �������
		JLabel jLStudentInfoTable = null;//������±䶯��
		
		JLabel jLSPERSON  = null;//Ա����
		JLabel jLSSCHANGE   = null;//����
		JLabel jLSDESCRIPTION   = null;//Ȩ��

		
		JTextField jTFPERSON = null;//Ա����
		JTextField jTFSCHANGE = null;//����
		JTextField jTFDESCRIPTION  = null;//Ȩ��
		
		JButton jBInsert = null;//����
		JButton jBUpdate = null;//����
		
		JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10,jP11 = null;
		JPanel jPTop, jPBottom = null;
		DefaultTableModel studentTableModel = null;
		
		JTable studentJTable = null;
		JScrollPane studentJScrollPane = null;
		Vector studentVector = null;
		Vector titleVector = null;
	
		
	public MianBan_A_renshicharu() {
	// �������	
		System.out.println("qw");
		jLStudentInfoTable = new JLabel("������±䶯�� ",JLabel.CENTER);
	
		jLSPERSON  = new JLabel("Ա  �� ��:");
		jLSSCHANGE  =new JLabel("�䶯����:");
		jLSDESCRIPTION  =new JLabel("��ϸ��Ϣ:");
		
		
		jTFPERSON = new JTextField(20);//Ա����
		jTFSCHANGE = new JTextField(20);//����
		jTFDESCRIPTION = new JTextField(20);//Ȩ��
		
		
	

		jBInsert = new JButton("����");
		jBInsert.setSize(30, 20);
		jBUpdate = new JButton("����");
		// ���ü���
		jBInsert.addActionListener(this);
		jBUpdate.addActionListener(this);
		

		//jP1���� ���
		jP1 = new JPanel();	
		jP1.add(jLStudentInfoTable,BorderLayout.CENTER);
		

		//jP2 ��һ�����
		
		

		jP2 = new JPanel();
		jP2.add(jLSPERSON);
		jP2.add(jTFPERSON);
		
		
		jP3 = new JPanel();
		jP3.add(jLSSCHANGE );
		jP3.add(jTFSCHANGE);
		
		
		jP4 = new JPanel();
		jP4.add(jLSDESCRIPTION );
		jP4.add(jTFDESCRIPTION);

		

		//jP5 ��ť���
		jP10 = new JPanel();
		jP10.add(jBInsert);
//		jP10.add(jBUpdate);
		
		
	
		jP11 = new JPanel();
		jP11.setLayout(new GridLayout(5,1));
		jP11.add(jP1);
		jP11.add(jP2);
		jP11.add(jP3);
		jP11.add(jP4);
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
			if(jTFPERSON.getText().isEmpty()||jTFSCHANGE.getText().isEmpty()||jTFDESCRIPTION.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"�뽫������д����","��ʾ",JOptionPane.ERROR_MESSAGE);
			}else{
				insertProcess();
			}
		}
	}
		

	
	public void insertProcess()
	{
		System.out.println("�Ѿ����ò��뺯����");
		String PERSON = jTFPERSON.getText().trim();
		String SCHANGE = jTFSCHANGE.getText().trim();
		String DESCRIPTION = jTFDESCRIPTION.getText().trim();
		// ������������
		String sql = "insert into personnel (PERSON ,SCHANGE ,DESCRIPTION) values(";
		sql = sql + PERSON + ",";
		sql = sql + SCHANGE + ",'";
		sql = sql + DESCRIPTION + "');";
		

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
