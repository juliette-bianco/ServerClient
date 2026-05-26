//Juliette Bianco
package clientServerPackage;

import java.io.*;
import java.util.*;
import java.net.Socket;

/*To Do:
 * 
 * - Client creates output.txt with that same content
 * - reads the contents and sends the contents to the Client
 * 
 * Done:
 * 
 * - Client sends "input.txt" to the Server
 * - Server receives the name and opens ANY file with THAT name
 * 
 */

public class Client 
{
	public static void main(String[] args) 
	{
		Socket socket = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		
		try {
			// connects to server
			socket = new Socket("localhost", 1234);
			
			inputStreamReader = new InputStreamReader(socket.getInputStream());
			outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			Scanner scanner = new Scanner(System.in);
			
			while(true)
			{
				//write message to server
				String message = scanner.nextLine();
				// prints message to servers screen
				bufferedWriter.write(message);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				//store servers file contents
				String fileContent = bufferedReader.readLine();
				// prints contents
				System.out.println("Server: " + fileContent);
				// Creating the output.txt file
				FileWriter writer = new FileWriter("output.txt");
				//fills up output.txt with the contents from input.txt
				writer.write(fileContent);
				// closes file
				writer.close();
				
				break;

				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				if (socket != null)
					socket.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (outputStreamWriter != null)
					outputStreamWriter.close();
				if (bufferedReader != null)
					bufferedReader.close();
				if (bufferedWriter != null)
					bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
