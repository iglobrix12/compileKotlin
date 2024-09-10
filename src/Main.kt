fun main() {
    val script = "8 + 2"
    val lexer = Lexer(script)
    val parser = Parser(lexer)
    val result = parser.parse()
    println("$result")
}
