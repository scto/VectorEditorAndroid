package good.damn.editor.animator.options.tickTimer.data

import good.damn.editor.animator.options.tickTimer.data.base.VETickDataBase

data class VETickDataPosition(
    override val tickFactor: Float,
    val x: Float,
    val y: Float
): VETickDataBase()