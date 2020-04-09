package entidades;

import org.apache.log4j.Logger;

public class Usuario {
	private int idUsuario;
	private String apellidos;
	private String dni;
	public enum TipoSexo {MASCULINO,FEMENINO,NO_ESPECIFICADO};
	private TipoSexo sexo;
	private float altura;
	private String correo;
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	
	/**
	 * Constructor por defecto de Usuario
	 * jsduewjaa
	 */
	
	//Constructor por defecto
	public Usuario()
	{
		this.idUsuario = 0;
		this.apellidos = "apellido1/apellido2";
		this.dni = "0000000A";
		this.sexo = TipoSexo.NO_ESPECIFICADO;
		this.altura = 0;
		this.correo = "correo@correo.es";
	}
	//Constructor con atributos especificos
	public Usuario(int atr1, String atr2, String atr3, String atr4, float atr5, String atr6)
	{
		this.idUsuario = atr1;
		this.apellidos = atr2;
		this.dni = atr3;
		try{
			this.sexo = TipoSexo.valueOf(atr4);
			}catch (IllegalArgumentException e){
				this.sexo = TipoSexo.NO_ESPECIFICADO;
			}
		this.altura= atr5;
		this.correo = atr6;
	}
	
	
	//Metodos para cambiar los distintos atributos de los objetos de la clase Usuario
	public void changeIdUsuario(int atr1)
	{
		this.idUsuario = atr1;
	}
	public void changeApellidos(String atr2)
	{
		this.apellidos = atr2;
	}
	public void changeDni(String atr3)
	{
		this.dni = atr3;
	}
	public void changeSexo(String atr4)
	{
		try{
			this.sexo = TipoSexo.valueOf(atr4);
			}catch (IllegalArgumentException e){
				this.sexo = TipoSexo.NO_ESPECIFICADO;
			}
	}
	public void changeAltura(float atr5)
	{
		this.altura = atr5;
	}
	public void changeCorreo(String atr6)
	{
		this.correo = atr6;
	}
	
	
	//Metodos para obtener cierto atributo de Usuario
	public int getIdUsuario()
	{
		return this.idUsuario;
	}
	public String getApellidos()
	{
		return this.apellidos;
	}
	public String getDni()
	{
		return this.dni;
	}
	public TipoSexo getSexo()
	{
		return this.sexo;
	}
	public float getAltura()
	{
		return this.altura;
	}
	public String getCorreo()
	{
		return this.correo;
	}
	
	
	//Metodos para comparar atributos de distintos usuarios
	//Creamos estos metodos solo para los atributos: idUsuario, dni y correo
	//Esto es porque no puede haber dos Usuarios conmpartiendo alguno de estos atributos
	public boolean equalsIdUsuario(Usuario user)
	{
		return this.idUsuario == user.idUsuario;
	}
	public boolean equalsDni(Usuario user)
	{
		return this.dni.equalsIgnoreCase(user.dni);
	}
	public boolean equalsCorreo(Usuario user)
	{
		return (this.correo.compareToIgnoreCase(user.correo) == 0);
	}
	
	
	//Metodo para comparar 2 usuarios
	public boolean equals(Usuario user)
	{
		boolean bool1 = this.idUsuario == user.idUsuario;
		boolean bool2 = this.apellidos.equalsIgnoreCase(user.apellidos);
		boolean bool3 = this.dni.equalsIgnoreCase(user.dni);
		boolean bool4 = this.sexo.equals(user.sexo);
		boolean bool5 = this.altura == user.altura;
		boolean bool6 = (this.correo.compareToIgnoreCase(user.correo) == 0);
		if (bool1 && bool2 && bool3 && bool4 && bool5 && bool6)
		{
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void finalize()
	{
		logger.info("Usuario con idUsuario: " + this.idUsuario + " borrado de memoria");
	}
	
}
