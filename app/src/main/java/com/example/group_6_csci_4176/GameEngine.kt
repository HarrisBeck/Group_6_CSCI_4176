package com.example.group_6_csci_4176

import kotlin.random.Random

// A data class for use in our application. I read through the documentation for this and it is really simple.
// https://kotlinlang.org/docs/data-classes.html#properties-declared-in-the-class-body
data class Token(var value: Int = 0, var used: Boolean = false)

object GameEngine {
    private lateinit var masterCode : Array<Token>

    private fun GameEngine(){}

    // Create the game by selecting 4 total different numbers from 1-8.
    fun CreateGame(){
        // Create an array of different tokens
        masterCode = Array(8){ Token(Random.nextInt(1, 8 + 1)) }

        println("Mastercode: ${masterCode[0]}\t${masterCode[1]}\t${masterCode[2]}\t${masterCode[3]}\t${masterCode[4]}\t${masterCode[5]}\t${masterCode[6]}\t${masterCode[7]}")
    }

    fun TestResult(guessedCode : IntArray) : IntArray {
        /*
        *   The results array is to be read as follows:
        *
        *   results[0] = # of correct colours in the correct location
        *   results[1] = # of correct colours in the incorrect position
        *   results[2] = # of incorrect colours
         */
        var results = IntArray(3)

        ResetMasterCode()

        // check for all the correct guesses in correct location. This must be done
        // first to avoid the issue of positions being used for other results.
        for(i in guessedCode.indices){
            if(guessedCode[i] == masterCode[i].value) {
                masterCode[i].used = true
                results[0] += 1
            }
        }

        // get the guesses.
        for(i in guessedCode.indices){
            if(HasAvailablePosition(guessedCode[i])) results[1] += 1
        }

        // the remaining guesses are incorrect
        results[2] = masterCode.size - (results[1] + results[0])

        return results
    }

    fun GetCodeLength() : Int{
        return masterCode.size
    }

    /*
        HELPER FUNCTIONS
     */

    // check
    private fun HasAvailablePosition(value: Int) : Boolean{
        for(i in masterCode.indices)
            if(masterCode[i].value == value && !masterCode[i].used){
                masterCode[i].used = true
                return true
            }

        return false
    }

    private fun ResetMasterCode(){
        for(i in masterCode.indices)
            masterCode[i].used = false
    }
}