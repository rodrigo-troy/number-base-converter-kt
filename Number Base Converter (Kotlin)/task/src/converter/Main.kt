package converter

import java.math.BigInteger

// Do not delete this line

fun main() {
    var askAgain = true
    var input: String
    var source: BigInteger = 0.toBigInteger()
    var base: BigInteger = 0.toBigInteger()

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
            source = bases[0].toBigInteger()
            base = bases[1].toBigInteger()
            askAgain = false
        }

        println("Enter number in base $source to convert to base $base (To go back type /back)")
        input = readln()

        if (input == "/exit") {
            break
        } else if (input == "/back") {
            askAgain = true
            continue
        }

        println("Conversion result: ${convertBase(source, base, input)}")
    }
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
