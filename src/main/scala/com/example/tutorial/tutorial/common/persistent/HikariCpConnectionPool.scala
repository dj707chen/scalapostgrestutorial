package com.example.tutorial.tutorial.common.persistent

import cats.effect.{Async, Blocker, ContextShift, Resource}
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts

object HikariCpConnectionPool {
  def loadTransactor[F[_]: Async: ContextShift](
      driver: String,
      url: String,
      user: String,
      password: String): Resource[F, HikariTransactor[F]] =
    for {
      ce <- ExecutionContexts.fixedThreadPool[F](4)
      xa <- HikariTransactor.newHikariTransactor[F](
        driver,
        url,
        user,
        password,
        ce,
        Blocker.liftExecutionContext(ce)
      )
    } yield xa
}
