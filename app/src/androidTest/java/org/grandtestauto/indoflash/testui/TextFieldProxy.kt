package org.grandtestauto.indoflash.testui

internal class TextFieldProxy(id: Int) : ViewProxy(id) {
    fun checkThatTextIsEmpty() {
        checkText("")
    }
}