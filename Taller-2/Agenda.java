package aed;

public class Agenda {
    private Fecha fActual;
    private Recordatorio[] agenda;

    public Agenda(Fecha fechaActual) {
        fActual = fechaActual;
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        if (agenda == null){ //si no hay agenda, creo una nueva lista con el recordatorio
            agenda = new Recordatorio[1];
            agenda[0] = recordatorio;
        } else {
            Recordatorio[] copiaRecord = new Recordatorio[agenda.length + 1];
            for (int i = 0; i < agenda.length; i++) {
                copiaRecord[i] = agenda[i];
            }
            copiaRecord[copiaRecord.length - 1] = recordatorio; //agregoo el nuevo recordatorio a la lista de agenda que ya existia
            agenda = copiaRecord;
        }
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(fActual.toString()).append("\n=====\n"); //pasO el dia y mes a string y le agrego los "====="

        for (Recordatorio recordatorio : agenda) { //recorro la agenda y SOLO muestro los recordatorios que tienen la misma fecha que la actual
            if (recordatorio.fecha().equals(fActual)) {
                str.append(recordatorio.mensaje()).append(" @ ");
                str.append(recordatorio.fecha()).append(" ");
                str.append(recordatorio.horario()).append("\n");
            }
        }
        return str.toString();
    }

    public void incrementarDia() {
        fActual.incrementarDia(); //incremento el dia de la fecha actual
        for (Recordatorio recordatorio : agenda) { //recorro la agenda y si el recordatorio tiene la misma fecha que la actual, lo incremento
            if (recordatorio.fecha().equals(fActual)) {
                recordatorio.fecha().incrementarDia();
            }
        }
    }

    public Fecha fechaActual() {
        return new Fecha(fActual);
    }

}
