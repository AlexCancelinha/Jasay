val to_19 = listOf( "zero",  "one",   "two",  "three", "four",   "five",   "six",
    "seven", "eight", "nine", "ten",   "eleven", "twelve", "thirteen",
    "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" )
val tens  = listOf( "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
val denom = listOf( "",
    "thousand",     "million",         "billion",       "trillion",       "quadrillion",
    "quintillion",  "sextillion",      "septillion",    "octillion",      "nonillion",
    "decillion",    "undecillion",     "duodecillion",  "tredecillion",   "quattuordecillion",
    "sexdecillion", "septendecillion", "octodecillion", "novemdecillion", "vigintillion" )

val text = "12233.5"


fun main(argv: Array<String>) {
    //val number1 = Integer.parseInt(text) != null
    val number = english_number(133)
    println(number)
}

// convert a value < 100 to English.
@Throws(Exception::class)
private fun convert_nn(`val`: Int): String {
    if (`val` < 20) return to_19[`val`]
    for (v in tens.indices) {
        val dcap = tens[v]
        val dval = 20 + 10 * v
        if (dval + 10 > `val`) {
            return if (`val` % 10 != 0) dcap + "-" + to_19[`val` % 10] else dcap
        }
    }
    throw Exception("Should never get here, less than 100 failure")
}
// convert a value < 1000 to english, special cased because it is the level that kicks
// off the < 100 special case.  The rest are more general.  This also allows you to
// get strings in the form of "forty-five hundred" if called directly.
@Throws(Exception::class)
private fun convert_nnn(`val`: Int): String {
    var word = ""
    val rem = `val` / 100
    val mod = `val` % 100
    if (rem > 0) {
        word = to_19[rem] + " hundred"
        if (mod > 0) {
            word = "$word "
        }
    }
    if (mod > 0) {
        word = word + convert_nn(mod)
    }
    return word
}

@Throws(Exception::class)
fun english_number(`val`: Int): String {
    if (`val` < 100) {
        return convert_nn(`val`)
    }
    if (`val` < 1000) {
        return convert_nnn(`val`)
    }
    for (v in 0 until denom.size) {
        val didx = v - 1
        val dval = Math.pow(1000.0, v.toDouble()).toInt()
        if (dval > `val`) {
            val mod = Math.pow(1000.0, didx.toDouble()).toInt()
            val l = `val` / mod
            val r = `val` - l * mod
            var ret = convert_nnn(l) + " " + denom[didx]
            if (r > 0) {
                ret = ret + ", " + english_number(r)
            }
            return ret
        }
    }
    throw Exception("Should never get here, bottomed out in english_number")
}