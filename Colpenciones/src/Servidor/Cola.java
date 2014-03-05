package Servidor;

import Cliente.Mensaje;

/**
 * Clase Cola
 * @author davidmesa 
 * ( Con apoyo de CircularQueue http://soft0.upc.edu/pc/practiques/semafors-monitors/prodcons-java/CircularQueue.java)
 * Encargada de almacenar los mensajes
 */
public class Cola {

	//-----------------------------------------------------------------------
	//								Atributos
	//-----------------------------------------------------------------------

	/**
	 * Contiene los mensajes de la cola
	 */
	private Mensaje[] mensajes;
	
	/**
	 * La capacidad de la cola
	 */
	private int capacidad;

	/**
	 * Posicion del primer elemento
	 */
	private int primero;

	/**
	 * Posicion del ultimo elemento
	 */
	private int ultimo;

	/**
	 * Cantidad de elementos en la cola
	 */
	private int cantidad;
	
	
	//-----------------------------------------------------------------------
	//							   Constructor
	//-----------------------------------------------------------------------

	/**
	 * Constructor de la clase cola
	 * Inicializa los valores de primero y ultimo en 0
	 * @param nCapacidad La capacidad de la cola
	 */
	public Cola(int nCapacidad)
	{
		capacidad = nCapacidad;
		primero = 0;
		ultimo = 0;
		cantidad = 0;
		mensajes = new Mensaje[capacidad];
		
	}

	//-----------------------------------------------------------------------
	//								Metodos
	//-----------------------------------------------------------------------

	/**
	 * Verifica si la cola esta vacia
	 * @return True si la cola esta vacia, false de lo contrario
	 */
	public boolean estaVacia()
	{
		return cantidad == 0;
	}

	/**
	 * Verifica si la cola esta llena
	 * @return True si la cola esta llena, false de lo contrario
	 */
	public boolean estaLlena()
	{
		return cantidad == capacidad;
	}

	/**
	 * Agrega mensaje a la cola
	 * @param nMensaje El mensaje que se requiere agregar a la cola
	 * @return Retorna true en caso de que se pueda agregar, false cuando esta llena
	 */
	public boolean agregarMensaje( Mensaje nMensaje )
	{
		if(!estaLlena())
		{
			mensajes[ultimo] = nMensaje;
			ultimo = ( ultimo + 1 ) % capacidad;
			cantidad ++;
			return true;
		}
		return false;
	}

	/**
	 * Obtener mensaje de la cola
	 * @return El primer mensaje de la cola o null en caso de que este vacia.
	 */
	public Mensaje obtenerMensaje()
	{	
		if(!estaVacia())
		{
			Mensaje respuesta = mensajes[primero];
			mensajes[primero] = null;
			primero = ( primero + 1 ) % capacidad;
			cantidad --;
			return respuesta;
		}
		return null;
	}
}
