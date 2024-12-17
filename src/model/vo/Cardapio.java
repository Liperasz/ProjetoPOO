package model.vo;

import java.util.Date;
import java.util.List;

public class Cardapio {

    private List<Comida> comidas;
    private Date data;
    private Turno turno;

    public Cardapio(List<Comida> comidas, Date data, Turno turno) {
        this.comidas = comidas;
        this.data = data;
        this.turno = turno;
    }
}
