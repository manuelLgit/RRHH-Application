package entidades;

import org.apache.log4j.Logger;

public class Departamento {
	private int idDepartamento;
	private String nombre;
	private String director;
	private int numPersonas;
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	//Constructor por defecto
	public Departamento()
	{
		this.idDepartamento = 0;
		this.nombre = "departamento";
		this.director = "director";
		this.numPersonas = 0;
	}
	//Constructor con atributos especificos
	public Departamento(int atr1, String atr2, String atr3, int atr4)
	{
		this.idDepartamento = atr1;
		this.nombre = atr2;
		this.director = atr3;
		this.numPersonas=atr4;
	}
	
	
	//Metodos para cambiar los distintos atributos de los objetos de la clase Departamento
	public void changeIdDepartamento(int atr1)
	{
		this.idDepartamento = atr1;
	}
	public void changeNombre(String atr2)
	{
		this.nombre = atr2;
	}
	public void changeDirector(String atr3)
	{
		this.director = atr3;
	}
	public void changeNumPersonas(int atr4)
	{
		this.numPersonas = atr4;
	}
	
	
	//Metodos para obtener cierto atributo de Departamento
	public int getIdDepartamento()
	{
		return this.idDepartamento;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public String getDirector()
	{
		return this.director;
	}
	public int getNumPersonas()
	{
		return this.numPersonas;
	}
	
	
	//Metodos para comparar atributos de distintos departamentos
	//Creamos estos metodos solo para los atributos: idDepartamento y nombre
	//Esto es porque no puede haber 2 Departamentos compartiendo alguno de estos atributos
	public boolean equalsIdDepartamento(Departamento departament)
	{
		return this.idDepartamento == departament.idDepartamento;
	}
	public boolean equalsNombre(Departamento departament)
	{
		return this.nombre.equalsIgnoreCase(departament.nombre);
	}
	
	
	//Metodo para comparar 2 departamento
	public boolean equals(Departamento departament)
	{
		boolean bool1 = this.idDepartamento == departament.idDepartamento;
		boolean bool2 = this.nombre.equalsIgnoreCase(departament.nombre);
		boolean bool3 = this.director.equalsIgnoreCase(departament.director);
		boolean bool4 = this.numPersonas == departament.numPersonas;
		if (bool1 && bool2 && bool3 && bool4)
		{
			return true;
		}else{
			return false;
		}
	}
	
	
	@Override
	public void finalize()
	{
		logger.info("Departamento con idDepartamento: " + this.idDepartamento + " borrado de memoria");
	}
	

}
