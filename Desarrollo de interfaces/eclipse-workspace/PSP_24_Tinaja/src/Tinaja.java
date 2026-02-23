public class Tinaja {
    private int litros = 0;

    // Ritmos actuales (litros/segundo). Si es 0, ese proceso está parado.
    private int ritmoLlenado = 10;
    private int ritmoVaciado = 0;

    // Para no repetir mensajes continuamente
    private int ultimoRitmoLlenado = -1;
    private int ultimoRitmoVaciado = -1;

    public int getLitros() {
        return litros;
    }

    public int getRitmoLlenado() {
        return ritmoLlenado;
    }

    public int getRitmoVaciado() {
        return ritmoVaciado;
    }

    // Se llama cada "segundo" para actualizar litros según ritmos
    public void tick() {
        // Aplicamos llenado y vaciado en el mismo segundo
        litros += ritmoLlenado;
        litros -= ritmoVaciado;

        if (litros < 0) litros = 0;
        if (litros > 1000) litros = 1000;

        // Reglas del enunciado (según litros alcanzados)
        // 1) Al llegar a 900: se activa vaciado 5 mientras sigue llenado 10
        if (litros >= 900 && ritmoVaciado == 0) {
            ritmoVaciado = 5;
        }

        // 2) Al llegar a 1000: llenado se para y vaciado pasa a 10
        if (litros >= 1000) {
            ritmoLlenado = 0;
            ritmoVaciado = 10;
        }

        // 3) Al llegar a 100 litros (bajando): se activa llenado a 5 mientras sigue vaciado 10
        if (litros <= 100 && ritmoVaciado == 10) {
            ritmoLlenado = 5;
        }

        // 4) Al llegar a 0: vaciado se para y llenado sube a 10
        if (litros <= 0) {
            ritmoVaciado = 0;
            ritmoLlenado = 10;
        }
    }

    public void imprimirCambiosSiHay() {
        if (ritmoLlenado != ultimoRitmoLlenado || ritmoVaciado != ultimoRitmoVaciado) {
            System.out.println("** CAMBIO ** Llenado=" + ritmoLlenado + " L/s, Vaciado=" + ritmoVaciado + " L/s");
            ultimoRitmoLlenado = ritmoLlenado;
            ultimoRitmoVaciado = ritmoVaciado;
        }
    }
}
