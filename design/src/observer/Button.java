package observer;

public class Button {
    private  String name;
    private iButtonListener  buttonListener;

    public Button(String name){
        this.name = name;
    }

    public void click(String message){
        buttonListener.clickEvent(message);
    }

    public void addListener(iButtonListener buttonListener){
        this.buttonListener=buttonListener;
    }
}
