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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MianBan_B_GeRenXinXi_XinXiXiuGai_MiMaXiuGai extends JPanel implements ActionListener{
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
	
	
	JLabel jLSname1 =null;//����
	JLabel jLSsex1 =null;//�Ա�
	JLabel jLSauthority1 =null;//Ȩ��
	JLabel jLSid1 = null;//Ա����1
	JLabel jLSDEPARTMENT1 = null;//���ڲ���
	JLabel JLSJOB1 = null;//ְ��
	JLabel jLSEDU_LEVEL1 = null;//ѧ��
	JLabel jLSADDRESS1 = null;//סַ
	JLabel jLSTEL1 = null;//�绰
	
	JButton Mimaxiugai = null;//��ѯ
	JButton jBUpdate = null;//����
	JButton QieHuanYongHu = null;//�л��û�
	
	JPanel jP1, jP2,jP3,jP4,jP5,jP6,jP7,jP8,jP9,jP10,jP11,jP12,jP13,jP14,jP15 = null;
	public MianBan_B_GeRenXinXi_XinXiXiuGai_MiMaXiuGai(String id1){
		this.id=id1;
		System.out.println("id��ֵΪ"+id);
		
		jLSid1=new JLabel();

		jLSname1 =new JLabel();
		jLSsex1=new JLabel();
		jLSauthority1 =new JLabel();
		
		jLSDEPARTMENT1 =new JLabel();
		JLSJOB1 = new JLabel();
		jLSEDU_LEVEL1 = new JLabel();
		jLSADDRESS1 = new JLabel();
		jLSTEL1 =new JLabel();
		
		
		dbProcess = new DbProcess();
		//��ʾ������Ϣ
		queryProcess(id);
		jLSgerenxinxi =new JLabel("������Ϣ",JLabel.CENTER);
		
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
		QieHuanYongHu=new JButton("�л��û�");

		// ���ü���
		jBUpdate.addActionListener(this);
		Mimaxiugai.addActionListener(this);
		QieHuanYongHu.addActionListener(this);
		
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
		jP14=new JPanel();
		jP15=new JPanel();
		
		jP1.add(jLSgerenxinxi);

		jP2.add(jLSid);
		jP2.add(jLSid1);
		jP3.add(jLSname);
		jP3.add(jLSname1);
		jP4.add(jLSsex);
		jP4.add(jLSsex1);
		jP5.add(jLSauthority);
		jP5.add(jLSauthority1);
		jP6.add(jLSDEPARTMENT);
		jP6.add(jLSDEPARTMENT1);
		jP7.add(JLSJOB);
		jP7.add(JLSJOB1);
		jP8.add(jLSEDU_LEVEL);
		jP8.add(jLSEDU_LEVEL1);
		jP9.add(jLSADDRESS);
		jP9.add(jLSADDRESS1);
		jP10.add(jLSTEL);
		jP10.add(jLSTEL1);
		jP11.add(jBUpdate);
		jP11.add(Mimaxiugai);
		jP11.add(QieHuanYongHu);


		this.setLayout(new GridLayout(11,1));
		this.add(jP1);
		this.add(jP2);
		this.add(jP3);
		this.add(jP4);
		this.add(jP5);
		this.add(jP6);
		this.add(jP7);
		this.add(jP8);
		this.add(jP9);
		this.add(jP10);
		this.add(jP11);
		
		this.setSize(700, 600);
		this.setLocation(300, 200);
		this.setVisible(true);
		
	}
	//��ѯ��Ա�����޸ĵ���Ϣ
		public void queryProcess(String sQueryField)
		{
			try{
				// ������ѯ����
				String sql = "select * from person where id = ";
				sql = sql + sQueryField + ";";
				System.out.println("��ѯ����Ϊ :" + sql);
				dbProcess.connect();
				System.out.println("1");
				ResultSet rs = dbProcess.executeQuery(sql);

				System.out.println("2");
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MianBan_B_GeRenXinXi_XinXiXiuGai_MiMaXiuGai("5");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("����")){
			new MianBan_C_GeRenXinXiXIuGai(id);
			System.out.println("fdsfdgvgdfg");
			queryProcess(id);
		}else if(arg0.getActionCommand().equals("�޸�����")) {
			new MianBan_C_XiuGaiMiMa(id);
		}
	}

}
