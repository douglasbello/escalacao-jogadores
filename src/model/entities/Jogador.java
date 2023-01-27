package model.entities;

import model.enums.JogadorStatus;

public class Jogador {
    private String numero;
    private String nomeJogador;
    private String posicao;
    private JogadorStatus situacao;
    private boolean participouPartida = false;

    public Jogador(String numero,String nomeJogador,String posicao,JogadorStatus status)
    {
        this.numero = numero;
        this.nomeJogador = nomeJogador;
        this.posicao = posicao;
        this.situacao = status;
    }
    public Jogador(String numero,String nomeJogador,String posicao,JogadorStatus status,boolean participouPartida)
    {
        this.numero = numero;
        this.nomeJogador = nomeJogador;
        this.posicao = posicao;
        this.situacao = status;
        this.participouPartida = participouPartida;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public JogadorStatus getSituacao() {
        return situacao;
    }

    public void setSituacao(JogadorStatus situacao) {
        this.situacao = situacao;
    }

    public boolean getParticipouPartida() {
        return participouPartida;
    }

    public void setParticipouPartida(boolean participouPartida) {
        this.participouPartida = participouPartida;
    }
}
