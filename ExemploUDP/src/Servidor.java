import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

	public static void main(String[] args) throws Exception {
		System.out.println("Servidor ativo!");
		// Cria socket do servidor com a porta 7999
		DatagramSocket serverSocket = new DatagramSocket(7999);
		// Declara strings de bytes para envio e recebimento de mensagens
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		while(true) {
		// Cria pacote de dados a ser recebido do cliente
		DatagramPacket receivePacket = new DatagramPacket(receiveData,

		receiveData.length);

		// Recebe o pacote de dados do cliente
		serverSocket.receive(receivePacket);
		// Recupera os dados, o IP e a porta do cliente
		String msg_recebida = new String(receivePacket.getData());
		InetAddress IPAddress = receivePacket.getAddress();
		int port = receivePacket.getPort();
		// Transforma mensagem recebida em string de bytes
		sendData = msg_recebida.getBytes();
		// Monta o pacote de retorno ao cliente
		DatagramPacket sendPacket =
		new DatagramPacket(sendData, sendData.length,

		IPAddress, port);

		// Envia dados ao cliente
		serverSocket.send(sendPacket);
		}
	}
}
