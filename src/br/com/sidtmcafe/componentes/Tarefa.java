package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.controller.ControllerCadastroEmpresa;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.dao.WsCepPostmonDAO;
import br.com.sidtmcafe.model.dao.WsCnpjReceitaWsDAO;
import br.com.sidtmcafe.model.vo.WsCepPostmonVO;
import br.com.sidtmcafe.model.vo.WsCnpjReceitaWsVO;
import javafx.concurrent.Task;
import javafx.util.Pair;
import webService.correios.atende.EnderecoERP;
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
    WsCepPostmonVO wsCepPostmonVO;
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

    public WsCnpjReceitaWsVO tarefaWsCnpjReceitaWs(List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    String valCnpj = tarefaAtual.getValue().toString().replaceAll("[\\-/. \\[\\]]", "");
                    valCnpj = valCnpj.substring(valCnpj.length() - 14);
                    wsCnpjReceitaWsVO = new WsCnpjReceitaWsDAO().getWsCnpjReceitaWsVO(valCnpj);

                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde pesquisando cnpj na receita federal...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
        return wsCnpjReceitaWsVO;
    }

    public WsCepPostmonVO tarefaWsCepPostmon(List<Pair> tarefas) {
        qtdTarefas = tarefas.size();
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    updateProgress(tarefas.indexOf(tarefaAtual), qtdTarefas);
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    String valCep = tarefaAtual.getValue().toString().replaceAll("[\\-/. \\[\\]]", "");
                    valCep = valCep.substring(valCep.length() - 8);
                    wsCepPostmonVO = new WsCepPostmonDAO().getCepPostmonVO(valCep);
                }
                updateProgress(qtdTarefas, qtdTarefas);
                return null;
            }
        };
        new AlertMensagem("Aguarde pesquisando cep nos correios...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, qtdTarefas);
        return wsCepPostmonVO;
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
                            cadastroEmpresa.criarTabelaEmpresa();
                            break;
                        case "carregarSisTipoEndereco":
                            cadastroEmpresa.carregarSisTipoEndereco();
                            break;
                        case "carregarSisTelefoneOperadora":
                            cadastroEmpresa.carregarSisTelefoneOperadora();
                            break;
                        case "carregarTodosMunicipios":
                            cadastroEmpresa.carregarTodosMunicipios();
                            break;
                        case "carregarListaEmpresa":
                            cadastroEmpresa.carregarListaEmpresa();
                            break;
                        case "preencherCboEndUF":
                            cadastroEmpresa.preencherCboEndUF();
                            break;
                        case "preencherCboSituacaoSistema":
                            cadastroEmpresa.preencherCboSituacaoSistema();
                            break;
                        case "preencherCboFiltroPesquisa":
                            cadastroEmpresa.preencherCboFiltroPesquisa();
                            break;
                        case "preencherCboClassificacaoJuridica":
                            cadastroEmpresa.preencherCboClassificacaoJuridica();
                            break;
                        case "preencherTabelaEmpresa":
                            cadastroEmpresa.preencherTabelaEmpresa();
                            break;
                        case "carregarTabCargo":
                            cadastroEmpresa.carregarTabCargo();
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

    public boolean tarefaSalvaEmpresa(ControllerCadastroEmpresa cadastroEmpresa, List<Pair> tarefas) {
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
                        case "carregarListaEmpresa":
                            cadastroEmpresa.carregarListaEmpresa();
                            break;
                        case "salvarEmpresa":
                            cadastroEmpresa.salvarEmpresa();
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
        return true;
    }

}
