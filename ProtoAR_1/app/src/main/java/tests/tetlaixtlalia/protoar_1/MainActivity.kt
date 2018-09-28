package tests.tetlaixtlalia.protoar_1

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import org.artoolkitx.arx.arxj.ARActivity
import org.artoolkitx.arx.arxj.ARController
import org.artoolkitx.arx.arxj.assets.AssetHelper
import org.artoolkitx.arx.arxj.rendering.ARRenderer

class MainActivity : ARActivity() {
    override fun supplyFrameLayout(): FrameLayout {
        return this.findViewById(R.id.mainFrameLayout)
    }

    override fun supplyRenderer(): ARRenderer {
        return PARRender()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initializeInstance()
    }

    protected fun initializeInstance() {
        val assetH = AssetHelper(assets)
        assetH.cacheAssetFolder(this, "Data")
        assetH.cacheAssetFolder(this, "cparam_cache")
    }
}
