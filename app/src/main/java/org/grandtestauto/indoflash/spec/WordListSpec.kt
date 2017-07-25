package org.grandtestauto.indoflash.spec

import org.w3c.dom.Element

val WORD_LIST = "WordList"

/**
 * Title and filename for a word list. Read from XML.

 * @author Tim Lavers
 */
class WordListSpec : Spec {
    private val FILE_TAG = "File"

    val fileName: String

    internal constructor(title: String, fileName: String) : super(title) {
        this.fileName = fileName
    }

    internal constructor(node: Element) : super(node) {
        val childNodes = node.getElementsByTagName(FILE_TAG)
        fileName = childNodes.item(0).textContent.trim()
    }
}