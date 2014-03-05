package Cliente;

import Servidor.Buffer;

/**
 * Clase Mensaje
 * @author miguelangelcaldasvillamizar
 * Encargada de depositar el mensaje en el buffer y contener su informacion
 */
public class Mensaje 
{
	//-----------------------------------------------------------------------
	//								Atributos
	//-----------------------------------------------------------------------
	
	/**
	 * El cliente creador del mensaje
	 */
	private Cliente cliente;
	
	/**
	 * El buffer donde se almacena el mensaje
	 */
	private Buffer buffer;
	
	/**
	 * El dato que contiene el mensaje
	 */
	private int dato;
	
	/**
	 * Identifica si el mensaje esta dormido
	 */
	private boolean dormido;
	
	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase
	 * @param client el cliente que crea el mensaje
	 */
	public Mensaje(Cliente client, Buffer nBuffer, int nDato)
	{
		cliente = client;
		dato = nDato;
		buffer = nBuffer;
		dormido = false;
	}
	
	//-----------------------------------------------------------------------
	//								Metodos
	//-----------------------------------------------------------------------

	/**
	 * Encargado de almacenar el mensaje y dormir al cliente mientras este es procesado
	 */
	public synchronized void enviarMensaje()
	{
		while ( !buffer.almacenar(this) ) { }
		dormido = true;

		while( dormido )
		{
			try { wait(); }
			catch (InterruptedException e) { }
		}
	}

	/**
	 * Se encarga de despertar al thread del cliente
	 */
	public synchronized void despertar( )
	{
		dormido = false;
		notify();
	}

	/**
	 * Devuelve el dato del mensaje
	 * @return Dato del mensaje
	 */
	public int darDato()
	{
		return dato;
	}

	/**
	 * Ingresa nuevo dato al mensaje
	 * @param nDato Nuevo dato
	 */
	public void ingresarDato( int nDato )
	{
		dato = nDato;
	}

	/**
	 * Retorna el id del cliente
	 * @return Id del cliente
	 */
	public int darIdCliente()
	{
		return cliente.darId();
	}
}
