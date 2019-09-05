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

class MIanBan_B_renshibiandongchaxun extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	String id=null;
	public static void main(String[] args) {
		MIanBan_B_renshibiandongchaxun q=new MIanBan_B_renshibiandongchaxun("5");
    }
	
	// �������
	JLabel jLStudentInfoTable = null;//ѧ����Ϣ��
	JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�
	JLabel jLEqual = null;//=

	JLabel jLSNo = null;//��¼���
//	JLabel jLSPERSON  = null;//Ա�����
	JLabel jLSSCHANGE  = null;//�������
	JLabel DESCRIPTION = null;//��ϸ��¼

	JTextField jTFQueryField = null;//��ѯ�ֶ�
	
	JTextField jTFSNo = null;//��¼���
	JTextField jTFMima = null;//Ա�����
	JTextField jTFQuanxian = null;//�������
	JTextField jTFSName = null;//��ϸ��¼
	
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
	String SelectQueryFieldStr = "��¼���";
	
	// ���캯��
	public MIanBan_B_renshibiandongchaxun(String id1) {
		this.id=id1;
		// �������	
		jLStudentInfoTable = new JLabel("���±䶯��ѯ ",JLabel.CENTER);
		jLSelectQueryField = new JLabel("���������±䶯����");
		jLEqual = new JLabel(" = ");
		
		jLSNo = new JLabel("��¼���");
//		jLSPERSON  =new JLabel("Ա�����");
		jLSSCHANGE  =new JLabel("�������");
		DESCRIPTION = new JLabel("��ϸ��¼");
		
		
		jTFQueryField = new JTextField(20);//��ѯ�ֶ�
		
		jTFSNo = new JTextField(15);//��¼���
		jTFMima = new JTextField(15);//Ա�����
		jTFQuanxian = new JTextField(15);//�������
		jTFSName = new JTextField(15);//��ϸ��¼
		
		
		jBQuery = new JButton("��ѯ");
		jBQueryAll = new JButton("��ѯ���м�¼");
		// ���ü���
		jBQuery.addActionListener(this);
		jBQueryAll.addActionListener(this);
		

		studentVector = new Vector();
		titleVector = new Vector();
		
		// �����ͷ
		titleVector.add("��¼���");
//		titleVector.add("Ա�����");
		titleVector.add("�������");
		titleVector.add("��ϸ��¼");
		studentJTable = new JTable(studentVector, titleVector);
		studentJTable.setPreferredScrollableViewportSize(new Dimension(650,400));
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

				jTFSNo.setText(Integer.toString((int) v.get(0)));// ��¼���
//				jTFMima.setText((String) v.get(1));// Ա�����
				jTFQuanxian.setText((String) v.get(1));// �Ա�
				jTFSName.setText((String) v.get(2));// ��ϸ��¼
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
		jP3.add(jTFQueryField);
		jP3.add(jBQuery);
		jP3.add(jBQueryAll);
		jP3.setLayout(new FlowLayout());
		
		jPBottom.add(jP3);
		
		this.add(jLStudentInfoTable,BorderLayout.NORTH);
		this.add(jP2,BorderLayout.CENTER);
		this.add(jPBottom,BorderLayout.SOUTH);
		
		
		this.setSize(700, 600);
		this.setLocation(600, 200);
		this.setVisible(true);
//		this.setTitle("���¹���ϵͳ");
//		this.setResizable(false);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dbProcess = new DbProcess();
	}

//�����¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��ѯ")  
			&& !jTFQueryField.getText().isEmpty()){
			System.out.println("actionPerformed(). ��ѯ");
			String sQueryField = jTFQueryField.getText().trim();
			queryProcess(sQueryField,id);
			jTFQueryField.setText("");
		}else if(e.getActionCommand().equals("��ѯ���м�¼")) {
			System.out.println("actionPerformed(). ��ѯ���м�¼");
			queryAllProcess(id);
		}
	}
	
	//��ѯ
	public void queryProcess(String sQueryField,String yuangongbianhao)
	{
		try{
			// ������ѯ����
			String sql = "select * from PERSONNEL where ";
			String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);
		
			sql = sql + queryFieldStr;
			sql = sql + " = " + sQueryField+ " and person = "+yuangongbianhao+";";
			
			
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
//					v.add(rs.getString("PERSON"));
					v.add(rs.getString("SCHANGE"));
					v.add(rs.getString("DESCRIPTION"));
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
	public void queryAllProcess(String yuangongbianhao)
	{
		try{
			// ������ѯ����
			String sql = "select * from PERSONNEL where PERSON = ";
			sql=sql + yuangongbianhao + ";";
			System.out.println("��ѯ���Ϊ: = " + sql);
	
			dbProcess.connect();
			ResultSet rs = dbProcess.executeQuery(sql);

			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			studentVector.clear();
			while(rs.next()){
				Vector v = new Vector();
				v.add(Integer.valueOf(rs.getInt("id")));
//				v.add(rs.getString("PERSON"));
				v.add(rs.getString("SCHANGE"));
				v.add(rs.getString("DESCRIPTION"));
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
		
		if(InputStr.equals("��¼���")){
			outputStr = "id";
		}else if(InputStr.equals("Ա�����")){
			outputStr = "PERSON";
		}else if(InputStr.equals("�������")){
			outputStr = "SCHANGE";
		}
		
		System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
		return outputStr;
	}
}

