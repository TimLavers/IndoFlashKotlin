package org.grandtestauto.indoflash

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

val TAG = "WordList"
/**
 * Title and filename for a word list. Read from XML.

 * @author TimL
 */
class WordListSpec : Spec {
    private val FILE_TAG = "File"

    private var fileName: String = ""

    internal constructor(title: String, fileName: String) : super(title) {
        this.fileName = fileName
    }

    internal constructor(node: Element) : super(node) {
        val childNodes = node.getElementsByTagName(FILE_TAG)
        val child = childNodes.item(0)
        fileName = child.textContent.trim { it <= ' ' }
    }

    override fun toString(): String {
        return title()
    }

    internal fun fileName(): String {
        return fileName
    }
}