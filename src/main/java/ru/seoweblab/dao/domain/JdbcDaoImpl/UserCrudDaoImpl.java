package ru.seoweblab.dao.domain.JdbcDaoImpl;


import ru.seoweblab.dao.domain.documentsDaoJdbc.UserAccountDao;
import ru.seoweblab.model.UserAccount;
import ru.seoweblab.service.connection.ConnectionDataBaseFactory;
import ru.seoweblab.service.crypt.CryptPasswordImpl;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserCrudDaoImpl implements UserAccountDao {

    Connection connection;
    //language=SQL
    private final String SQL_SELECT_ALL = " SELECT * from user_account";
    //language=sql
    private final String SQL_ADD_USER = "INSERT INTO user_account(name, password) VALUES (?,?)";
    //language=sql
    private final String SQL_DELETE_USER = "DELETE FROM user_account where id = ? ";
    //language=sql
    private final String SQL_UPDATE_USER = "UPDATE user_account SET name=?, password = ? WHERE user_account.id = ?";



    @Override
    public void save(UserAccount model) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserAccount model, Integer id) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            connection = ConnectionDataBaseFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isExist(String name, String password) {

        List<UserAccount> userList = findAll();
        for (UserAccount user : userList) {
            boolean matched = new CryptPasswordImpl().checkPassword(password, user.getPassword());
            if (user.getName().equals(name) && matched) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserAccount> findAll() {
        try {
            List<UserAccount> userList = new ArrayList<>();
            connection = ConnectionDataBaseFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                UserAccount userAccount = new UserAccount(name, password);
                userList.add(userAccount);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}