package padroes.strategy;

public interface CalculoFrete {

    /** Recebe peso positivo e finito, validado pelo contexto Entrega. */
    double calcular(double pesoKg);
}
