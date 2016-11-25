package Login_Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DB_con_register extends DB_connect implements ActionListener{

	JTextField textname,textpassword;
	JButton okButton,resetButton;
	
	public void setTextname(JTextField textname) {
		this.textname = textname;
	}

	public void setTextpassword(JTextField textpassword) {
		this.textpassword = textpassword;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==okButton){
			if(textname.getText()==null)
				JOptionPane.showMessageDialog(null, "�������û���","����Ի���",JOptionPane.WARNING_MESSAGE);
			else if(textpassword.getText()==null)
				JOptionPane.showMessageDialog(null,"����������","����Ի���",JOptionPane.WARNING_MESSAGE);
			else{
				String name = textname.getText();
				String password = textpassword.getText();
				String state="1";//1��ʾ����
				
					try {
						connection();
						saveInDB(name,password,state);
					} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						System.out.println("��������ʧ��");
						e1.printStackTrace();
					}
			}
		}
		
		//����Ϊ��
		else if(e.getSource()==resetButton){
			textname.setText("");
			textpassword.setText("");
		}
			
		
	}
	
	//�û���Ϣд�����ݿ�
	void saveInDB(String name,String password,String state) throws SQLException{
		Connection con=super.con;
		Statement stmt=con.createStatement();
		
		//������userinfo
		String sql="create table if not exists userinfo(username varchar(10),userpassword varchar(20),userstate varchar(5))";
		stmt.executeUpdate(sql);
		System.out.println("�����û���Ϣ��ɹ�");
		
		//������Ϣ
		sql="insert into userinfo(username,userpassword,userstate) values('"+name+"','"+password+"','"+state+"')";
		int judge=stmt.executeUpdate(sql);
		if(judge>0)
			System.out.println("ע��ɹ�");
		else
			System.out.println("ע��ʧ��");
		
	}

}
