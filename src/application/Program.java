package application;

import java.util.Date;
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
		Department obj = new Department(1,"Jo�o");
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
		
		System.out.println("=== TEST 4: Seller insert ===");
		Seller newSeller = new Seller(null, "Anderson", "anderson@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = "+ newSeller.getId());
	}
	}
	
	//SellerDao sellerDao = DaoFactory.createSellerDao();//assim o programa conhce somente a interface e nao a implementacao
	


