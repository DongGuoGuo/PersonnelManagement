package mycom;
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

public class MianBan_C_XiuGaiMiMa  extends JFrame{

	private static DbProcess dbProcess;
	public static void main(String[] args) {
		MianBan_C_XiuGaiMiMa q=new MianBan_C_XiuGaiMiMa("5");
    }
	
	
	public MianBan_C_XiuGaiMiMa(String id){
		openDialog(id);
	}
	
	//�ж�ԭ�����Ƿ���ȷ
	public boolean PANduan(String id,String password,String password0,String password1)
		{
			try{
				String sql = "select * from PERSON where id = ";	
				sql = sql + id + " and " +    " password = '" + password + "';";
				System.out.println(sql);
				dbProcess.connect();
				ResultSet rs = dbProcess.executeQuery(sql);
				if(rs.next()){
					//�����޸ĺ���
					System.out.println("ԭ������ȷ");
					if(password0.equals(password1)){
						XiuGaiMiMa(id,password1);
						dbProcess.disconnect();
						return true;
					}else{
						JOptionPane.showMessageDialog(null,"�������벻һ�£�","��ʾ",JOptionPane.ERROR_MESSAGE);
						dbProcess.disconnect();
						return false;
					}
					
				}else{
					JOptionPane.showMessageDialog(null,"ԭ�������","��ʾ",JOptionPane.ERROR_MESSAGE);
					
					dbProcess.disconnect();
					return false;
				}
			}catch(SQLException sqle){
				System.out.println("sqle = " + sqle);
				JOptionPane.showMessageDialog(null,
					"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}catch(Exception e){
				System.out.println("e = " + e);
				JOptionPane.showMessageDialog(null,
					"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}
	
	public void XiuGaiMiMa(String id,String password)
	{
		String sql = "update person set password = '";
		sql = sql + password + "' ";
		sql = sql + " WHERE id = " + id + ";";
		System.out.println("�������Ϊ:" + sql);
		try{
			if (dbProcess.executeUpdate(sql) < 1) {
				System.out.println("updateProcess(). update database failed.");
			}else{
				System.out.println("�ɹ��޸�����");
				JOptionPane.showMessageDialog(null,
						"�ɹ��޸�����","��ʾ",JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception e){
			System.out.println("e = " + e);
			JOptionPane.showMessageDialog(null,
				"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void openDialog(final String id) {
        final JDialog dialog = new JDialog(this, true);
        dialog.setSize(400, 200);
        dialog.setLocation(getX() + 600, getY() + 350);
        dialog.setLayout(new GridLayout(4, 2));
        final JLabel jLSmima =new JLabel("�����ʼ����",JLabel.CENTER);
        final JTextField mima = new JTextField(10);
        final JLabel jLSnewmima =new JLabel("������������",JLabel.CENTER);
        final JTextField newmima = new JTextField(10);
        final JLabel jLSrenewmima =new JLabel("�ٴ���������",JLabel.CENTER);
        final JTextField renewmima = new JTextField(10);
        JPanel jP1=new JPanel();
        
        JButton save = new JButton("�޸�");
        JButton cancel = new JButton("ȡ��");
        jP1.add(save);
        jP1.add(cancel);  
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(!mima.getText().isEmpty()&&!newmima.getText().isEmpty()&&!renewmima.getText().isEmpty()){
            		if(PANduan(id,mima.getText(),newmima.getText(),renewmima.getText())){
            			dialog.dispose();
            		}
            	}else{
            		JOptionPane.showMessageDialog(null,
            				"����������","��ʾ",JOptionPane.WARNING_MESSAGE);
            	}
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        dialog.add(jLSmima);
        dialog.add(mima);
        dialog.add(jLSnewmima);
        dialog.add(newmima);
        dialog.add(jLSrenewmima);
        dialog.add(renewmima);
        dialog.add(save);
        dialog.add(cancel);
        
        dialog.setVisible(true);
    }

}


