
package main

import javafx.scene.layout.HBox
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root: HBox = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
}

