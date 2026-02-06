import java.util.ArrayList;

public class Coleccion {

    private ArrayList<Instrumento> instrumentos;

    public Coleccion(){
        this.instrumentos = new ArrayList<>();
    }

    // Buscamos un instrumento por clave.
    public Instrumento buscarPorClave(double clave){
        return instrumentos.stream()
                .filter(ins -> ins.getClave() == clave)
                .findFirst()
                .orElse(null); // Si no encuentra un objeto con la clave, regresamos null
    }

    // Agrega un instrumento.
    public void agregar(Instrumento instrumento) {
        instrumentos.add(instrumento);
    }

    public ArrayList<Instrumento> getInstrumentos(){
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    // Elimina un instrumento
    public boolean eliminar (Instrumento instrumento){
        return instrumentos.remove(instrumento);
    }
}
