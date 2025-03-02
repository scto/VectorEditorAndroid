package good.damn.editor.vector.bottomsheets

import android.content.Context
import android.graphics.PointF
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import good.damn.editor.vector.VEApp
import good.damn.editor.vector.bottomsheets.listeners.VEIListenerBottomSheetFill
import good.damn.editor.vector.extensions.views.bounds
import good.damn.editor.vector.extensions.views.boundsFrame
import good.damn.editor.vector.view.gradient.VECanvasColorSeek
import good.damn.editor.vector.view.gradient.VEViewGradientMaker
import good.damn.sav.core.shapes.fill.VEIFill
import good.damn.sav.core.shapes.fill.VEMFillColor
import good.damn.sav.core.shapes.fill.VEMFillGradientLinear
import good.damn.sav.misc.extensions.toInt32

class VEBottomSheetMakeGradient(
    private val toView: ViewGroup,
    private val onConfirmFill: VEIListenerBottomSheetFill<VEMFillGradientLinear>
): VEBottomSheet(
    toView
) {

    override fun onCreateView(
        context: Context
    ) = FrameLayout(
        context
    ).apply {

        setBackgroundColor(
            0xff000315.toInt()
        )

        VEViewGradientMaker(
            context
        ).apply {

            colors = arrayListOf(
                VECanvasColorSeek().apply {
                    color = 0xffff0000.toInt()
                },
                VECanvasColorSeek().apply {
                    color = 0xff00ff00.toInt()
                }
            )

            addView(
                this,
                -1,
                -1
            )
        }

        boundsFrame(
            width = VEApp.width.toFloat(),
            height = VEApp.height * 0.2f,
            gravity = Gravity.BOTTOM
        )
    }

}
