package application;


import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj = new Department(1,"João");
		Seller seller = new Seller(21, "Pedro", "pedro@gmail.com", new Date(), 3000.0, obj);
		System.out.println(obj);
		System.out.println(seller);
	}
	
	SellerDao sellerDao = DaoFactory.createSellerDao();//assim o programa conhce somente a interface e nao a implementacao
	//e uma forma de fazer a injecao de dependencia sem explicitar a implementacao

}
