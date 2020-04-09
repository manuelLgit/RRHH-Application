package entidadesUtilidades;
import entidades.*;
import entidadesArrayList.*;
public class MostrarPorPantalla {
	
	
	
// -------------------------------------------------------------------------------------------------------------------------------------
// --------------------------------------------------------- MOSTRAR MENUS  ------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void MostrarMenuPrincipal()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU PRINCIPAL");
		System.out.println("	1.- Menu Usuario");
		System.out.println("	2.- Menu Departamentos");
		System.out.println("	3.- Menu Usuario-Departamento");
		System.out.println("	4.- Salir");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuUsuario()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU USUARIO");
		System.out.println("	1.- Buscar");
		System.out.println("	2.- Añadir");
		System.out.println("	3.- Modificar");
		System.out.println("	4.- Borrar");
		System.out.println("	5.- Volver y Guardar");
		System.out.println("	6.- Exportar a Excel");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU DEPARTAMENTO");
		System.out.println("	1.- Buscar");
		System.out.println("	2.- Añadir");
		System.out.println("	3.- Modificar");
		System.out.println("	4.- Borrar");
		System.out.println("	5.- Volver y Guardar");
		System.out.println("	6.- Exportar a Excel");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuUsuarioDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU RELACIONES");
		System.out.println("	1.- Buscar");
		System.out.println("	2.- Añadir");
		System.out.println("	3.- Modificar");
		System.out.println("	4.- Borrar");
		System.out.println("	5.- Volver y Guardar");
		System.out.println("	6.- Exportar a Excel");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuBusquedaUsuario()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU BUSQUEDA USUARIO");
		System.out.println("	1.- Buscar por IdUsuario");
		System.out.println("	2.- Buscar por Apellidos");
		System.out.println("	3.- Buscar por Dni");
		System.out.println("	4.- Buscar por Sexo");
		System.out.println("	5.- Buscar por Altura");
		System.out.println("	6.- Buscar por Correo");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuBusquedaDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU BUSQUEDA DEPARTAMENTO");
		System.out.println("	1.- Buscar por IdDepartamento");
		System.out.println("	2.- Buscar por Nombre");
		System.out.println("	3.- Buscar por Director/a");
		System.out.println("	4.- Buscar por Numero de Personas");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuBusquedaUsuarioDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU BUSQUEDA RELACIONES");
		System.out.println("	1.- Buscar por IdUsuario");
		System.out.println("	2.- Buscar por IdDepartamento");
		System.out.println("	3.- Buscar por Fecha");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuModificarUsuario()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU MODIFICAR USUARIO");
		System.out.println("	1.- Modificar IdUsuario");
		System.out.println("	2.- Modificar Apellidos");
		System.out.println("	3.- Modificar Dni");
		System.out.println("	4.- Modificar Sexo");
		System.out.println("	5.- Modificar Altura");
		System.out.println("	6.- Modificar Correo");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuModificarDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU MODIFICAR DEPARTAMENTO");
		System.out.println("	1.- Modificar IdDepartamento");
		System.out.println("	2.- Modificar Nombre");
		System.out.println("	3.- Modificar Director/a");
		System.out.println("	4.- Modificar Personal");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuModificarUsuarioDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("MENU MODIFICAR RELACIONES");
		System.out.println("	1.- Modificar IdUsuario");
		System.out.println("	2.- Modificar IdDepartamento");
		System.out.println("	3.- Modificar Fecha");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuBorrarUsuario()
	{
		System.out.print("\n\n\n");
		System.out.println("¿Quiere Borrar Este Usuario?");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuBorrarDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("¿Quiere Borrar Este Departamento?");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuBorrarUsuarioDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("¿Quiere Borrar Esta Relacion?");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuSubsanacionErroresUsuario()
	{
		System.out.print("\n\n\n");
		System.out.println("¿Quiere Solucionar los Errores en los Datos de Usuario?");
		System.out.println("******RECUERDE: Si no se Solucionan los Errores, los datos CORROMPIDOS se perderan.******");
		System.out.print("\n");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuSubsanacionErroresDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("¿Quiere Solucionar los Errores en los Datos de Departamento?");
		System.out.println("******RECUERDE: Si no se Solucionan los Errores, los datos CORROMPIDOS se perderan.******");
		System.out.print("\n");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuSubsanacionErroresUsuarioDepartamento()
	{
		System.out.print("\n\n\n");
		System.out.println("¿Quiere Solucionar los Errores en los Datos de Relaciones?");
		System.out.println("******RECUERDE: Si no se Solucionan los Errores, los datos CORROMPIDOS se perderan.******");
		System.out.print("\n");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuMostrarIdUsuarioOIdDepartamentoExistentes()
	{
		System.out.print("\n");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuAddUsuarioNuevoDepartamento()
	{
		System.out.print("\n");
		System.out.println("¿Desea añadir Usuario al Nuevo Departamento?");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuAddDepartamentoNuevoUsuario()
	{
		System.out.print("\n");
		System.out.println("¿Desea añadir el Nuevo Usuario a algun Departamento Existente?");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuDepartamentoCambiaNumPersonas()
	{
		System.out.print("\n");
		System.out.println("¿Que desea hacer?");
		System.out.println("	1.- Añadir Personal a Departamento");
		System.out.println("	2.- Borrar Personal de Departamento");
		System.out.println("	3.- No hacer nada");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}
	public void MostrarMenuExportarPdf()
	{
		System.out.print("\n");
		System.out.println("¿Desea exportar la informacion de la Busqueda a un archivo PDF?");
		System.out.println("	1.- SI");
		System.out.println("	2.- NO");
		System.out.println("\n");
		System.out.println("Introduce la opcion:");
	}

	
// -------------------------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------- MOSTRAR PRINCIPAL -----------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	public void MostrarErrorLecturaFicheroProperties(String p1, String p3, String p5)
	{
		System.out.println("ERROR: No se puede leer el FICHERO de PROPIEDADES" + "\n");
		System.out.println("______________Se carga la configuracion por DEFECTO______________");
		System.out.println("Ruta del archivo de Usuarios: " +p1);
		System.out.println("Ruta del archivo de Departamentos: " + p3);
		System.out.println("Ruta del archivo de Relaciones: " +p5);
		System.out.println("_________________________________________________________________");
	}
	public void MostrarCerrandoPrograma()
	{
		System.out.println("*****CERRANDO PROGRAMA*****");
	}
	
	
	
	
// -------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------- MOSTRAR USUARIO ------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void MostrarUsuario(Usuario mostrar)
	{
		System.out.print("\n\n\n");
		System.out.println("		IdUsuario: " + mostrar.getIdUsuario());
		System.out.println("		Apellidos: " + mostrar.getApellidos());
		System.out.println("		Dni: " + mostrar.getDni());
		System.out.println("		Sexo: " + mostrar.getSexo().toString());
		System.out.println("		Altura: " + mostrar.getAltura());
		System.out.println("		Correo: " + mostrar.getCorreo());
		System.out.print("\n\n\n\n");
	}
	public void MostrarUsuarioArrayList(UsuarioArrayList mostrar){
		String num = "Nº";
		String idu = "ID_USUARIO";
		String apl = "APELLIDOS";
		String dni = "DNI";
		String sex = "SEXO";
		String alt = "ALTURA";
		String cor = "CORREO";
		int espaciado1 = 25;
		int espaciado2 = 13;
		int espaciado3 = 17;
		int espaciado4 = 4;
		System.out.print("\n\n\n");
		System.out.printf("	%-"+espaciado4+"s|%-"+espaciado2+"s|%-"+espaciado1+"s|%-"+espaciado3+"s|%-"+espaciado3+"s|%-"+espaciado2+"s|%-"+espaciado1+"s\n",num,idu,apl,dni,sex,alt,cor);
		System.out.println("_____________________________________________________________________________________________________________________________________________");
		int i = 1;
		for (Usuario usuario : mostrar.getListaUsuarios()) {
			System.out.printf("	%-"+espaciado4+"s|%-"+espaciado2+"s|%-"+espaciado1+"s|%-"+espaciado3+"s|%-"+espaciado3+"s|%-"+espaciado2+"s|%-"+espaciado1+"s\n",i+".",usuario.getIdUsuario(), usuario.getApellidos(),usuario.getDni(),usuario.getSexo().toString(),usuario.getAltura(),usuario.getCorreo());
			i++;
		}
		System.out.print("\n\n\n\n");
	}
	public void MostrarUsuarioCorrompido(int i, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, String archivo)
	{
		System.out.print("\n\n");
		System.out.println("USUARIO nº"+i+" CORROMPIDO, en archivo: " +archivo);
		System.out.println("		IdUsuario: " + b1);
		System.out.println("		Apellidos: " + b2);
		System.out.println("		Dni: " + b3);
		System.out.println("		Sexo: " + b4);
		System.out.println("		Altura: " + b5);
		System.out.println("		Correo: " + b6);
		System.out.print("\n\n");
	}
	public void MostrarUsuarioRepetido(int i, boolean b1, boolean b2, boolean b3, String archivo)
	{
		System.out.print("\n\n");
		System.out.println("USUARIO nº"+i+" REPETIDO, en archivo: " +archivo);
		System.out.println("		IdUsuario: " + b1);
		System.out.println("		Dni: " + b2);
		System.out.println("		Correo: " + b3);
		System.out.print("\n\n");
	}
	public void MostrarUsuarioAdd(){
		System.out.println("Se ha añadido USUARIO:");
	}
	public void MostrarUsuarioIdUsuario(int idUsuario)
	{
		System.out.println("\n\n");
		System.out.println("IdUsuario: " + idUsuario);
	}
	public void MostrarUsuarioIntroduzcaIdUsuario(int idMax)
	{
		System.out.println("\n\n");
		System.out.println("Introduzca IdUsuario (numero Natural menor de "+ idMax +"):");
	}
	public void MostrarUsuarioErrorExisteIdUsuario(int idUsuario)
	{
		System.out.println("ERROR: Ya exite un Usuario con IdUsuario: " + idUsuario);
	}
	public void MostrarUsuarioIntroduzcaPrimerApellido()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca Los Apellidos (longitud maxima de 75 caracteres)" + "\n");
		System.out.println("Introduzca Primer Apellido de Usuario:");
	}
	public void MostrarUsuarioIntroduzcaSegundoApellido()
	{
		System.out.println("Introduzca Segundo Apellido de Usuario:");
	}
	public void MostrarUsuarioIntroduzcaDni()
	{
		System.out.println("\n\n");
		System.out.println("Formato Dni: '00000000A'");
		System.out.println("Introduzca Dni:");
	}
	public void MostrarUsuarioErrorExisteDni(String dni)
	{
		System.out.println("ERROR: Ya exite un Usuario con DNI: " + dni);
	}
	public void MostrarUsuarioIntroduzcaSexo()
	{
		System.out.println("\n\n");
		System.out.println("Sexo: Masculino, Femenino o No_Especificado");
		System.out.println("Si introduce Otro, se evaluara como No_Especificado"+"\n");
		System.out.println("Introduzca Sexo de Usuario:");
	}
	public void MostrarUsuarioIntroduzcaAltura()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca Altura de Usuario (numero decimal: 0.00)");
	}
	public void MostrarUsuarioIntroduzcaCorreo(int minCaracteresX, int minCaracteresY, int minCaracteresZ)
	{
		System.out.println("\n\n");
		System.out.println("Formato de Correo Electronico: xxxxxxxx@yyyy.zz");
		System.out.println("Minimo "+minCaracteresX+" caracteres 'x'");
		System.out.println("Minimo "+minCaracteresY+" caracteres 'y'");
		System.out.println("Minimo "+minCaracteresZ+" caracteres 'z'" + "\n");
		System.out.println("Introduzca Correo Electronico:");
	}
	public void MostrarUsuarioErrorExisteCorreo(String correo)
	{
		System.out.println("ERROR: Ya existe un Usuario existente con correo: "+ correo);
	}
	public void MostrarUsuarioBuscarPor(String atributo)
	{
		System.out.println("Ha elegido: Buscar Usuario por " + atributo);
	}
	public void MostrarUsuarioPrimeroBuscar()
	{
		System.out.println("PRIMERO: Buscamos el Usuario.");
	}
	public void MostrarUsuarioEncontrado()
	{
		System.out.println("Se ha encontrado el USUARIO:");
	}
	public void MostrarUsuarioNoEncontrado()
	{
		System.out.println("No se ha encontrado USUARIO en la busqueda");
	}
	public void MostrarUsuarioAtributoModificar()
	{
		System.out.println("Que Atributo de Usuario quiere Modificar:");
	}
	public void MostrarUsuarioElijaNumero()
	{
		System.out.println("Elija Numero del Usuario: ");
	}
	public void MostrarUsuarioBorrado()
	{
		System.out.println("La informacion del USUARIO ha sido Borrada con EXITO");
	}
	public void MostrarUsuarioNoBorrado()
	{
		System.out.println("NO se ha borrado la informacion de USUARIO");
	}
	public void MostrarUsuarioNoDatosPrevios()
	{
		System.out.println("\n\n"+"***No hay datos de Usuarios Previos***"+"\n\n");
	}
	public void MostrarUsuarioCabeceraErrores(Integer i)
	{
		System.out.println("\n\n" + "		ERRORES en Usuario nº " + (i+1));
		System.out.println("_________________________________________" + "\n");
	}
	public void MostrarUsuarioFinErrores()
	{
		System.out.println("\n\n\n" +"****TODOS LOS ERRORES DE USUARIO SOLUCIONADOS*****");
		System.out.println("\n" + "GUARDANDO...");	
	}
	public void MostrarUsuarioExportarExcel()
	{
		System.out.println("\n\n");
		System.out.println("Ha elegido Exportar la lista de Usuarios a Excel");
		System.out.println("\n");
		System.out.println("Introduzca la ruta donde quiere crear el archivo (.xls):  ");
	}
	public void MostrarUsuarioExportarExcelNombre()
	{
		System.out.println("Introduzca nombre del archivo Excel (SIN FORMATO): ");
	}
	public void  MostrarUsuarioExportarExcelExito(String ruta)
	{
		System.out.println("\n");
		System.out.println("Se ha exportado a Excel la lista de Usuarios con EXITO");
		System.out.println("Ruta del archivo: " + ruta);
	}
	public void  MostrarUsuarioExportarExcelFracaso(String ruta)
	{
		System.out.println("\n");
		System.out.println("No se ha podido exportar a Excel la lista de Usuarios");
		System.out.println("No existe: " + ruta);
	}
	

	
	
// -------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------- MOSTRAR DEPARTAMENTO ------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void MostrarDepartamento(Departamento mostrar)
	{
		System.out.print("\n\n\n");
		System.out.println("		IdDepartamento: " + mostrar.getIdDepartamento());
		System.out.println("		Nombre: " + mostrar.getNombre());
		System.out.println("		Director/a: " + mostrar.getDirector());
		System.out.println("		Personal: " + mostrar.getNumPersonas());
		System.out.print("\n\n\n\n");
	}
	public void MostrarDepartamentoArrayList(DepartamentoArrayList mostrar){
		String num = "Nº";
		String idd = "ID_DEPARTAMENTO";
		String nom = "NOMBRE";
		String dir = "DIRECTOR/A";
		String npe = "PERSONAL";
		int espaciado1 = 20;
		int espaciado2 = 16;
		int espaciado3 = 35;
		int espaciado4 = 5;
		System.out.print("\n\n\n");
		System.out.printf("				%-"+espaciado4+"s|%-"+espaciado2+"s|%-"+espaciado1+"s|%-"+espaciado3+"s|%-"+espaciado4+"s\n",num,idd,nom,dir,npe);
		System.out.println("		 	__________________________________________________________________________________________________________");
		int i = 1;
		for (Departamento departamento : mostrar.getListaDepartamentos()) {
			System.out.printf("				%-"+espaciado4+"s|%-"+espaciado2+"s|%-"+espaciado1+"s|%-"+espaciado3+"s|%-"+espaciado4+"s\n",i+".",departamento.getIdDepartamento(), departamento.getNombre(),departamento.getDirector(),departamento.getNumPersonas());
			i++;
		}
		System.out.print("\n\n\n\n");
	}
	public void MostrarDepartamentoCorrompido(int i, boolean b1, boolean b2, boolean b3, boolean b4, String archivo)
	{
		System.out.print("\n\n");
		System.out.println("DEPARTAMENTO nº"+i+" CORROMPIDO, en archivo: " +archivo);
		System.out.println("		IdDepartamento: " + b1);
		System.out.println("		Nombre: " + b2);
		System.out.println("		Director/a: " + b3);
		System.out.println("		Personal: " + b4);
		System.out.print("\n\n");
	}
	public void MostrarDepartamentoRepetido(int i, boolean b1, boolean b2, String archivo)
	{
		System.out.print("\n\n");
		System.out.println("DEPARTAMENTO nº"+i+" REPETIDO, en archivo: " +archivo);
		System.out.println("		IdDepartamento: " + b1);
		System.out.println("		Nombre: " + b2);
		System.out.print("\n\n");
	}
	public void MostrarDepartamentoAdd(){
		System.out.println("Se ha añadido DEPARTAMENTO:");
	}
	public void MostrarDepartamentoIdDepartamento(int idDepartamento)
	{
		System.out.println("\n\n");
		System.out.println("IdDepartamento: " + idDepartamento);
	}
	public void MostrarDepartamentoIntroduzcaIdDepartamento(int idMax)
	{
		System.out.println("\n\n");
		System.out.println("Introduzca IdDepartamento (numero Natural menor de "+idMax+"):");
	}
	public void MostrarDepartamentoErrorExisteIdDepartamento(int idDepartamento)
	{
		System.out.println("ERROR: Ya exite un Departamento con IdDepartamento: " + idDepartamento);
	}
	public void MostrarDepartamentoIntroduzcaNombre()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca Nombre de Departamento (Longitud Maxima de 50 caracteres):");
	}
	public void MostrarDepartamentoErrorExisteNombre(String nombre)
	{
		System.out.println("ERROR: Ya exite un Departamento con nombre: " + nombre);
	}
	public void MostrarDepartamentoIntroduzcaNombreDirector()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca Nombre del Director/a (Longitud Maxima de 30 caracteres):");
	}
	public void MostrarDepartamentoIntroduzcaApellidoDirector()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca Apellido del Director/a (Longitud Maxima de 30 caracteres):");
	}
	public void MostrarDepartamentoIntroduzcaNumPersonas()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca Personal del Departamento (Numero Entero):");
	}
	public void MostrarDepartamentoAddMasUsuarios(int i)
	{
		System.out.println("\n");
		System.out.println("El Departamento ya tiene " + i + " personas.");
		System.out.println("¿Desea Añadir mas usuarios al Departamento?");
	}
	public void MostrarDepartamentoBorrarMasUsuarios()
	{
		System.out.println("\n");
		System.out.println("¿Desea Borrar mas usuarios del Departamento?");
	}
	public void MostrarDepartamentoImposibleBorrarRelaciones()
	{
		System.out.println("\n");
		System.out.println("El Departamento ya tiene 0 personas, no se puede borrar mas personal");
	}
	public void MostrarDepartamentoBuscarPor(String atributo)
	{
		System.out.println("Ha elegido: Buscar Departamento por " + atributo);
	}
	public void MostrarDepartamentoPrimeroBuscar()
	{
		System.out.println("PRIMERO: Buscamos el Departamento.");
	}
	public void MostrarDepartamentoEncontrado()
	{
		System.out.println("Se ha encontrado el DEPARTAMENTO:");
	}
	public void MostrarDepartamentoNoEncontrado()
	{
		System.out.println("No se ha encontrado DEPARTAMENTO en la busqueda");
	}
	public void MostrarDepartamentoAtributoModificar()
	{
		System.out.println("Que Atributo de Departamento quiere Modificar:");
	}
	public void MostrarDepartamentoElijaNumero()
	{
		System.out.println("Elija Numero del Departamento: ");
	}
	public void MostrarDepartamentoBorrado()
	{
		System.out.println("La informacion del DEPARTAMENTO ha sido Borrada con EXITO");
	}
	public void MostrarDepartamentoNoBorrado()
	{
		System.out.println("NO se ha borrado la informacion de DEPARTAMENTO");
	}
	public void MostrarDepartamentoNoDatosPrevios()
	{
		System.out.println("\n\n"+"***No hay datos de Departamentos Previos***"+"\n\n");
	}
	public void MostrarDepartamentoCabeceraErrores(Integer i)
	{
		System.out.println("\n\n" + "		ERRORES en Departamento nº " + (i+1));
		System.out.println("_________________________________________________________________" + "\n");
	}
	public void MostrarDepartamentoFinErrores()
	{
		System.out.println("\n\n\n" +"****TODOS LOS ERRORES DE DEPARTAMENTO SOLUCIONADOS*****");
		System.out.println("\n" + "GUARDANDO...");	
	}
	public void MostrarDepartamentoExportarExcel()
	{
		System.out.println("\n\n");
		System.out.println("Ha elegido Exportar la lista de Departamentos a Excel");
		System.out.println("\n");
		System.out.println("Introduzca la ruta donde quiere crear el archivo (.xls):  ");
	}
	public void MostrarDepartamentoExportarExcelNombre()
	{
		System.out.println("Introduzca nombre del archivo Excel (SIN FORMATO): ");
	}
	public void  MostrarDepartamentoExportarExcelExito(String ruta)
	{
		System.out.println("\n");
		System.out.println("Se ha exportado a Excel la lista de Departamentos con EXITO");
		System.out.println("Ruta del archivo: " + ruta);
	}
	public void  MostrarDepartamentoExportarExcelFracaso(String ruta)
	{
		System.out.println("\n");
		System.out.println("No se ha podido exportar a Excel la lista de Departamentos");
		System.out.println("No existe: " + ruta);
	}
	
	
	
// -------------------------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------- MOSTRAR USUARIO - DEPARTAMENTO-----------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void MostrarUsuarioDepartamento(UsuarioDepartamento mostrar)
	{
		System.out.print("\n\n\n");
		System.out.println("		IdUsuario: " + mostrar.getIdUsuario());
		System.out.println("		IdDepartamento: " + mostrar.getIdDepartamento());
		System.out.println("		Fecha: " + mostrar.getFechaTexto());
		System.out.print("\n\n\n\n");
	}
	public void MostrarUsuarioDepartamentoArrayList(UsuarioDepartamentoArrayList mostrar){
		String num = "Nº";
		String idu = "ID_USUARIO";
		String idd = "ID_DEPARTAMENTO";
		String fec = "FECHA";
		int espaciado1 = 20;
		int espaciado2 = 16;
		int espaciado3 = 25;
		int espaciado4 = 5;
		System.out.print("\n\n\n");
		System.out.printf("				%-"+espaciado4+"s|%-"+espaciado2+"s|%-"+espaciado1+"s|%-"+espaciado3+"s\n",num,idu,idd,fec);
		System.out.println("		 	___________________________________________________________________________________________");
		int i = 1;
		for (UsuarioDepartamento usuarioDepartamento : mostrar.getListaUsuarioDepartamentos()) {
			System.out.printf("				%-"+espaciado4+"s|%-"+espaciado2+"s|%-"+espaciado1+"s|%-"+espaciado3+"s\n",i+".",usuarioDepartamento.getIdUsuario(), usuarioDepartamento.getIdDepartamento(),usuarioDepartamento.getFechaTexto());
			i++;
		}
		System.out.print("\n\n\n\n");
	}
	public void MostrarUsuarioDepartamentoCorrompido(int i, boolean b1, boolean b2, boolean b3, String archivo)
	{
		System.out.print("\n\n");
		System.out.println("RELACION nº"+i+" CORROMPIDA, en archivo: " +archivo);
		System.out.println("		IdUsuario: " + b1);
		System.out.println("		IdDepartamento: " + b2);
		System.out.println("		Fecha: " + b3);
		System.out.print("\n\n");
	}
	public void MostrarUsuarioDepartamentoAdd()
	{
		System.out.println("Se ha añadido RELACION:");
	}
	public void MostrarUsuarioDepartamentoDeseaMostrarIdUsuario()
	{
		System.out.println("¿Desea ver los IdUsuario EXISTENTES?");
	}
	public void MostrarUsuarioDepartamentoIdUsuarioDisponibles(UsuarioArrayList listaUsuarios)
	{
		int espaciado = 30;
		System.out.println("\n\n");
		System.out.println("IdUsuario disponibles: ");
		for (Usuario usuario : listaUsuarios.getListaUsuarios()) {
			System.out.printf("Apellidos:  %-"+espaciado+"s IdUsuario:  %-"+espaciado+"s\n", usuario.getApellidos(),usuario.getIdUsuario());
		}
	}
	public void MostrarUsuarioDepartamentoIdUsuarioDisponiblesDadoIdDepartamento(UsuarioArrayList listaUsuarios,UsuarioDepartamentoArrayList listaRelaciones, int idDepartamento)
	{
		CheckFormat check = new CheckFormat();
		int espaciado = 30;
		System.out.println("\n\n");
		System.out.println("IdUsuario disponibles para este DEPARTAMENTO: ");
		for (Usuario usuario : listaUsuarios.getListaUsuarios()) {
			if(check.checkUsuarioDepartamentoRepetido(usuario.getIdUsuario(), idDepartamento, listaRelaciones) == false){
				System.out.printf("Apellidos:  %-"+espaciado+"s IdUsuario:  %-"+espaciado+"s\n", usuario.getApellidos(),usuario.getIdUsuario());
		
			}
		}
			
	}
	public void MostrarUsuarioDepartamentoIntroduzcaIdUsuario()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca IdUsuario EXISTENTE:");
	}
	public void MostrarUsuarioDepartamentoIntroduzcaIdUsuarioNuevo()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca IdUsuario NUEVO:");
	}
	public void MostrarUsuarioDepartamentoErrorNoExisteIdUsuario()
	{
		System.out.println("ERROR: No existe IdUsuario introducido");
	}
	public void MostrarUsuarioDepartamentoErrorExisteIdUsuario()
	{
		System.out.println("ERROR: Ya existe el IdUsuario introducido");
	}
	public void MostrarUsuarioDepartamentoDeseaMostrarIdDepartamento()
	{
		System.out.println("¿Desea ver los IdDepartamento EXISTENTES?");
	}
	public void MostrarUsuarioDepartamentoIdDepartamentoDisponibles(DepartamentoArrayList listaDepartamentos)
	{
		int espaciado = 20;
		System.out.println("\n\n");
		System.out.println("IdDepartamento disponibles: ");
		for (Departamento departamento : listaDepartamentos.getListaDepartamentos()) {
			System.out.printf("Nombre:  %-"+espaciado+"s IdDepartamento:  %-"+espaciado+"s\n", departamento.getNombre(),departamento.getIdDepartamento());
		}
	}
	public void MostrarUsuarioDepartamentoIdDepartamentoDisponiblesDadoIdUsuario(DepartamentoArrayList listaDepartamentos,UsuarioDepartamentoArrayList listaRelaciones, int idUsuario)
	{
		int espaciado = 20;
		CheckFormat check = new CheckFormat();
		System.out.println("\n\n");
		System.out.println("IdDepartamento disponibles para dicho USUARIO: ");
		for (Departamento departamento : listaDepartamentos.getListaDepartamentos()) {
			if(check.checkUsuarioDepartamentoRepetido(idUsuario, departamento.getIdDepartamento(), listaRelaciones) == false){
			System.out.printf("Nombre:  %-"+espaciado+"s IdDepartamento:  %-"+espaciado+"s\n", departamento.getNombre(),departamento.getIdDepartamento());
			}
		}
	}
	public void MostrarUsuarioDepartamentoIntroduzcaIdDepartamento()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca IdDepartamento EXISTENTE:");
	}
	public void MostrarUsuarioDepartamentoIntroduzcaIdDepartamentoNuevo()
	{
		System.out.println("\n\n");
		System.out.println("Introduzca IdDepartamento NUEVO:");
	}
	public void MostrarUsuarioDepartamentoErrorNoExisteIdDepartamento()
	{
		System.out.println("ERROR: No existe IdDepartamento introducido");
	}
	public void MostrarUsuarioDepartamentoErrorExisteIdDepartamento()
	{
		System.out.println("ERROR: Ya existe el IdDepartamento introducido");
	}
	public void MostrarUsuarioDepartamentoRepetido(int idUsuario, int idDepartamento)
	{
		System.out.println("ERROR: Ya existe la Relacion: ");
		System.out.println("IdUsuario: " + idUsuario);
		System.out.println("IdDepartamento: " + idDepartamento);
	}
	public void MostrarUsuarioDepartamentoIntroduzcaFecha()
	{
		System.out.println("\n\n");
		System.out.println("Formato fecha: 'DD/MM/YYYY'");
		System.out.println("Introduzca Fecha:");
	}
	public void MostrarUsuarioDepartamentoEncontrado()
	{
		System.out.println("Se ha encontrado la RELACION:");
	}
	public void MostrarUsuarioDepartamentoNoEncontrado()
	{
		System.out.println("No se ha encontrado RELACION en la busqueda");
	}
	public void MostrarUsuarioDepartamentoBuscarPor(String atributo)
	{
		System.out.println("Ha elegido: Buscar Relacion por " + atributo);
	}
	public void MostrarUsuarioDepartamentoElijaNumero()
	{
		System.out.println("Elija Numero de la Relacion: ");
	}
	public void MostrarUsuarioDepartamentoPrimeroBuscar()
	{
		System.out.println("PRIMERO: Buscamos la Relacion.");
	}
	public void MostrarUsuarioDepartamentoAtributoModificar()
	{
		System.out.println("Que Atributo la Relacion quiere Modificar:");
	}
	public void MostrarUsuarioDepartamentoBorrado()
	{
		System.out.println("La informacion de la RELACION ha sido Borrada con EXITO");
	}
	public void MostrarUsuarioDepartamentoNoBorrado()
	{
		System.out.println("NO se ha borrado la informacion de la RELACION");
	}
	public void MostrarUsuarioDepartamentoNoDatosPrevios()
	{
		System.out.println("\n\n"+"***No hay datos de Relaciones Previas***"+"\n\n");
	}
	public void MostrarUsuarioDepartamentoCabeceraErrores(Integer i)
	{
		System.out.println("\n\n" + "		ERRORES en Relacion nº " + (i+1));
		System.out.println("_________________________________________________________________" + "\n");
	}
	public void MostrarUsuarioDepartamentoFinErrores()
	{
		System.out.println("\n\n\n" +"****TODOS LOS ERRORES DE RELACIONES SOLUCIONADOS*****");
		System.out.println("\n" + "GUARDANDO...");	
	}
	public void MostrarUsuarioDepartamentoExportarExcel()
	{
		System.out.println("\n\n");
		System.out.println("Ha elegido Exportar la lista de Relaciones a Excel");
		System.out.println("\n");
		System.out.println("Introduzca la ruta donde quiere crear el archivo (.xls):  ");
	}
	public void MostrarUsuarioDepartamentoExportarExcelNombre()
	{
		System.out.println("Introduzca nombre del archivo Excel (SIN FORMATO): ");
	}
	public void  MostrarUsuarioDepartamentoExportarExcelExito(String ruta)
	{
		System.out.println("\n");
		System.out.println("Se ha exportado a Excel la lista de Relaciones con EXITO");
		System.out.println("Ruta del archivo: " + ruta);
	}
	public void  MostrarUsuarioDepartamentoExportarExcelFracaso(String ruta)
	{
		System.out.println("\n");
		System.out.println("No se ha podido exportar a Excel la lista de Relaciones");
		System.out.println("No existe: " + ruta);
	}
	public void MostrarUsuarioDepartamentoPrimeroAddUsuariosYDepartamentos()
	{
		System.out.println("\n\n");
		System.out.println("No se pueden añadir Relaciones, si no existe algun Usuario y algun Departamento");
	}
	
	
// -------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------- MOSTRAR MODIFICACIONES --------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	public void MostrarModificarAtributo(String atributo)
	{
		System.out.println("Ha elegido modificar el "+atributo+"\n\n");
	}
	public void MostrarModificarAtributoActualizado(String atributo, String antiguoAtributo, String nuevoAtributo)
	{
		System.out.println("\n\n" + "Se ha actualizado el "+ atributo);
		System.out.println("Antiguo "+ atributo +": " + antiguoAtributo);
		System.out.println("Nuevo "+ atributo +": " + nuevoAtributo);
	}
	public void MostrarModificarNumPersonas(String nombre)
	{
		System.out.println("\n\n");
		System.out.println("Tiene que Añadir o Borrar USUARIOS para modificar el Personal del Departamento: " + nombre);
	}
	
	

// -------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------- INFORME DE ERRORES EN LECTURA ----------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void MostrarInicioInformeErroresSolucionables()
	{
		System.out.print("\n\n");
		System.out.println("____________________________________INFORME DE ERRORES DE FORMATO____________________________________");
	}
	public void MostrarFinInformeErroresSolucionables(String archivo, int numeroErrores)
	{
		if(numeroErrores != 1){
			System.out.println("Se han encontrado: " +numeroErrores+" errores SOLUCIONABLES en la lectura del archivo: " + archivo);
			System.out.println("_____________________________________________________________________________________________________");
			System.out.print("\n\n");
		}else{
			System.out.println("Se ha encontrado: " +numeroErrores+" error SOLUCIONABLE en la lectura del archivo: " + archivo);
			System.out.println("_____________________________________________________________________________________________________");
			System.out.print("\n\n");
		}
		
	}
	public void MostrarInicioInformeErroresIrrecuperables()
	{
		System.out.print("\n\n");
		System.out.println("__________________________________INFORME DE ERRORES IRRECUPERABLES__________________________________");
	}
	public void MostrarFinInformeErroresIrrecuperables(String archivo, int numeroErrores)
	{
		if(numeroErrores != 1){
			System.out.println("Se han encontrado: " +numeroErrores+" errores NO SOLUCIONABLES en la lectura del archivo: " + archivo);
			System.out.println("_____________________________________________________________________________________________________");
			System.out.print("\n\n");
		}else{
			System.out.println("Se ha encontrado: " +numeroErrores+" error NO SOLUCIONABLE en la lectura del archivo: " + archivo);
			System.out.println("_____________________________________________________________________________________________________");
			System.out.print("\n\n");
		}
	}
	public void MostrarDatosCorrompidos(String archivo, int numeroLinea)
	{
		System.out.println("\n" + "****Los datos del archivo: " + archivo + " estan corrompidos en la linea: " + numeroLinea + "****\n");
	}
	public void MostrarErrorLecturaFormatoAtributo(String atributo, String atributoConError)
	{
		System.out.println("\n\n" +"ERROR de Formato en "+atributo+": " + atributoConError);
	}
	public void MostrarErrorLecturaAtributoRepetido(String atributo, String atributoRepetido)
	{
		System.out.println("\n\n" +"ERROR: "+atributo+" repetido: " + atributoRepetido);
	}
	
	
	
// -------------------------------------------------------------------------------------------------------------------------------------
// --------------------------------------------------------- MOSTRAR ERRORES -----------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public void MostrarErrorEscrituraFichero()
	{
		System.out.println("No se puede escribir en fichero");
	}	
	public void MostrarErrorFormato()
	{
		System.out.println("ERROR: Formato Invalido");
	}
	public void MostrarErrorOpcion()
	{
		System.out.println("ERROR: Opcion INVALIDA");
	}
	public void MostrarExportacionPdf()
	{
		System.out.println("Se ha creado archivo .pdf en el directorio raiz.");
	}
}




