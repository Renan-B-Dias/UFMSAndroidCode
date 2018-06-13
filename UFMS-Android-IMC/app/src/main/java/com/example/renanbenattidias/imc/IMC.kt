package com.example.renanbenattidias.imc

/**
 * Created by renanbenattidias on 07/03/18.
 */
class IMC(height: Double, weight: Double) {

    val imc = weight / (height * height)
    val imcString = imc.toString()

}