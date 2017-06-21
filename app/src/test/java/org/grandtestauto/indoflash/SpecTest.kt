package org.grandtestauto.indoflash

import org.junit.Assert
import org.junit.Test

class SpecTest {

    private fun spec(title: String): Spec {
        return Spec(title)
    }

    @Test
    fun testTitle() {
        Assert.assertEquals("The title", spec("The title").title())
    }
}