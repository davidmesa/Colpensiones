package Cliente;

import java.util.concurrent.atomic.AtomicBoolean;

import Servidor.Buffer;

/**
 * 
 * @author miguelangelcaldasvillamizar
 *
 */
public class Mensaje 
{
	private Cliente cliente;
	private Buffer buffer;
	private int dato;
	private AtomicBoolean dormido;

	/**
	 * Metodo constructor de la clase
	 * @param client el cliente que crea el mensaje
	 */
	public Mensaje(Cliente client, Buffer nBuffer, int nDato)
	{
		cliente = client;
		dato = nDato;
		buffer = nBuffer;
		dormido = new AtomicBoolean();
	}

	/**
	 * Encargado de almacenar el mensaje y dormir al cliente mientras este es procesado
	 */
	public void enviarMensaje()
	{
		while ( !buffer.almacenar(this) ) { }
		dormido.set(true);;
		synchronized (cliente) {
			while( dormido.get() )
			{
				try { cliente.wait(); }
				catch (InterruptedException e) { }
			}
		}
	}

	/**
	 * Se encarga de despertar al thread del cliente
	 */
	public void despertar( )
	{
		synchronized (cliente) {
			dormido.set(false);
			cliente.notify();
		}
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
	
	public int darIdCliente()
	{
		return cliente.darId();
	}


}
