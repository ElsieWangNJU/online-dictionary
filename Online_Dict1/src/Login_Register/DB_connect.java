package Login_Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB_connect {
	Connection con=null;
	String url=null;
	
	public void connection() throws SQLException{
		url="jdbc:mysql://localhost:3306/onlinedict?user=root&password=1234&characterEnunicode=UTF8";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
        // ������������Ҫָ��useUnicode��characterEncoding
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�ɹ�����mysql����");
			con=DriverManager.getConnection(url);
			System.out.println("���ݿ����ӳɹ�");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("���ݿ��������");
			e.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args) {
		DB_connect lo = new DB_connect();
		try {
			lo.connection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}*/
	
}
