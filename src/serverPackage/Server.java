package serverPackage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import operateurPackage.Operateur;




public class Server {

	public static void main(String[] args) throws IOException {
		try{
			int num = 0;
			ServerSocket socketServeur =  new ServerSocket(1234);
			System.out.println("Je suis un serveur en attente la connexion d'un client ");
			
			
			
			while (true) {
				Socket socket = socketServeur.accept();
		        num++;
		          
		        // Création et démarrage du thread client
		        ClientProcess client = new ClientProcess(socket, num);
		        client.start();
	        }

			}catch(IOException e)
			{
				e.printStackTrace();
				
			}
		}
	  	// Classe interne static pour éviter l'erreur
	  	    public static class ClientProcess extends Thread {
	  	        private Socket socket;
	  	        private int num;
	  	        private static int Compteur_operation=0;

	  	        public ClientProcess(Socket socket, int clientNumber) {
	  	            this.socket = socket;
	  	            this.num = clientNumber;
	  	        }
	  	        
	  	      // Méthode synchronisée pour incrémenter le compteur en toute sécurité 
	  	      public static synchronized void Compteur() {
	  	    	Compteur_operation++;
	              System.out.println("Nombre total d'opérations traitées : " + Compteur_operation);
	          }
	  	      
	  	        @Override
	  	        public void run() {
	  	        	try {
	  	        		System.out.println("Client n" + num + " et son adress " + socket.getRemoteSocketAddress());
	  	        		InputStream is = socket.getInputStream();
		  	      		ObjectInputStream ois = new ObjectInputStream(is) ;
		  	      		Operateur op=(Operateur) ois.readObject();
	
			  	  		int res = 0;
			  	  		switch (op.getO()) {
			  	  			case "+":
			  	  				res = op.getX1()+op.getX2();
			  	  				System.out.println("Addition : " + op.getX1() + " + "+op.getX2()+" = " + res);
			  	  				break;
			  	  			case "-":
			  	  				res = op.getX1()-op.getX2();
			  	  				System.out.println("Soustraction : " + op.getX1() +" -  "+op.getX2()+" = " + res);
			  	  				break;
			  	  			case "*":
			  	  				res = op.getX1()*op.getX2();
			  	  				System.out.println("Multiplication : " + op.getX1() + " *  "+op.getX2()+" = " + res);
			  	  				break;
			  	  			case "/":
			  	  				if(op.getX2() !=0) {
			  	  					res = op.getX1()/op.getX2();
			  	  					System.out.println("Division : " + op.getX1() + " /  "+op.getX2()+" = " + res);
			  	  				}
			  	  				else
			  	  				{System.out.println("Division par zéro impossible");}
			  	  				break;
			  	  			default:
			  	  				System.out.println("Erreur : operateur invalide");
			  	  		}
			  	  	 // Incrémente le compteur global de manière synchronisée
			  	  		Compteur();
			  	  		OutputStream os = socket.getOutputStream();
			  	  		os.write(res);
			  		
			  	  		socket.close();
		  	        	} catch (IOException | ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						  
			  	    }
		  	    }
       
		



}
}

