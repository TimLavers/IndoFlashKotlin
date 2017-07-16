package org.grandtestauto.indoflash.spec

import org.grandtestauto.indoflash.FAVOURITES_FILE_NAME
import org.w3c.dom.Element

/**
 * Sub-topic structure for the word lists that make up
 * a chapter in the application. Read from XML.
 *
 * @author Tim Lavers
 */
val CHAPTER = "Chapter"
val FAVOURITES = "Favourites"

class ChapterSpec(node: org.w3c.dom.Element) : Spec(node) {
    private val wordLists = java.util.LinkedList<WordListSpec>()

    init {
        val childNodes = node.getElementsByTagName(TAG)
        for (i in 0..childNodes.length - 1) {
            wordLists.add(WordListSpec(childNodes.item(i) as Element))
        }
        wordLists.add(WordListSpec(FAVOURITES, FAVOURITES_FILE_NAME))
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