package multiplicacao.matriz.server.rmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.rmi.Naming;

import multiplicacao.matriz.server.rmi.util.MatrizUtil;

public class MultiplicacaoMatrizClientRMI {
	
	public static void main(String[] args) {
		System.out.print("\nIniciando cliente da CalculadoraRemota...");
		try {
			CalculadoraMatrizInterface lCalculadoraMatrizInterface;
			
//			Registra o gerencioador de segurança
			
			System.out.print("\nRegistrando o gerenciador de segurança...");
			System.setSecurityManager(new SecurityManager());
			
//			Deveremos mudar o caminho para mandar em outra maquina
			lCalculadoraMatrizInterface = (CalculadoraMatrizInterface) Naming.lookup("rmi://localhost:1099/CalculadoraMatriz");
			
			long [][]matrizA = caregarMatriz("src/matA.txt");
			long [][]matrizB = caregarMatriz("src/matB.txt");
			
			long[][] matriz1 = dividirMatriz(matrizA, 0, 4096, 0, 1024);
			long[][] matriz2 = dividirMatriz(matrizA, 0, 4096, 1024, 2048);
			long[][] matriz3 = dividirMatriz(matrizA, 0, 4096, 2048, 3072);
			long[][] matriz4 = dividirMatriz(matrizA, 0, 4096, 3072, 4096);
			
			multiplicar(matriz1,matrizB, "rmi://localhost:1099/CalculadoraMatriz", "1");
			//multiplicar(matriz1,matrizB, "rmi://localhost:1109/CalculadoraMatriz","2");
			//multiplicar(matriz1,matrizB, "rmi://localhost:1100/CalculadoraMatriz","3");
			//multiplicar(matriz1,matrizB, "rmi://localhost:1101/CalculadoraMatriz","4");

			
//			for(int i = 0; i< 1024; i++) {
//				for(int j = 0; j< 4096; j++ ) {
//					System.out.println(matriz1[i][j]);
//				}
//			}
			//System.out.print("Result: " + caregarMatriz(teste));
		} catch (Exception e) {//(MalformedURLException | RemoteException | NotBoundException e) {
			// TODO: handle exception
			System.out.print("\n\tErro cliente: " + e.getMessage());
			System.exit(1);
		}
	}
	
	private static String caregarMatriz(long[] vetor) {
		String resultado = "";
		for(long valor : vetor) {
			resultado += valor + ", ";
		}
		return resultado;
	}
	
	private static long[][] caregarMatriz(String aCaminho) {
		long[][] mat = null;
		try {
			mat = MatrizUtil.carregar(4096, 4096, aCaminho);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		return mat;
	}
	
	public static long[][] dividirMatriz(long matriz[][], int colMin, int colMax,int linhaMin, int linhaMax){
		long [][] matrizResultado = new long[1024][4096];
		int k = 0 ;
		int l = 0 ;
		
		for(int i = linhaMin; i<linhaMax; i++) {
			
			for(int j = colMin; j< colMax; j++) {
				matrizResultado[k][l] = matriz[i][j]; 
				l++;
			}
			l = 0;
			k++;
		}
		

		return matrizResultado;
	}
	
	public static void multiplicar(long matA[][],long matB[][], String caminho, String num) {
		
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				try {
					System.out.print("\nThread: Started");
					CalculadoraMatrizInterface lCalculadoraMatrizInterface;
					lCalculadoraMatrizInterface = (CalculadoraMatrizInterface) Naming.lookup(caminho);
					
					long [][]matResultado = lCalculadoraMatrizInterface.multiplicar(matA, matB, 1024, 4096);
					
					System.out.print("\nThread: Done");
					int lin = 1024;
					int col = 4096;
					System.out.print("\nGravando arquivo da matriz C...");
					try {
						File fOut = new File("src/matriz"+num);
						BufferedWriter writer = new BufferedWriter(new FileWriter(fOut));
						for (int i = 0; i < lin; i++) {
							for (int j = 0; j < col; j++) {
								writer.write(String.valueOf(matResultado[i][j]));
								if ((i == lin-1) && (j == col-1)) {
									continue;
								} else {
									writer.newLine();
								}
							}
						}
						writer.flush();
						writer.close();
						System.out.print("\n Matriz"+num+"gravada!");
					} catch (Exception e) {
						System.err.print("\n\tErro: "+e.getMessage());
						System.exit(1);
					}
					
				} catch (Exception e) {
					System.err.print("\nErro: " + e.getMessage());
					System.exit(1);
				}
				
			}
			
		};

		thread.start();
		
		
	}
	
	
}
