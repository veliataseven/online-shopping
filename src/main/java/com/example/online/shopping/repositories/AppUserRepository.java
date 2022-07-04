package com.example.online.shopping.repositories;

import com.example.online.shopping.models.AppUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    static AppUserRepository getInstance() {
        return new AppUserRepository() {
            @Override
            public boolean existsByEmailAndPassword(String email, String password) {
                return false;
            }

            @Override
            public Optional<AppUser> findByEmail(String email) {
                return Optional.empty();
            }

            @Override
            public boolean existsByEmail(String email) {
                return false;
            }

            @Override
            public Optional<AppUser> findByEmailAndPassword(String email, String password) {
                return Optional.empty();
            }

            @Override
            public List<AppUser> findAll() {
                return null;
            }

            @Override
            public List<AppUser> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<AppUser> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends AppUser> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends AppUser> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends AppUser> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<AppUser> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public AppUser getOne(Long aLong) {
                return null;
            }

            @Override
            public AppUser getById(Long aLong) {
                return null;
            }

            @Override
            public <S extends AppUser> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends AppUser> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<AppUser> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends AppUser> S save(S entity) {
                return null;
            }

            @Override
            public Optional<AppUser> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(AppUser entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends AppUser> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends AppUser> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends AppUser> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends AppUser> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends AppUser> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends AppUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

    boolean existsByEmailAndPassword(String email, String password);

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<AppUser> findByEmailAndPassword(String email, String password);
}
