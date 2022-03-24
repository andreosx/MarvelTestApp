package com.test.marveltestapp.util;

import com.test.marveltestapp.data.dto.Character;

public class ImageUtil {

    public static String getUrlImg(Character character, String size) {
        String imgUrl = character.getThumbnail().getPath()+"/"+size+"."+character.getThumbnail().getExtension();
        imgUrl = imgUrl.replace("http","https");
        return imgUrl;
    }

}
