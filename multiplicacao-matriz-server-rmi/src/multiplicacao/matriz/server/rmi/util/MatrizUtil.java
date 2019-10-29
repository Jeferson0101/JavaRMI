package multiplicacao.matriz.server.rmi.util;

public class MatrizUtil {
	
	public static final byte LINE = 0;
	public static final byte COLUNM = 1;
	
	public static long[][] multiplicar(long[][] aMatrizA, long[][] aMatrizB) {
		int lLinha = aMatrizA[LINE].length;
		int lColuna = aMatrizA[LINE].length;
		long[][] lMatrizC = new long[lLinha][lColuna];
		for (int i = 0; i < lLinha; i++) {
			for (int j = 0; j < lLinha; j++) {
				for (int k = 0; k < lColuna; k++) {
					lMatrizC[i][j] = lMatrizC[i][j] + (aMatrizA[i][k] * aMatrizB[k][j]);
				}
			}
		}
		return lMatrizC;
	}
	
	public static long[] multiplicar(int aLinha,long[][] aMatrizA, long[][] aMatrizB) {
		long[] lResultLines = new long[aMatrizA[1].length];
		for (int j = 0; j < aMatrizA[0].length; j++) {
			for (int k = 0; k < aMatrizA[1].length; k++) {
				lResultLines[j] = lResultLines[j] + (aMatrizA[aLinha][k] * aMatrizB[k][j]);
			}
		}
		return lResultLines;
	}
}
