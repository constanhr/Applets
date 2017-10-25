<!-- #include file=conexion.asp -->
	<%
		' Script ASP
		' Esta pagina es llmada por un Applet
		' se utiliza para insertar, actualizar o consultar vuelos

		' Recibe los siguientes parametros
		' 
		'   Accion : Valores "ConsultaX",  "Insertar" o "Actualizar"
		'            Según este valor se realizara un select,insert o update
		'
		'   idvuelo: valor del codigo de vuelo



		'---- Leemos los parametros recibidos por GET */

		' Recibimos el tipo de acción a realizar, mediante GET
		variable1=request.querystring("Accion")
		' Recibimos el id del vuelo mediante GET
		variable2=request.querystring("idvuelo")
		'variable1="Consulta2"
		'variable2="1"

		'Response.Write(variable1)& vbCrLf
		'Response.Write(variable2)& vbCrLf

		' Si la variable1 es igual a "insertar", recibimos el resto de variables que vienen como parametro

		'If variable1 == "Insertar" then
		' recibimos el resto de variables que vienen como parametro
		' $nombre_recibido=$_GET["nombre"];
		' $apellido1_recibido=$_GET["apellido1"];
		'end if

		' Este ejemplo es de consulta de selección, si se recibe como parámetro una consulta de inserción o de actualización
		' es posible que se necesiten recibir más parámetros



		' Diferentes consultas SQL según el valor de los parametros de entrada

		' Estas funciones seran utilizadas para llenar con datos los controles del applet

		if (variable1 = "IDVUELO") then
			consulta = "SELECT DISTINCT IDVUELO from VUELO"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("IDVUELO") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		if (variable1 = "ORIGEN") then
			consulta = "SELECT DISTINCT CIUDAD from CIUDAD"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("CIUDAD") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		if (variable1 = "AVION") then
			consulta = "SELECT DISTINCT AVION from AVION"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("AVION") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		if (variable1 = "COMPANIA") then
			consulta = "SELECT DISTINCT COMPANIA from COMPANIA"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("COMPANIA") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		if (variable1 = "idorigen") then
			consulta = "SELECT IDCIUDAD from CIUDAD where CIUDAD='" &variable2& "'"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("IDCIUDAD") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		if (variable1 = "iddestino") then
			consulta = "SELECT IDCIUDAD from CIUDAD where CIUDAD='" &variable2& "'"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("IDCIUDAD") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		
		if (variable1 = "idcompania") then
			consulta = "SELECT IDCOMPANIA from COMPANIA where COMPANIA='" &variable2& "'"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("IDCOMPANIA") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		
		
		if (variable1 = "idavion") then
			consulta = "SELECT IDAVION from AVION where AVION='" &variable2& "'"
			Set origen = Conexion.Execute(consulta)
			do while not origen.EOF
				Response.Write(origen("IDAVION") & vbCrLf)
				origen.MoveNext
			loop 
			consulta.Close()
		end if
		

		if (variable1 = "Vuelos") then
			consulta = "SELECT * from VUELO where IDVUELO=" & variable2
			'escribo el select ejecutado para el area de texto a modo de LOG
			'Response.Write(consulta & "Variable1=" & variable1 & " Variable2=" & variable2 & vbCrLf)

			Set origen = Conexion.Execute(consulta)
			'Recorreremos el RecordSet
			do while not origen.EOF
				Response.Write(origen("IDVUELO") & "-")
				Response.Write(origen("IDCIUDADORIGEN") & "-")
				Response.Write(origen("IDCIUDADDESTINO") & "-")
				Response.Write(origen("FECHA") & "-")
				Response.Write(origen("IDCOMPANIA") & "-")
				Response.Write(origen("IDAVION") & "-")
				Response.Write(origen("DURACION") & "-")
				Response.Write(origen("N_PLAZAS_DISPONIBLES") & "-")
				'Nos desplazamos por el RecordSet
				origen.MoveNext
			loop 

			consulta.Close()
			Response.Write(consulta) & vbCrLf
		end if
		
		
		if (variable1 = "MaxID") then
			consulta = "SELECT MAX (IDVUELO)+1 as maximo from VUELO"
			'escribo el select ejecutado para el area de texto a modo de LOG
			'Response.Write(consulta & "Variable1=" & variable1 & " Variable2=" & variable2 & vbCrLf)
			Response.Write(consulta) & vbCrLf

			Set origen = Conexion.Execute(consulta)
			'Recorreremos el RecordSet
			do while not origen.EOF

				Response.Write(origen("maximo") & "-")
				'Nos desplazamos por el RecordSet
				origen.MoveNext
			loop 
			consulta.Close()
			'Response.Write(consulta) & vbCrLf
		end if
		' ... así, detallamos todas las consultas que se podrían realizar
		
		
		if (variable1 = "Ejecutar") then
			Response.Write(variable2) & vbCrLf
			consulta = variable2
			Set origen = Conexion.Execute(consulta)
			consulta.Close()
			response.write("Base de datos actualizada")
		end if
		
		Conexion.close
%>