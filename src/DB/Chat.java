package DB;
public class Chat {
    private int ID;
    private String CreatorName;
    private String Message;
    private int ChatID;

    public Chat(String creatorIDEmail, String Message,int chatID) {
        this.CreatorName = creatorIDEmail;
        this.Message = Message;
        this.ChatID = chatID;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorIDEmail) {
        CreatorName = creatorIDEmail;
    }

    public int getChatID() {
        return ChatID;
    }

    public void setChatID(int chatName) {
        ChatID = chatName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}