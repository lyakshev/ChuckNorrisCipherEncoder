package chucknorris

fun main() {
    var exit = false

    while (!exit) {
        println("Please input operation (encode/decode/exit):")
        when (val operation = readln()) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                exit = true
                print("Bye!")
            }
            else -> println("There is no '$operation' operation\n")
        }
    }


}

fun decode() {
    println("Input encoded string:")
    val input = readln()

    var result = ""
    var listInput = input.split(" ")
    var otherCharacters = false
    for (i in input) {
        if (i != ' ' && i != '0') otherCharacters = true
    }

    when {
        (listInput.lastIndex +1) % 2 != 0 -> println("Encoded string is not valid.")
        listInput[0] != "0" && listInput[0] != "00" -> println("Encoded string is not valid.")
        otherCharacters -> println("Encoded string is not valid.")
        else -> {
            for (i in listInput.indices step 2) {
                if (listInput[i] =="0") {
                    repeat(listInput[i+1].length) {
                        result += "1"
                    }
                }
                if (listInput[i] =="00") {
                    repeat(listInput[i+1].length) {
                        result += "0"
                    }
                }
            }

            if (result.length % 7 == 0) {
                println("Decoded string:")
                for (i in result.chunked(7)) print(i.toInt(2).toChar())
                println()
                println()
            } else {
                println("Encoded string is not valid.")
            }

        }

    }




}

fun encode() {
    var fullInput = ""
    var encodedString = ""
    println("Input string:")
    val encodedInput = readln()
    println("Encoded string:")

        for (i in encodedInput) {
        val simvol = String.format("%07d", Integer.toBinaryString(i.code).toInt())
        fullInput += simvol
    }

    for (j in fullInput.indices) {

        if (j == 0) {
            when {
                fullInput[j] == '1' -> encodedString += "0 0"
                fullInput[j] == '0' -> encodedString += "00 0"
            }
        } else {
                when {
                    fullInput[j] == '1' && fullInput[j-1] == '1' -> encodedString += "0"
                    fullInput[j] == '1' && fullInput[j-1] == '0' -> encodedString += " 0 0"

                    fullInput[j] == '0' && fullInput[j-1] == '1' -> encodedString += " 00 0"
                    fullInput[j] == '0' && fullInput[j-1] == '0' -> encodedString += "0"
                }
        }
    }

    println(encodedString)
    println()

}