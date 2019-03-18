@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {

}

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (i in v) {
        result += i * i
    }
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0.0
    if (list.size < 1) return result
    for (i in list) {
        result += i
    }
    return result / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val meanVal = mean(list)
    for (i in 0 until list.size) {
        list[i] -= meanVal
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    for (i in 0 until a.size) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var result = 0.0
    for (i in 0 until p.size) {
        result += p[i] * x.pow(i)
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.size == 1) return list
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var nn = n
    var i = 2
    if (n < 4) {
        result.add(n)
        return result
    }
    while (nn > 4) {
        if (nn % i == 0) {
            result.add(i)
            nn /= i
        } else i += 1
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val result = mutableListOf<Int>()
    var nn = n
    var i = 2
    if (n < 4) {
        result.add(n)
        return result.joinToString(separator = "*")
    }
    while (nn > 4) {
        if (nn % i == 0) {
            result.add(i)
            nn /= i
        } else i += 1
    }
    return result.joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var nn = n
    do {
        result.add(nn % base)
        nn /= base
    } while (nn != 0)
    return result.asReversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var war = 'a'
    var result = mutableListOf<Char>()
    var nn = n
    do {
        if (nn % base > 9) result.add(war + nn % base - 10)
        else result.add((nn % base).toChar() + 48)
        nn /= base
    } while (nn != 0)
    result = result.asReversed()
    return result.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for (i in 0 until digits.size) {
        result += digits[i] * Math.pow(base.toDouble(), (digits.size - i - 1).toDouble()).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    //result = 'a'.toInt()
    for (i in 0 until str.length) {
        if (str[i].toInt() >= 48 && str[i].toInt() <= 57)
            result += (str[i].toInt() - 48) * Math.pow(base.toDouble(), (str.length - i - 1).toDouble()).toInt()
        else
            result += (str[i].toInt() - 87) * Math.pow(base.toDouble(), (str.length - i - 1).toDouble()).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    var newN = n
    while (newN > 0) {
        if (newN / 1000 >= 1) {
            result += "M"
            newN -= 1000
        } else if (newN / 900 >= 1) {
            result += "CM"
            newN -= 900
        } else if (newN / 500 >= 1) {
            result += "D"
            newN -= 500
        } else if (newN / 400 >= 1) {
            result += "CD"
            newN -= 400
        } else if (newN / 100 >= 1) {
            result += "C"
            newN -= 100
        } else if (newN / 90 >= 1) {
            result += "XC"
            newN -= 90
        } else if (newN / 50 >= 1) {
            result += "L"
            newN -= 50
        } else if (newN / 40 >= 1) {
            result += "XL"
            newN -= 40
        } else if (newN / 10 >= 1) {
            result += "X"
            newN -= 10
        } else if (newN / 9 >= 1) {
            result += "IX"
            newN -= 9
        } else if (newN / 5 >= 1) {
            result += "V"
            newN -= 5
        } else if (newN / 4 >= 1) {
            result += "IV"
            newN -= 4
        } else if (newN > 0) {
            result += "I"
            newN -= 1
        }
    }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
/*  1 - тысяча
    2 3 4  - тысячи
    5 6 7 8 9 0- тысяч
 */
fun leftTripleNumber(n: Int): String {
    var result1 = when (n / 100) {
        1 -> "сто "
        2 -> "двести"
        3 -> "триста "
        4 -> "четыреста "
        5 -> "пятьсот "
        6 -> "шестьсот "
        7 -> "семьсот "
        8 -> "восемьсот "
        9 -> "девятьсот"
        else -> ""
    }
    var result23 = n % 100
    if (result23 in 11..19) {
        var result34 = when (n % 100) {
            11 -> "однадцать"
            12 -> "двенадцать"
            13 -> "тринадцать"
            14 -> "четырнадцать"
            15 -> "пятнадцать"
            16 -> "шестнадцать"
            17 -> "семнадцать"
            18 -> "восемнадцать"
            19 -> "девятнадцать"
            else -> ""
        }
        return result1 + result34
    } else {
        var result2 = when (n % 100 / 10) {
            1 -> "десят "
            2 -> "двадцать "
            3 -> "тридцать "
            4 -> "сорок "
            5 -> "пятьдесят "
            6 -> "шестьдесят "
            7 -> "семьдесят "
            8 -> "восемьдесят "
            9 -> "девяноста "
            else -> ""
        }
        var result3 = when (n % 10) {
            1 -> "одна"
            2 -> "две"
            3 -> "три"
            4 -> "четыре"
            5 -> "пять"
            6 -> "шесть"
            7 -> "семь"
            8 -> "восемь"
            9 -> "девять"
            else -> ""
        }
        return result1 + result2 + result3
    }
}

fun rightTripleNumber(n: Int): String {
    var result1 = when (n / 100) {
        1 -> "сто "
        2 -> "двести "
        3 -> "триста "
        4 -> "четыреста "
        5 -> "пятьсот "
        6 -> "шестьсот "
        7 -> "семьсот "
        8 -> "восемьсот "
        9 -> "девятьсот "
        else -> ""
    }
    var result23 = n % 100
    if (result23 in 11..19) {
        var result34 = when (n % 100) {
            11 -> "однадцать"
            12 -> "двенадцать"
            13 -> "тринадцать"
            14 -> "четырнадцать"
            15 -> "пятнадцать"
            16 -> "шестнадцать"
            17 -> "семнадцать"
            18 -> "восемнадцать"
            19 -> "девятнадцать"
            else -> ""
        }
        return result1 + result34
    } else {
        var result2 = when (n % 100 / 10) {
            1 -> "десят "
            2 -> "двадцать "
            3 -> "тридцать "
            4 -> "сорок "
            5 -> "пятьдесят "
            6 -> "шестьдесят "
            7 -> "семьдесят "
            8 -> "восемьдесят "
            9 -> "девяноста "
            else -> ""
        }
        var result3 = when (n % 10) {
            1 -> "один"
            2 -> "два"
            3 -> "три"
            4 -> "четыре"
            5 -> "пять"
            6 -> "шесть"
            7 -> "семь"
            8 -> "восемь"
            9 -> "девять"
            else -> ""
        }
        return result1 + result2 + result3
    }
}

fun russian(n: Int): String {
    var result = ""
    val left = n / 1000
    val right = n % 1000
    val leftS = leftTripleNumber(left)
    val rightS = rightTripleNumber(right)

    result = rightS
    if (left > 0) {
        if ((left % 100 < 19 && left % 100 > 10) || left % 10 in 5..9 || left % 10 == 0) result = leftS + " тысяч " + rightS
        else if (left % 10 == 1) result = leftS + " тысяча " + rightS
        else if (left % 10 in 2..4) result = leftS + " тысячи " + rightS
    }

    result.replace("  ", " ")
    return result.trim()

}