package multiplicacao.matriz.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraMatrizInterface extends Remote {
	long[][] multiplicar(long aMatrizA[][], long aMatrizB[][], int linha, int coluna) throws RemoteException;
}
