package metodos;

import entidades.Cancion;
import java.util.List;

public class metodosFicha {

    public static String calcularDuracionTotal(List<Cancion> canciones) {
        int duracionTotalMinutos = 0;

        for (Cancion cancion : canciones) {
            duracionTotalMinutos += cancion.getDuracionMinutos();
        }

        int horas = duracionTotalMinutos / 60;
        int minutos = duracionTotalMinutos % 60;

        return String.format("%02d:%02d:00", horas, minutos);
    }
}
