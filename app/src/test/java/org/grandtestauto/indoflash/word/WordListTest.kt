package org.grandtestauto.indoflash.word

import org.junit.Assert
import org.junit.Test
import java.io.StringReader
import java.io.StringWriter
import java.util.*

class WordListTest {

    @Test
    fun words() {
        val list = WordList(emptyList<Word>())
        Assert.assertEquals(0, list.words().size.toLong())
        Assert.assertEquals(3, wl123().words().size.toLong())
        Assert.assertTrue(wl123().words().contains(Word("satu", "one")))
        Assert.assertTrue(wl123().words().contains(Word("dua", "two")))
        Assert.assertTrue(wl123().words().contains(Word("tiga", "three")))
    }

    @Test
    fun wordsReturnsCopies() {
        val list = wl123()
        Assert.assertNotSame(list.words(), list.words())
    }

    @Test
    fun addWordToEmpty() {
        val list = WordList(emptyList<Word>())
        Assert.assertEquals(0, list.words().size.toLong())
        list.add(Word("empat", "four"))
        Assert.assertEquals(1, list.words().size.toLong())
        Assert.assertTrue(list.words().contains(Word("empat", "four")))
    }

    @Test
    fun testAddWord() {
        val list = wl123()
        list.add(Word("empat", "four"))
        Assert.assertEquals(4, list.words().size.toLong())
        Assert.assertTrue(list.words().contains(Word("empat", "four")))
    }

    @Test
    fun removeWordFromEmpty() {
        val list = WordList(emptyList<Word>())
        Assert.assertEquals(0, list.words().size.toLong())
        list.remove(Word("empat", "four"))
        Assert.assertEquals(0, list.words().size.toLong())
    }

    @Test
    fun testRemoveWord() {
        val list = wl123()
        list.remove(Word("dua", "two"))
        Assert.assertEquals(2, list.words().size.toLong())
        Assert.assertFalse(list.words().contains(Word("dua", "two")))
    }

    @Test
    fun readFromStreamTest() {
        val reader = StringReader("satu=one\ndua=two\n\ntiga=three")
        Assert.assertEquals(readFromStream(reader).words(), wl123().words())
    }

    @Test
    fun store() {
        val writer = StringWriter()
        wl123().store(writer)
        val restored = readFromStream(StringReader(writer.toString()))
        Assert.assertEquals(restored.words(), wl123().words())
    }

    private fun wl123(): WordList {
        val w123 = LinkedList<Word>()
        w123.add(Word("satu", "one"))
        w123.add(Word("dua", "two"))
        w123.add(Word("tiga", "three"))
        return WordList(w123)
    }
}