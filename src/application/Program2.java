package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		
	Scanner in = new Scanner(System.in);	
	DepartmentDao departmentDao = DaoFactory.createDepartmentDao(); //programa conhece somente a interface e nao a implementacao	
	
	System.out.println("=== TEST 1: Department findById ===");	
	Department department = departmentDao.findById(5);
	System.out.println(department);
	
	System.out.println("=== TEST 3: Department findAll ===");
	List<Department> list = departmentDao.findAll();
	for(Department obj : list){
		System.out.println(obj);
	}

	System.out.println("=== TEST 4: Department insert ===");
	Department newDepartment = new Department(null, "Development");
	departmentDao.insert(newDepartment);;
	System.out.println("Inserted! New id = "+ newDepartment.getId());	
		
	System.out.println("=== TEST 5: Department update ===");
	newDepartment = departmentDao.findById(1);
	department = departmentDao.findById(5);
	department.setName("Production");
	departmentDao.update(department);
	System.out.println("Update completed! "+ department);	
		
	System.out.println("=== TEST 6: Department delete ===");
	System.out.println("Enter id for delete test");
	int id = in.nextInt(); 
	departmentDao.deleteById(id);
	System.out.println("Delete completed! ");
		
	}
}
