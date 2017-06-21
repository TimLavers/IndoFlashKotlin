package org.grandtestauto.indoflash

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import java.util.LinkedList

/**
 * Topic and sub-topic structure for the word lists that make up
 * the application. Read from XML.
 *
 * @author Tim Lavers
 */
private val FLASH_CARDS_APP_TAG = "FlashCardsApp"
private val CHAPTERS_TAG = "Chapters"
class ApplicationSpec internal constructor(document: Document) {

    private val chapterSpecs = LinkedList<ChapterSpec>()

    init {
        val appNode = document.getElementsByTagName(FLASH_CARDS_APP_TAG).item(0)
        val children = appNode.childNodes
        for (i in 0..children.length - 1) {
            val child = children.item(i)
            if (child.nodeName == CHAPTERS_TAG) {
                val chapterNodes = (child as Element).getElementsByTagName(CHAPTER)
                for (c in 0..chapterNodes.length - 1) {
                    chapterSpecs.add(ChapterSpec(chapterNodes.item(c) as Element))
                }
            }
        }
    }

    fun chapterSpecs(): List<ChapterSpec> {
        return chapterSpecs
    }

    internal fun chapterForName(name: String): ChapterSpec? {
        for (spec in chapterSpecs) {
            if (spec.title() == name) {
                return spec
            }
        }
        return null
    }
}