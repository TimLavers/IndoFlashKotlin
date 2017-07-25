package org.grandtestauto.indoflash.spec

import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

/**
 * Title and filename, read from XML.

 * @author Tim Lavers
 */
private val TITLE_TAG = "Title"

fun Node.children(): Iterable<Node> {
    return this.childNodes.iterable()
}

fun NodeList.iterable(): Iterable<Node> {
    val result = mutableListOf<Node>()
    for (i in 0..this.length - 1) {
        result.add(this.item(i))
    }
    return result.asIterable()
}

open class Spec {
    val title: String

    constructor(title: String) {
        this.title = title
    }

    constructor(node: Element) {
        title = node.children().find { it.nodeName == TITLE_TAG }?.textContent?.trim() ?: ""
    }

    override fun toString(): String {
        return title
    }
}