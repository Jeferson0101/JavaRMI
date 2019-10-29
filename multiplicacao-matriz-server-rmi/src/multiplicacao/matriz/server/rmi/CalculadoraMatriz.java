package multiplicacao.matriz.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraMatriz extends UnicastRemoteObject implements CalculadoraMatrizInterface {
	
	private static final long serialVersionUID = 1L;
	
	protected CalculadoraMatriz() throws RemoteException {
		super();
	}

	@Override
	public long[][] multiplicar(long[][] matA, long[][] matB, int lin, int col) {
		long[][] matC = new long[lin][col];
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < lin; i++) {
		for (int j = 0; j < lin; j++) {
			for (int k = 0; k < col; k++) {
				matC[i][j] = matC[i][j] + (matA[i][k] * matB[k][j]);
			}
		}
	}
		long stopTime = System.currentTimeMillis();
		System.out.print("\n\tTempo de execução: "+(stopTime-startTime)+" ms");
		System.out.print("\n\tTempo de execução: "+(stopTime-startTime)/1000+" segundos");
		System.out.print("\n\tTempo de execução: "+((stopTime-startTime)/1000)/60+" minutos");
		
		return matC;
	}
	
//	MultiplicacaoMatrizResult lMultiplicacaoMatrizResult = new MultiplicacaoMatrizResult(aLinhaCalcular);
//	lMultiplicacaoMatrizResult.mResult = MatrizUtil.multiplicar(aLinhaCalcular, aMatriz, aMatrizB);
//	return lMultiplicacaoMatrizResult;



	
}