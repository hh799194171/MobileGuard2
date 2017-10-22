package cn.edu.gdmec.android.mobileguard.m2theftguard.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mobileguard.m2theftguard.entity.ContactInfo;

/**
 * Created by asus on 2017/10/22.
 */

public class ContactlnfoParser {
    public static List<ContactInfo> getSystemContact(Context context){
        ContentResolver resolver=context.getContentResolver();
        Uri uri= Uri.parse("content://com.android.contacts/raw_contacts");
        Uri datauri=Uri.parse("content://com.android.contacts/data");
        List<ContactInfo> infos=new ArrayList<ContactInfo>();

        Cursor cursor=resolver.query(uri,new String[]{"contact_id"},null,null,null);
        while(cursor.moveToNext()){
            String id=cursor.getString(0);
            if(id!=null){
                System.out.println("联系人id:"+id);
                ContactInfo info=new ContactInfo();
                info.id=id;
                Cursor dataCursor=resolver.query(datauri,new String[]{
                        "datal","mimetype"},"raw_contact_id=?",new String[]{id},null);
                while (dataCursor.moveToNext()){
                    String datal=dataCursor.getString(0);
                    String mimetype=dataCursor.getString(1);
                    if("vnd.android.cursor.item/name".equals(mimetype)){
                        System.out.println("姓名="+datal);
                        info.phone=datal;


                    }
                }
                if(TextUtils.isEmpty(info.name)&&TextUtils.isEmpty(info.phone))
                    continue;
                infos.add(info);
                dataCursor.close();
            }
        }
cursor.close();
        return infos;

    }
    public static List<ContactInfo>getSimContacts(Context context) {
        Uri uri = Uri.parse("content://icc/adn");
    List<ContactInfo>infos=new ArrayList<ContactInfo>();
        Cursor mCursor=context.getContentResolver().query(uri,null,null,null,null);
        if(mCursor!=null){
            while(mCursor.moveToNext()){
                ContactInfo info=new ContactInfo();
                int numberFieldColumnIndex=mCursor.getColumnIndex("number");

                info.phone=mCursor.getString(numberFieldColumnIndex);
                infos.add(info);

            }
        }
    mCursor.close();
        return infos;
    }
}
