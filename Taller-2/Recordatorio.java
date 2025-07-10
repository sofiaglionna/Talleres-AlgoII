package aed;

public class Recordatorio {

    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha); //para evitar aliasing = new Fecha(fecha)
        this.horario = horario;
    }
    /*
     * Por como estan dados los tests, parece ser que se espera que a los atributos de recordatorio se les asigne
     * una copia de los parametros pasados (fecha y horario) para evitar aliasing. 
    */

    public Horario horario() {
        return horario;
    }

    public Fecha fecha() {
        Fecha copiaFecha = new Fecha(fecha); //objeto
        return copiaFecha;
    }

    public String mensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return mensaje+" @ "+fecha+" "+horario;
    }

    @Override //esto estaba mal
    public boolean equals(Object otro) {
        if (this == otro) return true; // si son el mismo objeto, son iguales (como son objetos, el == compara referencias)
        if (otro == null || getClass() != otro.getClass()) return false; // Verificar clase y null

        Recordatorio unRecordatorio = (Recordatorio) otro;

        // Comparar los valores de los atributos en lugar de las referencias
        return mensaje.equals(unRecordatorio.mensaje) &&
               fecha.equals(unRecordatorio.fecha) &&
               horario.equals(unRecordatorio.horario);
    }
}