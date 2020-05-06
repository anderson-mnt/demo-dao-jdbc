package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {//classe auxiliar responsavel por instanciar os Daos

	public static SellerDao createSellerDao() {//retorna o tipo da interface
		return new SellerDaoJDBC(DB.getConnection());//internamente instancia uma implementacao
	}
}
