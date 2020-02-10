package control;

import DAO.DAO;
import DAO.DAOException;
import DAO.FactoryDAO;
import model.Film;

public class Main {

	public static void main(String[] args) throws DAOException {
		
		DAO<Film, Integer> dao = FactoryDAO.getInstance().getDAO();
		//dao.get(1);
		dao.cargarDatos();

	}

}
