package good.damn.editor.importer.animation

import android.graphics.PointF
import good.damn.editor.importer.animation.extractor.VEIImportAnimationExtractor
import good.damn.editor.importer.animation.extractor.VEImportAnimationExtractorFill
import good.damn.editor.importer.animation.extractor.VEImportAnimationExtractorPosition
import good.damn.editor.importer.animation.extractor.VEImportAnimationExtractorStrokeWidth
import good.damn.sav.core.animation.animators.VEAnimatorInterpolation
import good.damn.sav.core.animation.animators.VEIListenerAnimation
import good.damn.sav.core.animation.interpolators.VEAnimationInterpolatorPosition
import good.damn.sav.core.animation.interpolators.VEIAnimationInterpolator
import good.damn.sav.core.animation.interpolators.fill.VEAnimationInterpolatorFill
import good.damn.sav.core.animation.interpolators.fill.VEAnimationObserverFill
import good.damn.sav.core.points.VEPointIndexed
import good.damn.sav.core.shapes.VEShapeBase
import good.damn.sav.core.shapes.fill.VEIFill
import good.damn.sav.misc.Size
import java.io.InputStream

class VEImportAnimationDefault(
    private val canvasSize: Size
): VEIListenerImportAnimation<VEIListenerAnimation> {

    override fun createFillAnimation(
        type: Int,
        keyframesCount: Int,
        observerFill: VEAnimationObserverFill,
        inp: InputStream
    ) = VEAnimatorInterpolation(
        VEIImportAnimationExtractor.extractAnimationInterpolators(
            keyframesCount,
            inp,
            VEImportAnimationExtractorFill(
                observerFill,
                canvasSize
            )
        )
    )

    override fun createShapeAnimation(
        property: Int,
        keyframesCount: Int,
        shape: VEShapeBase,
        inp: InputStream
    ) = VEAnimatorInterpolation(
        VEIImportAnimationExtractor.extractAnimationInterpolators(
            keyframesCount,
            inp,
            VEImportAnimationExtractorStrokeWidth(
                shape,
                canvasSize
            )
        )
    )

    override fun createPointAnimation(
        property: Int,
        keyframesCount: Int,
        point: VEPointIndexed,
        inp: InputStream
    ) = VEAnimatorInterpolation(
        VEIImportAnimationExtractor.extractAnimationInterpolators(
            keyframesCount,
            inp,
            VEImportAnimationExtractorPosition(
                canvasSize,
                point
            )
        )
    )
}