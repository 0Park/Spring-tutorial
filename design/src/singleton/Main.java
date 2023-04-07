package singleton;

import adapter.*;
import singleton.AClazz;
import singleton.BClazz;
import singleton.SocketClient;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class Main {
    public static void main(String[] args) {
        /*AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

//        객체가 동일한지 확인
        System.out.println("두객체가 동일한가?");
        System.out.println(aClient.equals(bClient));*/
// 객체가 단 하나만 존재한다!!

        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airconditioner = new AirConditioner();
        Electronic110V adapter2= new SocketAdapter(airconditioner);
        connect(adapter2);
    }

    // 콘센트
    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}