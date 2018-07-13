package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        //Cenário de teste

        Leilao leilao = new Leilao("Console");

        //Executar ação esperada

        String descricaoDevolvida = leilao.getDescricao();

        //Teste ação esperada

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeUmLanceDevolveMaiorLance() {
        //Cenário de teste
        Leilao console = new Leilao("console");
        console.propoe(new Lance(new Usuario("joao"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = console.getMaiorLance();

        double maiorEsperado = 500.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, delta);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance() {
        //Cenário de teste
        Leilao console = new Leilao("console");
        console.propoe(new Lance(new Usuario("joao"), 100.00));
        console.propoe(new Lance(new Usuario("joao"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = console.getMaiorLance();

        double maiorEsperado = 500.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, delta);

    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLAnce() {
        //Cenário de teste
        Leilao console = new Leilao("console");
        console.propoe(new Lance(new Usuario("joao"), 800.00));
        console.propoe(new Lance(new Usuario("joao"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = console.getMaiorLance();

        double maiorEsperado = 800.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, delta);

    }
}