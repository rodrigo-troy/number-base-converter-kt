package converter

import kotlin.math.pow

// Do not delete this line

fun main() {
    while (true) {
        println("Do you want to convert /from decimal or /to decimal?  (To quit type /exit)")
        when (readln()) {
            "/from" -> fromDecimal()
            "/to" -> toDecimal()
            "/exit" -> return
            else -> println("Unknown option")
        }
    }
}

fun fromDecimal() {
    println("Enter number in decimal system:")
    var dec = readln().toInt()
    println("Enter target base:")
    val target = readln().toInt()

    var result = ""
    while (dec > 0) {
        val remain = dec % target
        result = if (remain >= 10) 'A' + remain - 10 + result else remain.toString() + result
        dec /= target
    }

    println("Conversion result: $result")
}

fun toDecimal() {
    println("Enter source number:")
    val source = readln().reversed().uppercase()
    println("Enter source base:")
    val base = readln().toDouble()

    val result = source.indices.sumOf {
        (if (source[it] >= 'A') source[it] - 'A' + 10 else source[it].digitToInt()) * base.pow(
            it
        ).toInt()
    }

    println("Conversion to decimal result: $result")
}


