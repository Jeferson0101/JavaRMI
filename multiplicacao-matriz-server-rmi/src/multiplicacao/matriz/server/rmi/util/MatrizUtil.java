package multiplicacao.matriz.server.rmi.util;

public class MatrizUtil {
	
	public static final byte COLUNM = 1;
	
	public static long[][] multiplicar(long[][] aMatrizA, long[][] aMatrizB) {
		int lLinha = aMatrizA.length;
		int lLinhaII = aMatrizB.length;
		int lColuna = aMatrizA[1].length;
		long[][] lMatrizC = new long[lLinha][lColuna];
		for (int i = 0; i < lLinha; i++) {
			for (int j = 0; j < lLinhaII; j++) {
				for (int k = 0; k < lColuna; k++) {
					lMatrizC[i][j] = lMatrizC[i][j] + (aMatrizA[i][k] * aMatrizB[k][j]);
				}
			}
		}
		return lMatrizC;
	}
}
