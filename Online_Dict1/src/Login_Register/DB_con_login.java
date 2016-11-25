package Login_Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DB_con_login extends DB_connect implements ActionListener{
	
	register re;//����ע��

	JTextField textname,textpassword;
	JButton loginButton,registerButton;
	
	public void setTextname(JTextField textname) {
		this.textname = textname;
	}

	public void setTextpassword(JTextField textpassword) {
		this.textpassword = textpassword;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource() == loginButton){
			if(textname.getText()==null)			//�ж��û������Ƿ�Ϊ�գ�
				JOptionPane.showMessageDialog(null, "����������");
			else if(textpassword.getText()==null)
				JOptionPane.showMessageDialog(null, "����������");
			else{
				String name = textname.getText();
				String password = textpassword.getText();
				try {
					connection(); 		//����conn_db�࣬�������ݿ⣻
					boolean com = compareWithDB(name,password);
					if(com==true)
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");
					else{
						JOptionPane.showMessageDialog(null, "�û����������벻��ȷ������������");
						textname.setText("");
						textpassword.setText("");
					}
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		//ע��
		else if(e.getSource()==registerButton){
			new JFrame().dispose();//������ǰ����
			re = new register();
		}
			
		
	}
	
	//�ж������Ƿ������ݿ���
	boolean compareWithDB(String name,String password) throws Exception{	
		Connection con = super.con;
		Statement stmt = con.createStatement();
		
		String sql = "select * from userinfo";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){				
			//�û��������Ϣ�����ݿ��е���Ϣ���Ƚϣ��ж������Ƿ���ȷ��
			String username = rs.getString(1);
			String userpassword = rs.getString(2);
			String state=rs.getString(3);
			if(username.equals(name) && userpassword.equals(password))
			{
				state="1";//2��ʾ����
				sql="update userinfo set userstate='"+state+"' where username='"+name+"'";
				int judge=stmt.executeUpdate(sql);
				if(judge>0)
					System.out.println("״̬�޸ĳɹ�");
				else
					System.out.println("״̬�޸�ʧ��");
				return true;
			}
		}

		return false;
		
	}

}
