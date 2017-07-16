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

    private val chapterSpecs = java.util.LinkedList<ChapterSpec>()

    init {
        val appNode = document.getElementsByTagName(org.grandtestauto.indoflash.spec.FLASH_CARDS_APP_TAG).item(0)
        val children = appNode.childNodes
        for (i in 0..children.length - 1) {
            val child = children.item(i)
            if (child.nodeName == org.grandtestauto.indoflash.spec.CHAPTERS_TAG) {
                val chapterNodes = (child as org.w3c.dom.Element).getElementsByTagName(CHAPTER)
                for (c in 0..chapterNodes.length - 1) {
                    chapterSpecs.add(ChapterSpec(chapterNodes.item(c) as Element))
                }
            }
        }
    }

    fun chapterSpecs(): List<ChapterSpec> {
        return chapterSpecs
    }

    fun chapterForName(name: String): ChapterSpec? {
        for (spec in chapterSpecs) {
            if (spec.title() == name) {
                return spec
            }
        }
        return null
    }
}