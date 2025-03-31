import os
from plantuml import PlantUML

# Definir el contenido del diagrama UML en formato PlantUML
uml_content = """
@startuml Pet Health System

' Definición de clases principales
abstract class Mascota {
  -nombre: String
  -edad: int
  -altura: float
  -peso: float
  +getNombre(): String
  +getEdad(): int
  +getAltura(): float
  +getPeso(): float
  +setNombre(nombre: String): void
  +setEdad(edad: int): void
  +setAltura(altura: float): void
  +setPeso(peso: float): void
  +getTratamientos(): List<Tratamiento>
}

class Perro extends Mascota {
  -raza: String
  +getRaza(): String
  +setRaza(raza: String): void
}

class Gato extends Mascota {
  -raza: String
  +getRaza(): String
  +setRaza(raza: String): void
}

class Loro extends Mascota {
  -especie: String
  +getEspecie(): String
  +setEspecie(especie: String): void
}

class Dueno {
  -id: int
  -nombre: String
  -apellido: String
  -telefono: String
  -direccion: String
  -email: String
  +getId(): int
  +getNombre(): String
  +getApellido(): String
  +getTelefono(): String
  +getDireccion(): String
  +getEmail(): String
  +agregarMascota(mascota: Mascota): void
  +obtenerPerros(): List<Perro>
  +obtenerHistorialTratamientos(): List<Tratamiento>
}

class Doctor {
  -id: int
  -nombre: String
  -apellido: String
  -especialidad: String
  -telefono: String
  -email: String
  +getId(): int
  +getNombre(): String
  +getApellido(): String
  +getEspecialidad(): String
  +getTelefono(): String
  +getEmail(): String
  +agregarTratamiento(tratamiento: Tratamiento): void
  +getMascotasAtendidas(): List<Mascota>
  +getTratamientosRealizados(): List<Tratamiento>
}

abstract class Tratamiento {
  -id: int
  -fecha: Date
  -costo: float
  -descripcion: String
  +getId(): int
  +getFecha(): Date
  +getCosto(): float
  +getDescripcion(): String
  +setFecha(fecha: Date): void
  +setCosto(costo: float): void
  +setDescripcion(descripcion: String): void
  +agregarDoctor(doctor: Doctor): void
}

class Vacunacion extends Tratamiento {
  -tipoVacuna: String
  +getTipoVacuna(): String
  +setTipoVacuna(tipoVacuna: String): void
}

class Desparasitacion extends Tratamiento {
  -tipoDesparasitante: String
  +getTipoDesparasitante(): String
  +setTipoDesparasitante(tipoDesparasitante: String): void
}

class Cirugia extends Tratamiento {
  -tipoCirugia: String
  -duracion: int
  +getTipoCirugia(): String
  +getDuracion(): int
  +setTipoCirugia(tipoCirugia: String): void
  +setDuracion(duracion: int): void
}

class ChequeoGeneral extends Tratamiento {
  -resultados: String
  +getResultados(): String
  +setResultados(resultados: String): void
}

class DoctorTratamiento {
  -rol: String
  +getRol(): String
  +setRol(rol: String): void
}

' Relaciones entre clases
Mascota <|-- Perro
Mascota <|-- Gato
Mascota <|-- Loro

Dueno "1" -- "0..*" Mascota : posee >
Mascota "1" -- "0..*" Tratamiento : recibe >

Tratamiento <|-- Vacunacion
Tratamiento <|-- Desparasitacion
Tratamiento <|-- Cirugia
Tratamiento <|-- ChequeoGeneral

Tratamiento "0..*" -- "1" Mascota : pertenece a
Doctor "1..*" -- "0..*" Tratamiento
(Doctor, Tratamiento) .. DoctorTratamiento : Relación muchos a muchos

@enduml
"""

# Guardar el contenido UML en un archivo
with open("pet_health.puml", "w") as file:
    file.write(uml_content)

print("Diagrama UML generado en formato PlantUML.")

# Para visualizar el diagrama, normalmente se usaría:
plantuml = PlantUML(url='http://www.plantuml.com/plantuml/img/')
plantuml.processes_file('pet_health.puml')