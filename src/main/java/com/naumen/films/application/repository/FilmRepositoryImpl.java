package com.naumen.films.application.repository;

import com.naumen.films.application.model.data.entities.Film;
import com.naumen.films.application.model.data.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.naumen.films.application.utils.Utils.loadAllData;

@Component
public class FilmRepositoryImpl implements FilmRepository {

    @Override
    public Film findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Film.class, id);
    }

    @Override
    public void saveFilm(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction tx1 = session.beginTransaction();
        session.save(film);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateFilm(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction tx1 = session.beginTransaction();
        session.update(film);
        tx1.commit();
        session.close();
    }

    @Override
    public <S extends Film> S save(S s) {
        return null;
    }

    @Override
    public <S extends Film> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Film> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Film> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        return loadAllData(Film.class, session);
    }

    @Override
    public Iterable<Film> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Film film) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Film> entities) {

    }

    @Override
    public void deleteFilm(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction tx1 = session.beginTransaction();
        session.delete(film);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteAll() {

    }
}