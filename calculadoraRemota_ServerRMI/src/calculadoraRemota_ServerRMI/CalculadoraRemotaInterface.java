package calculadoraRemota_ServerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraRemotaInterface extends Remote{
	
	public float add(float vlrA, float vlrB)throws RemoteException;
	public float sub(float vlrA, float vlrB)throws RemoteException;
	public float mult(float vlrA, float vlrB)throws RemoteException;
	public float div(float vlrA, float vlrB)throws RemoteException;
	
}
