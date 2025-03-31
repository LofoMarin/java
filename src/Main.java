import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    // Clase Dueño
    static class Dueño {
        private String nombre;
        private String direccion;
        private String telefono;
        private List<Mascota> mascotas;

        public Dueño(String nombre, String direccion, String telefono) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
            this.mascotas = new ArrayList<>();
        }

        public void agregarMascota(Mascota mascota) {
            mascotas.add(mascota);
        }

        public List<Mascota> getMascotas() {
            return mascotas;
        }

        // Método para obtener solo los perros del dueño
        public List<Perro> getPerros() {
            List<Perro> perros = new ArrayList<>();
            for (Mascota mascota : mascotas) {
                if (mascota instanceof Perro) {
                    perros.add((Perro) mascota);
                }
            }
            return perros;
        }

        // Método para obtener el historial de tratamientos de todas las mascotas
        public List<Tratamiento> getHistorialTratamientos() {
            List<Tratamiento> historial = new ArrayList<>();
            for (Mascota mascota : mascotas) {
                historial.addAll(mascota.getTratamientos());
            }
            return historial;
        }

        public String getNombre() {
            return nombre;
        }
    }

    // Clase abstracta Mascota
    static abstract class Mascota {
        private String nombre;
        private int edad;
        private double altura;
        private double peso;
        private Dueño dueño;
        private List<Tratamiento> tratamientos;

        public Mascota(String nombre, int edad, double altura, double peso, Dueño dueño) {
            this.nombre = nombre;
            this.edad = edad;
            this.altura = altura;
            this.peso = peso;
            this.dueño = dueño;
            this.tratamientos = new ArrayList<>();
            dueño.agregarMascota(this);
        }

        public void agregarTratamiento(Tratamiento tratamiento) {
            tratamientos.add(tratamiento);
        }

        public List<Tratamiento> getTratamientos() {
            return tratamientos;
        }

        public String getNombre() {
            return nombre;
        }

        public Dueño getDueño() {
            return dueño;
        }
    }

    // Subclase Perro
    static class Perro extends Mascota {
        private String raza;

        public Perro(String nombre, String raza, int edad, double altura, double peso, Dueño dueño) {
            super(nombre, edad, altura, peso, dueño);
            this.raza = raza;
        }

        public String getRaza() {
            return raza;
        }
    }

    // Subclase Gato
    static class Gato extends Mascota {
        private String raza;

        public Gato(String nombre, String raza, int edad, double altura, double peso, Dueño dueño) {
            super(nombre, edad, altura, peso, dueño);
            this.raza = raza;
        }

        public String getRaza() {
            return raza;
        }
    }

    // Subclase Loro
    static class Loro extends Mascota {
        private String especie;

        public Loro(String nombre, String especie, int edad, double altura, double peso, Dueño dueño) {
            super(nombre, edad, altura, peso, dueño);
            this.especie = especie;
        }

        public String getEspecie() {
            return especie;
        }
    }

    // Clase Doctor
    static class Doctor {
        private String nombre;
        private String especialidad;
        private String numeroLicencia;
        private List<Tratamiento> tratamientosRealizados;

        public Doctor(String nombre, String especialidad, String numeroLicencia) {
            this.nombre = nombre;
            this.especialidad = especialidad;
            this.numeroLicencia = numeroLicencia;
            this.tratamientosRealizados = new ArrayList<>();
        }

        public void agregarTratamiento(Tratamiento tratamiento) {
            tratamientosRealizados.add(tratamiento);
        }

        // Método para obtener todas las mascotas atendidas por el doctor
        public List<Mascota> getMascotasAtendidas() {
            Set<Mascota> mascotasSet = new HashSet<>();
            for (Tratamiento tratamiento : tratamientosRealizados) {
                mascotasSet.add(tratamiento.getMascota());
            }
            return new ArrayList<>(mascotasSet);
        }

        // Método para obtener todos los tratamientos realizados por el doctor
        public List<Tratamiento> getTratamientosRealizados() {
            return tratamientosRealizados;
        }

        public String getNombre() {
            return nombre;
        }
    }

    // Clase abstracta Tratamiento
    static abstract class Tratamiento {
        private Date fecha;
        private double costo;
        private List<Doctor> doctores;
        private Mascota mascota;

        public Tratamiento(Date fecha, double costo, Mascota mascota) {
            this.fecha = fecha;
            this.costo = costo;
            this.mascota = mascota;
            this.doctores = new ArrayList<>();
            mascota.agregarTratamiento(this);
        }

        public void agregarDoctor(Doctor doctor) {
            doctores.add(doctor);
            doctor.agregarTratamiento(this);
        }

        public abstract String getTipo();

        public Date getFecha() {
            return fecha;
        }

        public double getCosto() {
            return costo;
        }

        public Mascota getMascota() {
            return mascota;
        }

        public List<Doctor> getDoctores() {
            return doctores;
        }
    }

    // Subclase Vacunacion
    static class Vacunacion extends Tratamiento {
        private String tipoVacuna;

        public Vacunacion(Date fecha, double costo, Mascota mascota, String tipoVacuna) {
            super(fecha, costo, mascota);
            this.tipoVacuna = tipoVacuna;
        }

        @Override
        public String getTipo() {
            return "Vacunación";
        }

        public String getTipoVacuna() {
            return tipoVacuna;
        }
    }

    // Subclase Desparasitacion
    static class Desparasitacion extends Tratamiento {
        private String tipoDesparasitante;

        public Desparasitacion(Date fecha, double costo, Mascota mascota, String tipoDesparasitante) {
            super(fecha, costo, mascota);
            this.tipoDesparasitante = tipoDesparasitante;
        }

        @Override
        public String getTipo() {
            return "Desparasitación";
        }

        public String getTipoDesparasitante() {
            return tipoDesparasitante;
        }
    }

    // Subclase Cirugia
    static class Cirugia extends Tratamiento {
        private String tipoCirugia;
        private int duracion; // en minutos

        public Cirugia(Date fecha, double costo, Mascota mascota, String tipoCirugia, int duracion) {
            super(fecha, costo, mascota);
            this.tipoCirugia = tipoCirugia;
            this.duracion = duracion;
        }

        @Override
        public String getTipo() {
            return "Cirugía";
        }

        public String getTipoCirugia() {
            return tipoCirugia;
        }

        public int getDuracion() {
            return duracion;
        }
    }

    // Subclase ChequeoGeneral
    static class ChequeoGeneral extends Tratamiento {
        private String resultados;

        public ChequeoGeneral(Date fecha, double costo, Mascota mascota, String resultados) {
            super(fecha, costo, mascota);
            this.resultados = resultados;
        }

        @Override
        public String getTipo() {
            return "Chequeo General";
        }

        public String getResultados() {
            return resultados;
        }
    }

    // Método para buscar mascotas por tipo de tratamiento
    public static List<Mascota> getMascotasPorTipoTratamiento(List<Tratamiento> tratamientos, String tipo) {
        Set<Mascota> mascotasSet = new HashSet<>();
        for (Tratamiento tratamiento : tratamientos) {
            if (tratamiento.getTipo().equals(tipo)) {
                mascotasSet.add(tratamiento.getMascota());
            }
        }
        return new ArrayList<>(mascotasSet);
    }

    // Método para identificar al doctor que ha realizado la mayor cantidad de tratamientos 
    // de un tipo específico a gatos
    public static Doctor getDoctorConMasTratamientosAGatos(List<Doctor> doctores, String tipoTratamiento) {
        Doctor doctorConMasTratamientos = null;
        int maxTratamientos = 0;
        
        for (Doctor doctor : doctores) {
            int contador = 0;
            for (Tratamiento tratamiento : doctor.getTratamientosRealizados()) {
                // Verificar si el tratamiento es del tipo especificado y si la mascota es un gato
                if (tratamiento.getTipo().equals(tipoTratamiento) && tratamiento.getMascota() instanceof Gato) {
                    contador++;
                }
            }
            
            // Actualizar el doctor con más tratamientos si encontramos uno con mayor cantidad
            if (contador > maxTratamientos) {
                maxTratamientos = contador;
                doctorConMasTratamientos = doctor;
            }
        }
        
        return doctorConMasTratamientos;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            // Crear doctores
            Doctor drJuan = new Doctor("Dr. Juan Pérez", "Medicina General", "001");
            Doctor drAna = new Doctor("Dra. Ana García", "Cirugía", "002");
            Doctor drLuis = new Doctor("Dr. Luis Rodríguez", "Especialista en Aves", "003");

            // Crear dueños
            Dueño carlos = new Dueño("Carlos Martínez", "Calle 123", "555-1234");
            Dueño maria = new Dueño("María López", "Avenida 456", "555-5678");

            // Crear mascotas
            Perro rex = new Perro("Rex", "Labrador", 5, 0.7, 30.0, carlos);
            Perro max = new Perro("Max", "Pastor Alemán", 3, 0.8, 35.0, carlos);
            Gato michi = new Gato("Michi", "Siamés", 3, 0.4, 5.0, carlos);
            Gato luna = new Gato("Luna", "Persa", 2, 0.35, 4.5, maria);
            Gato felix = new Gato("Felix", "Bengala", 1, 0.3, 4.0, maria);
            Loro paco = new Loro("Paco", "Guacamayo", 10, 0.3, 1.2, maria);

            // Lista para almacenar todos los tratamientos
            List<Tratamiento> todosTratamientos = new ArrayList<>();

            // Crear tratamientos
            Vacunacion vacunaRex = new Vacunacion(sdf.parse("15/03/2023"), 50.0, rex, "Rabia");
            vacunaRex.agregarDoctor(drJuan);
            todosTratamientos.add(vacunaRex);

            Vacunacion vacunaMichi = new Vacunacion(sdf.parse("20/03/2023"), 45.0, michi, "Triple Felina");
            vacunaMichi.agregarDoctor(drJuan);
            todosTratamientos.add(vacunaMichi);

            Vacunacion vacunaLuna = new Vacunacion(sdf.parse("22/03/2023"), 45.0, luna, "Triple Felina");
            vacunaLuna.agregarDoctor(drJuan);
            todosTratamientos.add(vacunaLuna);

            Vacunacion vacunaFelix = new Vacunacion(sdf.parse("25/03/2023"), 45.0, felix, "Leucemia Felina");
            vacunaFelix.agregarDoctor(drAna);
            todosTratamientos.add(vacunaFelix);

            Desparasitacion desparasitacionRex = new Desparasitacion(sdf.parse("10/04/2023"), 30.0, rex, "Oral");
            desparasitacionRex.agregarDoctor(drJuan);
            todosTratamientos.add(desparasitacionRex);

            Desparasitacion desparasitacionMax = new Desparasitacion(sdf.parse("10/04/2023"), 30.0, max, "Oral");
            desparasitacionMax.agregarDoctor(drJuan);
            todosTratamientos.add(desparasitacionMax);

            Desparasitacion desparasitacionMichi = new Desparasitacion(sdf.parse("12/04/2023"), 25.0, michi, "Pipeta");
            desparasitacionMichi.agregarDoctor(drAna);
            todosTratamientos.add(desparasitacionMichi);

            Cirugia cirugiaLuna = new Cirugia(sdf.parse("05/05/2023"), 500.0, luna, "Esterilización", 120);
            cirugiaLuna.agregarDoctor(drAna);
            todosTratamientos.add(cirugiaLuna);

            ChequeoGeneral chequeoPaco = new ChequeoGeneral(sdf.parse("12/05/2023"), 80.0, paco, "Estado saludable, plumaje en buen estado");
            chequeoPaco.agregarDoctor(drLuis);
            todosTratamientos.add(chequeoPaco);

            // Crear una lista con todos los doctores
            List<Doctor> todosDoctores = new ArrayList<>();
            todosDoctores.add(drJuan);
            todosDoctores.add(drAna);
            todosDoctores.add(drLuis);

            // Demostrar las funcionalidades requeridas
            System.out.println("\n===== FUNCIONALIDADES DEL SISTEMA =====");

            // 1. Dado un doctor, búsqueda de todas las mascotas que ha atendido
            System.out.println("\n1. Mascotas atendidas por " + drJuan.getNombre() + ":");
            List<Mascota> mascotasAtendidas = drJuan.getMascotasAtendidas();
            for (Mascota mascota : mascotasAtendidas) {
                System.out.println("   - " + mascota.getNombre() + " (" + mascota.getClass().getSimpleName() + ")");
            }

            // 2. Dado un doctor, búsqueda de todos los tratamientos realizados
            System.out.println("\n2. Tratamientos realizados por " + drJuan.getNombre() + ":");
            List<Tratamiento> tratamientosRealizados = drJuan.getTratamientosRealizados();
            for (Tratamiento tratamiento : tratamientosRealizados) {
                System.out.println("   - " + tratamiento.getTipo() + " a " + tratamiento.getMascota().getNombre() + 
                                  " el " + sdf.format(tratamiento.getFecha()));
            }

            // 3. Dado un dueño, búsqueda de todos sus perros
            System.out.println("\n3. Perros de " + carlos.getNombre() + ":");
            List<Perro> perrosCarlos = carlos.getPerros();
            for (Perro perro : perrosCarlos) {
                System.out.println("   - " + perro.getNombre() + " (" + perro.getRaza() + ")");
            }

            // 4. Dado un dueño, búsqueda del historial de tratamientos de todas sus mascotas
            System.out.println("\n4. Historial de tratamientos de las mascotas de " + carlos.getNombre() + ":");
            List<Tratamiento> historialCarlos = carlos.getHistorialTratamientos();
            for (Tratamiento tratamiento : historialCarlos) {
                System.out.println("   - " + tratamiento.getMascota().getNombre() + ": " + 
                                  tratamiento.getTipo() + " el " + sdf.format(tratamiento.getFecha()));
            }

            // 5. Dado un tipo de tratamiento, búsqueda de todas las mascotas que lo han recibido
            String tipoTratamiento = "Desparasitación";
            System.out.println("\n5. Mascotas que han recibido " + tipoTratamiento + ":");
            List<Mascota> mascotasDesparasitadas = getMascotasPorTipoTratamiento(todosTratamientos, tipoTratamiento);
            for (Mascota mascota : mascotasDesparasitadas) {
                System.out.println("   - " + mascota.getNombre() + " (Dueño: " + mascota.getDueño().getNombre() + ")");
            }

            // 6. Identificar al doctor que ha realizado la mayor cantidad de tratamientos de un tipo específico a gatos
            String tipoTratamientoEspecifico = "Vacunación";
            System.out.println("\n6. Doctor que ha realizado más tratamientos de " + tipoTratamientoEspecifico + " a gatos:");
            Doctor doctorConMasTratamientos = getDoctorConMasTratamientosAGatos(todosDoctores, tipoTratamientoEspecifico);
            if (doctorConMasTratamientos != null) {
                System.out.println("   - " + doctorConMasTratamientos.getNombre());
                
                // Mostrar detalles de los tratamientos
                System.out.println("   - Detalles de los tratamientos:");
                for (Tratamiento tratamiento : doctorConMasTratamientos.getTratamientosRealizados()) {
                    if (tratamiento.getTipo().equals(tipoTratamientoEspecifico) && tratamiento.getMascota() instanceof Gato) {
                        Gato gato = (Gato) tratamiento.getMascota();
                        System.out.println("     * " + gato.getNombre() + " (" + gato.getRaza() + ") el " + 
                                          sdf.format(tratamiento.getFecha()));
                    }
                }
            } else {
                System.out.println("   - Ningún doctor ha realizado tratamientos de " + tipoTratamientoEspecifico + " a gatos.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}