package br.olinda.factory.model.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository <C,KEY> {

    public void create(C c) throws SQLException;
    public C read(KEY k) throws SQLException;

    public List<C> readAll() throws SQLException;

}