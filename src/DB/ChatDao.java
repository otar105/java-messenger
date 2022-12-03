package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDao implements DAO<Chat> {

    @Override
    public int create(Chat chat) throws SQLException {
        Connection con = Database.connect();
        String sql = "insert into Chat(CreatorName, message, Chatid) values(?,?,?)";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setString(1,chat.getCreatorName());
        preparedStatement.setString(2,chat.getMessage());
        preparedStatement.setInt(3,chat.getChatID());

        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int check(int chatid) throws SQLException {
        Connection con = Database.connect();
        int count = 0;
        String sql = "select * from Chat where Chatid = "+chatid;
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            count++;
        }
        return count;
    }
    @Override
    public List<Chat> get() throws SQLException {
        return null;
    }

    public List<Chat> read(int chatid) throws SQLException {
        Connection con = Database.connect();
        List<Chat> chats= null;
        chats = new ArrayList<Chat>();
        String sql = "select * from Chat where Chatid = "+chatid;
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int ID = rs.getInt("ID");
            String creatorid = rs.getString("CreatorName");
            String message = rs.getString("message");
            int chatidd = rs.getInt("chatid");
            Chat chat = new Chat(creatorid, message,chatidd);
            chat.setID(ID);
            chats.add(chat);
        }
        return chats;
    }

    @Override
    public int edit(Chat item) throws SQLException {
        return 0;
    }

    @Override
    public int remove(Chat item) throws SQLException {
        return 0;
    }

}