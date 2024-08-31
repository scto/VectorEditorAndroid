package good.damn.editor.vector

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import good.damn.editor.vector.enums.VEEnumPrimitives
import good.damn.editor.vector.porters.VEExporter
import good.damn.editor.vector.extensions.views.boundsFrame
import good.damn.editor.vector.files.VEFileDocument
import good.damn.editor.vector.views.VEViewVector
import good.damn.gradient_color_picker.GradientColorPicker

class VEActivityMain
: AppCompatActivity() {

    private var mViewVector: VEViewVector? = null

    private var mExporter = VEExporter()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this
        val root = FrameLayout(
            context
        ).apply {
            setBackgroundColor(
                0xff08193A.toInt()
            )
        }

        val topOptions = VEApp.height * 0.3f

        mViewVector = VEViewVector(
            context
        ).apply {

            setBackgroundColor(
                0xff565656.toInt()
            )

            boundsFrame(
                width = VEApp.width.toFloat(),
                height = topOptions
            )

            root.addView(
                this
            )
        }


        View(
            context
        ).apply {

            setBackgroundColor(
                0xffff00ff.toInt()
            )

            val s = VEApp.width * 0.1f
            boundsFrame(
                width = s,
                height = s,
                top = topOptions
            )

            setOnClickListener {
                mViewVector?.currentPrimitive = VEEnumPrimitives
                    .LINE
            }

            root.addView(
                this
            )
        }

        View(
            context
        ).apply {

            setBackgroundColor(
                0xffffff00.toInt()
            )

            val s = VEApp.width * 0.1f
            boundsFrame(
                width = s,
                height = s,
                top = topOptions,
                start = s
            )

            setOnClickListener {
                mViewVector?.currentPrimitive = VEEnumPrimitives
                    .CIRCLE
            }

            root.addView(
                this
            )
        }

        SeekBar(
            context
        ).apply {
            progress = 0
            max = 100

            setOnSeekBarChangeListener(
                object : OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        val n = progress / 100f
                        mViewVector?.strokeWidth = n * VEApp.width
                    }
                    override fun onStartTrackingTouch(
                        seekBar: SeekBar?
                    ) = Unit
                    override fun onStopTrackingTouch(
                        seekBar: SeekBar?
                    ) = Unit
                }
            )

            boundsFrame(
                top = VEApp.height * 0.4f,
                width = VEApp.width.toFloat()
            )

            root.addView(
                this
            )
        }

        GradientColorPicker(
            context
        ).apply {

            boundsFrame(
                top = VEApp.height * 0.5f,
                width = VEApp.width.toFloat(),
                height = VEApp.height * 0.2f
            )

            setOnPickColorListener {
                mViewVector?.color = it
            }

            root.addView(
                this
            )
        }

        LinearLayout(
            context
        ).apply {
            orientation = LinearLayout.HORIZONTAL

            AppCompatButton(
                context
            ).apply {
                text = "Export"
                setOnClickListener(
                    this@VEActivityMain::onClickExportVector
                )
                addView(
                    this
                )
            }

            AppCompatButton(
                context
            ).apply {
                text = "Import"
                setOnClickListener(
                    this@VEActivityMain::onClickImportVector
                )
                addView(this)
            }

            AppCompatButton(
                context
            ).apply {
                text = "Delete all"
                setOnClickListener(
                    this@VEActivityMain::onClickDeleteAll
                )
                addView(this)
            }

            AppCompatButton(
                context
            ).apply {
                text = "Undo"
                setOnClickListener(
                    this@VEActivityMain::onClickUndoAction
                )
                addView(this)
            }

            boundsFrame(
                top = VEApp.height * 0.7f
            )

            root.addView(this)
        }

        setContentView(
            root
        )

    }

    private fun onClickExportVector(
        v: View
    ) {
        val data = mViewVector?.primitives
            ?: return

        mExporter.exportTo(
            VEFileDocument(
                "myVector.sav"
            ),
            data
        )
    }

    private fun onClickImportVector(
        v: View
    ) {

    }

    private fun onClickDeleteAll(
        v: View
    ) {
        mViewVector?.clearVector()
    }

    private fun onClickUndoAction(
        v: View
    ) {
        mViewVector?.undoVector()
    }

}