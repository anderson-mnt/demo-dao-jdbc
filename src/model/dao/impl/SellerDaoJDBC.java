package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao	{

	private Connection conn;
	
	public SellerDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+"VALUES "
					+"(?, ?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);//Retorna id gerado do insert
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0){//inseriu
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);// objeto recebe o valor do id do insert
				}
				DB.closeResultSet(rs);
			}
			
			else {
				throw new DbException("Unexpected error! No rows affected");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ? "
					);
					
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {//se existe valor na query
				Department dep = InstantiateDepartment(rs);
				Seller obj = InstantiateSeller(dep, rs);
				return obj;
			}
		}
		catch(SQLException e) {
			throw new DbException (e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}


	private Seller InstantiateSeller(Department dep, ResultSet rs) throws SQLException {
		Seller obj = new Seller();//cria nova instancia de Selle
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department InstantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();//cria nova instancia de Department
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}


	

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name "
					);
					
			rs = st.executeQuery();
			List<Seller> listSeller = new ArrayList<Seller>(); 
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {//se existe valor na query
				Department dep = map.get(rs.getInt("DepartmentId"));//verifica se no map existe o valor informado no DepartmentId
				
				if(dep == null) {//se o departament não existe no map
					dep = InstantiateDepartment(rs); //instancia department
					map.put(rs.getInt("DepartmentId"), dep);//atribui o valor no map para a proxima verifica	
				}
				
				Seller obj = InstantiateSeller(dep, rs);
				listSeller.add(obj);
			}
			return listSeller;
		}
		catch(SQLException e) {
			throw new DbException (e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}
	

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name "
					);
					
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Seller> listSeller = new ArrayList<Seller>(); 
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {//se existe valor na query
				Department dep = map.get(rs.getInt("DepartmentId"));//verifica se no map existe o valor informado no DepartmentId
				
				if(dep == null) {//se o departament não existe no map
					
					dep = InstantiateDepartment(rs); //instancia department
					map.put(rs.getInt("DepartmentId"), dep);//atribui o valor no map para a proxima verifica
					
				}
				
				
				Seller obj = InstantiateSeller(dep, rs);
				listSeller.add(obj);
			}
			return listSeller;
		}
		catch(SQLException e) {
			throw new DbException (e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

}
