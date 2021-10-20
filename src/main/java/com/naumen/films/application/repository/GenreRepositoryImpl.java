package com.naumen.films.application.repository;

import com.naumen.films.application.model.data.entities.Genre;
import com.naumen.films.application.model.data.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.naumen.films.application.utils.Utils.loadAllData;

@Component
public class GenreRepositoryImpl implements GenreRepository {

    @Override
    public Genre findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Genre.class, id);
    }

    @Override
    public void saveGenre(Genre genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction tx1 = session.beginTransaction();
        session.save(genre);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateGenre(Genre genre) {

    }

    @Override
    public void deleteGenre(Genre genre) {

    }

    @Override
    public <S extends Genre> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Genre> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Genre> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Genre> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        return loadAllData(Genre.class, session);
    }

    @Override
    public Iterable<Genre> findAllById(Iterable<Integer> integers) {
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
    public void delete(Genre entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Genre> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
