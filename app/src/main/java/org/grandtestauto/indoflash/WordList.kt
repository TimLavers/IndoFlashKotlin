package org.grandtestauto.indoflash

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.Reader
import java.io.Writer
import java.util.*

/**
 * List of words for the user to learn.
 *
 * @author Tim Lavers
 */
internal class WordList(wordss: List<Word>) {
    private val words = wordss.toMutableList()

    fun words(): List<Word> {
        return Collections.unmodifiableList(words)
    }

    fun add(word: Word) {
        if (words.contains(word)) return
        words.add(word)
    }

    fun remove(word: Word) {
        words.remove(word)
    }

    override fun toString(): String {
        return "WordList" + words
    }

    fun storeIn(writer: Writer) {
        val bw = BufferedWriter(writer)
        for (word in words) {
            try {
                bw.append(word.word)
                bw.append("=")
                bw.append(word.definition)
                bw.newLine()
            } catch (e: Throwable) {

            }
        }
        try {
            bw.close()
        } catch (e: Throwable) {
        }
    }

    companion object {
        fun readFromStream(reader: Reader): WordList {
            val words = LinkedList<Word>()
            val bufferedReader = BufferedReader(reader)
            var nextLine: String? = bufferedReader.readLine()
            while (nextLine != null) {
                if (nextLine.contains("=")) {
                    val pair = nextLine.split("=".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                    if (pair.size != 2) continue
                    words.add(Word(pair[0].trim({ it <= ' ' }), pair[1].trim({ it <= ' ' })))
                }
                nextLine = bufferedReader.readLine()
            }
            return WordList(words)
        }
    }
}