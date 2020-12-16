/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.model;

/**
 *
 * @author Gabriel Vinicius
 */
public class Embalagem {
    private int idEmbalagem;
    private String tipoEmbalagem;
    private int quantidadeEmbalagem;
    private String descricaoEmbalagem;
    private String codigoBarraEmbalagem;
    private String situacao;

    public Embalagem() {
    }

    public Embalagem(int idEmbalagem, String tipoEmbalagem, int quantidadeEmbalagem, String descricaoEmbalagem, String codigoBarraEmbalagem, String situacao) {
        this.idEmbalagem = idEmbalagem;
        this.tipoEmbalagem = tipoEmbalagem;
        this.quantidadeEmbalagem = quantidadeEmbalagem;
        this.descricaoEmbalagem = descricaoEmbalagem;
        this.codigoBarraEmbalagem = codigoBarraEmbalagem;
        this.situacao = situacao;
    }

    public int getIdEmbalagem() {
        return idEmbalagem;
    }

    public void setIdEmbalagem(int idEmbalagem) {
        this.idEmbalagem = idEmbalagem;
    }

    public String getTipoEmbalagem() {
        return tipoEmbalagem;
    }

    public void setTipoEmbalagem(String tipoEmbalagem) {
        this.tipoEmbalagem = tipoEmbalagem;
    }

    public int getQuantidadeEmbalagem() {
        return quantidadeEmbalagem;
    }

    public void setQuantidadeEmbalagem(int quantidadeEmbalagem) {
        this.quantidadeEmbalagem = quantidadeEmbalagem;
    }

    public String getDescricaoEmbalagem() {
        return descricaoEmbalagem;
    }

    public void setDescricaoEmbalagem(String descricaoEmbalagem) {
        this.descricaoEmbalagem = descricaoEmbalagem;
    }

    public String getCodigoBarraEmbalagem() {
        return codigoBarraEmbalagem;
    }

    public void setCodigoBarraEmbalagem(String codigoBarraEmbalagem) {
        this.codigoBarraEmbalagem = codigoBarraEmbalagem;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
