package proxy;

import adapter.*;
import aop.AopBrowser;

import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        /*Browser browser = new Browser("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
*/
        /*IBrowser browser = new BrowserPoxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();*/

//        멀티 쓰레드에서 synchronize 없이 사용가능
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

//        인터페이스를 람다식으로 표현하려고하면 해당 인터페이스가 하나의 메소드만 가져야한다.
        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                ()-> {
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now = System.currentTimeMillis();
                    end.set(now-start.get());
                });
        aopBrowser.show();
        System.out.println("loading time:"+end.get());

        aopBrowser.show();
        System.out.println("loading time:"+end.get());
    }



}