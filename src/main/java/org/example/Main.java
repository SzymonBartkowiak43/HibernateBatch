package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.hibernate.jpa.HibernatePersistenceConfiguration;

public class Main {
  public static void main(String[] args) {
    var sessionFactory =
      new HibernatePersistenceConfiguration("Bookshelf")
        .managedClass(Book.class)
        .jdbcUrl("jdbc:postgresql://localhost:5432/bookshelf")
        .jdbcCredentials("hibernate", "hibernate")
        .jdbcPoolSize(16)
        .showSql(true, true, true)
        .property("hibernate.jdbc.batch_size", "5")
        .property("hibernate.order_inserts", "true")
        .createEntityManagerFactory();

    // export the inferred database schema
    sessionFactory.getSchemaManager().create(true);

    // persist an entity
    sessionFactory.inTransaction(session -> {
      session.setJdbcBatchSize(5);

      for (int i = 0; i < 25; i++) {
        session.persist(new Book("ISBN" + i, "Book " + i));
      }
    });
  }
}
