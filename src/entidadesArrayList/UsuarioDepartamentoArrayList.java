package entidadesArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import entidades.UsuarioDepartamento;
import entidadesUtilidades.MostrarPorPantalla;

public class UsuarioDepartamentoArrayList {
	
	private ArrayList<UsuarioDepartamento> listaUsuarioDepartamentos = new ArrayList<UsuarioDepartamento>();
	
	
	//Metodos para Obtener los datos del ArrayList de UsuarioDepartamentos
	public int sizeListaUsuarioDepartamentos()
	{
		return this.listaUsuarioDepartamentos.size();
	}
	public ArrayList<UsuarioDepartamento> getListaUsuarioDepartamentos()
	{
		return this.listaUsuarioDepartamentos;
	}
	public UsuarioDepartamento getUsuarioDepartamento(int n)
	{
		return this.listaUsuarioDepartamentos.get(n);
	}
	
	
	//Metodo para añadir UsuarioDepartamento a la lista de UsuarioDepartamentos
	public void addUsuarioDepartamento(int atr1, int atr2,long atr3)
	{
		UsuarioDepartamento nuevoUsuarioDepartamento;
		nuevoUsuarioDepartamento = new UsuarioDepartamento(atr1, atr2,atr3);
		this.listaUsuarioDepartamentos.add(nuevoUsuarioDepartamento);
	}
	private void addUsuarioDepartamento(UsuarioDepartamento nuevoUsuarioDepartamento)
	{
		this.listaUsuarioDepartamentos.add(nuevoUsuarioDepartamento);
	}
	
	//Metodos para buscar un UsuarioDepartamento segun uno de sus atributos: IdUsuario, IdDepartamento
	public UsuarioDepartamentoArrayList searchUsuarioDepartamentoIdUsuario(int atr1)
	{
		UsuarioDepartamentoArrayList resultado = new UsuarioDepartamentoArrayList();
		for (UsuarioDepartamento usuarioDepartamento : this.listaUsuarioDepartamentos) {
			if(usuarioDepartamento.getIdUsuario() == atr1){
				resultado.addUsuarioDepartamento(usuarioDepartamento);
			}
		}
		return resultado;
	}
	public UsuarioDepartamentoArrayList searchUsuarioDepartamentoIdDepartamento(int atr2)
	{
		UsuarioDepartamentoArrayList resultado = new UsuarioDepartamentoArrayList();
		for (UsuarioDepartamento usuarioDepartamento : this.listaUsuarioDepartamentos) {
			if(usuarioDepartamento.getIdDepartamento() == atr2){
				resultado.addUsuarioDepartamento(usuarioDepartamento);
			}
		}
		return resultado;
	}
	public UsuarioDepartamentoArrayList searchUsuarioDepartamentoFecha(long atr3)
	{
		UsuarioDepartamentoArrayList resultado = new UsuarioDepartamentoArrayList();
		for (UsuarioDepartamento usuarioDepartamento : this.listaUsuarioDepartamentos) {
			if(usuarioDepartamento.getFecha() == atr3){
				resultado.addUsuarioDepartamento(usuarioDepartamento);
			}
		}
		return resultado;
	}
	
	
	//Metodo para borrar cierto UsuarioDepartamento de la Lista de UsuarioDepartamentos
	public boolean deleteUsuarioDepartamento(UsuarioDepartamento borrarUsuarioDepartamento)
	{
		int existeUsuarioDepartamento = -1;
		for (int i = 0; i < this.listaUsuarioDepartamentos.size(); i++) {
			if (this.listaUsuarioDepartamentos.get(i).equals(borrarUsuarioDepartamento)) {
				existeUsuarioDepartamento=i;
			}
		}
		if(existeUsuarioDepartamento != -1){
			this.listaUsuarioDepartamentos.remove(existeUsuarioDepartamento);
			return true;
		}else{
			return false;
		}
	}
	
	//Metodo para exportar a excell
	public boolean exportExcelUsuarioDepartamentoArrayList (String rutaArchivoExcel)
	{
		MostrarPorPantalla show = new MostrarPorPantalla();
		String rutaArchivo= rutaArchivoExcel;
		String hoja="Hoja1";
		
		HSSFWorkbook libro= new HSSFWorkbook();
		HSSFSheet hoja1 = libro.createSheet(hoja);
		
		
		//cabecera de la hoja de excel
		String [] header= new String[]{"ID.USUARIO","ID.DEPARTAMENTO","FECHA"};
		
		
		//crear los datos para introducirlos en excel
		ArrayList<String[]> usuarioDepartamentoExcell = new ArrayList<String[]>();
		for (int i = -1; i < this.listaUsuarioDepartamentos.size(); i++) {
			if(i==-1){
				usuarioDepartamentoExcell.add(header);
			}else{
			Integer idUsuario = this.listaUsuarioDepartamentos.get(i).getIdUsuario();
			String textoIdUsuario =  idUsuario.toString();
			Integer idDepartamento = this.listaUsuarioDepartamentos.get(i).getIdDepartamento();
			String textoIdDepartamento = idDepartamento.toString();
			String textoFecha = this.listaUsuarioDepartamentos.get(i).getFechaTexto().toString();
			String[] atributosUsuarioDepartamento = {textoIdUsuario,textoIdDepartamento,textoFecha};
			usuarioDepartamentoExcell.add(atributosUsuarioDepartamento);
			}
		}
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
		Font font = libro.createFont();
		font.setBold(true);
		style.setFont(font);
		
		for (int i = 0; i < usuarioDepartamentoExcell.size(); i++) {
			HSSFRow row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <header.length; j++) {
				HSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
				cell.setCellValue(usuarioDepartamentoExcell.get(i)[j]); //se añade el contenido
			}
		
		}
		
		File file;
		file = new File(rutaArchivo);
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
