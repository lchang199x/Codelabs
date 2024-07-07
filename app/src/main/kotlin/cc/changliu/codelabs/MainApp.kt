package cc.changliu.codelabs

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

@SuppressLint("StaticFieldLeak")
class MainApp : Application(), DefaultLifecycleObserver {
    override fun onCreate() {
        super<Application>.onCreate()
        context = applicationContext

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
//        Toast.makeText(this, " ^_^", Toast.LENGTH_SHORT).show()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
//        Toast.makeText(this, " v_v", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var context: Context? = null

        @JvmStatic
        fun getContext(): Context = context!!
    }
}