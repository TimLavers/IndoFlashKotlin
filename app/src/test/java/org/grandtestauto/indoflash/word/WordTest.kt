package org.grandtestauto.indoflash.word

import org.junit.Assert
import org.junit.Test

class WordTest {

    @Test
    fun equalsTest() {
        val w1 = Word("satu", "one")
        val w2 = Word("satu", "1")
        val w3 = Word("Satu", "one")
        val w4 = Word("satu", "one")
        Assert.assertFalse(w1 == w2)
        Assert.assertFalse(w1 == w3)
        Assert.assertTrue(w1 == w4)
    }

    @Test
    fun hashCodeTest() {
        val w1 = Word("satu", "one")
        val w2 = Word("satu", "one")
        Assert.assertEquals(w1.hashCode().toLong(), w2.hashCode().toLong())
    }

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