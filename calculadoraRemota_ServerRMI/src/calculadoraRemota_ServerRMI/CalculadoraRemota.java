package calculadoraRemota_ServerRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraRemota extends UnicastRemoteObject implements CalculadoraRemotaInterface{
	
	protected CalculadoraRemota() throws RemoteException {
		
	}

	private static final long serialVersionUID = 1L; // índice que vamos usar como referência
	
	// método de adição 
	@Override
	public float add(float vlrA, float vlrB) throws RemoteException {
		System.out.println("\n\tCliente invocou o método remoto add...");
		return vlrA + vlrB;
	}

	// método de subtração
	@Override
	public float sub(float vlrA, float vlrB) throws RemoteException {
		System.out.println("\n\tCliente invocou o método remoto subtração...");
		return vlrA - vlrB;
	}
	
	// método de multiplicação
	@Override
	public float mult(float vlrA, float vlrB) throws RemoteException {
		System.out.println("\n\tCliente invocou o método remoto multiplicação...");
		return vlrA * vlrB;
	}
	
	// método de divisão
	@Override
	public float div(float vlrA, float vlrB) throws RemoteException {
		System.out.println("\n\tCliente invocou o método remoto divisão...");
		return vlrA * vlrB;
	}
	
}
