package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
import java.nio.file.Paths

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/current-path") {
            // Get the absolute path of the current working directory
            val currentPath = Paths.get("").toAbsolutePath().toString()
            call.respondText("Current Absolute Path: $currentPath")
        }
        get("/list-files") {
            val bodyText = call.receiveText()
            val textWithoutQuotes = bodyText.replace("\"", "")
            println("Received body: '$textWithoutQuotes'")
            val directoryPath = textWithoutQuotes // Relative path from the project root
            val directory = File(directoryPath)

            if (directory.exists() && directory.isDirectory) {
                val absolutePath = directory.absolutePath
                val files = directory.listFiles()?.filter { it.isFile }
                val fileNames = "$absolutePath --> " + (files?.joinToString(", ") { it.name } ?: "No files found.")
                call.respondText("Files it $fileNames")
            } else {
                call.respondText("Directory not found or is not a directory.")

            }
        }
    }
}
