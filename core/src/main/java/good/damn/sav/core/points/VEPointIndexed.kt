package good.damn.sav.core.points

import android.graphics.PointF
import good.damn.sav.core.VEIIndexable
import kotlin.random.Random

class VEPointIndexed(
    x: Float,
    y: Float
): PointF(
    x,y
), VEIIndexable {
    override var index: Int = -1
    constructor(
        point: PointF
    ): this(
        point.x,
        point.y
    )

    private val hash = Random.nextInt()

    override fun hashCode() = hash
}