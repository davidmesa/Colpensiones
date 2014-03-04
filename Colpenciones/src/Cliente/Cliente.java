package Cliente;

import Servidor.Buffer;
import Servidor.Servidor;


/**
 * 
 * @author miguelangelcaldasvillamizar
 *
 */
public class Cliente extends Thread
{

	
	private int idCliente;
	
	private Buffer buffer;
	
	private int cantidadMensajes;
	
	
	/**
	 * Metodo constructor de la clase cliente
	 */
	public Cliente(int id, Buffer buff)
	{
		idCliente = id;
		buffer = buff;
		cantidadMensajes = (int) ( 100*Math.random() );
	}
	
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
		for (int i = 0; i < 5; i++) {
			int dato = (int) ( 100*Math.random() );
			Mensaje mensajeActual = new Mensaje(this, buffer, dato);
			System.out.println("Envia Mesaje:   id "+idCliente + " Mensaje no " + i);
			mensajeActual.enviarMensaje();
			System.out.println("Recibe mensaje: id "+idCliente + " Mensaje no " + i);
		}
		buffer.salirCliente();
	}
	
}
