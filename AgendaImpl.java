import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaImpl 
    extends 
      java.rmi.server.UnicastRemoteObject 
    implements Agenda  { 
    
        public AgendaImpl() 
        throws java.rmi.RemoteException { 
        super(); 
    } 

    @Override
    public int salvar(Object contato) throws java.rmi.RemoteException {
        int retorno;
            try {
                Database d = new Database();
                Contato c = (Contato) contato;
                retorno =  d.cadastrar(c);
            } catch (IOException ex) {
                Logger.getLogger(AgendaImpl.class.getName()).log(Level.SEVERE, null, ex);
                retorno = 0;
            }
            return retorno;
    }

    @Override
    public int atualizar(Object contato) throws java.rmi.RemoteException {
        int retorno;
            try {
                Database d = new Database();
                Contato c = (Contato) contato;
                retorno = d.atualizar(c);
            } catch (IOException ex) {
                Logger.getLogger(AgendaImpl.class.getName()).log(Level.SEVERE, null, ex);
                retorno = 0;
            }
            return retorno;
        
    }

    @Override
    public int apagar(Object contato) throws java.rmi.RemoteException {
       int retorno;
            try {
                Database d = new Database();
                Contato c = (Contato) contato;
                retorno = d.apagar(c.getNome());
            } catch (IOException ex) {
                Logger.getLogger(AgendaImpl.class.getName()).log(Level.SEVERE, null, ex);
                retorno = 0;
            }
            return retorno;
    }

    @Override
    public Object consultar(Object contato) throws java.rmi.RemoteException  {
        Contato retorno;
            try {
                Database d = new Database();
                Contato c = (Contato) contato;
                retorno = d.consultar(c.getNome());
            } catch (IOException ex) {
                Logger.getLogger(AgendaImpl.class.getName()).log(Level.SEVERE, null, ex);
                retorno = null;
            }
        return retorno;
    }
    
}
