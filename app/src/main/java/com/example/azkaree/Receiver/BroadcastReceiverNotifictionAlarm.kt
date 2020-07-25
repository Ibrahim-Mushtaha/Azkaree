package com.example.nurbk.ps.appathkar.Receiver


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.azkaree.Activity.MainActivity
import com.example.azkaree.R
import com.example.azkaree.model.MyApp
import com.example.azkaree.model.SettingSystemString
import com.example.azkaree.model.SharedPrefTheme
import com.example.nurbk.ps.appathkar.Fragment.AthkerTypeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BroadcastReceiverNotifictionAlarm : BroadcastReceiver() {

    private lateinit var share: SharedPreferences
    override fun onReceive(context: Context, intent: Intent) {

        share = context.getSharedPreferences(SettingSystemString.NAME_FILE_SHER, Context.MODE_PRIVATE)

        var time = SimpleDateFormat("HH:mm").format(Date())

        createNotification(context,getData(context), time)
        Thread(Runnable {
        AthkerTypeFragment.startAlam(context,share.getLong(SettingSystemString.COUNTER_NOTIFICATION,20*60000))
        }).start()

    }


    fun createNotification(context: Context, content: String, time: String) {
        val expandedView = RemoteViews(context.packageName,
            if (MyApp.sharedRefrance!!.loadNightModeState() == true){
            R.layout.notification_alam_night_mode
        }else{
            R.layout.notification_alam
        }
        )

        expandedView.setTextViewText(
            R.id.txtContentNotificationAlarm,
            content
        )
        expandedView.setTextViewText(R.id.txtTimeNotificationAlarm, time)

        val nc = NotificationCompat.Builder(context,SettingSystemString.CHANNEL_ID)
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifyIntent = Intent(context, MainActivity::class.java)

        notifyIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        nc.setContentIntent(pendingIntent)
        nc.setContentText(content)
        nc.setSmallIcon(R.drawable.ic_play)
        nc.setAutoCancel(true)
        nc.setCustomContentView(expandedView)
        nm.notify(8, nc.build())


    }


    fun getData(context: Context): String {
        val editor =
            share.edit()

        val data = ArrayList<String>()
        data.add(" رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ")
        data.add("اللهم إني أعوذ بك من جهد البلاء، ودرك الشقاء، وسوء القضاء، وشماتة الأعداءِ")
        data.add("اللهم إني أسألك الهدى، والتقى، والعفاف، والغنىِ")
        data.add("اللهم اهدني وسددني، اللهم إني أسألك الهدى والسدادِ")
        data.add("اللهم إني أعوذ بك من شر ما عملت، ومن شر ما لم أعملِ")
        data.add("اللهم إني أعوذ بك من زوال نعمتك، وتحول عافيتك، وفجاءة نقمتك، وجميع سخطك")
        data.add("اللهم رحمتك أرجو فلا تكلني إلى نفسي طرفة عين، وأصلح لي شأني كله، لا إله إلا أنت")
        data.add("اللهم مصرف القلوب صرف قلوبنا على طاعتك")
        data.add("اللهم أحسن عاقبتنا في الأمور كلها، وأجرنا من خزي الدنيا وعذاب الآخرة")
        data.add("اللهم إني أعوذ بك من شر سمعي، ومن شر بصري، ومن شر لساني، ومن شر قلبي، ومن شر منيي")
        data.add("اللهم إني أعوذ بك من البرص، والجنون، والجذام، ومن سيئ الأسقام")
        data.add("اللهم إنك عفو كريم تحب العفو فاعف عني")
        data.add("اللهم اجعل أوسع رزقك علي عند كبر سني، وانقطاع عمري")
        data.add("اللهم اغفر لي ذنبي، ووسع لي في داري، وبارك لي في رزقي")
        data.add("اللهم إني أسألك من فضلك ورحمتك، فإنه لا يملكها إلا أنت")
        data.add("اللهم إني أعوذ بك من الفقر، والقلة، والذلة، وأعوذ بك من أن أظلم أو أظلم")
        data.add("اللهم إني أعوذ بك من جار السوء في دار المقامة؛ فإن جار البادية يتحول")
        data.add("اللهم إني أعوذ بك أن أشرك بك وأنا أعلم، وأستغفرك لما لا أعلم")
        data.add("اللهم انفعني بما علمتني، وعلمني ما ينفعني، وزدني علماً")
        data.add("اللهم إني أسألك علماً نافعاً، ورزقاً طيباً، وعملاً متقبلاً")
        data.add("رب اغفر لي، وتب علي، إنك أنت التواب الغفور")
        data.add("اللهم إني أعوذ بك من البخل، والجبن، وسوء العمر، وفتنة الصدر، وعذاب القبر")
        data.add("اللهم رب جبرائيل، وميكائيل، ورب إسرافيل، أعوذ بك من حر النار ومن عذاب القبر")
        data.add("اللهم ألهمني رشدي، وأعذني من شر نفسي")
        data.add("اللهم إني أسألك علماً نافعاً، وأعوذ بك من علم لا ينفع")
        data.add("اللهم جنبني منكرات الأخلاق، والأهواء، والأعمال، والأدواء")
        data.add("اللهم قنعني بما رزقتني، وبارك لي فيه، واخلف عليّ كل غائبة لي بخير")
        data.add("اللهم أعنا على ذكرك، وشكرك، وحسن عبادتك")
        data.add("اللهم إني أعوذ بك من غلبة الدين، وغلبة العدو، وشماتة الأعداء")
        data.add("اللهم اغفر لي، واهدني، وارزقني، وعافني، أعوذ بالله من ضيق المقام يوم القيامة")
        data.add("اللهم متعني بسمعي، وبصري، واجعلهما الوارث مني، وانصرني على من يظلمني، وخذ منه بثأري")
        data.add("اللهم إني أسألك عيشة نقية، وميتة سوية، ومرداً غير مخز ولا فاضح")
        data.add("اللهم زدنا ولا تنقصنا، وأكرمنا ولا تهنا، وأعطنا ولا تحرمنا، وآثرنا ولا تؤثر علينا، وأرضنا وارض عنا")
        data.add("اللهم أحسنت خَلقي فأحسن خُلُقي")
        data.add(" اللهم اكفنى بحلالك عن حرامك وأعننى بفضلك عمن سواك")
        data.add("اللهم قنى عذابك يوم تبعث عبادك")
        data.add("يا حى ياقيوم برحمتك استغيث")
        data.add("اللهم انى اعوذ بك من شر ماعملت ومن شر مالم اعمل")
        var i = share.getInt(SettingSystemString.ITEM_DATA, 0)
        if (i == data.size) {
            i = 0
        }
        val content = data[i]
        editor.putInt(
            SettingSystemString.ITEM_DATA,
            share.getInt(SettingSystemString.ITEM_DATA, 0) + 1
        ).apply()

        return content
    }


}
