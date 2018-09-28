package tests.tetlaixtlalia.protoar_1

import org.artoolkitx.arx.arxj.rendering.ARRenderer
import org.artoolkitx.arx.arxj.rendering.shader_impl.SimpleShaderProgram
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import org.artoolkitx.arx.arxj.Trackable
import org.artoolkitx.arx.arxj.rendering.shader_impl.Cube
import org.artoolkitx.arx.arxj.ARController
import org.artoolkitx.arx.arxj.rendering.shader_impl.SimpleFragmentShader
import org.artoolkitx.arx.arxj.rendering.shader_impl.SimpleVertexShader
import android.opengl.GLES20

class PARRender : ARRenderer(){


    private var shaderProgram : SimpleShaderProgram? = null

    private var trackables = arrayOf(Trackable("hiro", 80.0f), Trackable("kanji", 80.0f))

    private var trackableUIDs = IntArray(trackables.size)

    private var cube: Cube? = null

    override fun configureARScene(): Boolean {
        var i = 0
        for (trackable in trackables) {
            trackableUIDs[i] = ARController.getInstance().addTrackable("single;Data/" + trackable.name + ".patt;" + trackable.width)
            if (trackableUIDs[i] < 0) return false
            i++
        }
        return true
    }

    override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
        this.shaderProgram = SimpleShaderProgram(SimpleVertexShader(), SimpleFragmentShader())
        cube = Cube(40.0f, 0.0f, 0.0f, 20.0f)
        cube!!.setShaderProgram(shaderProgram)
        super.onSurfaceCreated(unused, config)
    }

    override fun draw() {
        super.draw()

        GLES20.glEnable(GLES20.GL_CULL_FACE)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)
        GLES20.glFrontFace(GLES20.GL_CCW)

        // Look for trackables, and draw on each found one.
        for (trackableUID in trackableUIDs) {
            // If the trackable is visible, apply its transformation, and render a cube
            val modelViewMatrix = FloatArray(16)
            if (ARController.getInstance().queryTrackableVisibilityAndTransformation(trackableUID, modelViewMatrix)) {
                val projectionMatrix = ARController.getInstance().getProjectionMatrix(10.0f, 10000.0f)
                cube!!.draw(projectionMatrix, modelViewMatrix)
            }
        }
    }
}