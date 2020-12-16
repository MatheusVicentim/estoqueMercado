/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marketstock.dao;

import java.util.List;

/**
 *
 * @author Gabriel Vinicius
 */
public interface GenericPessoa {

   public Boolean cadastrar(Object pObjeto);
   public Boolean alterar(Object pObjeto);
   public Boolean excluir(String pCpfCnpj);
   public List<Object> listar();
   public Object carregar(int pnIdPessoa);
}
