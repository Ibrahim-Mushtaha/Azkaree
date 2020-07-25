package com.example.azkaree.db

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.azkaree.model.Athker

class DatabaseHelper(var context: Context) :
    SQLiteOpenHelper(context, DATABSE_NAME, null, VERSION) {

    companion object {
        val DATABSE_NAME = "Version 1.0.1"
        val VERSION = 6
    }

    val dbw = writableDatabase

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Athker.CREATE_TABLE)
        insertAthkerAlsabah(db)
        insertAthkerAlmasa(db)
        insertAthkersleepless(db)
        insertAthkerALiistiqaz(db)
        insertAthkerALmosque(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Athker.TABLE_NAME}")
        onCreate(db)
    }


    fun getAllData(): ArrayList<Athker> {
        val data = ArrayList<Athker>()

        val query = dbw.rawQuery("SELECT * FROM ${Athker.TABLE_NAME}", null)
        query.moveToFirst()
        while (!query.isAfterLast) {
            data.add(
                Athker(
                    query.getInt(query.getColumnIndex(Athker.COL_ID)),
                    query.getString(query.getColumnIndex(Athker.COL_CONTENT)),
                    query.getInt(query.getColumnIndex(Athker.COL_COUNTER)),
                    query.getInt(query.getColumnIndex(Athker.COL_STATUS)),
                    query.getInt(query.getColumnIndex(Athker.COL_TYPE))
                )
            )
            query.moveToNext()
        }

        query.close()
        return data
    }
    fun setUpateAthker(id:Int,status:Int):Boolean{
        val cv =ContentValues()
        cv.put(Athker.COL_STATUS,status)
        return dbw.update(Athker.TABLE_NAME,cv,"${Athker.COL_ID}=?", arrayOf(id.toString()))>0
    }

    private fun insertAthkersleepless(db: SQLiteDatabase) {
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'بِاسْمِكَ رَبِّـي وَضَعْـتُ جَنْـبي ، وَبِكَ أَرْفَعُـه، فَإِن أَمْسَـكْتَ نَفْسـي فارْحَـمْها ، وَإِنْ أَرْسَلْتَـها فاحْفَظْـها بِمـا تَحْفَـظُ بِه عِبـادَكَ الصّـالِحـين.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'اللّهُـمَّ إِنَّـكَ خَلَـقْتَ نَفْسـي وَأَنْـتَ تَوَفّـاهـا لَكَ ممَـاتـها وَمَحْـياها ، إِنْ أَحْيَيْـتَها فاحْفَظْـها ، وَإِنْ أَمَتَّـها فَاغْفِـرْ لَـها . اللّهُـمَّ إِنَّـي أَسْـأَلُـكَ العـافِـيَة.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','3'," +
                    "'اللّهُـمَّ قِنـي عَذابَـكَ يَـوْمَ تَبْـعَثُ عِبـادَك.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'بِاسْـمِكَ اللّهُـمَّ أَمـوتُ وَأَحْـيا.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'الـحَمْدُ للهِ الَّذي أَطْـعَمَنا وَسَقـانا، وَكَفـانا، وَآوانا، فَكَـمْ مِمَّـنْ لا كـافِيَ لَـهُ وَلا مُـؤْوي.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'اللّهُـمَّ عالِـمَ الغَـيبِ وَالشّـهادةِ فاطِـرَ السّماواتِ وَالأرْضِ رَبَّ كُـلِّ شَـيءٍ وَمَليـكَه، أَشْهـدُ أَنْ لا إِلـهَ إِلاّ أَنْت، أَعـوذُ بِكَ مِن شَـرِّ نَفْسـي، وَمِن شَـرِّ الشَّيْـطانِ وَشِـرْكِه، وَأَنْ أَقْتَـرِفَ عَلـى نَفْسـي سوءاً أَوْ أَجُـرَّهُ إِلـى مُسْـلِم.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'اللّهُـمَّ أَسْـلَمْتُ نَفْـسي إِلَـيْكَ، وَفَوَّضْـتُ أَمْـري إِلَـيْكَ، وَوَجَّـهْتُ وَجْـهي إِلَـيْكَ، وَأَلْـجَـاْتُ ظَهـري إِلَـيْكَ، رَغْبَـةً وَرَهْـبَةً إِلَـيْكَ، لا مَلْجَـأَ وَلا مَنْـجـا مِنْـكَ إِلاّ إِلَـيْكَ، آمَنْـتُ بِكِتـابِكَ الّـذي أَنْزَلْـتَ وَبِنَبِـيِّـكَ الّـذي أَرْسَلْـت.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','33'," +
                    "'سُبْحَانَ اللَّهِ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','33'," +
                    "'الْحَمْدُ لِلَّهِ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','33'," +
                    "'اللَّهُ أَكْبَرُ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','3'," +
                    "'يجمع كفيه ثم ينفث فيهما والقراءة فيهما\u200F:\u200F \u200F{\u200Fقل هو الله أحد\u200F}\u200F و\u200F{\u200Fقل أعوذ برب الفلق\u200F}\u200F و\u200F{\u200Fقل أعوذ برب الناس\u200F}\u200F ومسح ما استطاع من الجسد يبدأ بهما على رأسه ووجه وما أقبل من جسده.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'سورة البقرة: أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ\n" +
                    "آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ. لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَسِينَا أَوْ أَخْطَأْنَا رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ.\n" +
                    "[البقرة 285 - 286]')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'آية الكرسى: أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ \n" +
                    "اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.  [البقرة 255]')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'أذكار من قلق في فراشه ولم ينم\n" +
                    "عن بريدة رضي الله عنه، قال\u200F:\u200F شكا خالد بن الوليد رضي الله عنه إلى النبي صلى الله عليه وسلم فقال\u200F:\u200F يا رسول الله\u200F!\u200F ما أنام الليل من الأرق، فقال النبي صلى الله عليه وسلم\u200F:\u200F \u200F\"\u200Fإذا أويت إلى فراشك فقل\u200F:\u200F اللهم رب السموات السبع وما أظلت، ورب الأرضين وما أقلت، ورب الشياطين وما أضلت، كن لي جارا من خلقك كلهم جميعا أن يفرط علي أحد منهم أو أن يبغي علي، عز جارك، وجل ثناؤك ولا إله غيرك، ولا إله إلا أنت\u200F\"\n" +
                    "عن عمرو بن شعيب، عن أبيه، عن جده: أن رسول الله صلى الله عليه وسلم كان يعلمهم من الفزع كلمات\u200F:\u200F \u200F\"\u200Fأعوذ بكلمات الله التامة من غضبه وشر عباده، ومن همزات الشياطين وأن يحضرون\u200F\"')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('3','1'," +
                    "'أذكار الأحلام\n" +
                    "عن أبي قتادة رضي الله عنه قال\u200F:\u200F قال رسول الله صلى الله عليه وسلم\u200F:\u200F \u200F\"\u200Fالرؤيا الصالحة\u200F\"\u200F وفي رواية \u200F\"\u200Fالرؤيا الحسنة من الله، والحلم من الشيطان، فمن رأى شيئا يكرهه فلينفث عن شماله ثلاثا وليتعوذ من الشيطان، فإنها لا تضره\u200F\".')"
        )

    }


    private fun insertAthkerALiistiqaz(db: SQLiteDatabase){
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('4','1'," +
                    "'الحَمْـدُ لِلّهِ الّذي أَحْـيانا بَعْـدَ ما أَماتَـنا وَإليه النُّـشور. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('4','1'," +
                    "'الحمدُ للهِ الذي عافاني في جَسَدي وَرَدّ عَليّ روحي وَأَذِنَ لي بِذِكْرِه.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('4','1'," +
                    "'لا إلهَ إلاّ اللّهُ وَحْـدَهُ لا شَـريكَ له، لهُ المُلـكُ ولهُ الحَمـد، وهوَ على كلّ شيءٍ قدير، سُـبْحانَ اللهِ، والحمْـدُ لله ، ولا إلهَ إلاّ اللهُ واللهُ أكبَر، وَلا حَولَ وَلا قوّة إلاّ باللّهِ العليّ العظيم. رَبِّ اغْفرْ لي.')"
        )

    }
    private fun insertAthkerAlmasa(db: SQLiteDatabase) {
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ : {اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {قُلْ هُوَ ٱللَّهُ أَحَدٌ، ٱللَّهُ ٱلصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌُۢ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ. لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَّسِينَآ أَوْ أَخْطَأْنَا رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ.}')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {قُلْ أَعُوذُ بِرَبِّ ٱلْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِى ٱلْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {قُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ، مَلِكِ ٱلنَّاسِ، إِلَٰهِ ٱلنَّاسِ، مِن شَرِّ ٱلْوَسْوَاسِ ٱلْخَنَّاسِ، ٱلَّذِى يُوَسْوِسُ فِى صُدُورِ ٱلنَّاسِ، مِنَ ٱلْجِنَّةِ وَٱلنَّاسِ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'أَمْسَيْـنا وَأَمْسـى المـلكُ لله وَالحَمدُ لله ، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُـلكُ ولهُ الحَمْـد، وهُوَ على كلّ شَيءٍ قدير ، رَبِّ أسْـأَلُـكَ خَـيرَ ما في هـذهِ اللَّـيْلَةِ وَخَـيرَ ما بَعْـدَهـا ، وَأَعـوذُ بِكَ مِنْ شَـرِّ ما في هـذهِ اللَّـيْلةِ وَشَرِّ ما بَعْـدَهـا ، رَبِّ أَعـوذُبِكَ مِنَ الْكَسَـلِ وَسـوءِ الْكِـبَر ، رَبِّ أَعـوذُ بِكَ مِنْ عَـذابٍ في النّـارِ وَعَـذابٍ في القَـبْر. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ. لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَّسِينَآ أَوْ أَخْطَأْنَا رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'اللّهـمَّ أَنْتَ رَبِّـي لا إلهَ إلاّ أَنْتَ ، خَلَقْتَنـي وَأَنا عَبْـدُك ، وَأَنا عَلـى عَهْـدِكَ وَوَعْـدِكَ ما اسْتَـطَعْـت ، أَعـوذُبِكَ مِنْ شَـرِّ ما صَنَـعْت ، أَبـوءُ لَـكَ بِنِعْـمَتِـكَ عَلَـيَّ وَأَبـوءُ بِذَنْـبي فَاغْفـِرْ لي فَإِنَّـهُ لا يَغْـفِرُ الذُّنـوبَ إِلاّ أَنْتَ .')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'رَضيـتُ بِاللهِ رَبَّـاً وَبِالإسْلامِ ديـناً وَبِمُحَـمَّدٍ صلى الله عليه وسلم نَبِيّـاً. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','4'," +
                    "'اللّهُـمَّ إِنِّـي أَمسيتُ أُشْـهِدُك ، وَأُشْـهِدُ حَمَلَـةَ عَـرْشِـك ، وَمَلَائِكَتَكَ ، وَجَمـيعَ خَلْـقِك ، أَنَّـكَ أَنْـتَ اللهُ لا إلهَ إلاّ أَنْـتَ وَحْـدَكَ لا شَريكَ لَـك ، وَأَنَّ ُ مُحَمّـداً عَبْـدُكَ وَرَسـولُـك.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'اللّهُـمَّ ما أَمسى بي مِـنْ نِعْـمَةٍ أَو بِأَحَـدٍ مِـنْ خَلْـقِك ، فَمِـنْكَ وَحْـدَكَ لا شريكَ لَـك ، فَلَـكَ الْحَمْـدُ وَلَـكَ الشُّكْـر.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','7'," +
                    "'حَسْبِـيَ اللّهُ لا إلهَ إلاّ هُوَ عَلَـيهِ تَوَكَّـلتُ وَهُوَ رَبُّ العَرْشِ العَظـيم.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'بِسـمِ اللهِ الذي لا يَضُـرُّ مَعَ اسمِـهِ شَيءٌ في الأرْضِ وَلا في السّمـاءِ وَهـوَ السّمـيعُ العَلـيم.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'اللّهُـمَّ بِكَ أَمْسَـينا وَبِكَ أَصْـبَحْنا، وَبِكَ نَحْـيا وَبِكَ نَمُـوتُ وَإِلَـيْكَ الْمَصِيرُ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'أَمْسَيْنَا عَلَى فِطْرَةِ الإسْلاَمِ، وَعَلَى كَلِمَةِ الإِخْلاَصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِينَا إبْرَاهِيمَ حَنِيفاً مُسْلِماً وَمَا كَانَ مِنَ المُشْرِكِينَ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'سُبْحـانَ اللهِ وَبِحَمْـدِهِ عَدَدَ خَلْـقِه ، وَرِضـا نَفْسِـه ، وَزِنَـةَ عَـرْشِـه ، وَمِـدادَ كَلِمـاتِـه.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'اللّهُـمَّ عافِـني في بَدَنـي ، اللّهُـمَّ عافِـني في سَمْـعي ، اللّهُـمَّ عافِـني في بَصَـري ، لا إلهَ إلاّ أَنْـتَ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'اللّهُـمَّ إِنّـي أَعـوذُ بِكَ مِنَ الْكُـفر ، وَالفَـقْر ، وَأَعـوذُ بِكَ مِنْ عَذابِ القَـبْر ، لا إلهَ إلاّ أَنْـتَ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ في الدُّنْـيا وَالآخِـرَة ، اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ في ديني وَدُنْـيايَ وَأهْـلي وَمالـي ، اللّهُـمَّ اسْتُـرْ عـوْراتي وَآمِـنْ رَوْعاتـي ، اللّهُـمَّ احْفَظْـني مِن بَـينِ يَدَيَّ وَمِن خَلْفـي وَعَن يَمـيني وَعَن شِمـالي ، وَمِن فَوْقـي ، وَأَعـوذُ بِعَظَمَـتِكَ أَن أُغْـتالَ مِن تَحْتـي.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'يَا حَيُّ يَا قيُّومُ بِرَحْمَتِكَ أسْتَغِيثُ أصْلِحْ لِي شَأنِي كُلَّهُ وَلاَ تَكِلْنِي إلَى نَفْسِي طَـرْفَةَ عَيْنٍ.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'أَمْسَيْنا وَأَمْسَى الْمُلْكُ للهِ رَبِّ الْعَالَمَيْنِ، اللَّهُمَّ إِنَّي أسْأَلُكَ خَيْرَ هَذَه اللَّيْلَةِ فَتْحَهَا ونَصْرَهَا، ونُوْرَهَا وبَرَكَتهَا، وَهُدَاهَا، وَأَعُوذُ بِكَ مِنْ شَرِّ مَا فيهِا وَشَرَّ مَا بَعْدَهَا.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'اللّهُـمَّ عالِـمَ الغَـيْبِ وَالشّـهادَةِ فاطِـرَ السّماواتِ وَالأرْضِ رَبَّ كـلِّ شَـيءٍ وَمَليـكَه ، أَشْهَـدُ أَنْ لا إِلـهَ إِلاّ أَنْت ، أَعـوذُ بِكَ مِن شَـرِّ نَفْسـي وَمِن شَـرِّ الشَّيْـطانِ وَشِرْكِهِ ، وَأَنْ أَقْتَـرِفَ عَلـى نَفْسـي سوءاً أَوْ أَجُـرَّهُ إِلـى مُسْـلِم.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'أَعـوذُ بِكَلِمـاتِ اللّهِ التّـامّـاتِ مِنْ شَـرِّ ما خَلَـق.')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','10'," +
                    "'اللَّهُمَّ صَلِّ وَسَلِّمْ وَبَارِكْ على نَبِيِّنَا مُحمَّد. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'اللَّهُمَّ إِنَّا نَعُوذُ بِكَ مِنْ أَنْ نُشْرِكَ بِكَ شَيْئًا نَعْلَمُهُ ، وَنَسْتَغْفِرُكَ لِمَا لَا نَعْلَمُهُ.  ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ الْهَمِّ وَالْحَزَنِ، وَأَعُوذُ بِكَ مِنْ الْعَجْزِ وَالْكَسَلِ، وَأَعُوذُ بِكَ مِنْ الْجُبْنِ وَالْبُخْلِ، وَأَعُوذُ بِكَ مِنْ غَلَبَةِ الدَّيْنِ، وَقَهْرِ الرِّجَالِ. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'أسْتَغْفِرُ اللهَ العَظِيمَ الَّذِي لاَ إلَهَ إلاَّ هُوَ، الحَيُّ القَيُّومُ، وَأتُوبُ إلَيهِ. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','3'," +
                    "'يَا رَبِّ , لَكَ الْحَمْدُ كَمَا يَنْبَغِي لِجَلَالِ وَجْهِكَ , وَلِعَظِيمِ سُلْطَانِكَ. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','100'," +
                    "'لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءِ قَدِيرِ. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','1'," +
                    "'اللَّهُمَّ أَنْتَ رَبِّي لا إِلَهَ إِلا أَنْتَ ، عَلَيْكَ تَوَكَّلْتُ ، وَأَنْتَ رَبُّ الْعَرْشِ الْعَظِيمِ , مَا شَاءَ اللَّهُ كَانَ ، وَمَا لَمْ يَشَأْ لَمْ يَكُنْ ، وَلا حَوْلَ وَلا قُوَّةَ إِلا بِاللَّهِ الْعَلِيِّ الْعَظِيمِ , أَعْلَمُ أَنَّ اللَّهَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ ، وَأَنَّ اللَّهَ قَدْ أَحَاطَ بِكُلِّ شَيْءٍ عِلْمًا , اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ شَرِّ نَفْسِي ، وَمِنْ شَرِّ كُلِّ دَابَّةٍ أَنْتَ آخِذٌ بِنَاصِيَتِهَا ، إِنَّ رَبِّي عَلَى صِرَاطٍ مُسْتَقِيمٍ. ')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('2','100'," +
                    "'سُبْحـانَ اللهِ وَبِحَمْـدِهِ.')"
        )


    }
    private fun insertAthkerAlsabah(db: SQLiteDatabase) {
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'أَعُوذُ بِاللهِ مِنْ الشَّيْطَانِ الرَّجِيمِ : {اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {قُلْ هُوَ ٱللَّهُ أَحَدٌ، ٱللَّهُ ٱلصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌُۢ.}')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {قُلْ أَعُوذُ بِرَبِّ ٱلْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِى ٱلْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'بِسْمِ اللهِ الرَّحْمنِ الرَّحِيمِ : {قُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ، مَلِكِ ٱلنَّاسِ، إِلَٰهِ ٱلنَّاسِ، مِن شَرِّ ٱلْوَسْوَاسِ ٱلْخَنَّاسِ، ٱلَّذِى يُوَسْوِسُ فِى صُدُورِ ٱلنَّاسِ، مِنَ ٱلْجِنَّةِ وَٱلنَّاسِ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{أَصْـبَحْنا وَأَصْـبَحَ المُـلْكُ لله وَالحَمدُ لله ، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُـلكُ ولهُ الحَمْـد، وهُوَ على كلّ شَيءٍ قدير ، رَبِّ أسْـأَلُـكَ خَـيرَ ما في هـذا اليوم وَخَـيرَ ما بَعْـدَه ، وَأَعـوذُ بِكَ مِنْ شَـرِّ ما في هـذا اليوم وَشَرِّ ما بَعْـدَه، رَبِّ أَعـوذُبِكَ مِنَ الْكَسَـلِ وَسـوءِ الْكِـبَر ، رَبِّ أَعـوذُ بِكَ مِنْ عَـذابٍ في النّـارِ وَعَـذابٍ في القَـبْر.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللّهـمَّ أَنْتَ رَبِّـي لا إلهَ إلاّ أَنْتَ ، خَلَقْتَنـي وَأَنا عَبْـدُك ، وَأَنا عَلـى عَهْـدِكَ وَوَعْـدِكَ ما اسْتَـطَعْـت ، أَعـوذُبِكَ مِنْ شَـرِّ ما صَنَـعْت ، أَبـوءُ لَـكَ بِنِعْـمَتِـكَ عَلَـيَّ وَأَبـوءُ بِذَنْـبي فَاغْفـِرْ لي فَإِنَّـهُ لا يَغْـفِرُ الذُّنـوبَ إِلاّ أَنْتَ .}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{رَضيـتُ بِاللهِ رَبَّـاً وَبِالإسْلامِ ديـناً وَبِمُحَـمَّدٍ صلى الله عليه وسلم نَبِيّـاً.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','4'," +
                    "'{اللّهُـمَّ إِنِّـي أَصْبَـحْتُ أُشْـهِدُك ، وَأُشْـهِدُ حَمَلَـةَ عَـرْشِـك ، وَمَلَائِكَتَكَ ، وَجَمـيعَ خَلْـقِك ، أَنَّـكَ أَنْـتَ اللهُ لا إلهَ إلاّ أَنْـتَ وَحْـدَكَ لا شَريكَ لَـك ، وَأَنَّ ُ مُحَمّـداً عَبْـدُكَ وَرَسـولُـك.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللّهُـمَّ ما أَصْبَـَحَ بي مِـنْ نِعْـمَةٍ أَو بِأَحَـدٍ مِـنْ خَلْـقِك ، فَمِـنْكَ وَحْـدَكَ لا شريكَ لَـك ، فَلَـكَ الْحَمْـدُ وَلَـكَ الشُّكْـر.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','7'," +
                    "'{حَسْبِـيَ اللّهُ لا إلهَ إلاّ هُوَ عَلَـيهِ تَوَكَّـلتُ وَهُوَ رَبُّ العَرْشِ العَظـيم.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{بِسـمِ اللهِ الذي لا يَضُـرُّ مَعَ اسمِـهِ شَيءٌ في الأرْضِ وَلا في السّمـاءِ وَهـوَ السّمـيعُ العَلـيم.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللّهُـمَّ بِكَ أَصْـبَحْنا وَبِكَ أَمْسَـينا ، وَبِكَ نَحْـيا وَبِكَ نَمُـوتُ وَإِلَـيْكَ النُّـشُور.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{أَصْبَـحْـنا عَلَى فِطْرَةِ الإسْلاَمِ، وَعَلَى كَلِمَةِ الإِخْلاَصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِينَا إبْرَاهِيمَ حَنِيفاً مُسْلِماً وَمَا كَانَ مِنَ المُشْرِكِينَ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{سُبْحـانَ اللهِ وَبِحَمْـدِهِ عَدَدَ خَلْـقِه ، وَرِضـا نَفْسِـه ، وَزِنَـةَ عَـرْشِـه ، وَمِـدادَ كَلِمـاتِـه. }')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{اللّهُـمَّ عافِـني في بَدَنـي ، اللّهُـمَّ عافِـني في سَمْـعي ، اللّهُـمَّ عافِـني في بَصَـري ، لا إلهَ إلاّ أَنْـتَ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{اللّهُـمَّ إِنّـي أَعـوذُ بِكَ مِنَ الْكُـفر ، وَالفَـقْر ، وَأَعـوذُ بِكَ مِنْ عَذابِ القَـبْر ، لا إلهَ إلاّ أَنْـتَ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ في الدُّنْـيا وَالآخِـرَة ، اللّهُـمَّ إِنِّـي أسْـأَلُـكَ العَـفْوَ وَالعـافِـيةَ في ديني وَدُنْـيايَ وَأهْـلي وَمالـي ، اللّهُـمَّ اسْتُـرْ عـوْراتي وَآمِـنْ رَوْعاتـي ، اللّهُـمَّ احْفَظْـني مِن بَـينِ يَدَيَّ وَمِن خَلْفـي وَعَن يَمـيني وَعَن شِمـالي ، وَمِن فَوْقـي ، وَأَعـوذُ بِعَظَمَـتِكَ أَن أُغْـتالَ مِن تَحْتـي.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{يَا حَيُّ يَا قيُّومُ بِرَحْمَتِكَ أسْتَغِيثُ أصْلِحْ لِي شَأنِي كُلَّهُ وَلاَ تَكِلْنِي إلَى نَفْسِي طَـرْفَةَ عَيْنٍ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{أَصْبَـحْـنا وَأَصْبَـحْ المُـلكُ للهِ رَبِّ العـالَمـين ، اللّهُـمَّ إِنِّـي أسْـأَلُـكَ خَـيْرَ هـذا الـيَوْم ، فَـتْحَهُ ، وَنَصْـرَهُ ، وَنـورَهُ وَبَـرَكَتَـهُ ، وَهُـداهُ ، وَأَعـوذُ بِـكَ مِـنْ شَـرِّ ما فـيهِ وَشَـرِّ ما بَعْـدَه.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللّهُـمَّ عالِـمَ الغَـيْبِ وَالشّـهادَةِ فاطِـرَ السّماواتِ وَالأرْضِ رَبَّ كـلِّ شَـيءٍ وَمَليـكَه ، أَشْهَـدُ أَنْ لا إِلـهَ إِلاّ أَنْت ، أَعـوذُ بِكَ مِن شَـرِّ نَفْسـي وَمِن شَـرِّ الشَّيْـطانِ وَشِرْكِهِ ، وَأَنْ أَقْتَـرِفَ عَلـى نَفْسـي سوءاً أَوْ أَجُـرَّهُ إِلـى مُسْـلِم.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{أَعـوذُ بِكَلِمـاتِ اللّهِ التّـامّـاتِ مِنْ شَـرِّ ما خَلَـق.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','10'," +
                    "'{اللَّهُمَّ صَلِّ وَسَلِّمْ وَبَارِكْ على نَبِيِّنَا مُحمَّد.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{اللَّهُمَّ إِنَّا نَعُوذُ بِكَ مِنْ أَنْ نُشْرِكَ بِكَ شَيْئًا نَعْلَمُهُ ، وَنَسْتَغْفِرُكَ لِمَا لَا نَعْلَمُهُ. }')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{للَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ الْهَمِّ وَالْحَزَنِ، وَأَعُوذُ بِكَ مِنْ الْعَجْزِ وَالْكَسَلِ، وَأَعُوذُ بِكَ مِنْ الْجُبْنِ وَالْبُخْلِ، وَأَعُوذُ بِكَ مِنْ غَلَبَةِ الدَّيْنِ، وَقَهْرِ الرِّجَالِ. }')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{أسْتَغْفِرُ اللهَ العَظِيمَ الَّذِي لاَ إلَهَ إلاَّ هُوَ، الحَيُّ القَيُّومُ، وَأتُوبُ إلَيهِ. }')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','3'," +
                    "'{يَا رَبِّ , لَكَ الْحَمْدُ كَمَا يَنْبَغِي لِجَلَالِ وَجْهِكَ , وَلِعَظِيمِ سُلْطَانِكَ. }')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللَّهُمَّ إِنِّي أَسْأَلُكَ عِلْمًا نَافِعًا، وَرِزْقًا طَيِّبًا، وَعَمَلًا مُتَقَبَّلًا. }')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','1'," +
                    "'{اللَّهُمَّ أَنْتَ رَبِّي لا إِلَهَ إِلا أَنْتَ ، عَلَيْكَ تَوَكَّلْتُ ، وَأَنْتَ رَبُّ الْعَرْشِ الْعَظِيمِ , مَا شَاءَ اللَّهُ كَانَ ، وَمَا لَمْ يَشَأْ لَمْ يَكُنْ ، وَلا حَوْلَ وَلا قُوَّةَ إِلا بِاللَّهِ الْعَلِيِّ الْعَظِيمِ , أَعْلَمُ أَنَّ اللَّهَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ ، وَأَنَّ اللَّهَ قَدْ أَحَاطَ بِكُلِّ شَيْءٍ عِلْمًا , اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنْ شَرِّ نَفْسِي ، وَمِنْ شَرِّ كُلِّ دَابَّةٍ أَنْتَ آخِذٌ بِنَاصِيَتِهَا ، إِنَّ رَبِّي عَلَى صِرَاطٍ مُسْتَقِيمٍ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','100'," +
                    "'{لَا إلَه إلّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءِ قَدِيرِ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','100'," +
                    "'{سُبْحـانَ اللهِ وَبِحَمْـدِهِ.}')"
        )
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('1','100'," +
                    "'{أسْتَغْفِرُ اللهَ وَأتُوبُ إلَيْهِ}')"
        )
    }

    private fun insertAthkerALmosque(db: SQLiteDatabase) {
        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('5','1'," +
                    "'دُعَاءُ الذَّهَابِ إلَى المَسْجِدِ\n" +
                    " اللّهُـمَّ اجْعَـلْ في قَلْبـي نورا ، وَفي لِسـاني نورا، وَاجْعَـلْ في سَمْعي نورا، وَاجْعَـلْ في بَصَري نورا، وَاجْعَـلْ مِنْ خَلْفي نورا، وَمِنْ أَمامـي نورا، وَاجْعَـلْ مِنْ فَوْقـي نورا ، وَمِن تَحْتـي نورا .اللّهُـمَّ أَعْطِنـي نورا.')"
        )

        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('5','1'," +
                    "'دُعَاءُ دُخُولِ المَسْجِدِ\n" +
                    "يَبْدَأُ بِرِجْلِهِ اليُمْنَى، وَيَقُولُ:\n" +
                    "أَعوذُ باللهِ العَظيـم وَبِوَجْهِـهِ الكَرِيـم وَسُلْطـانِه القَديـم مِنَ الشّيْـطانِ الرَّجـيم، بِسْمِ اللَّهِ، وَالصَّلاةُ وَالسَّلامُ عَلَى رَسُولِ الله، اللّهُـمَّ افْتَـحْ لي أَبْوابَ رَحْمَتـِك.')"
        )


        db.execSQL(
            "insert into ${Athker.TABLE_NAME} (${Athker.COL_TYPE},${Athker.COL_COUNTER},${Athker.COL_CONTENT}) " +
                    "values ('5','1'," +
                    "'دُعَاءُ الخُرُوجِ مِنَ المَسْجِدِ\n" +
                    "يَبْدَأُ بِرِجْلِهِ الْيُسْرَى، وَيَقُولُ:\n" +
                    "بِسْـمِ اللَّـهِ وَالصَّلاةُ وَالسَّلامُ عَلَى رَسُولِ اللَّهِ، اللَّهُمَّ إنِّي أَسْأَلُكَ مِنْ فَضْلِكَ، اللَّهُمَّ اعْصِمْنِي مِنَ الشَّيْطَانِ الرَّجِيم.')"
        )
    }

}