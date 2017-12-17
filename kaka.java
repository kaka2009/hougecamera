this is dev
package com.ronda.barcodescanner;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;


private void startKeyEventService(){
    //注意 这里可能为空（也就是如果当前没有任何一个无障碍服务被授权的时候 就为空了 ）
    String enabledServicesSetting = Settings.Secure.getString(getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);

    ComponentName selfComponentName = new ComponentName(getPackageName(), "com.ronda.barcodescanner.KeyEventService");
    String flattenToString = selfComponentName.flattenToString();
    if (enabledServicesSetting == null || !enabledServicesSetting.contains(flattenToString)) {
        enabledServicesSetting += flattenToString;
    }
    Settings.Secure.putString(getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, enabledServicesSetting);
    Settings.Secure.putInt(getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, 1);
}

*/

public class KeyEventService extends AccessibilityService {
    private static final String TAG = KeyEventService.class.getSimpleName();

    public KeyEventService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("Liu", "onAccessibilityEvent --> " + event);

    }

    @Override
    public void onInterrupt() {
        Log.i("Liu", "onInterrupt");
    }

    /**
     * 复写这个方法可以捕获按键事件
     *
     * @param event
     * @return
     */
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Log.w(TAG, "keyEvent:" + event + "keyCode: " + keyCode + "char: " + KeyEvent.keyCodeToString(keyCode));

        return super.onKeyEvent(event);
    }
}
