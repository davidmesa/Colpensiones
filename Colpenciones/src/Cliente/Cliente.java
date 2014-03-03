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

	
	private static long cedula;
	
	private Buffer buffer;
	
	
	/**
	 * Metodo constructor de la clase cliente
	 */
	public Cliente(long id, Buffer buff)
	{
		cedula = id;
		buffer = buff;
	}
	
	/**
	 * Metodo que retorna el identificador unico del cliente
	 * @return un numero que identifica al cliente de todos los demas
	 */
	public long darId()
	{
		return cedula;
	}
	

	/**
	 * Metodo que realiza las operaciones de cada thread
	 */
	public void run()
	{
		Mensaje mess = new Mensaje(this, buffer);
		//TODO
	}
	
}
