package entidadesUtilidades;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import entidades.UsuarioDepartamento;
import entidades.Usuario.TipoSexo;
import entidadesArrayList.DepartamentoArrayList;
import entidadesArrayList.UsuarioArrayList;
import entidadesArrayList.UsuarioDepartamentoArrayList;

public class CheckFormat {
	
	private static Logger logger  = Logger.getLogger("loggerProyecto");
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------METODOS PARA COMPROBAR EL FORMATO DE LOS ATRIBUTOS DEL USUARIO-------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-checkFormatIdUsuario.
// 2-checkFormatApellidos.
// 3-checkFormatDni.
// 4-checkFormatSexo.
// 5-checkFormatAltura.
// 6-checkFormatCorreo.

		
	
	public boolean checkFormatIdUsuario(String comprueba, UsuarioArrayList listaUsuarios)
	{
		logger.info("checkFormatIdUsuario con: " + comprueba);
		if(comprueba == null){
			return false;
		}
		String patternIdUsuario = "[0-9]{1,7}";
		if(Pattern.matches(patternIdUsuario, comprueba) == false){
			return false;
		}
		int numeroIdUsuario = Integer.parseInt(comprueba);
		if(numeroIdUsuario > listaUsuarios.getUsuariosMaximos()){
			return false;
		}
		return true;
	}
	public boolean checkFormatApellidos(String comprueba)
	{
		logger.info("checkFormatApellidos con: " + comprueba);
		int longMaxApellidos = 76;
		if(comprueba == null){
			return false;
		}
		if (comprueba.length()>longMaxApellidos){
			return false;
		}
		String patternApellidos = "[a-zA-ZñÑáàâéèêíìîóòôúùûÁÀÂÉÈÊÍÌÎÓÒÔÚÙÛ\\h]{1,}/[a-zA-ZñÑ\\h]{0,}";
		if(Pattern.matches(patternApellidos, comprueba) == false){
			return false;
		}
		return true;
	}
	public boolean checkFormatDni(String comprueba)
	{
		logger.info("checkFormatDni con: " + comprueba);
		String patternDni = "[0-9]{8}[a-zA-Z]{1}";
		if(Pattern.matches(patternDni, comprueba) == false){
			return false;
		}
		return true;
	}
	public boolean checkFormatSexo(String comprueba)
	{
		logger.info("checkFormatSexo con: " + comprueba);
		if(comprueba.equalsIgnoreCase(TipoSexo.MASCULINO.toString()))
		{
			return true;
		}else if (comprueba.equalsIgnoreCase(TipoSexo.FEMENINO.toString())) {
			return true;
		}else if (comprueba.equalsIgnoreCase(TipoSexo.NO_ESPECIFICADO.toString())) {
			return true;
		}else{
			return false;
		}
	}
	public boolean checkFormatAltura(String comprueba)
	{
		logger.info("checkFormatAltura con: " + comprueba);
		if(comprueba == null){
			return false;
		}
		String patternAltura = "[0-9]{1}\\.[0-9]{0,2}";
		if(Pattern.matches(patternAltura, comprueba) == false){
			return false;
		}
		return true;
	}
	public boolean checkFormatCorreo(String comprueba)
	{
		logger.info("checkFormatCorreo con: " + comprueba);
		int longMaxCorreo = 80;
		int minCaracteresX = 3;
		int minCaracteresY = 3;
		int minCaracteresZ = 2;
		if(comprueba == null){
			return false;
		}
		if (comprueba.length()>longMaxCorreo){
			return false;
		}
		String patternCorreo = "[a-zA-Z0-9\\._-]{"+minCaracteresX+",}@[a-zA-Z0-9\\._-]{"+minCaracteresY+",}\\.[a-z]{"+minCaracteresZ+",}";
		if(Pattern.matches(patternCorreo, comprueba) == false){
			return false;
		}
		return true;
	}
	
	
	
	
//-----------------------------------------------------------------------------------------------------------------------------------
//------------------------------------METODOS PARA COMPROBAR EL FORMATO DE LOS ATRIBUTOS DEL DEPARTAMENTO----------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-checkFormatIdDepartamento.
// 2-checkFormatNombre.
// 3-checkFormatDirector.
// 4-checkFormatNumPersonas.
		
		
	public boolean checkFormatIdDepartamento (String comprueba, DepartamentoArrayList listaDepartamentos)
	{
		logger.info("checkFormatIdDepartamento con: " + comprueba);
		if(comprueba == null){
			return false;
		}
		String patternIdDepartamento = "[0-9]{1,7}";
		if(Pattern.matches(patternIdDepartamento, comprueba) == false){
			return false;
		}
		int numeroIdDepartamento = Integer.parseInt(comprueba);
		if(numeroIdDepartamento > listaDepartamentos.getDepartamentosMaximos()){
			return false;
		}
		return true;
	}
	public boolean checkFormatNombre (String comprueba)
	{
		logger.info("checkFormatNombre con: " + comprueba);
		int longMaxNombre = 50;
		if(comprueba == null){
			return false;
		}
		if(comprueba.length() > longMaxNombre){
			return false;
		}
		return true;
	}
	public boolean checkFormatDirector (String comprueba)
	{
		logger.info("checkFormatDirector con: " + comprueba);
		Integer longMaxNombreDirector = 30;
		Integer longMaxApellidoDirector = 30;
		// \\h para incluir el caracter en ESPACIO
		String patternNombreDirector = "[a-zA-ZñÑáàâéèêíìîóòôúùûÁÀÂÉÈÊÍÌÎÓÒÔÚÙÛ\\h]{0,"+longMaxNombreDirector.toString()+"}"; 
		String patternApellidoDirector = "[a-zA-ZñÑáàâéèêíìîóòôúùûÁÀÂÉÈÊÍÌÎÓÒÔÚÙÛ\\h]{0,"+longMaxApellidoDirector.toString()+"}";
		String separador = "/";
		String patternDirector = patternNombreDirector + separador + patternApellidoDirector;
		if(Pattern.matches(patternDirector, comprueba)){
			return true;
		}
		return false;
	}
	public boolean checkFormatNumPersonas (String comprueba)
	{
		logger.info("checkFormatNumPersonas con: " + comprueba);
		if(comprueba == null){
			return false;
		}
		String patternNumPersonas = "[0-9]{1,7}";
		if(Pattern.matches(patternNumPersonas, comprueba) == false){
			return false;
		}
		return true;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------
//--------------------------------METODOS PARA COMPROBAR EL FORMATO DE LOS ATRIBUTOS DEL USUARIO_DEPARTAMENTO------------------------
//-----------------------------------------------------------------------------------------------------------------------------------
// INDICE:
// 1-checkFormatFecha.
// 2-checkFormatTextoFecha.
// 3-checkFecha.
// 4-checkYearBisiesto.
// 5-checkUsuarioDepartamentoRepetido.

	public boolean checkFormatFecha(String comprueba)
	{
		logger.info("checkFormatFecha con: " + comprueba);
		if(comprueba == null){
			return false;
		}
		String patternFecha = "[0-9]{1,17}";
		if(Pattern.matches(patternFecha, comprueba) == false){
			return false;
		}
		return true;
	}
	public boolean checkFormatTextoFecha (String comprueba)
	{
		logger.info("checkFormatTextoFecha con: " + comprueba);
		if(comprueba == null){
			return false;
		}
		String patternTextoFecha = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
		if(Pattern.matches(patternTextoFecha, comprueba) == false){
			return false;
		}
		String[] fecha;
		try{
			fecha = comprueba.split("\\/");
		}catch(PatternSyntaxException e){
			return false;
		}
		int dd = Integer.parseInt(fecha[0]);
		int mm = Integer.parseInt(fecha[1]);
		int yyyy = Integer.parseInt(fecha[2]);
		if(yyyy < 1970){
			return false;
		}
		if(checkFecha(dd,mm,yyyy) == false){
			return false;
		}
		return true;
	}
	private static boolean checkFecha(int dd, int mm, int yyyy)
	{
		logger.info("checkfecha con: " + dd +" / " + mm + " / " + yyyy);
		int diasMax = 31;
		int mesesMax = 12;
		int diasFebBis = 29;
		int diasFebNoBis = 28;
		int FEB = 2, ABR = 4, JUN = 6, SEP = 9, NOV = 11;
		if(dd == 00 || dd > diasMax || mm == 00 || mm > mesesMax ){
			return false;
		}
		if(mm == ABR || mm == JUN || mm == SEP || mm == NOV){
			if(dd == 31){
				return false;
			}
		}
		if(checkYearBisiesto(yyyy)){
			if(mm == FEB && dd > diasFebBis){
				return false;
			}
		}else{
			if(mm == FEB && dd > diasFebNoBis){
				return false;
			}
		}
		return true;
	}
	private static boolean checkYearBisiesto(int year)
	{
		logger.info("checkBisiesto con: " + year);
		if(year%4 == 0 && year%100 !=0 || year%400 ==0){
			return true;
		}else{
			return false;
		}
	}
	public boolean checkUsuarioDepartamentoRepetido(int idUsuario, int idDepartamento, UsuarioDepartamentoArrayList listaRelaciones)
	{
		logger.info("checkUsuarioDepartamentoRepetido con: " + idUsuario + " / " +idDepartamento);
		UsuarioDepartamento relacionRepetida = new UsuarioDepartamento(idUsuario, idDepartamento, 0);
		for (UsuarioDepartamento comprueba : listaRelaciones.getListaUsuarioDepartamentos()) {
			if(comprueba.equals(relacionRepetida)){
				return true;
			}
		}
		return false;
	}
	
	
}
