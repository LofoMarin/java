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
}

class Perro {
  -raza: String
  +getRaza(): String
  +setRaza(raza: String): void
}

class Gato {
  -raza: String
  +getRaza(): String
  +setRaza(raza: String): void
}

class Loro {
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
  +setNombre(nombre: String): void
  +setApellido(apellido: String): void
  +setTelefono(telefono: String): void
  +setDireccion(direccion: String): void
  +setEmail(email: String): void
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
  +setNombre(nombre: String): void
  +setApellido(apellido: String): void
  +setEspecialidad(especialidad: String): void
  +setTelefono(telefono: String): void
  +setEmail(email: String): void
  +obtenerMascotasAtendidas(): List<Mascota>
  +obtenerTratamientosRealizados(): List<Tratamiento>
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
}

class Vacunacion {
  -tipoVacuna: String
  +getTipoVacuna(): String
  +setTipoVacuna(tipoVacuna: String): void
}

class Desparasitacion {
  -tipoDesparasitante: String
  +getTipoDesparasitante(): String
  +setTipoDesparasitante(tipoDesparasitante: String): void
}

class Cirugia {
  -tipoCirugia: String
  -duracion: int
  +getTipoCirugia(): String
  +getDuracion(): int
  +setTipoCirugia(tipoCirugia: String): void
  +setDuracion(duracion: int): void
}

class ChequeoGeneral {
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

Tratamiento <|-- Vacunacion
Tratamiento <|-- Desparasitacion
Tratamiento <|-- Cirugia
Tratamiento <|-- ChequeoGeneral

Mascota "1" -- "0..*" Tratamiento : recibe >

Doctor "1..*" -- "0..*" Tratamiento
(Doctor, Tratamiento) .. DoctorTratamiento

@enduml
"""

# Guardar el contenido UML en un archivo
with open("pet_health.puml", "w") as file:
    file.write(uml_content)

print("Diagrama UML generado en formato PlantUML.")
print("\nExplicación del diagrama:")
print("1. Clases principales:")
print("   - Mascota (abstracta): Clase base para todos los tipos de mascotas")
print("   - Perro, Gato, Loro: Subclases específicas de Mascota")
print("   - Dueno: Representa al propietario de las mascotas")
print("   - Doctor: Representa a los veterinarios")
print("   - Tratamiento (abstracta): Clase base para todos los tipos de tratamientos")
print("   - Vacunacion, Desparasitacion, Cirugia, ChequeoGeneral: Subclases específicas de Tratamiento")
print("   - DoctorTratamiento: Clase de asociación para la relación muchos a muchos entre Doctor y Tratamiento")
print("\n2. Relaciones:")
print("   - Un Dueño puede tener muchas Mascotas, pero una Mascota pertenece a un único Dueño")
print("   - Una Mascota puede recibir muchos Tratamientos")
print("   - Un Doctor puede realizar muchos Tratamientos y un Tratamiento puede involucrar a varios Doctores")
print("\n3. Funcionalidades implementadas:")
print("   - Dado un doctor, búsqueda de todas las mascotas que ha atendido: método obtenerMascotasAtendidas()")
print("   - Dado un doctor, búsqueda de todos los tratamientos realizados: método obtenerTratamientosRealizados()")
print("   - Dado un dueño, búsqueda de todos sus perros: método obtenerPerros()")
print("   - Dado un dueño, búsqueda del historial de tratamientos de todas sus mascotas: método obtenerHistorialTratamientos()")
print("   - Para buscar mascotas por tipo de tratamiento, se puede implementar una consulta a través de las relaciones existentes")

#Para visualizar el diagrama, normalmente se usaría:
plantuml = PlantUML(url='http://www.plantuml.com/plantuml/img/')
plantuml.processes_file('pet_health.puml')