package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoMySql;
import entity.Student;
import util.enums.EnumPerson;
import util.enums.EnumStudent;
import util.interfaces.IStudent;

public class StudentDAO implements IStudent {

	public void insert(Student obj) {
		String SQL_insertPerson = "INSERT INTO " + EnumPerson.person + " (" + 
		EnumPerson.name + ", " + 
		EnumPerson.email + ", " +
		EnumPerson.phone + ", " +
		EnumPerson.address + ") " + "VALUES (?, ?, ?, ?)";
		
		String SQL_selectPerson = "SELECT * FROM " + EnumPerson.person + " ORDER BY cod DESC LIMIT 1";
		
		String SQL_insertStudent = "INSERT INTO " + EnumStudent.student + " (" +
		EnumStudent.cod + ", " +
		EnumStudent.ra + ", " +
		EnumStudent.course + ", " + 
		EnumStudent.period + ") " + "VALUES (?, ?, ?, ?)";
		
		try (Connection conn = ConexaoMySql.getInstance().getConnection();
				PreparedStatement stInsertPerson = conn.prepareStatement(SQL_insertPerson);
				PreparedStatement stSelectPerson = conn.prepareStatement(SQL_selectPerson);
				PreparedStatement stInsertStudent = conn.prepareStatement(SQL_insertStudent);) {
			
			stInsertPerson.setString(1, obj.getName());
			stInsertPerson.setString(2, obj.getEmail());
			stInsertPerson.setString(3, obj.getPhone());
			stInsertPerson.setString(4, obj.getAddress());
			stInsertPerson.execute();
			
			ResultSet rs = stSelectPerson.executeQuery();
			rs.next();
			
			stInsertStudent.setInt(1, rs.getInt(EnumPerson.cod.name()));
			stInsertStudent.setString(2, obj.getRa());
			stInsertStudent.setString(3, obj.getCourse());
			stInsertStudent.setString(4, obj.getPeriod());
			stInsertStudent.execute();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delet(Integer id) {
		String sqlDeletStudent = "delete from " + EnumStudent.student + 
				" where " + EnumStudent.cod	+ "= ?";
		
		String sqlDeletPerson = "delete from " + EnumPerson.person +
				" where " + EnumPerson.cod + "= ?";

		try (Connection connection = ConexaoMySql.getInstance().getConnection();
				PreparedStatement statementPerson = connection.prepareStatement(sqlDeletPerson);
				PreparedStatement statementStudent = connection.prepareStatement(sqlDeletStudent);) {

			statementStudent.setInt(1, id);
			statementPerson.setInt(1, id);

			statementStudent.execute();
			statementPerson.execute();

		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void edit(Student obj) {
		String sqlUpdatePerson = "UPDATE " + EnumPerson.person + " SET " + 
				EnumPerson.name + " = ?, " + 
				EnumPerson.email + " = ?, " + 
				EnumPerson.phone + " = ?, " + 
				EnumPerson.address + " = ?, " + 
				"WHERE " + EnumPerson.cod + " = ?";

		String sqlUpdateStudent = "UPDATE " + EnumStudent.student + " SET " + 
				EnumStudent.ra + "= ?, " + 
				EnumStudent.course + "= ?, " +
				EnumStudent.period + "= ? " +
				" WHERE " + EnumStudent.cod + " = ?";
		
		try (Connection connection = ConexaoMySql.getInstance().getConnection();
				PreparedStatement stUpPerson = connection.prepareStatement(sqlUpdatePerson);
				PreparedStatement stUpStudent = connection.prepareStatement(sqlUpdateStudent);) {
		
			stUpPerson.setString(1, obj.getName());
			stUpPerson.setString(2, obj.getEmail());
			stUpPerson.setString(3, obj.getPhone());
			stUpPerson.setString(4, obj.getAddress());
			stUpPerson.setInt(5, obj.getCod());
			
			stUpStudent.setString(1, obj.getRa());
			stUpStudent.setString(2, obj.getCourse());
			stUpStudent.setString(3, obj.getPeriod());
			stUpStudent.setInt(4, obj.getCod());
			
			stUpPerson.execute();
			stUpStudent.execute();
			
		}catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public List<Student> list() {
		List<Student> listStu = new ArrayList<>();
		
		String SQL_selectStudent = "SELECT * FROM " 
				+ EnumPerson.person + " p INNER JOIN " + EnumStudent.student + 
				" s ON p." + EnumPerson.cod + " = s." + EnumStudent.cod; 
		
		try (Connection conn = ConexaoMySql.getInstance().getConnection();
				PreparedStatement st = conn.prepareStatement(SQL_selectStudent);) {
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				listStu.add(new Student(
						rs.getInt(EnumPerson.cod.name()),
						rs.getString(EnumPerson.name.name()),
						rs.getString(EnumPerson.email.name()),
						rs.getString(EnumPerson.phone.name()),
						rs.getString(EnumPerson.address.name()),
						
						rs.getInt(EnumStudent.cod.name()),
						rs.getString(EnumStudent.ra.name()),
						rs.getString(EnumStudent.course.name()),
						rs.getString(EnumStudent.period.name())
						));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStu;
	}
	
	//NOT USED - list() method already have the search statement
	public void searchId(Integer id) {
		// TODO Auto-generated method stub
		
	}


}
