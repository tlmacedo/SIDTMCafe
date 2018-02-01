/**
 * SigepClienteExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package webService.correios.atende;

public class SigepClienteExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1517446540460L;
    private webService.correios.atende.AtendeClienteServiceStub.SigepClienteException faultMessage;

    public SigepClienteExceptionException() {
        super("SigepClienteExceptionException");
    }

    public SigepClienteExceptionException(java.lang.String s) {
        super(s);
    }

    public SigepClienteExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public SigepClienteExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        webService.correios.atende.AtendeClienteServiceStub.SigepClienteException msg) {
        faultMessage = msg;
    }

    public webService.correios.atende.AtendeClienteServiceStub.SigepClienteException getFaultMessage() {
        return faultMessage;
    }
}
