package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.controller.ControllerCadastroEmpresa;
import br.com.sidtmcafe.controller.ControllerCadastroProduto;
import br.com.sidtmcafe.controller.ControllerEntradaProduto;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.dao.WsCepPostmonDAO;
import br.com.sidtmcafe.model.dao.WsCnpjReceitaWsDAO;
import br.com.sidtmcafe.model.dao.WsEanCosmosDAO;
import br.com.sidtmcafe.model.model.TabModel;
import br.com.sidtmcafe.model.vo.*;
import br.com.sidtmcafe.service.FormatarDado;
import javafx.concurrent.Task;
import javafx.util.Pair;
import org.apache.velocity.runtime.directive.contrib.For;
import webService.fonteDeDados.ConsultaStub;
import webService.fonteDeDados.ConsultaStub.*;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.List;

public class Tarefa implements Constants {

    URL url;
    BigDecimal getSaldo;
    WsCnpjReceitaWsVO wsCnpjReceitaWsVO;
    TabEmpresaVO tabEmpresaVO;
    TabProdutoVO tabProdutoVO;
    TabEnderecoVO tabEnderecoVO;
    WsCepPostmonVO wsCepPostmonVO;
    WsEanCosmosVO wsEanCosmosVO;
    int qtdTarefas = 1;


    public void tarefaWsCorreios_BuscaCEP(String cep) {
        AlertMensagem alertMensagem = new AlertMensagem();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("localizando cep [" + cep + "] ");
                try {
//                    RastroStub rastroStub;
//                    url = new URL("http://webservice.correios.com.br/service/rastro/Rastro.wsdl");
//                    BuscaEventos buscaEventos = new BuscaEventos();
//                    BuscaEventosE buscaEventosE = new BuscaEventosE();
//                    RastroStub.BuscaEventosResponse buscaEventosResponse = new RastroStub.BuscaEventosResponse();
//
//                    buscaEventos.setUsuario("9999999999");
//                    buscaEventos.setSenha("S@1234YWC5");
//                    buscaEventos.setTipo("L");
//                    buscaEventos.setResultado("T");
//                    buscaEventos.setLingua("101");
//                    //buscaEventos.setObjetos(objeto);
//
//                    buscaEventosE.setBuscaEventos(buscaEventos);
//
//                    rastroStub = new RastroStub(url.toString());
//                    buscaEventosResponse = rastroStub.buscaEventos(buscaEventosE).getBuscaEventosResponse();
//                    System.out.println("retorno objeto correio: " + buscaEventosResponse.get_return().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                alertMensagem.setResultPromptText("");
                return null;
            }
        };
        alertMensagem.getProgressBar(voidTask, true, true, qtdTarefas);
    }

    public void tarefaWsFonteDeDados_ConstulaSaldo() {
        AlertMensagem alertMensagem = new AlertMensagem();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("consultando saldo ");
                try {
                    Saldo saldo = new Saldo();
                    ConsultaStub consultaStub;

                    url = new URL("http://ws.fontededados.com.br/consulta.asmx");
                    saldo.setLogin(WS_FONTE_DE_DADOS_LOGIN_NAME);
                    saldo.setSenha(WS_FONTE_DE_DADOS_LOGIN_SENHA);

                    consultaStub = new ConsultaStub(url.toString());
                    getSaldo = consultaStub.saldo(saldo).getSaldoResult().getValor().setScale(2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                alertMensagem.setResultPromptText("Seu saldo é: R$ " + new DecimalFormat("0.00").format(getSaldo).replace(".", ","));
                return null;
            }
        };
        alertMensagem.getProgressBar(voidTask, true, true, qtdTarefas);
    }

    public TabEmpresaVO tarefaWsCnpjReceitaWs(List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    switch (tarefaAtual.getKey().toString()) {
                        case "buscarCNPJ":
                            updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                            Thread.sleep(200);
                            String cnpj = FormatarDado.getCampoFormatado(tarefaAtual.getValue().toString(), "cnpj");
                            updateMessage("Pesquisando C.N.P.J: [" + cnpj + "]");
                            tabEmpresaVO = new WsCnpjReceitaWsDAO().getTabEmpresaVO(tarefaAtual.getValue().toString());
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde pesquisando cnpj na receita federal...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
        return tabEmpresaVO;
    }

    public TabProdutoVO tarefaWsEanCosmos(List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando.");
                for (Pair tarefaAtual : tarefas) {
                    switch (tarefaAtual.getKey().toString()){
                        case "buscaEan":
                            updateProgress(tarefas.indexOf(tarefaAtual),qtdTarefas);
                            Thread.sleep(201);
                            String ean  = tarefaAtual.getValue().toString();
                            updateMessage("Pesquisando EAN: [" + ean + "]");
                            tabProdutoVO = new WsEanCosmosDAO().getProdutoVO(tarefaAtual.getValue().toString());
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde pesquisando código Ean...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
        return tabProdutoVO;
    }

    public TabEnderecoVO tarefaWsCepPostmon(List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    switch (tarefaAtual.getKey().toString()) {
                        case "buscarCEP":
                            updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                            Thread.sleep(200);
                            String cep = FormatarDado.getCampoFormatado(tarefaAtual.getValue().toString(), "cep");
                            updateMessage("Pesquisando CEP: [" + cep + "]");
                            tabEnderecoVO = new WsCepPostmonDAO().getTabEnderecoVO(tarefaAtual.getValue().toString());
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde pesquisando cep nos correios...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
        return tabEnderecoVO;
    }

    public void tarefaAbreCadastroEmpresa(ControllerCadastroEmpresa cadastroEmpresa, List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
                        case "criarTabelaEmpresa":
                            TabModel.tabelaEmpresa();
                            //TabModel.tabelaQsaReceita();
                            break;
                        case "preencherCboFiltroPesquisa":
                            cadastroEmpresa.preencherCboFiltroPesquisa();
                            break;
                        case "preencherCboClassificacaoJuridica":
                            cadastroEmpresa.preencherCboClassificacaoJuridica();
                            break;
                        case "preencherCboSituacaoSistema":
                            cadastroEmpresa.preencherCboSituacaoSistema();
                            break;
                        case "preencherCboEndUF":
                            cadastroEmpresa.preencherCboEndUF();
                            break;
                        case "carregarTabCargo":
                            cadastroEmpresa.carregarTabCargo();
                            break;
                        case "carregarSisTipoEndereco":
                            cadastroEmpresa.carregarSisTipoEndereco();
                            break;
                        case "carregarSisTelefoneOperadora":
                            cadastroEmpresa.carregarSisTelefoneOperadora();
                            break;
                        case "carregarListaEmpresa":
                            cadastroEmpresa.carregarListaEmpresa();
                            break;
                        case "preencherTabelaEmpresa":
                            cadastroEmpresa.preencherTabelaEmpresa();
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde carregando dados do sistema...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
    }

    public void tarefaAbreCadastroProduto(ControllerCadastroProduto cadastroProduto, List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando.");
                for (Pair tarefaAtual : tarefas) {
                    updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(201);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
                        case "criarTabelaProduto":
                            TabModel.tabelaProduto();
                            break;
                        case "preencherCboUnidadeComercial":
                            cadastroProduto.preencherCboUnidadeComercial();
                            break;
                        case "preencherCboSituacaoSistema":
                            cadastroProduto.preencherCboSituacaoSistema();
                            break;
                        case "preencherCboFiscalOrigem":
                            cadastroProduto.preencherCboFiscalOrigem();
                            break;
                        case "preencherCboFiscalIcms":
                            cadastroProduto.preencherCboFiscalIcms();
                            break;
                        case "preencherCboFiscalPis":
                            cadastroProduto.preencherCboFiscalPis();
                            break;
                        case "preencherCboFiscalCofins":
                            cadastroProduto.preencherCboFiscalCofins();
                            break;
                        case "carregarListaProduto":
                            cadastroProduto.carregarListaProduto();
                            break;
                        case "preencherTabelaProduto":
                            cadastroProduto.preencherTabelaProduto();
                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde carregando dados do sistema...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
    }

    public void tarefaAbreEntradaProduto(ControllerEntradaProduto entradaProduto, List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
//                        case "carregarLojaDestino":
//                            entradaProduto.carregarLojaDestino();
//                            break;
//                        case "carregarFornecedor":
//                            entradaProduto.carregarFornecedor();
//                            break;
//                        case "carregarTributo":
//                            entradaProduto.carregarTributo();
//                            break;
//                        case "carregarTomadorServico":
//                            entradaProduto.carregarTomadorServico();
//                            break;
//                        case "carregarModelo":
//                            entradaProduto.carregarModelo();
//                            break;
//                        case "carregarSituacaoTributaria":
//                            entradaProduto.carregarSituacaoTributaria();
//                            break;
//                        case "carregarTransportadora":
//                            entradaProduto.carregarTransportadora();
//                            break;
//                        case "carregarListaProduto":
//                            TabModel.tabelaProduto();
//                            break;
                    }
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde carregando dados do sistema...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
    }

//    public boolean tarefaSalvaEmpresa(ControllerCadastroEmpresa cadastroEmpresa, List<Pair> tarefas) {
//        qtdTarefas = tarefas.size();
//        Task<Void> voidTask = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                updateMessage("carregando");
//                for (Pair tarefaAtual : tarefas) {
//                    updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
//                    Thread.sleep(200);
//                    updateMessage(tarefaAtual.getValue().toString());
//                    switch (tarefaAtual.getKey().toString()) {
//                        case "carregarListaEmpresa":
//                            cadastroEmpresa.carregarListaEmpresa();
//                            break;
//                        case "salvarEmpresa":
//                            cadastroEmpresa.salvarEmpresa();
//                            break;
//                        case "preencherTabelaEmpresa":
//                            cadastroEmpresa.preencherTabelaEmpresa();
//                            break;
//                    }
//                }
//                updateProgress(qtdTarefas, qtdTarefas);
//                return null;
//            }
//        };
//        new AlertMensagem("Aguarde carregando dados do sistema...", "",
//                "ic_aguarde_sentado_orange_32dp.png")
//                .getProgressBar(voidTask, true, false, qtdTarefas);
//        return true;
//    }

}
