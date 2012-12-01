package com.itccxx.core.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static Connection getConn(){
		Connection conn = null;
		return conn;
	}
	
	public static void closeStatement(Statement stmt,Connection conn,ResultSet rs){
		try{
			if(stmt != null){
				stmt.close();
			}
			if(rs != null){
				rs.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
