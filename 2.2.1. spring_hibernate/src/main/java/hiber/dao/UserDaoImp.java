package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user, Car car) {
        user.setCar(car);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        String hql = "From User u Where car.model =:model and car.series =:series";
        TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter("model", model).setParameter("series", series);
        return userTypedQuery.getSingleResult();
    }

}
