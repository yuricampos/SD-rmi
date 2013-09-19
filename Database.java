import java.io.IOException;
import java.util.Properties;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.helper.FastIterator;
import jdbm.htree.HTree;

public class Database {

    RecordManager recman;
    HTree hashtable;
    FastIterator iter;
    String nome;
    String telefone;

    public Database() throws IOException {
        Properties props = new Properties();
        recman = RecordManagerFactory.createRecordManager("contatos", props);
        long recid = recman.getNamedObject("contatos");
        if (recid != 0) {
          //  System.out.println("Carregando banco de dados...");
            hashtable = HTree.load(recman, recid);
            recman.commit();
        } else {
        //    System.out.println("Criando banco de dados...");
            hashtable = HTree.createInstance(recman);
            recman.setNamedObject("contatos", hashtable.getRecid());
            recman.commit();
        }

    }

    public int cadastrar(Contato c) throws IOException {
        if (hashtable.get(c.getNome()) != null) {
            return 0;
        } else {
            hashtable.put(c.getNome(), c.getTelefone());
            recman.commit();
            return 1;
        }
    }

    public int atualizar(Contato c) throws IOException {
        if (hashtable.get(c.getNome()) == null) {
            return 0;
        } else {
            hashtable.put(c.getNome(), c.getTelefone());
            recman.commit();
            return 1;
        }
    }

    public int apagar(String nome) throws IOException {
        if (hashtable.get(nome) == null) {
            return 0;
        } else {
            hashtable.remove(nome);
            recman.commit();
            return 1;
        }
    }

    public Contato consultar(String nome) throws IOException {
        if (hashtable.get(nome) == null) {
            return null;
        } else {
            String telefone = (String) hashtable.get(nome);
            Contato c = new Contato();
            c.setNome(nome);
            c.setTelefone(telefone);
            return c;

        }
    }

}
