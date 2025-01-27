package good.damn.sav.core.animation.keyframe

import good.damn.sav.core.VEIExportable

interface VEIKeyframe
: VEIExportable {
    val factor: Float // value in range 0.0 <= x <= 1.0
}