package Cliente;

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
	
	/**
	 * Metodo constructor de la clase
	 * @param client el cliente que crea el mensaje
	 */
	public Mensaje(Cliente client, Buffer buff)
	{
		cliente = client;
		buffer = buff;
	}
	
	/**
	 * Metodo que retona el cliente que envio este mensaje
	 * @return el cliente que envia el mensaje
	 */
	public Cliente darCliente()
	{
		return cliente;
	}
	
	/**
	 * Metodo que simboliza el envio del mansaje al buffer
	 */
	public void envio()
	{
		//TODO
	}
	
}
