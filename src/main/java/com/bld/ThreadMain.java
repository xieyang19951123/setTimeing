package com.bld;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadMain {

    //gdfgd
    private static  String test="DDgt9Sl7j2AxTwO0Cray";

    //充电桩944FDC8E0D84
    private static  String  powerBank =  "5yfLx78lsUzL5GPD4DxH";

    //光能145C65ABF498
    private static String lightPower = "IXC4XVdVtadqezYopWAg";

    //国家电网2C5C65ABF498
    private static String nationPower = "S2j4WIEerRfDZbdBkPy8";

    //储能584D65ABF498
    private static String savingPower = "feoy2kfs4Js5tH6Inu9o";

    public static void main(String[] args) {
        System.out.println("系统开始运作！cd");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);//创建线程池
        final int[] powerint = {2222,88,0,0};
        //充电桩产生的电
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("ChipID","944FDC8E0D84");
                Random random = new Random();
                int i = random.nextInt(8);
                i += 1;
                powerint[0] +=i;
                data.put("EN",powerint[0]);//模拟电量2分钟1-8
                data.put("IA",0);
               // data.put("")
                data.put("PT","1");
                data.put("RSSI","16");
                data.put("SN","2005");
                data.put("TimeStamp",new Date().getTime());
                data.put("UA","2248");

                HttpUtils.get(powerBank,data);
            }
        }, 1,2, TimeUnit.MINUTES);

        //光能产生的电
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("ChipID","145C65ABF498");
                Random random = new Random();
                int i = random.nextInt(8);
                i += 1;
                powerint[1]+=i;
                data.put("EN",powerint[1]+i);//模拟电量2分钟1-8
                data.put("IA",0);
                // data.put("")
                //判断是否为晚上
                if(MyDateUtils.senseDate("17:00","8:00")){
                    data.put("PT",0);
                }else{
                    data.put("PT","1");
                }
                data.put("RSSI","24");
                data.put("SN","289");
                data.put("TimeStamp",new Date().getTime());
                data.put("UA","2203");
                HttpUtils.get(lightPower,data);
            }
        }, 1,2, TimeUnit.MINUTES);

        //国家电网产生的电
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("ChipID","2C5C65ABF498");
                Random random = new Random();
                int i = random.nextInt(8);
                i += 1;
                powerint[2]+=i;
                data.put("EN",powerint[1]+i);//模拟电量2分钟1-8//模拟电量2分钟1-8
                data.put("IA",0);
                // data.put("")
                data.put("PT","1");
                data.put("RSSI","20");
                data.put("SN","293");
                data.put("TimeStamp",new Date().getTime());
                data.put("UA","2188");
                HttpUtils.get(nationPower,data);
            }
        }, 1,2, TimeUnit.MINUTES);

        //储能产生的电
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Map<String,Object> data = new HashMap<String, Object>();
                data.put("ChipID","584D65ABF498");
                Random random = new Random();
                int i = random.nextInt(8);
                i += 1;
                powerint[2]+=i;
                data.put("EN",powerint[2]);//模拟电量2分钟1-8
                data.put("IA",0);
                // data.put("")
                if(MyDateUtils.senseDate("17:00","8:00")){
                    data.put("PT","-1");
                }
                else if(MyDateUtils.senseDate("8:00","10:00")){
                    data.put("PT","1");
                }else{
                    data.put("PT","0");
                }
                data.put("RSSI","16");
                data.put("SN","2005");
                data.put("TimeStamp",new Date().getTime());
                data.put("UA","2248");
                HttpUtils.get(savingPower,data);
            }
        }, 1,2, TimeUnit.MINUTES);
    }
}
