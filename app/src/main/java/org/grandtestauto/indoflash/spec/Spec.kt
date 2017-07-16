package org.grandtestauto.indoflash.spec

import org.w3c.dom.Element

/**
 * Title and filename, read from XML.

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