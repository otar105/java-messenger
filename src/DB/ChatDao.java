package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ChatDao implements DAO<Chat> {

    @Override
    public int create(Chat chat) throws SQLException {
        Connection con = Database.connect();
        String sql = "insert into messenger.Chat(CreatorIDEmail, Chatname) values(?,?)";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setString(1,chat.getCreatorIDEmail());
        preparedStatement.setString(2,chat.getChatName());

        int result = preparedStatement.executeUpdate();
        return result;
    }

    @Override
    public List<Chat> get() throws SQLException {
        Connection con = Database.connect();
        List<Chat> chats= null;
        String sql = "select * from Chat;";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int ID = rs.getInt("ID");
            String chatName = rs.getString("Chatname");
            String creatorIDEmail = rs.getString("CreatorIDEmail");
            Chat chat = new Chat(ID, creatorIDEmail, chatName);
            chats.add(chat);
        }
        return chats;
    }
    @Override
    public int edit(Chat chat) throws SQLException {
        Connection con = Database.connect();
        String sql = "update Chat set Chatname = ?";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setString(1,chat.getChatName());

        int result = preparedStatement.executeUpdate();
        return result;
    }
    @Override
    public int remove(Chat chat) throws SQLException {
        Connection con = Database.connect();
        String sql = "delete from Chat where ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,chat.getID());
        int result = preparedStatement.executeUpdate();
        return result;
    }

}