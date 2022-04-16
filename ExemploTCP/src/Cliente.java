import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Ciente ativo!");
		String msg_digitada; // mensagem digitada
		String msg_recebida; // mensagem recebida
		String nome_cliente; // nome do cliente
		// cria o stream do teclado
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		// solicita um nome para o cliente
		System.out.println("Informe o nome do cliente:");
		nome_cliente = teclado.readLine();
		// cria o socket de acesso ao server hostname na porta 8657
		Socket cliente = new Socket("localhost", 8657);
		System.out.println(nome_cliente + " entrou no chat!");
		// cria os streams de entrada e saida com o servidor
		DataOutputStream saida_servidor = new DataOutputStream(cliente.getOutputStream());
		BufferedReader entrada_servidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		while (true) {
			// le uma linha do teclado
			msg_digitada = teclado.readLine();
			// testa se o chat deve ser finalizado
			if (msg_digitada.startsWith("fim") == true)
				break;
			// envia a linha para o servidor
			saida_servidor.writeBytes(msg_digitada + '\n');
			// lê uma linha do servidor
			msg_recebida = entrada_servidor.readLine();
			// apresenta a linha do servidor na console
			System.out.println("Servidor: " + msg_recebida);
		}
		// fecha o cliente
		cliente.close();
		System.out.println(nome_cliente + " saiu do chat!");
	}

}
