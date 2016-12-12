package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Result extends UnicastRemoteObject implements Resultator{
	
	
	private static final long serialVersionUID = 1L;
	private String result;
	private boolean processed = false;
	
	public Result() throws RemoteException {
		super();
	}

	public String getResult() throws RemoteException {
		return result;
	}

	public void setResult(String result) throws RemoteException {
		this.result = result;
	}

	public boolean isProcessed() throws RemoteException {
		return processed;
	}
	
	@Override
	public void setProcessed() throws RemoteException {
		processed = true;
		
	}

	

}
