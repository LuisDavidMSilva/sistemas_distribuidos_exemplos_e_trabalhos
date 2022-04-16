import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		String msg_digitada; //mensagem digitada 
		String msg_recebida; //mensagem recebida
		
		//cria o stream do teclado
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		
		// Declara Socket Cliente
		DatagramSocket clientSocket = new DatagramSocket();
		
		
		// Declara strings de bytes para o envio e recebimento de mensagens
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		// Solicita mensagem do teclado
		System.out.println("Digite uma mensagem: ");
		msg_digitada = teclado.readLine();
		sendData = msg_digitada.getBytes();
		
		// Cria pacote de dados a ser enviado para o servidor
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 7999);
		
		// Envia o pacote
		try {
			clientSocket.send(sendPacket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Cria pacote de dados a ser recebido pelo servidor
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		// Recebe o pacote do servidor
		try {
			clientSocket.receive(receivePacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Separa somente o dado recebido 
		msg_recebida = new String(receivePacket.getData());
		
		// Imprime na console o dado recebido
		System.out.println("Servidor: " + msg_recebida);
		
		// Fecha o cliente
		clientSocket.close();
	}
}
