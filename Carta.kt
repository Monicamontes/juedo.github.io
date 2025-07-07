
package com.tuinventor.juegomemoria

data class Carta(
    val id: Int,
    val imagenId: Int,
    var descubierta: Boolean = false,
    var emparejada: Boolean = false
)
