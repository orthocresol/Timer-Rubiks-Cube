package org.timerrubikscube.nonactivityclass

import kotlin.random.Random

class ScrambleGenerator {
    private val moveList = arrayOf("U", "U'", "U2", "F", "F'", "F2", "D", "D'", "D2",
        "L", "L'", "L2", "R", "R'", "R2", "B", "B'", "B2")
    private val length = 20
    private var scramble = ""
    private var storeList = ArrayList<Int>()


    fun giveScramble(): String {
        var iteration = 0
        var prev = -3
        storeList.add(-9)
        storeList.add(-9)

        while(iteration < length){
            val random = getRandomNumber(prev, storeList[iteration])
            scramble += moveList[random]
            storeList.add(random)
            scramble += " "
            iteration++
            prev = random

            if(iteration == 10) scramble += "\n"
        }
        return scramble
    }
    private fun getRandomNumber(prev: Int, prevFromTwo: Int): Int {
        val number = Random.nextInt(18)
        return if((number.div(3) != prev.div(3)) && (number.div(3) != prevFromTwo.div(3))){
            number
        } else {
            getRandomNumber(prev, prevFromTwo)
        }
    }
}