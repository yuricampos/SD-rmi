
public interface Agenda 
          extends java.rmi.Remote { 
    public int salvar(Object contato) 
        throws java.rmi.RemoteException; 
    public int atualizar(Object contato) 
        throws java.rmi.RemoteException; 
    public int apagar(Object contato) 
        throws java.rmi.RemoteException; 
    public Object consultar(Object contato) 
        throws java.rmi.RemoteException; 
} 
