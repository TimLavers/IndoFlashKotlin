package org.grandtestauto.indoflash.spec

import org.w3c.dom.Element

/**
 * Topic and sub-topic structure for the word lists that make up
 * the application. Read from XML.
 *
 * @author Tim Lavers
 */
private val FLASH_CARDS_APP_TAG = "FlashCardsApp"
private val CHAPTERS_TAG = "Chapters"

class ApplicationSpec internal constructor(document: org.w3c.dom.Document) {

    val chapterSpecs : List<ChapterSpec>

    init {
        val tempList = mutableListOf<ChapterSpec>()
        fun addChapters(chapters : Element) {
            val chapterNodes = chapters.getElementsByTagName(CHAPTER)
            chapterNodes.iterable().forEach {
                tempList.add(ChapterSpec(it as Element))
            }
        }
        val appNode = document.getElementsByTagName(FLASH_CARDS_APP_TAG).item(0)
        appNode.children().forEach {
            if (it.nodeName == CHAPTERS_TAG) {
                addChapters(it as Element)
            }
        }
        chapterSpecs = tempList.toList()

    }

    fun chapterForName(name: String): ChapterSpec? = chapterSpecs.filter { it.title == name }.firstOrNull()
}