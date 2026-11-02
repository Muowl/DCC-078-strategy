package padroes.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntregaTest {

    @Test
    void deveCalcularFreteEconomico() {
        Entrega entrega = new Entrega(new FreteEconomico());
        assertEquals(20.0, entrega.calcularFrete(5.0), 0.0001);
        assertEquals(15.0, entrega.calcularFrete(2.5), 0.0001);
    }

    @Test
    void deveCalcularFreteExpresso() {
        Entrega entrega = new Entrega(new FreteExpresso());
        assertEquals(40.0, entrega.calcularFrete(5.0), 0.0001);
        assertEquals(30.0, entrega.calcularFrete(2.5), 0.0001);
    }

    @Test
    void devePermitirRetiradaGratuitaIndependentementeDoPeso() {
        Entrega entrega = new Entrega(new RetiradaLocal());
        assertEquals(0.0, entrega.calcularFrete(0.5), 0.0001);
        assertEquals(0.0, entrega.calcularFrete(50.0), 0.0001);
    }

    @Test
    void deveTrocarEstrategiaNaMesmaEntrega() {
        Entrega entrega = new Entrega(new FreteEconomico());
        assertEquals(20.0, entrega.calcularFrete(5.0), 0.0001);
        entrega.setCalculoFrete(new FreteExpresso());
        assertEquals(40.0, entrega.calcularFrete(5.0), 0.0001);
        entrega.setCalculoFrete(new RetiradaLocal());
        assertEquals(0.0, entrega.calcularFrete(5.0), 0.0001);
    }

    @Test
    void deveDelegarParaUmaNovaEstrategiaSemAlterarContexto() {
        Entrega entrega = new Entrega(pesoKg -> pesoKg * 3.0);
        assertEquals(7.5, entrega.calcularFrete(2.5), 0.0001);
    }

    @Test
    void deveManterEstrategiasIndependentesEntreEntregas() {
        CalculoFrete economico = new FreteEconomico();
        Entrega primeira = new Entrega(economico);
        Entrega segunda = new Entrega(economico);
        primeira.setCalculoFrete(new FreteExpresso());
        assertEquals(40.0, primeira.calcularFrete(5.0), 0.0001);
        assertEquals(20.0, segunda.calcularFrete(5.0), 0.0001);
    }

    @Test
    void deveRejeitarEstrategiaInicialNula() {
        assertThrows(NullPointerException.class, () -> new Entrega(null));
    }

    @Test
    void devePreservarEstrategiaAoRejeitarTrocaNula() {
        Entrega entrega = new Entrega(new FreteEconomico());
        assertThrows(NullPointerException.class, () -> entrega.setCalculoFrete(null));
        assertEquals(20.0, entrega.calcularFrete(5.0), 0.0001);
    }

    @Test
    void deveRejeitarPesosInvalidosAntesDeDelegar() {
        Entrega entrega = new Entrega(pesoKg -> {
            fail("Peso invalido nao deve chegar a estrategia");
            return 0;
        });
        double[] pesos = {0.0, -1.0, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        for (double peso : pesos) {
            assertThrows(IllegalArgumentException.class, () -> entrega.calcularFrete(peso));
        }
    }
}
