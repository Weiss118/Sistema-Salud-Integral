public enum Forma {

    ESCALA("Escala", "Mide grados o niveles de intensidad"),
    CUESTIONARIO("Cuestionario", "Serie de preguntas"),
    TEST("Test", "Prueba psicométrica estandarizada");

    private final String etiqueta;
    private final String descripcion;

    Forma(String etiqueta, String descripcion) {
        this.etiqueta = etiqueta;
        this.descripcion = descripcion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
