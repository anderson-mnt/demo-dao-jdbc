package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {//classe auxiliar responsavel por instanciar os Daos

	public static SellerDao createSellerDao() {//retorna o tipo da interface
		return new SellerDaoJDBC();//internamente instancia uma implementacao
	}
}
