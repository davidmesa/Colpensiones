package Servidor;

import Cliente.Mensaje;

/**
 * Clase Buffer
 * @author davidmesa
 * Encagada de contener los mensajes listos para que el servidor los consuma
 */
public class Buffer {
	
	//-----------------------------------------------------------------------
	//								Atributos
	//-----------------------------------------------------------------------
	
	/**
	 * Contiene la cantidad de clientes
	 */
	private int cantClientes;
	
	private Cola cola;
			

	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------
	
	/**
	 * Constructor del Buffer
	 	* @param nCapacidad la capacidad del buffer
	 */
	public Buffer ( int nCapacidad, int nCantClientes )
	{
		cantClientes = nCantClientes;
		cola = new Cola(nCapacidad);
	}

	//-----------------------------------------------------------------------
	//								Metodos
	//-----------------------------------------------------------------------
	
	/**
	 * Almacena un mensaje en la cola
	 * @param nMensaje El mensaje que se quiere almacenar
	 * @return True si se almaceno, False si no hay espacio
	 */
	public synchronized boolean almacenar( Mensaje nMensaje )
	{
		return cola.agregarMensaje(nMensaje);
	}
	
	/**
	 * Obtiene el mensaje de la cola
	 * @return Mensaje de la cola, null en caso de estar vacia.
	 */
	public synchronized Mensaje obtener()
	{
		return cola.obtenerMensaje();
	}
	
	/**
	 * 
	 */
	public synchronized void salirCliente()
	{
		cantClientes --;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean quedanClientes()
	{
		return cantClientes == 0;
	}
	
	
	
	
}
