package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricao() {

        //Cenário de teste

        Leilao leilao = new Leilao("Console");

        //Executar ação esperada

        String descricaoDevolvida = leilao.getDescricao();

        //Teste ação esperada

        assertEquals("Console", descricaoDevolvida);
    }
}