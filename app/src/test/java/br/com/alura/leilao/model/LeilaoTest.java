package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario PEDRO = new Usuario("PEDRO");

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {

        //Executar ação esperada

        String descricaoDevolvida = CONSOLE.getDescricao();

        //Teste ação esperada

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void getMaiorLanceQuandoRecebeUmLanceDevolveMaiorLance() {
        //Cenário de teste
        CONSOLE.propoe(new Lance(PEDRO, 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = CONSOLE.getMaiorLance();

        double maiorEsperado = 500.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, delta);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = CONSOLE.getMaiorLance();

        double maiorEsperado = 500.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, delta);

    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 800.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = CONSOLE.getMaiorLance();

        double maiorEsperado = 800.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, delta);

    }

    @Test
    public void getMenorLanceQuandoRecebeUmLanceDevolveMenorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));

        //Executar ação esperada
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        double menorEsperado = 100.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(menorEsperado, menorLanceDevolvido, delta);
    }

    @Test
    public void getMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMenorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double menorLanceEsperado = CONSOLE.getMenorLance();

        double maenorEsperado = 100.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(maenorEsperado, menorLanceEsperado, delta);

    }

    @Test
    public void getMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMenorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 800.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double menorLanceEsperado = CONSOLE.getMenorLance();

        double menorLance = 500.00;
        double delta = 0.0001;

        //Teste ação esperada
        assertEquals(menorLance, menorLanceEsperado, delta);

    }
}