class Lexer(private val input: String) {
    private var position: Int = 0
    private var currentChar: Char? = input.getOrNull(position)

    fun getNextToken(): Token {
        while (currentChar != null) {
            when {
                currentChar!!.isWhitespace() -> {
                    skipWhitespace()
                    continue
                }
                currentChar!!.isDigit() -> return Token(TokenType.NUMBER, consumeNumber())
                currentChar == '+' -> {
                    advance()
                    return Token(TokenType.PLUS, "+")
                }
                currentChar == '-' -> {
                    advance()
                    return Token(TokenType.MINUS, "-")
                }
                currentChar == '*' -> {
                    advance()
                    return Token(TokenType.MULTIPLY, "*")
                }
                currentChar == '/' -> {
                    advance()
                    return Token(TokenType.DIVIDE, "/")
                }
            }
        }
        return Token(TokenType.EOF, "")
    }

    private fun advance() {
        position++
        currentChar = input.getOrNull(position)
    }

    private fun skipWhitespace() {
        while (currentChar != null && currentChar!!.isWhitespace()) {
            advance()
        }
    }

    private fun consumeNumber(): String {
        val result = StringBuilder()
        while (currentChar != null && currentChar!!.isDigit()) {
            result.append(currentChar)
            advance()
        }
        return result.toString()
    }
}
