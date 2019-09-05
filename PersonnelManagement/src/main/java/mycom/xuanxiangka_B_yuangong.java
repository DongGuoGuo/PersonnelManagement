package mycom;


import javax.swing.*;

public class xuanxiangka_B_yuangong extends JFrame{
	JLabel jbl1;
	
	//����ѡ�
	JTabbedPane jtp;
	String id=null;
	//�������
	
	JButton jb1,jb2;
	
	public static void main(String[] args) {
		xuanxiangka_B_yuangong q=new xuanxiangka_B_yuangong("7");
    }
	
	
	
	public xuanxiangka_B_yuangong(String id1){
		this.id=id1;
		MianBan_B_GeRenXinXi_XinXiXiuGai_MiMaXiuGai gerenxinxi=new MianBan_B_GeRenXinXi_XinXiXiuGai_MiMaXiuGai(id);
		MIanBan_B_renshibiandongchaxun renshibiandongchaxun = new MIanBan_B_renshibiandongchaxun(id);
		//ѡ�����
		jtp=new JTabbedPane();

		jtp.add("�ҵĸ�����Ϣ", gerenxinxi);
		jtp.add("�ҵı����¼", renshibiandongchaxun);
		
		this.add(jtp);
		this.setSize(700, 600);
		this.setLocation(600, 200);
		this.setVisible(true);
		this.setTitle("���¹���ϵͳ");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
