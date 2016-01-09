//
//  AndroidUntils.java
//  MallCoreLib
//
//  Created by Guo Li on 15-10-28.
//  Copyright (c) 2014 JunQian. All rights reserved.
//

package com.example.gululu.icreatorsdksampler;

import android.os.Environment;

import java.io.File;

/**
 * 在SD存储器上设置一个路径
 * 从集成的角度考虑合并至了@link iCreatorSDKLib getAndroidSDPath()
 * 区别库里面自带的getSDPath()方法
 */
public class AndroidUntils
{
	public static String getSDPath()
	{
		String strSDPath = Environment.getExternalStorageDirectory().toString() + "/iCreatorDownLoad/";
		File destDir = new File(strSDPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return strSDPath;
	}
}

