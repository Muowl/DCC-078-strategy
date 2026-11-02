package padroes.strategy;

import java.util.Objects;

public class Entrega {

    private CalculoFrete calculoFrete;

    public Entrega(CalculoFrete calculoFrete) {
        setCalculoFrete(calculoFrete);
    }

    public void setCalculoFrete(CalculoFrete calculoFrete) {
        this.calculoFrete = Objects.requireNonNull(calculoFrete, "Calculo de frete obrigatorio");
    }

    public double calcularFrete(double pesoKg) {
        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            throw new IllegalArgumentException("Peso deve ser positivo e finito");
        }
        return calculoFrete.calcular(pesoKg);
    }
}
