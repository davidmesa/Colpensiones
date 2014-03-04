package Servidor;

import Cliente.Cliente;
import Cliente.Mensaje;

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
	 * ID del servidor
	 */
	private int idServidor;

	/**
	 * Buffer, consume mensajes de este.
	 */
	private Buffer buffer;

	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------

	/**
	 * Constructor del Servidor
	 * @param nBuffer Buffer del cual se consumen mensajes
	 */
	public Servidor( int nID, Buffer nBuffer )
	{
		idServidor = nID;
		buffer = nBuffer;
	}

	//-----------------------------------------------------------------------
	//								Metodos
	//-----------------------------------------------------------------------

	/**
	 * Metodo run del thread del servidor.
	 * Encargado de consumir mensaje y retornar al buffer.
	 */
	@Override
	public void run()
	{
		System.out.println(idServidor);
		outerloop:
		while(true)
		{
			Mensaje mensajeActual = null;
			while(mensajeActual == null)
			{
				if(buffer.quedanClientes())
				{
					break outerloop;
				}
				mensajeActual = buffer.obtener();
			}
			procesarMensaje(mensajeActual);
			mensajeActual.despertar();
		}
	}
	
	/**
	 * Procesa el mensaje enviado por el cliente
	 * @param mensajeActual El mensaje a procesar
	 * @return retorna el mensaje procesado
	 */
	public void procesarMensaje( Mensaje mensajeActual )
	{
		int dato = mensajeActual.darDato();
		dato ++;
		mensajeActual.ingresarDato(dato);
	}

	//-----------------------------------------------------------------------
	//								  Main
	//-----------------------------------------------------------------------

	/**
	 * Main clase, Primera en iniciar
	 * @param args
	 */
	public static void main( String[] args )
	{
		int capacidadBuffer = 20;

		int cantServidores = 4;

		int cantClientes = 10;

		Buffer buffer = new Buffer(capacidadBuffer, cantClientes);

		// Crea servidores

		Servidor[] servidores = new Servidor[ cantServidores ];

		for (int i = 0; i < cantServidores; i++) 
		{
			servidores[i] = new Servidor( i, buffer );
			servidores[i].start();
		}

		//Crea Clientes
		
		Cliente[] clientes = new Cliente[cantClientes];
		for (int i= 0; i<clientes.length; i++)
		{
			clientes[i] = new Cliente(i, buffer);
			clientes[i].start();
		}
		
	}

}
