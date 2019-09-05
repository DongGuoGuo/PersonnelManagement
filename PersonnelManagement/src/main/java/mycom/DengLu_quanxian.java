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

class DengLu_quanxian extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	final JTextField jt = new JTextField("",10);
    final JPasswordField jpf = new JPasswordField();
    String authority1=null;  //
    String password1=null;   //�ı���
    String password2=null;//���ݿ�
	public static void main(String[] args) {
		DengLu_quanxian q=new DengLu_quanxian();
    }
	
	
	private static DbProcess dbProcess;
	String SelectQueryFieldStr = "id";
	
	// ���캯��
	public DengLu_quanxian() {
		this.setTitle("���¹���ϵͳ��¼����3");
        //��������
        Container cont = getContentPane();
        //�رվ��Բ���
        this.setLayout(null);
        //�����ؼ�
        JLabel jl1 = new JLabel("�û�");
        JLabel jl2 = new JLabel("����");
        jpf.setEchoChar('*');
        JButton jb1 = new JButton("��½");
        jb1.addActionListener(this);
        jb1.setActionCommand("a");
        
        JButton jb2 = new JButton("����");
        jb2.addActionListener(this);
        jb2.setActionCommand("b");
        
        JButton jb3 = new JButton("��������");
        jb3.addActionListener(this);
        jb3.setActionCommand("c");
         
        //���ؼ�����������
        cont.add(jl1);
        cont.add(jl2);
        cont.add(jt);
        cont.add(jpf);
        cont.add(jb1);
        cont.add(jb2);
        cont.add(jb3);
        //�ؼ��������е�λ�ü���С
        jl1.setBounds(60, 20, 50, 20);
        jl2.setBounds(60, 50, 50, 20);
        jt.setBounds(110,20,200,20);
        jpf.setBounds(110,50,200,20);
        jb1.setBounds(130,90,60,20);
        jb2.setBounds(220,90,60,20);
        jb3.setBounds(110,150,200,30);
		
		
		this.setBounds(500, 360, 400, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
		
		
		dbProcess = new DbProcess();
	}

//�����¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("a")){
			
			if(!jt.getText().isEmpty()&&!jpf.getText().isEmpty()){
				
				String id=jt.getText().trim();
				password1=jpf.getText();
				String sql="select password,authority from person where id = ";
				sql=sql+id;
				sql=sql+";";
				
				try{
					dbProcess.connect();
					ResultSet rs = dbProcess.executeQuery(sql);
					
					if(rs.next()){
						rs.previous(); 
						while(rs.next()){
							password2=rs.getString("password");
							authority1=rs.getString("authority");
						}

						if(PuanDuan()){
							if(authority1.equals("b")){
								//Ա������
								System.out.println("������ȷ������Ա������");
								new xuanxiangka_B_yuangong(id);
								this.dispose();
							}else if(authority1.equals("a")){
								//����Ա����
								System.out.println("������ȷ���������Ա����");
								new xuanxiangka_A_guanliyuan(id);
								this.dispose();
							}else{
								System.out.println("������ȷ");
							}
						}else{
							JOptionPane.showMessageDialog(null,
									"�������\n����������","����",JOptionPane.ERROR_MESSAGE);
						}
						
					}else{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(),"�û���������!\n���������룡", "��ʾ", JOptionPane.WARNING_MESSAGE); 
					}
					dbProcess.disconnect();
				}catch(SQLException sqle){
					System.out.println("sqle = " + sqle);
					JOptionPane.showMessageDialog(null,
						"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
				}catch(Exception e1){
					System.out.println("e = " + e1);
					JOptionPane.showMessageDialog(null,
						"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				if(jt.getText().isEmpty()&&jpf.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,
							"�������û���������","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else if(jt.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,
							"�������û���","��ʾ",JOptionPane.WARNING_MESSAGE);
				}else if(jpf.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,
							"����������","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			}
			
			
			
			
		}else if(e.getActionCommand().equals("b")){
		}else if(e.getActionCommand().equals("c")){
			JOptionPane.showMessageDialog(this,"?????");
		}
		
		
		
	}

	private boolean PuanDuan() {
		if(password1.equals(password2)){
			return true;
		}
		return false;
	}
}

