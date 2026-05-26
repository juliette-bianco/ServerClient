//Juliette Bianco
package clientServerPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server 
{
	public static void main(String[] args) throws IOException 
	{
		Socket socket = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		ServerSocket serverSocket = null;
		
		serverSocket = new ServerSocket(1234);
		
		try {
			//	connects to client
			socket = serverSocket.accept();
					
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
					
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
				
				
			while(true)
			{
				//store clients message
				String messageFromClient = bufferedReader.readLine();
				//print message from client on servers screen
				System.out.println("Client: " + messageFromClient);
				
				try {
					// Opening input.txt
					File file = new File(messageFromClient);
					Scanner scan = new Scanner(file);
						
					// creating variable to store input.txt contents
					String fileContent = "";
					while(scan.hasNext()) {    //loop to store the contents of "input.txt" into "fileContent"
						fileContent = fileContent.concat(scan.nextLine() + "\n");
					}
					
					// send contents to client
					bufferedWriter.write(fileContent);
					bufferedWriter.newLine();
					bufferedWriter.flush();
					
				} catch (IOException e) {
					bufferedWriter.write("sorry, this file does not exist.");
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
				
				break;
			}
			
			socket.close();
			inputStreamReader.close();
			outputStreamWriter.close();
			bufferedReader.close();
			bufferedWriter.close();

		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
}
