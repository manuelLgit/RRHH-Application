package entidadesMain;

import entidadesArrayList.*;
import entidades.*;
import entidades.Usuario.TipoSexo;
import entidadesUtilidades.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class UsuarioMain {
	
	private static MostrarPorPantalla show = new MostrarPorPantalla();
	private static CreateAtributo crea = new CreateAtributo();
	private static CheckFormat comprueba = new CheckFormat();
	private static ReadAndWriteInFiles ryw = new ReadAndWriteInFiles();
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------MAIN USUARIOS------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-Buscar Usuario.
// 2-Añadir Usuario.
// 3-Modificar Usuario.
// 4-Borrar Usuario.
// 5-Volver al Menu Principal y Guardar.
// 6-Exportar a Excel
	

	public void mainUsuarios(Scanner leer,String rutaArchivoUsuarios, String nombreArchivoUsuarios,String rutaArchivoDepartamentos, String nombreArchivoDepartamentos,String rutaArchivoUsuarioDepartamentos, String nombreArchivoUsuarioDepartamentos) 
	{
		logger.info("Entramos en Menu Usuarios");
		UsuarioArrayList listaUsuarios = new UsuarioArrayList();
		DepartamentoArrayList listaDepartamentos = new DepartamentoArrayList();
		UsuarioDepartamentoArrayList listaRelaciones = new UsuarioDepartamentoArrayList();
		
		
		boolean sigue = true;
		boolean compruebaIdUsuarioEnUsuarioArrayList; //Asi, HABILITAMOS O DESHABILITAMOS la comprobacion de IdUsuario existente en el metodo *\createIdUsuario\*,
		boolean compruebaDniEnUsuarioArrayList; // Asi, HABILITAMOS O DESHABILITAMOS la comprobacion de Dni existente en el metodo *\createDni\*,
		boolean compruebaCorreoEnUsuarioArrayList; // Asi, HABILITAMOS O DESHABILITAMOS la comprobacion de Correo existente en el metodo *\createCorreo\*,
		boolean soloMuestra;
		Usuario usuarioBusqueda;
		Usuario usuarioModificacion;
		Usuario usuarioBorrar = new Usuario();
		
		
		//Antes de nada, cargamos datos anteriores (si hay)
		logger.info("Leemos los datos Previos");
		ryw.readDatosPreviosUsuario(listaUsuarios, leer, rutaArchivoUsuarios, nombreArchivoUsuarios);
		ryw.readDatosPreviosDepartamento(listaDepartamentos, leer, rutaArchivoDepartamentos, nombreArchivoDepartamentos);
		ryw.readDatosPreviosUsuarioDepartamento(listaRelaciones, listaUsuarios, listaDepartamentos, leer,rutaArchivoUsuarioDepartamentos,nombreArchivoUsuarioDepartamentos);
		logger.info("Datos previos leidos");
		
		while(sigue){
			// 1 = Mostrar Menu de Usuario
			logger.info("Bucle del Main Usuarios");
			switch (chooseEleccion(leer, 1)) {
			case 1://Busqueda de Usuario
				logger.info("Busqueda Usuarios");
				soloMuestra = true;
				compruebaIdUsuarioEnUsuarioArrayList = false;
				compruebaDniEnUsuarioArrayList = false;
				compruebaCorreoEnUsuarioArrayList = false;
				usuarioBusqueda = menuSearchUsuario(leer, listaUsuarios, soloMuestra,compruebaIdUsuarioEnUsuarioArrayList,compruebaDniEnUsuarioArrayList,compruebaCorreoEnUsuarioArrayList);
				if(usuarioBusqueda != null && chooseEleccion(leer, 7) ==1){
					try{
						printUsuarioPdf(usuarioBusqueda, listaDepartamentos, listaRelaciones);
						logger.info("se ha creado pdf con inforamcion de usuario: " + usuarioBusqueda.getIdUsuario());
						show.MostrarExportacionPdf();
			        }catch(Exception e){
			        	logger.warn("no se ha podido crear archivo .pdf");
			            show.MostrarErrorEscrituraFichero();
			        }
				}
				
				break;
				
				
			case 2://Añadir Usuario en listaUsuarios
				logger.info("Añadir Usuarios");
				compruebaDniEnUsuarioArrayList = true; 
				compruebaCorreoEnUsuarioArrayList = true; 
				int idUsuario = crea.createRandomIdUsuario(listaUsuarios);
				String apellidos = crea.createApellidos(leer);
				String dni = crea.createDni(listaUsuarios,leer,compruebaDniEnUsuarioArrayList);
				String sexo = crea.createSexo(leer);
				float altura = crea.createAltura(leer);
				String correo = crea.createCorreo(listaUsuarios,leer,compruebaCorreoEnUsuarioArrayList);
				listaUsuarios.addUsuario(idUsuario, apellidos, dni, sexo, altura, correo);
				show.MostrarUsuarioAdd();
				show.MostrarUsuario(listaUsuarios.searchUsuarioIdUsuario(idUsuario));
				// 5 = Mostrar Menu añadir Usuario Nuevo a Departamento Existente
				logger.info("Nuevo Usuario con IdUsuario: " + idUsuario);
				if(listaDepartamentos.sizeListaDepartamento() != 0 && chooseEleccion(leer, 5) == 1){
					logger.info("Vincular Nuevo Usuario con Departamento");
					createRelacionesNuevoUsuario(idUsuario, listaDepartamentos, listaRelaciones, leer);
				}
				break;
				
				
			case 3://Modificar Usuario en listaUsuarios
				logger.info("Modificar Usuarios");
				show.MostrarUsuarioPrimeroBuscar();
				soloMuestra = false;
				compruebaIdUsuarioEnUsuarioArrayList = false;
				compruebaDniEnUsuarioArrayList = false;
				compruebaCorreoEnUsuarioArrayList = false;
				usuarioModificacion = menuSearchUsuario(leer, listaUsuarios, soloMuestra,compruebaIdUsuarioEnUsuarioArrayList,compruebaDniEnUsuarioArrayList,compruebaCorreoEnUsuarioArrayList);
				if(usuarioModificacion != null){
					logger.info("Usuario para Modificacion Encontrado. IdUsuario: " +usuarioModificacion.getIdUsuario());
					show.MostrarUsuarioEncontrado();
					show.MostrarUsuario(usuarioModificacion);
					show.MostrarUsuarioAtributoModificar();
					compruebaIdUsuarioEnUsuarioArrayList = true;
					compruebaDniEnUsuarioArrayList = true;
					compruebaCorreoEnUsuarioArrayList = true;
					// 3 = Mostrar Menu de Modificar Atributos de Usuario
					changeAtributosUsuario(usuarioModificacion, listaUsuarios, listaRelaciones, leer, chooseEleccion(leer, 3),compruebaIdUsuarioEnUsuarioArrayList,compruebaDniEnUsuarioArrayList,compruebaCorreoEnUsuarioArrayList);
				}
				break;
				
				
			case 4://Borrar Usuario en listaUsuarios
				logger.info("Borrar Usuarios");
				show.MostrarUsuarioPrimeroBuscar();
				soloMuestra = false;
				compruebaIdUsuarioEnUsuarioArrayList = false;
				compruebaDniEnUsuarioArrayList = false;
				compruebaCorreoEnUsuarioArrayList = false;
				usuarioBorrar = menuSearchUsuario(leer, listaUsuarios, soloMuestra,compruebaIdUsuarioEnUsuarioArrayList,compruebaDniEnUsuarioArrayList,compruebaCorreoEnUsuarioArrayList);
				if(usuarioBorrar != null){
					logger.info("Usuario para Eliminacion Encontrado. IdUsuario: " +usuarioBorrar.getIdUsuario());
					show.MostrarUsuarioEncontrado();
					show.MostrarUsuario(usuarioBorrar);
					// 4 = Mostrar Menu de Borrar Usuario
					if(chooseEleccion(leer, 4) == 1 && listaUsuarios.deleteUsuario(usuarioBorrar)){
						logger.info("Usuario con IdUsuario: " + usuarioBorrar.getIdUsuario() +" ELIMINADO");
						ArrayList<UsuarioDepartamento> listaRelacionesBorrar = new ArrayList<UsuarioDepartamento>();
						listaRelacionesBorrar = listaRelaciones.searchUsuarioDepartamentoIdUsuario(usuarioBorrar.getIdUsuario()).getListaUsuarioDepartamentos();
						for (UsuarioDepartamento relacionBorrar : listaRelacionesBorrar) {
							listaRelaciones.deleteUsuarioDepartamento(relacionBorrar);
							logger.info("Relacion: " + relacionBorrar.getIdUsuario() +" "+relacionBorrar.getIdDepartamento()+" "+relacionBorrar.getFechaTexto() + "ELIMINADA");
						}
						logger.info("Relaciones de Usuario con IdUsuario: " + usuarioBorrar.getIdUsuario() +" ELIMINADAS");
						show.MostrarUsuarioBorrado();
					}else{
						logger.info("Usuario con IdUsuario: " + usuarioBorrar.getIdUsuario() +" NO ELIMINADO");
						show.MostrarUsuarioNoBorrado();
					}
				}
				break;
				
				
			case 5://Volver a Menu Principal
				logger.info("Guardar y Salir al Menu Principal");
				try{
					ryw.writeUsuarioArrayList(rutaArchivoUsuarios, nombreArchivoUsuarios, listaUsuarios);
					ryw.writeDepartamentoArrayList(rutaArchivoDepartamentos, nombreArchivoDepartamentos, listaDepartamentos);
					ryw.writeUsuarioDepartamentoArrayList(rutaArchivoUsuarioDepartamentos, nombreArchivoUsuarioDepartamentos, listaRelaciones);
					logger.info("Guardado con Exito");
				}catch(Exception e){
					show.MostrarErrorEscrituraFichero();
					logger.warn("ERROR en guardado en MainUsuarios");
				}
				sigue = false;
				break;
				
				
			case 6:
				logger.info("Exportar a Excel lista de Usuarios");
				show.MostrarUsuarioExportarExcel();
				String rutaArchivo = leer.nextLine();
				show.MostrarUsuarioExportarExcelNombre();
				String nombreArchivo = leer.nextLine();
				String rutaAbsoluta;
				if(rutaArchivo.contains("\\")){
					rutaAbsoluta = rutaArchivo.concat("\\").concat(nombreArchivo).concat(".xls");
				}else if(rutaArchivo.contains("/")){
					rutaAbsoluta = rutaArchivo.replaceAll("/", "//").concat("//").concat(nombreArchivo).concat(".xls");
				}else{
					rutaAbsoluta = rutaArchivo.concat(System.getProperty("file.separator")).concat(nombreArchivo).concat(".xls");
				}
				if(listaUsuarios.exportExcelUsuarioArrayList(rutaAbsoluta)){
					logger.info("Exportado a Excel con EXITO: "+rutaAbsoluta);
					show.MostrarUsuarioExportarExcelExito(rutaAbsoluta);
				}else{
					logger.warn("ERROR en exportacion a Excel la lista de Usuarios: "+rutaAbsoluta);
					show.MostrarUsuarioExportarExcelFracaso(rutaAbsoluta);
				}
				break;
				
				
			default:
				logger.warn("OPCION INCORRECTA en Menu Usuarios");
				show.MostrarErrorOpcion();
				break;
			}
		}
	}
	
	
//-------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------CREAR RELACIONES PARA NUEVO USUARIO-----------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------
// Indice:
// 1-createRelacionesNuevoUsuario
// 2-setNumPersonas
	
	private static void createRelacionesNuevoUsuario(int idUsuario, DepartamentoArrayList listaDepartamentos, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer)
	{
		logger.info("Crear relaciones para nuevo usuario IdUsuario: " + idUsuario);
		int idDepartamento;
		while(true){
			while (true){
				show.MostrarUsuarioDepartamentoDeseaMostrarIdDepartamento();
				// 6 = Mostrar Menu Mostrar IdDepartamentos Existentes
				if(chooseEleccion(leer, 6) == 1){
					show.MostrarUsuarioDepartamentoIdDepartamentoDisponiblesDadoIdUsuario(listaDepartamentos, listaRelaciones, idUsuario);
				}
				idDepartamento = crea.createIdDepartamentoUD(listaDepartamentos, leer, true);
				if(listaDepartamentos.searchDepartamentoIdDepartamento(idDepartamento)!=null){
					break;
				}else{
					show.MostrarUsuarioDepartamentoErrorNoExisteIdDepartamento();
				}
			}
			if(comprueba.checkUsuarioDepartamentoRepetido(idUsuario, idDepartamento, listaRelaciones) == false){
				break;
			}else{
				logger.warn("Relacion existente: " + idUsuario + " - "  + idDepartamento);
				show.MostrarUsuarioDepartamentoRepetido(idUsuario,idDepartamento);
			}
		}
		long fecha = crea.createFechaUD(leer);
		listaRelaciones.addUsuarioDepartamento(idUsuario, idDepartamento,fecha);
		setNumPersonas(listaDepartamentos, listaRelaciones);
		
	}
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
	

	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------	
//-----------------------------------METODOS PARA BUSCAR UN USUARIO EN LA LISTA DE USUARIOS------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-menuSearchUsuario.
// 2-optionSearchUsuario.
// 3-optionSearchUsuarioArrayList.
// 4-searchUsuarioArrayListIdUsuario.
// 5-searchUsuarioArrayListApellido.
// 6-searchUsuarioArrayListDni.
// 7-searchUsuarioArrayListSexo.
// 8-searchUsuarioArrayListAltura.
// 9-searchUsuarioArrayListCorreo.
// 10-chooseOneUsuario.

	
	private static Usuario menuSearchUsuario(Scanner leer, UsuarioArrayList listaUsuarios, boolean soloMuestra,boolean compruebaIdUsuarioEnUsuarioArrayList,boolean compruebaDniEnUsuarioArrayList,boolean compruebaCorreoEnUsuarioArrayList)
	{
		logger.info("Entrada en menuSearchUsuario con soloMuestra: " + soloMuestra);
		Usuario usuarioBusqueda;
		UsuarioArrayList variosUsuariosBusqueda;
		// 2 = Mostrar Menu de Busqueda
		int eleccion2 = chooseEleccion(leer, 2);
		//Las opciones 1, 3 y 6 nos devuelven un Objeto tipo Usuario
		if(eleccion2 == 1 || eleccion2 == 3 || eleccion2 == 6){
			usuarioBusqueda = optionSearchUsuario(leer,listaUsuarios, eleccion2,compruebaIdUsuarioEnUsuarioArrayList,compruebaDniEnUsuarioArrayList,compruebaCorreoEnUsuarioArrayList);
			if(usuarioBusqueda != null){
				if(soloMuestra){
					show.MostrarUsuarioEncontrado();
					show.MostrarUsuario(usuarioBusqueda);
					logger.info("usuario: "+ usuarioBusqueda.getApellidos() + " encontrado");
					return usuarioBusqueda;
				}else{
					logger.info("usuario con idUsuario: "+usuarioBusqueda.getIdUsuario() + "Encontrado");
					return usuarioBusqueda;
				}
			}else{
				show.MostrarUsuarioNoEncontrado();
				logger.info("Usuario no encontrado");
				return null;
			}
		//Las opciones 2, 4 y 5 nos devuelven un Objeto tipo UsuarioArrayList, debemos convertirlo a solo 1 Usuario
		}else if(eleccion2 == 2 || eleccion2 == 4 || eleccion2 == 5){
			variosUsuariosBusqueda = optionSearchUsuarioArrayList(leer, listaUsuarios, eleccion2);
			if(variosUsuariosBusqueda.sizeListaUsuarios() != 0){
				if(variosUsuariosBusqueda.sizeListaUsuarios() != 1){
					show.MostrarUsuarioEncontrado();
					show.MostrarUsuarioArrayList(variosUsuariosBusqueda);
					if(soloMuestra){
						logger.info("usuario nulo");
						return null;
					}else{
						logger.info("Encontrados " + variosUsuariosBusqueda.sizeListaUsuarios() + " usuarios");
						return chooseOneUsuario(variosUsuariosBusqueda, leer);
					}
				}else{
					show.MostrarUsuarioEncontrado();
					show.MostrarUsuario(variosUsuariosBusqueda.getUsuario(0));
					if(soloMuestra){
						logger.info("usuario: " + variosUsuariosBusqueda.getUsuario(0).getApellidos() + " encontrado");
						return variosUsuariosBusqueda.getUsuario(0);
					}else{
						logger.info("usuario con idUsuario: "+ variosUsuariosBusqueda.getUsuario(0).getIdUsuario() + "Encontrado");
						return variosUsuariosBusqueda.getUsuario(0);
					}
				}
			}else{
				show.MostrarUsuarioNoEncontrado();
				logger.info("Usuario no encontrado");
				return null;
			}
		}else if(eleccion2 == 7){
			show.MostrarUsuarioArrayList(listaUsuarios);
			logger.info("Mostramos listaUsuarios");
			return null;
		}else{
			show.MostrarErrorOpcion();
			logger.warn("Error: opcion incorrecta");
			return null;
		}
	}
	private static Usuario optionSearchUsuario(Scanner leer, UsuarioArrayList listaUsuarios, int eleccion,boolean compruebaIdUsuarioEnUsuarioArrayList,boolean compruebaDniEnUsuarioArrayList,boolean compruebaCorreoEnUsuarioArrayList)
	{
		logger.info("Buscamos Usuarios: " + eleccion);
		switch (eleccion){
		case 1:
			show.MostrarUsuarioBuscarPor("IdUsuario");
			return searchUsuarioArrayListIdUsuario(listaUsuarios,leer,compruebaIdUsuarioEnUsuarioArrayList);
		case 3:
			show.MostrarUsuarioBuscarPor("Dni");
			return searchUsuarioArrayListDni(listaUsuarios,leer,compruebaDniEnUsuarioArrayList);
		case 6:
			show.MostrarUsuarioBuscarPor("Correo");
			return searchUsuarioArrayListCorreo(listaUsuarios,leer,compruebaCorreoEnUsuarioArrayList);
		default:
			return null;
		}
	}
	private static UsuarioArrayList optionSearchUsuarioArrayList(Scanner leer, UsuarioArrayList listaUsuarios, int eleccion)
	{
		logger.info("Buscamos ListaUsuarios: " + eleccion);
		switch (eleccion){
		case 2:
			show.MostrarUsuarioBuscarPor("Apellidos");
			return searchUsuarioArrayListApellidos(listaUsuarios,leer);
		case 4:
			show.MostrarUsuarioBuscarPor("Sexo");
			return searchUsuarioArrayListSexo(listaUsuarios,leer);
		case 5:
			show.MostrarUsuarioBuscarPor("Altura");
			return searchUsuarioArrayListAltura(listaUsuarios,leer);
		default:
			return null;
		}
	}
	private static Usuario searchUsuarioArrayListIdUsuario(UsuarioArrayList listaUsuarios,Scanner leer,boolean compruebaIdUsuarioEnUsuarioArrayList)
	{
		int idUsuario = crea.createIdUsuario(listaUsuarios, leer, compruebaIdUsuarioEnUsuarioArrayList);
		logger.info("buscamos idUsuario: "+idUsuario);
		return listaUsuarios.searchUsuarioIdUsuario(idUsuario);
	}
	private static UsuarioArrayList searchUsuarioArrayListApellidos(UsuarioArrayList listaUsuarios,Scanner leer)
	{
		String apellidos = crea.createApellidos(leer);
		logger.info("buscamos apellidos: "+apellidos);
		return listaUsuarios.searchUsuarioApellidos(apellidos);
	}
	private static Usuario searchUsuarioArrayListDni(UsuarioArrayList listaUsuarios,Scanner leer,boolean compruebaDniEnUsuarioArrayList)
	{
		String dni = crea.createDni(listaUsuarios,leer, compruebaDniEnUsuarioArrayList);
		logger.info("buscamos dni: "+dni);
		return listaUsuarios.searchUsuarioDni(dni);
	}
	private static UsuarioArrayList searchUsuarioArrayListSexo(UsuarioArrayList listaUsuarios,Scanner leer)
	{
		String sexo = crea.createSexo(leer);
		logger.info("buscamos sexo: "+sexo);
		return listaUsuarios.searchUsuarioSexo(sexo);
	}
	private static UsuarioArrayList searchUsuarioArrayListAltura(UsuarioArrayList listaUsuarios,Scanner leer)
	{
		Float altura = crea.createAltura(leer);
		logger.info("buscamos altura: "+altura);
		return listaUsuarios.searchUsuarioAltura(altura);
	}
	private static Usuario searchUsuarioArrayListCorreo(UsuarioArrayList listaUsuarios,Scanner leer,boolean compruebaCorreoEnUsuarioArrayList)
	{
		String correo = crea.createCorreo(listaUsuarios, leer,compruebaCorreoEnUsuarioArrayList);
		logger.info("buscamos correo: "+correo);
		return listaUsuarios.searchUsuarioCorreo(correo);
	}
	private static Usuario chooseOneUsuario(UsuarioArrayList variosUsuariosBusqueda, Scanner leer)
	{
		int num;
		while(true){
			try{
				show.MostrarUsuarioElijaNumero();
				String input = leer.nextLine();
				logger.info("opcion introducida: " + input);
				num = Integer.parseInt(input);
				logger.info("chooseOneUsuario, opcion: "+num);
				if(num <= variosUsuariosBusqueda.sizeListaUsuarios() && num!=0){
					break;
				}else {
					show.MostrarErrorOpcion();
				}
			}catch(NumberFormatException e){
				logger.warn("Opcion invalida");
				show.MostrarErrorOpcion();
			}
		}
		logger.info("delovemos usuario con idUsuario: " + variosUsuariosBusqueda.getUsuario(num-1).getIdUsuario());
		return variosUsuariosBusqueda.getUsuario(num-1);
	}
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------METODOS PARA MODIFICAR CIERTO ATRIBUTO DE USUARIO-------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-changeAtributosUsuario.
// 2-changeIdUsuario.
// 3-changeApellidos.
// 4-changeDni.
// 5-changeSexo.
// 6-changeAltura.
// 7-changeCorreo.
	
	
	private static void changeAtributosUsuario(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer, int eleccion3,boolean compruebaIdUsuarioEnUsuarioArrayList,boolean compruebaDniEnUsuarioArrayList,boolean compruebaCorreoEnUsuarioArrayList)
	{
		logger.info(" changeAtributosUsuario eleccion: "+eleccion3);
		switch (eleccion3) {
		case 1:
			changeIdUsuario(UsuarioModificacion, listaUsuarios, listaRelaciones, leer, compruebaIdUsuarioEnUsuarioArrayList);
			break;
		case 2:
			changeApellidos(UsuarioModificacion, listaUsuarios, leer);
			break;
		case 3:
			changeDni(UsuarioModificacion, listaUsuarios, leer,compruebaDniEnUsuarioArrayList);
			break;
		case 4:
			changeSexo(UsuarioModificacion, listaUsuarios, leer);
			break;
		case 5:
			changeAltura(UsuarioModificacion, listaUsuarios, leer);
			break;
		case 6:
			changeCorreo(UsuarioModificacion, listaUsuarios, leer,compruebaCorreoEnUsuarioArrayList);
			break;
		default:
			show.MostrarErrorOpcion();
			break;
		}
	}
	private static void changeIdUsuario(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, UsuarioDepartamentoArrayList listaRelaciones, Scanner leer,boolean compruebaIdUsuarioEnUsuarioArrayList)
	{
		show.MostrarModificarAtributo("IdUsuario");
		Integer nuevoIdUsuario = crea.createIdUsuario(listaUsuarios, leer, compruebaIdUsuarioEnUsuarioArrayList);
		Integer antiguoIdUsuario = UsuarioModificacion.getIdUsuario();
		logger.info("Antiguo idUsuario: " +antiguoIdUsuario+ " nuevo IdUSuario: "+ nuevoIdUsuario);
		UsuarioModificacion.changeIdUsuario(nuevoIdUsuario);
		show.MostrarModificarAtributoActualizado("IdUsuario",antiguoIdUsuario.toString(),nuevoIdUsuario.toString());
		for (UsuarioDepartamento relacion : listaRelaciones.getListaUsuarioDepartamentos()) {
			if(relacion.getIdUsuario() == antiguoIdUsuario){
				relacion.changeIdUsuario(nuevoIdUsuario);
				logger.info("IdUsuario cambiado en relacion: " + relacion.getIdDepartamento());
			}
		}
	}
	private static void changeApellidos(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, Scanner leer)
	{
		show.MostrarModificarAtributo("Apellidos");
		String nuevoApellidos = crea.createApellidos(leer);
		String antiguoApellidos = UsuarioModificacion.getApellidos();
		logger.info("Antiguos apellidos: " +antiguoApellidos+ " nuevos apellidos: "+ nuevoApellidos);
		UsuarioModificacion.changeApellidos(nuevoApellidos);
		show.MostrarModificarAtributoActualizado("Apellidos", antiguoApellidos, nuevoApellidos);
	}
	private static void changeDni(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, Scanner leer,boolean compruebaDniEnUsuarioArrayList)
	{
		show.MostrarModificarAtributo("Dni");
		String nuevoDni = crea.createDni(listaUsuarios, leer, compruebaDniEnUsuarioArrayList);
		String antiguoDni = UsuarioModificacion.getDni();
		logger.info("Antiguo dni: " +antiguoDni+ " nuevo dni: "+ nuevoDni);
		UsuarioModificacion.changeDni(nuevoDni);
		show.MostrarModificarAtributoActualizado("Dni", antiguoDni, nuevoDni);
	}
	private static void changeSexo(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, Scanner leer)
	{
		show.MostrarModificarAtributo("Sexo");
		String nuevoSexo = crea.createSexo(leer);
		String antiguoSexo = UsuarioModificacion.getSexo().toString();
		logger.info("Antiguo sexo: " +antiguoSexo+ " nuevo sexo: "+ nuevoSexo);
		UsuarioModificacion.changeSexo(nuevoSexo);
		try{
			show.MostrarModificarAtributoActualizado("Sexo", antiguoSexo, TipoSexo.valueOf(nuevoSexo).name());
		}catch(IllegalArgumentException e){
			show.MostrarModificarAtributoActualizado("Sexo", antiguoSexo, TipoSexo.NO_ESPECIFICADO.name());
		}
	}
	private static void changeAltura(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, Scanner leer)
	{
		show.MostrarModificarAtributo("Altura");
		Float nuevoAltura = crea.createAltura(leer);
		Float antiguoAltura = UsuarioModificacion.getAltura();
		logger.info("Antigua altura: " +antiguoAltura+ " nueva altura: "+ nuevoAltura);
		UsuarioModificacion.changeAltura(nuevoAltura);
		show.MostrarModificarAtributoActualizado("Altura", antiguoAltura.toString(), nuevoAltura.toString());
	}
	private static void changeCorreo(Usuario UsuarioModificacion, UsuarioArrayList listaUsuarios, Scanner leer,boolean compruebaCorreoEnUsuarioArrayList)
	{
		show.MostrarModificarAtributo("Correo");
		String nuevoCorreo = crea.createCorreo(listaUsuarios, leer, compruebaCorreoEnUsuarioArrayList);
		String antiguoCorreo = UsuarioModificacion.getCorreo();
		logger.info("Antiguo correo: " +antiguoCorreo+ " nuevo correo: "+ nuevoCorreo);
		UsuarioModificacion.changeCorreo(nuevoCorreo);
		show.MostrarModificarAtributoActualizado("Correo", antiguoCorreo, nuevoCorreo);
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------METODOS PARA IMPRIMIR INFORMACION DE USUARIO---------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1- printUsuariopdf
	
	private static void printUsuarioPdf(Usuario usuario, DepartamentoArrayList listaDepartamentos, UsuarioDepartamentoArrayList listaRelaciones) throws Exception
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("USUARIO-"+usuario.getIdUsuario()+".pdf"));
        document.open();
		String espacio = "\n";
		String cabecera = "INFORMACION USUARIO";
		String idUsuario = "ID.USUARIO: " + usuario.getIdUsuario();
		String apellidos = "APELLIDOS: " + usuario.getApellidos();
		String dni = "DNI: " + usuario.getDni();
		String sexo = "SEXO: " + usuario.getSexo().name();
		String altura = "ALTURA: " + usuario.getAltura();
		String correo = "CORREO: " + usuario.getCorreo();
		String infoUsuario =cabecera + espacio + espacio + idUsuario + espacio + apellidos + espacio + dni + espacio + sexo + espacio + altura + espacio + correo +espacio+espacio+espacio;
		document.add(new Paragraph(infoUsuario));
		
		String cabeceraDepartamentos = "DEPARTAMENTOS A LOS QUE PERTENECE USUARIO" + espacio + espacio;
		document.add(new Paragraph(cabeceraDepartamentos));
		for (UsuarioDepartamento relacion : listaRelaciones.getListaUsuarioDepartamentos()) {
			if(relacion.getIdUsuario() == usuario.getIdUsuario()){
				String infoRelacion = "Usuario " + usuario.getDni() + " se unio al departamento " + listaDepartamentos.searchDepartamentoIdDepartamento(relacion.getIdDepartamento()).getNombre() + " en " + relacion.getFechaTexto().toString();
				String idDepartamento ="ID.DEPARTAMENTO: " + listaDepartamentos.searchDepartamentoIdDepartamento(relacion.getIdDepartamento()).getIdDepartamento();
				String director ="DIRECTOR: " + listaDepartamentos.searchDepartamentoIdDepartamento(relacion.getIdDepartamento()).getDirector();
				String numPersonas ="PERSONAL: " + listaDepartamentos.searchDepartamentoIdDepartamento(relacion.getIdDepartamento()).getNumPersonas();
				String infoDepartamento = infoRelacion + espacio + espacio + idDepartamento + espacio + director + espacio + numPersonas + espacio + espacio;
				document.add(new Paragraph(infoDepartamento));
			}
		}
		document.close();
	}
//-----------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------METODOS PARA CONSEGUIR UNA ELECCION DE USUARIO-------------------------------------------
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
					show.MostrarMenuUsuario();
					break;
				case 2:
					show.MostrarMenuBusquedaUsuario();
					break;
				case 3:
					show.MostrarMenuModificarUsuario();
					break;
				case 4:
					show.MostrarMenuBorrarUsuario();
					break;
				case 5:
					show.MostrarMenuAddDepartamentoNuevoUsuario();
					break;
				case 6:
					show.MostrarMenuMostrarIdUsuarioOIdDepartamentoExistentes();
					break;
				case 7:
					show.MostrarMenuExportarPdf();
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

