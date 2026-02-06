public enum Tipo {

    IDENTIFICAR("identificar"),
    MANEJAR("manejar");

    private final String tipo;

    Tipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
