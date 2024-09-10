class Parser(private val lexer: Lexer) {
    private var currentToken: Token = lexer.getNextToken()

    fun parse(): Int {
        return expression()
    }

    private fun expression(): Int {
        var result = term()

        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {
            val op = currentToken
            eat(op.type)
            if (op.type == TokenType.PLUS) {
                result += term()
            } else if (op.type == TokenType.MINUS) {
                result -= term()
            }
        }
        return result
    }

    private fun term(): Int {
        var result = factor()

        while (currentToken.type == TokenType.MULTIPLY || currentToken.type == TokenType.DIVIDE) {
            val op = currentToken
            eat(op.type)
            if (op.type == TokenType.MULTIPLY) {
                result *= factor()
            } else if (op.type == TokenType.DIVIDE) {
                result /= factor()
            }
        }
        return result
    }

    private fun factor(): Int {
        val token = currentToken
        eat(TokenType.NUMBER)
        return token.value.toInt()
    }

    private fun eat(tokenType: TokenType) {
        if (currentToken.type == tokenType) {
            currentToken = lexer.getNextToken()
        }
    }
}
