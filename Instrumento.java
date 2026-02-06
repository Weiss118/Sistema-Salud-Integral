public class Instrumento {

    private String nombre;
    private Tipo tipo;
    private String autor;
    private double clave;
    private Condicion condicion;
    private Forma forma;
    private Evaluacion evaluacion;
    private String cita;

    public Instrumento(double clave, String nombre, Tipo tipo, Condicion condicion,
                       Forma forma, String autor, Evaluacion evaluacion, String cita) {
        this.clave = clave;
        this.nombre = nombre;
        this.tipo = tipo;
        this.condicion = condicion;
        this.forma = forma;
        this.autor = autor;
        this.evaluacion = evaluacion;
        this.cita = cita;
    }

    // Bloque de setters y getters.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getClave() {
        return clave;
    }

    public void setClave(double clave) {
        this.clave = clave;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    // Le damos formato a nuestro objeto.
    @Override
    public String toString() {
        return "--------------------------------------------------\n" +
                "Clave: " + clave + "\n" +
                "Nombre: " + nombre + "\n" +
                "Autor: " + autor + "\n" +
                "Propósito: " + tipo.getTipo() + "\n" +
                "Condición: " + condicion.getCondicion() + "\n" +
                "Formato: " + forma.getEtiqueta() + "\n" +
                "Evaluación: " + evaluacion.getNombreMostrar() + " (" + evaluacion.getDescripcion() + ")\n" +
                "Cita: " + cita + "\n" +
                "--------------------------------------------------";
    }
}
