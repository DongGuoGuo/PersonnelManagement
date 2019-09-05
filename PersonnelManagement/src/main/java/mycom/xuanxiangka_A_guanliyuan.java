package mycom;

import javax.swing.*;

public class xuanxiangka_A_guanliyuan extends JFrame{
	JLabel jbl1;
	
	//����ѡ�
	JTabbedPane jtp;
	JPanel jp1,jp2,jp3,jp4;
	String id=null;
	//�������
	
	JButton jb1,jb2;
	
	public static void main(String[] args) {
		xuanxiangka_A_guanliyuan q=new xuanxiangka_A_guanliyuan("3");
    }
	
	
	
	public xuanxiangka_A_guanliyuan(String id1){
		this.id=id1;
		MianBan_A_yuangongcharu charu=new MianBan_A_yuangongcharu();
		MianBan_A_yuangongchaxun chaxun=new MianBan_A_yuangongchaxun();
		MianBan_A_yuangonggengxin_shanchu gengxin =new MianBan_A_yuangonggengxin_shanchu();
		MIanBan_A_renshibiandongchaxun renshibiandong =new MIanBan_A_renshibiandongchaxun();
		MianBan_A_renshicharu renshicharu =new MianBan_A_renshicharu();
		MianBan_A_GeRenXinXi_XinXiXiuGai_MiMaXiuGai GeRenXinXi = new MianBan_A_GeRenXinXi_XinXiXiuGai_MiMaXiuGai(id);
		//ѡ�����
		jtp=new JTabbedPane();
		
		jtp.add("Ա����Ϣ��ѯ", chaxun);
		jtp.add("Ա�����ϸ�����ɾ��", gengxin);
		jtp.add("Ա������¼��", charu);
		jtp.add("���±䶯��ѯ", renshibiandong);
		jtp.add("�������±䶯", renshicharu);
		jtp.add("�ҵĸ�����Ϣ", GeRenXinXi);
		
		this.add(jtp);
		
		
		this.setSize(700, 600);
		this.setLocation(600, 200);
		this.setVisible(true);
		this.setTitle("���¹���ϵͳ");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
