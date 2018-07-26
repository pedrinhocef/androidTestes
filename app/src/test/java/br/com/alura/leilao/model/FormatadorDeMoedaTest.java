package br.com.alura.leilao.model;

import org.junit.Test;

import br.com.alura.leilao.formatter.FormatadorDeMoeda;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FormatadorDeMoedaTest {

    @Test
    public void deve_formatarParaMoeda_QuandoRecebeValorDouble(){
        FormatadorDeMoeda formatador = new FormatadorDeMoeda();

        String moedaFormatada = formatador.formata(200.0);

        assertThat(moedaFormatada, is(equalTo("R$ 200,00")));
    }
}
