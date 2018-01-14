package br.com.sidtmcafe.interfaces;

public interface Database {
    public abstract boolean ConectarBancoDeDados();

    public abstract void DesconectarBancoDeDados();
}
