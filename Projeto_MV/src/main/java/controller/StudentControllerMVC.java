package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.StudentDAO;
import entity.Student;
import db.ConexaoMySql;

public class StudentControllerMVC {
	
//	public List<Student> Listar(String pesquisa) throws ClassNotFoundException, SQLException {
//		
//		List<Student> lstCat = null;
//		try {
//			
//			Connection conn = ConexaoMySql.getInstance().getConnection();
//			StudentDAO studDAO = new StudentDAO(conn);
//			
//			if(pesquisa.trim().equals("")) {
//				lstCat = studDAO.list();
//			}
//			else {
//				lstCat = studDAO.list(pesquisa);
//			}
//			
//			
//			conn.close();
//		}
//		catch(SQLException ex){
//			throw ex;
//		}
//		
//		return lstCat;
//}


}
