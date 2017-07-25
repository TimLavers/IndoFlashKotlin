package org.grandtestauto.indoflash.spec

import org.w3c.dom.Document
import org.w3c.dom.Element

/**
 * Topic and sub-topic structure for the word lists that make up
 * the application. Read from XML.
 *
 * @author Tim Lavers
 */
class ApplicationSpec internal constructor(document: Document) {

    val chapterSpecs: List<ChapterSpec>

    init {
        val tempList = mutableListOf<ChapterSpec>()
        document.getElementsByTagName(CHAPTER).iterable().forEach {
            tempList.add(ChapterSpec(it as Element))
        }
        chapterSpecs = tempList.toList()
    }

    fun chapterForName(name: String): ChapterSpec? = chapterSpecs.filter { it.title == name }.firstOrNull()
}