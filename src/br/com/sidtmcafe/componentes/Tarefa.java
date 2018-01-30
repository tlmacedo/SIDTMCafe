package br.com.sidtmcafe.componentes;

import br.com.sidtmcafe.controller.ControllerCadastroEmpresa;
import br.com.sidtmcafe.interfaces.Constants;
import javafx.concurrent.Task;
import javafx.util.Pair;

import java.util.List;

public class Tarefa implements Constants {


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
                    }
                }
                updateProgress(tarefas.size(), tarefas.size());
                return null;
            }
        };
        //new AlertMensagem().getProgressBarTransparent(voidTask, false);
        new AlertMensagem("Aguarde carregando dados do sistema...", "",
                "ic_aguarde_sentado_orange_32dp.png")
                .getProgressBar(voidTask, true, false, false);
    }

}
