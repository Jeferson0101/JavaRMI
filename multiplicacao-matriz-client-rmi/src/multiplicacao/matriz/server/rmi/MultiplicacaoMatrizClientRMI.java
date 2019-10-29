package multiplicacao.matriz.server.rmi;

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
			lCalculadoraMatrizInterface.multiplicar(caregarMatriz("src/mat1.txt"), caregarMatriz("src/matB.txt"));
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
			mat = MatrizUtil.carregar(4, 4, aCaminho);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < 4; j ++) {
				System.out.println(mat[i][j]);
			}
		}
		System.out.println("acabou");
		return mat;
	}
}
