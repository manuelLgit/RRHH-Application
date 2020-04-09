package entidadesArrayList;
import java.util.ArrayList;
import entidades.Usuario;
import entidades.Usuario.TipoSexo;
import entidadesUtilidades.MostrarPorPantalla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;


public class UsuarioArrayList {
	
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private int usuariosMaximos = 1000;
	
	
	//Metodos para OBTENER o CAMBIAR los atributos de los UsuarioArraylist
	//---------------------------------------------------------------------
	// 1.sizeListaUsuarios
	// 2.getListaUsuarios
	// 3.getUsuariosMaximos
	// 4.changeUsuariosMaximos
	// 5.getUsuario
	
	
	
	public int sizeListaUsuarios()
	{
		return this.listaUsuarios.size();
	}
	public ArrayList<Usuario> getListaUsuarios()
	{
		return this.listaUsuarios;
	}
	public int getUsuariosMaximos()
	{
		return this.usuariosMaximos;
	}
	public void changeUsuariosMaximos(int n)
	{
		this.usuariosMaximos = n;
	}
	public Usuario getUsuario(int n)
	{
		return this.listaUsuarios.get(n);
	}
	
	
	//Metodos para añadir Usuarios a la lista de de Usuarios
	//-------------------------------------------------------
	// 1.addUsuario(por sus atributos)
	// 2.addUsuario(por objeto Usuario)
	
	
	
	public void addUsuario(int atr1, String atr2, String atr3, String atr4, float atr5, String atr6)
	{
		Usuario nuevoUsuario;
		nuevoUsuario = new Usuario(atr1, atr2, atr3, atr4, atr5, atr6);
		this.listaUsuarios.add(nuevoUsuario);
	}
	private void addUsuario(Usuario nuevoUsuario)
	{
		this.listaUsuarios.add(nuevoUsuario);
	}
	
	
	//Metodos para buscar un Usuario segun uno de sus atributos
	//------------------------------------------------------------
	// 1.searchUsuarioIdUsuario
	// 2.searchUsuarioApellidos
	// 3.searchUsuarioDni
	// 4.searchUsuarioSexo
	// 5.searchUsuarioAltura
	// 6.searchUsuarioCorreo
	
	
	
	public Usuario searchUsuarioIdUsuario(int atr1)
	{
		for (Usuario resultado : this.listaUsuarios) {
			if (resultado.getIdUsuario() == atr1) {
				return resultado;
			}
		}
		return null;
	}
	public UsuarioArrayList searchUsuarioApellidos(String atr2)
	{
		UsuarioArrayList resultado = new UsuarioArrayList();
		for (Usuario usuario : this.listaUsuarios) {
			if(usuario.getApellidos().equalsIgnoreCase(atr2)){
				resultado.addUsuario(usuario);
			}
		}
		return resultado;
	}
	public Usuario searchUsuarioDni(String atr3)
	{
		for (Usuario resultado : this.listaUsuarios) {
			if (resultado.getDni().equalsIgnoreCase(atr3)) {
				return resultado;
			}
		}
		return null;
	}
	public UsuarioArrayList searchUsuarioSexo(String atr4)
	{
		UsuarioArrayList resultado = new UsuarioArrayList();
		for (Usuario usuario : this.listaUsuarios) {
			try{
				if(usuario.getSexo().compareTo(TipoSexo.valueOf(atr4)) == 0){
					resultado.addUsuario(usuario);
				}
			}catch(IllegalArgumentException e){
				if(usuario.getSexo().compareTo(TipoSexo.NO_ESPECIFICADO) == 0){
					resultado.addUsuario(usuario);
				}
			}
		}
		return resultado;
	}
	public UsuarioArrayList searchUsuarioAltura(float atr5)
	{
		UsuarioArrayList resultado = new UsuarioArrayList();
		for (Usuario usuario : this.listaUsuarios) {
			if(usuario.getAltura() == atr5){
				resultado.addUsuario(usuario);
			}
		}
		return resultado;
	}
	public Usuario searchUsuarioCorreo(String atr6)
	{
		for (Usuario resultado : this.listaUsuarios) {
			if (resultado.getCorreo().compareTo(atr6) == 0){
				return resultado;
			}
		}
		return null;
	}
	
	
	//Metodo para borrar cierto Usuario de la Lista de Usuarios
	//---------------------------------------------------------
	// 1.deleteUsuario
	
	
	
	public boolean deleteUsuario(Usuario borrarUsuario)
	{
		int exiteUsuario = -1;
		for (int i = 0; i < this.listaUsuarios.size(); i++) {
			if (this.listaUsuarios.get(i).equals(borrarUsuario)) {
				exiteUsuario =i;
			}
		}
		if(exiteUsuario != -1){
			this.listaUsuarios.remove(exiteUsuario);
			return true;
		}else{
			return false;
		}
	}
	
	
	//Metodo para Exportar a Excel
	//----------------------------
	// 1.exportExcelUsuarioArrayList
	
	
	
	public boolean exportExcelUsuarioArrayList (String rutaArchivoExcel)
	{
		MostrarPorPantalla show = new MostrarPorPantalla();
		String rutaArchivo = rutaArchivoExcel;
		String hoja="USUARIOS";
		
		HSSFWorkbook libro= new HSSFWorkbook();
		HSSFSheet hoja1 = libro.createSheet(hoja);
		
		
		//cabecera de la hoja de excel
		String [] header= new String[]{"ID.USUARIO","APELLIDOS","DNI","SEXO","ALTURA","CORREO"};
		
		
		//crear los datos para introducirlos en excel
		ArrayList<String[]> usuariosExcell = new ArrayList<String[]>();
		for (int i = -1; i < this.listaUsuarios.size(); i++) {
			if(i==-1){
				usuariosExcell.add(header);
			}else{
			Integer idUsuario = listaUsuarios.get(i).getIdUsuario();
			String textoIdUSuario = idUsuario.toString();
			String textoApellidos = listaUsuarios.get(i).getApellidos();
			String textoDni = listaUsuarios.get(i).getDni();
			String textoSexo = listaUsuarios.get(i).getSexo().toString();
			Float altura = listaUsuarios.get(i).getAltura();
			String textoAltura = altura.toString();
			textoAltura = textoAltura.replaceAll("\\.", ",");
			String textoCorreo = listaUsuarios.get(i).getCorreo();
			String[] atributosUsuario = {textoIdUSuario,textoApellidos,textoDni,textoSexo,textoAltura,textoCorreo};
			usuariosExcell.add(atributosUsuario);
			}
		}
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
		Font font = libro.createFont();
		font.setBold(true);
		style.setFont(font);
		
		for (int i = 0; i < usuariosExcell.size(); i++) {
			HSSFRow row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <header.length; j++) {
				HSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
				cell.setCellValue(usuariosExcell.get(i)[j]); //se añade el contenido
			}
		
		}
		
		File file = new File(rutaArchivo);
		try (FileOutputStream fileOuS = new FileOutputStream(file)){						
			if (file.exists()) {// si el archivo existe se elimina
				file.delete();
			}
			libro.write(fileOuS);
			fileOuS.flush();
			fileOuS.close();
			libro.close();
			return true;
			
		} catch (FileNotFoundException e) {
			show.MostrarErrorEscrituraFichero();
			return false;
		}catch (IOException e) {
			show.MostrarErrorEscrituraFichero();
			return false;
		}
	}
}
