package converter // Do not delete this line

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

fun toDecimal() {
    println("Enter source number:")
    val number = readln()
    println("Enter source base:")
    val base = readln().toInt()
    println("Conversion to decimal result: ${number.toInt(base)}")
}

fun fromDecimal() {
    println("Enter a number in decimal system:")
    val number = readln().toInt()
    println("Enter target base:")
    val base = readln().toInt()
    println("Conversion result: ${number.toString(base)}")
}
