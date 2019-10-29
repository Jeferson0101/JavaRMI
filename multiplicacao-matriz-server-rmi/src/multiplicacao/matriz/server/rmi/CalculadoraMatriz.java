package multiplicacao.matriz.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraMatriz extends UnicastRemoteObject implements CalculadoraMatrizInterface {
	
	private static final long serialVersionUID = 1L;
	
	protected CalculadoraMatriz() throws RemoteException {
		super();
	}

	@Override
	public long[][] multiplicar(long[][] matrizA, long[][] matrizB) {
//		for(int i = 0; i< 2; i++) {
//			for (int j = 0 ;j < 2; j++) {
//				System.out.println(matrizA[i][j]);
//			}
//		}
			
		long[][] matriz1 = dividirMatriz(matrizA, 0, 2, 0, 2);
		long[][] matriz2 = dividirMatriz(matrizA, 2, 4, 0, 2);
		long[][] matriz3 = dividirMatriz(matrizA, 0, 2, 2, 4);
		long[][] matriz4 = dividirMatriz(matrizA, 2, 4, 2, 4);
		
		System.out.println("Matriz1 resultado: \n");
		for(int i = 0 ; i < 2; i++) {
			for (int j = 0; j< 2; j++) {
				System.out.println(matriz1[i][j]);
			}
		}
		
		
		return matrizA;
	}
	
//	MultiplicacaoMatrizResult lMultiplicacaoMatrizResult = new MultiplicacaoMatrizResult(aLinhaCalcular);
//	lMultiplicacaoMatrizResult.mResult = MatrizUtil.multiplicar(aLinhaCalcular, aMatriz, aMatrizB);
//	return lMultiplicacaoMatrizResult;


	public long[][] dividirMatriz(long matriz[][], int colMin, int colMax,int linhaMin, int linhaMax){
		long [][] matrizResultado = new long[2][2];
		int k = 0 ;
		int l = 0 ;
		
		for(int i = linhaMin; i<linhaMax; i++) {
			
			for(int j = colMin; j< colMax; j++) {
				matrizResultado[k][l] = matriz[i][j]; 
				//System.out.println("i" + i + "j" + j +matriz[i][j]);
				//System.out.println(matriz[i][j]);	
				//System.out.println("k"+ k +"l" + l);
				l++;
			}
			l = 0;
			k++;
		}
		
//		System.out.println("Matriz1 resultado: \n");
//		for(int i = 0 ; i < 2; i++) {
//			for (int j = 0; j< 2; j++) {
//				System.out.println(matrizResultado[i][j]);
//			}
//		}
		
		
		
//		for (int i = 0; i < lin; i++) {
//			for (int j = 0; j < lin; j++) {
//				for (int k = 0; k < col; k++) {
//					matC[i][j] = matC[i][j] + (matA[i][k] * matB[k][j]);
//				}
//			}
//		}
		

		return matrizResultado;
	}
	
}