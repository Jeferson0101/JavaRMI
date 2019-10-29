package multiplicacao.matriz.server.rmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.util.concurrent.CountDownLatch;

public class MultiplicacaoMatrizClienteRMI {

	private final static String policy = "C:\\Users\\Angela\\Desktop\\Calculadora\\multiplicacao-matriz-client-rmi\\client.policy";

	private final static String caminho1 = "rmi://localhost:1100/MultiplicacaoMatriz";
	private final static String caminho2 = "rmi://localhost:1100/MultiplicacaoMatriz";
	private final static String caminho3 = "rmi://localhost:1100/MultiplicacaoMatriz";
	private final static String caminho4 = "rmi://localhost:1100/MultiplicacaoMatriz";

	private final static int lin = 4096;
	private final static int col = 4096;
	private static int cont = 0;

	private static int[][] matA = new int[lin][col];
	private static int[][] matB = new int[lin][col];
	private static int[][] matC = new int[lin][col];

	public static void main(String[] args) {
		System.out.print("\nIniciando cliente...");

		try {
			System.out.print("\nRegistrando o gerenciador de seguranca...");
			System.setProperty("java.security.policy", policy);
			System.setSecurityManager(new SecurityManager());

			System.out.print("\nCarregando matrizes...");
			caregarMatrizes();
//			exibirMatriz(matA);
//			exibirMatriz(matB);

			System.out.print("\nDividindo matriz A...");
			int[][] matA1 = dividirMatriz(matA, 0, lin / 2, 0, col / 2);
			int[][] matA2 = dividirMatriz(matA, 0, lin / 2, col / 2, col);
			int[][] matA3 = dividirMatriz(matA, lin / 2, lin, 0, col / 2);
			int[][] matA4 = dividirMatriz(matA, lin / 2, lin, col / 2, col);
//			exibirMatriz(matA1);
//			exibirMatriz(matA2);
//			exibirMatriz(matA3);
//			exibirMatriz(matA4);

			System.out.print("\nCalculando...");
			long startTime = System.currentTimeMillis();
			final CountDownLatch latch = new CountDownLatch(4);
			multiplicar(caminho1, matA1, matB, 0, lin / 2, 0, col, latch);
			multiplicar(caminho2, matA2, matB, 0, lin / 2, 0, col, latch);
			multiplicar(caminho3, matA3, matB, lin / 2, lin, 0, col, latch);
			multiplicar(caminho4, matA4, matB, lin / 2, lin, 0, col, latch);
			latch.await();
			long stopTime = System.currentTimeMillis();
			
			System.out.print("\nResultado: ");
//			exibirMatriz(matC);

			System.out.print("\nTempo de execucao: " + (stopTime - startTime) + " ms");
			System.out.print("\nTempo de execucao: " + (stopTime - startTime) / 1000 + " segundos");
			System.out.print("\nTempo de execucao: " + ((stopTime - startTime) / 1000) / 60 + " minutos");

			System.out.print("\nGravando arquivo da matriz C...");
			gravarResultado();

			// Gera o MD5
			System.out.print("\nGerando MD5 da matriz C...");
			gerarMd5();
		} catch (Exception e) {
			System.out.print("\nErro: " + e.getMessage());
			System.exit(1);
		}
	}

	private static void multiplicar(String caminho, int[][] matA, int[][] matB, int linMin, int linMax, int colMin, int colMax, CountDownLatch latch) {
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				try {
					System.out.print("\nThread: começou");
					MultiplicacaoMatrizInterface lCalculadoraMatrizInterface;
					lCalculadoraMatrizInterface = (MultiplicacaoMatrizInterface) Naming.lookup(caminho);
					int[][] resultado = lCalculadoraMatrizInterface.multiplicar(matA, matB);
					System.out.print("\nThread: multiplicou");
					somar(resultado, linMin, linMax, colMin, colMax);
					exibirMatriz(resultado);
					System.out.print("\nThread: somou");
					cont = cont + 1;
					System.out.print("\nThread: terminou");
					latch.countDown();
				} catch (Exception e) {
					System.err.print("\nErro: " + e.getMessage());
					System.exit(1);
				}
			}
		};

		thread.start();
	}

	private static void somar(int[][] mat, int linMin, int linMax, int colMin, int colMax) {
		int k = 0;
		
		for (int i = linMin; i < linMax; i++) {
			int l = 0;
			
			for (int j = colMin; j < colMax; j++) {
				matC[i][j] += mat[k][l];
				l++;
			}
			
			k++;
		}
	}

	private static void caregarMatrizes() {
		System.out.print("\nLendo arquivo da matriz A...");

		int l, c;

		try {
			FileReader file = new FileReader("src/matA2.txt");
			BufferedReader bufFile = new BufferedReader(file);

			String line = bufFile.readLine();
			l = c = 0;

			while (line != null) {
				matA[l][c] = Integer.parseInt(line);
				c++;

				if (c >= col) {
					l++;
					c = 0;
				}

				line = bufFile.readLine();
			}

			bufFile.close();
		} catch (Exception e) {
			System.err.print("\nErro: " + e.getMessage());
			System.exit(1);
		}

		// Carrega a matriz B
		System.out.print("\nLendo arquivo da matriz B...");
		try {
			FileReader file = new FileReader("src/matB2.txt");
			BufferedReader bufFile = new BufferedReader(file);

			String line = bufFile.readLine();
			l = c = 0;

			while (line != null) {
				matB[l][c] = Integer.parseInt(line);
				c++;

				if (c >= col) {
					l++;
					c = 0;
				}

				line = bufFile.readLine();
			}

			bufFile.close();
		} catch (Exception e) {
			System.err.print("\nErro: " + e.getMessage());
			System.exit(1);
		}
	}

	private static String exibirMatriz(int[][] mat) {
		String resultado = "";

		System.out.print("\n");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.print("\n");
		}

		return resultado;
	}

	private static int[][] dividirMatriz(int[][] mat, int linMin, int linMax, int colMin, int colMax) {
		int[][] novaMat = new int[linMax - linMin][col];

		int k = 0;
		
		for (int i = linMin; i < linMax; i++) {
			for (int j = colMin; j < colMax; j++) {
				novaMat[k][j] = mat[i][j];
			}
			
			k++;
		}

		return novaMat;
	}

	private static void gravarResultado() {
		try {
			File fOut = new File("src/matC.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fOut));
			
			for (int i = 0; i < lin; i++) {
				for (int j = 0; j < col; j++) {
					writer.write(String.valueOf(matC[i][j]));
					if ((i == lin - 1) && (j == col - 1)) {
						continue;
					} else {
						writer.newLine();
					}
				}
			}
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.print("\nErro: " + e.getMessage());
			System.exit(1);
		}
	}

	private static void gerarMd5() {
		try {
			File matCFile = new File("src/matC.txt");
			int matCSize = (int) matCFile.length();
			byte[] matCBytes = new byte[matCSize];
			
			matCBytes = getFileBytes(matCFile);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(matCBytes);

			System.out.print("\nHash: " + toHexFormat(hash));
			System.out.print("\nGravando arquivo matC.md5...");
			
			File md5File = new File("src/matC.md5");
			BufferedWriter writer = new BufferedWriter(new FileWriter(md5File));
			writer.write(toHexFormat(hash) + "   matC.txt");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.print("\nErro: " + e.getMessage());
			System.exit(1);
		}
	}

	public static byte[] getFileBytes(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;

		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);
		} catch (Exception e) {
			System.err.print("\nErro: " + e.getMessage());
			System.exit(1);
		}

		return sendBuf;
	}

	public static String toHexFormat(final byte[] bytes) {
		final StringBuilder sb = new StringBuilder();

		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}
}
