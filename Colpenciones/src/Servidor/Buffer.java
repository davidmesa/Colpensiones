package Servidor;

import java.util.concurrent.atomic.AtomicInteger;

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
	 * Contiene la capacidad del buffer.
	 */
	private AtomicInteger capacidad;

	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------
	
	/**
	 * Constructor del Buffer
	 * @param nCapacidad la capacidad del buffer
	 */
	public Buffer ( int nCapacidad )
	{
		capacidad = new AtomicInteger(nCapacidad);
	}

	//-----------------------------------------------------------------------
	//								Metodos
	//-----------------------------------------------------------------------
	
}
