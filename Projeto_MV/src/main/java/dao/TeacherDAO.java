package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoMySql;
import entity.Teacher;
import util.enums.EnumPerson;
import util.enums.EnumTeacher;
import util.interfaces.ITeacher;

public class TeacherDAO implements ITeacher {

	@Override
	public void insert(Teacher obj) {
		String SQL_insertPerson = "INSERT INTO " + EnumPerson.person + " (" + 
		EnumPerson.name + ", " + 
		EnumPerson.email + ", " +
		EnumPerson.phone + ", " +
		EnumPerson.address + ") " + "VALUES (?, ?, ?, ?)";
		
		String SQL_selectPerson = "SELECT * FROM " + EnumPerson.person + " ORDER BY cod DESC LIMIT 1";
		
		String SQL_insertTeacher = "INSERT INTO " + EnumTeacher.teacher + " (" +
		EnumTeacher.cod + ", " +
		EnumTeacher.rp + ", " +
		EnumTeacher.teaching + ", " + 
		EnumTeacher.salary + ") " + "VALUES (?, ?, ?, ?)";
		
		try (Connection conn = ConexaoMySql.getInstance().getConnection();
				PreparedStatement stInsertPerson = conn.prepareStatement(SQL_insertPerson);
				PreparedStatement stSelectPerson = conn.prepareStatement(SQL_selectPerson);
				PreparedStatement stInsertTeacher = conn.prepareStatement(SQL_insertTeacher);) {
			
			stInsertPerson.setString(1, obj.getName());
			stInsertPerson.setString(2, obj.getEmail());
			stInsertPerson.setString(3, obj.getPhone());
			stInsertPerson.setString(4, obj.getAddress());
			stInsertPerson.execute();
			
			ResultSet rs = stSelectPerson.executeQuery();
			rs.next();
			
			stInsertTeacher.setInt(1, rs.getInt(EnumPerson.cod.name()));
			stInsertTeacher.setString(2, obj.getRp());
			stInsertTeacher.setString(3, obj.getTeaching());
			stInsertTeacher.setString(4, obj.getSalary());
			stInsertTeacher.execute();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delet(Integer id) {
		String sqlDeletTeacher = "delete from " + EnumTeacher.teacher + 
				" where " + EnumTeacher.cod	+ "= ?";
		
		String sqlDeletPerson = "delete from " + EnumPerson.person +
				" where " + EnumPerson.cod + "= ?";

		try (Connection connection = ConexaoMySql.getInstance().getConnection();
				PreparedStatement statementPerson = connection.prepareStatement(sqlDeletPerson);
				PreparedStatement statementTeacher = connection.prepareStatement(sqlDeletTeacher);) {

			statementTeacher.setInt(1, id);
			statementPerson.setInt(1, id);

			statementTeacher.execute();
			statementPerson.execute();

		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

	@Override
	public void edit(Teacher obj) {
		String sqlUpdatePerson = "UPDATE " + EnumPerson.person + " SET " + 
				EnumPerson.name + " = ?, " + 
				EnumPerson.email + " = ?, " + 
				EnumPerson.phone + " = ?, " + 
				EnumPerson.address + " = ?, " + 
				"WHERE " + EnumPerson.cod + " = ?";

		String sqlUpdateTeacher = "UPDATE " + EnumTeacher.teacher + " SET " + 
				EnumTeacher.rp + "= ?, " + 
				EnumTeacher.teaching + "= ?, " +
				EnumTeacher.salary + "= ? " +
				" WHERE " + EnumTeacher.cod + " = ?";
		
		try (Connection connection = ConexaoMySql.getInstance().getConnection();
				PreparedStatement stUpPerson = connection.prepareStatement(sqlUpdatePerson);
				PreparedStatement stUpTeacher = connection.prepareStatement(sqlUpdateTeacher);) {
		
			stUpPerson.setString(1, obj.getName());
			stUpPerson.setString(2, obj.getEmail());
			stUpPerson.setString(3, obj.getPhone());
			stUpPerson.setString(4, obj.getAddress());
			stUpPerson.setInt(5, obj.getCod());
			
			stUpTeacher.setString(1, obj.getRp());
			stUpTeacher.setString(2, obj.getTeaching());
			stUpTeacher.setString(3, obj.getSalary());
			stUpTeacher.setInt(4, obj.getCod());
			
			stUpPerson.execute();
			stUpTeacher.execute();
			
		}catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}


	@Override
	public List<Teacher> list() {
		List<Teacher> listTea = new ArrayList<>();
		
		String SQL_selectStudent = "SELECT * FROM " 
				+ EnumPerson.person + " p INNER JOIN " + EnumTeacher.teacher + 
				" s ON p." + EnumPerson.cod + " = s." + EnumTeacher.cod; 
		
		try (Connection conn = ConexaoMySql.getInstance().getConnection();
				PreparedStatement st = conn.prepareStatement(SQL_selectStudent);) {
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				listTea.add(new Teacher(
						rs.getInt(EnumPerson.cod.name()),
						rs.getString(EnumPerson.name.name()),
						rs.getString(EnumPerson.email.name()),
						rs.getString(EnumPerson.phone.name()),
						rs.getString(EnumPerson.address.name()),
						
						rs.getString(EnumTeacher.teaching.name()),
						rs.getString(EnumTeacher.rp.name()),
						rs.getString(EnumTeacher.salary.name()),
						rs.getInt(EnumTeacher.cod.name())
						));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listTea;
	}
	
	//NOT USED 
	@Override
	public void searchId(Integer id) {
		// TODO Auto-generated method stub
		
	}


}
