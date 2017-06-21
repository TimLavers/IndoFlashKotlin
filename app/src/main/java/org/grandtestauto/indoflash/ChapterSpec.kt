package org.grandtestauto.indoflash

import org.w3c.dom.Element

import java.util.LinkedList

/**
 * Sub-topic structure for the word lists that make up
 * a chapter in the application. Read from XML.
 *
 * @author Tim Lavers
 */
internal val CHAPTER = "Chapter"

class ChapterSpec(node: Element) : Spec(node) {
    private val wordLists = LinkedList<WordListSpec>()

    init {
        val childNodes = node.getElementsByTagName(TAG)
        for (i in 0..childNodes.length - 1) {
            wordLists.add(WordListSpec(childNodes.item(i) as Element))
        }
        wordLists.add(WordListSpec("Favourites", IndoFlash.FAVOURITES_FILE_NAME))
    }

    fun wordLists(): List<WordListSpec> {
        return wordLists
    }

    override fun toString(): String {
        return title()
    }

    fun forName(name: String): WordListSpec? {
        for (list in wordLists) {
            if (list.title() == name) {
                return list
            }
        }
        return null
    }
}