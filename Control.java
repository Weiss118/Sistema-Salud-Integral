import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class Control {

    private Coleccion coleccion;
    private final String ARCHIVO = "instrumentos_apa.csv";

    public Control() {
        this.coleccion = new Coleccion();
    }

    // Agregamos un instrumento, en caso de no poderse agregar regresamos false.
    public boolean agregarInstrumento(Instrumento ins) {
        if (coleccion.buscarPorClave(ins.getClave()) != null) {
            return false;
        }
        coleccion.agregar(ins);
        return true;
    }

    // Eliminamos un instrumento, en caso de no poderse regresamos false.
    public boolean eliminarInstrumento(double clave) {
        Instrumento aEliminar = coleccion.buscarPorClave(clave);
        if (aEliminar != null) {
            return coleccion.eliminar(aEliminar);
        }
        return false;
    }

    public ArrayList<Instrumento> obtenerTodos() {
        return coleccion.getInstrumentos();
    }

    // ----- Consultas -----

    // Consultamos por autor.
    public ArrayList<Instrumento> consultarPorAutor(String autor) {
        return (ArrayList<Instrumento>) coleccion.getInstrumentos().stream()
                .filter(i -> i.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Consultamos por tipo.
    public ArrayList<Instrumento> consultarPorTipo(String textoTipo) {
        return (ArrayList<Instrumento>) coleccion.getInstrumentos().stream()
                .filter(i -> i.getTipo().getTipo().equalsIgnoreCase(textoTipo) ||
                        i.getTipo().name().equalsIgnoreCase(textoTipo))
                .collect(Collectors.toList());
    }

    // Consultamos por forma
    public ArrayList<Instrumento> consultarPorForma(String textoForma) {
        return (ArrayList<Instrumento>) coleccion.getInstrumentos().stream()
                .filter(i -> i.getForma().getEtiqueta().equalsIgnoreCase(textoForma) ||
                        i.getForma().name().equalsIgnoreCase(textoForma))
                .collect(Collectors.toList());
    }

    // Consultamos por condición
    public ArrayList<Instrumento> consultarPorCondicion(String textoCondicion) {
        return (ArrayList<Instrumento>) coleccion.getInstrumentos().stream()
                .filter(i -> i.getCondicion().getCondicion().equalsIgnoreCase(textoCondicion) ||
                        i.getCondicion().name().equalsIgnoreCase(textoCondicion))
                .collect(Collectors.toList());
    }

    // Consultamos de acuerdo a una condición y si están validados.
    public ArrayList<Instrumento> consultarCondicionYValidez(Condicion cond) {
        return (ArrayList<Instrumento>) coleccion.getInstrumentos().stream()
                .filter(i -> (i.getCondicion() == cond || i.getCondicion() == Condicion.AMBOS))
                .filter(i -> (i.getEvaluacion() == Evaluacion.VALIDEZ))
                .collect(Collectors.toList());
    }

    // ---- Ordenar ----
    public void ordenarPorClave() {
        coleccion.getInstrumentos().sort(Comparator.comparingDouble(Instrumento::getClave));
    }

    public void ordenarPorAutor() {
        coleccion.getInstrumentos().sort(Comparator.comparing(Instrumento::getAutor));
    }

    // Aquí se carga el archivo y se guardan nuevos registros en el mismo.
    // Esta parte del codigo lo realice con la IA Gemini debido a que no recordaba
    // exactamente como se realizaba esto, aparte de falta de tiempo.

    public void guardarArchivo() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Instrumento i : coleccion.getInstrumentos()) {
                writer.printf(Locale.US, "%.2f,%s,%s,%s,%s,%s,%s,%s%n",
                        i.getClave(), i.getNombre(),
                        i.getTipo().name(), i.getCondicion().name(), i.getForma().name(),
                        i.getAutor(), i.getEvaluacion().name(), i.getCita());
            }
        }
        // Si falla, Java lanzará IOException automáticamente hacia quien llamó este método.
    }

    public void cargarArchivo() throws IOException {
        File f = new File(ARCHIVO);
        if (!f.exists()) return;

        ArrayList<Instrumento> listaNueva = new ArrayList<>();
        // Usamos try-with-resources, pero si falla algo crítico (como disco lleno), la excepción sube.
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
                    try {
                        Instrumento ins = new Instrumento(
                                Double.parseDouble(datos[0]),
                                datos[1],
                                Tipo.valueOf(datos[2]),
                                Condicion.valueOf(datos[3]),
                                Forma.valueOf(datos[4]),
                                datos[5],
                                Evaluacion.valueOf(datos[6]),
                                datos[7]
                        );
                        listaNueva.add(ins);
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
            coleccion.setInstrumentos(listaNueva);
        }
    }

}
