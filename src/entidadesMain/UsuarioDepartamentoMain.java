package entidadesMain;

import java.util.Scanner;
import java.util.regex.Pattern;

import entidades.*;
import entidadesArrayList.*;
import entidadesUtilidades.*;

import org.apache.log4j.Logger;

public class UsuarioDepartamentoMain {
	
	private static MostrarPorPantalla show = new MostrarPorPantalla();
	private static CreateAtributo crea = new CreateAtributo();
	private static CheckFormat comprueba = new CheckFormat();
	private static ReadAndWriteInFiles ryw = new ReadAndWriteInFiles();
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------MAIN USUARIO_DEPARTAMENTOS------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------
	// INDICE:
	// 1-Buscar UsuarioDepartamento.
	// 2-Añadir UsuarioDepartamento.
	// 3-Modificar UsuarioDepartamento.
	// 4-Borrar UsuarioDepartamento.
	// 5-Volver al Menu Principal y Guardar.

	public void mainUsuarioDepartamento(Scanner leer,String rutaArchivoUsuarios, String nombreArchivoUsuarios,String rutaArchivoDepartamentos, String nombreArchivoDepartamentos,String rutaArchivoUsuarioDepartamentos, String nombreArchivoUsuarioDepartamentos) {
		logger.info("Entramos en Menu UsuarioDepartamentos");
		UsuarioArrayList listaUsuarios = new UsuarioArrayList();
		DepartamentoArrayList listaDepartamentos = new DepartamentoArrayList();
		UsuarioDepartamentoArrayList listaRelaciones = new UsuarioDepartamentoArrayList();
		
		
		boolean sigue = true;
		boolean soloMuestra;
		UsuarioDepartamento relacionModificacion;
		UsuarioDepartamento relacionBorrar = new UsuarioDepartamento();
		
		logger.info("leemos datos previos");
		//Antes de nada cargamos datos previos de Usuarios, Departamentos y Relaciones (si hay)
		ryw.readDatosPreviosUsuario(listaUsuarios, leer, rutaArchivoUsuarios, nombreArchivoUsuarios);
		ryw.readDatosPreviosDepartamento(listaDepartamentos, leer, rutaArchivoDepartamentos, nombreArchivoDepartamentos);
		ryw.readDatosPreviosUsuarioDepartamento(listaRelaciones, listaUsuarios, listaDepartamentos, leer, rutaArchivoUsuarioDepartamentos, nombreArchivoUsuarioDepartamentos);
		logger.info("datos previos leidos");
		
		while(sigue){
			// 1 = Mostrar Menu de UsuarioDepartamento
			switch (chooseEleccion(leer, 1)) {
			case 1://Busqueda de UsuarioDepartamento
				logger.info("buscamos usuarioDepartamento");
				soloMuestra = true;
				menuSearchUsuarioDepartamento(leer, listaRelaciones, listaUsuarios, listaDepartamentos, soloMuestra);
				break;	
			case 2://Añadir UsuarioDepartamento en listaUsuarioDepartamentos
				logger.info("creamos nuevo usuarioDepartamento");
				if(listaUsuarios.sizeListaUsuarios() == 0 || listaDepartamentos.sizeListaDepartamento() == 0){
					show.MostrarUsuarioDepartamentoPrimeroAddUsuariosYDepartamentos();
					break;
				}
				int idUsuario;
				int idDepartamento;
				while (true){
					while (true){
						show.MostrarUsuarioDepartamentoDeseaMostrarIdUsuario();
						// 5 = Mostrar Menu IdUsuario IdDepartamento existentes
						if(chooseEleccion(leer, 5) == 1){
							logger.info("mostramos lista de idUsuario disponibles");
							show.MostrarUsuarioDepartamentoIdUsuarioDisponibles(listaUsuarios);
						}
						idUsuario = crea.createIdUsuarioUD(listaUsuarios,leer,true);
						logger.info("idUsuario introducido: " + idUsuario);
						if(listaUsuarios.searchUsuarioIdUsuario(idUsuario)!=null){
							logger.info("idUsuario correcto");
							break;
						}else{
							logger.warn("idUsuario incorrecto");
							show.MostrarUsuarioDepartamentoErrorNoExisteIdUsuario();
						}
					}
					while(true){
						show.MostrarUsuarioDepartamentoDeseaMostrarIdDepartamento();
						// 5 = Mostrar Menu IdUsuario IdDepartamento existentes
						if(chooseEleccion(leer, 5) == 1){
							logger.info("mostramos lista de idDepartamentos disponibles para idUsuario: " +idUsuario);
							show.MostrarUsuarioDepartamentoIdDepartamentoDisponiblesDadoIdUsuario(listaDepartamentos, listaRelaciones, idUsuario);
						}
						idDepartamento = crea.createIdDepartamentoUD(listaDepartamentos,leer,true);
						logger.info("idDepartamento introducido: " +idDepartamento);
						if(listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento)!=null){
							logger.info("idDepartametno correcto");
							break;
						}else{
							logger.warn("idDepartamento incorrecto");
							show.MostrarUsuarioDepartamentoErrorNoExisteIdDepartamento();
						}
					}
					if(comprueba.checkUsuarioDepartamentoRepetido(idUsuario, idDepartamento, listaRelaciones) == false){
						logger.info("relacion idUsuario: "+idUsuario+" - idDepartamento: " +idDepartamento +" correcta");
						break;
					}else{
						logger.warn("relacion idUsuario: "+idUsuario+" - idDepartamento: " +idDepartamento +"  INCORRECTA");
						show.MostrarUsuarioDepartamentoRepetido(idUsuario,idDepartamento);
					}
				}
				long fecha = crea.createFechaUD(leer);
				logger.info("fecha introducida: " + fecha );
				listaRelaciones.addUsuarioDepartamento(idUsuario, idDepartamento,fecha);
				logger.info("nueva relacion creada");
				show.MostrarUsuarioDepartamentoAdd();
				show.MostrarUsuarioDepartamento(listaRelaciones.getUsuarioDepartamento((listaRelaciones.sizeListaUsuarioDepartamentos()-1)));
				break;			
			case 3://Modificar UsuarioDepartamento
				logger.info("modificar usuarioDepartametno");
				show.MostrarUsuarioDepartamentoPrimeroBuscar();
				soloMuestra = false;
				relacionModificacion = menuSearchUsuarioDepartamento(leer, listaRelaciones, listaUsuarios, listaDepartamentos, soloMuestra);
				if(relacionModificacion != null){
					show.MostrarUsuarioDepartamentoEncontrado();
					show.MostrarUsuarioDepartamento(relacionModificacion);
					show.MostrarUsuarioDepartamentoAtributoModificar();
					// 3 = Mostrar Menu de Modificar Atributos de Departamento
					changeAtributosUsuarioDepartamento(relacionModificacion, listaUsuarios, listaDepartamentos,listaRelaciones, leer, chooseEleccion(leer, 3));
				}
				break;
			case 4://Borrar Usuario en listaDepartamentos
				logger.info("borrar usuarioDepartamento");
				show.MostrarUsuarioDepartamentoPrimeroBuscar();
				soloMuestra = false;
				relacionBorrar = menuSearchUsuarioDepartamento(leer, listaRelaciones, listaUsuarios, listaDepartamentos, soloMuestra);
				if(relacionBorrar != null){
					show.MostrarUsuarioDepartamentoEncontrado();
					show.MostrarUsuarioDepartamento(relacionBorrar);
					// 4 = Mostrar Menu de Borrar Departamento
					if(chooseEleccion(leer, 4) == 1 && listaRelaciones.deleteUsuarioDepartamento(relacionBorrar)){
						logger.info("usuarioDepartamento con idUsuario: " + relacionBorrar.getIdUsuario() + " y idDepartamento: " + relacionBorrar.getIdDepartamento() + " eliminada con EXITO"); 
						show.MostrarUsuarioDepartamentoBorrado();
					}else{
						logger.warn("usuarioDepartamento con idUsuario: " + relacionBorrar.getIdUsuario() + " y idDepartamento: " + relacionBorrar.getIdDepartamento() + " no ha podido ser eliminada"); 
						show.MostrarUsuarioDepartamentoNoBorrado();
					}
				}
				break;
			case 5://Volver a Menu Principal
				sigue = false;
				try{
					ryw.writeUsuarioArrayList(rutaArchivoUsuarios, nombreArchivoUsuarios, listaUsuarios);
					ryw.writeDepartamentoArrayList(rutaArchivoDepartamentos, nombreArchivoDepartamentos, listaDepartamentos);
					ryw.writeUsuarioDepartamentoArrayList(rutaArchivoUsuarioDepartamentos, nombreArchivoUsuarioDepartamentos, listaRelaciones);
					logger.info("Guardado con Exito");
				}catch(Exception e){
					logger.warn("ERROR en guardado en MainUsuarioDepartamentos");
					show.MostrarErrorEscrituraFichero();
				}
				break;
			
				
			case 6://Exportar a Excel
				show.MostrarUsuarioDepartamentoExportarExcel();
				String rutaArchivo = leer.nextLine();
				show.MostrarUsuarioDepartamentoExportarExcelNombre();
				String nombreArchivo = leer.nextLine();
				String rutaAbsoluta;
				if(rutaArchivo.contains("\\")){
					rutaAbsoluta = rutaArchivo.concat("\\").concat(nombreArchivo).concat(".xls");
				}else if(rutaArchivo.contains("/")){
					rutaAbsoluta = rutaArchivo.replaceAll("/", "//").concat("//").concat(nombreArchivo).concat(".xls");
				}else{
					rutaAbsoluta = rutaArchivo.concat(System.getProperty("file.separator")).concat(nombreArchivo).concat(".xls");
				}
				if(listaRelaciones.exportExcelUsuarioDepartamentoArrayList(rutaAbsoluta)){
					logger.info("Exportado a Excel con EXITO: "+rutaAbsoluta);
					show.MostrarUsuarioDepartamentoExportarExcelExito(rutaAbsoluta);
				}else{
					logger.warn("ERROR en exportacion a Excel la lista de Usuarios: "+rutaAbsoluta);
					show.MostrarUsuarioDepartamentoExportarExcelFracaso(rutaAbsoluta);
				}
				break;
			default:
				logger.warn("OPCION INCORRECTA en Menu Departamentos");
				show.MostrarErrorOpcion();
				break;
			}
		}

	}
	
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------	
	//---------------------METODOS PARA BUSCAR UN USUARIO-DEPARTAMENTO EN LA LISTA DE USUARIO_DEPARTAMENTOS------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------
	// INDICE:
	// 1-menuSearchUsuarioDepartamento.
	// 2-optionSearchUsuarioDepartamentoArrayList.
	// 3-searchDepartamentoArrayListIdUsuario.
	// 4-searchDepartamentoArrayListIdDepartamento.
	// 5-searchDepartamentoArrayListFecha.
	// 5-chooseOneUsuarioDepartamento.
	
	
	
	private static UsuarioDepartamento menuSearchUsuarioDepartamento(Scanner leer, UsuarioDepartamentoArrayList listaRelaciones, UsuarioArrayList listaUsuarios, DepartamentoArrayList listaDepartamentos, boolean soloMuestra)
	{
		logger.info("Entrada en menuSearchUsuarioDepartamento con soloMuestra: " + soloMuestra);
		UsuarioDepartamentoArrayList variasRelacionesBusqueda = new UsuarioDepartamentoArrayList();
		// 2 = Mostrar Menu de Busqueda
		int eleccion2 = chooseEleccion(leer, 2);
		//Las opciones 1, 2 y 3 nos devuelven un Objeto tipo UsuarioDepartamentoArrayList
		if(eleccion2 == 1 || eleccion2 == 2 || eleccion2 == 3){
			variasRelacionesBusqueda = optionSearchUsuarioDepartamentoArrayList(leer, listaRelaciones,listaUsuarios,listaDepartamentos, eleccion2);
			if(variasRelacionesBusqueda.sizeListaUsuarioDepartamentos() != 0){
				if(variasRelacionesBusqueda.sizeListaUsuarioDepartamentos() != 1){
					show.MostrarUsuarioDepartamentoEncontrado();
					show.MostrarUsuarioDepartamentoArrayList(variasRelacionesBusqueda);
					if(soloMuestra){
						logger.info("usuarioDepartamento nulo");
						return null;
					}else{
						logger.info("encontradas " + variasRelacionesBusqueda.sizeListaUsuarioDepartamentos() + " relaciones");
						return chooseOneUsuarioDepartamento(variasRelacionesBusqueda, leer);
					}
				}else{
					show.MostrarUsuarioDepartamentoEncontrado();
					show.MostrarUsuarioDepartamento(variasRelacionesBusqueda.getUsuarioDepartamento(0));
					if(soloMuestra){
						logger.info("usuarioDepartamento nulo");
						return null;
					}else{
						logger.info("relacion con idUsuario: "+ variasRelacionesBusqueda.getUsuarioDepartamento(0).getIdUsuario() + " e idDepartamento: "+variasRelacionesBusqueda.getUsuarioDepartamento(0).getIdDepartamento() + "Encontrada");
						return variasRelacionesBusqueda.getUsuarioDepartamento(0);
					}
				}
			}else{
				show.MostrarUsuarioDepartamentoNoEncontrado();
				logger.info("usuarioDepartamento no encontrado");
				return null;
			}
		}else if(eleccion2 == 7){
			logger.info("Mostramos listaUsuarioDepartamentos");
			show.MostrarUsuarioDepartamentoArrayList(listaRelaciones);
			return null;
		}else{
			logger.warn("Opcion incorrecta");
			show.MostrarErrorOpcion();
			return null;
		}
	}
	private static UsuarioDepartamentoArrayList optionSearchUsuarioDepartamentoArrayList(Scanner leer, UsuarioDepartamentoArrayList listaRelaciones, UsuarioArrayList listaUsuarios, DepartamentoArrayList listaDepartamentos, int eleccion)
	{
		logger.info("buscamos relaciones con eleccion: " +eleccion);
		switch (eleccion){
		case 1:
			show.MostrarUsuarioDepartamentoBuscarPor("IdUsuario");
			return searchUsuarioDepartamentoArrayListIdUsuario(listaRelaciones, listaUsuarios, leer);
		case 2:
			show.MostrarUsuarioDepartamentoBuscarPor("IdDepartamento");
			return searchUsuarioDepartamentoArrayListIdDepartamento(listaRelaciones, listaDepartamentos, leer);
		case 3:
			show.MostrarUsuarioDepartamentoBuscarPor("Fecha");
			return searchUsuarioDepartamentoArrayListFecha(listaRelaciones,leer);
		default:
			return null;
		}
	}
	private static UsuarioDepartamentoArrayList searchUsuarioDepartamentoArrayListIdUsuario(UsuarioDepartamentoArrayList listaRelaciones, UsuarioArrayList listaUsuarios, Scanner leer)
	{
		int idUsuario = crea.createIdUsuarioUD(listaUsuarios, leer,true);
		logger.info("buscamos relaciones por idUsuario: " +idUsuario);
		return listaRelaciones.searchUsuarioDepartamentoIdUsuario(idUsuario);
	}
	private static UsuarioDepartamentoArrayList searchUsuarioDepartamentoArrayListIdDepartamento(UsuarioDepartamentoArrayList listaRelaciones, DepartamentoArrayList listaDepartamentos, Scanner leer)
	{
		int idDepartamento = crea.createIdDepartamentoUD(listaDepartamentos, leer,true);
		logger.info("buscamos relacion por idDepartamento: " +idDepartamento);
		return listaRelaciones.searchUsuarioDepartamentoIdDepartamento(idDepartamento);
	}
	private static UsuarioDepartamentoArrayList searchUsuarioDepartamentoArrayListFecha(UsuarioDepartamentoArrayList listaRelaciones, Scanner leer)
	{
		long fecha = crea.createFechaUD(leer);
		logger.info("buscamos relacion por fecha: " +fecha);
		return listaRelaciones.searchUsuarioDepartamentoFecha(fecha);
	}
	private static UsuarioDepartamento chooseOneUsuarioDepartamento(UsuarioDepartamentoArrayList variosUsuarioDepartamentoBusqueda, Scanner leer)
	{
		int num;
		while(true){
			try{
				show.MostrarUsuarioDepartamentoElijaNumero();
				String input = leer.nextLine();
				logger.info("opcion introducida: " + input);
				num = Integer.parseInt(input);
				logger.info("chooseOneUsuarioDepartamento, opcion: "+num);
				if(num <= variosUsuarioDepartamentoBusqueda.sizeListaUsuarioDepartamentos() && num!=0){
					break;
				}else {
					logger.warn("Opcion invalida");
					show.MostrarErrorOpcion();
				}
			}catch(NumberFormatException e){
				logger.warn("Opcion invalida");
				show.MostrarErrorOpcion();
			}
		}
		logger.info("devolvemos relacion con idUsuario "+ variosUsuarioDepartamentoBusqueda.getUsuarioDepartamento(num-1).getIdUsuario() + " e idDepartamento " + variosUsuarioDepartamentoBusqueda.getUsuarioDepartamento(num-1).getIdDepartamento());
		return variosUsuarioDepartamentoBusqueda.getUsuarioDepartamento(num-1);
	}
	
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	//--------------------------------METODOS PARA MODIFICAR CIERTO ATRIBUTO DE USUARIO_DEPARTAMENTO-------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------
	// INDICE:
	// 1-changeAtributosUsuarioDepartamento.
	// 2-changeIdUsuario.
	// 3-changeIdDepartamento.
	// 4-changeFecha.
	
	
	private static void changeAtributosUsuarioDepartamento(UsuarioDepartamento relacionModificacion, UsuarioArrayList listaUsuarios, DepartamentoArrayList listaDepartamentos,UsuarioDepartamentoArrayList listaRelaciones, Scanner leer, int eleccion3)
	{
		logger.info(" changeAtributosUsuarioDepartamento eleccion: "+eleccion3);
		switch (eleccion3) {
		case 1:
			changeIdUsuario(relacionModificacion, listaUsuarios,listaRelaciones, leer);
			break;
		case 2:
			changeIdDepartamento(relacionModificacion, listaDepartamentos,listaRelaciones, leer);
			break;
		case 3:
			changeFecha(relacionModificacion, leer);
			break;
		default:
			show.MostrarErrorOpcion();
			break;
		}
	}
	private static void changeIdUsuario(UsuarioDepartamento relacionModificacion,UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer)
	{
		show.MostrarModificarAtributo("IdUsuario");
		logger.info("cambiamos idUsuario");
		Integer nuevoIdUsuario = new Integer(0);
		while (true){
			nuevoIdUsuario = crea.createIdUsuarioUD(listaUsuarios,leer,false);
			logger.info("nuevo idUsuario: " +nuevoIdUsuario);
			if(listaUsuarios.searchUsuarioIdUsuario(nuevoIdUsuario)==null){
				logger.info("idUsuario " + nuevoIdUsuario + " no esta repetido, ACEPTADO");
				break;
			}else{
				logger.warn("idUsuario " +nuevoIdUsuario + " ya existe, RECHAZADO");
				show.MostrarUsuarioDepartamentoErrorExisteIdUsuario();
			}
		}
		if(comprueba.checkUsuarioDepartamentoRepetido(nuevoIdUsuario, relacionModificacion.getIdDepartamento(), listaRelaciones) == false){
			logger.info("Relacion no existente, operacion aceptada");
			Integer antiguoIdUsuario = relacionModificacion.getIdUsuario();
			relacionModificacion.changeIdUsuario(nuevoIdUsuario);
			logger.info("idUsuario antiguo: " + antiguoIdUsuario + " idUsuario nuevo: " + nuevoIdUsuario);
			listaUsuarios.searchUsuarioIdUsuario(antiguoIdUsuario).changeIdUsuario(nuevoIdUsuario);
			show.MostrarModificarAtributoActualizado("IdUsuario",antiguoIdUsuario.toString(),nuevoIdUsuario.toString());
			logger.info("operacion de cambio de idUsuario en todo el Sistema, correcta");
		}else{
			logger.warn("Relacion EXISTENTE, operacion rechazada");
			show.MostrarUsuarioDepartamentoRepetido(nuevoIdUsuario, relacionModificacion.getIdDepartamento());
		}
		
	}
	private static void changeIdDepartamento(UsuarioDepartamento relacionModificacion,DepartamentoArrayList listaDepartamentos, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer)
	{
		show.MostrarModificarAtributo("IdDepartamento");
		Integer nuevoIdDepartamento = new Integer(0);
		logger.info("cambiamos idDepartamento");
		while(true){
			nuevoIdDepartamento = crea.createIdDepartamentoUD(listaDepartamentos,leer,false);
			logger.info("nuevo idDepartamento: " +nuevoIdDepartamento);
			if(listaDepartamentos.searchDepartamentoIdDepartamento(nuevoIdDepartamento)==null){
				logger.info("idDepartamento " + nuevoIdDepartamento + " no esta repetido, ACEPTADO");
				break;
			}else{
				logger.warn("idDepartamento " +nuevoIdDepartamento + " ya existe, RECHAZADO");
				show.MostrarUsuarioDepartamentoErrorExisteIdDepartamento();
			}
		}
		if(comprueba.checkUsuarioDepartamentoRepetido(relacionModificacion.getIdUsuario(), nuevoIdDepartamento, listaRelaciones) == false){
			logger.info("Relacion no existente, operacion aceptada");
			Integer antiguoIdDepartamento = relacionModificacion.getIdDepartamento();
			relacionModificacion.changeIdDepartamento(nuevoIdDepartamento);
			logger.info("idDepartamento antiguo: " + antiguoIdDepartamento + " idDepartamento nuevo: " + nuevoIdDepartamento);
			listaDepartamentos.searchDepartamentoIdDepartamento(antiguoIdDepartamento).changeIdDepartamento(nuevoIdDepartamento);
			show.MostrarModificarAtributoActualizado("IdDepartamento",antiguoIdDepartamento.toString(),nuevoIdDepartamento.toString());
			logger.info("operacion de cambio de idDepartamento en todo el Sistema, correcta");
		}else{
			logger.warn("Relacion EXISTENTE, operacion rechazada");
			show.MostrarUsuarioDepartamentoRepetido(relacionModificacion.getIdUsuario(), nuevoIdDepartamento);
		}
	}
	private static void changeFecha(UsuarioDepartamento relacionModificacion, Scanner leer)
	{
		show.MostrarModificarAtributo("Fecha");
		long nuevaFecha = crea.createFechaUD(leer);
		String antiguaFecha = relacionModificacion.getFechaTexto().toString();
		logger.info("Antiguo fecha: " +antiguaFecha+ " nueva fecha: "+ nuevaFecha);
		relacionModificacion.changeFecha(nuevaFecha);
		show.MostrarModificarAtributoActualizado("Fecha",antiguaFecha,relacionModificacion.getFechaTexto().toString());
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------METODOS PARA CONSEGUIR UNA ELECCION DE USUARIO_DEPARTAMENTO-------------------------------------
	//-----------------------------------------------------------------------------------------------------------------------------------
	// INDICE:
	// 1- chooseEleccion
	
	
	private static int chooseEleccion(Scanner leer, int eligeMenu)
	{
		logger.info("chooseEleccion con eligeMenu: "+eligeMenu);
		int eleccion = 0;
		String input;
		while(true){
				switch (eligeMenu) {
				case 1:
					show.MostrarMenuUsuarioDepartamento();
					break;
				case 2:
					show.MostrarMenuBusquedaUsuarioDepartamento();
					break;
				case 3:
					show.MostrarMenuModificarUsuarioDepartamento();
					break;
				case 4:
					show.MostrarMenuBorrarUsuarioDepartamento();
					break;
				case 5:
					show.MostrarMenuMostrarIdUsuarioOIdDepartamentoExistentes();
					break;
				default:
					break;
				}
				input = leer.nextLine();
				logger.info("eleccion escrita: " +input);
				if(Pattern.matches("[1-7]{1}", input)){
					break;
				}else{
					show.MostrarErrorOpcion();
				}
		}
		eleccion = Integer.parseInt(input); 
		return eleccion;
	}
	

}
