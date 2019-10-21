package com.ageone.naladonipartner.External.Libraries.Loger

import timber.log.Timber

class TimberTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber})"
    }
}