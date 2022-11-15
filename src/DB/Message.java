package DB;

public class Message {
    private int ID;
    private String messageText;
    private String userIdEmail;
    private String chatId;
    private String sendDate;

    public Message(int ID, String messageText, String userIdEmail, String chatId, String sendDate) {
        this.ID = ID;
        this.messageText = messageText;
        this.userIdEmail = userIdEmail;
        this.chatId = chatId;
        this.sendDate = sendDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUserIdEmail() {
        return userIdEmail;
    }

    public void setUserIdEmail(String userIdEmail) {
        this.userIdEmail = userIdEmail;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}
