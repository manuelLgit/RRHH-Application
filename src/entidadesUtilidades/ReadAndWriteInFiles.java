package entidadesUtilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import entidades.Departamento;
import entidades.Usuario;
import entidades.UsuarioDepartamento;
import entidadesArrayList.DepartamentoArrayList;
import entidadesArrayList.UsuarioArrayList;
import entidadesArrayList.UsuarioDepartamentoArrayList;

import org.apache.log4j.Logger;

public class ReadAndWriteInFiles {
	
	private static MostrarPorPantalla show = new MostrarPorPantalla();
	private static CreateAtributo crea = new CreateAtributo();
	private static CheckFormat comprueba = new CheckFormat();
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	public void writeUsuarioArrayList(String rutaArchivoUsuarios, String nombreArchivoUsuarios, UsuarioArrayList listaUsuarios) throws Exception
	{
		logger.info("escribimos la lista de Usuarios en: " +rutaArchivoUsuarios + " con nombre: "+ nombreArchivoUsuarios);
		File fichero = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		fichero = new File(rutaArchivoUsuarios,nombreArchivoUsuarios);
		fw = new FileWriter(fichero);
		pw = new PrintWriter(fw);
		for (Usuario usuario : listaUsuarios.getListaUsuarios()) {
			pw.println(usuario.getIdUsuario() +";"+ usuario.getApellidos() +";"+ usuario.getDni() +";"+ usuario.getSexo().toString() +";"+ usuario.getAltura() +";"+ usuario.getCorreo());
		}
		fw.close();
	}
	public void writeDepartamentoArrayList(String rutaArchivoDepartamentos, String nombreArchivoDepartamentos,DepartamentoArrayList listaDepartamentos) throws Exception
	{
		logger.info("escribimos la lista de Departamentos en: " +rutaArchivoDepartamentos + " con nombre: "+ nombreArchivoDepartamentos);
		File fichero = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		fichero = new File(rutaArchivoDepartamentos,nombreArchivoDepartamentos);
		fw = new FileWriter(fichero);
		pw = new PrintWriter(fw);
		for (Departamento departamento : listaDepartamentos.getListaDepartamentos()) {
			pw.println(departamento.getIdDepartamento() +";"+ departamento.getNombre() +";"+ departamento.getDirector() +";"+ departamento.getNumPersonas());
		}
		fw.close();
	}
	public void writeUsuarioDepartamentoArrayList(String rutaArchivoUsuarioDepartamentos, String nombreArchivoUsuarioDepartamentos,UsuarioDepartamentoArrayList listaRelaciones) throws Exception
	{
		logger.info("escribimos la lista de Relaciones en: " +rutaArchivoUsuarioDepartamentos + " con nombre: "+ nombreArchivoUsuarioDepartamentos);
		File fichero = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		fichero = new File(rutaArchivoUsuarioDepartamentos,nombreArchivoUsuarioDepartamentos);
		fw = new FileWriter(fichero);
		pw = new PrintWriter(fw);
		for (UsuarioDepartamento usuarioDepartamento : listaRelaciones.getListaUsuarioDepartamentos()) {
			pw.println(usuarioDepartamento.getIdUsuario() +";"+ usuarioDepartamento.getIdDepartamento() +";"+ usuarioDepartamento.getFecha());
		}
		fw.close();
	}
	
	
	private static ArrayList<String[]> readArrayList(String rutaArchivo, String nombreArchivo,final int numCampos)
	{
		logger.info("leemos un ArrayList : " +rutaArchivo+ " con nombre: "+ nombreArchivo + " y numero de campos: " + numCampos);
		String linea;
		int cuentaLineas=1;
		int cuentaErrores=0;
		ArrayList<String[]> datosEnBruto = new ArrayList<String[]>();
		ArrayList<Integer> lineasError = new ArrayList<Integer>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(rutaArchivo,nombreArchivo)));
			while((linea = br.readLine())!=null) {
				try{
					String[] datos = linea.split(";");
					for (int j = 0; j < datos.length; j++) {
						datos[j] = datos[j].trim();
					}
					if(datos.length == numCampos){
						datosEnBruto.add(datos);
					}else{
						lineasError.add(cuentaLineas);
						cuentaErrores++;
					}
					cuentaLineas++;
				}catch(Exception e){
					lineasError.add(cuentaLineas);
					cuentaErrores++;
					cuentaLineas++;
				}
			}
			if(cuentaErrores != 0){
				informeErroresIrrecuperablesEnLectura(cuentaErrores, lineasError,(rutaArchivo + "/").concat(nombreArchivo));
			}
	        br.close();
	        return datosEnBruto;
		}catch (Exception e){
			return null;
		}	
	}
	private static void informeErroresIrrecuperablesEnLectura(int cuentaErrores, ArrayList<Integer> lineasError, String archivo)
	{
		
		show.MostrarInicioInformeErroresIrrecuperables();
		for (Integer lineasConError : lineasError) {
			logger.warn("hay errores");
			show.MostrarDatosCorrompidos(archivo, lineasConError);
		}
		show.MostrarFinInformeErroresIrrecuperables(archivo, cuentaErrores);
	}
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------METODOS PARA LECTURA Y ESCRITURA EN USUARIOS---------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------
	// INDICE:
	// 1- readDatosPreviosUsuario
	// 2- fixErrores
	// 3- fixAtributosErroneos
		
		
	public void readDatosPreviosUsuario(UsuarioArrayList listaUsuarios, Scanner leer, String rutaArchivoUsuarios, String nombreArchivoUsuarios)
	{
		boolean compruebaIdUsuario, compruebaApellidos, compruebaDni, compruebaSexo, compruebaAltura, compruebaCorreo;
		boolean compruebaIdUsuarioRepetido = true, compruebaDniRepetido = true, compruebaCorreoRepetido = true;
		int cuentaErrores = 0;
		int cuentaLineas = 1;
		ArrayList<String[]> datosEnBruto = readArrayList(rutaArchivoUsuarios, nombreArchivoUsuarios,6);
		ArrayList<Integer> indiceError = new ArrayList<Integer>();
		try{
			for (String[] datosUsuario : datosEnBruto) {
				compruebaIdUsuario = comprueba.checkFormatIdUsuario(datosUsuario[0], listaUsuarios);
				if(compruebaIdUsuario && (listaUsuarios.sizeListaUsuarios() > 1)){
					compruebaIdUsuarioRepetido = (listaUsuarios.searchUsuarioIdUsuario(Integer.parseInt(datosUsuario[0])) == null);
				}
				compruebaApellidos = comprueba.checkFormatApellidos(datosUsuario[1]);
				compruebaDni = comprueba.checkFormatDni(datosUsuario[2]);
				if(compruebaDni && (listaUsuarios.sizeListaUsuarios() > 1)){
					compruebaDniRepetido = (listaUsuarios.searchUsuarioDni(datosUsuario[2]) == null);
				}
				compruebaSexo = comprueba.checkFormatSexo(datosUsuario[3]);
				compruebaAltura = comprueba.checkFormatAltura(datosUsuario[4]);
				compruebaCorreo = comprueba.checkFormatCorreo(datosUsuario[5]);
				if(compruebaCorreo && (listaUsuarios.sizeListaUsuarios() > 1)){
					compruebaCorreoRepetido = (listaUsuarios.searchUsuarioCorreo(datosUsuario[5]) == null);
				}
				if (compruebaIdUsuario && compruebaApellidos && compruebaDni && compruebaSexo && compruebaAltura && compruebaCorreo) {
					//Si todos los datos tienen el formato pertinente, comprobamos si estan repetidos
					if(compruebaIdUsuarioRepetido && compruebaDniRepetido && compruebaCorreoRepetido){
						int idUsuario = Integer.parseInt(datosUsuario[0]);
						float alturaUsuario = Float.parseFloat(datosUsuario[4]);
						listaUsuarios.addUsuario(idUsuario,datosUsuario[1],datosUsuario[2],datosUsuario[3],alturaUsuario,datosUsuario[5]);
					}else{
						if(cuentaErrores==0){
							show.MostrarInicioInformeErroresSolucionables();
						}
						show.MostrarUsuarioRepetido(cuentaLineas,compruebaIdUsuarioRepetido, compruebaDniRepetido, compruebaCorreoRepetido, (rutaArchivoUsuarios +"/").concat(nombreArchivoUsuarios));
						indiceError.add(cuentaLineas-1);
						cuentaErrores++;
					}
				} else {
					//Si un Usuario, marcamos cual o cuales son los atributos del Usuario que no son correctos
					if(cuentaErrores==0){
						show.MostrarInicioInformeErroresSolucionables();
					}
					show.MostrarUsuarioCorrompido(cuentaLineas,compruebaIdUsuario, compruebaApellidos, compruebaDni, compruebaSexo, compruebaAltura, compruebaCorreo, (rutaArchivoUsuarios +"/").concat(nombreArchivoUsuarios));
					indiceError.add(cuentaLineas-1);
					cuentaErrores++;
					if(compruebaIdUsuarioRepetido == false || compruebaDniRepetido == false || compruebaCorreoRepetido == false){
						show.MostrarUsuarioRepetido(cuentaLineas,compruebaIdUsuarioRepetido, compruebaDniRepetido, compruebaCorreoRepetido, (rutaArchivoUsuarios +"/").concat(nombreArchivoUsuarios));
						cuentaErrores++;						
					}
				}
				cuentaLineas++;
			}
		}catch(Exception e){
			show.MostrarUsuarioNoDatosPrevios();
		}
		if(cuentaErrores!=0){
			show.MostrarFinInformeErroresSolucionables((rutaArchivoUsuarios +"/").concat(nombreArchivoUsuarios),cuentaErrores);
			// 1 = Mostrar Menu de Subsanacion Errores de Usuario
			if(chooseEleccion(leer,1) == 1){
				fixErrores(datosEnBruto, indiceError, leer, listaUsuarios);
			}
		}
		try{
			writeUsuarioArrayList(rutaArchivoUsuarios, nombreArchivoUsuarios, listaUsuarios);
		}catch(Exception e){
			show.MostrarErrorEscrituraFichero();
		}
		
	}
	private static void fixErrores(ArrayList<String[]> datosEnBruto, ArrayList<Integer> indiceError, Scanner leer, UsuarioArrayList listaUsuarios)
	{
		ArrayList<String[]> datosDepurados = new ArrayList<String[]>();
		for (Integer indice : indiceError) {
			show.MostrarUsuarioCabeceraErrores(indice);
			datosDepurados.add(fixAtributosErroneos(datosEnBruto,indice,listaUsuarios,leer));
		}
		for (String[] datosUsuario : datosDepurados) {
			int idUsuario = Integer.parseInt(datosUsuario[0]);
			float alturaUsuario = Float.parseFloat(datosUsuario[4]);
			listaUsuarios.addUsuario(idUsuario,datosUsuario[1],datosUsuario[2],datosUsuario[3],alturaUsuario,datosUsuario[5]);
		}
		show.MostrarUsuarioFinErrores();	
	}
	private static String[] fixAtributosErroneos(ArrayList<String[]> datosEnBruto,int indice, UsuarioArrayList listaUsuarios,Scanner leer)
	{
		boolean compruebaIdUsuarioEnUsuarioArrayList = true;
		boolean compruebaDniEnUsuarioArrayList = true;
		boolean compruebaCorreoEnUsuarioArrayList = true;
		if(comprueba.checkFormatIdUsuario(datosEnBruto.get(indice)[0], listaUsuarios) == false){
			show.MostrarErrorLecturaFormatoAtributo("IdUsuario",datosEnBruto.get(indice)[0]);
			Integer idUsuarioFixed = crea.createIdUsuario(listaUsuarios, leer, compruebaIdUsuarioEnUsuarioArrayList);
			datosEnBruto.get(indice)[0] = idUsuarioFixed.toString();
		}else if(listaUsuarios.searchUsuarioIdUsuario(Integer.parseInt(datosEnBruto.get(indice)[0])) != null){
			show.MostrarErrorLecturaAtributoRepetido("IdUsuario",datosEnBruto.get(indice)[0]);
			Integer idUsuarioFixed = crea.createIdUsuario(listaUsuarios, leer, compruebaIdUsuarioEnUsuarioArrayList);
			datosEnBruto.get(indice)[0] = idUsuarioFixed.toString();
		}
		if(comprueba.checkFormatApellidos(datosEnBruto.get(indice)[1]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Apellidos",datosEnBruto.get(indice)[1]);
			String apellidosFixed = crea.createApellidos(leer);
			datosEnBruto.get(indice)[1] = apellidosFixed;
		}
		if(comprueba.checkFormatDni(datosEnBruto.get(indice)[2]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Dni",datosEnBruto.get(indice)[2]);
			String dniFixed = crea.createDni(listaUsuarios, leer, compruebaDniEnUsuarioArrayList);
			datosEnBruto.get(indice)[2] = dniFixed;
		}else if(listaUsuarios.searchUsuarioDni(datosEnBruto.get(indice)[2]) != null){
			show.MostrarErrorLecturaAtributoRepetido("Dni",datosEnBruto.get(indice)[2]);
			String dniFixed = crea.createDni(listaUsuarios, leer, compruebaDniEnUsuarioArrayList);
			datosEnBruto.get(indice)[2] = dniFixed;
		}
		if(comprueba.checkFormatSexo(datosEnBruto.get(indice)[3]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Sexo",datosEnBruto.get(indice)[3]);
			String sexoFixed = crea.createSexo(leer);
			datosEnBruto.get(indice)[3] = sexoFixed;
		}
		if(comprueba.checkFormatAltura(datosEnBruto.get(indice)[4]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Altura",datosEnBruto.get(indice)[4]);
			Float alturaFixed = crea.createAltura(leer);
			datosEnBruto.get(indice)[4] = alturaFixed.toString();
		}
		if(comprueba.checkFormatCorreo(datosEnBruto.get(indice)[5]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Dni",datosEnBruto.get(indice)[5]);
			String correoFixed = crea.createCorreo(listaUsuarios, leer, compruebaCorreoEnUsuarioArrayList);
			datosEnBruto.get(indice)[5] = correoFixed;
		}else if(listaUsuarios.searchUsuarioCorreo(datosEnBruto.get(indice)[5]) != null){
			show.MostrarErrorLecturaAtributoRepetido("Dni",datosEnBruto.get(indice)[5]);
			String correoFixed = crea.createCorreo(listaUsuarios, leer, compruebaCorreoEnUsuarioArrayList);
			datosEnBruto.get(indice)[5] = correoFixed;
		}
		return datosEnBruto.get(indice);
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------METODOS PARA LECTURA Y ESCRITURA EN DEPARTAMENTOS------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1- readDatosPreviosDepartamento
// 2- fixErrores
// 3- fixAtributosErroneos
		
		
	
	
	public void readDatosPreviosDepartamento(DepartamentoArrayList listaDepartamentos, Scanner leer,String rutaArchivoDepartamentos, String nombreArchivoDepartamentos)
	{
		boolean compruebaIdDepartamento, compruebaNombre, compruebaDirector, compruebaNumPersonas;
		boolean compruebaIdDepartamentoRepetido = true, compruebaNombreRepetido = true;
		int cuentaErrores = 0;
		int cuentaLineas = 1;
		ArrayList<String[]> datosEnBruto = readArrayList(rutaArchivoDepartamentos, nombreArchivoDepartamentos,4);
		ArrayList<Integer> indiceError = new ArrayList<Integer>();
		try{
			for (String[] datosDepartamento : datosEnBruto) {
				compruebaIdDepartamento = comprueba.checkFormatIdDepartamento(datosDepartamento[0], listaDepartamentos);
				if(compruebaIdDepartamento && (listaDepartamentos.sizeListaDepartamento() > 0)){
					compruebaIdDepartamentoRepetido = (listaDepartamentos.searchDepartamentoIdDepartamento(Integer.parseInt(datosDepartamento[0])) == null);
				}
				compruebaNombre = comprueba.checkFormatNombre(datosDepartamento[1]);
				if(compruebaNombre && (listaDepartamentos.sizeListaDepartamento() > 0)){
					compruebaNombreRepetido = (listaDepartamentos.searchDepartamentoNombre(datosDepartamento[1]) == null);
				}
				compruebaDirector = comprueba.checkFormatDirector(datosDepartamento[2]);
				compruebaNumPersonas = comprueba.checkFormatNumPersonas(datosDepartamento[3]);
				if (compruebaIdDepartamento && compruebaNombre && compruebaDirector && compruebaNumPersonas) {
					//Si todos los datos tienen el formato pertinente, comprobamos si estan repetidos
					if(compruebaIdDepartamentoRepetido && compruebaNombreRepetido){
						//Si tampoco estan repetidos, introducimos los datos en la lista de Departamentos
						int idDepartamento = Integer.parseInt(datosDepartamento[0]);
						int numPersonas = Integer.parseInt(datosDepartamento[3]);
						listaDepartamentos.addDepartamento(idDepartamento,datosDepartamento[1],datosDepartamento[2],numPersonas);
					}else{
						//Si hay datos de IdDepartamento o de Nombre que estan repetidos, marcamos cual
						if(cuentaErrores==0){
							show.MostrarInicioInformeErroresSolucionables();
						}
						show.MostrarDepartamentoRepetido(cuentaLineas,compruebaIdDepartamentoRepetido, compruebaNombreRepetido, (rutaArchivoDepartamentos + "/").concat(nombreArchivoDepartamentos));
						indiceError.add(cuentaLineas-1);
						cuentaErrores++;
					}
				} else {
					//Si hay datos de Departamento que no tienen el formato pertinente, marcamos cual.
					if(cuentaErrores==0){
						show.MostrarInicioInformeErroresSolucionables();
					}
					show.MostrarDepartamentoCorrompido(cuentaLineas,compruebaIdDepartamento, compruebaNombre, compruebaDirector, compruebaNumPersonas, (rutaArchivoDepartamentos + "/").concat(nombreArchivoDepartamentos));
					indiceError.add(cuentaLineas-1);
					cuentaErrores++;
					if(compruebaIdDepartamentoRepetido == false || compruebaNombreRepetido == false){
						show.MostrarDepartamentoRepetido(cuentaLineas,compruebaIdDepartamentoRepetido, compruebaNombreRepetido, (rutaArchivoDepartamentos + "/").concat(nombreArchivoDepartamentos));
						cuentaErrores++;						
					}
				}
				cuentaLineas++;
			}
		}catch(Exception e){
			show.MostrarDepartamentoNoDatosPrevios();
		}
		if(cuentaErrores!=0){
			show.MostrarFinInformeErroresSolucionables((rutaArchivoDepartamentos + "/").concat(nombreArchivoDepartamentos),cuentaErrores);
			// 2 = Mostrar Menu de Subsanacion Errores Departamento
			if(chooseEleccion(leer, 2) == 1){
				fixErrores(datosEnBruto, indiceError, leer, listaDepartamentos);
			}
		}
		try{
			writeDepartamentoArrayList(rutaArchivoDepartamentos, nombreArchivoDepartamentos, listaDepartamentos);
		}catch(Exception e){
			show.MostrarErrorEscrituraFichero();
		}
		
	}
	private static void fixErrores(ArrayList<String[]> datosEnBruto, ArrayList<Integer> indiceError, Scanner leer, DepartamentoArrayList listaDepartamentos)
	{
		ArrayList<String[]> datosDepurados = new ArrayList<String[]>();
		for (Integer indice : indiceError) {
			show.MostrarDepartamentoCabeceraErrores(indice);
			datosDepurados.add(fixAtributosErroneos(datosEnBruto,indice,listaDepartamentos,leer));
		}
		for (String[] datosDepartamento : datosDepurados) {
			int idDepartamento = Integer.parseInt(datosDepartamento[0]);
			int numPersonas = Integer.parseInt(datosDepartamento[3]);
			listaDepartamentos.addDepartamento(idDepartamento,datosDepartamento[1],datosDepartamento[2],numPersonas);
		}
		show.MostrarDepartamentoFinErrores();	
	}
	private static String[] fixAtributosErroneos(ArrayList<String[]> datosEnBruto,int indice, DepartamentoArrayList listaDepartamentos,Scanner leer)
	{
		boolean compruebaIdDepartamentoEnDepartamentoArrayList = true;
		boolean compruebaNombreEnDepartamentoArrayList = true;
		if(comprueba.checkFormatIdDepartamento(datosEnBruto.get(indice)[0], listaDepartamentos) == false){
			show.MostrarErrorLecturaFormatoAtributo("IdDepartamento",datosEnBruto.get(indice)[0]);
			Integer idDepartamentoFixed = crea.createIdDepartamento(listaDepartamentos, leer, compruebaIdDepartamentoEnDepartamentoArrayList);
			datosEnBruto.get(indice)[0] = idDepartamentoFixed.toString();
		}else if(listaDepartamentos.searchDepartamentoIdDepartamento(Integer.parseInt(datosEnBruto.get(indice)[0])) != null){
			show.MostrarErrorLecturaAtributoRepetido("IdDepartamento",datosEnBruto.get(indice)[0]);
			Integer idDepartamentoFixed = crea.createIdDepartamento(listaDepartamentos, leer, compruebaIdDepartamentoEnDepartamentoArrayList);
			datosEnBruto.get(indice)[0] = idDepartamentoFixed.toString();
		}
		if(comprueba.checkFormatNombre(datosEnBruto.get(indice)[1]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Nombre",datosEnBruto.get(indice)[1]);
			String nombreFixed = crea.createNombre(listaDepartamentos, leer, compruebaNombreEnDepartamentoArrayList);
			datosEnBruto.get(indice)[1] = nombreFixed;
		}else if(listaDepartamentos.searchDepartamentoNombre(datosEnBruto.get(indice)[1]) != null){
			show.MostrarErrorLecturaAtributoRepetido("Nombre",datosEnBruto.get(indice)[1]);
			String nombreFixed = crea.createNombre(listaDepartamentos, leer, compruebaNombreEnDepartamentoArrayList);
			datosEnBruto.get(indice)[1] = nombreFixed;
		}
		if(comprueba.checkFormatDirector(datosEnBruto.get(indice)[2]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Director",datosEnBruto.get(indice)[2]);
			String directorFixed = crea.createDirector(leer);
			datosEnBruto.get(indice)[2] = directorFixed;
		}
		if(comprueba.checkFormatNumPersonas(datosEnBruto.get(indice)[3]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Personal",datosEnBruto.get(indice)[3]);
			Integer numPersonasFixed = 0;
			datosEnBruto.get(indice)[3] = numPersonasFixed.toString();
		}
		return datosEnBruto.get(indice);
	}
	
	
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------METODOS PARA LECTURA Y ESCRITURA EN RELACIONES---------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1- readDatosPreviosUsuarioDepartamento
// 2- fixErrores
// 3- fixAtributosErroneos

	
	
	
	public void readDatosPreviosUsuarioDepartamento(UsuarioDepartamentoArrayList listaRelaciones, UsuarioArrayList listaUsuarios, DepartamentoArrayList listaDepartamentos, Scanner leer,String rutaArchivoUsuarioDepartamentos, String nombreArchivoUsuarioDepartamentos)
	{
		boolean compruebaIdUsuario, compruebaIdDepartamento, compruebaFecha,compruebaIdUsuarioExiste, compruebaIdDepartamentoExite;
		int cuentaErrores = 0;
		int cuentaLineas = 1;
		ArrayList<String[]> datosEnBruto = readArrayList(rutaArchivoUsuarioDepartamentos, nombreArchivoUsuarioDepartamentos,3);
		ArrayList<Integer> indiceError = new ArrayList<Integer>();
		try{
			for (String[] datosRelaciones : datosEnBruto) {
				compruebaIdUsuario = comprueba.checkFormatIdUsuario(datosRelaciones[0], listaUsuarios);
				compruebaIdDepartamento = comprueba.checkFormatIdDepartamento (datosRelaciones[1], listaDepartamentos);
				compruebaFecha = comprueba.checkFormatFecha(datosRelaciones[2]);
				if (compruebaIdUsuario && compruebaIdDepartamento && compruebaFecha) {
					//Si todos los datos tienen el formato pertinente, introducimos los datos en la lista de Relaciones
					int idUsuario = Integer.parseInt(datosRelaciones[0]);
					int idDepartamento = Integer.parseInt(datosRelaciones[1]);
					compruebaIdUsuarioExiste = (listaUsuarios.searchUsuarioIdUsuario(idUsuario) != null);
					compruebaIdDepartamentoExite = (listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento)!=null);
					long fecha = Long.parseLong(datosRelaciones[2]);
					//Si existe los IdUsuario e IdDepartamento y no esta repetida la relacion, añadimos a la lista de Relaciones una instancia.
					if(compruebaIdUsuarioExiste && compruebaIdDepartamentoExite && comprueba.checkUsuarioDepartamentoRepetido(idUsuario, idDepartamento, listaRelaciones) == false){
						listaRelaciones.addUsuarioDepartamento(idUsuario,idDepartamento,fecha);
					}
				} else {
					//Si hay datos de Relaciones que no tienen el formato pertinente, marcamos cual.
					if(cuentaErrores==0){
						show.MostrarInicioInformeErroresSolucionables();
					}
					show.MostrarUsuarioDepartamentoCorrompido(cuentaLineas,compruebaIdUsuario, compruebaIdDepartamento, compruebaFecha, (rutaArchivoUsuarioDepartamentos + "/").concat(nombreArchivoUsuarioDepartamentos));
					indiceError.add(cuentaLineas-1);
					cuentaErrores++;					
				}
				cuentaLineas++;
			}
		}catch(Exception e){
			show.MostrarUsuarioDepartamentoNoDatosPrevios();
		}
		if(cuentaErrores!=0){
			show.MostrarFinInformeErroresSolucionables((rutaArchivoUsuarioDepartamentos + "/").concat(nombreArchivoUsuarioDepartamentos),cuentaErrores);
			// 5 = Mostrar Menu de Subsanacion Errores UsuarioDepartamento
			if(chooseEleccion(leer, 3) == 1){
				fixErrores(datosEnBruto, indiceError, leer, listaRelaciones, listaUsuarios, listaDepartamentos);
			}
		}
		try{
			writeUsuarioDepartamentoArrayList(rutaArchivoUsuarioDepartamentos, nombreArchivoUsuarioDepartamentos, listaRelaciones);;
		}catch(Exception e){
			show.MostrarErrorEscrituraFichero();
		}
		
	}
	private static void fixErrores(ArrayList<String[]> datosEnBruto, ArrayList<Integer> indiceError, Scanner leer, UsuarioDepartamentoArrayList listaRelaciones, UsuarioArrayList listaUsuarios, DepartamentoArrayList listaDepartamentos)
	{
		ArrayList<String[]> datosDepurados = new ArrayList<String[]>();
		for (Integer indice : indiceError) {
			show.MostrarUsuarioDepartamentoCabeceraErrores(indice);
			datosDepurados.add(fixAtributosErroneos(datosEnBruto,indice,listaUsuarios,listaDepartamentos,leer));
		}
		for (String[] datosRelaciones : datosDepurados) {
			int idUsuario = Integer.parseInt(datosRelaciones[0]);
			int idDepartamento = Integer.parseInt(datosRelaciones[1]);
			long fecha = Long.parseLong(datosRelaciones[2]);
			if(comprueba.checkUsuarioDepartamentoRepetido(idUsuario, idDepartamento, listaRelaciones) == false){
				listaRelaciones.addUsuarioDepartamento(idUsuario,idDepartamento,fecha);
			}			
		}
		show.MostrarUsuarioDepartamentoFinErrores();	
	}
	private static String[] fixAtributosErroneos(ArrayList<String[]> datosEnBruto,int indice, UsuarioArrayList listaUsuarios, DepartamentoArrayList listaDepartamentos, Scanner leer)
	{
		System.out.println(datosEnBruto.get(indice)[0]+"  "+datosEnBruto.get(indice)[1]+"  "+datosEnBruto.get(indice)[2]);
		if(comprueba.checkFormatIdUsuario(datosEnBruto.get(indice)[0], listaUsuarios) == false){
			show.MostrarErrorLecturaFormatoAtributo("IdUsuario",datosEnBruto.get(indice)[0]);
			Integer idUsuarioFixed;
			while (true){
				show.MostrarUsuarioDepartamentoDeseaMostrarIdUsuario();
				// 4 = Mostrar Menu IdUsuario IdDepartamento existentes
				if(chooseEleccion(leer, 4) == 1){
					show.MostrarUsuarioDepartamentoIdUsuarioDisponibles(listaUsuarios);
				}
				idUsuarioFixed = crea.createIdUsuarioUD(listaUsuarios,leer,true);
				if(listaUsuarios.searchUsuarioIdUsuario(idUsuarioFixed)!=null){
					break;
				}else{
					show.MostrarUsuarioDepartamentoErrorNoExisteIdUsuario();
				}
			}
			datosEnBruto.get(indice)[0] = idUsuarioFixed.toString();
		}
		if(comprueba.checkFormatIdDepartamento(datosEnBruto.get(indice)[1], listaDepartamentos) == false){
			show.MostrarErrorLecturaFormatoAtributo("IdDepartamento",datosEnBruto.get(indice)[1]);
			Integer idDepartamentoFixed;
			while(true){
				show.MostrarUsuarioDepartamentoDeseaMostrarIdDepartamento();
				// 4 = Mostrar Menu IdUsuario IdDepartamento existentes
				if(chooseEleccion(leer, 4) == 1){
					show.MostrarUsuarioDepartamentoIdDepartamentoDisponibles(listaDepartamentos);
				}
				idDepartamentoFixed = crea.createIdDepartamentoUD(listaDepartamentos,leer,true);
				if(listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamentoFixed)!=null){
					break;
				}else{
					show.MostrarUsuarioDepartamentoErrorNoExisteIdDepartamento();
				}
			}
			datosEnBruto.get(indice)[1] = idDepartamentoFixed.toString();
		}
		if(comprueba.checkFormatFecha(datosEnBruto.get(indice)[2]) == false){
			show.MostrarErrorLecturaFormatoAtributo("Fecha",datosEnBruto.get(indice)[2]);
			Long fechaFixed = crea.createFechaUD(leer);
			datosEnBruto.get(indice)[2] = fechaFixed.toString();
		}
		return datosEnBruto.get(indice);
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------METODOS PARA CONSEGUIR UNA ELECCION--------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1- chooseEleccion

	private static int chooseEleccion(Scanner leer, int eligeMenu)
	{
		int eleccion = 0;
		String input;
		while(true){
			try{
				switch (eligeMenu) {
				case 1:
					show.MostrarMenuSubsanacionErroresUsuario();
					break;
				case 2:
					show.MostrarMenuSubsanacionErroresDepartamento();
					break;
				case 3:
					show.MostrarMenuSubsanacionErroresUsuarioDepartamento();
					break;
				case 4:
					show.MostrarMenuMostrarIdUsuarioOIdDepartamentoExistentes();
				default:
					break;
				}
				input = leer.nextLine();
				eleccion = Integer.parseInt(input); 
				break;
			}catch(NumberFormatException e){
				show.MostrarErrorOpcion();
			}
		}
		return eleccion;
	}
	

}
