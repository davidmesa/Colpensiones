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
	 * Metodo del main de los threads
	 * @param args
	 */
	public void main(String[] args)
	{
		int cantiClients = 30;
		Cliente[] clientes = new Cliente[cantiClients];
		for (int i= 0; i<clientes.length; i++)
		{
			clientes[i] = new Cliente(i, buffer);
			clientes[i].start();
		}
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
