package multiplicacao.matriz.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import multiplicacao.matriz.server.rmi.model.MultiplicacaoMatrizResult;
import multiplicacao.matriz.server.rmi.util.MatrizUtil;

public class CalculadoraMatriz extends UnicastRemoteObject implements CalculadoraMatrizInterface {
	
	private static final long serialVersionUID = 1L;
	
	protected CalculadoraMatriz() throws RemoteException {
		super();
	}

	@Override
	public MultiplicacaoMatrizResult multiplicar(int aPosicaoInicial, int aPosicaoFinal, long[][] aMatrizA,
			long[][] aMatrizB) throws RemoteException {
		System.out.println("\nMultiplicar [] x [][]");
		MultiplicacaoMatrizResult lMultiplicacaoMatrizResult = new MultiplicacaoMatrizResult(aPosicaoInicial, aPosicaoFinal);
		lMultiplicacaoMatrizResult.mResult = MatrizUtil.multiplicar(aMatrizA, aMatrizB);
		return lMultiplicacaoMatrizResult;
	}
}
