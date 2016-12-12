package ie.gmit.sw;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Servant {
	public static void main(String[] args) throws Exception {
		
		StringService strserv = new StringServiceImpl();
		
		LocateRegistry.createRegistry(1099);
		
		Naming.rebind("stringService", strserv);
		
		System.out.println("Servant doing his job....\nServer is up on port 1099....");
		
	}
}
