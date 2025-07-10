package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;
    private int longitud;

    public ArregloRedimensionableDeRecordatorios() {
        recordatorios = new Recordatorio[0];
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAtras(Recordatorio i) {
        longitud += 1;
        Recordatorio[] copiaRecord = new Recordatorio[longitud];

        for(int j=0; j < longitud - 1; j++) {
            copiaRecord[j] = recordatorios[j];
        }   
   
        copiaRecord[longitud - 1] = i;
        recordatorios = copiaRecord;
    }

    public Recordatorio obtener(int i) {
        return recordatorios[i];
    }

    public void quitarAtras() {
        longitud -= 1;
        Recordatorio[] copiaRecord = new Recordatorio[longitud];
        for(int j=0; j < longitud; j++) { // longitud - 1?
            copiaRecord[j] = recordatorios[j];
        }   
        recordatorios = copiaRecord;
     }

    public void modificarPosicion(int indice, Recordatorio valor) {
        recordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.longitud = vector.longitud;
        this.recordatorios = new Recordatorio[vector.longitud];
        for(int i=0; i<vector.longitud; i++) {
            this.recordatorios[i] = vector.obtener(i);
    }
    }
    

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios nuevoArreglo = new ArregloRedimensionableDeRecordatorios();
        for (int j = 0; j<this.longitud(); j++){
            nuevoArreglo.agregarAtras(this.obtener(j));
        }
        return nuevoArreglo;
    }
}
