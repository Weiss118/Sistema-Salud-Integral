import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlConsola {
    private Control control;
    private Scanner scanner;

    public ControlConsola() {
        control = new Control();
        scanner = new Scanner(System.in);
        try {
            control.cargarArchivo();
            System.out.println(">> Sistema iniciado. Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println(">> Advertencia: No se pudo cargar el archivo de datos (" + e.getMessage() + ")");
        }
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n=== SISTEMA APA: SALUD MENTAL ===");
            System.out.println("1. Registrar Instrumento");
            System.out.println("2. Consultas y Filtros");
            System.out.println("3. Listar TODO (Ordenado)");
            System.out.println("4. Eliminar por Clave");
            System.out.println("5. Guardar en Archivo");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            try {
                String entrada = scanner.nextLine();
                opcion = Integer.parseInt(entrada);
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Por favor ingrese un número válido.");
            }
        } while (opcion != 6);
    }

    private void ejecutarOpcion(int op) {
        switch (op) {
            case 1: registrar(); break;
            case 2: menuConsultas(); break;
            case 3: listarOrdenado(); break;
            case 4: eliminar(); break;
            case 5: guardarDatos(); break; // Método separado para manejo de errores
            case 6:
                guardarDatos();
                System.out.println("Finalizando aplicación...");
                break;
            default: System.out.println("Opción no válida.");
        }
    }

    private void guardarDatos() {
        try {
            control.guardarArchivo();
            System.out.println("¡Éxito! Archivo guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error Crítico: No se pudo guardar el archivo. " + e.getMessage());
        }
    }

    private void registrar() {
        System.out.println("\n--- Nuevo Instrumento ---");
        try {
            System.out.print("Clave (numérica): ");
            double clave = Double.parseDouble(scanner.nextLine());

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            Tipo tipo = (Tipo) elegirEnum(Tipo.values(), "Tipo");
            Condicion cond = (Condicion) elegirEnum(Condicion.values(), "Condición");
            Forma forma = (Forma) elegirEnum(Forma.values(), "Forma");

            System.out.print("Autor: ");
            String autor = scanner.nextLine();

            Evaluacion eva = (Evaluacion) elegirEnum(Evaluacion.values(), "Evaluación");

            System.out.print("Cita: ");
            String cita = scanner.nextLine();

            Instrumento nuevo = new Instrumento(clave, nombre, tipo, cond, forma, autor, eva, cita);

            boolean registrado = control.agregarInstrumento(nuevo);

            if (registrado) {
                System.out.println("Instrumento registrado con éxito.");
            } else {
                System.out.println("Error: Ya existe un instrumento con la clave " + clave);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Dato numérico inválido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private Object elegirEnum(Object[] valores, String titulo) {
        System.out.println("Seleccione " + titulo + ":");
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i+1) + ". " + valores[i]);
        }
        int indice = -1;
        while (indice < 0 || indice >= valores.length) {
            System.out.print("Opción: ");
            try {
                String s = scanner.nextLine();
                indice = Integer.parseInt(s) - 1;
            } catch(Exception e) { indice = -1; }
        }
        return valores[indice];
    }

    private void menuConsultas() {
        System.out.println("\n--- CONSULTAS ---");
        System.out.println("1. Por Autor");
        System.out.println("2. Por Tipo (Identificar/Manejar)");
        System.out.println("3. Por Forma (Test/Cuestionario...)");
        System.out.println("4. Por Condición (Estrés/Ansiedad)");
        System.out.println("5. Validados por Condición");
        System.out.print("Seleccione: ");

        try {
            int op = Integer.parseInt(scanner.nextLine());
            ArrayList<Instrumento> res = null;

            switch (op) {
                case 1:
                    System.out.print("Ingrese nombre del autor: ");
                    String autor = scanner.nextLine();
                    res = control.consultarPorAutor(autor);
                    break;
                case 2:
                    System.out.print("Ingrese tipo (identificar/manejar): ");
                    res = control.consultarPorTipo(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Ingrese forma (test/escala...): ");
                    res = control.consultarPorForma(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Ingrese condición (estrés/ansiedad): ");
                    res = control.consultarPorCondicion(scanner.nextLine());
                    break;
                case 5:
                    Condicion c = (Condicion) elegirEnum(Condicion.values(), "Condición");
                    res = control.consultarCondicionYValidez(c);
                    break;
                default:
                    System.out.println("Opción no válida dentro de consultas.");
            }

            mostrarLista(res);

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número para seleccionar la opción.");
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    private void listarOrdenado() {
        System.out.println("1. Ordenar por Clave\n2. Ordenar por Autor");
        try {
            int op = Integer.parseInt(scanner.nextLine());
            if (op == 1) control.ordenarPorClave();
            else control.ordenarPorAutor();
            mostrarLista(control.obtenerTodos());
        } catch (Exception e) { System.out.println("Error en opción"); }
    }

    private void eliminar() {
        System.out.print("Ingrese clave a eliminar: ");
        try {
            double c = Double.parseDouble(scanner.nextLine());
            if(control.eliminarInstrumento(c)) {
                System.out.println("Eliminado correctamente.");
            } else {
                System.out.println("No se encontró un instrumento con esa clave.");
            }
        } catch (Exception e) { System.out.println("Clave inválida"); }
    }

    private void mostrarLista(ArrayList<Instrumento> lista) {
        if(lista == null || lista.isEmpty()) {
            System.out.println("No hay resultados.");
        } else {
            System.out.println("Resultados encontrados (" + lista.size() + "):");
            for(Instrumento i : lista) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        new ControlConsola().iniciar();
    }
}
