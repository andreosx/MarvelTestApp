package com.test.marveltestapp.util;

import com.test.marveltestapp.data.dto.character.Result;

public class ImageUtil {

    public static String getUrlImg(Result characterDto, String size) {
        String imgUrl = characterDto.getThumbnail().getPath()+"/"+size+"."+ characterDto.getThumbnail().getExtension();
        imgUrl = imgUrl.replace("http","https");
        return imgUrl;
    }

}
