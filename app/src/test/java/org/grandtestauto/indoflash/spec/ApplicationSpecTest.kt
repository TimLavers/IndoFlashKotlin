package org.grandtestauto.indoflash.spec

import org.junit.Assert
import org.junit.Test

/**
 * @author Tim Lavers
 */
class ApplicationSpecTest {

    @Test
    fun constructFromNode() {
        val spec = ApplicationSpec(getDocument(XML))
        Assert.assertEquals(2, spec.chapterSpecs.size.toLong())
        Assert.assertEquals("Lessons 1 - 10", spec.chapterSpecs[0].title())
        Assert.assertEquals("Lessons 11 - 20", spec.chapterSpecs[1].title())
    }

    @Test
    fun chapterForName() {
        val spec = ApplicationSpec(getDocument(XML))
        Assert.assertEquals("Lessons 1 - 10", spec.chapterForName("Lessons 1 - 10")!!.title())
        Assert.assertNull(spec.chapterForName("not there"))
    }

    private val XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<FlashCardsApp><AppName>IndoFlash</AppName>" +
            "    <Chapters>\n" +
            "        <Chapter>\n" +
            "            <Title>Lessons 1 - 10</Title>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 1</Title>\n" +
            "                <File>lesson1</File>\n" +
            "            </WordList>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 2</Title>\n" +
            "                <File>lesson2</File>\n" +
            "            </WordList>\n" +
            "        </Chapter>\n" +
            "        <Chapter>\n" +
            "            <Title>Lessons 11 - 20</Title>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 11</Title>\n" +
            "                <File>lesson11</File>\n" +
            "            </WordList>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 12</Title>\n" +
            "                <File>lesson12</File>\n" +
            "            </WordList>\n" +
            "        </Chapter>\n" +
            "    </Chapters>\n" +
            "</FlashCardsApp>"
}