package aed;

public class Horario {

    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override
    public String toString() {
        return hora+":"+minutos;
    }

    @Override
    public boolean equals(Object otro) {

        if (otro.getClass() != Horario.class) return false; //verificar si es de clase (si no son de la misma tira false directamente)
        
        Horario unHorario = (Horario)otro;

        return (hora == unHorario.hora() && minutos == unHorario.minutos());
    }

}
