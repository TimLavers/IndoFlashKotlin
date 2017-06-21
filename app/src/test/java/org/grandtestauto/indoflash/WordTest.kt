package org.grandtestauto.indoflash

import org.junit.Assert
import org.junit.Test

class WordTest {

    @Test
    fun testWord() {
        val word = Word("satu", "one")
        Assert.assertEquals("satu", word.word)
    }

    @Test
    fun testDefinition() {
        val word = Word("satu", "one")
        Assert.assertEquals("one", word.definition)
    }
}