package Cliente;

import Servidor.Buffer;


/**
 * Clase cliente
 * @author miguelangelcaldasvillamizar
 * Encargada de crear los mensajes y enviarlos
 */
public class Cliente extends Thread
{
	//-----------------------------------------------------------------------
	//								Atributos
	//-----------------------------------------------------------------------

	/**
	 * El id del cleinte, Para verificar el programa
	 */
	private int idCliente;
	
	/**
	 * El buffer donde se almacenan los mensajes
	 */
	private Buffer buffer;
	
	/**
	 * Cantidad de mensajes
	 */
	private int cantidadMensajes;
	
	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------
	
	/**
	 * Metodo constructor de la clase cliente
	 */
	public Cliente(int id, Buffer buff)
	{
		idCliente = id;
		buffer = buff;
		cantidadMensajes = (int) ( 100*Math.random() );
	}
	
	//-----------------------------------------------------------------------
	//								Metodos
	//-----------------------------------------------------------------------
	
	/**
	 * Metodo que retorna el identificador unico del cliente
	 * @return un numero que identifica al cliente de todos los demas
	 */
	public int darId()
	{
		return idCliente;
	}
	

	/**
	 * Metodo que realiza las operaciones de cada thread
	 */
	public void run()
	{
		for (int i = 0; i < cantidadMensajes; i++) {
			int dato = (int) ( 100*Math.random() );
			Mensaje mensajeActual = new Mensaje(this, buffer, dato);
			mensajeActual.enviarMensaje();
		}
		buffer.salirCliente();
	}
	
}
