package entidadesMain;

import java.util.Scanner;
import java.util.regex.Pattern;

import entidadesArrayList.*;
import entidades.*;
import entidadesUtilidades.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class DepartamentoMain {
	
	private static MostrarPorPantalla show = new MostrarPorPantalla();
	private static CreateAtributo crea = new CreateAtributo();
	private static CheckFormat comprueba = new CheckFormat();
	private static ReadAndWriteInFiles ryw = new ReadAndWriteInFiles();
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
//-----------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------MAIN DEPARTAMENTOS---------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-Buscar Departamento.
// 2-Añadir Departamento.
// 3-Modificar Departamento.
// 4-Borrar Departamento.
// 5-Volver al Menu Principal y Guardar.
// 6-Exportar a Excel la lista de Departamentos.

	public void mainDepartamento(Scanner leer,String rutaArchivoUsuarios, String nombreArchivoUsuarios,String rutaArchivoDepartamentos, String nombreArchivoDepartamentos,String rutaArchivoUsuarioDepartamentos, String nombreArchivoUsuarioDepartamentos) {
		logger.info("Entramos a Menu Departamentos");
		
		UsuarioArrayList listaUsuarios = new UsuarioArrayList();
		DepartamentoArrayList listaDepartamentos = new DepartamentoArrayList();
		UsuarioDepartamentoArrayList listaRelaciones = new UsuarioDepartamentoArrayList();
		
		
		boolean sigue = true;
		boolean compruebaIdDepartamentoEnDepartamentoArrayList; //Asi, HABILITAMOS O DESHABILITAMOS la comprobacion de IdDepartamento existente en el metodo *\createIdDepartamento\*,
		boolean compruebaNombreEnDepartamentoArrayList; // Asi, HABILITAMOS O DESHABILITAMOS la comprobacion de Nombre existente en el metodo *\createNombre\*,
		boolean soloMuestra;
		Departamento departamentoBusqueda;
		Departamento departamentoModificacion;
		Departamento departamentoBorrar = new Departamento();
		
		
		//Antes de nada cargamos datos previos de Departamento y Relaciones (si hay)
		logger.info("leemos datos previos");
		ryw.readDatosPreviosUsuario(listaUsuarios, leer, rutaArchivoUsuarios, nombreArchivoUsuarios);
		ryw.readDatosPreviosDepartamento(listaDepartamentos, leer, rutaArchivoDepartamentos, nombreArchivoDepartamentos);
		ryw.readDatosPreviosUsuarioDepartamento(listaRelaciones, listaUsuarios, listaDepartamentos, leer, rutaArchivoUsuarioDepartamentos, nombreArchivoUsuarioDepartamentos);
		logger.info("datos previos leidos");
		
		//Actualizamos el Personal de cada departamento
		setNumPersonas(listaDepartamentos, listaRelaciones);
		logger.info("actualizado personal");
		
		
		while(sigue){
			// 1 = Mostrar Menu de Departamento
			switch (chooseEleccion(leer, 1)) {
			case 1://Busqueda de Departamento
				logger.info("Buscamos departamento");
				soloMuestra = true;
				compruebaIdDepartamentoEnDepartamentoArrayList = false;
				compruebaNombreEnDepartamentoArrayList = false;
				departamentoBusqueda = menuSearchDepartamento(leer, listaDepartamentos, soloMuestra,compruebaIdDepartamentoEnDepartamentoArrayList,compruebaNombreEnDepartamentoArrayList);
				if(departamentoBusqueda != null && chooseEleccion(leer, 8) ==1){
					try{
						printDepartamentoPdf(departamentoBusqueda, listaUsuarios, listaRelaciones);
						logger.info("informacion de departamento exportada a archivo pdf");
						show.MostrarExportacionPdf();
			        }catch(Exception e){
			        	logger.warn("no ha sido posible crear archivo pdf");
			            show.MostrarErrorEscrituraFichero();
			        }
				}
				break;
				
				
			case 2://Añadir Departamento en listaDepartamentos
				logger.info("Creamos Departamento");
				compruebaNombreEnDepartamentoArrayList = true;
				int idDepartamento = crea.createRandomIdDepartamento(listaDepartamentos);
				String nombre = crea.createNombre(listaDepartamentos,leer,compruebaNombreEnDepartamentoArrayList);
				String director = crea.createDirector(leer);
				int numPersonas = 0;
				listaDepartamentos.addDepartamento(idDepartamento, nombre, director, numPersonas);
				show.MostrarDepartamentoAdd();
				show.MostrarDepartamento(listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento));
				// 6 = Mostrar Menu de Añadir o No Usuario a Nuevo Departamento
				if(listaUsuarios.sizeListaUsuarios() != 0 && chooseEleccion(leer, 5) == 1){
					logger.info("Añadimos usuarios al deparamento: "+nombre);
					createRelacionesDepartamento(idDepartamento,listaDepartamentos, listaUsuarios, listaRelaciones, leer);
					setNumPersonas(listaDepartamentos, listaRelaciones);
				}
				break;
				
				
			case 3://Modificar Departamento en Departamentos
				logger.info("Modificamos info del Departamento");
				show.MostrarDepartamentoPrimeroBuscar();
				soloMuestra = false;
				compruebaIdDepartamentoEnDepartamentoArrayList = false;
				compruebaNombreEnDepartamentoArrayList = false;
				departamentoModificacion = menuSearchDepartamento(leer, listaDepartamentos, soloMuestra,compruebaIdDepartamentoEnDepartamentoArrayList,compruebaNombreEnDepartamentoArrayList);
				if(departamentoModificacion != null){
					show.MostrarDepartamentoEncontrado();
					show.MostrarDepartamento(departamentoModificacion);
					show.MostrarDepartamentoAtributoModificar();
					compruebaIdDepartamentoEnDepartamentoArrayList = true;
					compruebaNombreEnDepartamentoArrayList = true;
					// 3 = Mostrar Menu de Modificar Atributos de Departamento
					changeAtributosDepartamento(departamentoModificacion, listaDepartamentos,listaUsuarios, listaRelaciones, leer, chooseEleccion(leer, 3),compruebaIdDepartamentoEnDepartamentoArrayList,compruebaNombreEnDepartamentoArrayList);
				}
				break;
				
				
			case 4://Borrar Usuario en listaDepartamentos
				logger.info("Borramos un departamento");
				show.MostrarDepartamentoPrimeroBuscar();
				soloMuestra = false;
				compruebaIdDepartamentoEnDepartamentoArrayList = false;
				compruebaNombreEnDepartamentoArrayList = false;
				departamentoBorrar = menuSearchDepartamento(leer, listaDepartamentos, soloMuestra,compruebaIdDepartamentoEnDepartamentoArrayList,compruebaNombreEnDepartamentoArrayList);
				if(departamentoBorrar != null){
					show.MostrarDepartamentoEncontrado();
					show.MostrarDepartamento(departamentoBorrar);
					// 4 = Mostrar Menu de Borrar Departamento
					if(chooseEleccion(leer, 4) == 1 && listaDepartamentos.deleteDepartamento(departamentoBorrar)){
						ArrayList<UsuarioDepartamento> listaRelacionesBorrar = new ArrayList<UsuarioDepartamento>();
						listaRelacionesBorrar = listaRelaciones.searchUsuarioDepartamentoIdDepartamento(departamentoBorrar.getIdDepartamento()).getListaUsuarioDepartamentos();
						for (UsuarioDepartamento relacionBorrar : listaRelacionesBorrar) {
							logger.info("Borramos relacion de departamento: " + departamentoBorrar.getNombre());
							listaRelaciones.deleteUsuarioDepartamento(relacionBorrar);
						}
						logger.info("Departamento: "+departamentoBorrar.getNombre() + " BORRADO");
						show.MostrarDepartamentoBorrado();
					}else{
						logger.warn("Departamento: "+departamentoBorrar.getNombre() + " NO BORRADO");
						show.MostrarDepartamentoNoBorrado();
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
					show.MostrarErrorEscrituraFichero();
					logger.warn("ERROR en guardado en MainDepartamentos");
				}
				break;
				
				
			case 6://Exportar a Excel
				show.MostrarDepartamentoExportarExcel();
				String rutaArchivo = leer.nextLine();
				show.MostrarDepartamentoExportarExcelNombre();
				String nombreArchivo = leer.nextLine();
				String rutaAbsoluta;
				if(rutaArchivo.contains("\\")){
					rutaAbsoluta = rutaArchivo.concat("\\").concat(nombreArchivo).concat(".xls");
				}else if(rutaArchivo.contains("/")){
					rutaAbsoluta = rutaArchivo.replaceAll("/", "//").concat("//").concat(nombreArchivo).concat(".xls");
				}else{
					rutaAbsoluta = rutaArchivo.concat(System.getProperty("file.separator")).concat(nombreArchivo).concat(".xls");
				}
				if(listaDepartamentos.exportExcelDepartamentosArrayList(rutaAbsoluta)){
					show.MostrarDepartamentoExportarExcelExito(rutaAbsoluta);
					logger.info("Exportado a Excel con EXITO: "+rutaAbsoluta);
				}else{
					logger.warn("ERROR en exportacion a Excel la lista de Usuarios: "+rutaAbsoluta);
					show.MostrarDepartamentoExportarExcelFracaso(rutaAbsoluta);
				}
				break;
			
			default:
				logger.warn("OPCION INCORRECTA en Menu Departamentos");
				show.MostrarErrorOpcion();
				break;
			}
		}
	}
	

	
//------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------CREAR RELACIONES PARA NUEVO USUARIO----------------------------------------
//------------------------------------------------------------------------------------------------------------------------
// 1- setNumPersonas.
// 2- createRelacionesNuevoDepartamento.
// 3-deleteRelacionesDadoElDepartamento.
	
	
	
	private static void setNumPersonas(DepartamentoArrayList listaDepartamentos, UsuarioDepartamentoArrayList listaRelaciones)
	{
		logger.info("actualizamos el personal");
		Integer numPersonas = 0;
		for (Departamento departamento : listaDepartamentos.getListaDepartamentos()) {
			logger.info("Departamento: " + departamento.getNombre() + " con: " + departamento.getNumPersonas());
			numPersonas = listaRelaciones.searchUsuarioDepartamentoIdDepartamento(departamento.getIdDepartamento()).sizeListaUsuarioDepartamentos();
			if(comprueba.checkFormatNumPersonas(numPersonas.toString())){
				departamento.changeNumPersonas(numPersonas);
			}
			logger.info("Departamento: " + departamento.getNombre() + " ACTUALIZADO con: " + departamento.getNumPersonas());
		}
	}
	private static void createRelacionesDepartamento(int idDepartamento, DepartamentoArrayList listaDepartamentos, UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer)
	{
		logger.info("añadimos usuarios al departamento con id: "+idDepartamento);
		int idUsuario;
		int numUsuariosAdd = listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento).getNumPersonas();
		while(true){
			while(true){
				while (true){
					show.MostrarUsuarioDepartamentoDeseaMostrarIdUsuario();
					// 7 = Mostrar Menu de ver IdUsuarios EXISTENTES.
					if(chooseEleccion(leer, 6) == 1){
						show.MostrarUsuarioDepartamentoIdUsuarioDisponiblesDadoIdDepartamento(listaUsuarios, listaRelaciones, idDepartamento);
					}
					idUsuario = crea.createIdUsuarioUD(listaUsuarios, leer, true);
					if(listaUsuarios.searchUsuarioIdUsuario(idUsuario)!=null){
						break;
					}else{
						logger.warn("no existe idUsuario: " + idUsuario);
						show.MostrarUsuarioDepartamentoErrorNoExisteIdUsuario();
					}
				}
				if(comprueba.checkUsuarioDepartamentoRepetido(idUsuario, idDepartamento, listaRelaciones) == false){
					break;
				}else{
					logger.warn("Ya existe relacion: "+idUsuario + "/" + idDepartamento);
					show.MostrarUsuarioDepartamentoRepetido(idUsuario,idDepartamento);
				}
			}
			long fecha = crea.createFechaUD(leer);
			listaRelaciones.addUsuarioDepartamento(idUsuario, idDepartamento,fecha);
			logger.info("creamos relacion: " + idUsuario + " - "+ idDepartamento + " - " + fecha);
			numUsuariosAdd++;
			show.MostrarDepartamentoAddMasUsuarios(numUsuariosAdd);
			// 6 = Mostrar Menu de Añadir o No Usuario a Nuevo Departamento
			if (chooseEleccion(leer, 6) == 2) {
				break;
			}	
		}
	}
	private static void deleteRelacionesDadoElDepartamento(int idDepartamento,DepartamentoArrayList listaDepartamentos, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer)
	{
		logger.info("borramos relaciones de Departamento con id: " + idDepartamento);
		int num;
		show.MostrarUsuarioDepartamentoEncontrado();
		while(true){
			UsuarioDepartamentoArrayList listaRelacionesBorrar = listaRelaciones.searchUsuarioDepartamentoIdDepartamento(idDepartamento);
			if(listaRelacionesBorrar.sizeListaUsuarioDepartamentos() == 0){
				show.MostrarDepartamentoImposibleBorrarRelaciones();
				break;
			}
			show.MostrarUsuarioDepartamentoArrayList(listaRelacionesBorrar);
			while(true){
				try{
					show.MostrarUsuarioDepartamentoElijaNumero();
					String input = leer.nextLine();
					num = Integer.parseInt(input);
					if(num <= listaRelacionesBorrar.sizeListaUsuarioDepartamentos() && num!=0){
						break;
					}else {
						show.MostrarErrorOpcion();
					}
				}catch(NumberFormatException e){
					show.MostrarErrorOpcion();
				}
			}
			if(listaRelaciones.deleteUsuarioDepartamento(listaRelacionesBorrar.getUsuarioDepartamento(num-1))){
				logger.info("borrada la relacion: " + listaRelacionesBorrar.getUsuarioDepartamento(num-1).getIdUsuario() + " - " + listaRelacionesBorrar.getUsuarioDepartamento(num-1).getIdDepartamento() + " - " + listaRelacionesBorrar.getUsuarioDepartamento(num-1).getFechaTexto());
				show.MostrarUsuarioDepartamentoBorrado();
			}else{
				show.MostrarUsuarioDepartamentoNoBorrado();
			}
			show.MostrarDepartamentoBorrarMasUsuarios();
			// 6 = Mostrar Menu de Borrar o No Usuarios de Departamento
			if (chooseEleccion(leer, 6) == 2) {
				break;
			}
		}
			
		
	}

	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------	
//------------------------------METODOS PARA BUSCAR UN DEPARTAMENTO EN LA LISTA DE DEPARTAMENTOS-------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-menuSearchDepartamento.
// 2-optionSearchDepartamento.
// 3-optionSearchDepartamentoArrayList.
// 4-searchDepartamentoArrayListIdDepartamento.
// 5-searchDepartamentoArrayListNombre.
// 6-searchDepartamentoArrayListDirector.
// 7-searchDepartamentoArrayListNumPersonas.
// 8-chooseOneDepartamento.
	
	
	private static Departamento menuSearchDepartamento(Scanner leer, DepartamentoArrayList listaDepartamentos, boolean soloMuestra,boolean compruebaIdDepartamentoEnDepartamentoArrayList,boolean compruebaNombreEnDepartamentoArrayList)
	{
		logger.info("Entrada en menuSearchDepartamento con soloMuestra: " + soloMuestra);
		Departamento departamentoBusqueda = new Departamento();
		DepartamentoArrayList variosDepartamentosBusqueda = new DepartamentoArrayList();
		// 2 = Mostrar Menu de Busqueda
		int eleccion2 = chooseEleccion(leer, 2);
		//Las opciones 1 y 2 nos devuelven un Objeto tipo Departamento
		if(eleccion2 == 1 || eleccion2 == 2){
			departamentoBusqueda = optionSearchDepartamento(leer,listaDepartamentos, eleccion2,compruebaIdDepartamentoEnDepartamentoArrayList,compruebaNombreEnDepartamentoArrayList);
			if(departamentoBusqueda != null){
				if(soloMuestra){
					show.MostrarDepartamentoEncontrado();
					show.MostrarDepartamento(departamentoBusqueda);
					logger.info("departamento: " +departamentoBusqueda.getNombre() + " encontrado");
					return departamentoBusqueda;
				}else{
					logger.info("departamento con idDepartamento: "+departamentoBusqueda.getIdDepartamento() + "Encontrado");
					return departamentoBusqueda;
				}
			}else{
				show.MostrarDepartamentoNoEncontrado();
				logger.info("departamento no encontrado");
				return null;
			}
		//Las opciones 3 y 4 nos devuelven un Objeto tipo DepartamentoArrayList, debemos convertirlo a solo 1 Departamento
		}else if(eleccion2 == 3 || eleccion2 == 4){
			variosDepartamentosBusqueda = optionSearchDepartamentoArrayList(leer, listaDepartamentos, eleccion2);
			if(variosDepartamentosBusqueda.sizeListaDepartamento() != 0){
				if(variosDepartamentosBusqueda.sizeListaDepartamento() != 1){
					show.MostrarDepartamentoEncontrado();
					show.MostrarDepartamentoArrayList(variosDepartamentosBusqueda);
					if(soloMuestra){
						logger.info("departamento nulo");
						return null;
					}else{
						logger.info("Encontrados " + variosDepartamentosBusqueda.sizeListaDepartamento() + " departamentos");
						return chooseOneDepartamento(variosDepartamentosBusqueda, leer);
					}
				}else{
					show.MostrarDepartamentoEncontrado();
					show.MostrarDepartamento(variosDepartamentosBusqueda.getDepartamento(0));
					if(soloMuestra){
						logger.info("departamento: " + variosDepartamentosBusqueda.getDepartamento(0) + " encontrado");
						return variosDepartamentosBusqueda.getDepartamento(0);
					}else{
						logger.info("departamento con idDepartamento: "+ variosDepartamentosBusqueda.getDepartamento(0).getIdDepartamento() + "Encontrado");
						return variosDepartamentosBusqueda.getDepartamento(0);
					}
				}
			}else{
				show.MostrarDepartamentoNoEncontrado();
				logger.info("Departamento no encontrado");
				return null;
			}
		}else if(eleccion2 == 7){
			show.MostrarDepartamentoArrayList(listaDepartamentos);
			logger.info("Mostramos listaDepartamentos");
			return null;
		}else{
			show.MostrarErrorOpcion();
			logger.warn("Opcion incorrecta");
			return null;
		}
	}
	private static Departamento optionSearchDepartamento(Scanner leer, DepartamentoArrayList listaDepartamentos, int eleccion, boolean compruebaIdDepartamentoEnDepartamentoArrayList, boolean compruebaNombreEnDepartamentoArrayList)
	{
		logger.info("buscamos departamentos con eleccion: " +eleccion);
		switch (eleccion){
		case 1:
			show.MostrarDepartamentoBuscarPor("IdDepartamento");
			return searchDepartamentoArrayListIdDepartamento(listaDepartamentos,leer,compruebaIdDepartamentoEnDepartamentoArrayList);
		case 2:
			show.MostrarDepartamentoBuscarPor("Nombre");
			return searchDepartamentoArrayListNombre(listaDepartamentos,leer,compruebaNombreEnDepartamentoArrayList);
		default:
			return null;
		}
	}
	private static DepartamentoArrayList optionSearchDepartamentoArrayList(Scanner leer, DepartamentoArrayList listaDepartamentos, int eleccion)
	{
		logger.info("buscamos lista de departamentos con eleccion: " +eleccion);
		switch (eleccion){
		case 3:
			show.MostrarDepartamentoBuscarPor("Director");
			return searchDepartamentoArrayListDirector(listaDepartamentos,leer);
		case 4:
			show.MostrarDepartamentoBuscarPor("NumPersonas");
			return searchDepartamentoArrayListNumPersonas(listaDepartamentos,leer);
		default:
			return null;
		}
	}
	private static Departamento searchDepartamentoArrayListIdDepartamento(DepartamentoArrayList listaDepartamentos,Scanner leer,boolean compruebaIdDepartamentoEnDepartamentoArrayList)
	{
		int idDepartamento = crea.createIdDepartamento(listaDepartamentos, leer, compruebaIdDepartamentoEnDepartamentoArrayList);
		logger.info("buscamos departamento por idDepartamento: " +idDepartamento);
		return listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento);
	}
	private static Departamento searchDepartamentoArrayListNombre(DepartamentoArrayList listaDepartamentos,Scanner leer,boolean compruebaNombreEnDepartamentoArrayList)
	{
		String nombre = crea.createNombre(listaDepartamentos,leer, compruebaNombreEnDepartamentoArrayList);
		logger.info("buscamos departamento por nombre: "+ nombre);
		return listaDepartamentos.searchDepartamentoNombre(nombre);
	}
	private static DepartamentoArrayList searchDepartamentoArrayListDirector(DepartamentoArrayList listaDepartamentos,Scanner leer)
	{
		String director = crea.createDirector(leer);
		logger.info("buscamos departamento por director: "+ director);
		return listaDepartamentos.searchDepartamentoDirector(director);
	}
	private static DepartamentoArrayList searchDepartamentoArrayListNumPersonas(DepartamentoArrayList listaDepartamentos,Scanner leer)
	{
		int numPersonas = crea.createNumPersonas(leer);
		logger.info("buscamos departamento por personal: "+numPersonas);
		return listaDepartamentos.searchDepartamentoNumPersonas(numPersonas);
	}
	private static Departamento chooseOneDepartamento(DepartamentoArrayList variosDepartamentosBusqueda, Scanner leer)
	{
		int num;
		while(true){
			try{
				show.MostrarDepartamentoElijaNumero();
				String input = leer.nextLine();
				logger.info("opcion introducida: " + input);
				num = Integer.parseInt(input);
				logger.info("chooseOneDepartamento, opcion: "+num);
				if(num <= variosDepartamentosBusqueda.sizeListaDepartamento() && num!=0){
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
		logger.info("delovemos departamento con nombre: " + variosDepartamentosBusqueda.getDepartamento(num-1).getNombre());
		return variosDepartamentosBusqueda.getDepartamento(num-1);
	}

	

//-----------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------METODOS PARA MODIFICAR CIERTO ATRIBUTO DE DEPARTAMENTO----------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-changeAtributosDepartamento.
// 2-changeIdDepartamento.
// 3-changeNombre.
// 4-changeDirector.
// 5-changeNumPersonas.
	
	
	
	private static void changeAtributosDepartamento(Departamento departamentoModificacion, DepartamentoArrayList listaDepartamentos,UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer, int eleccion3,boolean compruebaIdDepartamentoEnDepartamentoArrayList,boolean compruebaNombreEnDepartamentoArrayList)
	{
		logger.info(" changeAtributosDepartamento eleccion: "+eleccion3);
		switch (eleccion3) {
		case 1:
			changeIdDepartamento(departamentoModificacion, listaDepartamentos, listaRelaciones, leer, compruebaIdDepartamentoEnDepartamentoArrayList);
			break;
		case 2:
			changeNombre(departamentoModificacion, listaDepartamentos, leer,compruebaNombreEnDepartamentoArrayList);
			break;
		case 3:
			changeDirector(departamentoModificacion,leer);
			break;
		case 4:
			changeNumPersonas(departamentoModificacion,leer,listaDepartamentos, listaUsuarios, listaRelaciones);
			break;
		default:
			show.MostrarErrorOpcion();
			break;
		}
	}
	private static void changeIdDepartamento(Departamento departamentoModificacion, DepartamentoArrayList listaDepartamentos, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer,boolean compruebaIdDepartamentoEnDepartamentoArrayList)
	{
		show.MostrarModificarAtributo("IdDepartamento");
		Integer nuevoIdDepartamento = crea.createIdDepartamento(listaDepartamentos, leer, compruebaIdDepartamentoEnDepartamentoArrayList);
		Integer antiguoIdDepartamento = departamentoModificacion.getIdDepartamento();
		departamentoModificacion.changeIdDepartamento(nuevoIdDepartamento);
		logger.info("Antiguo idDepartamento: " +antiguoIdDepartamento+ " nuevo IdDepartamento: "+ nuevoIdDepartamento);
		show.MostrarModificarAtributoActualizado("IdDepartamento",antiguoIdDepartamento.toString(),nuevoIdDepartamento.toString());
		for (UsuarioDepartamento relacion : listaRelaciones.getListaUsuarioDepartamentos()) {
			if(relacion.getIdDepartamento() == antiguoIdDepartamento){
				relacion.changeIdDepartamento(nuevoIdDepartamento);
				logger.info("IdDepartamento cambiado en relacion: " + relacion.getIdUsuario());
			}
		}
	}
	private static void changeNombre(Departamento departamentoModificacion, DepartamentoArrayList listaDepartamentos, Scanner leer,boolean compruebaNombreEnDepartamentoArrayList)
	{
		show.MostrarModificarAtributo("Nombre");
		String nuevoNombre = crea.createNombre(listaDepartamentos, leer, compruebaNombreEnDepartamentoArrayList);
		String antiguoNombre = departamentoModificacion.getNombre();
		logger.info("Antiguo nombre: " +antiguoNombre+ " nuevo nombre: "+ nuevoNombre);
		departamentoModificacion.changeNombre(nuevoNombre);
		show.MostrarModificarAtributoActualizado("Nombre",antiguoNombre,nuevoNombre);
	}
	private static void changeDirector(Departamento departamentoModificacion, Scanner leer)
	{
		show.MostrarModificarAtributo("Director");
		String nuevoDirector = crea.createDirector(leer);
		String antiguoDirector = departamentoModificacion.getDirector();
		logger.info("Antiguo director: " +antiguoDirector+ " nuevo director: "+ nuevoDirector);
		departamentoModificacion.changeDirector(nuevoDirector);
		show.MostrarModificarAtributoActualizado("Director",antiguoDirector,nuevoDirector);
	}
	private static void changeNumPersonas(Departamento departamentoModificacion, Scanner leer,DepartamentoArrayList listaDepartamentos, UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones)
	{
		show.MostrarModificarAtributo("Personal");
		show.MostrarModificarNumPersonas(departamentoModificacion.getNombre());
		Integer antiguoNumPersonas = departamentoModificacion.getNumPersonas();
		logger.info("antiguo personal: "+antiguoNumPersonas);
		int eleccion = chooseEleccion(leer, 7);
		if(eleccion == 1){
			logger.info("Creamos relaciones para: " + departamentoModificacion.getNombre());
			createRelacionesDepartamento(departamentoModificacion.getIdDepartamento(),listaDepartamentos, listaUsuarios, listaRelaciones, leer);
		}else if(eleccion == 2){
			logger.info("eliminamos relaciones para: " + departamentoModificacion.getNombre());
			deleteRelacionesDadoElDepartamento(departamentoModificacion.getIdDepartamento(), listaDepartamentos, listaRelaciones, leer);
		}		
		setNumPersonas(listaDepartamentos, listaRelaciones);
		Integer nuevoNumPersonas = departamentoModificacion.getNumPersonas();
		logger.info("nuevo personal: " +nuevoNumPersonas);
		departamentoModificacion.changeNumPersonas(nuevoNumPersonas);
		show.MostrarModificarAtributoActualizado("Personal",antiguoNumPersonas.toString(),nuevoNumPersonas.toString());
	}


//-----------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------METODOS PARA IMPRIMIR INFORMACION DE USUARIO---------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1- printDepartamentoPdf
		
		private static void printDepartamentoPdf(Departamento departamento, UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones) throws Exception
		{
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("DEPARTAMENTO-"+departamento.getIdDepartamento()+".pdf"));
	        document.open();
			String espacio = "\n";
			String cabecera = "INFORMACION DEL DEPARTAMENTO";
			String idDepartamento = "ID.DEPARTAMENTO: " + departamento.getIdDepartamento();
			String nombre = "NOMBRE: " + departamento.getNombre();
			String director = "DIRECTOR: " + departamento.getDirector();
			String numPersonas = "PERSONAL: " + departamento.getNumPersonas();
			String infoDepartamento =cabecera + espacio + espacio + idDepartamento + espacio + nombre + espacio + director + espacio + numPersonas +espacio+espacio+espacio;
			document.add(new Paragraph(infoDepartamento));
			
			String cabeceraUsuarios = "USUARIOS QUE PERTENECEN AL DEPARTAMENTO" + espacio + espacio;
			document.add(new Paragraph(cabeceraUsuarios));
			
			
			for (UsuarioDepartamento relacion : listaRelaciones.getListaUsuarioDepartamentos()) {
				if(relacion.getIdDepartamento() == departamento.getIdDepartamento()){
					listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario());
					String infoRelacion = "Usuario " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getApellidos() + " se unio al DEPARTAMENTO en " + relacion.getFechaTexto().toString();
					String info = "INFORMACION USUARIO";
					String idUsuario = "ID.USUARIO: " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getIdUsuario();
					String apellidos = "APELLIDOS: " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getApellidos();
					String dni = "DNI: " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getDni();
					String sexo = "SEXO: " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getSexo().name();
					String altura = "ALTURA: " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getAltura();
					String correo = "CORREO: " + listaUsuarios.searchUsuarioIdUsuario(relacion.getIdUsuario()).getCorreo();
					String infoUsuario = infoRelacion + espacio + espacio +info+espacio+ idUsuario + espacio + apellidos + espacio + dni + espacio + sexo + espacio + altura + espacio + correo +espacio+espacio;
					document.add(new Paragraph(infoUsuario));
				}
			}
			document.close();
		}	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------METODOS PARA CONSEGUIR UNA ELECCION DE DEPARTAMENTO-----------------------------------------
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
					show.MostrarMenuDepartamento();
					break;
				case 2:
					show.MostrarMenuBusquedaDepartamento();
					break;
				case 3:
					show.MostrarMenuModificarDepartamento();
					break;
				case 4:
					show.MostrarMenuBorrarDepartamento();
					break;
				case 5:
					show.MostrarMenuAddUsuarioNuevoDepartamento();
					break;
				case 6:
					show.MostrarMenuMostrarIdUsuarioOIdDepartamentoExistentes();
					break;
				case 7:
					show.MostrarMenuDepartamentoCambiaNumPersonas();
					break;
				case 8:
					show.MostrarMenuExportarPdf();
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
