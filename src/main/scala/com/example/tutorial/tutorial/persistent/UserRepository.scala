package com.example.tutorial.tutorial.persistent

import com.example.tutorial.tutorial.domain.User

trait UserRepository[F[_]] {
  def add(u: User): F[Unit]

  def find(id: BigDecimal): F[Option[User]]
}
