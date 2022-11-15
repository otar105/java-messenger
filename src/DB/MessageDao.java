package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MessageDao implements DAO<Message> {

    @Override
    public int create(Message message) throws SQLException {
        Connection con = Database.connect();
        String sql = "insert into messenger.Message(messageText, userIdEmail, chatId,sendDate) values(?,?,?,?)";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setString(1,message.getMessageText());
        preparedStatement.setString(2,message.getUserIdEmail());
        preparedStatement.setString(3,message.getChatId());
        preparedStatement.setString(4,message.getSendDate());

        int result = preparedStatement.executeUpdate();
        return result;
    }

    @Override
    public List<Message> get() throws SQLException {
        Connection con = Database.connect();
        List<Message> messages= null;
        String sql = "select * from Message;";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int ID = rs.getInt("ID");
            String messageText = rs.getString("messageText");
            String userIdEmail = rs.getString("userIdEmail");
            String chatId = rs.getString("chatId");
            String sendDate = rs.getString("sendDate");
            Message message = new Message(ID, messageText, userIdEmail, chatId,sendDate);
            messages.add(message);
        }
        return messages;
    }

    @Override
    public int edit(Message message) throws SQLException {
        return 0;
    }
    @Override
    public int remove(Message message) throws SQLException {
        return 0;
    }

}