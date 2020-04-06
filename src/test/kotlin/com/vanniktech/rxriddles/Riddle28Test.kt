package com.vanniktech.rxriddles

import com.vanniktech.rxriddles.solutions.Riddle28Solution
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.PublishSubject
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

/** Solution [Riddle28Solution] */
class Riddle28Test {
  @Test fun onComplete() {
    val subject = PublishSubject.create<AtomicInteger>()
    val atomicInteger = AtomicInteger()

    val o = Riddle28.solve(subject) { atomicInteger.incrementAndGet() }
        .test()
        .assertEmpty()

    subject.onNext(atomicInteger)
    assertThat(atomicInteger.get()).isEqualTo(0)

    subject.onComplete()
    assertThat(atomicInteger.get()).isEqualTo(1)
    o.assertResult()
  }

  @Test fun onError() {
    val subject = PublishSubject.create<AtomicInteger>()
    val atomicInteger = AtomicInteger()

    val o = Riddle28.solve(subject) { atomicInteger.incrementAndGet() }
        .test()
        .assertEmpty()

    subject.onError(RuntimeException("error"))
    assertThat(atomicInteger.get()).isEqualTo(0)
    o.assertFailure(RuntimeException::class.java)
  }
}
