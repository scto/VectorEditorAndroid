package good.damn.sav.core.animation.keyframe.fill

import good.damn.sav.core.animation.keyframe.VEIInterpolatablePriority
import good.damn.sav.core.animation.keyframe.VEIKeyframe
import good.damn.sav.core.shapes.fill.VEIFill
import good.damn.sav.core.shapes.fill.VEMFillColor
import good.damn.sav.core.shapes.fill.VEMFillGradientLinear
import good.damn.sav.misc.Size
import good.damn.sav.misc.extensions.io.write
import good.damn.sav.misc.extensions.primitives.toDigitalFraction
import java.io.InputStream
import java.io.OutputStream

data class VEMKeyframeFill(
    override val factor: Float,
    val fill: VEIFill,
    val fillPriority: VEIInterpolatablePriority
): VEIKeyframe {

    companion object {
        fun import(
            factor: Float,
            inp: InputStream,
            canvasSize: Size
        ) = VEIFill.importFill(
            inp,
            canvasSize
        ).run {
            VEMKeyframeFill(
                factor,
                this,
                createPriority()
            )
        }
    }

    override fun export(
        os: OutputStream,
        canvasSize: Size
    ) = os.run {
        write(
            factor.toDigitalFraction()
        )

        fill.write(
            this,
            canvasSize
        )
    }
}