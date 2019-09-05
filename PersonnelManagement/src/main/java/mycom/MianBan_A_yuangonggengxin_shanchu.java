package mycom;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

class MianBan_A_yuangonggengxin_shanchu extends JPanel implements ActionListener {

	private static DbProcess dbProcess;
	String quanxian=null;
	public static void main(String[] args) {
		MianBan_A_yuangonggengxin_shanchu q=new MianBan_A_yuangonggengxin_shanchu();
    }
	// �������
	JComboBox comboBox=null;
	JLabel jLSTishi = null;
	JLabel jLSid = null;//Ա����1
	JLabel jLSauthority  = null;//Ȩ��
	JLabel jLSDEPARTMENT = null;//���ڲ���
	JLabel JLSJOB = null;//ְ��
	JLabel jLSEDU_LEVEL = null;//ѧ��
	JLabel jLSADDRESS = null;//סַ
	JLabel jLSTEL = null;//�绰


	JTextField jTFid = null;//Ա����1
	JTextField jTFDEPARTMENT = null;//���ڲ���
	JTextField jTFJOB = null;//ְ��
	JTextField jTFSEDU_LEVEL = null;//ѧ��
	JTextField jTFSADDRESS= null;//סַ
	JTextField jTFSTEL = null;//�绰
	
	JButton jBQuery = null;//��ѯ
	JButton jBUpdate = null;//����
	JButton chongzhimima = null;//����
	JButton jBDeleteCurrentRecord = null;//ɾ����ǰ��¼
	
	JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10,jP11,jP12,jP13,jP14,jP15 = null;
	
	

	// ���캯��
	public MianBan_A_yuangonggengxin_shanchu() {
		
		jLSTishi = new JLabel("Ա����Ϣ����");
		
		jLSid=new JLabel("������Ҫ���µ�Ա����:");
		jLSauthority =new JLabel("Ȩ         ��:");
		jLSDEPARTMENT =new JLabel("���ڲ���:");
		JLSJOB = new JLabel("ְ         ��:");
		jLSEDU_LEVEL = new JLabel("ѧ         ��:");
		jLSADDRESS = new JLabel("ס         ַ:");
		jLSTEL =new JLabel("��         ��:");
		
		jTFid = new JTextField(20);//
//		jTFpassword = new JTextField(20);//����
		jTFDEPARTMENT = new JTextField(20);//���ڲ���
		jTFJOB = new JTextField(20);//ְ��
		jTFSEDU_LEVEL = new JTextField(20);//ѧ��
		jTFSADDRESS = new JTextField(20);//סַ
		jTFSTEL = new JTextField(20);//�绰
		
		jTFid.setEditable(true);
//		jTFpassword.setEditable(false);
		jTFDEPARTMENT.setEditable(false);
		jTFJOB.setEditable(false);
		jTFSEDU_LEVEL.setEditable(false);
		jTFSADDRESS.setEditable(false);
		jTFSTEL.setEditable(false);
		
		jBQuery = new JButton("��ѯ");
		jBUpdate = new JButton("����");
		chongzhimima = new JButton("��������");
		jBDeleteCurrentRecord = new JButton("ɾ����ǰ��¼");
		jBUpdate.setEnabled(false);
		chongzhimima.setEnabled(false);
		jBDeleteCurrentRecord.setEnabled(false);

		// ���ü���
		jBQuery.addActionListener(this);
		jBUpdate.addActionListener(this);
		chongzhimima.addActionListener(this);
		jBDeleteCurrentRecord.addActionListener(this);

		jP1 = new JPanel();	
		jP1.add(jLSTishi,BorderLayout.CENTER);
		
		jP2 = new JPanel();	
		jP2.add(jLSid);
		jP2.add(jTFid);

		jP13=new JPanel();
//		jP13.setLayout(new FlowLayout());
		jP13.add(jBQuery);

		jP14=new JPanel();
		jP14.add(jP2,BorderLayout.CENTER);
		jP14.add(jP13,BorderLayout.SOUTH);
		
		
		comboBox=new JComboBox();  
		comboBox.setPreferredSize(new Dimension(220, 30));
		comboBox.setEnabled(false);
        comboBox.addItem("a");  
        comboBox.addItem("b");  
	
		
		jP3 = new JPanel();	
		jP3.add(jLSauthority);
		jP3.add(comboBox);
		
		jP4 = new JPanel();
		jP4.add(jLSDEPARTMENT);
		jP4.add(jTFDEPARTMENT);
		
		jP5 = new JPanel();
		jP5.add(JLSJOB);
		jP5.add(jTFJOB);
		
		jP6 = new JPanel();
		jP6.add(jLSEDU_LEVEL);
		jP6.add(jTFSEDU_LEVEL);

		
		jP7 = new JPanel();
		jP7.add(jLSADDRESS);
		jP7.add(jTFSADDRESS);
		
		jP8 = new JPanel();
		jP8.add(jLSTEL);
		jP8.add(jTFSTEL);
		
		
		
		jP12 = new JPanel();
		jP12.setLayout(new GridLayout(7,1));
		jP12.add(jP14);
		jP12.add(jP3);
		jP12.add(jP4);
		jP12.add(jP5);
		jP12.add(jP6);
		jP12.add(jP7);
		jP12.add(jP8);

		jP15 = new JPanel();
		jP15.setLayout(new FlowLayout(FlowLayout.CENTER));
		jP15.add(chongzhimima);
		jP15.add(jBUpdate);
		jP15.add(jBDeleteCurrentRecord);
		
		this.setLayout(new BorderLayout());
		this.add(jP1,BorderLayout.NORTH);
		this.add(jP12,BorderLayout.CENTER);
		this.add(jP15,BorderLayout.SOUTH);
		dbProcess = new DbProcess();
		this.setSize(700, 600);
		this.setLocation(300, 200);
		this.setVisible(true);
//		this.setTitle("���¹���ϵͳ");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void bianji1(){
		jTFid.setEditable(true);
		comboBox.setEnabled(false); //bu�ɱ��༭
		jTFDEPARTMENT.setEditable(false);
		jTFJOB.setEditable(false);
		jTFSEDU_LEVEL.setEditable(false);
		jTFSADDRESS.setEditable(false);
		jTFSTEL.setEditable(false);
		

		jBUpdate.setEnabled(false);
		chongzhimima.setEnabled(false);
		jBDeleteCurrentRecord.setEnabled(false);
	}
	
	
	public void bianji2(){
		jTFid.setEditable(false);
		comboBox.setEnabled(true); //�ɱ��༭
		jTFDEPARTMENT.setEditable(true);
		jTFJOB.setEditable(true);
		jTFSEDU_LEVEL.setEditable(true);
		jTFSADDRESS.setEditable(true);
		jTFSTEL.setEditable(true);
		

		jBUpdate.setEnabled(true);
		chongzhimima.setEnabled(true);
		jBDeleteCurrentRecord.setEnabled(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��ѯ")  
				&& !jTFid.getText().isEmpty()){
				System.out.println("actionPerformed(). ��ѯ");
				String sQueryField = jTFid.getText().trim();
				queryProcess(sQueryField);
				
		}else if(e.getActionCommand().equals("����")
//				&& !jTFpassword.getText().isEmpty()
				&& !jTFDEPARTMENT.getText().isEmpty()
				&& !jTFJOB.getText().isEmpty()
				&& !jTFSEDU_LEVEL.getText().isEmpty()
				&& !jTFSADDRESS.getText().isEmpty()
				&& !jTFSTEL.getText().isEmpty()){
			System.out.println("actionPerformed(). ����");
			updateProcess();
			bianji1();
		}else if(e.getActionCommand().equals("ɾ����ǰ��¼")){
			System.out.println("actionPerformed(). ɾ����ǰ��¼");
			deleteCurrentRecordProcess();
			bianji1();
		}else if(e.getActionCommand().equals("��������")){
			mimachongzhi();

			bianji1();
			System.out.println("�ɹ���������");
		}
	}
	public void deleteCurrentRecordProcess()
	{
		String sNo = jTFid.getText().trim();
		
		// ����ɾ������
		String sql = "delete from person where id = " + sNo + ";";
		System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("deleteCurrentRecordProcess(). delete database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
		jTFid.setText("");
//		jTFpassword.setText("");
		jTFDEPARTMENT.setText("");
		jTFJOB.setText("");
		jTFSEDU_LEVEL.setText("");
		jTFSADDRESS.setText("");
		jTFSTEL.setText("");
	}
	
	public void queryProcess(String sQueryField)
	{
		try{
			// ������ѯ����
			String sql = "select * from person where id = ";
			sql = sql + sQueryField + ";";
		//	System.out.println("��ѯ����Ϊ :" + sql);
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			
			if(rs.next()){
				bianji2();
			
				
				rs.previous(); 
				while(rs.next()){
//				jTFpassword.setText(rs.getString("authority"));
				System.out.println("��Ա��Ȩ��Ϊ"+rs.getString("authority"));
				comboBox.setSelectedIndex(1);
				jTFDEPARTMENT.setText(rs.getString("DEPARTMENT"));
				jTFJOB.setText(rs.getString("JOB"));
				jTFSEDU_LEVEL.setText(rs.getString("EDU_LEVEL"));
				jTFSADDRESS.setText(rs.getString("ADDRESS"));
				jTFSTEL.setText(rs.getString("TEL"));
			}
			}else{
				JOptionPane.showMessageDialog(null,"��Ա��������","��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
			dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	

	public void updateProcess()
	{
		String id = jTFid.getText().trim();
		quanxian=comboBox.getSelectedItem().toString();
//		String password = jTFpassword.getText().trim();
		String DEPARTMENT = jTFDEPARTMENT.getText().trim();
		String JOB = jTFJOB.getText().trim();
		String SEDU_LEVEL = jTFSEDU_LEVEL.getText().trim();
		String SADDRESS = jTFSADDRESS.getText().trim();
		String STEL = jTFSTEL.getText().trim();

		// ������������
		String sql = "update person set  authority = '";

		sql = sql + quanxian + "', DEPARTMENT = '";

		
		
		sql = sql + DEPARTMENT + "', JOB = '";
		sql = sql + JOB + "', EDU_LEVEL = '";
		sql = sql + SEDU_LEVEL + "', ADDRESS = '";
		sql = sql + SEDU_LEVEL + "', TEL = '";
		sql = sql + STEL + "'";
		sql = sql + " WHERE id = " + id + ";";
		System.out.println("�������Ϊ:" + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
		jTFid.setText("");
//		jTFpassword.setText("");
		jTFDEPARTMENT.setText("");
		jTFJOB.setText("");
		jTFSEDU_LEVEL.setText("");
		jTFSADDRESS.setText("");
		jTFSTEL.setText("");
	}
	
	
	public void mimachongzhi()
	{
		String id = jTFid.getText().trim();

		// ������������
		String sql = "update person set password = '666666'";
		System.out.println("�������Ϊ:" + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
//		jTFpassword.setText("");
		jTFDEPARTMENT.setText("");
		jTFJOB.setText("");
		jTFSEDU_LEVEL.setText("");
		jTFSADDRESS.setText("");
		jTFSTEL.setText("");
	}
	
	public String jCBSelectQueryFieldTransfer(String InputStr)
	{
		String outputStr = "";
		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);
		
		if(InputStr.equals("ѧ��")){
			outputStr = "sNo";
		}else if(InputStr.equals("����")){
			outputStr = "sName";
		}else if(InputStr.equals("�Ա�")){
			outputStr = "sSex";
		}else if(InputStr.equals("����")){
			outputStr = "sAge";
		}else if(InputStr.equals("רҵ")){
			outputStr = "sSpecialty";
		}else if(InputStr.equals("סַ")){
			outputStr = "sAddress";
		}
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
		return outputStr;
	}
}

