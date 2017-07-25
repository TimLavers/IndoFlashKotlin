package org.grandtestauto.indoflash.word

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.Reader
import java.io.Writer

fun readFromStream(reader: Reader): WordList {
    val words = mutableListOf<Word>()
    val bufferedReader = BufferedReader(reader)
    var nextLine: String? = bufferedReader.readLine()
    while (nextLine != null) {
        if (nextLine.contains("=")) {
            val pair = nextLine.split("=")
            if (pair.size != 2) continue
            words.add(Word(pair[0].trim(), pair[1].trim()))
        }
        nextLine = bufferedReader.readLine()
    }
    return WordList(words)
}

/**
 * List of words for the user to learn.
 *
 * @author Tim Lavers
 */
class WordList(words: List<Word>) {
    private val words = words.toMutableList()

    fun words(): List<Word> = words.toList()

    fun add(word: Word) {
        if (words.contains(word)) return
        words.add(word)
    }

    fun remove(word: Word) {
        words.remove(word)
    }

    override fun toString(): String = "WordList" + words

    fun store(writer: Writer) {
        val bw = BufferedWriter(writer)
        words.forEach { bw.append("${it.word}=${it.definition}\n") }
        bw.close()
    }
}