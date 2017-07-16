package org.grandtestauto.indoflash.spec

import org.grandtestauto.indoflash.spec.Spec
import org.junit.Assert
import org.junit.Test

class SpecTest {

    private fun spec(title: String): org.grandtestauto.indoflash.spec.Spec {
        return org.grandtestauto.indoflash.spec.Spec(title)
    }

    @org.junit.Test
    fun testTitle() {
        org.junit.Assert.assertEquals("The title", spec("The title").title())
    }
}