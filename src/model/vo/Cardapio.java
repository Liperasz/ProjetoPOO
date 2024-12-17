package model.vo;

import java.time.LocalDate;
import java.util.List;

public class Cardapio {

    private List<Comida> comidas;
    private LocalDate data;
    private Turno turno;

    public Cardapio(List<Comida> comidas, LocalDate data, Turno turno) {
        this.comidas = comidas;
        this.data = data;
        this.turno = turno;
    }
}
