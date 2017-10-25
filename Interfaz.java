import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.text.*;
import java.applet.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Interfaz extends Applet
{

	// variables para la conexion con la pagina ASP / PHP

	String hostName, applicationPath, webServerStr, applicationGet;
	int port;  
	int nlinea=0;
	boolean bloqueado=true,nuevo=false;
	String sql;

	// Creación de los controles de controles

	// Etiquetas	
	Label etiqueta_ID;
	Label etiqueta_idvuelo;
	Label etiqueta_ciudadorigen;
	Label etiqueta_ciudaddestino;
	Label etiqueta_fecha;
	Label etiqueta_compania;
	Label etiqueta_avion;
	Label etiqueta_duracion;
	Label etiqueta_plazas;

	// Campos de Texto

	TextField campo_ID;
	TextField campo_ciudadorigen;
	TextField campo_ciudaddestino;
	TextField campo_fecha;
	TextField campo_entrada_fecha;
	TextField campo_compania;
	TextField campo_avion;
	TextField campo_duracion;
	TextField campo_entrada_duracion;
	TextField campo_plazas;
	TextField campo_entrada_plazas;


	// Listas desplegables
	Choice lista_idvuelo;
	Choice lista_origen;
	Choice lista_destino;
	//Choice lista_fecha;
	Choice lista_compania;
	Choice lista_avion;

	// Botones		
	Button boton_modificar;	
	Button boton_nuevo;	

	// Areas de texto		
	TextArea ta;

	@Override
	public void init()
	{
		//etiqueta_idvuelo= new Label("BUSCAR ID");
		//etiqueta_idvuelo.setForeground(Color.RED);
		etiqueta_ID = new Label("ID");
		etiqueta_ciudadorigen = new Label("Origen");
		etiqueta_ciudaddestino = new Label("Destino");
		etiqueta_fecha= new Label("Fecha");
		etiqueta_compania = new Label("Compania");
		etiqueta_avion = new Label("Avion");
		etiqueta_duracion = new Label("Duracion");
		etiqueta_plazas = new Label("Plazas");


		lista_idvuelo = new Choice();
		lista_idvuelo.add("Seleccione...");
		lista_origen = new Choice();
		lista_origen.add("Seleccione...");
		lista_destino = new Choice();
		lista_destino.add("Seleccione...");
		campo_entrada_fecha = new TextField(10);
		campo_entrada_fecha.setEditable(false);
		//lista_fecha = new Choice();
		//lista_fecha.add("Seleccione...");
		lista_compania = new Choice();
		lista_compania.add("Seleccione...");
		lista_avion = new Choice();
		lista_avion.add("Seleccione...");
		//lista_idvuelo.add("1");
		//lista_idvuelo.add("2");
		//... aquí se rellenarían los valores de la lista desplegable...


		campo_ID = new TextField(9);
		campo_ID.setEditable(false);
		campo_ciudadorigen= new TextField(9);
		campo_ciudadorigen.setEditable(false);
		campo_ciudaddestino= new TextField(9);
		campo_ciudaddestino.setEditable(false);
		campo_fecha= new TextField(12);
		campo_fecha.setEditable(false);
		campo_compania= new TextField(9);
		campo_compania.setEditable(false);
		campo_avion= new TextField(9);
		campo_avion.setEditable(false);
		campo_duracion = new TextField(9);
		campo_duracion.setEditable(false);
		campo_plazas= new TextField(9);
		campo_plazas.setEditable(false);
		campo_entrada_duracion = new TextField(9);
		campo_entrada_duracion.setEditable(false);
		campo_entrada_plazas = new TextField(9);
		campo_entrada_plazas.setEditable(false);

		boton_modificar=new Button(" Modificar/Guardar ");
		boton_nuevo=new Button(" Nuevo ");


		ta=new TextArea(3,117);

		// Disposicion de los controles en la pantalla     

		GridBagLayout gbl=new GridBagLayout();

		GridBagConstraints gbc=new GridBagConstraints(); 
		// El objeto gbc tiene los valores por defecto para gridwidth, anchor, fill, etc. 
		// el cambio permanece hasta que se vuelve a establecer otro valor.

		// Establecer el gestor de diseño GridBagLayout mediante setLayout.
		setLayout(gbl);

		// Se añaden los controles por filas
		gbc.anchor=GridBagConstraints.NORTH; 
		// los objetos están anclados a la parte de arriba de su celda correspondiente
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.insets=new Insets(20,20,0,0); 
		// especifica el padding entre los componentes del objeto gbc (padding abajo,izquierda,derecha,arriba)

		// Nueva fila

		gbc.anchor=GridBagConstraints.WEST;  
		// los componentes se anclan a la izquierda dentro de su celda correspondiente
		gbc.gridwidth=1; 
		// valor por defecto para la anchura, para que los objetos ocupen la anchura disponible

		//add(etiqueta_idvuelo, gbc);
		add(etiqueta_ID, gbc);
		add(etiqueta_ciudadorigen, gbc);
		add(etiqueta_ciudaddestino, gbc);
		add(etiqueta_fecha, gbc);
		add(etiqueta_compania, gbc);
		add(etiqueta_avion, gbc);
		add(etiqueta_duracion, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;  
		// Esto hace que el siguiente componente sea el último de la fila
		add(etiqueta_plazas, gbc);

		// Nueva fila
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridwidth=1;
		add(lista_idvuelo, gbc);
		add(lista_origen, gbc);
		add(lista_destino, gbc);
		add(campo_entrada_fecha, gbc);
		add(lista_compania, gbc);
		add(lista_avion, gbc);
		add(campo_entrada_duracion, gbc);
		add(campo_entrada_plazas, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(boton_nuevo, gbc);
		
		// Nueva fila
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridwidth=1;
		add(campo_ID, gbc);
		add(campo_ciudadorigen, gbc);
		add(campo_ciudaddestino, gbc);
		add(campo_fecha, gbc);
		add(campo_compania, gbc);
		add(campo_avion, gbc);
		add(campo_duracion, gbc);
		add(campo_plazas, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(boton_modificar, gbc);


		// Nueva fila
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridwidth=1;
		gbc.gridx=1; // desplazo el area de texto 1 unidad a la derecha
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(ta, gbc);


		// Ejemplos de Inicializacion de los controles de lista
		// llama a la pagina ASP con diferentes consultas
		// vuelos origen, vuelos destino....y rellena las listas

		
		String intro;
		intro="Seleccione un ID para visualizar y/o modificar un vuelo. \nPulse en nuevo para crear un vuelo nuevo";
		ta.setText(intro);
		recibe_datos("IDVUELO","0");	
		recibe_datos("ORIGEN","0");		
		recibe_datos("AVION","0");		
		recibe_datos("COMPANIA","0");	
		Bloquea();

	} // termina public void init()

	



//  Función que llama a la pagina ASP indicando que solicita datos
// dependiando del valor del campo Accion recibiremos datos
// para rellenar los controles de ciudades, vuelos, avion, compañia...
   
public void recibe_datos(String consulta, String idvuelo)
{

	try 
	{
		// Pagina ASP destino

		// Obtiene la URL del documento donde el applet está incrustado
		URL hostURL = getDocumentBase(); 

		// Sustituimos el nombre del fichero HTML por el nombre del archivo ASP destino
		applicationPath=hostURL.getPath().toString().replace("/Administrador.asp","/servApplet.asp");

		// Obtenemos el nombre del host
		//hostName = hostURL.getHost();

		// Obtenemos el puerto
		port = hostURL.getPort();

		if (port == -1)
			port = 80;

		webServerStr = "http://localhost:" + port + applicationPath;

		//webServerStr = "http://" + hostName + ":" + port + applicationPath;


		applicationGet = webServerStr + "?" + 

		URLEncoder.encode("Accion") + "=" + consulta+ "&" +

		URLEncoder.encode("idvuelo") + "=" + idvuelo;

		//ta.append(applicationGet);  // mostrar en una caja de texto la variable applicationGet


		/*   
		Si llamamos a esta función de la siguiente forma recibe_datos("Consulta1","0"); 

		Tendríamos en applicationGet:
		http://localhost:80/ruta_del_fichero_ASP/serv_applet.asp?Accion=Consulta1&idvuelo=0

		*/


		// Abrimos la conexion 

		/* Se crea una conexión http al servidor por el que fluyen los datos con varias clases Java que trabajan conjuntamente
		a varios niveles   */

		URL servApplet = new URL(applicationGet);

		URLConnection servAppletConnection = servApplet.openConnection();

		DataInputStream dis = new DataInputStream(servAppletConnection.getInputStream());

		String inputLine; // obtebemos la cadena de texto que devuelve la página de servidor servapplet.asp



		// Leemos los datos y rellenamos el control que proceda
		// según la consulta que hemos realizado a través de la llamada correspondiente

		// mientras la consulta realizada al fichero serv_applet.asp vaya devolviendo tuplas, las tuplas empiezan por la fila 0n
		while ((inputLine = dis.readLine()) != null) 
		{      
			// si la consulta ejecutada es la Consulta1, por cada vuelta del bucle, añadimos una vuelo al cuadro de lista idvuelo
			if (consulta.equals("IDVUELO"))
			{
				lista_idvuelo.addItem(inputLine);
			}
			if (consulta.equals("ORIGEN"))
			{
				lista_origen.addItem(inputLine);
				lista_destino.addItem(inputLine);
			}
			if (consulta.equals("AVION"))
			{
				lista_avion.addItem(inputLine);
			}
			if (consulta.equals("COMPANIA"))
			{
				lista_compania.addItem(inputLine);
			}
			if (consulta.equals("idorigen"))
			{
				campo_ciudadorigen.setText(inputLine);
			}
			if (consulta.equals("iddestino"))
			{
				campo_ciudaddestino.setText(inputLine);
			}
			if (consulta.equals("idcompania"))
			{
				campo_compania.setText(inputLine);
			}
			if (consulta.equals("idavion"))
			{
				campo_avion.setText(inputLine);
			}	
			if (consulta.equals("Ejecutar"))
			{
				ta.setText(inputLine);
			}	

			if (consulta.equals("Vuelos"))
			{
				StringTokenizer st = new StringTokenizer(inputLine,"-");

				campo_ID.setText(st.nextToken());
				campo_ciudadorigen.setText(st.nextToken());
				campo_ciudaddestino.setText(st.nextToken());
				campo_fecha.setText(st.nextToken());
				campo_compania.setText(st.nextToken());
				campo_avion.setText(st.nextToken());
				campo_duracion.setText(st.nextToken());
				campo_plazas.setText(st.nextToken());
				ta.setText(st.nextToken());
				//ta.setText("HOLA");
			}
			
			if (consulta.equals("MaxID"))
			{				
				StringTokenizer st = new StringTokenizer(inputLine,"-");
				campo_ID.setText(st.nextToken());
				//ta.setText(st.nextToken());
				campo_ciudadorigen.setText("");
				campo_ciudaddestino.setText("");
				campo_fecha.setText("");
				campo_compania.setText("");
				campo_avion.setText("");
				campo_duracion.setText("");
				campo_plazas.setText("");
				campo_entrada_duracion.setText("");
				campo_entrada_plazas.setText("");
				//ta.setText("holi");
			}
			/* else if (consulta.equals("Consulta6"))
			// Códigos de vuelo disponibles
			lista_idvuelo.addItem(inputLine); */
			nlinea=nlinea+1;
		}
		// Cerramos la conexion  
		dis.close();

	} 
	catch (MalformedURLException me) 
	{
		System.out.println("MalformedURLException: " + me);
	} 
	catch (IOException ioe) 
	{
		System.out.println("IOException: " + ioe);
	}

}   // Termina public void recibe_datos()
	

//Capturamos Evento clic sobre el botón


// Control del evento producido click, enter.....
    public boolean action( Event evt,Object obj ) 
	{
	String sentencia;
		if( evt.target.equals( boton_modificar ) )
		{	
			if(bloqueado)
			{
				Desbloquea();
				bloqueado=false;
			}
			else
			{
				Bloquea();
				bloqueado=true;
				sentencia=CrearInsercion();
				recibe_datos("Ejecutar",sentencia);
				lista_idvuelo.removeAll();
				recibe_datos("IDVUELO","0");
				nuevo=false;
			}
		}
		
		if( evt.target.equals( boton_nuevo ) )
		{
			Desbloquea();
			bloqueado=false;
			recibe_datos("MaxID","0");
			nuevo=true;
			
		}
		
		
		// Aquí podemos controlar los eventos sobre los distintos controles del interfaz ...
		
		if( evt.target.equals( lista_idvuelo ) )
		{
			Bloquea();
			if(lista_idvuelo.getSelectedItem()!="Seleccione...")
				recibe_datos("Vuelos",lista_idvuelo.getSelectedItem());
			
		}
		
		if( evt.target.equals( lista_origen ) )
		{
			if(lista_origen.getSelectedItem()!="Seleccione...")
			{
				String ej,ej1;//Esto permite enviar elementos con espacios a la pagina asp
				ej=lista_origen.getSelectedItem();
				ej1=ej.replaceAll(" ", "%20");
				recibe_datos("idorigen",ej1);
			}
			
		}
		
		if( evt.target.equals( lista_destino ) )
		{
			if(lista_destino.getSelectedItem()!="Seleccione...")
			{
				String ej,ej1;//Esto permite enviar elementos con espacios a la pagina asp
				ej=lista_destino.getSelectedItem();
				ej1=ej.replaceAll(" ", "%20");
				recibe_datos("iddestino",ej1);
			}
			
		}
		
		if( evt.target.equals( lista_compania ) )
		{
			if(lista_compania.getSelectedItem()!="Seleccione...")
			{
				String ej,ej1;//Esto permite enviar elementos con espacios a la pagina asp
				ej=lista_compania.getSelectedItem();
				ej1=ej.replaceAll(" ", "%20");
				recibe_datos("idcompania",ej1);
			}
			
		}
		
		if( evt.target.equals( lista_avion ) )
		{
			if(lista_avion.getSelectedItem()!="Seleccione...")
			{
				String ej,ej1;//Esto permite enviar elementos con espacios a la pagina asp
				ej=lista_avion.getSelectedItem();
				ej1=ej.replaceAll(" ", "%20");
				recibe_datos("idavion",ej1);
			}
			
		}
		
		if( evt.target.equals( campo_entrada_duracion ) )
		{
			campo_duracion.setText(campo_entrada_duracion.getText());
			
		}
		
		if( evt.target.equals( campo_entrada_plazas ) )
		{
			campo_plazas.setText(campo_entrada_plazas.getText());
		}
		
		if( evt.target.equals( campo_entrada_fecha ) )
		{
			campo_fecha.setText(campo_entrada_fecha.getText());
		}
		return true;
	}


	public void Bloquea()
	{
		lista_origen.setEnabled(false);
		lista_destino.setEnabled(false);
		campo_entrada_fecha.setEditable(false);
		lista_compania.setEnabled(false);
		lista_avion.setEnabled(false);
		campo_entrada_duracion.setEditable(false);
		campo_entrada_plazas.setEditable(false);
		
	}
	
	public void Desbloquea()
	{
		lista_origen.setEnabled(true);
		lista_destino.setEnabled(true);
		campo_entrada_fecha.setEditable(true);
		lista_compania.setEnabled(true);
		lista_avion.setEnabled(true);
		campo_entrada_duracion.setText("");
		campo_entrada_plazas.setText("");
		campo_entrada_fecha.setText("");
		campo_entrada_duracion.setEditable(true);
		campo_entrada_plazas.setEditable(true);
	}

	
	public String CrearInsercion()
	{
		if(nuevo)
		{
			sql = "insert%20into%20VUELO%20values%20("+campo_ID.getText();
			sql += "," +campo_ciudadorigen.getText();
			sql += "," +campo_ciudaddestino.getText();
			sql += ",'" +campo_fecha.getText();
			sql += "'," +campo_compania.getText();
			sql += "," +campo_avion.getText();
			sql += "," +campo_duracion.getText();
			sql += "," +campo_plazas.getText()+")";
			ta.setText(sql);
		}
		else
		{				
			//String nf = campo_fecha.getText();
			
			sql = "UPDATE%20VUELO%20set%20IDCIUDADORIGEN="+campo_ciudadorigen.getText();
			sql += ",%20IDCIUDADDESTINO="+campo_ciudaddestino.getText();
			sql += ",%20FECHA='" +campo_fecha.getText();
			sql += "',%20IDCOMPANIA=" +campo_compania.getText();
			sql += ",%20IDAVION=" +campo_avion.getText();
			sql += ",%20DURACION=" +campo_duracion.getText();
			sql += ",%20N_PLAZAS_DISPONIBLES=" +campo_plazas.getText();
			sql += "%20where%20IDVUELO=" +campo_ID.getText();
			ta.setText(sql);
			
		}
		return(sql);
	}
	
}