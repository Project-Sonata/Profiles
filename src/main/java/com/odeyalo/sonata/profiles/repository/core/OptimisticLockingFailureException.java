package com.odeyalo.sonata.profiles.repository.core;

/**
 * Exception thrown on an optimistic locking violation.
 * This exception will be thrown either by O/R mapping tools or by custom DAO implementations.
 * Optimistic locking failure is typically not detected by the database itself.
 */
public final class OptimisticLockingFailureException extends RuntimeException {
}
