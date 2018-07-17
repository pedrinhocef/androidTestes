package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    private static final double DELTA = 0.0001;
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

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, DELTA);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = CONSOLE.getMaiorLance();

        double maiorEsperado = 500.00;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, DELTA);

    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 800.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double maiorLanceEsperado = CONSOLE.getMaiorLance();

        double maiorEsperado = 800.00;

        //Teste ação esperada
        assertEquals(maiorEsperado, maiorLanceEsperado, DELTA);

    }

    @Test
    public void getMenorLanceQuandoRecebeUmLanceDevolveMenorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));

        //Executar ação esperada
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        double menorEsperado = 100.00;

        //Teste ação esperada
        assertEquals(menorEsperado, menorLanceDevolvido, DELTA);
    }

    @Test
    public void getMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMenorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double menorLanceEsperado = CONSOLE.getMenorLance();

        double maenorEsperado = 100.00;

        //Teste ação esperada
        assertEquals(maenorEsperado, menorLanceEsperado, DELTA);

    }

    @Test
    public void getMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMenorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 800.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

        //Executar ação esperada
        double menorLanceEsperado = CONSOLE.getMenorLance();

        double menorLance = 500.00;

        //Teste ação esperada
        assertEquals(menorLance, menorLanceEsperado, DELTA);

    }

    @Test
    public void getDeveDevolverOsTresMaioresLancesQuandoRecebeExatoTresLances() {
        CONSOLE.propoe(new Lance(PEDRO, 300.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 500.00));
        CONSOLE.propoe(new Lance(PEDRO, 700.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(700.00, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(500.00, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoNaoRecebeNenhumLance() {
        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLances.size());
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoApenasUmLance() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLances.size());
        assertEquals(200, tresMaioresLances.get(0).getValor(),DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoRecebeDoisLances() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 300.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(300, tresMaioresLances.get(0).getValor(),DELTA);
        assertEquals(200, tresMaioresLances.get(1).getValor(),DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoMaisDeTresLances() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));
        CONSOLE.propoe(new Lance(PEDRO, 300.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 400.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 500.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(500, tresMaioresLances.get(0).getValor(),DELTA);
        assertEquals(400, tresMaioresLances.get(1).getValor(),DELTA);
        assertEquals(300, tresMaioresLances.get(2).getValor(),DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoMaisDeQuatroLances() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));
        CONSOLE.propoe(new Lance(PEDRO, 300.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 400.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 500.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 600.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(600, tresMaioresLances.get(0).getValor(),DELTA);
        assertEquals(500, tresMaioresLances.get(1).getValor(),DELTA);
        assertEquals(400, tresMaioresLances.get(2).getValor(),DELTA);
    }
}