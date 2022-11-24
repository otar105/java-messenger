package DB;
public class Chat {
    private int ID;
    private String CreatorIDEmail;
    private String ChatName;

    public Chat(int ID, String creatorIDEmail, String chatName) {
        this.ID = ID;
        CreatorIDEmail = creatorIDEmail;
        ChatName = chatName;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreatorIDEmail() {
        return CreatorIDEmail;
    }

    public void setCreatorIDEmail(String creatorIDEmail) {
        CreatorIDEmail = creatorIDEmail;
    }

    public String getChatName() {
        return ChatName;
    }

    public void setChatName(String chatName) {
        ChatName = chatName;
    }


}