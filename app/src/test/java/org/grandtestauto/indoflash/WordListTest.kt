package org.grandtestauto.indoflash

import org.junit.Assert
import org.junit.Test

import java.util.Collections
import java.util.LinkedList

class WordListTest {

    @Test
    fun testWords() {
        val list = WordList(emptyList<Word>())
        Assert.assertEquals(0, list.words().size.toLong())
        Assert.assertEquals(3, wl123().words().size.toLong())
        Assert.assertTrue(wl123().words().contains(Word("satu", "one")))
        Assert.assertTrue(wl123().words().contains(Word("dua", "two")))
        Assert.assertTrue(wl123().words().contains(Word("tiga", "three")))
    }

    @Test
    fun testAddWord() {
        var list = WordList(emptyList<Word>())
        Assert.assertEquals(0, list.words().size.toLong())
        list.add(Word("empat", "four"))
        Assert.assertEquals(1, list.words().size.toLong())
        Assert.assertTrue(list.words().contains(Word("empat", "four")))

        list = wl123()
        list.add(Word("empat", "four"))
        Assert.assertEquals(4, list.words().size.toLong())
        Assert.assertTrue(list.words().contains(Word("empat", "four")))
    }

    @Test
    fun testRemoveWord() {
        var list = WordList(emptyList<Word>())
        Assert.assertEquals(0, list.words().size.toLong())
        list.remove(Word("empat", "four"))
        Assert.assertEquals(0, list.words().size.toLong())

        list = wl123()
        list.remove(Word("dua", "two"))
        Assert.assertEquals(2, list.words().size.toLong())
        Assert.assertFalse(list.words().contains(Word("dua", "two")))
    }

    private fun wl123(): WordList {
        val w123 = LinkedList<Word>()
        w123.add(Word("satu", "one"))
        w123.add(Word("dua", "two"))
        w123.add(Word("tiga", "three"))
        return WordList(w123)
    }
}