@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import jdk.nashorn.internal.runtime.JSType.toDouble
import kotlin.math.*

fun main(args: Array<String>) {
    print(hasDifferentDigits(323))
}

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var i = 1
    var n = n
    while (n / 10 > 0) {
        i += 1
        n /= 10
    }
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result = 1
    if (n <= 2) return result
    else return fib(n - 2) + fib(n - 1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    for (i in 1..(m * n)) {
        if (i % n == 0 && i % m == 0)
            return i
    }
    return m * n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 1) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..m * n) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {

    val msqrt = sqrt(toDouble(m)).toInt()
    val nsqrt = sqrt(toDouble(n)).toInt()
    for (i in msqrt..nsqrt) {
        //if (i * i <= n && i * i >= m) return true
        if (i * i in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x = x
    var i = 0
    while (x > 1) {
        if (x % 2 == 0) x /= 2
        else x = x * 3 +1
        i += 1
    }
    return i
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var xleft = x
    var xright = Math.pow(-1.0, 1.0) / factorial(3) * Math.pow(x, 3.0)
    var i = 3
    var sinx = xleft + xright
    while (abs(xright) > eps) {
        xleft += xright
        xright = Math.pow((-1).toDouble(), (i - 1).toDouble()) * Math.pow(x, (2 * i - 1).toDouble()) / factorial(2 * i - 1)
        i += 1
        sinx = xleft + xright
    }
    return sinx
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var xleft = 1.0
    var xright = Math.pow(-1.0, 1.0) / factorial(2) * Math.pow(x, 2.0)
    var i = 2
    var cosx = xleft + xright
    while (abs(xright) > eps) {
        xleft += xright
        xright = Math.pow((-1).toDouble(), (i).toDouble()) * Math.pow(x, (2 * i).toDouble()) / factorial(2 * i)
        i += 1
        cosx = xleft + xright
    }
    return cosx
}
/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    if (n < 9) return n
    else {
        var revertn = n % 10
        var n = n / 10

        while (n > 9) {
            revertn = revertn * 10 + n % 10
            n /= 10
        }
        revertn = revertn * 10 + n
        return revertn
    }
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var k = 0
    var n1 = n
    while (n1 > 9) {
        k += 1
        n1 = n1 /10
    }
    k += 1

    var leftI = (n / Math.pow(10.0, k - 1.0)).toInt()
    var rightI = (n % 10.0).toInt()
    var i = 1
    var fl = false
    if (leftI == rightI) {
        fl = true
    }
    while (fl && (abs(i - (k / 2 + 1)) > 1)) {
        fl = false
        i += 1
        leftI = (n / Math.pow(10.0, (k - i).toDouble()) % Math.pow(10.0, (1).toDouble())).toInt()
        rightI = (n % Math.pow(10.0, (i).toDouble()) / Math.pow(10.0, (i - 1).toDouble())).toInt()
        if (leftI == rightI) {
            fl = true
        }

    }
    return fl
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n = n
    if (n < 10) return false
    else {
        var i = n % 10
        n /= 10
        var newI = n % 10
        while (n > 0) {
            if (newI == i) {
                n /= 10
                newI = n % 10
            } else return true
        }
        return false
    }
    //return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 1 4 9 16 25 36 49 64 81 100 121 144 ...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    val a = listOf(1, 4, 9, 1, 6, 2, 5, 3, 6, 4, 9, 6, 4, 8, 1, 1, 0, 0, 1, 2, 1, 1, 4 ,4, 1, 6, 9, 1, 9, 6)
    return a[n - 1]
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1 1 2 3 5 8 13 21 34 55 89 144 ...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    val a = listOf(1, 1, 2, 3, 5, 8, 1, 3, 2, 1, 3, 4, 5, 5, 8, 9, 1, 4, 4, 2, 3, 3)
    return a[n - 1]
}
