/**
 * ExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package webService.correios.atende;

public class ExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1517446540469L;
    private webService.correios.atende.AtendeClienteServiceStub.ExceptionE faultMessage;

    public ExceptionException() {
        super("ExceptionException");
    }

    public ExceptionException(java.lang.String s) {
        super(s);
    }

    public ExceptionException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public ExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        webService.correios.atende.AtendeClienteServiceStub.ExceptionE msg) {
        faultMessage = msg;
    }

    public webService.correios.atende.AtendeClienteServiceStub.ExceptionE getFaultMessage() {
        return faultMessage;
    }
}
