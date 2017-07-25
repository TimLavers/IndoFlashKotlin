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

class ChapterSpec(node: Element) : Spec(node) {
    private val wordLists = mutableListOf<WordListSpec>()

    init {
        val childNodes = node.getElementsByTagName(WORD_LIST)
        childNodes.iterable().forEach { wordLists.add(WordListSpec(it as Element)) }
        wordLists.add(WordListSpec(FAVOURITES, FAVOURITES_FILE_NAME))
    }

    fun wordLists(): List<WordListSpec> {
        return wordLists
    }

    fun forName(name: String): WordListSpec? = wordLists.filter { it.title == name }.firstOrNull()
}