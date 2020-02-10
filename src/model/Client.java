package model;
import java.util.Date;


public class Client {
	
	private int id_cliente;
	private String name;
	private String city;
	private boolean premium;
	private Date birthdate;
	
	public Client() {
		super();
	}

	public Client(int id_cliente, String name, String city, boolean premium, Date birthdate) {
		this.id_cliente = id_cliente;
		this.name = name;
		this.city = city;
		this.premium = premium;
		this.birthdate = birthdate;
	}
	
	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", name=" + name + ", city=" + city + ", premium=" + premium
				+ ", birthdate=" + birthdate + "]";
	}
	
	
	

}
