package calculadoraRemota_ServerRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CalculadoraRemota_ServerRMI {

	public static void main(String[] args) {
		
		System.out.print("Iniciando Servidor");
		
		
		try {
			//instancia o gerenciador de segurança
			System.out.println("Registrando o serviço de segurança");
			System.setSecurityManager(new SecurityManager());
			
			//instancia do objeto local
			CalculadoraRemota calc = new CalculadoraRemota();
			
			//registra o objeto no rmi Registry
			System.out.println("\n\t Registrando objeto no RMI Registry");
			Naming.rebind("rmi://localhost:1099/CalculadoraRemota", calc);
			
			System.out.println("Aguardando requisições...");
		}	
		catch(RemoteException | MalformedURLException e) {
			System.err.print("\n\tErro:" + e.getMessage());
			System.exit(1);
		}
		
	}

}
