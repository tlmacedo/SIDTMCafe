/**
 * SQLExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package webService.correios.atende;

public class SQLExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1517446540450L;
    private webService.correios.atende.AtendeClienteServiceStub.SQLExceptionE faultMessage;

    public SQLExceptionException() {
        super("SQLExceptionException");
    }

    public SQLExceptionException(java.lang.String s) {
        super(s);
    }

    public SQLExceptionException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public SQLExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        webService.correios.atende.AtendeClienteServiceStub.SQLExceptionE msg) {
        faultMessage = msg;
    }

    public webService.correios.atende.AtendeClienteServiceStub.SQLExceptionE getFaultMessage() {
        return faultMessage;
    }
}
