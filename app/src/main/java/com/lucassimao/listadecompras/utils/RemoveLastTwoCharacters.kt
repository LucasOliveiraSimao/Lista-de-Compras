package com.lucassimao.listadecompras.utils

fun String.removeLastTwoCharacters(): String {
    return this.dropLast(2)
}