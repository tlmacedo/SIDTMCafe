/**
 * ErroMontagemRelatorioException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package webService.correios.atende;

public class ErroMontagemRelatorioException extends java.lang.Exception {
    private static final long serialVersionUID = 1517446540464L;
    private webService.correios.atende.AtendeClienteServiceStub.ErroMontagemRelatorioE faultMessage;

    public ErroMontagemRelatorioException() {
        super("ErroMontagemRelatorioException");
    }

    public ErroMontagemRelatorioException(java.lang.String s) {
        super(s);
    }

    public ErroMontagemRelatorioException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public ErroMontagemRelatorioException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        webService.correios.atende.AtendeClienteServiceStub.ErroMontagemRelatorioE msg) {
        faultMessage = msg;
    }

    public webService.correios.atende.AtendeClienteServiceStub.ErroMontagemRelatorioE getFaultMessage() {
        return faultMessage;
    }
}
