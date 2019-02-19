package uk.co.transferx.app.util.schedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


/**
 * Implementation of the [BaseSchedulerProvider] making all [Scheduler]s execute
 * synchronously so we can easily run assertions in our tests.
 *
 *
 * To achieve this, we are using the [io.reactivex.internal.schedulers.TrampolineScheduler] from the [Schedulers] class.
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {

    @NonNull
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    @NonNull
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    @NonNull
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}