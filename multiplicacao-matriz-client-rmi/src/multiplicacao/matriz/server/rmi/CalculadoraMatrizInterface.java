package multiplicacao.matriz.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraMatrizInterface extends Remote {
	long[][] multiplicar(long MatrizA[][], long matrizB[][], int linha, int coluna) throws RemoteException;
}
