package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.DAO;
import DAO.DAOException;
import model.Film;

public class Database implements DAO<Film, Integer> {

	private Connection connection;
	private final String GET= "SELECT shirt_num, name, position FROM players WHERE shirt_num = ?";
	private final static Logger log = Logger.getLogger("Database");
	private String query = " insert into users (idCliente, name, dateBirth, city, premium)"
	        + " values (?, ?, ?, ?, ?)";
	
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
		/**
		 * @author David Pacios Fernández
		 * Creación del método que intorduce un/a cliente en la base de datos. no había tocado base de datos nunca, muy divertido.
		 * El método pide al usuario los datos del cliente: id, nombre, fecha de nacimiento, ciudad y si es o no cliente premium. 
		 */
				  
			Scanner in = new Scanner(System.in);
				
				System.out.println("select the id of the client to insert: ");
			      int i = in.nextInt();
			    System.out.println("now select the client's name: ");
			      String name = in.nextLine();
			    System.out.println("select the client's date of birth: ");
			      int dateOfBirth = in.nextInt();
			    System.out.println("select the client's city of residence: ");
			      String city = in.nextLine();
			    System.out.println("select if the client's contract is of the premium modality(Y/N):");
			      int isPremium;
			      String modality;
			      do {
			      modality = in.nextLine();
			      if (modality.equalsIgnoreCase("y")) {
			    	  isPremium = 1;
			      } else if (modality.equalsIgnoreCase("n")) {
			    	  isPremium = 0;
			      } else {
			    	  log.log(Level.SEVERE, "sorry, only 'Y' or 'N' are acceptable responses");
			      }
			      } while(!modality.equalsIgnoreCase("y") && !modality.equalsIgnoreCase("n"));
			      
			      PreparedStatement preparedStmt = connection.prepareStatement(query);
			      preparedStmt.setInt (1, i);
			      preparedStmt.setString (2, name);
			      preparedStmt.setInt   (3, dateOfBirth);
			      preparedStmt.setString (4, "city");
			      preparedStmt.setInt  (5, isPremium);

			      
			      preparedStmt.execute();
			     in.close(); 		      
		
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
