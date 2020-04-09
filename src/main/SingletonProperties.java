package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SingletonProperties  {
	
	private static SingletonProperties instancia=null;
	private Properties p;
	private SingletonProperties() throws IOException {
	 
		p = new Properties();
		p.load(new FileInputStream(new File(".".concat(System.getProperty("file.separator")).concat("properties").concat(System.getProperty("file.separator")).concat("configProperties.txt"))));
		
	}
	
	public static SingletonProperties getInstancia() throws IOException {
		 
		if (instancia==null) {
		 
			instancia= new SingletonProperties();
		}
		return instancia;
	}
	
	public String getPropiedad(String clave) {
		 
		return p.getProperty(clave);
	}
}
