package singleton;



public class SocketClient {
//    static 메소드에서 사용되기 때문에 static으로 변수를 설정해주어야 한다.
    private static SocketClient  socketClient = null;
    private SocketClient(){

    }
    public static SocketClient getInstance(){
        if(socketClient==null){
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect(){
        System.out.println("connect");
    }
}
