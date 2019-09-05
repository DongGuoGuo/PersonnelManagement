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

public class DatabaseCourseDesign extends JFrame implements ActionListener {
	// �������
	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��
	JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�
	JLabel jLEqual = null;//=
	JLabel jLSNo = null;//ѧ��
	JLabel jLSName = null;//����
	JLabel jLSSex = null;//�Ա�
	JLabel jLSAge = null;//����
	JLabel jLSSpecialty = null;//רҵ
	JLabel jLSAddress = null;//סַ

	JTextField jTFQueryField = null;//��ѯ�ֶ�
	JTextField jTFSNo = null;//ѧ��
	JTextField jTFSName = null;//����
	JTextField jTFSSex = null;//�Ա�
	JTextField jTFSAge = null;//����
	JTextField jTFSSpecialty = null;//רҵ
	JTextField jTFSAddress = null;//סַ
	
	JButton jBQuery = null;//��ѯ
	JButton jBQueryAll = null;//��ѯ���м�¼
	JButton jBInsert = null;//����
	JButton jBUpdate = null;//����
	JButton jBDeleteCurrentRecord = null;//ɾ����ǰ��¼
	JButton jBDeleteAllRecords = null;//ɾ�����м�¼
	
	
	JComboBox<String> jCBSelectQueryField = null;//��ѯ�ֶ�
	JPanel jP1, jP2,jP3,jP4,jP5,jP6 = null;
	JPanel jPTop, jPBottom = null;
	DefaultTableModel studentTableModel = null;
	JTable studentJTable = null;
	JScrollPane studentJScrollPane = null;
	Vector studentVector = null;
	Vector titleVector = null;
	
	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "ѧ��";
	
	// ���캯��
	public DatabaseCourseDesign() {
		// �������	
		jLStudentInfoTable = new JLabel("ѧ����Ϣ��");
		jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");
		jLEqual = new JLabel(" = ");
		jLSNo = new JLabel("ѧ��");
		jLSName = new JLabel("����");
		jLSSex = new JLabel("�Ա�");
		jLSAge = new JLabel("����");
		jLSSpecialty = new JLabel("רҵ");
		jLSAddress = new JLabel("סַ");
		
		jTFQueryField = new JTextField(10);//��ѯ�ֶ�
		jTFSNo = new JTextField(10);//ѧ��
		jTFSName = new JTextField(10);//����
		jTFSSex = new JTextField(10);//�Ա�
		jTFSAge = new JTextField(10);//����
		jTFSSpecialty = new JTextField(10);//רҵ
		jTFSAddress = new JTextField(10);//סַ
		
		jBQuery = new JButton("��ѯ");
		jBQueryAll = new JButton("��ѯ���м�¼");
		jBInsert = new JButton("����");
		jBUpdate = new JButton("����");
		jBDeleteCurrentRecord = new JButton("ɾ����ǰ��¼");
		jBDeleteAllRecords = new JButton("ɾ�����м�¼");
		// ���ü���
		jBQuery.addActionListener(this);
		jBQueryAll.addActionListener(this);
		jBInsert.addActionListener(this);
		jBUpdate.addActionListener(this);
		jBDeleteCurrentRecord.addActionListener(this);
		jBDeleteAllRecords.addActionListener(this);
		
		jCBSelectQueryField = new JComboBox<String>();//��ѯ�ֶ�
		jCBSelectQueryField.addItem("ѧ��");  
		jCBSelectQueryField.addItem("����");  
		jCBSelectQueryField.addItem("�Ա�");
		jCBSelectQueryField.addItem("����");
		jCBSelectQueryField.addItem("רҵ");
		jCBSelectQueryField.addItem("סַ");
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
		titleVector.add("ѧ��");
		titleVector.add("����");
		titleVector.add("�Ա�");
		titleVector.add("����");
		titleVector.add("רҵ");
		titleVector.add("סַ");
		//studentTableModel = new DefaultTableModel(tableTitle, 15);
		studentJTable = new JTable(studentVector, titleVector);
		studentJTable.setPreferredScrollableViewportSize(new Dimension(450,160));
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

				jTFSNo.setText((String) v.get(0));// ѧ��
				jTFSName.setText((String) v.get(1));// ����
				jTFSSex.setText((String) v.get(2));// �Ա�
				jTFSAge.setText(Integer.toString((int) v.get(3)));// ����
				jTFSSpecialty.setText((String) v.get(4));// רҵ
				jTFSAddress.setText((String) v.get(5));// סַ
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
		
		jP3.add(jLSelectQueryField);
		jP3.add(jCBSelectQueryField);
		jP3.add(jLEqual);
		jP3.add(jTFQueryField);
		jP3.add(jBQuery);
		jP3.add(jBQueryAll);
		jP3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP3.setPreferredSize(new Dimension(20,20));
		
		jP4.add(jLSNo);
		jP4.add(jTFSNo);
		jP4.add(jLSName);
		jP4.add(jTFSName);
		jP4.add(jLSSex);
		jP4.add(jTFSSex);
		jP4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP4.setPreferredSize(new Dimension(20,20));
	
		jP5.add(jLSAge);
		jP5.add(jTFSAge);
		jP5.add(jLSSpecialty);
		jP5.add(jTFSSpecialty);
		jP5.add(jLSAddress);
		jP5.add(jTFSAddress);
		jP5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP5.setPreferredSize(new Dimension(20,20));
		
		jP6.add(jBInsert);
		jP6.add(jBUpdate);
		jP6.add(jBDeleteCurrentRecord);
		jP6.add(jBDeleteAllRecords);
		jP6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jP6.setPreferredSize(new Dimension(20,20));
		
		jPTop.add(jP1);
		jPTop.add(jP2);
		
		jPBottom.setLayout(new GridLayout(4, 1));
		jPBottom.add(jP3);
		jPBottom.add(jP4);
		jPBottom.add(jP5);
		jPBottom.add(jP6);
		
		this.add("North", jPTop);
		this.add("South", jPBottom);
	
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("ѧ������ϵͳ");
		this.setSize(500, 500);
		this.setLocation(150, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		dbProcess = new DbProcess();
	}

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
		}else if(e.getActionCommand().equals("����")
				&& !jTFSNo.getText().isEmpty()
				&& !jTFSName.getText().isEmpty()
				&& !jTFSSex.getText().isEmpty()
				&& !jTFSAge.getText().isEmpty()
				&& !jTFSSpecialty.getText().isEmpty()
				&& !jTFSAddress.getText().isEmpty()){
			System.out.println("actionPerformed(). ����");
			insertProcess();
		}else if(e.getActionCommand().equals("����")
				&& !jTFSNo.getText().isEmpty()
				&& !jTFSName.getText().isEmpty()
				&& !jTFSSex.getText().isEmpty()
				&& !jTFSAge.getText().isEmpty()
				&& !jTFSSpecialty.getText().isEmpty()
				&& !jTFSAddress.getText().isEmpty()){
			System.out.println("actionPerformed(). ����");
			updateProcess();
		}else if(e.getActionCommand().equals("ɾ����ǰ��¼")){
			System.out.println("actionPerformed(). ɾ����ǰ��¼");
			deleteCurrentRecordProcess();
		}else if(e.getActionCommand().equals("ɾ�����м�¼")){
			System.out.println("actionPerformed(). ɾ�����м�¼");
			deleteAllRecordsProcess();
		}
	}
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
		DatabaseCourseDesign getcon = new  DatabaseCourseDesign();
    }
	
	public void queryProcess(String sQueryField)
	{
		try{
			// ������ѯ����
			String sql = "select * from student where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
		
			if(queryFieldStr.equals("sAge")){//int sAge.
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
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("sNo"));
				v.add(rs.getString("sName"));
				v.add(rs.getString("sSex"));
				v.add(Integer.valueOf(rs.getInt("sAge")));
				v.add(rs.getString("sSpecialty"));
				v.add(rs.getString("sAddress"));
				studentVector.add(v);
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
	
	public void queryAllProcess()
	{
		try{
			// ������ѯ����
			String sql = "select * from student;";
			System.out.println("queryAllProcess(). sql = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			studentVector.clear();
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("sNo"));
				v.add(rs.getString("sName"));
				v.add(rs.getString("sSex"));
				v.add(Integer.valueOf(rs.getInt("sAge")));
				v.add(rs.getString("sSpecialty"));
				v.add(rs.getString("sAddress"));
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
	
	public void insertProcess()
	{
		String sNo = jTFSNo.getText().trim();
		String sName = jTFSName.getText().trim();
		String sSex = jTFSSex.getText().trim();
		String sAge = jTFSAge.getText().trim();
		String sSpecialty = jTFSSpecialty.getText().trim();
		String sAddress = jTFSAddress.getText().trim();
		
		// ������������
		String sql = "insert into student values('";
		sql = sql + sNo + "','";
		sql = sql + sName + "','";
		sql = sql + sSex + "',";
		sql = sql + sAge + ",'";
		sql = sql + sSpecialty + "','";
		sql = sql + sAddress + "');";

		System.out.println("insertProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("insertProcess(). insert database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}

	public void updateProcess()
	{
		String sNo = jTFSNo.getText().trim();
		String sName = jTFSName.getText().trim();
		String sSex = jTFSSex.getText().trim();
		String sAge = jTFSAge.getText().trim();
		String sSpecialty = jTFSSpecialty.getText().trim();
		String sAddress = jTFSAddress.getText().trim();
		
		// ������������
		String sql = "update student set sName = '";
		sql = sql + sName + "', sSex = '";
		sql = sql + sSex + "', sAge = ";
		sql = sql + sAge + ", sSpecialty = '";
		sql = sql + sSpecialty + "', sAddress = '";
		sql = sql + sAddress + "'";
		sql = sql + " WHERE sNo = '" + sNo + "';";
		System.out.println("updateProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
	}

	public void deleteCurrentRecordProcess()
	{
		String sNo = jTFSNo.getText().trim();
		
		// ����ɾ������
		String sql = "delete from student where sNo = '" + sNo + "';";
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
		queryAllProcess();
	}

	public void deleteAllRecordsProcess()
	{
		// ����ɾ������
		String sql = "delete from student;";
		System.out.println("deleteAllRecordsProcess(). sql = " + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("deleteAllRecordsProcess(). delete database failed.");
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
		queryAllProcess();
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
