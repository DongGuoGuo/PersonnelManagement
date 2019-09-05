package mycom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MianBan_C_GeRenXinXiXIuGai extends JFrame implements ActionListener{
	private static DbProcess dbProcess;
	String id=null;

	JLabel jLSgerenxinxi = null;
	JLabel jLSgerenxinxi1 = null;
	
	
	JLabel jLSid = null;//Ա����1
	JLabel jLSDEPARTMENT = null;//���ڲ���
	JLabel JLSJOB = null;//ְ��
	JLabel jLSEDU_LEVEL = null;//ѧ��
	JLabel jLSADDRESS = null;//סַ
	JLabel jLSTEL = null;//�绰
	JLabel jLSname =null;//����
	JLabel jLSsex =null;//�Ա�
	JLabel jLSauthority =null;//Ȩ��
	

	JTextField jLSid1 = null;//Ա����1
	JTextField jLSname1 =null;//����
	JTextField jLSsex1 =null;//�Ա�
	JTextField jLSauthority1 =null;//Ȩ��
	JTextField jLSDEPARTMENT1 = null;//���ڲ���
	JTextField JLSJOB1 = null;//ְ��
	JTextField jLSEDU_LEVEL1 = null;//ѧ��
	JTextField jLSADDRESS1 = null;//סַ
	JTextField jLSTEL1 = null;//�绰
	
	JButton Mimaxiugai = null;//��ѯ
	JButton jBUpdate = null;//����
	
	JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10,jP11,jP12,jP13,jP14,jP15 = null;
	public MianBan_C_GeRenXinXiXIuGai(String id){
		this.id=id;
		dbProcess = new DbProcess();
		//��ʾ������Ϣ
		System.out.println("id��ֵΪ"+id);
		queryProcess(id);
		jLSgerenxinxi =new JLabel("������Ϣ�޸�",JLabel.CENTER);
		
		jLSid=new JLabel("Ա����� :   ",JLabel.RIGHT);
		jLSname =new JLabel("��         �� :   ",JLabel.RIGHT);
		jLSsex =new JLabel("��         �� :   ",JLabel.RIGHT);
		jLSauthority =new JLabel("Ȩ         �� :   ",JLabel.RIGHT);
		jLSDEPARTMENT =new JLabel("���ڲ��� :   ",JLabel.RIGHT);
		JLSJOB = new JLabel("ְ         �� :   ",JLabel.RIGHT);
		jLSEDU_LEVEL = new JLabel("ѧ         �� :   ",JLabel.RIGHT);
		jLSADDRESS = new JLabel("ס         ַ :   ",JLabel.RIGHT);
		jLSTEL =new JLabel("��         �� :   ",JLabel.RIGHT);
		
		
		jBUpdate = new JButton("����");
		Mimaxiugai = new JButton("�޸�����");

		// ���ü���
		jBUpdate.addActionListener(this);
		Mimaxiugai.addActionListener(this);
		
		openDialog();
		
	}
	//��ѯ��Ա�����޸ĵ���Ϣ
		public void queryProcess(String sQueryField)
		{
			jLSid1 = new JTextField(15);
			jLSname1 =new JTextField(15);
			jLSsex1=new JTextField(15);
			jLSauthority1 =new JTextField(15);
			jLSDEPARTMENT1 =new JTextField(15);
			JLSJOB1 = new JTextField(15);
			jLSEDU_LEVEL1 = new JTextField(15);
			jLSADDRESS1 = new JTextField(15);
			jLSTEL1 =new JTextField(15);
			try{
				// ������ѯ����
				String sql = "select * from person where id = ";
				sql = sql + sQueryField + ";";
				System.out.println("��ѯ����Ϊ :" + sql);
				dbProcess.connect();
				ResultSet rs = dbProcess.executeQuery(sql);

				if(rs.next()){
					rs.previous(); 
					while(rs.next()){
						jLSid1.setText(rs.getString("id"));
						jLSname1.setText(rs.getString("name"));
						jLSsex1.setText(rs.getString("sex"));
						jLSauthority1.setText(rs.getString("authority"));
						jLSDEPARTMENT1.setText(rs.getString("DEPARTMENT"));
						JLSJOB1.setText(rs.getString("JOB"));
						jLSEDU_LEVEL1.setText(rs.getString("EDU_LEVEL"));
						jLSADDRESS1.setText(rs.getString("ADDRESS"));
						jLSTEL1.setText(rs.getString("TEL"));
						
				}
				}else{
					JOptionPane.showMessageDialog(null,"��Ա��������","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
				
				dbProcess.disconnect();
			}catch(SQLException sqle){
				System.out.println("sqle = " + sqle);
				JOptionPane.showMessageDialog(null,
					"���ݲ�������1","����",JOptionPane.ERROR_MESSAGE);
			}catch(Exception e){
				System.out.println("e = " + e);
				JOptionPane.showMessageDialog(null,
					"���ݲ�������22","����",JOptionPane.ERROR_MESSAGE);
			}
		}
	
		
		public void updateProcess(String id)
		{
			String DEPARTMENT = jLSDEPARTMENT1.getText().trim();
			String JOB = JLSJOB1.getText().trim();
			String SEDU_LEVEL = jLSEDU_LEVEL1.getText().trim();
			String SADDRESS = jLSADDRESS1.getText().trim();
			String STEL = jLSTEL1.getText().trim();

			// ������������
			String sql = "update person set DEPARTMENT = '";

			sql = sql + DEPARTMENT + "', JOB = '";
			sql = sql + JOB + "', EDU_LEVEL = '";
			sql = sql + SEDU_LEVEL + "', ADDRESS = '";
			sql = sql + SADDRESS + "', TEL = '";
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
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MianBan_C_GeRenXinXiXIuGai("5");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("����")
				&& !jLSDEPARTMENT1.getText().isEmpty()
				&& !JLSJOB1.getText().isEmpty()
				&& !jLSEDU_LEVEL1.getText().isEmpty()
				&& !jLSADDRESS1.getText().isEmpty()
				&& !jLSTEL1.getText().isEmpty()){
			System.out.println("actionPerformed(). ����");
			updateProcess(id);
			JOptionPane.showMessageDialog(null,
					"��Ϣ�޸ĳɹ�","��ʾ",JOptionPane.WARNING_MESSAGE);
			this.dispose();
		}
	}
	
	private void openDialog() {
        final JDialog dialog = new JDialog(this, true);
        dialog.setSize(700, 600);
        dialog.setLocation(getX() + 450, getY() + 250);
        dialog.setLayout(new GridLayout(4, 2));
        
        
        jP1=new JPanel();
		jP2=new JPanel();
		jP3=new JPanel();
		jP4=new JPanel();
		jP5=new JPanel();
		jP6=new JPanel();
		jP7=new JPanel();
		jP8=new JPanel();
		jP9=new JPanel();
		jP10=new JPanel();
		jP11=new JPanel();
		jP12=new JPanel();
		jP13=new JPanel();
		

		jP12.add(jLSgerenxinxi);
		
		jP5.add(jLSDEPARTMENT );
		jP5.add(jLSDEPARTMENT1 );
		
		jP6.add(JLSJOB );
		jP6.add(JLSJOB1 );
		
		jP7.add(jLSEDU_LEVEL );
		jP7.add(jLSEDU_LEVEL1 );
		
		jP8.add(jLSADDRESS );
		jP8.add(jLSADDRESS1 );
		
		jP9.add(jLSTEL );
		jP9.add(jLSTEL1 );
		
		jP11.add(jBUpdate);
		
		


		dialog.setLayout(new GridLayout(8,1));
		dialog.add(jP12);

		dialog.add(jP5);
		dialog.add(jP6);
		dialog.add(jP7);
		dialog.add(jP8);
		dialog.add(jP9);
		dialog.add(jP11);
        


        
        dialog.setVisible(true);
    }

}
