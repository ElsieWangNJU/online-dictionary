package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Exit extends DB_con{
	
	public String exit(String name){
	
		String result=null;
		try {
			connection(); 		//����conn_db�࣬�������ݿ⣻
			boolean com = changeInDB(name);
			if(com==true)
				result="success";
			else{
				result="wrong";
			}
		} 
		catch (Exception e1) {
			result="fail";
			e1.printStackTrace();
		}
		return result;
	}
	
	//�޸����ݿ����û�״̬
	boolean changeInDB(String name) throws Exception{	
		Connection con = super.con;
		Statement stmt = con.createStatement();
		
		String sql = "select * from userinfo";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){				
			//�û��������Ϣ�����ݿ��е���Ϣ���Ƚϣ��ж������Ƿ���ȷ��
			String username = rs.getString(1);
			String userpassword = rs.getString(2);
			String state=rs.getString(3);
			if(username.equals(name) )
			{
				state="1";//1��ʾ����
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