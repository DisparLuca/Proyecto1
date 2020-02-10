package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.DAO;
import DAO.DAOException;
import model.Film;
import mysqlConnection.DBConnection_MySQL;

public class Database implements DAO<Film, Integer> {

	private Connection connection;
	private final String GET= "SELECT shirt_num, name, position FROM players WHERE shirt_num = ?";
	private static final String SQL_INSERT = "INSERT INTO pelicula(name, year, category) VALUES(?, ?, ?)";
	
	private final static Logger LOGGER = Logger.getLogger("CargarDatosBD");
	private File archivo = null;
	private FileReader fr =null;
	private BufferedReader br = null;
	

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
    public void save(Film pelicula) throws DAOException {
    	
        PreparedStatement stmt = null;
      
        try {
            
            stmt = connection.prepareStatement(SQL_INSERT);
            stmt.setString(1, pelicula.getName());
            stmt.setInt(2, pelicula.getYear());
            stmt.setString(3, pelicula.getCategory());
            stmt.executeUpdate();
            
            System.out.println("ejecutando query:" + SQL_INSERT);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
        	   
        }
        
        
    }
	
	@Override
	public void cargarDatos() {
		
		LOGGER.log(Level.INFO,"Leyendo fichero de peliculas");
		try {
			
			archivo = new File ("./settings/peliculas_cat.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
						
			String linea=null;
					
			Film pelicula;
			while((linea=br.readLine())!=null) {
			
				String[] parts = linea.split(",");
				String nombrePelicula = parts[0]; 
				String añoString = parts[1];
				String categoria = parts[2];
				int añoInt = Integer.parseInt(añoString);	
				pelicula = new Film(nombrePelicula,categoria,añoInt);
				save(pelicula);
				System.out.println(pelicula);
				
				}
			LOGGER.log(Level.INFO,"Peliculas leidas correctamente");
									
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(null != fr) {
					fr.close();
				}
			}catch(Exception e2){
					e2.printStackTrace();
				}
		}
	
	}
	
	
	@Override
	public List<Film> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
