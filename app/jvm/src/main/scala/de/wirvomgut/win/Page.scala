package de.wirvomgut.win

import scalatags.Text.all._

object Page{
  val boot =
    "de.wirvomgut.win.Client().main(document.getElementById('contents'))"
  val skeleton =
    html(
      head(
        script(src:="/app-fastopt.js"),
        link(
          rel:="stylesheet",
          href:="lib/semantic-ui/dist/semantic.min.css"
        )
      ),
      body(
        onload:=boot,
        div(id:="contents")
      )
    )
}