/**
 * ConsultaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package webService.fonteDeDados;


/**
 *  ConsultaCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ConsultaCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ConsultaCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ConsultaCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for protestoPF method
     * override this method for handling normal response from protestoPF operation
     */
    public void receiveResultprotestoPF(
        webService.fonteDeDados.ConsultaStub.ProtestoPFResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from protestoPF operation
     */
    public void receiveErrorprotestoPF(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for denatranRenavam method
     * override this method for handling normal response from denatranRenavam operation
     */
    public void receiveResultdenatranRenavam(
        webService.fonteDeDados.ConsultaStub.DenatranRenavamResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from denatranRenavam operation
     */
    public void receiveErrordenatranRenavam(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for certidaoRFCnpj method
     * override this method for handling normal response from certidaoRFCnpj operation
     */
    public void receiveResultcertidaoRFCnpj(
        webService.fonteDeDados.ConsultaStub.CertidaoRFCnpjResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from certidaoRFCnpj operation
     */
    public void receiveErrorcertidaoRFCnpj(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for certidaoSPCcm method
     * override this method for handling normal response from certidaoSPCcm operation
     */
    public void receiveResultcertidaoSPCcm(
        webService.fonteDeDados.ConsultaStub.CertidaoSPCcmResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from certidaoSPCcm operation
     */
    public void receiveErrorcertidaoSPCcm(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for bonus method
     * override this method for handling normal response from bonus operation
     */
    public void receiveResultbonus(
        webService.fonteDeDados.ConsultaStub.BonusResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from bonus operation
     */
    public void receiveErrorbonus(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for situacaoCadastralPF method
     * override this method for handling normal response from situacaoCadastralPF operation
     */
    public void receiveResultsituacaoCadastralPF(
        webService.fonteDeDados.ConsultaStub.SituacaoCadastralPFResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from situacaoCadastralPF operation
     */
    public void receiveErrorsituacaoCadastralPF(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for nomePorCPF method
     * override this method for handling normal response from nomePorCPF operation
     */
    public void receiveResultnomePorCPF(
        webService.fonteDeDados.ConsultaStub.NomePorCPFResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from nomePorCPF operation
     */
    public void receiveErrornomePorCPF(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for protestoPJ method
     * override this method for handling normal response from protestoPJ operation
     */
    public void receiveResultprotestoPJ(
        webService.fonteDeDados.ConsultaStub.ProtestoPJResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from protestoPJ operation
     */
    public void receiveErrorprotestoPJ(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for nfe method
     * override this method for handling normal response from nfe operation
     */
    public void receiveResultnfe(
        webService.fonteDeDados.ConsultaStub.NfeResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from nfe operation
     */
    public void receiveErrornfe(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for situacaoCadastralPJ method
     * override this method for handling normal response from situacaoCadastralPJ operation
     */
    public void receiveResultsituacaoCadastralPJ(
        webService.fonteDeDados.ConsultaStub.SituacaoCadastralPJResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from situacaoCadastralPJ operation
     */
    public void receiveErrorsituacaoCadastralPJ(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for cep method
     * override this method for handling normal response from cep operation
     */
    public void receiveResultcep(
        webService.fonteDeDados.ConsultaStub.CepResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from cep operation
     */
    public void receiveErrorcep(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for saldo method
     * override this method for handling normal response from saldo operation
     */
    public void receiveResultsaldo(
        webService.fonteDeDados.ConsultaStub.SaldoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from saldo operation
     */
    public void receiveErrorsaldo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for simplesNacional method
     * override this method for handling normal response from simplesNacional operation
     */
    public void receiveResultsimplesNacional(
        webService.fonteDeDados.ConsultaStub.SimplesNacionalResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from simplesNacional operation
     */
    public void receiveErrorsimplesNacional(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for sintegraCNPJ method
     * override this method for handling normal response from sintegraCNPJ operation
     */
    public void receiveResultsintegraCNPJ(
        webService.fonteDeDados.ConsultaStub.SintegraCNPJResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from sintegraCNPJ operation
     */
    public void receiveErrorsintegraCNPJ(java.lang.Exception e) {
    }
}
