package good.damn.editor.animation.animator.options.canvas

import good.damn.editor.animation.animator.options.canvas.previews.VECanvasOptionPreviewColor
import good.damn.editor.transaction.VEIRequesterFloat
import good.damn.editor.transaction.VEITransactionReceiver
import good.damn.editor.transaction.VETransactionKeyFrame
import good.damn.sav.core.animation.animators.VEAnimatorColor
import good.damn.sav.core.animation.keyframe.VEIAnimationOption
import good.damn.sav.core.animation.keyframe.VEMKeyframeColor
import good.damn.sav.core.shapes.VEShapeBase
import good.damn.sav.misc.extensions.primitives.toByteArray
import good.damn.sav.misc.structures.tree.toList

class VEAnimationOptionCanvasColor(
    private val shape: VEShapeBase,
    option: VEIAnimationOption<VEMKeyframeColor>,
    requester: VEIRequesterFloat
): VEAnimationOptionCanvasBase<VEMKeyframeColor>(
    option,
    requester
), VEITransactionReceiver {

    override val preview = VECanvasOptionPreviewColor(
        VETransactionKeyFrame(
            this
        )
    )

    override fun createAnimator() = if (
        option.keyframes.size > 1
    ) VEAnimatorColor(
        shape,
        option.keyframes.toList().iterator(),
        option.duration
    ) else null

    override fun onReceiveTransaction() = option.keyframes.add(
        VEMKeyframeColor(
            getFactor(),
            shape.color.toByteArray()
        )
    )

}