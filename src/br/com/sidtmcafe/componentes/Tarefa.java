package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.controller.ControllerCadastroEmpresa;
import br.com.sidtmcafe.interfaces.Constants;
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
        alertMensagem.getProgressBar(voidTask, true, true);
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
        alertMensagem.getProgressBar(voidTask, true, true);
    }


    public void tarefaAbreCadastroEmpresa(ControllerCadastroEmpresa cadastroEmpresa, List<Pair> tarefas) {
        Task<Void> voidTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("carregando");
                for (Pair tarefaAtual : tarefas) {
                    updateProgress(tarefas.indexOf(tarefaAtual), tarefas.size());
                    Thread.sleep(200);
                    updateMessage(tarefaAtual.getValue().toString());
                    switch (tarefaAtual.getKey().toString()) {
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
                        case "carregarTodosMunicipios":
                            cadastroEmpresa.carregarTodosMunicipios();
                            break;
                        case "carregarTipoEndereco":
                            cadastroEmpresa.carregarTipoEndereco();
                            break;
                    }
                }
                updateProgress(tarefas.size(), tarefas.size());
                return null;
            }
        };
        //new AlertMensagem().getProgressBarTransparent(voidTask, false);
        new AlertMensagem("Aguarde carregando dados do sistema...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false);
    }


}
