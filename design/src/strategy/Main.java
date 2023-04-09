package strategy;

public class Main {
    public static void main(String[] args) {
        Encoder encoder = new Encoder();

//        base64
        EncodingStrategy base64 = new Base64Strategy();

//        normal
        EncodingStrategy normal = new NormalStrategy();

        EncodingStrategy append = new AppendStrategy();

        String message = "hello java";
//     전략 설정
        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

//    전략 설정
        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(append);
        String appendResult = encoder.getMessage(message);
        System.out.println(appendResult);


    }
}
