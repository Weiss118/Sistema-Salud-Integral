public enum Condicion {

    ESTRES("estrés"),
    ANSIEDAD("ansiedad"),
    AMBOS("ambos (ansiedad y estrés");

    private final String condicion;

    Condicion(String condicion) {
        this.condicion = condicion;
    }

    public String getCondicion() {
        return condicion;
    }



}
