package converter // Do not delete this line

fun main() {
    println("Enter number in decimal system:")
    val number = readln().toInt()
    println("Enter target base:")
    val base = readln().toInt()
    println("Conversion result: ${number.toString(base)}")
}
