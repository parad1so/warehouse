package User;

import com.warehouse.dao.domain.JdbcDaoImpl.UserCrudDaoImpl;
import com.warehouse.model.UserAccount;
import com.warehouse.service.crypt.CryptPasswordImpl;
import com.warehouse.dao.domain.documentsDaoJdbc.UserAccountDao;


public class AddUser {
    public static void main(String[] args) {
        //UserAccountDao userAccount = new UserCrudDaoImpl();
        String password = new CryptPasswordImpl().cryptPassword("1234");
        UserAccount userAccount1 = new UserAccount("serg", password);
        //userAccount.save(userAccount1);

        System.out.println(new CryptPasswordImpl().cryptPassword("1234"));
    }

}
