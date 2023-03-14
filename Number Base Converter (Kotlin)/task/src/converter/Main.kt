package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import kotlin.math.pow

// Do not delete this line

fun main() {
    var askAgain = true
    var input: String
    var origenBase: BigInteger = 0.toBigInteger()
    var targetBase: BigInteger = 0.toBigInteger()

    while (true) {
        if (askAgain) {
            println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
            input = readln()

            if (input == "/exit") {
                break
            } else if (input == "/back") {
                askAgain = true
                continue
            }

            val bases = input.split(" ")
            origenBase = bases[0].toBigInteger()
            targetBase = bases[1].toBigInteger()
            askAgain = false
        }

        println("Enter number in base $origenBase to convert to base $targetBase (To go back type /back)")
        input = readln()

        if (input == "/exit") {
            break
        } else if (input == "/back") {
            askAgain = true
            continue
        }

        if (input.contains(".")) {
            println("Conversion result: ${convertBigDecimal(input, origenBase.toInt(), targetBase.toInt())}")
        } else {
            println("Conversion result: ${convertBase(origenBase, targetBase, input)}")
        }
    }
}

fun convertBigDecimal(number: String, originBase: Int, targetBase: Int, decimalPlaces: Int = 5): String {
    val (integer, decimal) = number.split(".")
    val integerPart = convertBase(originBase.toBigInteger(), targetBase.toBigInteger(), integer)
    val decimalPart = convertFraction(decimal, originBase, targetBase).take(decimalPlaces)
    return "$integerPart.$decimalPart"
}

fun convertBase(sourceBase: BigInteger, targetBase: BigInteger, source: String): String {
    val toDecimal = source.uppercase().reversed().foldIndexed(BigInteger.ZERO) { index, acc, char ->
        val value = if (char >= 'A') char - 'A' + 10 else char.toString().toInt()
        acc + (value.toBigInteger() * sourceBase.pow(index))
    }.toString()

    var result = toDecimal.toBigInteger()
    val stringBuilder = StringBuilder()

    while (result > BigInteger.ZERO) {
        val remainder = (result % targetBase).toInt()
        stringBuilder.append(if (remainder >= 10) 'A' + remainder - 10 else remainder)
        result /= targetBase
    }

    return if (stringBuilder.isEmpty()) "0" else stringBuilder.toString().reversed()
}

fun convertFraction(fractional: String, source: Int, target: Int): String {
    var power = -1.0
    var fractionalDecimal = BigDecimal("0")

    fractional.subSequence(0, 5).forEach { x ->
        if (x.isLetter()) {
            val r = (10 + (x.uppercaseChar() - 'A')).toString()
            fractionalDecimal += BigDecimal(r) * source.toDouble().pow(power).toBigDecimal()
            power--
        } else {
            fractionalDecimal += BigDecimal(x.toString()) * source.toDouble().pow(power).toBigDecimal()
            power--
        }
    }

    fractionalDecimal = fractionalDecimal.setScale(5, RoundingMode.CEILING)
    var product: BigDecimal
    var s = ""
    var m: String
    var n = BigDecimal("1")
    var counter = 0

    while (s.length != 5 && n != BigDecimal.ZERO) {
        product = fractionalDecimal * BigDecimal(target.toString())
        m = product.toString().split(".")[0]
        n = BigDecimal(product.toString().split(".")[1])

        if (m.toInt() > 9) {
            m = ('A' + (m.toInt() - 10)).toString()
        }

        s += m
        fractionalDecimal = BigDecimal("0." + product.toString().split(".")[1])
        counter += 1
    }

    return if (s.length < 5) {
        s + "0".repeat(5 - n.toString().length)
    } else {
        s
    }
}
