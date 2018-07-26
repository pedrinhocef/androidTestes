package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.MesmoUsuarioDoUltimoLanceException;
import br.com.alura.leilao.exception.UsuarioDeuMaisDeCincoLancesException;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class LeilaoTest {

    private static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario PEDRO = new Usuario("Pedro");
    private final Usuario NICE = new Usuario("Nice");

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {

        //Executar ação esperada

        String descricaoDevolvida = CONSOLE.getDescricao();

        //Teste ação esperada

//        assertEquals("Console", descricaoDevolvida);
        assertThat(descricaoDevolvida, equalTo("Console" ));
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

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance() {
        CONSOLE.propoe(new Lance(PEDRO, 800.00));
        CONSOLE.propoe(new Lance(new Usuario("PEDRO"), 500.00));

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
    public void getDeveDevolverOsTresMaioresLancesQuandoRecebeExatoTresLances() {
        CONSOLE.propoe(new Lance(PEDRO, 300.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 500.00));
        CONSOLE.propoe(new Lance(PEDRO, 700.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();
//        assertEquals(3, tresMaioresLances.size());
//        assertEquals(700.00, tresMaioresLances.get(0).getValor(), DELTA);
//        assertEquals(500.00, tresMaioresLances.get(1).getValor(), DELTA);
//        assertEquals(300.00, tresMaioresLances.get(2).getValor(), DELTA);

        assertThat(tresMaioresLances,
                both(Matchers.<Lance>hasSize(3))
                        .and(contains(
                                new Lance(PEDRO, 300.00),
                                new Lance(new Usuario("Nice"), 500.00),
                                new Lance(PEDRO, 700.00))));
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
        assertEquals(200, tresMaioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoRecebeDoisLances() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 300.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(300, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(200, tresMaioresLances.get(1).getValor(), DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoTemMaisDeTresLances() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));
        CONSOLE.propoe(new Lance(new Usuario("joao"), 300.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 400.00));
        CONSOLE.propoe(new Lance(new Usuario("Nice"), 500.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(500, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(400, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(300, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void getDeveDevolverOsTresMaioresLAncesQuandoTemMaisDeQuatroLances() {
        CONSOLE.propoe(new Lance(PEDRO, 200.00));
        CONSOLE.propoe(new Lance(NICE, 300.00));
        CONSOLE.propoe(new Lance(PEDRO, 400.00));
        CONSOLE.propoe(new Lance(NICE, 500.00));
        CONSOLE.propoe(new Lance(PEDRO, 600.00));

        List<Lance> tresMaioresLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(600, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(500, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(400, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void getDeveDevolverZeroQuandoRecebeMaiorLance() {
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void getDeveDevolverZeroQuandoRecebeMenorLance() {
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeveAceitarLanceMenorDoQueLanceAtual() {
        CONSOLE.propoe(new Lance(PEDRO, 600.00));
        CONSOLE.propoe(new Lance(PEDRO, 500.00));

    }

    @Test(expected = MesmoUsuarioDoUltimoLanceException.class)
    public void naoDeveAdicionarLanceQuandoForDomMesmoUsuarioOultimoLance() {
        CONSOLE.propoe(new Lance(PEDRO, 500));
        CONSOLE.propoe(new Lance(PEDRO, 600));

    }

    @Test(expected = UsuarioDeuMaisDeCincoLancesException.class)
    public void naoDeveAceitarCincoLancesDoMesmoUsuario() {
        CONSOLE.propoe(new Lance(PEDRO, 100.00));
        CONSOLE.propoe(new Lance(NICE, 200.00));
        CONSOLE.propoe(new Lance(PEDRO, 300.00));
        CONSOLE.propoe(new Lance(NICE, 400.00));
        CONSOLE.propoe(new Lance(PEDRO, 500.00));
        CONSOLE.propoe(new Lance(NICE, 600.00));
        CONSOLE.propoe(new Lance(PEDRO, 700.00));
        CONSOLE.propoe(new Lance(NICE, 800.00));
        CONSOLE.propoe(new Lance(PEDRO, 900.00));
        CONSOLE.propoe(new Lance(NICE, 1000.00));
        CONSOLE.propoe(new Lance(PEDRO, 1100.00));

    }


}