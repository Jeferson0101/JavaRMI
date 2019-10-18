package multiplicacao.matriz.server.rmi.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MatrizUtil {
	
	public static final byte LINE = 0;
	public static final byte COLUNM = 1;
	
	public static long[][] carregar(int aLinhas, int aColunas, String aCaminho) throws Exception {
		long[][] lMatriz = new long[aLinhas][aColunas];
		FileReader file = new FileReader(aCaminho);//("src/matA.txt");
		BufferedReader bufFile = new BufferedReader(file);
		
		// L� a primeira linha
		String line = bufFile.readLine();
		int lLinhas =  0;
		int lColunas = 0;
		while (line != null) {
			lMatriz[lLinhas][lColunas] = Integer.parseInt(line);
			lColunas++;
			if (lColunas >= aColunas) {
				lLinhas++;
				lColunas = 0;
			}
			line = bufFile.readLine();
		}
		bufFile.close();

		return lMatriz;
	}
	
	public static boolean gravar(long[][] aMatrizResult, String aCaminho) {
		try {
			File fOut = new File(aCaminho);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fOut));
			for (int i = 0; i < aMatrizResult[LINE].length; i++) {
				for (int j = 0; j < aMatrizResult[COLUNM].length; j++) {
					writer.write(String.valueOf(aMatrizResult[i][j]));
					if ((i ==  aMatrizResult[LINE].length - 1) && (j == aMatrizResult[COLUNM].length - 1)) {
						continue;
					} else {
						writer.newLine();
					}
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.print("\n\tErro: "+e.getMessage());
			System.exit(1);
			return false;
		}
		return true;
	}

}
