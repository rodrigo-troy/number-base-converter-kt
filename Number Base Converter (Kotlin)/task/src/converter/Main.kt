package converter // Do not delete this line

fun main() {
    println("Enter number in decimal system:")
    val number = readln().toInt()
    println("Enter target base:")
    val base = readln().toInt()
    println("Conversion result: ${convert(number, base)}")
}

fun convert(number: Int, base: Int): String {
    var result = ""
    var num = number

    while (num > 0) {
        result = (num % base).toString() + result
        num /= base
    }

    return result
}

