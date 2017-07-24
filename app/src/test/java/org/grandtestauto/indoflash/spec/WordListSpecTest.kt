package org.grandtestauto.indoflash.spec

import org.junit.Assert
import org.junit.Test
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory

private fun wordListSpec(title: String, fileName: String): WordListSpec {
    return WordListSpec(title, fileName)
}

fun parseNode(xml: String, tag: String): Node {
    return getDocument(xml).getElementsByTagName(tag).item(0)
}

fun getDocument(xml: String): Document {
    val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val stream = ByteArrayInputStream(xml.toByteArray(charset("utf-8")))
    return builder.parse(stream)
}

class WordListSpecTest {

    @Test
    fun testTitle() {
        val title = "The title"
        Assert.assertEquals(title, wordListSpec(title, "The file").title)
    }

    @Test
    fun testFileName() {
        val title = "The title"
        val fileName = "The file"
        Assert.assertEquals(fileName, wordListSpec(title, fileName).fileName)
    }

    @Test
    fun toStringTest() {
        val title = "The title"
        Assert.assertEquals(title, wordListSpec(title, "The file").toString())
    }

    @Test
    fun constructFromNode() {
        val xml = "<WordList><Title>Lesson 1</Title><File>lesson1</File></WordList>"
        val appNode = parseNode(xml, TAG)
        val list = WordListSpec(appNode as Element)
        Assert.assertEquals("Lesson 1", list.title)
        Assert.assertEquals("lesson1", list.fileName)
    }
}