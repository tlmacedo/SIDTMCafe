/**
 * AtendeClienteServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package webService.correios.atende;


/**
 *  AtendeClienteServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class AtendeClienteServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public AtendeClienteServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public AtendeClienteServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for buscaServicosAdicionaisAtivos method
     * override this method for handling normal response from buscaServicosAdicionaisAtivos operation
     */
    public void receiveResultbuscaServicosAdicionaisAtivos(
        webService.correios.atende.AtendeClienteServiceStub.BuscaServicosAdicionaisAtivosResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaServicosAdicionaisAtivos operation
     */
    public void receiveErrorbuscaServicosAdicionaisAtivos(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for fechaPlp method
     * override this method for handling normal response from fechaPlp operation
     */
    public void receiveResultfechaPlp(
        webService.correios.atende.AtendeClienteServiceStub.FechaPlpResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from fechaPlp operation
     */
    public void receiveErrorfechaPlp(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for consultaSRO method
     * override this method for handling normal response from consultaSRO operation
     */
    public void receiveResultconsultaSRO(
        webService.correios.atende.AtendeClienteServiceStub.ConsultaSROResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from consultaSRO operation
     */
    public void receiveErrorconsultaSRO(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for calculaTarifaServico method
     * override this method for handling normal response from calculaTarifaServico operation
     */
    public void receiveResultcalculaTarifaServico(
        webService.correios.atende.AtendeClienteServiceStub.CalculaTarifaServicoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from calculaTarifaServico operation
     */
    public void receiveErrorcalculaTarifaServico(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for validaPlp method
     * override this method for handling normal response from validaPlp operation
     */
    public void receiveResultvalidaPlp(
        webService.correios.atende.AtendeClienteServiceStub.ValidaPlpResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from validaPlp operation
     */
    public void receiveErrorvalidaPlp(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for verificaSeTodosObjetosCancelados method
     * override this method for handling normal response from verificaSeTodosObjetosCancelados operation
     */
    public void receiveResultverificaSeTodosObjetosCancelados(
        webService.correios.atende.AtendeClienteServiceStub.VerificaSeTodosObjetosCanceladosResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from verificaSeTodosObjetosCancelados operation
     */
    public void receiveErrorverificaSeTodosObjetosCancelados(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for cancelarObjeto method
     * override this method for handling normal response from cancelarObjeto operation
     */
    public void receiveResultcancelarObjeto(
        webService.correios.atende.AtendeClienteServiceStub.CancelarObjetoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from cancelarObjeto operation
     */
    public void receiveErrorcancelarObjeto(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for atualizaPagamentoNaEntrega method
     * override this method for handling normal response from atualizaPagamentoNaEntrega operation
     */
    public void receiveResultatualizaPagamentoNaEntrega(
        webService.correios.atende.AtendeClienteServiceStub.AtualizaPagamentoNaEntregaResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from atualizaPagamentoNaEntrega operation
     */
    public void receiveErroratualizaPagamentoNaEntrega(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for obterClienteAtualizacao method
     * override this method for handling normal response from obterClienteAtualizacao operation
     */
    public void receiveResultobterClienteAtualizacao(
        webService.correios.atende.AtendeClienteServiceStub.ObterClienteAtualizacaoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from obterClienteAtualizacao operation
     */
    public void receiveErrorobterClienteAtualizacao(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for verificaDisponibilidadeServico method
     * override this method for handling normal response from verificaDisponibilidadeServico operation
     */
    public void receiveResultverificaDisponibilidadeServico(
        webService.correios.atende.AtendeClienteServiceStub.VerificaDisponibilidadeServicoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from verificaDisponibilidadeServico operation
     */
    public void receiveErrorverificaDisponibilidadeServico(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for fechaPlpVariosServicos method
     * override this method for handling normal response from fechaPlpVariosServicos operation
     */
    public void receiveResultfechaPlpVariosServicos(
        webService.correios.atende.AtendeClienteServiceStub.FechaPlpVariosServicosResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from fechaPlpVariosServicos operation
     */
    public void receiveErrorfechaPlpVariosServicos(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for geraDigitoVerificadorEtiquetas method
     * override this method for handling normal response from geraDigitoVerificadorEtiquetas operation
     */
    public void receiveResultgeraDigitoVerificadorEtiquetas(
        webService.correios.atende.AtendeClienteServiceStub.GeraDigitoVerificadorEtiquetasResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from geraDigitoVerificadorEtiquetas operation
     */
    public void receiveErrorgeraDigitoVerificadorEtiquetas(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for obterEmbalagemLRS method
     * override this method for handling normal response from obterEmbalagemLRS operation
     */
    public void receiveResultobterEmbalagemLRS(
        webService.correios.atende.AtendeClienteServiceStub.ObterEmbalagemLRSResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from obterEmbalagemLRS operation
     */
    public void receiveErrorobterEmbalagemLRS(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for validaEtiquetaPLP method
     * override this method for handling normal response from validaEtiquetaPLP operation
     */
    public void receiveResultvalidaEtiquetaPLP(
        webService.correios.atende.AtendeClienteServiceStub.ValidaEtiquetaPLPResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from validaEtiquetaPLP operation
     */
    public void receiveErrorvalidaEtiquetaPLP(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaServicosValorDeclarado method
     * override this method for handling normal response from buscaServicosValorDeclarado operation
     */
    public void receiveResultbuscaServicosValorDeclarado(
        webService.correios.atende.AtendeClienteServiceStub.BuscaServicosValorDeclaradoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaServicosValorDeclarado operation
     */
    public void receiveErrorbuscaServicosValorDeclarado(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for consultaCEP method
     * override this method for handling normal response from consultaCEP operation
     */
    public void receiveResultconsultaCEP(
        webService.correios.atende.AtendeClienteServiceStub.ConsultaCEPResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from consultaCEP operation
     */
    public void receiveErrorconsultaCEP(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for integrarUsuarioScol method
     * override this method for handling normal response from integrarUsuarioScol operation
     */
    public void receiveResultintegrarUsuarioScol(
        webService.correios.atende.AtendeClienteServiceStub.IntegrarUsuarioScolResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from integrarUsuarioScol operation
     */
    public void receiveErrorintegrarUsuarioScol(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for atualizaRemessaAgrupada method
     * override this method for handling normal response from atualizaRemessaAgrupada operation
     */
    public void receiveResultatualizaRemessaAgrupada(
        webService.correios.atende.AtendeClienteServiceStub.AtualizaRemessaAgrupadaResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from atualizaRemessaAgrupada operation
     */
    public void receiveErroratualizaRemessaAgrupada(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for solicitaPLP method
     * override this method for handling normal response from solicitaPLP operation
     */
    public void receiveResultsolicitaPLP(
        webService.correios.atende.AtendeClienteServiceStub.SolicitaPLPResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from solicitaPLP operation
     */
    public void receiveErrorsolicitaPLP(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getStatusCartaoPostagem method
     * override this method for handling normal response from getStatusCartaoPostagem operation
     */
    public void receiveResultgetStatusCartaoPostagem(
        webService.correios.atende.AtendeClienteServiceStub.GetStatusCartaoPostagemResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getStatusCartaoPostagem operation
     */
    public void receiveErrorgetStatusCartaoPostagem(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaTarifaVale method
     * override this method for handling normal response from buscaTarifaVale operation
     */
    public void receiveResultbuscaTarifaVale(
        webService.correios.atende.AtendeClienteServiceStub.BuscaTarifaValeResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaTarifaVale operation
     */
    public void receiveErrorbuscaTarifaVale(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for validarPostagemSimultanea method
     * override this method for handling normal response from validarPostagemSimultanea operation
     */
    public void receiveResultvalidarPostagemSimultanea(
        webService.correios.atende.AtendeClienteServiceStub.ValidarPostagemSimultaneaResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from validarPostagemSimultanea operation
     */
    public void receiveErrorvalidarPostagemSimultanea(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getStatusPLP method
     * override this method for handling normal response from getStatusPLP operation
     */
    public void receiveResultgetStatusPLP(
        webService.correios.atende.AtendeClienteServiceStub.GetStatusPLPResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getStatusPLP operation
     */
    public void receiveErrorgetStatusPLP(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaServicosXServicosAdicionais method
     * override this method for handling normal response from buscaServicosXServicosAdicionais operation
     */
    public void receiveResultbuscaServicosXServicosAdicionais(
        webService.correios.atende.AtendeClienteServiceStub.BuscaServicosXServicosAdicionaisResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaServicosXServicosAdicionais operation
     */
    public void receiveErrorbuscaServicosXServicosAdicionais(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for cancelarPedidoScol method
     * override this method for handling normal response from cancelarPedidoScol operation
     */
    public void receiveResultcancelarPedidoScol(
        webService.correios.atende.AtendeClienteServiceStub.CancelarPedidoScolResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from cancelarPedidoScol operation
     */
    public void receiveErrorcancelarPedidoScol(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for bloquearObjeto method
     * override this method for handling normal response from bloquearObjeto operation
     */
    public void receiveResultbloquearObjeto(
        webService.correios.atende.AtendeClienteServiceStub.BloquearObjetoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from bloquearObjeto operation
     */
    public void receiveErrorbloquearObjeto(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaContrato method
     * override this method for handling normal response from buscaContrato operation
     */
    public void receiveResultbuscaContrato(
        webService.correios.atende.AtendeClienteServiceStub.BuscaContratoResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaContrato operation
     */
    public void receiveErrorbuscaContrato(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for solicitaEtiquetas method
     * override this method for handling normal response from solicitaEtiquetas operation
     */
    public void receiveResultsolicitaEtiquetas(
        webService.correios.atende.AtendeClienteServiceStub.SolicitaEtiquetasResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from solicitaEtiquetas operation
     */
    public void receiveErrorsolicitaEtiquetas(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for solicitaXmlPlp method
     * override this method for handling normal response from solicitaXmlPlp operation
     */
    public void receiveResultsolicitaXmlPlp(
        webService.correios.atende.AtendeClienteServiceStub.SolicitaXmlPlpResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from solicitaXmlPlp operation
     */
    public void receiveErrorsolicitaXmlPlp(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for validarPostagemReversa method
     * override this method for handling normal response from validarPostagemReversa operation
     */
    public void receiveResultvalidarPostagemReversa(
        webService.correios.atende.AtendeClienteServiceStub.ValidarPostagemReversaResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from validarPostagemReversa operation
     */
    public void receiveErrorvalidarPostagemReversa(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaCliente method
     * override this method for handling normal response from buscaCliente operation
     */
    public void receiveResultbuscaCliente(
        webService.correios.atende.AtendeClienteServiceStub.BuscaClienteResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaCliente operation
     */
    public void receiveErrorbuscaCliente(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaPagamentoEntrega method
     * override this method for handling normal response from buscaPagamentoEntrega operation
     */
    public void receiveResultbuscaPagamentoEntrega(
        webService.correios.atende.AtendeClienteServiceStub.BuscaPagamentoEntregaResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaPagamentoEntrega operation
     */
    public void receiveErrorbuscaPagamentoEntrega(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for solicitarPostagemScol method
     * override this method for handling normal response from solicitarPostagemScol operation
     */
    public void receiveResultsolicitarPostagemScol(
        webService.correios.atende.AtendeClienteServiceStub.SolicitarPostagemScolResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from solicitarPostagemScol operation
     */
    public void receiveErrorsolicitarPostagemScol(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for buscaServicos method
     * override this method for handling normal response from buscaServicos operation
     */
    public void receiveResultbuscaServicos(
        webService.correios.atende.AtendeClienteServiceStub.BuscaServicosResponseE result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from buscaServicos operation
     */
    public void receiveErrorbuscaServicos(java.lang.Exception e) {
    }
}
