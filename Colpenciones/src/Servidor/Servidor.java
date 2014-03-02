package Servidor;

/**
 * Clase servidor
 * @author davidmesa
 * Encargada de recibir las peticiones almacenadas en el buffer y retornar
 * Esta clase incluye el main
 */
public class Servidor extends Thread {

	//-----------------------------------------------------------------------
	//								Atributos
	//-----------------------------------------------------------------------
	
	/**
	 * 
	 */
	private Buffer buffer;
	
	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------
	
	public Servidor( Buffer nBuffer )
	{
		
	}
	
	//-----------------------------------------------------------------------
	//								  Main
	//-----------------------------------------------------------------------

	/**
	 * Main clase, Priera en iniciar
	 * @param args
	 */
	public static void main( String[] args )
	{
		Buffer buffer = new Buffer(20);

		// Crea servidores

		int cantServidores = 10;

		Servidor[] servidores = new Servidor[ cantServidores ];

		for (int i = 0; i < cantServidores; i++) 
		{
			servidores[i] = new Servidor( buffer );
			servidores[i].start();
		}

		//Crea Clientes

		int cantClientes = 30;
	}

}
