package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringServiceImpl extends UnicastRemoteObject implements StringService {

	private static final long serialVersionUID = 1L;

	protected StringServiceImpl() throws RemoteException {
		super();
	}

	//private Resultator r;
	
	@Override
	public Resultator compare(String s, String t, String algo) throws RemoteException {
		
		Resultator r = new Result();
		
		System.out.println("\n\n\nGo to sleep");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			Compare c = new Compare(s, t, algo, r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
