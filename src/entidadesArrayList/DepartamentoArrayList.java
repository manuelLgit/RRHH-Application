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

import entidades.Departamento;
import entidadesUtilidades.MostrarPorPantalla;

public class DepartamentoArrayList {
	
	private ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	private int departamentosMaximos = 100;
	
	
	//Metodos para Obtener los datos del ArrayList de Departamentos
	public int sizeListaDepartamento()
	{
		return this.listaDepartamentos.size();
	}
	public ArrayList<Departamento> getListaDepartamentos()
	{
		return this.listaDepartamentos;
	}
	public int getDepartamentosMaximos()
	{
		return this.departamentosMaximos;
	}
	public void changeDepartamentosMaximos(int n)
	{
		this.departamentosMaximos = n;
	}
	public Departamento getDepartamento(int n)
	{
		return this.listaDepartamentos.get(n);
	}
	
	
	//Metodo para añadir Departamento a la lista de Departamentos
	public void addDepartamento(int atr1, String atr2, String atr3, int atr4)
	{
		Departamento nuevoDepartamento;
		nuevoDepartamento = new Departamento(atr1, atr2, atr3, atr4);
		this.listaDepartamentos.add(nuevoDepartamento);
	}
	private void addDepartamento(Departamento nuevoDepartamento)
	{
		this.listaDepartamentos.add(nuevoDepartamento);
	}
	
	
	//Metodos para buscar un Departamento segun uno de sus atributos: IdDepartamento, Nombre, Director o NumPersonas
	public Departamento searchDepartamentoIdDepartamento(int atr1)
	{
		for (Departamento resultado : this.listaDepartamentos) {
			if (resultado.getIdDepartamento() == atr1) {
				return resultado;
			}
		}
		return null;
	}
	public Departamento searchDepartamentoNombre(String atr2)
	{
		for (Departamento resultado : this.listaDepartamentos) {
			if (resultado.getNombre().equalsIgnoreCase(atr2)) {
				return resultado;
			}
		}
		return null;
	}
	public DepartamentoArrayList searchDepartamentoDirector(String atr3)
	{
		DepartamentoArrayList resultado = new DepartamentoArrayList();
		for (Departamento departamento : this.listaDepartamentos) {
			if(departamento.getDirector().equalsIgnoreCase(atr3)){
				resultado.addDepartamento(departamento);
			}
		}
		return resultado;
	}
	public DepartamentoArrayList searchDepartamentoNumPersonas(int atr4)
	{
		DepartamentoArrayList resultado = new DepartamentoArrayList();
		for (Departamento departamento : this.listaDepartamentos) {
			if(departamento.getNumPersonas() == atr4){
				resultado.addDepartamento(departamento);
			}
		}
		return resultado;
	}
	
	
	//Metodo para borrar cierto Departamento de la Lista de Departamentos
	public boolean deleteDepartamento(Departamento borrarDepartamento)
	{
		int existeDepartamento = -1;
		for (int i = 0; i < this.listaDepartamentos.size(); i++) {
			if (this.listaDepartamentos.get(i).equals(borrarDepartamento)) {
				existeDepartamento=i;
			}
		}
		if(existeDepartamento != -1){
			this.listaDepartamentos.remove(existeDepartamento);
			return true;
		}else{
			return false;
		}
	}
	
	//Metodo para exportar a Excel
	
	
	public boolean exportExcelDepartamentosArrayList (String rutaArchivoExcel)
	{
		MostrarPorPantalla show = new MostrarPorPantalla();
		String rutaArchivo= rutaArchivoExcel;
		String hoja="Hoja1";
		
		HSSFWorkbook libro= new HSSFWorkbook();
		HSSFSheet hoja1 = libro.createSheet(hoja);
		
		
		//cabecera de la hoja de excel
		String [] header= new String[]{"ID.DEPARTAMENTO","NOMBRE","DIRECTOR","PERSONAL"};
		
		
		//crear los datos para introducirlos en excel
		ArrayList<String[]> departamentosExcell = new ArrayList<String[]>();
		for (int i = -1; i < this.listaDepartamentos.size(); i++) {
			if(i==-1){
				departamentosExcell.add(header);
			}else{
			Integer idDepartamento = listaDepartamentos.get(i).getIdDepartamento();
			String textoIdDepartamento = idDepartamento.toString();
			String textoNombre = listaDepartamentos.get(i).getNombre();
			String textoDirector = listaDepartamentos.get(i).getDirector();
			Integer numPersonas = listaDepartamentos.get(i).getNumPersonas();
			String textoNumPersonas = numPersonas.toString();
			String[] atributosDepartamento = {textoIdDepartamento,textoNombre,textoDirector,textoNumPersonas};
			departamentosExcell.add(atributosDepartamento);
			}
		}
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
		Font font = libro.createFont();
		font.setBold(true);
		style.setFont(font);
		
		for (int i = 0; i < departamentosExcell.size(); i++) {
			HSSFRow row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <header.length; j++) {
				HSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
				cell.setCellValue(departamentosExcell.get(i)[j]); //se añade el contenido
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
