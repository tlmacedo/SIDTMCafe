
package webService.correios.atende;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.1
 * 2018-02-01T20:04:39.958-04:00
 * Generated source version: 3.2.1
 */

@WebFault(name = "SigepClienteException", targetNamespace = "http://cliente.bean.master.sigep.bsb.correios.com.br/")
public class SigepClienteException extends java.lang.Exception {
    
    private java.lang.String sigepClienteException;

    public SigepClienteException() {
        super();
    }
    
    public SigepClienteException(String message) {
        super(message);
    }
    
    public SigepClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SigepClienteException(String message, java.lang.String sigepClienteException) {
        super(message);
        this.sigepClienteException = sigepClienteException;
    }

    public SigepClienteException(String message, java.lang.String sigepClienteException, Throwable cause) {
        super(message, cause);
        this.sigepClienteException = sigepClienteException;
    }

    public java.lang.String getFaultInfo() {
        return this.sigepClienteException;
    }
}
