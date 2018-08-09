package com.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//Server Side
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSideSocket { 
	public void run() {
		try {
			//Crea un servidor en el puerto 4020 con timeout 10000
			final int serverPort = 4020;
			final ServerSocket serverSocket = new ServerSocket(serverPort);
			serverSocket.setSoTimeout(1000000); 
			while(true) {
				System.out.println("Esperando que alguien se conecte en el puerto " + serverSocket.getLocalPort() + "..."); 
				//Espera a que llegue una petición
				final Socket server = serverSocket.accept();
				System.out.println("Alguien se conecto " + server.getRemoteSocketAddress()); 

				//Esto nos servira para contestar al cliente
				final PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);

				//Esto nos servira para leer desde el cliente
				final BufferedReader fromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
				//Leemos una linea del cliente
				final String line = fromClient.readLine();
				System.out.println("El servidor ha recibido: " + line); 

				//Enviamos este mensaje al cliente
				toClient.println("Gracias por contactar con nosotros " + server.getLocalSocketAddress() + "\nAdios!"); 
			}
		}
		catch(final UnknownHostException ex) {
			ex.printStackTrace();
		}
		catch(final IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final ServerSideSocket srv = new ServerSideSocket();
		srv.run();
	}
}