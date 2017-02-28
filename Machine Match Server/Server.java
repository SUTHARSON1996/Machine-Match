import java.util.*;
import java.net.*;
import java.io.*;

public class Server{

	public static void main(String[] args)throws Throwable{
		ServerSocket server = new ServerSocket(5000);
		System.out.println("Server is ready!!!");
			
		while(true){

			Socket soc = server.accept();
		

			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			byte[] buffer = (byte[])ois.readObject();
			FileOutputStream fos = new FileOutputStream("test.jpg");
			fos.write(buffer);




			Process p = Runtime.getRuntime().exec("java ExtractData");
			BufferedReader tempBuf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String mac = tempBuf.readLine().toString();
			System.out.println(mac);		
			PrintStream ps = new PrintStream(soc.getOutputStream());
			ps.println(mac);


		
			tempBuf.close();
			ps.close();
			soc.close();
		}
		//server.close();
	}

}
