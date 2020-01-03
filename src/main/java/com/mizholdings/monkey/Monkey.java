package com.mizholdings.monkey;

import cn.hutool.core.io.FileUtil;
import com.mizholdings.monkey.adbDevice.AdbDevice;
import com.mizholdings.monkey.adbDevice.AndroidKeyCode;
import com.mizholdings.monkey.adbDevice.XuImage;
import com.mizholdings.monkey.adbDevice.utils.ImageUtils;

import java.io.File;
import java.util.Random;

public class Monkey {

    private AdbDevice device = new AdbDevice();
    private Random random = new Random();


    public Boolean openApp(String packageName, String Activity) {
        if (device.isInstalled(packageName)) {
            device.startActivity(getComponent(packageName, Activity));
            device.waitDevice();
            return true;
        }
        return false;
    }

    public void installApp(String appPath) {
        device.installApp(appPath);
    }


    /**
     * 当前应用是否是在指定应用上
     */
    public Boolean isInPackageName(String packageName) {
        return packageName.equals(device.getCurrentPackageName());
    }

    /**
     * 返回按钮
     */
    public void back() {
        device.sendKeyEvent(AndroidKeyCode.BACK);
    }

    /**
     * 随机点击事件
     */
    public void click() {
        double y = random.nextDouble();
        y = y + 0.1 > 1 ? y : y + 0.1;
        device.tap(random.nextDouble(), y);
    }

    /**
     * 随机滑动事件
     */
    public void swip() {
        device.swipe(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), 500);
    }

    public void action(String action) {
        XuImage image = device.getSceenshot();
        image.writeToFile(image.getTemp(), action);

        click();


    }


    private String getComponent(String packageName, String Activity) {
        return packageName + "/" + Activity;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        Monkey monkey = new Monkey();
        String packageName = "com.mizholdings.me2.cloundclass";
        String Activity = "com.mizholdings.orange.function.splash.SplashActivity";

        monkey.openApp(packageName, Activity);
        System.out.println(new File("screenshot").getAbsolutePath());


        int flag = 0;
        int max = 5;
//
//        while (true) {
//
//            monkey.sleep(500);
//            if (monkey.isInPackageName(packageName)) {
//
//                monkey.click();
//            } else {
//                if (flag < max) {
//                    flag++;
//                    monkey.back();
//                } else {
//                    flag = 0;
//                    monkey.openApp(packageName, Activity);
//                }
//            }
//        }
    }
}
