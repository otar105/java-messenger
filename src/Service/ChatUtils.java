package Service;

import DB.*;
import DB.UserDao;

import java.sql.SQLException;
import java.util.List;

public class ChatUtils {
    private ChatDao chatDao;

    public ChatUtils() {
        chatDao = new ChatDao();
    }

    public int add_message(Chat chat) throws SQLException {
        chatDao.create(chat);
        return 0;
    }
    public int check(int chatid) throws SQLException {
        int count = chatDao.check(chatid);
        return count;
    }
    public List<Chat> read(int chatid) throws SQLException {
        List<Chat> chats= null;
        chats = chatDao.read(chatid);
        return chats;
    }
}
