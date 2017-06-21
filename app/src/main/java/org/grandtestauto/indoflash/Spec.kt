package org.grandtestauto.indoflash

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

/**
 * Title and filename. Read from XML.

 * @author TimL
 */
private val TITLE_TAG = "Title"

open class Spec {
    private var title: String = ""

    constructor(title: String) {
        this.title = title
    }

    constructor(node: Element) {
        val childNodes = node.childNodes
        for (i in 0..childNodes.length - 1) {
            val child = childNodes.item(i)
            if (child.nodeName == TITLE_TAG) {
                title = child.textContent.trim { it <= ' ' }
            }
        }
    }

    fun title(): String {
        return title
    }
}