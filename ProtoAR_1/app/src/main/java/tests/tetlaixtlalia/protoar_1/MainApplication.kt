package tests.tetlaixtlalia.protoar_1

import android.app.Application
import android.content.Context
import org.artoolkitx.arx.arxj.ARController
import org.artoolkitx.arx.arxj.assets.AssetHelper

class MainApplication : Application() {
    private var mContext: Context? = null

    fun getContext(): Context? {
        return mContext
    }

    override fun onCreate() {
        super.onCreate()
        mContext = getContext()

        initializeInstance()
    }

    protected fun initializeInstance() {
        val assetH = AssetHelper(assets)
        assetH.cacheAssetFolder(this, "Data")
        assetH.cacheAssetFolder(this, "cparam_cache")
    }


}

