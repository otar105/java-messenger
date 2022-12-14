package DB;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements DAO<User> {

    @Override
    public int create(User user) throws SQLException {
        Connection con = Database.connect();
        String sql = "insert into users (email,username,password,currentchatid) values(?,?,?,?)";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setString(1,user.getEmail());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setInt(4,user.getCurrentchatid());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    @Override
    public List<User> get() throws SQLException {
        Connection con = Database.connect();
        List<User> users = null;
        users = new ArrayList<User>();
        String sql = "select * from users;";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int ID = rs.getInt("ID");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int currentchatid = rs.getInt("currentchatid");
            User user = new User(email,username,password,currentchatid);
            user.setID(ID);
            users.add(user);
        }
        return users;
    }

    @Override
    public int edit(User user) throws SQLException {
        Connection con = Database.connect();
        String sql = "update users set username = ?, password = ? where email = ?";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getEmail());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    @Override
    public int remove(User user) throws SQLException {
        Connection con = Database.connect();
        String sql = "delete from users where email = ?";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public List<User> getUsersByChatId(int chatId) throws SQLException {
        Connection con = Database.connect();
        List<User> users = null;
        String sql = "SELECT * FROM Chat ,Userr \n" +
                "JOIN UserChatIsMemberOf ON UserChatIsMemberOf.UserIDEmail = userr.UserIDEmail \n" +
                "AND UserChatIsMemberOf.ChatId = Chat.ChatId\n" +
                "WHERE Chat.ChatId = ?";
        PreparedStatement preparedStatement =  con.prepareStatement(sql);
        preparedStatement.setInt(1,chatId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            int ID = rs.getInt("ID");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int currentchatid = rs.getInt("currentchatid");
            User user = new User(email,username,password,currentchatid);
            users.add(user);
        }
        return users;
    }
}