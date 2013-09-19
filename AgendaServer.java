import java.rmi.Naming;

public class AgendaServer {

	static String serverName = null;

    public AgendaServer() {
        try {
            Agenda a = new AgendaImpl();
            Naming.rebind("rmi://"+ serverName+":1099/AgendaService", a);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
if (args.length == 0) {
            System.out.println("Informe o endereco de IP desta maquina como parametro!");

        } else {
            serverName = args[0];
	    new AgendaServer();
}
    }

}
