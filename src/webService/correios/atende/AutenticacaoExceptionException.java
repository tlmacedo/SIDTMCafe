/**
 * AutenticacaoExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package webService.correios.atende;

public class AutenticacaoExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1517446540455L;
    private webService.correios.atende.AtendeClienteServiceStub.AutenticacaoException faultMessage;

    public AutenticacaoExceptionException() {
        super("AutenticacaoExceptionException");
    }

    public AutenticacaoExceptionException(java.lang.String s) {
        super(s);
    }

    public AutenticacaoExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public AutenticacaoExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        webService.correios.atende.AtendeClienteServiceStub.AutenticacaoException msg) {
        faultMessage = msg;
    }

    public webService.correios.atende.AtendeClienteServiceStub.AutenticacaoException getFaultMessage() {
        return faultMessage;
    }
}
