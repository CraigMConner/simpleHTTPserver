/*
 * Java Program that creates a server that listens for an HTTP request and sends out
 * a response of the date.
 * 
 * Adapted from:
 * https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

public class simpleHTTPserver {

		public static void main(String[] args) throws Exception {
			
			// Create a server listening on port 8090
			final ServerSocket server = new ServerSocket(8090);
			System.out.print("Listening for connection on port 8090...");
			
			// Loop forever
			while (true) {
				final Socket clientSocket = server.accept();
				
				// 1. Read HTTP request from the client socket and print request to console
				InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
				BufferedReader reader = new BufferedReader(isr);
				String line = reader.readLine();
				while (!line.isEmpty()) {
					System.out.println(line);
					line = reader.readLine();
				}
				
				// 2. Prepare an HTTP response of the date and time
				Date today = new Date();
				String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
				
				// 3. Send HTTP response to the client
				clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				
				// 4. Close the socket
				clientSocket.close();
				
			}
		} 
}
