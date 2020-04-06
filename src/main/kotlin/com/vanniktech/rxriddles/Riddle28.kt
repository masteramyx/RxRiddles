package com.vanniktech.rxriddles

import io.reactivex.Completable
import io.reactivex.Observable
import java.util.concurrent.atomic.AtomicInteger

object Riddle28 {
  /**
   * Call the given [function] when the [source] completes.
   *
   * Use case: Add some logging.
   */
  fun solve(source:  Observable<AtomicInteger>, function: () -> Unit): Completable {
    return Completable.fromObservable(source.doOnComplete(function))
  }
}
