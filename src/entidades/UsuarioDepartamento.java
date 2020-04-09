package entidades;

import java.util.*;

import org.apache.log4j.Logger;

public class UsuarioDepartamento {
	private int idUsuario;
	private int idDepartamento;
	private Date fecha = new Date();
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	//Constructor por defecto
	public UsuarioDepartamento()
	{
		this.idUsuario = 0;
		this.idDepartamento = 0;
		this.fecha.setTime(0);
	}
	//Constructor con atributos especificos
	public UsuarioDepartamento(int atr1, int atr2,long atr3)
	{
		this.idUsuario = atr1;
		this.idDepartamento = atr2;
		this.fecha.setTime(atr3);
	}
	
	
	//Metodos para cambiar los distintos atributos de los objetos de la clase Departamento
	public void changeIdUsuario(int atr1)
	{
		this.idUsuario = atr1;
	}
	public void changeIdDepartamento(int atr2)
	{
		this.idDepartamento = atr2;
	}
	public void changeFecha(long atr3)
	{
		this.fecha.setTime(atr3);
	}
	
	
	//Metodos para obtener cierto atributo de Departamento
	public int getIdUsuario()
	{
		return this.idUsuario;
	}
	public int getIdDepartamento()
	{
		return this.idDepartamento;
	}
	public long getFecha()
	{
		return this.fecha.getTime();
	}
	public Date getFechaTexto()
	{
		return this.fecha;
	}
	
	
	
	//Metodo para comparar 2 UsuarioDepartamento
	public boolean equals(UsuarioDepartamento UserDepartament)
	{
		boolean bool1 = this.idUsuario == UserDepartament.idUsuario;
		boolean bool2 = this.idDepartamento == UserDepartament.idDepartamento;
		if (bool1 && bool2)
		{
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void finalize()
	{
		logger.info("Relacion con idUsuario: " + this.idUsuario + " y con idDepartamento: " + idDepartamento + " borrada de memoria");
	}
}
