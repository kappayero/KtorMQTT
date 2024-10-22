package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/list-files") {
            val directoryPath = "./cert" // Relative path from the project root
            val directory = File(directoryPath)

            if (directory.exists() && directory.isDirectory) {
                val files = directory.listFiles()?.filter { it.isFile }
                val fileNames = files?.joinToString(", ") { it.name } ?: "No files found."
                call.respondText("Files: $fileNames")
            } else {
                call.respondText("Directory not found or is not a directory.")

            }
        }
    }
}
