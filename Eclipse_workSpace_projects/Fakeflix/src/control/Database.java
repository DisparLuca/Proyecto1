package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.DAO;
import DAO.DAOException;
import model.Film;

public class Database implements DAO<Film, Integer> {

	private Connection connection;
	private final String GET= "SELECT shirt_num, name, position FROM players WHERE shirt_num = ?";
	public Database(Connection connection) {
		this.connection= connection;
	}

	@Override
	public Film get(Integer idFilm) throws DAOException {
		ResultSet resultSet= null;
		PreparedStatement preparedStatement= null;
		Film film= null;

		try {
			
			preparedStatement= connection.prepareStatement(GET);
			preparedStatement.setInt(1, idFilm);
			resultSet= preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String name= resultSet.getString("name");
				String category=resultSet.getString("category");
				int year = resultSet.getInt("year");
				
				film= new Film(name, category,year);
				System.out.println(film.getName());
			
			}//if
			
		} catch (SQLException e) {throw new DAOException("Excepción SQL", e);
		
		} finally {
			
			try {
				
				preparedStatement.close();
				resultSet.close();
				
			} catch (SQLException e) {throw new DAOException("Excepción SQL", e);}
			
		}//finally
		
		return film;
	}
	
	@Override
	public List<Film> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Film t) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int t) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Film t) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
