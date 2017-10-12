package pl.j2dev.dao;

import java.util.List;

public interface IDao<T> {

    final static String DBURL = "jdbc:mysql://192.166.218.87/j2bills_test";
    final static String DBUSER = "root";
    final static String DBPASS = "j2dev123";
    final static String DBDRIVER = "com.mysql.jdbc.Driver";

    /**
     * @return every object from database of type T
     */
    List<T> getList();

    /**
     * @param id ID of object in DB
     * @return object of type T
     */
    T getById(long id);

    /**
     *
     * @param limit how many object return
     * @param offset from which object start returning (sorted by id from newest)
     * @return return list of object from DB
     */
    List<T> getList(int limit, int offset);

    /**
     * method to add object to DB
     * @param object object which will added to DB
     * @return true id if added successfully, otherwise 0
     */
    long add(T object);

    /**
     * delete object from database
     * @param object object which will be deleted from DB
     * @return true if deleted successfully, otherwise false
     */
    boolean delete(T object);
	
}
