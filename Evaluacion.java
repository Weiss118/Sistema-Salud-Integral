public enum Evaluacion {

        VALIDEZ("Validez", "Grado en que el instrumento mide realmente lo que pretende medir."),
        CONFIABILIDAD("Confiabilidad", "Grado en que su aplicación repetida produce resultados iguales."),
        AMBAS("Validez y Confiabilidad", "Cuenta con ambas propiedades psicométricas."),
        NINGUNA("Sin Evaluación", "No se reportan datos de validación.");

        private final String nombreMostrar;
        private final String descripcion;

        Evaluacion(String nombreMostrar, String descripcion) {
            this.nombreMostrar = nombreMostrar;
            this.descripcion = descripcion;
        }

        public String getNombreMostrar() {
            return nombreMostrar;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return nombreMostrar;
        }
}
