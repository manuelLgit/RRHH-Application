package entidadesUtilidades;

import java.util.Scanner;

import org.apache.log4j.Logger;

import entidadesArrayList.DepartamentoArrayList;
import entidadesArrayList.UsuarioArrayList;

public class CreateAtributo {
	
	private static MostrarPorPantalla show = new MostrarPorPantalla();
	private static CheckFormat comprueba = new CheckFormat();
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------METODOS PARA CREAR LOS ATRIBUTOS DEL USUARIO---------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-createRandomIdUsuario.
// 2-createIdUsuario.
// 3-createApellidos.
// 4-createDni.
// 5-createSexo.
// 6-createAltura.
// 7-createCorreo.

	
	public int createRandomIdUsuario(UsuarioArrayList listaUsuarios)
	{
		
		int n = listaUsuarios.getUsuariosMaximos();
		logger.info("entramos en createRandomIdUsuario con Usuario Maximos: " + n);
		//n tiene que ser mayor que el numero de Usuarios en mi Lista de Usuarios
		while(n <= listaUsuarios.sizeListaUsuarios()){
			n=n+100;
		}
		listaUsuarios.changeUsuariosMaximos(n);
		logger.info("Usuarios Maximos actualizados: " + listaUsuarios.getUsuariosMaximos());
		int numeroIdUsuario;
		while(true){
			numeroIdUsuario = (int) (Math.random() * n) + 1;
			logger.info("prueba de IdUsuario: " + numeroIdUsuario);
			if(listaUsuarios.searchUsuarioIdUsuario(numeroIdUsuario) == null){
				break;
			}
		}
		show.MostrarUsuarioIdUsuario(numeroIdUsuario);
		return numeroIdUsuario;
	}
	public int createIdUsuario(UsuarioArrayList listaUsuarios, Scanner leer, boolean compruebaIdUsuarioEnUsuarioArrayList)
	{
		logger.info("entramos en createIdUsuario con CompruebaIdUsuario: " + compruebaIdUsuarioEnUsuarioArrayList);
		int idUsuario;
		String textoIdUsuario;
		while(true){
			while(true){
				show.MostrarUsuarioIntroduzcaIdUsuario(listaUsuarios.getUsuariosMaximos());
				textoIdUsuario = leer.nextLine();
				logger.info("Introducido: " + textoIdUsuario);
				if (comprueba.checkFormatIdUsuario(textoIdUsuario, listaUsuarios)) {
					idUsuario = Integer.parseInt(textoIdUsuario.trim());
					break;					
				} else {
					logger.warn("error en formato de idUsuario:  " + textoIdUsuario);
					show.MostrarErrorFormato();
				}
			}
			if(compruebaIdUsuarioEnUsuarioArrayList){
				if(listaUsuarios.searchUsuarioIdUsuario(idUsuario)==null){
					logger.info("no existe idUsuario, muy bien");
					break;
				}else{
					logger.warn("ya existe idUsuario");
					show.MostrarUsuarioErrorExisteIdUsuario(idUsuario);
				}
			}else{
				break;
			}
		}
		return idUsuario;
	}
	public String createApellidos(Scanner leer)
	{
		logger.info("entramos en createApellidos");
		String apellido1;
		String apellido2;
		String apellidos;
		while(true){
			show.MostrarUsuarioIntroduzcaPrimerApellido();
			apellido1 = leer.nextLine();
			logger.info("introducido apellido1: " + apellido1);
			apellido1 = apellido1.trim().toUpperCase();
			show.MostrarUsuarioIntroduzcaSegundoApellido();
			apellido2 = leer.nextLine();
			logger.info("introducido apellido2: " + apellido2);
			apellido2 = apellido2.trim().toUpperCase();
			apellidos = apellido1 + "/" + apellido2;
			if(comprueba.checkFormatApellidos(apellidos)){
				logger.info("buen formato de apellidos");
				break;
			}else{
				logger.info("mal formato de apellidos");
				show.MostrarErrorFormato();
			}
		}
		return apellidos;
		
	}
	public String createDni(UsuarioArrayList listaUsuarios,Scanner leer, boolean compruebaDniEnUsuarioArrayList)
	{
		logger.info("entramo en createDni con compruebaDni: " + compruebaDniEnUsuarioArrayList);
		String dni;
		while(true){
			while(true){
				show.MostrarUsuarioIntroduzcaDni();
				dni = leer.nextLine();
				logger.info("introducido dni: "+dni);
				dni = dni.toUpperCase().trim();
				if(comprueba.checkFormatDni(dni)){
					break;
				}else{
					show.MostrarErrorFormato();
				}
			}
			if(compruebaDniEnUsuarioArrayList){
				if(null == listaUsuarios.searchUsuarioDni(dni)){
					break;
				}else{
					show.MostrarUsuarioErrorExisteDni(dni);
				}
			}else{
				break;
			}
		}
		return dni;	
	}
	public String createSexo(Scanner leer)
	{
		logger.info("entramos en createSexo");
		String sexo;
		show.MostrarUsuarioIntroduzcaSexo();
		sexo=leer.nextLine();
		logger.info("sexo introducido: " + sexo);
		return sexo.toUpperCase().trim();
	}
	public float createAltura(Scanner leer)
	{
		logger.info("entramos en createAltura");
		Float altura;
		String textoAltura;
		while(true){
			show.MostrarUsuarioIntroduzcaAltura();
			textoAltura = leer.nextLine();
			logger.info("altura introducida: " +textoAltura);
			if(comprueba.checkFormatAltura(textoAltura.trim())){
				altura = Float.parseFloat(textoAltura);
				break;
			}else{
				logger.warn("error en formato de altura");
				show.MostrarErrorFormato();
			}
		}
		return altura;
	}
	public String createCorreo(UsuarioArrayList listaUsuarios,Scanner leer,boolean compruebaCorreoEnUsuarioArrayList)
	{
		logger.info("entramos en createCorreo con compruebaCorreo: " + compruebaCorreoEnUsuarioArrayList);
		int minCaracteresX = 3;
		int minCaracteresY = 3;
		int minCaracteresZ = 2;
		String correo;
		while(true){
			while(true){
				show.MostrarUsuarioIntroduzcaCorreo(minCaracteresX,minCaracteresY,minCaracteresZ);
				correo = leer.nextLine();
				logger.info("correo introducido: "+correo);
				correo = correo.trim();
				if (comprueba.checkFormatCorreo(correo)) {
					break;
				} else {
					show.MostrarErrorFormato();
				}
			}
			if (compruebaCorreoEnUsuarioArrayList) {
				if (listaUsuarios.searchUsuarioCorreo(correo) == null) {
					break;
				} else {
					show.MostrarUsuarioErrorExisteCorreo(correo);
				}
			} else {
				break;
			}
		}
		return correo;
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------METODOS PARA CREAR LOS ATRIBUTOS DEL DEPARTAMENTO-------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-createRandomIdDepartamento.
// 2-createIdDepartamento.
// 3-createNombre.
// 4-createDirector.
// 5-createNumPersonas.
	
	
	
	public int createRandomIdDepartamento(DepartamentoArrayList listaDepartamentos)
	{
		int n = listaDepartamentos.getDepartamentosMaximos();
		logger.info("entramos en createRandomIdDepartamento con departamentos maximos: " + n);
		if(n <= listaDepartamentos.sizeListaDepartamento()){
			//n tiene que ser mayor que el numero de Departamentos en mi Lista de Departamentos
			n=n+20;
			listaDepartamentos.changeDepartamentosMaximos(n);
		}
		logger.info("departamentos maximos actuales: " + listaDepartamentos.getDepartamentosMaximos());
		int numeroIdDepartamento;
		while(true){
			numeroIdDepartamento = (int) (Math.random() * n) + 1;
			logger.info("intentamos idDepartamento: " + numeroIdDepartamento);
			if(listaDepartamentos.searchDepartamentoIdDepartamento(numeroIdDepartamento) == null){
				break;
			}
		}
		show.MostrarDepartamentoIdDepartamento(numeroIdDepartamento);
		return numeroIdDepartamento;
	}
	public int createIdDepartamento(DepartamentoArrayList listaDepartamentos, Scanner leer, boolean compruebaIdDepartamentoEnDepartamentoArrayList)
	{
		logger.info("entramos en createIdDepartamento con comprueba IdDepartamento: " + compruebaIdDepartamentoEnDepartamentoArrayList);
		int idDepartamento;
		String textoIdDepartamento;
		while(true){
			while(true){
				show.MostrarDepartamentoIntroduzcaIdDepartamento(listaDepartamentos.getDepartamentosMaximos());
				textoIdDepartamento = leer.nextLine();
				logger.info("idDepartamento introducido: " + textoIdDepartamento);
				if (comprueba.checkFormatIdDepartamento(textoIdDepartamento, listaDepartamentos)) {
					idDepartamento = Integer.parseInt(textoIdDepartamento.trim());
					break;					
				} else {
					show.MostrarErrorFormato();
				}
			}
			if(compruebaIdDepartamentoEnDepartamentoArrayList){
				if(listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento)==null){
					break;
				}else{
					show.MostrarDepartamentoErrorExisteIdDepartamento(idDepartamento);
				}
			}else{
				break;
			}
		}
		return idDepartamento;
	}
	public String createNombre(DepartamentoArrayList listaDepartamentos,Scanner leer,boolean compruebaNombreEnDepartamentoArrayList)
	{
		logger.info("entramos en createNombre con compruebaNombre: " + compruebaNombreEnDepartamentoArrayList);
		String nombre;
		while(true){
			while(true){
				show.MostrarDepartamentoIntroduzcaNombre();
				nombre = leer.nextLine();
				logger.info("introducido nombre: " + nombre);
				nombre = nombre.trim().toUpperCase();
				if(comprueba.checkFormatNombre(nombre)){
					break;
				}else{
					show.MostrarErrorFormato();
				}
			}
			if(compruebaNombreEnDepartamentoArrayList){
				if(listaDepartamentos.searchDepartamentoNombre(nombre)==null){
					break;
				}else{
					show.MostrarDepartamentoErrorExisteNombre(nombre);
				}
			}else{
				break;
			}
		}
		return nombre;	
	}
	public String createDirector(Scanner leer)
	{
		logger.info("entramos en create Director");
		String nombreDirector;
		String apellidoDirector;
		String director;
		String separador = "/";
		while(true){
			show.MostrarDepartamentoIntroduzcaNombreDirector();
			nombreDirector = leer.nextLine();
			logger.info("nombreDirector introducido: " + nombreDirector);
			nombreDirector = nombreDirector.trim().toUpperCase();
			show.MostrarDepartamentoIntroduzcaApellidoDirector();
			apellidoDirector = leer.nextLine();
			logger.info("apellidoDirector introducido: " + apellidoDirector);
			apellidoDirector = apellidoDirector.trim().toUpperCase();
			director = nombreDirector + separador + apellidoDirector;
			if(comprueba.checkFormatDirector(director)){
				break;
			}else{
				show.MostrarErrorFormato();
			}
		}
		return director;	
	}
	public int createNumPersonas(Scanner leer)
	{
		logger.info("entramos en createNumPersonas");
		Integer numPersonas;
		String textoNumPersonas;
		while(true){
			show.MostrarDepartamentoIntroduzcaNumPersonas();
			textoNumPersonas = leer.nextLine();
			logger.info("numPersonas introducido: ");
			if(comprueba.checkFormatNumPersonas(textoNumPersonas.trim())){
				numPersonas = Integer.parseInt(textoNumPersonas);
				break;
			}else{
				show.MostrarErrorFormato();
			}
		}
		return numPersonas;
	}
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//------------------------------------METODOS PARA CREAR LOS ATRIBUTOS DEL USUARIO_DEPARTAMENTO--------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-createIdUsuarioUD.
// 2-createIdDepartamentoUD.
// 3-createFechaUD.
// 4-convertTextoFechaToLong.
// 5-checkYearBisiesto.
	
	
	public int createIdUsuarioUD(UsuarioArrayList listaUsuarios, Scanner leer,boolean existe)
	{
		logger.info("entramos en createIdUsuarioUD con existe: " +existe);
		int idUsuario;
		String textoIdUsuario;
		while(true){
			if(existe){
				show.MostrarUsuarioDepartamentoIntroduzcaIdUsuario();
			}else{
				show.MostrarUsuarioDepartamentoIntroduzcaIdUsuarioNuevo();
			}
			textoIdUsuario = leer.nextLine();
			logger.info("idUsuarioUD introducido: " + textoIdUsuario);
			if (comprueba.checkFormatIdUsuario(textoIdUsuario, listaUsuarios)) {
				idUsuario = Integer.parseInt(textoIdUsuario.trim());
				break;					
			} else {
				show.MostrarErrorFormato();
			}
		}
		return idUsuario;
	}
	public int createIdDepartamentoUD(DepartamentoArrayList listaDepartamentos, Scanner leer,boolean existe)
	{
		logger.info("entramos en createIdDepartamentoUD con existe: "+ existe);
		int idDepartamento;
		String textoIdDepartamento;
		while(true){
			if(existe){
				show.MostrarUsuarioDepartamentoIntroduzcaIdDepartamento();
			}else{
				show.MostrarUsuarioDepartamentoIntroduzcaIdDepartamentoNuevo();
			}
			textoIdDepartamento = leer.nextLine();
			logger.info("IdDepartamentoUD introducido: " + textoIdDepartamento);
			if (comprueba.checkFormatIdDepartamento(textoIdDepartamento, listaDepartamentos)) {
				idDepartamento = Integer.parseInt(textoIdDepartamento.trim());
				break;					
			} else {
				show.MostrarErrorFormato();
			}
		}
		return idDepartamento;
	}
	public long createFechaUD(Scanner leer)
	{
		logger.info("entramos en createFecha");
		String textoFecha;
		long fecha;
		while (true){
			show.MostrarUsuarioDepartamentoIntroduzcaFecha();
			textoFecha = leer.nextLine();
			logger.info("fechaIntroducida: " +textoFecha);
			if(comprueba.checkFormatTextoFecha(textoFecha)){
				fecha = convertTextoFechaToLong(textoFecha);
				if(fecha != 0){
					break;
				}else{
					show.MostrarErrorFormato();
				}
			}else{
				show.MostrarErrorFormato();
			}
		}
		return fecha;
	}
	
	private static long convertTextoFechaToLong(String textoFecha)
	{
		logger.info("entramos en convertTextoFechaToLong con: " + textoFecha);
		String[] fecha= textoFecha.split("\\/");
		int dd = Integer.parseInt(fecha[0]);
		int mm = Integer.parseInt(fecha[1]);
		int yyyy = Integer.parseInt(fecha[2]);
		int yearEpoch = 1970;
		int ENE = 1, FEB = 2, MAR = 3, ABR = 4, MAY = 5, JUN = 6, JUL = 7, AGO = 8, SEP = 9, OCT = 10, NOV = 11, DIC = 12;
		long fechaEnMs = 0l;
		long diaEnMs = 86400000l;
		long mes28EnMs = diaEnMs * 28l;
		long mes29EnMs = diaEnMs * 29l;
		long mes30EnMs = diaEnMs * 30l;
		long mes31EnMs = diaEnMs * 31l;
		long yearEnMs = diaEnMs * 365l;
		long yearBisiestoEnMs = diaEnMs * 366l;
		for (int i = yearEpoch; i < yyyy; i++) {
			if(checkYearBisiesto(i)){
				fechaEnMs = fechaEnMs + yearBisiestoEnMs;
			}else{
				fechaEnMs = fechaEnMs + yearEnMs;
			}
		}
		for (int i = 1; i < mm; i++) {
			if( i == ENE || i == MAR || i == MAY || i == JUL || i == AGO || i == OCT || i == DIC){
				fechaEnMs = fechaEnMs + mes31EnMs;
			}else if (i == ABR || i == JUN || i == SEP || i == NOV){
				fechaEnMs = fechaEnMs + mes30EnMs;
			}else if (i == FEB){
				if(checkYearBisiesto(yyyy)){
					fechaEnMs = fechaEnMs + mes29EnMs;
				}else{
					fechaEnMs = fechaEnMs + mes28EnMs;
				}
			}else{
				return 0;
			}
		}
		for (int i = 1; i < dd; i++) {
			fechaEnMs = fechaEnMs + diaEnMs;
		}
		return fechaEnMs;
	}
	private static boolean checkYearBisiesto(int year)
	{
		if(year%4 == 0 && year%100 !=0 || year%400 ==0){
			return true;
		}else{
			return false;
		}
	}
	

}
