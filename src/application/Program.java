package application;

import java.util.List;

//import model.entities.Department;
//import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		/*
		Department obj = new Department(1,"João");
		Seller seller = new Seller(21, "Pedro", "pedro@gmail.com", new Date(), 3000.0, obj);
		System.out.println(obj);
		System.out.println(seller);
		*/
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1: Seller findById ===");	
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		
		System.out.println("=== TEST 2: Seller findByDepartmentId ===");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list){
			System.out.println(obj);
		}
		
		System.out.println("=== TEST 3: Seller findAll ===");
		list = sellerDao.findAll();
		for(Seller obj : list){
			System.out.println(obj);
		}
	}
	}
	
	//SellerDao sellerDao = DaoFactory.createSellerDao();//assim o programa conhce somente a interface e nao a implementacao
	


