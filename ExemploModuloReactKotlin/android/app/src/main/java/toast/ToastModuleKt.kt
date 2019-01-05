package toast

import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class ToastModuleKt(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName() = "ToastModuleKt"

  @ReactMethod
  fun showShort(message: String) = Toast.makeText(reactApplicationContext, message, Toast.LENGTH_SHORT).show()

  @ReactMethod
  fun showLong(message: String) = Toast.makeText(reactApplicationContext, message, Toast.LENGTH_LONG).show()

  @ReactMethod
  fun show(message: String, duration: Int) = Toast.makeText(reactApplicationContext, message, duration).show()
}
