package org.grandtestauto.indoflash.spec

import org.grandtestauto.indoflash.FAVOURITES_FILE_NAME
import org.junit.Assert
import org.junit.Test
import org.w3c.dom.Element

/**
 * @author Tim Lavers
 */
class ChapterSpecTest {

    private val XML = "<Chapter>" +
            "<Title>Lessons 1 - 10</Title>" +
            "<WordList><Title>Lesson 2</Title><File>lesson2</File></WordList>" +
            "<WordList><Title>Lesson 3</Title><File>lesson3</File></WordList>" +
            "</Chapter>"

    @Test
    fun constructFromNode() {
        val appNode = parseNode(XML, CHAPTER)
        val spec = ChapterSpec(appNode as Element)
        Assert.assertEquals("Lessons 1 - 10", spec.title)
        //Check that the ChapterSpec contains specs for the word lists
        //in the xml plus one for the favourites.
        val wordListSpecs = spec.wordLists()
        Assert.assertEquals(3, wordListSpecs.size.toLong())
        Assert.assertEquals("Lesson 2", wordListSpecs[0].title)
        Assert.assertEquals("lesson2", wordListSpecs[0].fileName)
        Assert.assertEquals("Lesson 3", wordListSpecs[1].title)
        Assert.assertEquals("lesson3", wordListSpecs[1].fileName)
        Assert.assertEquals(FAVOURITES, wordListSpecs[2].title)
        Assert.assertEquals(FAVOURITES_FILE_NAME, wordListSpecs[2].fileName)
    }

    @Test
    fun forName() {
        val appNode = parseNode(XML, CHAPTER)
        val spec = ChapterSpec(appNode as Element)
        Assert.assertEquals("lesson2", spec.forName("Lesson 2")!!.fileName)
        Assert.assertEquals("lesson3", spec.forName("Lesson 3")!!.fileName)
        Assert.assertEquals(FAVOURITES_FILE_NAME, spec.forName(FAVOURITES)!!.fileName)
        Assert.assertNull(spec.forName("not there"))
    }

    @Test
    fun toStringTest() {
        val appNode = parseNode(XML, CHAPTER)
        val spec = ChapterSpec(appNode as Element)
        Assert.assertEquals("Lessons 1 - 10", spec.toString())
    }
}