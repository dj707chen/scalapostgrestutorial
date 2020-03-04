package com.example.tutorial.tutorial.common.persistent

import cats.effect.{Async, Blocker, ContextShift}
import doobie.util.ExecutionContexts
import doobie.util.transactor.Transactor
import doobie.util.transactor.Transactor.Aux

object SingleConnection {
  def xa[F[_]](implicit cs: ContextShift[F], async: Async[F]): Aux[F, Unit] =
    Transactor.fromDriverManager[F](
      driver = "org.postgresql.Driver",
      url = "jdbc:postgresql:world",
      user = "postgres",
      pass = "",
      blocker = Blocker.liftExecutionContext(ExecutionContexts.synchronous)
    )

}
