package edu.tsystems.javaschool.logapp.api.dao.implementation;

import edu.tsystems.javaschool.logapp.api.dao.CityDao;
import edu.tsystems.javaschool.logapp.api.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CityDaoImpl implements CityDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CityDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<City> getAllCities() {
        Session session = this.sessionFactory.getCurrentSession();
        List<City> cityList = session.createQuery("from City").list();
        return cityList;
    }

    @Override
    @Transactional
    public City getCityById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.load(City.class, id);
        return session.load(City.class, id);
    }
}
