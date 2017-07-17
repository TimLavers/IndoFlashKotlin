package org.grandtestauto.indoflash.spec

import org.junit.Assert
import org.junit.Test
import org.w3c.dom.Element

class SpecTest {

    private fun spec(title: String): org.grandtestauto.indoflash.spec.Spec {
        return org.grandtestauto.indoflash.spec.Spec(title)
    }

    @org.junit.Test
    fun testTitle() {
        org.junit.Assert.assertEquals("The title", spec("The title").title())
    }

    @Test
    fun constructFromNode() {
        val xml = "<WordList><Title>Lesson 1</Title><File>lesson1</File></WordList>"
        val appNode = parseNode(xml, TAG)
        val list = Spec(appNode as Element)
        Assert.assertEquals("Lesson 1", list.title())
    }
}