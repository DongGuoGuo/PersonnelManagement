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

class MianBan_A_yuangongchaxun extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		MianBan_A_yuangongchaxun q=new MianBan_A_yuangongchaxun();
    }
	
	// �������
	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��
	JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�
	JLabel jLEqual = null;//=

	JLabel jLSNo = null;//Ա����
	JLabel jLSMima = null;//����
	JLabel jLSQuanxian = null;//Ȩ��
	JLabel jLSName = null;//����
	JLabel jLSSex = null;//�Ա�
	JLabel jLSDepartment = null;//���ڲ���
	JLabel JLSZhiwu = null;//ְ��
	JLabel jLSSpecialty = null;//ѧ��
	JLabel jLSAddress = null;//סַ
	JLabel jLSTel = null;//�绰

	JTextField jTFQueryField = null;//��ѯ�ֶ�
	
	JTextField jTFSNo = null;//Ա����
	JTextField jTFMima = null;//����
	JTextField jTFQuanxian = null;//Ȩ��
	JTextField jTFSName = null;//����
	JTextField jTFSSex = null;//�Ա�
	JTextField jTFDepartment = null;//���ڲ���
	JTextField jTFSZhiwu = null;//ְ��
	JTextField jTFSSpecialty = null;//ѧ��
	JTextField jTFSAddress = null;//סַ
	JTextField jTFSTel = null;//�绰
	
	JButton jBQuery = null;//��ѯ
	JButton jBQueryAll = null;//��ѯ���м�¼
	
	JComboBox<String> jCBSelectQueryField = null;//��ѯ�ֶ�
	JPanel jP1, jP2,jP3,jP4,jP5,jP6 = null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel studentTableModel = null;
	JTable studentJTable = null;
	JScrollPane studentJScrollPane = null;
	Vector studentVector = null;
	Vector titleVector = null;
	
	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "Ա����";
	
	// ���캯��
	public MianBan_A_yuangongchaxun() {
		// �������	
		jLStudentInfoTable = new JLabel("Ա����ϸ��Ϣ��ѯ ",JLabel.CENTER);
		jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");
		jLEqual = new JLabel(" = ");
		
		jLSNo = new JLabel("Ա����");
		jLSMima =new JLabel("����");
		jLSQuanxian =new JLabel("Ȩ��");
		jLSName = new JLabel("����");
		jLSSex = new JLabel("�Ա�");
		jLSDepartment =new JLabel("���ڲ���");
		JLSZhiwu = new JLabel("ְ��");
		jLSSpecialty = new JLabel("ѧ��");
		jLSAddress = new JLabel("סַ");
		jLSTel =new JLabel("�绰");
		
		
		jTFQueryField = new JTextField(20);//��ѯ�ֶ�
		
		jTFSNo = new JTextField(15);//Ա����
		jTFMima = new JTextField(15);//����
		jTFQuanxian = new JTextField(15);//Ȩ��
		jTFSName = new JTextField(15);//����
		jTFSSex = new JTextField(15);//�Ա�
		jTFDepartment = new JTextField(15);//���ڲ���
		jTFSZhiwu = new JTextField(15);//ְ��
		jTFSSpecialty = new JTextField(15);//ѧ��
		jTFSAddress = new JTextField(15);//סַ
		jTFSTel = new JTextField(15);//�绰
		
		
		jBQuery = new JButton("��ѯ");
		jBQueryAll = new JButton("��ѯ���м�¼");
		// ���ü���
		jBQuery.addActionListener(this);
		jBQueryAll.addActionListener(this);
		
		jCBSelectQueryField = new JComboBox<String>();//��ѯ�ֶ�
		jCBSelectQueryField.addItem("Ա����"); 
		jCBSelectQueryField.addItem("����");
		jCBSelectQueryField.addItem("�û�Ȩ��");
		jCBSelectQueryField.addItem("����");  
		jCBSelectQueryField.addItem("�Ա�");
		jCBSelectQueryField.addItem("���ڲ���");
		jCBSelectQueryField.addItem("ְ��");
		jCBSelectQueryField.addItem("ѧ��");
		jCBSelectQueryField.addItem("סַ");
		jCBSelectQueryField.addItem("�绰");
		jCBSelectQueryField.addItemListener(new ItemListener() {//�������¼�����  
            public void itemStateChanged(ItemEvent event) {  
                switch (event.getStateChange()) {  
                case ItemEvent.SELECTED:  
                	SelectQueryFieldStr = (String) event.getItem();  
                    System.out.println("ѡ�У�" + SelectQueryFieldStr);  
                    break;  
                case ItemEvent.DESELECTED:  
                    System.out.println("ȡ��ѡ�У�" + event.getItem());  
                    break;  
                }  
            }  
        });
		

		studentVector = new Vector();
		titleVector = new Vector();
		
		// �����ͷ
		titleVector.add("Ա����");
		titleVector.add("����");
		titleVector.add("�û�Ȩ��");
		titleVector.add("����");
		titleVector.add("�Ա�");
		titleVector.add("���ڲ���");
		titleVector.add("ְ��");
		titleVector.add("ѧ��");
		titleVector.add("סַ");
		titleVector.add("�绰");
		studentJTable = new JTable(studentVector, titleVector);
		studentJTable.setPreferredScrollableViewportSize(new Dimension(650,360));
		studentJScrollPane = new JScrollPane(studentJTable);
		//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
		studentJScrollPane.setHorizontalScrollBarPolicy(                
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentJScrollPane.setVerticalScrollBarPolicy(                
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//Ϊ�����Ӽ����� 
		studentJTable.addMouseListener(new MouseAdapter()
		{ 
			public void mouseClicked(MouseEvent e) 
			{ 
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // �����λ��
				System.out.println("mouseClicked(). row = " + row);
				Vector v = new Vector();
				v = (Vector) studentVector.get(row);
				//��ȡѡ�б���е�����
				jTFSNo.setText(Integer.toString((int) v.get(0)));// Ա����
				jTFMima.setText((String) v.get(1));// ����
				jTFSSpecialty.setText((String) v.get(2));// Ȩ��
				jTFQuanxian.setText((String) v.get(3));// �Ա�
				jTFSName.setText((String) v.get(4));// ����
				jTFDepartment.setText((String) v.get(5));// ���ڲ���
				jTFSZhiwu.setText((String) v.get(6));// ְ��
				jTFSSpecialty.setText((String) v.get(7));// ѧ��
				jTFSAddress.setText((String) v.get(8));// סַ
				jTFSTel.setText((String) v.get(9));// �绰
			}
		});


		jP1 = new JPanel();
		jP2 = new JPanel();
		jP3 = new JPanel();
		jP4 = new JPanel();
		jP5 = new JPanel();
		jP6 = new JPanel();
		jPTop = new JPanel();
		jPBottom = new JPanel();
		
		
		
		jP1.add(jLStudentInfoTable,BorderLayout.SOUTH);
		jP2.add(studentJScrollPane);
	
		
		jP3.setLayout(new FlowLayout());
		jP3.add(jLSelectQueryField);
		jP3.add(jCBSelectQueryField);
		jP3.add(jLEqual);
		jP3.add(jTFQueryField);
		jP3.add(jBQuery);
		jP3.add(jBQueryAll);
		
		this.add(jLStudentInfoTable,BorderLayout.NORTH);
		this.add(jP2,BorderLayout.CENTER);
		this.add(jP3,BorderLayout.SOUTH);
		dbProcess = new DbProcess();
	}

//�����¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��ѯ")  
			&& !jTFQueryField.getText().isEmpty()){
			System.out.println("actionPerformed(). ��ѯ");
			String sQueryField = jTFQueryField.getText().trim();
			queryProcess(sQueryField);
			jTFQueryField.setText("");
		}else if(e.getActionCommand().equals("��ѯ���м�¼")) {
			System.out.println("actionPerformed(). ��ѯ���м�¼");
			queryAllProcess();
		}
	}
	
	//��ѯ
	public void queryProcess(String sQueryField)
	{
		try{
			// ������ѯ����
			
			
			
			
			String sql = "select * from PERSON where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
		
			if(queryFieldStr.equals("id")){//int id.
				sql = sql + queryFieldStr;
				sql = sql + " = " + sQueryField;
			}else{
				sql = sql + queryFieldStr;
				sql = sql + " = ";
				sql = sql + "'" + sQueryField + "';";
			}
			System.out.println("queryProcess(). sql = " + sql);
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			studentVector.clear();
			
			if(rs.next()){
				rs.previous(); 
				while(rs.next()){
					Vector v = new Vector();
					v.add(Integer.valueOf(rs.getInt("id")));
					v.add(rs.getString("password"));
					v.add(rs.getString("authority"));
					v.add(rs.getString("name"));
					v.add(rs.getString("sex"));
					v.add(rs.getString("DEPARTMENT"));
					v.add(rs.getString("JOB"));
					v.add(rs.getString("EDU_LEVEL"));
					v.add(rs.getString("ADDRESS"));
					v.add(Integer.valueOf(rs.getInt("TEL")));
					studentVector.add(v);
				}
			}else{
				JOptionPane.showMessageDialog(null,"û�и����ݣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
			
			studentJTable.updateUI();

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
	
	
	//��ѯ����
	public void queryAllProcess()
	{
		try{
			// ������ѯ����
			String sql = "select * from PERSON;";
			System.out.println("��ѯ���Ϊ: = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			studentVector.clear();
			while(rs.next()){
				Vector v = new Vector();
				
				v.add(Integer.valueOf(rs.getInt("id")));
				v.add(rs.getString("password"));
				v.add(rs.getString("authority"));
				v.add(rs.getString("name"));
				v.add(rs.getString("sex"));
				v.add(rs.getString("DEPARTMENT"));
				v.add(rs.getString("JOB"));
				v.add(rs.getString("EDU_LEVEL"));
				v.add(rs.getString("ADDRESS"));
				v.add(rs.getString("TEL"));
				studentVector.add(v);
			}
			
			studentJTable.updateUI();

			dbProcess.disconnect();
		}catch(SQLException sqle){
			System.out.println("sqle = " + sqle);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
	}

	public String jCBSelectQueryFieldTransfer(String InputStr)
	{
		String outputStr = "";
		System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);
		
		if(InputStr.equals("Ա����")){
			outputStr = "id";
		}else if(InputStr.equals("����")){
			outputStr = "password";
		}else if(InputStr.equals("Ȩ��")){
			outputStr = "authority";
		}else if(InputStr.equals("����")){
			outputStr = "name";
		}else if(InputStr.equals("�Ա�")){
			outputStr = "sex";
		}else if(InputStr.equals("���ڲ���")){
			outputStr = "DEPARTMENT";
		}else if(InputStr.equals("ְ��")){
			outputStr = "JOB";
		}else if(InputStr.equals("ѧ��")){
			outputStr = "EDU_LEVEL";
		}else if(InputStr.equals("סַ")){
			outputStr = "ADDRESS";
		}else if(InputStr.equals("�绰")){
			outputStr = "TEL";
		}
		
		
		
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
		return outputStr;
	}
}

