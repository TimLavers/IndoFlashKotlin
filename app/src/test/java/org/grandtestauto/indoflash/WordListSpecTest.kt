package org.grandtestauto.indoflash

import org.junit.Assert
import org.junit.Test

class WordListSpecTest {

    private fun wordListSpec(title: String, fileName: String): WordListSpec {
        return WordListSpec(title, fileName)
    }

    @Test
    fun testTitle() {
        val title = "The title"
        Assert.assertEquals(title, wordListSpec(title, "The file").title())
    }

    @Test
    fun testFileName() {
        val title = "The title"
        val fileName = "The file"
        Assert.assertEquals(fileName, wordListSpec(title, fileName).fileName())
    }
}
