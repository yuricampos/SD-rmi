import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class AgendaClient {

    public static void main(String[] args) {
        String serverName = null;
        Scanner entrada = new Scanner(System.in);
        if (args.length == 0) {
            System.out.println("Informe o endereco do servidor como parametro!");

        } else {
            serverName = args[0];
            String rmiName = "rmi://" + serverName + "/AgendaService";

            System.out.println("Connecting to: " + rmiName);

            try {
                int loop = 1;
                Agenda a = (Agenda) Naming.lookup(rmiName);
                Contato c = new Contato();
                while (loop == 1) {
                    int opcao;
                    menu();
                    opcao = entrada.nextInt();
                    switch (opcao) {
                        case 1:
                            cadastrar(a);
                            break;

                        case 2:
                            consultar(a);
                            break;

                        case 3:
                            apagar(a);
                            break;

                        case 4:
                            loop = 0;
                            break;

                        default:
                            System.out.println("Opção inválida.");
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    public static void menu() {
        System.out.println("********************************");
        System.out.println("**Entre com a opcao desejada**");
        System.out.println("1 - Cadastrar / Atualizar Contato");
        System.out.println("2 - Consultar Contato");
        System.out.println("3 - Apagar Contato");
        System.out.println("4 - Finalizar Cliente");
        System.out.println("********************************");
    }

    public static void cadastrar(Agenda a) throws RemoteException {
        Scanner entrada = new Scanner(System.in);
        Contato c = new Contato();
        System.out.println("Nome do contato: ");
        String nomeCadastro = entrada.nextLine();
        System.out.println("Telefone do contato: ");
        String telefoneContato = entrada.nextLine();
        c.setNome(nomeCadastro);
        c.setTelefone(telefoneContato);
        if (a.salvar(c) == 1) {
            System.out.println("***Cadastrado com sucesso!!***");
        } else {
            System.out.println("***Cadastro já existe na base de dados!***");
            System.out.println("Deseja atualizar contato? ");
            System.out.println("1 - Sim ");
            System.out.println("2 - Não ");
            int atualizar = entrada.nextInt();
            if (atualizar == 1) {
                if (a.atualizar(c) == 1) {
                    System.out.println("Atualizado com sucesso!");
                } else {
                    System.out.println("***Erro ao atualizar***");
                }

            } else {
                System.out.println("***Contato não foi atualizado!***");
            }

        }

    }

    public static void consultar(Agenda a) throws RemoteException {
        Scanner entrada = new Scanner(System.in);
        Contato c = new Contato();
        System.out.println("Nome do contato: ");
        String nomeConsulta;
        nomeConsulta = entrada.nextLine();
        c.setNome(nomeConsulta);
        Contato c1 = new Contato();
        c1 = null;
        c1 = (Contato) a.consultar(c);
        if (c1 == null) {
            System.out.println("Contato não foi encontrado");
        } else {
            System.out.println("Dados do contato");
            System.out.println("Nome: " + c1.getNome());
            System.out.println("Telefone: " + c1.getTelefone());
        }
        System.gc();

    }

    public static void apagar(Agenda a) throws RemoteException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nome do contato: ");
        String nomeApagar;
        Contato c = new Contato();
        nomeApagar = entrada.nextLine();
        c.setNome(nomeApagar);
        if (a.apagar(c) == 1) {
            System.out.println("Contato apagado com sucesso!");
        } else {
            System.out.println("Contato nao existe!");
        }

    }

}
