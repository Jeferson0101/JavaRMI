package multiplicacao.matriz.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraMatrizInterface extends Remote {
	long[] multiplicar(int aLinhaCalcular, long aMatrizA[][], long aMatrizB[][]) throws RemoteException;
}
