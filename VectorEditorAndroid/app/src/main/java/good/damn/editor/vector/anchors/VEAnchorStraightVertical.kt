package good.damn.editor.vector.anchors

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import good.damn.editor.vector.extensions.drawLine
import kotlin.math.abs

class VEAnchorStraightVertical
: VEBaseAnchor() {

    override fun checkAnchor(
        x: Float,
        y: Float,
        x2: Float,
        y2: Float
    ) = abs(
        x - x2
    ) < 30 // px

    override fun onDraw(
        canvas: Canvas,
        x: Float,
        y: Float,
        x2: Float,
        y2: Float
    ) {
        onAnchorPoint?.apply {
            onAnchorX(x)
            onAnchorY(y2)
        }
        canvas.drawLine(
            x,
            y,
            x,
            y2,
            mPaint
        )
    }

}