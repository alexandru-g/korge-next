package com.soywiz.korge.component.docking

import com.soywiz.korge.component.*
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.*

fun <T : View> T.dockedTo(anchor: Anchor, scaleMode: ScaleMode = ScaleMode.NO_SCALE): T {
    DockingComponent(this, anchor, scaleMode).attach()
    return this
}

class DockingComponent(override val view: View, var anchor: Anchor, var scaleMode: ScaleMode = ScaleMode.NO_SCALE) : ResizeComponent {
    val initialViewSize = Size(view.width, view.height)
    private val actualVirtualSize = Size(0, 0)
    private val targetSize = Size(0, 0)

    init {
        view.deferWithViews { views ->
            resized(views, views.actualVirtualWidth, views.actualVirtualHeight)
        }
    }

    override fun resized(views: Views, width: Int, height: Int) {
        //println(views.actualVirtualWidth)
        view.position(
            views.actualVirtualWidth * anchor.sx,
            views.actualVirtualHeight * anchor.sy
        )
        // @TODO: This is not working? why?
        //view.alignX(views.stage, anchor.sx, true)
        //view.alignY(views.stage, anchor.sy, true)
        if (scaleMode != ScaleMode.NO_SCALE) {
            actualVirtualSize.setTo(views.actualVirtualWidth, views.actualVirtualHeight)
            val size = scaleMode.invoke(initialViewSize, actualVirtualSize, targetSize)
            view.setSize(size.width, size.height)
        }
        view.invalidate()
        view.parent?.invalidate()
    }
}
