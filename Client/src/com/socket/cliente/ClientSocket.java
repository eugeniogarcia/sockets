package com.socket.cliente;
//Client Side
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	public void run() {
		try {
			//Datos del servidor
			final int serverPort = 4020;
			final InetAddress host = InetAddress.getByName("localhost"); 
			System.out.println("Connecting to server on port " + serverPort); 

			//Crea un socket con el servidor
			final Socket socket = new Socket(host,serverPort); 
			//Socket socket = new Socket("127.0.0.1", serverPort);

			//Muestra la dirección del servidor
			System.out.println("Just connected to " + socket.getRemoteSocketAddress()); 

			//Para enviar al servidor
			final PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
			//Respuesta del servidor
			final BufferedReader fromServer = new BufferedReader( new InputStreamReader(socket.getInputStream()));

			toServer.println("Hola desde el avión de Zurich a Madrid. Enviado desde " + socket.getLocalSocketAddress()); 

			//Escribe la respuesta del servidor
			final String line = fromServer.readLine();
			System.out.println("Client received: " + line + " from Server");

			//Cierra todo
			toServer.close();
			fromServer.close();
			socket.close();
		}
		catch(final UnknownHostException ex) {
			ex.printStackTrace();
		}
		catch(final IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final ClientSocket client = new ClientSocket();
		client.run();
	}
}