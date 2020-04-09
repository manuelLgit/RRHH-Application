package main;
import java.util.Scanner;


import entidadesMain.*;
import entidadesUtilidades.MostrarPorPantalla;

import org.apache.log4j.*;

public class MainProyecto {
	
	private static MostrarPorPantalla show = new MostrarPorPantalla();
	private static Scanner leer = new Scanner(System.in);
	private static UsuarioMain mainUsuarios = new UsuarioMain();
	private static DepartamentoMain mainDepartamento = new DepartamentoMain();
	private static UsuarioDepartamentoMain mainUsuarioDepartamento = new UsuarioDepartamentoMain();
	private static Logger logger  = Logger.getLogger("loggerProyecto");

	public static void main(String[] args) {
		
		
		
		//CARGAMOS EL FICHERO DE PROPERTIES

		String rutaArchivoUsuarios;
		String nombreArchivoUsuarios;
		String rutaArchivoDepartamentos;
		String nombreArchivoDepartamentos;
		String rutaArchivoUsuarioDepartamentos;
		String nombreArchivoUsuarioDepartamentos;
		try{
			SingletonProperties sp = SingletonProperties.getInstancia();
			rutaArchivoUsuarios = sp.getPropiedad("rutaArchivoUsuarios");
			nombreArchivoUsuarios = sp.getPropiedad("nombreArchivoUsuarios");
			rutaArchivoDepartamentos = sp.getPropiedad("rutaArchivoDepartamentos");
			nombreArchivoDepartamentos = sp.getPropiedad("nombreArchivoDepartamentos");
			rutaArchivoUsuarioDepartamentos = sp.getPropiedad("rutaArchivoUsuarioDepartamentos");
			nombreArchivoUsuarioDepartamentos = sp.getPropiedad("nombreArchivoUsuarioDepartamentos");
		}catch(Exception e){
			rutaArchivoUsuarios = ".";
			nombreArchivoUsuarios = "listaUsuarios.csv";
			rutaArchivoDepartamentos = ".";
			nombreArchivoDepartamentos = "listaDepartamentos.csv";
			rutaArchivoUsuarioDepartamentos = ".";
			nombreArchivoUsuarioDepartamentos = "listaUsuarioDepartamentos.csv";
			show.MostrarErrorLecturaFicheroProperties((rutaArchivoUsuarios + System.getProperty("file.separator")).concat(nombreArchivoUsuarios), (rutaArchivoDepartamentos+System.getProperty("file.separator")).concat(nombreArchivoDepartamentos), (rutaArchivoUsuarioDepartamentos + System.getProperty("file.separator")).concat(nombreArchivoUsuarioDepartamentos));
		}
		
		// CARGAMOS EL FICHERO DE PROPERTIES DE LOG4J
		PropertyConfigurator.configure(".".concat(System.getProperty("file.separator")).concat("properties").concat(System.getProperty("file.separator")).concat("log4j.properties"));
		boolean sigue = true;
		
		
		
		// MENU PRINCIPAL DEL PROGRAMA
	
		
		int eleccion;
		int n = 1;
		logger.info("Empieza el programa");
		while(sigue){
			logger.info("Entrada " + n + " al BUCLE PRINCIPAL");
			while(true){
				try{
					show.MostrarMenuPrincipal();
					String input = leer.nextLine();
					eleccion = Integer.parseInt(input); 
					break;
				}catch(NumberFormatException e){
					show.MostrarErrorOpcion();
				}
			}
			switch (eleccion) {
			case 1:
				logger.info("Entrada al mainUsuarios");
				mainUsuarios.mainUsuarios(leer,rutaArchivoUsuarios,nombreArchivoUsuarios,rutaArchivoDepartamentos,nombreArchivoDepartamentos,rutaArchivoUsuarioDepartamentos,nombreArchivoUsuarioDepartamentos);
				logger.info("Salida del mainUsuarios");
				break;
			case 2:
				logger.info("Entrada al mainDepartamentos");
				mainDepartamento.mainDepartamento(leer,rutaArchivoUsuarios,nombreArchivoUsuarios,rutaArchivoDepartamentos,nombreArchivoDepartamentos,rutaArchivoUsuarioDepartamentos,nombreArchivoUsuarioDepartamentos);
				logger.info("Salida del mainDepartamentos");
				break;
			case 3:
				logger.info("Entrada al mainUsuarioDepartamentos");
				mainUsuarioDepartamento.mainUsuarioDepartamento(leer,rutaArchivoUsuarios,nombreArchivoUsuarios,rutaArchivoDepartamentos,nombreArchivoDepartamentos,rutaArchivoUsuarioDepartamentos,nombreArchivoUsuarioDepartamentos);
				logger.info("Salida del mainUsuarioDepartamentos");
				break;
			case 4:
				leer.close();
				logger.info("Salida del Bucle Principal");
				sigue = false;
				break;
			}
			n++;
		}
		show.MostrarCerrandoPrograma();
		logger.info("FIN del PROGRAMA");
	}
}
