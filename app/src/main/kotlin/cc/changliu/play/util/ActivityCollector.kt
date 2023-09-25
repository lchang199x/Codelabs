package cc.changliu.play.util

import android.content.pm.PackageManager
import cc.changliu.play.MainApp

fun collectActivitiesExcept(vararg list: String): List<String> {
    val manifestActivities = mutableListOf<String>()

    MainApp.getContext().packageManager.getPackageInfo(
        MainApp.getContext().packageName,
        PackageManager.GET_ACTIVITIES
    )?.apply {
        activities?.forEach {
            if (list.contains(it.name)) {
                return@forEach
            }
            manifestActivities.add(it.name)
        }
    }
    return manifestActivities
}