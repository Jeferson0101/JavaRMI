package multiplicacao.matriz.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraMatriz extends UnicastRemoteObject implements CalculadoraMatrizInterface {
	
	private static final long serialVersionUID = 1L;
	
	protected CalculadoraMatriz() throws RemoteException {
		super();
	}

	@Override
	public long[] multiplicar(int aLinhaCalcular, long[][] aMatrizA, long[][] aMatrizB) {
		long[] lResultLines = new long[aMatrizA[1].length];
		for (int j = 0; j < aMatrizA[0].length; j++) {
			for (int k = 0; k < aMatrizA[1].length; k++) {
				lResultLines[j] = lResultLines[j] + (aMatrizA[aLinhaCalcular][k] * aMatrizB[k][j]);
			}
		}
		return lResultLines;
	}
	
//	MultiplicacaoMatrizResult lMultiplicacaoMatrizResult = new MultiplicacaoMatrizResult(aLinhaCalcular);
//	lMultiplicacaoMatrizResult.mResult = MatrizUtil.multiplicar(aLinhaCalcular, aMatriz, aMatrizB);
//	return lMultiplicacaoMatrizResult;

}
