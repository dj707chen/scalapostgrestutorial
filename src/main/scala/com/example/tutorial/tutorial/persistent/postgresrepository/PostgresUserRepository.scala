package com.example.tutorial.tutorial.persistent.postgresrepository

import com.example.tutorial.tutorial.domain.User
import com.example.tutorial.tutorial.persistent.UserRepository
import doobie.free.connection.ConnectionIO
import doobie.implicits._

class PostgresUserRepository extends UserRepository[ConnectionIO]{
  override def find(id: BigDecimal): ConnectionIO[Option[User]] =
    PostgresUserRepository.select(id).option

  override def add(u: User): ConnectionIO[Unit] =
    PostgresUserRepository.add(u).run.map(_ => ())

}
object PostgresUserRepository{
  def select(id: BigDecimal): doobie.Query0[User] =
    sql"""
         | SELECT id, name, email FROM  "user" WHERE id = $id
         |""".stripMargin.query[User]

  def add(u: User): doobie.Update0 =
    sql"""
         | INSERT INTO "user" (id, name, email) VALUES (${u.id}, ${u.name}, ${u.email})
         |""".stripMargin.update
}